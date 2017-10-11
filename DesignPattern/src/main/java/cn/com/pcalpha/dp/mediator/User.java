package cn.com.pcalpha.dp.mediator;

/**
 * Created by caiyida on 2016/10/1.
 */
public abstract class User {
    private Mediator mediator;

    public User(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract void work();
}
