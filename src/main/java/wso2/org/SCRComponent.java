package wso2.org;

import java.util.ArrayList;
import java.util.List;

public class SCRComponent {

    private  String componentName;
    private  String immediate;
    private  List<SCRReference> scrReferences = new ArrayList<SCRReference>();

    public SCRComponent(String componentName, String immediate ){
        this.componentName = componentName;
        this.immediate = immediate;
    }

    public void addReferences(SCRReference scrReference){
        scrReferences.add(scrReference);
    }

    public String getComponentAnnoation(){
        return null;
    }
}
