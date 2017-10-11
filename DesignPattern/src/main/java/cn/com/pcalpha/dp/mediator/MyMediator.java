package cn.com.pcalpha.dp.mediator;

/**
 * Created by caiyida on 2016/10/1.
 */
public class MyMediator implements Mediator {
    private User user1;
    private User user2;

    @Override
    public void createMediator() {
        user1 = new User1(this);
        user2 = new User2(this);
    }

    @Override
    public void workAll() {
        user1.work();
        user2.work();
    }

}
