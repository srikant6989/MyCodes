
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Srikant
 */
public class Anagram {
    
    public void ana (String prefix, String suffix)
    {
        if(suffix.length() ==1)
        {
            System.out.println(suffix);
        }
        else if(suffix.length()==2)
        {
            String swap;
            swap = suffix.substring(1,2)+suffix.substring(0,1);
            System.out.println("Anagrams are"+prefix+swap+" "+prefix+suffix);
        }
        else
        {
            String before,after;
            for(int i=0;i<suffix.length();i++)
            {
                before = suffix.substring(0,1);
                after = suffix.substring(1,suffix.length());
                prefix = before;
                suffix = after + before;
                ana(before,after);
            }
        }
    }
    public static void main(String args[])
    {
        
        Anagram a = new Anagram();
        a.ana("","abc");
        
    }
            
            
}
