package javacore.senior.knowledge;

/**
 * @author hskBeginner Email：2752962035@qq.com
 * @version 1.0
 * @description 单例模式之懒汉式
 * @create 2020年02月21日 02时44分36秒
 */
public class Singleton {

    private static Singleton instance;//共享数据，多个线程异步的操作（指读写操作）共享数据有可能会存在线程安全问题

    private Singleton(){
//        super();
    }

    //实际的一个场景，getInstance方法被包含在run方法里面，被多个线程并发执行
    public static Singleton getInstance(){
        //方式一：效率稍差
//        synchronized (Singleton.class) {
//            if(instance == null)
//                instance = new Singleton();
//            return instance;
//        }
        //方式二：优化版
        if(instance == null){
            synchronized (Singleton.class) {
                if(instance == null)
                    instance = new Singleton();
            }
        }
        return instance;
    }

}
