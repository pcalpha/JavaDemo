package cn.com.pcalpha.dp.visitor;

/**
 * Created by caiyida on 2016/10/1.
 */
public interface Subject {
    public void accept(Visitor visitor);
    public String getSubject();
}
