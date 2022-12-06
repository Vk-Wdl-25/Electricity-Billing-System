package Electricity;

// import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.sql.*;

public class CustomerDetails extends JFrame implements ActionListener{
 
    JTable t1;
    JButton b1;
    JPanel ContentPane = new JPanel(); 
    String x[] = {"Customer Name","Meter Number","Address","City","State","Email","Phone"}; // Columns
    String y[][] = new String[40][8];  //Data
    int i=0, j=0;
    CustomerDetails(){
        super("Customer Details");
        setSize(1200,650);
        setLocation(400,150);
        
        try{
            Conn c1  = new Conn();
            String s1 = "select * from customer";
            ResultSet rs  = c1.s.executeQuery(s1);
            while(rs.next()){
                y[i][j++]=rs.getString("name");
                y[i][j++]=rs.getString("meter_no");
                y[i][j++]=rs.getString("address");
                y[i][j++]=rs.getString("city");
                y[i][j++]=rs.getString("state");
                y[i][j++]=rs.getString("email");
                y[i][j++]=rs.getString("phone");
                i++;
                j=0;
            }
            t1 = new JTable(y,x); // y => Data ; x => Columns 
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        b1 = new JButton("Print");
        add(b1,"South");
        JScrollPane sp = new JScrollPane(t1);
        add(sp);
        b1.addActionListener(this);
    }
    public void actionPerformed(ActionEvent ae){
        try{
            t1.print();
        }catch(Exception e){}
    }
    
    public static void main(String[] args){
        new CustomerDetails().setVisible(true);
    }
    
}
