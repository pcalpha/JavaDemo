package cn.com.pcalpha.dp.visitor;

/**
 * Created by caiyida on 2016/10/1.
 */
public class MyVisitor implements Visitor {
    @Override
    public void visit(Subject subject) {
        System.out.println("MyVisitor"+subject.getSubject());
    }
}
