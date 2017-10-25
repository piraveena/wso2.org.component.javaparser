package wso2.org;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class WS02JavaParser {

    private SCRComponent scrComponent;
    private boolean isScrAnnotation;
    FileInputStream in =null;
    private MethodVisitor methodVisitor;

    public void setFilePath(String path) throws IOException {

        Object element = null;
        this.in= new FileInputStream(path);
        CompilationUnit cu = JavaParser.parse(in);
         methodVisitor=new MethodVisitor();
         cu.accept(methodVisitor,null);
         methodVisitor.getComponentMethods();

        List commentList = cu.getComments();
        ListIterator itr = commentList.listIterator();
        while (itr.hasNext()) {
            element = itr.next();
            isScrAnnotation = checkScrComponentAnnotation(element.toString());
            if (isScrAnnotation) {
                System.out.print(element.toString());
                String[] scrElementList = element.toString().split("@");
                List<String> list = new ArrayList();
                for (String x : scrElementList) {
                    if (x.contains("scr")) {
                        x = x.replaceAll("/", "");
                        x = x.replaceAll("\\*", "");
                        list.add(x);
                    }
                }
                for (String x : list) {
                    if (x.contains("component")) {
                        getScrAnnotation(x);
                    } else if (x.contains("reference")) {
                        scrComponent.addReferences(getReference(x));
                    } else {
                        System.out.print("error");
                    }
                }
                break;
            }
        }

    }

    private boolean checkScrComponentAnnotation(String comment) {
        String patternString = "\\**^(.)*\\*\\s(.)*[@scr.component]+";
        Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(comment);
        return matcher.lookingAt();
    }

    private void getScrAnnotation(String x) {

        String immediate = null;
        String name = null;
        Pattern p = Pattern.compile("immediate=\"([^\"]*)\"");
        Matcher m = p.matcher(x);
        while (m.find()) {
            immediate = m.group(1);
        }
        Pattern pname = Pattern.compile("name=\"([^\"]*)\"");
        Matcher mname = pname.matcher(x);
        while (mname.find()) {
            name = mname.group(1);
        }
        System.out.println("name: " + name + " immediate :" + immediate + "\n");
        this.scrComponent = new SCRComponent(name, immediate);
    }

    private SCRReference getReference(String reference) {

        SCRReference scrReference = new SCRReference();
        Pattern p = Pattern.compile("name=\"([^\"]*)\"");
        Matcher m = p.matcher(reference);
        while (m.find()) {
            scrReference.setReferenceName(m.group(1));
            System.out.println("Reference Name: " + scrReference.getReferenceName());
        }
        Pattern q = Pattern.compile("interface=\"([^\"]*)\"");
        Matcher x = q.matcher(reference);
        while (x.find()) {
            scrReference.setService(x.group(1));
            System.out.println("Service: " + scrReference.getService());
        }
        Pattern c = Pattern.compile("cardinality=\"([^\"]*)\"");
        Matcher d = c.matcher(reference);
        while (d.find()) {
            scrReference.setCardinality(d.group(1));
            System.out.println("Cardinality: " + scrReference.getCardinality());
        }
        Pattern cs = Pattern.compile("policy=\"([^\"]*)\"");
        Matcher ds = cs.matcher(reference);
        while (ds.find()) {
            scrReference.setPolicy(ds.group(1));
            System.out.println("Policy: " + scrReference.getPolicy());
        }
        Pattern csd = Pattern.compile("unbind=\"([^\"]*)\"");
        Matcher dsd = csd.matcher(reference);
        while (dsd.find()) {
            scrReference.setUnbind(dsd.group(1));
            System.out.println("unbind: " + scrReference.getUnbind());
        }
        Pattern csdx = Pattern.compile("bind=\"([^\"]*)\"");
        String dsq = reference.replace("unbind", "***");
        Matcher dsdx = csdx.matcher(dsq);
        while (dsdx.find()) {
            scrReference.setBind(dsdx.group(1));
            System.out.println("bind: " + scrReference.getBind());
        }
        System.out.println("\n");
        return scrReference;
    }

    public String getInternalFiles(String directoryPath){
        File folder = new File( directoryPath);
        File[] listOfFiles = folder.listFiles();
        String fileName=null;
        if(listOfFiles!=null) {
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    fileName = directoryPath + "/" + listOfFiles[i].getName();
                    System.out.println(fileName);
                }
            }
        }
        return fileName;
    }

    public List<String> getComponentPaths(String file){
        List<String> paths=new ArrayList<>();
        try {
            File inputFile = new File(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            String mainNode=doc.getDocumentElement().getNodeName();
            System.out.println(mainNode);
            NodeList nodeList=doc.getElementsByTagName("Path");
            System.out.println(nodeList.getLength());
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                paths.add(nodeList.item(temp).getTextContent());
                System.out.println(nodeList.item(temp).getTextContent());
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.xml.sax.SAXException e) {
            e.printStackTrace();
        }
        return paths;
    }

    private int getLineNumber(SCRReference scrReference){
        Map<String,Integer> methods=methodVisitor.getComponentMethods();
        int lineNumber=methods.get(scrReference.getBind());
        return lineNumber;
    }

    private void addAnnotation(String annotation,int lineNumber,String filePath){
        FileChanger fileChanger=new FileChanger();
        try {
            fileChanger.writeInComponentFile(annotation,lineNumber,filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

