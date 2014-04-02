import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.JLabel;
import java.util.Scanner;
import javax.swing.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ssa7888
 *         rxt7007 
 */
public class Bank extends JFrame{
public JButton button0;                     // buttons declared for various choices
public JButton button1;
public JButton button2;
public JButton button3;
public JButton button4;
public JButton button5;
public JButton button6;
public JButton button7;
    public static void main(String args[]){
        
	//frame------>
	Account1 account = new Account1();      
	Bank frame = new Bank();                
	
	frame.setLocation(100,200);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setTitle("Chindi Bank");
	frame.setSize(500, 500);
	frame.setVisible(true);  
        frame.setLayout(new BorderLayout());            //layout set to border layout
	frame.pack();
}
Bank(){
	//Label(NORTH)-------->
        JLabel label = new JLabel("Welcome to Chindi Bank");    
	Font font = new Font("verdana",Font.BOLD,20);           
	label.setFont(font);
        add(label, BorderLayout.NORTH);
	
	//Panel(CENTER)-------->
        JPanel panel = new JPanel();
	panel.setLayout(new GridLayout(7,0));

	button0 = new JButton("CREATE ACCOUNTS");           //buttons created for various choices
        button1 = new JButton("ACCESS ACCOUNT");
        button2 = new JButton("BALANCE");
        button3 = new JButton("DEPOSIT");
        button4 = new JButton("WITHDRAW");
        button5 = new JButton("TRANSFER");
	button6 = new JButton("EXIT");
	//button7 = new JButton("SWITCH ACCOUNT");

	panel.add(button0);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
	panel.add(button5);
	panel.add(button6);
	//panel.add(button7);
        final JTextField tf = new JTextField();
        add(tf,BorderLayout.EAST);

        add(panel,BorderLayout.CENTER);

	//Panel1(SOUTH)---------->
	//JPanel panel1 = new JPanel();

	//JButton jbtOK = new JButton("OK");
        //JButton jbtCancel = new JButton("Cancel");
	//panel1.add(jbtOK);
	//panel1.add(jbtCancel);
        
	//add(panel1,BorderLayout.SOUTH);
	
        button0.addActionListener(new ActionListener(){                     //new account created if create account button is pressed
		
		public void actionPerformed(ActionEvent e){
		if(e.getSource() == button0){
                        
			tf.setText(new Account1().CreateAccount());
                        tf.getText();
                        
		}
	}
	});
	button1.addActionListener(new ActionListener(){                 //account accessed when this button is clicked
		
		public void actionPerformed(ActionEvent e){
		if(e.getSource() == button1){
			new Account1().AccessAccount();
		}
	}
	});
	button2.addActionListener(new ActionListener(){                 //checks balance when this button is clicked
		
		public void actionPerformed(ActionEvent e){
		if(e.getSource() == button2){
			new Account1().Balance();
		}
	}
	});
	button3.addActionListener(new ActionListener(){                 //deposits money of your choice on clicking this button
		
		public void actionPerformed(ActionEvent e){
		if(e.getSource() == button3){
			new Account1().Deposit();
		}
	}
	});
	button4.addActionListener(new ActionListener(){             //withdraw money on pressing this button
		
		public void actionPerformed(ActionEvent e){
		if(e.getSource() == button4){
			new Account1().Withdraw();
		}
	}
	});
		button5.addActionListener(new ActionListener(){     //transfer money on clicking this button
		
		public void actionPerformed(ActionEvent e){
		if(e.getSource() == button5){
			new Account1().Transfer();
		}
	}
	});
		button6.addActionListener(new ActionListener(){     //message displayed o clicking the exit button
		
		public void actionPerformed(ActionEvent e){
		if(e.getSource() == button6){
			System.out.println("Thanks for visit");
			System.exit(0);

		}
	}
	});
		

 	}      
}
