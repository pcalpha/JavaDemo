package cn.com.pcalpha.multithread.thread.conn01;

import java.util.ArrayList;
import java.util.List;

public class ListAdd1 {

    private volatile static List list = new ArrayList();

    public void add() {
        list.add("cddd");
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        final ListAdd1 list1 = new ListAdd1();
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        list1.add();
                        System.out.println("��ǰ�̣߳�" + Thread.currentThread().getName() + "�����һ��Ԫ��");
                        Thread.sleep(500);
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }

            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    if (list1.size() == 5) {
                        System.out.println("��ǰ�̣߳�" + Thread.currentThread().getName() + "list size = 5,�߳�ֹͣ");
                        throw new RuntimeException();
                    }
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
