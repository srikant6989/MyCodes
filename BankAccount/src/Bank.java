
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.JLabel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Srikant
 */
public class Bank extends JFrame{
    public static void main(String args[]){
        
	Bank frame = new Bank();
	frame.setLocation(100,200);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setTitle("Chindi Bank");
	frame.setSize(500, 500);
	frame.setVisible(true);  
        frame.setLayout(new BorderLayout());
        
        JLabel label = new JLabel("Welcome to Chindi Bank");
        //frame.add(label,BorderLayout);
        JPanel panel = new JPanel();
        
        //JButton button1 = new JButton("Enter your valid ID");
        JButton button2 = new JButton("WITHDRAW");
        JButton button3 = new JButton("DEPOSIT");
        JButton button4 = new JButton("BALANCE");
        JButton button5 = new JButton("CREATE ACCOUNT");
        JButton button6 = new JButton("TRANSFER");
        JButton jbtOK = new JButton("OK");
        JButton jbtCancel = new JButton("Cancel");
        
        panel.setLayout(new GridLayout(10,0));
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        
	panel.add(jbtOK);
	panel.add(jbtCancel);
        frame.add(panel);
        
        
        
        
        
/*	jbtOK.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		System.out.println("The " + e.getActionCommand() + " button "
		  + "is clicked at\n " + new java.util.Date(e.getWhen()));
	    }
	});
        

	jbtCancel.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		System.out.println("The " + e.getActionCommand() + " button "
		  + "is clicked at\n " + new java.util.Date(e.getWhen()));
	    }
	});*/
       
    }
}
