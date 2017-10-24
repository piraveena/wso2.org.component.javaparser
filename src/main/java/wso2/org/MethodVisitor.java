package wso2.org;

import com.github.javaparser.Position;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MethodVisitor extends VoidVisitorAdapter<Void> {

    private Map<String,Integer> methodNames=new HashMap<>();

    /**
     * this method will be called for all methods in this CompilationUnit, including inner class methods
     * @param n
     * @param arg
     */
    @Override
    public void visit(MethodDeclaration n, Void arg) {
        Optional<Position> methodPosition=n.getBegin();
        methodNames.put(n.getName().asString(),methodPosition.get().line);
        super.visit(n, arg);
    }

    public Map<String,Integer> getComponentMethods(){
        for (String name: methodNames.keySet()){
            String key =name.toString();
            String value = methodNames.get(name).toString();
            System.out.println(key + " " + value);
        }
        return methodNames;
    }
}