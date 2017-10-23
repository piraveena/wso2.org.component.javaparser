package wso2.org;

import java.io.IOException;

/**
 *
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        WS02JavaParser ws02JavaParser=new WS02JavaParser();
        ws02JavaParser.setFilePath("/home/dineth/wso2-test/16_10/wso2orgcomponentjavaparser/src/resources/IdentityRecoveryServiceComponent.java");
    }
}
