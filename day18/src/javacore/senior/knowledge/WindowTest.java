package javacore.senior.knowledge;

/**
 *
 * 开启三个从线程模拟三个窗口售票，票的总数为100张
 * 三个线程做同一个事情（同一个run方法）
 * 存在重票问题，线程安全问题，待解决
 *
 * @author hskbeginner
 * @create 2020/2/12
 */
public class WindowTest {

    public static void main(String[] args) {
        //创建三个线程
        Thread t1 = new Window();
        Thread t2 = new Window();
        Thread t3 = new Window();

        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");

        //分别开启上面创建的三个线程
        t1.start();
        t2.start();
        t3.start();
    }

}

class Window extends Thread{

//    private int ticket = 100;
    //静态属性 随着类的加载而加载了 只占据一块内存空间 共享数据
    private static int ticket = 100;

    @Override
    public void run() {
        while(true){
            if(ticket > 0){
                System.out.println(getName() + "售票，票号为：" + ticket--);
            }else{
                break;
            }
        }
    }

}
