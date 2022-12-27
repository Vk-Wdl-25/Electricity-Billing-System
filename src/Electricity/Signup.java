 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Electricity;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Signup extends JFrame implements ActionListener{
    JPanel p1;
    JTextField t1, t2, t3, t4, t5;
    Choice c1;
    JButton b1, b2;
    Signup(){
        super("Signup Page");
        setBounds(450, 250, 720, 380);
        p1 = new JPanel();
        p1.setBounds(30, 30, 650, 300);
        p1.setLayout( null);
        p1.setBackground(Color.WHITE);
        p1.setForeground(new Color(34, 139, 34));
        p1.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 2), "Create-Account", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(173, 216, 230)));
        add(p1);

        // 1 __________________________________________________________________________________
        
        JLabel l1 = new JLabel("Username");
        l1.setForeground(Color.DARK_GRAY);
        l1.setFont(new Font("Tahoma", Font.BOLD, 14));
        l1.setBounds(100, 130, 100, 20);
        p1.add(l1);
        
        t1 = new JTextField();
        t1.setBounds(260, 130, 150, 20);
        p1.add(t1);
        
        // 2 __________________________________________________________________________________

        JLabel l2 = new JLabel("Name");
        l2.setForeground(Color.DARK_GRAY);
        l2.setFont(new Font("Tahoma", Font.BOLD, 14));
        l2.setBounds(100, 170, 100, 20);
        p1.add(l2);
        
        t2 = new JTextField();
        t2.setBounds(260, 170, 150, 20);
        p1.add(t2);
        
        // 3 __________________________________________________________________________________

        JLabel l3 = new JLabel("Password");
        l3.setForeground(Color.DARK_GRAY);
        l3.setFont(new Font("Tahoma", Font.BOLD, 14));
        l3.setBounds(100, 210, 100, 20);
        p1.add(l3);
        
        t3 = new JTextField();
        t3.setBounds(260, 210, 150, 20);
        p1.add(t3);
        
        
        // 4 __________________________________________________________________________________

        JLabel l4 = new JLabel("Create Account As");
        l4.setForeground(Color.DARK_GRAY);
        l4.setFont(new Font("Tahoma", Font.BOLD, 14));
        l4.setBounds(100, 50, 140, 20);
        p1.add(l4);
        
        t4 = new JTextField();
        t4.setBounds(260, 90, 150, 20);
        t4.setVisible(false);
        p1.add(t4);
        
        t4.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe) {
                
            }
            
            @Override
            public void focusLost(FocusEvent fe) {
                try{
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from login Where meter_no = '"+t4.getText()+"'");
                    while(rs.next()){
                        t2.setText(rs.getString("name"));
                    }
                    
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        
        // 5 __________________________________________________________________________________

        JLabel l5 = new JLabel("Meter Number");
        l5.setForeground(Color.DARK_GRAY);
        l5.setFont(new Font("Tahoma", Font.BOLD, 14));
        l5.setBounds(100, 90, 100, 20);
        l5.setVisible(false);
        p1.add(l5);

        // 6 : Admin KEY _____________________________________________________________________________
        JLabel l6 = new JLabel("Admin KEY");
        l6.setForeground(Color.DARK_GRAY);
        l6.setFont(new Font("Tahoma", Font.BOLD, 14));
        l6.setBounds(100, 240, 100, 20);
        p1.add(l6);
        
        t5 = new JTextField();
        t5.setBounds(260, 240, 150, 20);
        p1.add(t5);

        // Choice for l4 : Create account As _____________
        c1 = new Choice();
        c1.add("Admin");
        c1.add("Customer");
        c1.setBounds(260, 50, 150, 20);
        p1.add(c1);
        
        c1.addItemListener(new ItemListener(){
           public void itemStateChanged(ItemEvent ae){
               String user = c1.getSelectedItem();
               if(user.equals("Customer")){
                   l5.setVisible(true);  // Show Meter text 
                   t4.setVisible(true);  // Show Meter field 
                   
                   l6.setVisible(false);  // hide Admin KEY text
                   t5.setVisible(false);  // hide Admin KEY field
               }else{
                   l5.setVisible(false);  // hide Meter text
                   t4.setVisible(false);  // hide Meter field

                   l6.setVisible(true);  // Show Admin KEY field
                   t5.setVisible(true);  // Admin KEY field
               }
           } 
        });
        
        // Buttons __________________________________________________________________________________

        b1 = new JButton("Create");
        b1.setBounds(140, 290, 120, 30);
        b1.addActionListener(this);
        p1.add(b1);
        
        b2 = new JButton("Back");
        b2.setBounds(300, 290, 120, 30);
        b2.addActionListener(this);
        p1.add(b2);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l7 = new JLabel(i3);
        l7.setBounds(450, 30, 250, 250);
        p1.add(l7);
    }
    
    //  Button ActionListener
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            String username = t1.getText();
            String name = t2.getText();
            String password = t3.getText();
            String user = c1.getSelectedItem();
            String meter = t4.getText();
            try{
                Conn c = new Conn();
                String str = null;
                if(!(username.isEmpty() || password.isEmpty()) ){
                    
                    if(user.equals("Admin") && t5.getText().equals("8822")){
                        str = "insert into login values('"+meter+"', '"+username+"', '"+name+"', '"+password+"', '"+user+"')";
                        System.out.println("SuccesFully Added Admin Login");
                    }else if(user.equals("Customer") && (!meter.isEmpty())){
                        str = "update login set username = '"+username+"', name = '"+name+"', password = '"+password+"', user = '"+user+"' where meter_no = '"+t4.getText()+"'";
                        System.out.println("SuccesFully Added Customer Login");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Please Input All Informtion");
                        this.setVisible(false);
                    }
                    
                }
                else{
                    JOptionPane.showMessageDialog(null, "Invalid Information");
                    this.setVisible(false);
                }

                c.s.executeUpdate(str);
                JOptionPane.showMessageDialog(null, "Account Created Successfully");
                this.setVisible(false);
                new Login().setVisible(true);
            }catch(Exception e){
                
            }
        } else if(ae.getSource()== b2){
            this.setVisible(false);
            new Login().setVisible(true);
        }
    }
    
    public static void main(String[] args){
        new Signup().setVisible(true);
    }
}
