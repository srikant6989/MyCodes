import java.net.*;  
import java.io.*;  
import java.util.*;
public class Receive {
    
    static int port = 6000;
    public static void main(String argv[]) throws InterruptedException, UnknownHostException, IOException { 
     
        while(true)
        {
           
        
      Socket sock = new Socket("127.0.0.1", port);
      BufferedReader din = new BufferedReader( 
        new InputStreamReader( sock.getInputStream() )); 
      String metric = din.readLine(); 
      System.out.println(metric); 
      sock.close();
      Thread.currentThread().sleep(5000); 
        }
    
}
}
