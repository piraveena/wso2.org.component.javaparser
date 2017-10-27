package wso2.org;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.MemberValuePair;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassVisitor extends VoidVisitorAdapter<Void> {

    private SCRComponent scrComponent;

    @Override
    public void visit(ClassOrInterfaceDeclaration n, Void arg) {
        super.visit(n, arg);
        n.getName();
        n.remove(n.getComment().get());
        n.addAnnotation(addComponentAnnotation(scrComponent));
    }

    public void setScrComponent(SCRComponent scrComponent){
        this.scrComponent=scrComponent;
    }
    private NormalAnnotationExpr addComponentAnnotation(SCRComponent scrComponent) {
        NodeList<MemberValuePair> nodeList = new NodeList<>();

        NormalAnnotationExpr normalAnnotationExpr = new NormalAnnotationExpr(new Name("Component"), nodeList);
        char c='"';
        normalAnnotationExpr.addPair("\n         name", c+scrComponent.getComponentName()+c);
        normalAnnotationExpr.addPair("\n         immediate", scrComponent.getImmediateName());
        return normalAnnotationExpr;
    }
}
