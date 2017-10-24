package wso2.org;

public class SCRReference {

    private String referenceName;
    private String service;
    private String cardinality;
    private String policy;
    private String unbind;

    public String getBind() {
        return bind;
    }

    public void setBind(String bind) {
        this.bind = bind;
    }

    private String bind;

    public SCRReference(){
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getCardinality() {
        return cardinality;
    }

    public void setCardinality(String cardinality) {
        this.cardinality = cardinality;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getUnbind() {
        return unbind;
    }

    public void setUnbind(String unbind) {
        this.unbind = unbind;
    }
}
