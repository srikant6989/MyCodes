import java.net.*;  
import java.io.*;  
import java.util.*;  
public class UDPEchoClient { 
  static int echoPort = 7000;  
  static int msgLen = 16;  
  static int timeOut=1000; 
  public static void main(String args[]) { 
    try { 
      DatagramSocket sock = new DatagramSocket(); 
      DatagramPacket pak; 
      byte msg[] = new byte[msgLen]; 
      InetAddress echoHost = InetAddress.getByName("localhost"); 
      pak = new DatagramPacket(msg,msgLen,echoHost,echoPort); 
      sock.send(pak);
      System.out.println("sent");
      sock.setSoTimeout(timeOut); 
      sock.receive(pak); 
    } 
    catch (InterruptedIOException e) {System.out.println("Timeout");} 
    catch (Exception e) {} 
  } 
}  