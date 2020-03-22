/**
 * @author hskBeginner Email：2752962035@qq.com
 * @version 1.0
 * @description
 * @create 2020年03月22日 07时55分40秒
 */module day30 {

    requires java9test;
    requires junit;
    //虽然当前模块已经有junit依赖
    //但是使用java9模块化功能后，还必须显示的声明注册一哈，我要依赖junit，否则@Test注解报错
    //模块化的好处是能够根据模块的需要加载程序运行时需要的class，按需加载（类似懒加载）使得模块更加轻量级
    requires java.net.http;

}