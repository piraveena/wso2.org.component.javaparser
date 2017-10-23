package wso2.org;

public class WSO2ComponentAnnotation {
    private String componentName;

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentImmediate() {
        return componentImmediate;
    }

    public void setComponentImmediate(String componentImmediate) {
        this.componentImmediate = componentImmediate;
    }

    private String componentImmediate;
}
