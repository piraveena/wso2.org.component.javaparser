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
        if(args[0]==null){
            System.out.println("path name is missing");
        } else {
            String path = args[0];
            WSO2JavaParser wso2JavaParser = new WSO2JavaParser();
            List internalPaths = wso2JavaParser.getComponentPaths(path);


            int directoryPaths = internalPaths.size();

            for (int i = 0; i < directoryPaths; i++) {
                List<String> fileNames = wso2JavaParser.getInternalFiles(internalPaths.get(i).toString());
                for (int x = 0; x < fileNames.size(); x++) {
                    wso2JavaParser.setFilePath(fileNames.get(x));
                }
            }
            System.out.println("Number of internal directories : "+ directoryPaths);
        }

        //String path="src" + File.separator +File.separator + "resources" + File.separator;
        //WSO2JavaParser wso2JavaParser =new WSO2JavaParser();
        //wso2JavaParser.setFilePath(path+"IdentityPasswordHistoryServiceComponent.java");

    }

}
