/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Srikant
 */
public class Locks1 extends Thread { 
  private Object lock = new Object();  
  private int myId; 
  public Locks1(  int id ) { ; myId = id; } 
  public void method() { 
    synchronized( lock ) { 
      for ( int i = 0; i < 3; i++ ) { 
        System.out.println( "Thread #" + myId + " is tired" ); 
        try { 
          Thread.currentThread().sleep( 10 ); 
        } catch ( InterruptedException e ){}            
        System.out.println("Thread #" + myId + " is rested" ); }}} 
  public void run() { method(); } 
  public static void main( String args[] ) { 
      
      for ( int i = 0; i < 3; i++ ) new Locks1( i ).start(); }}
