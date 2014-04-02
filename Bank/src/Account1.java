
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ssa7888
 *         rxt7007 
 */
public class Account1{

Account1(){}
static int k =0;
static int num=0;
static Object details[][] = new Object[10][10];
static int acc =0;
Scanner scan = new Scanner(System.in);

public String CreateAccount(){
	String s = "";
        try{	
                System.out.println("how many accounts you want to create");		
		num = scan.nextInt();                                           //stores user input for number of accounts to be created
		
		int j=0;
		for(int i=0; i < num; i++){
			System.out.println("Enter your name:");
			details[i][j] = scan.next();
			System.out.println("Enter your age");
			details[i][j+1] = scan.nextInt();
			System.out.println("Enter Amount");
			details[i][j+2] = scan.nextDouble();	 				
		}			
		for(int i=0; i < num ;i++){
			System.out.println("Account"+i+"has been created");		
		}
		}
		catch(Exception e){System.out.println("Wrong Input");}	
        return s;

}
public void AccessAccount(){                                            //method to access the details of the account
	here:
	try{	
		boolean t = true;
		
		System.out.println("Enter the account number that you want to Access");
		k=scan.nextInt();
		if(k >= num){
			System.out.println("Account does not exist");
			break here;		
		}
		System.out.println("Succesfully enetered Account"+ k);
	
	}
	catch(Exception e){System.out.println("Wrong Input");}
}

public void Balance(){                                                  //this method checks the balance of the account
		try{
		String str = details[k][2].toString();
		double i =Double.valueOf(str).doubleValue();
		System.out.println("Balance: "+i);
		}catch(Exception e){System.out.println("Wrong Input");}
}
public void Deposit(){                                              //this method deposits money in the account
		try{
		String str = details[k][2].toString();
		double i =Double.valueOf(str).doubleValue();
		System.out.println("Enter amount to be deposited");
		int deposit = scan.nextInt();
		details[k][2] = deposit + i;
		System.out.println("Amount succesfully deposited");
		}catch(Exception e){System.out.println("Wrong Input");}
}
public void Withdraw(){                                         //this method is used to withdraw money from the account
		try{
		String str = details[k][2].toString();
		double i =Double.valueOf(str).doubleValue();
		System.out.println("Enter amount to be withdrawn");
			int deposit = scan.nextInt();
			if(deposit < i){
				details[k][2] = i - deposit;
			System.out.println("Amount was succesfully withdrawn");
			}
			else{System.out.println("This Amount cannotbe withdrawn");}
		}catch(Exception e){System.out.println("Wrong Input");}
}
public void Transfer(){                                     //method to transfer money between 2 accounts
		here:
		try{
		System.out.println("Enter the Account number to that you want to Tranfer");			
				acc = scan.nextInt();
				if(acc >= num){
					System.out.println("Account does not exist");
					break here;
				}
				System.out.println("Enter the Amount to be Transfered");
				int deposit = scan.nextInt();
				String str = details[k][2].toString();
				double i =Double.valueOf(str).doubleValue();
				String str1 = details[acc][2].toString();
				double j =Double.valueOf(str1).doubleValue();
				if(i > deposit){
					details[k][2] = i - deposit;		
					details[acc][2] = j + deposit;
				System.out.println("Amount was succesfully transfered");
				}
				else{System.out.println("Insufficient Funds");}
		}catch(Exception e){System.out.println("Wrong Input");}
}

}
