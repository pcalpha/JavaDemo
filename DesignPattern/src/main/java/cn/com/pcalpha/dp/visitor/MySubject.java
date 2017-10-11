package cn.com.pcalpha.dp.visitor;

import observer.*;

/**
 * Created by caiyida on 2016/10/1.
 */
public class MySubject implements Subject {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getSubject() {
        return "love";
    }
}
