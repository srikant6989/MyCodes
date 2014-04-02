import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.*;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Srikant
 */
public class CircleClient {
   public static void main(String args[]) throws IOException {
      try {
	 if (args.length != 2) {
	    System.err.println("Usage: java CircleClient host port");
	 }
	 else {
	    Socket sock = new Socket(args[0], Integer.parseInt(args[1]));

	    DataInputStream fromServer = new DataInputStream(sock.getInputStream());
	    DataOutputStream toServer = new DataOutputStream(sock.getOutputStream());

	    Scanner sc = new Scanner(System.in);
            while(true) {
		double radius = sc.nextDouble();
	        toServer.writeDouble(radius);
	        toServer.flush();

	        System.out.println(fromServer.readDouble());
	    }
	 }
      }
      catch(UnknownHostException e) {
	 System.err.println("No such host");
      }
      catch(IOException e) {
	 System.err.println(e.getMessage());
      }
   } 
}
