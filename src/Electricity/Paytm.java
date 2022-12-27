package Electricity;

// import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Paytm extends JFrame implements ActionListener{
    String meter;
    JButton b1;
    Paytm(String meter){
        super("Paytm Page");
        this.meter = meter;
        JEditorPane j = new JEditorPane();
        j.setEditable(false);   
        setSize(800,800);
        setLocation(400,20);
        setVisible(true);
        
        try {
            j.setPage("https://paytm.com/online-payments");
        }catch (Exception e) {
            j.setContentType("text/html");
            j.setText("<html>Could not load, Check your Connection..!</html>");
        } 

        b1 = new JButton("Back");
        b1.setBounds(650, 20, 120, 25);
        b1.addActionListener(this);
        j.add(b1);

        JScrollPane scrollPane = new JScrollPane(j);
        add(scrollPane);
    }
    
    public void actionPerformed(ActionEvent ae){
        this.setVisible(false);
        new PayBill(meter).setVisible(true);
    }
    public static void main(String[] args){
        new Paytm("").setVisible(true);
    }
}
