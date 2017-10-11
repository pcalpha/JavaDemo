package cn.com.pcalpha.dp.mediator;

/**
 * Created by caiyida on 2016/10/1.
 */
public class User1 extends User {
    public User1(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void work() {
        System.out.println("User1 work");
    }
}
