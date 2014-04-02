/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Srikant
 */
import java.util.*;

public class Test {
    public static void main(String args[]) {
	Set s = new HashSet();
	String str1 = "Vish";
	String str2 = str1;
	String str3 = new String("Vish");

        s.add(str1);
        s.add(str2);
	System.out.println(s);
	//s.add(str3);
	//System.out.println(s);
    }
}

