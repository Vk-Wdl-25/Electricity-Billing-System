package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class LastBill extends JFrame implements ActionListener{
    JLabel l1;
    JTextArea t1;
    JButton b1;
    Choice c1;
    JPanel p1;
    LastBill(){
        setSize(600,800);
        setLayout(new BorderLayout());
        
        p1 = new JPanel();
        
        l1 = new JLabel("Generate Bill");
        
        c1 = new Choice();

        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while(rs.next()){   
                c1.add(rs.getString("meter_no"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        
        t1 = new JTextArea(50,15);
        JScrollPane jsp = new JScrollPane(t1);
        t1.setFont(new Font("Senserif",Font.ITALIC,18));
        
        b1 = new JButton("Generate Bill");
        
        p1.add(l1);
        p1.add(c1);
        add(p1,"North");
        
        add(jsp,"Center");
        add(b1,"South");
        
        b1.addActionListener(this);
        
        setLocation(350,10);
    }
    public void actionPerformed(ActionEvent ae){
        try{
            Conn c = new Conn();

            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+c1.getSelectedItem()+"'");
            
            if(rs.next()){
                t1.append("\n    Customer Name:"+rs.getString("name"));
                t1.append("\n    Meter Number:  "+rs.getString("meter_no"));
                t1.append("\n    Address:            "+rs.getString("address"));
                t1.append("\n    State:                 "+rs.getString("state"));
                t1.append("\n    City:                   "+rs.getString("city"));
                t1.append("\n    Email:                "+rs.getString("email"));
                t1.append("\n    Phone Number  "+rs.getString("phone"));
                t1.append("\n-------------------------------------------------------------");
                t1.append("\n");
            }

            t1.append("    Details of the Last Bills\n");
            
            rs = c.s.executeQuery("select * from bill where meter_no = '"+c1.getSelectedItem()+"'");
            
            while(rs.next()){
                t1.append("    "+ rs.getString("month") + "  -->  " +rs.getString("totalbill") + "\n");
            }
            t1.append("-------------------------------------------------------------");
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        new LastBill().setVisible(true);
    }
}

