
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Srikant
 */
public class Account{

static int accountNumber;
static double Amount;
static String name;
static Object details[][] = new Object[10][10];

Account(){}
Account(int accountNumber,String name,double Amount){
	this.accountNumber = accountNumber;
	this.name = name;
	this.Amount = Amount;
}

/*
public void setAccountNumber(int accountnumber){this.accountNumber= accountNumber;}
public void setName(String name){this.name= name;}
public void setAmount(double Amount){this.Amount= Amount;}

public int getAccountNumber(){return accountNumber;}
public String getName(){return name;}
public double getAmount(){return Amount;}
*/



	public static void main(String args[]){
		
		try{
		Scanner scan = new Scanner(System.in);	
		System.out.println("how many accounts you want to create");		
		int num = scan.nextInt();
			
		
		//System.out.println("Enter your choice???");
		//int i = scan.nextInt(); 
		
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
		int k;
		
		while(true){
		System.out.println("Enter the account number that you want to Access");
		k=scan.nextInt();
		boolean t =true;
		while(t){		
		String str = details[k][2].toString();
		double i =Double.valueOf(str).doubleValue();
		
		
		System.out.println("Enter your Choice????\n"+" 1.Check Balance\n"+" 2.Deposit\n"+" 3.Withdarw\n"+" 4.Transfer\n"+" 5.exit bank\n"+" 6.Switch Account");
		int iter =scan.nextInt();
		int deposit,acc;
		switch(iter){
			case 1:
				System.out.println(i);
				break;
			case 2:
				System.out.println("Enter amount to be deposited");
				deposit = scan.nextInt();
				details[k][2] = deposit + i;
				//System.out.println((deposit + i));
				break;
			case 3:
				System.out.println("Enter amount to be withdrawn");
				deposit = scan.nextInt();
				if(deposit < i){
				details[k][2] = i - deposit;
				}
				else{System.out.println("This Amount cannotbe withdrawn");}
				//System.out.println((i - deposit));
				break;
			case 4:
				System.out.println("Enter the Account number to that you want to Tranfer");			
				acc = scan.nextInt();
				if(acc >= num){
					System.out.println("Account does not exist");
					break;
				}
				System.out.println("Enter the Amount to be Transfered");
				deposit = scan.nextInt();
				
				if(i > deposit){
					details[k][2] = i - deposit;		
					details[acc][2] = i + deposit;
				}
				else{System.out.println("Insufficient Funds");}
				break;
			case 5:
				System.exit(0);
			case 6:
				t =false;
				break;
			default:
				System.out.println("Wrong Input--->");

		}
		}
	}
	}
		catch(Exception e){System.out.println("Wrong Input");}
	}

}
