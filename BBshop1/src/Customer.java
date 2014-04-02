
import java.util.concurrent.Semaphore;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Srikant
 */
public class Customer extends Thread {
    
    Semaphore chair ;
    Semaphore cChair;
    public static int id=0;
    public static Object o = new Object();
   

    public Customer(Semaphore chair, Semaphore cChair) {
        
        this.chair=chair;
        this.cChair=cChair;
    }
    public  void run (){
        
        
        if(Barber.custchairs>0){
            
            while(true){
            if(!Barber.barbbusy){
                try{
                    synchronized(o){
                try{
                    chair.acquire();
                    cChair.release();
                    o.notify();
                }
                catch(Exception ex){}
             Barber.barbbusy = true;
                              
                    System.out.println("Customer "+getName()+"having a haircut");
               
                    
                            try{//Thread.sleep(1000);
                                    Barber.barbbusy=false;
                                    chair.release();
                                    o.notify(); 
                                    Barber.custchairs = Barber.chairfree();
                                    break;
                                    
                            }
                            catch(Exception ex){}
                    }              
                }
                catch(Exception ex){}
            }
            
            else{
                    try{ 
                        Barber.custchairs = Barber.customerchair();
                        System.out.println("Customer "+getName()+" is waiting on a chair for haircut");
                        
                        o.wait();
                         cChair.acquire(); 
                        o.notify();                    
                    } 
                    catch(Exception ex){}
            }
            }
        }
            else{
                
                System.out.println("Customer "+getName()+" has left the shop without a haircut");
            }
        }
    }

