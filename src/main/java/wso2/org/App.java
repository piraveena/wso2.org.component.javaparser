package wso2.org;

import java.io.IOException;
import java.util.List;

/**
 *
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        WS02JavaParser ws02JavaParser=new WS02JavaParser();
        FileChanger fileChanger=new FileChanger();
        //fileChanger.writeInComponentFile("abc",4,filePath);
        List internalPaths=ws02JavaParser.getComponentPaths("/home/dineth/wso2-test/identity-governance/component.xml");
        for(int i=0;i<internalPaths.size();i++) {
            String fileName=null;
            if(internalPaths.get(i).toString()!=null) {
                fileName = ws02JavaParser.getInternalFiles(internalPaths.get(i).toString());
            }
            if(fileName!=null) {
                ws02JavaParser.setFilePath(fileName);
            }
        }
    }





}




