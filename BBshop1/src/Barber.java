
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Semaphore;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Srikant
 */
public class Barber extends Thread{
     static int k =1000;
    public static int custchairs=0;
    public static boolean barbbusy;
    public static boolean custchairbusy;
    
    public Barber(int custchairs){
        barbbusy=false;
        custchairbusy=false;
        this.custchairs=custchairs;
    }
    
    public static int customerchair(){
        
        custchairs--;
        return custchairs;
    }
    
    public static int chairfree(){
        
        custchairs++;
        return custchairs;
    }
    public static void leave(){}
    public synchronized void run(){
        
        
    }
    public static void main(String[] args){
        
        Semaphore chair = new Semaphore(1);
        Semaphore cChair = new Semaphore(1);      
        ArrayList a = new ArrayList();
        Thread barb = new Barber(4);
        barb.start();
     //   while(true){
        for(int i=0;i<=1001;i++){
        
          a.add(new Customer(chair,cChair));
        ((Thread)a.get(i)).start();
        
        /*if(i==k){
            try{
                    sleep(1000);
                    //i=0;
            } 
               catch(Exception ex){}
        }*/
        }  
        
        Iterator i = a.iterator();
        while(i.hasNext()){
            try{
            ((Thread)i.next()).join();
        }
            catch(Exception ex){}
}
}}

