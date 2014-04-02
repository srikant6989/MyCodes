import java.net.*;  
import java.io.*;  
import java.util.*; 
public class DayTimeClient { 
  static int dayTimePort = 13; 
  public static void main(String args[]) { 
    try { 
      Socket sock = new Socket("localhost", dayTimePort);
      BufferedReader din = new BufferedReader( 
        new InputStreamReader( sock.getInputStream() )); 
      String rTime = din.readLine(); 
      System.out.println(rTime); 
      sock.close(); 
    } 
    catch (Exception e) {} 
  } 
} 