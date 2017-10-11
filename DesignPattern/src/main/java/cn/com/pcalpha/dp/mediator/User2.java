package cn.com.pcalpha.dp.mediator;

/**
 * Created by caiyida on 2016/10/1.
 */
public class User2 extends User {

    public User2(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void work() {
        System.out.println("User2 work");
    }
}
