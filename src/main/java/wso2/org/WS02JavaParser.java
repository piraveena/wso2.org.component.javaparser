package wso2.org;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class WS02JavaParser {

    public void setFilePath(String path) throws IOException {

        Object element =null;

        FileInputStream in=new FileInputStream(path);
        CompilationUnit cu= JavaParser.parse(in);
        cu.accept(new MethodVisitor(),null);

        List commentList=cu.getComments();
        ListIterator itr= commentList.listIterator();
        while(itr.hasNext()) {
            element = itr.next();
            System.out.print(element + " ");
            break;
        }
        if(element!=null) {
        }
    }

    private static class MethodVisitor extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(MethodDeclaration n, Void arg) {
            /* here you can access the attributes of the method.
             this method will be called for all methods in this
             CompilationUnit, including inner class methods */
            System.out.println(n.getName());
            super.visit(n, arg);
        }
    }


   /* private void getAnnotationList(String scrComment){
        String[] scrElementList=scrComment.split(" ");
       // List<String> scrElementList = new ArrayList<String>(Arrays.asList(scrCommentElements));
        int referenceTagNo=0;
        

        for(int i=0;i<scrElementList.length;i++){
            if(scrElementList[i].equals(COMPONENT_ANNOTATION)){
                while(!scrElementList[i].equals(REFERENCE_ANNOTATION)){
                    if(scrElementList[i].equals(NAME)){
                        wso2ComponentAnnotation.setComponentName(scrElementList[i]);
                    }else if(scrElementList[i].equals(IMMEDIATE)){
                        wso2ComponentAnnotation.setComponentName(scrElementList[i]);
                    }
                    i++;
                }
            }else if(scrElementList[i].equals(REFERENCE_ANNOTATION)){
                WSO2ReferenceAnnotation wso2ReferenceAnnotation =new WSO2ReferenceAnnotation();
                do{
                    i++;
                    if(scrElementList[i].equals(NAME)){
                        wso2ReferenceAnnotation.setReferenceName(scrElementList[i]);
                    }else if(scrElementList[i].equals(INTERFACE)) {
                        wso2ReferenceAnnotation.setService(scrElementList[i]);
                    }else if(scrElementList[i].equals(CARDINALITY)){
                        wso2ReferenceAnnotation.setCardinality(scrElementList[i]);
                    }else if(scrElementList[i].equals(POLICY)){
                        wso2ReferenceAnnotation.setPolicy(scrElementList[i]);
                    }else if(scrElementList[i].equals(BIND)){
                        wso2ReferenceAnnotation.setBind(scrElementList[i]);
                    }else if(scrElementList[i].equals(UNBIND)){
                        wso2ReferenceAnnotation.setUnbind(scrElementList[i]);
                    }
                }while(scrElementList[i].equals(REFERENCE_ANNOTATION));
               wso2ReferenceAnnotationlist.add(referenceTagNo,wso2ReferenceAnnotation);
                referenceTagNo++;
            }
        }

    }

    public List<WSO2ReferenceAnnotation> getWSO2ReferenceAnnotation(){
        return this.wso2ReferenceAnnotationlist;
    }

    public WSO2ComponentAnnotation getWSO2ComponentAnnotation() {
         return this.wso2ComponentAnnotation;
    }


    private void printWSO2ReferenceAnnotation(List<WSO2ReferenceAnnotation> wso2ReferenceAnnotationlist){
        ListIterator itr= wso2ReferenceAnnotationlist.listIterator();
        Object element =null;
        while(itr.hasNext()) {
            element = itr.next();
            System.out.print(element + " ");
            break;
        }
    }*/

}
