import java.net.*;  
import java.io.*;  
import java.util.*; 
public class DayTimeServer { 
  public static void main(String args[]) { 
    try { 
      ServerSocket listen = new ServerSocket(13);
      System.out.println("Listening on port:  "+listen.getLocalPort()); 
      for(;;) { 
        Socket clnt = listen.accept();
        System.out.println(clnt.toString()); 
        PrintWriter out = new PrintWriter(clnt.getOutputStream(), true); 
        out.println(new Date()); 
        clnt.close();  
      } 
    } catch(Exception e) {}}} 