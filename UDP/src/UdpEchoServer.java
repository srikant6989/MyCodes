import java.net.*; 
import java.io.*; 
import java.util.*; 
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
public class UdpEchoServer {   
  static int echoPort = 7000;  
  static int msgLen = 1024; 
  public static void main(String args[]) {     
    try { 
      DatagramSocket sock = new DatagramSocket(echoPort); 
      DatagramPacket p, reply; 
      byte msg[] = new byte[msgLen]; 
p = new DatagramPacket(msg,msgLen); 
      for (;;) { 
          System.out.println(" I am here ");
                  
        sock.receive(p); 
        System.out.println(p.getAddress().toString()); 
        reply = new DatagramPacket(p.getData(), p.getLength(), 
                                   p.getAddress(), p.getPort());
        sock.send(reply);
      } 
    } catch (Exception e) {} }}