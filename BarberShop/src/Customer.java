


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ssa7888
 * @author rxt7007
 */

class Semaphore {
    
    protected int n; 
        public Semaphore (int n) { 
                this.n = n; 
        } 
        public synchronized void P () {                     //used to acquire a semaphore
                if (n <= 0) { 
                        try { 
                                wait();         
                        } catch(Exception e) { 
                                 
                        } 
                } 
                -- n; 
        } 
        public synchronized void V () {                     //used to release a semaphore
                if (++ n > 0) 
                        notify();               
        } 
}

public class Customer extends Common1 implements Runnable {
    
    int i;
    public Customer(int i){                         //constructor for customer that initializes customer id
        this.i=i;
    }
    public static int customerchair(){              //decreases the number of available chairs when a customer enters the shop
        
        custchairs--;
        return custchairs;
    }
    
    public static int chairfree(){                  //increases the number of available chairs when a customer enters the shop
        
        custchairs++;
        return custchairs;
    }

    public  void run ()                             //run method for customer
    {
        
            mutex.P();
        if(custchairs>0){                                       //if the number of chairs is greater then number of customers
                                                                //waiting, then the customer will wait
              custchairs = customerchair();
              System.out.println("Customer "+i+" is waiting on a chair for haircut");
              cChair.V();
              mutex.V();
              chair.P();
              System.out.println("Customer "+i+"having a haircut");
               try{
                    sleep(1000);
                  }
                    catch(Exception e){}
               
        } 
        
        else                                                    //the customer leaves the shop if chairs are not available
        {
            System.out.println("Customer "+i+" has left the shop without a haircut");
            mutex.V();
        }
         
        }
    }

class Barber extends Common1 implements Runnable{
   
    public synchronized void run(){
        
        while(true)
        {
            cChair.P();                                         //customer chair is acquired
            mutex.V();
            chair.V();                                          //Barber chair released
            
            Customer.custchairs = Customer.chairfree();         //number of free chair increased as a customer leaves the shop
            
            
             try
        {
            sleep(10000);
        }
        catch(Exception ex){}
        }
    }
    }

class Common1 extends Thread {
            
        public static Semaphore chair = new Semaphore(0);       //Semaphore for Barber Chair
        public static Semaphore cChair = new Semaphore(0);      //Semaphore for Customer waiting chairs
        public static Semaphore mutex = new Semaphore(1);       //Semaphore for critical section
        public static int custchairs=4;
        public static int i=0;                                  //Customer id
        
        public static void main(String[] args){
        
         boolean j=true;    
        Thread barb = new Thread(new Barber());                 //barber thread is created
        
        barb.start();
         try
        {
            System.out.println("Barber is sleeping");
            sleep(1000);
        }
        catch(Exception ex){}        
        while(true)
        {
        Thread cust = new Thread(new Customer(i));              //Customer thread is created
        if(j)
                {
                    System.out.println("Customer "+i+"wakes up barber");
                    j=false;
                }
        cust.start();
        i++;
        try
        {
            cust.sleep(1000);
        }
        catch(Exception ex){}
        }  
        
        
}
}