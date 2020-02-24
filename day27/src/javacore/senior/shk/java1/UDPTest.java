package javacore.senior.shk.java1;

import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

/**
 * UDPd协议的网络编程
 * @author shkstart
 * @create 2019 下午 4:34
 */
public class UDPTest {

    //发送端
    @Test
    public void sender() throws IOException {

        DatagramSocket socket = new DatagramSocket();



        String str = "我是UDP方式发送的导弹";
        byte[] data = str.getBytes();
        InetAddress inet = InetAddress.getLocalHost();
        DatagramPacket packet = new DatagramPacket(data,0,data.length,inet,9090);

        socket.send(packet);

        socket.close();

    }
    //接收端
    @Test
    public void receiver() throws IOException {

        DatagramSocket socket = new DatagramSocket(9090);

        byte[] buffer = new byte[100];
        DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length);

        socket.receive(packet);

        System.out.println(new String(packet.getData(),0,packet.getLength()));

        socket.close();
    }

    @Test
    public void test3() throws UnsupportedEncodingException {
        String str1 = "中国";
        byte[] bytes = str1.getBytes();//使用默认的编码方式，取决于当前文件的编码方式

        for (int i = 0; i < bytes.length; i++) {
            if(i != bytes.length - 1){
                System.out.print(bytes[i] + ",");
                continue;
            }
            System.out.println(bytes[i]);
        }//-28,-72,-83,-27,-101,-67

        byte[] bytes1 = new byte[]{-28,-72,-83,-27,-101,-67};

        //offset表示角标的偏移量
        String str = new String(bytes,3,3, StandardCharsets.UTF_8);//国

        System.out.println(str);

        String str2 = new String(bytes1,3,3);//国
        System.out.println(str2);
    }

}
