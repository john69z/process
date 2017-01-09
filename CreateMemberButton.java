/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.MemberButton;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import domain.Member;
import control.MaintainMember;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;

/**
 *
 * @author LaiWeiChai
 */
public class CreateMemberButton extends JFrame {
    
    private JTextField jtfMemberID = new JTextField ();
    private JTextField jtfMemberName = new JTextField();
    private JTextField jtfPhoneNumber = new JTextField();
    private JTextField jtfEmailAddress = new JTextField();
    private JTextField jtfCummulativePoint = new JTextField("0");
      private JTextField jtfPassword = new JTextField();
    private String host = "jdbc:derby://localhost:1527/Assignment";
    private String user = "nbuser";
    private String password = "nbuser";
    private String tableName = "MEMBER";
    private Connection conn;
    private Statement stmt;
    private int lastest;
    
    
    String[] MemberType = {"Non-Member","Member"};
        
    private JComboBox jtfMemberType = new JComboBox(MemberType); 
    
    private JButton jbtCreate = new JButton("Create");
    private JButton jbtBack = new JButton("Back");
    private String babidog ;
    private MaintainMember MemberControl;
    private int lastestNo;
    private int i = 1;
    private String lastestNO;
    public CreateMemberButton() {
    
        MemberControl = new MaintainMember();
        connection();
        
        JPanel jpCenter = new JPanel(new GridLayout(7,2));
    
        jpCenter.add(new JLabel("Customer ID: "));
        jpCenter.add(jtfMemberID);
    
        lastest = getLastRowRecord();
        lastestNo = (lastest+i);
        lastestNO = (String.valueOf(lastestNo));
        
        jtfMemberID.setEditable(false);
        jtfMemberID.setText(lastestNO);

        jpCenter.add(new JLabel("Customer Name :"));
        jpCenter.add(jtfMemberName);
        
        jpCenter.add(new JLabel("Phone Number :"));
        jpCenter.add(jtfPhoneNumber);
        
        jpCenter.add(new JLabel("Email Address :"));
        jpCenter.add(jtfEmailAddress);
        
        jpCenter.add(new JLabel("Cummulative Point :"));
        jpCenter.add(jtfCummulativePoint);
        jtfCummulativePoint.setEditable(false);
        jpCenter.add(new JLabel("Customer type :"));
        jpCenter.add(jtfMemberType);
        jpCenter.add(new JLabel("Password  :"));
        jpCenter.add(jtfPassword);
        add(jpCenter);
        
        JPanel jpSouth = new JPanel();
        jpSouth.add(jbtCreate);
        jpSouth.add(jbtBack);
    
        add(jpSouth,BorderLayout.SOUTH);
        
        jbtCreate.addActionListener(new CreateListener());
        
        setTitle("Member Detail");
        setSize(600,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);  

        jbtBack.addActionListener(new ActionListener()    {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    private class CreateListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String memberID = jtfMemberID.getText();
            String memberName = jtfMemberName.getText();
            String phoneNumber = jtfPhoneNumber.getText();
            String emailAddress = jtfEmailAddress.getText();
            String cummulativePoint =jtfCummulativePoint.getText();
            String memberType = (String)jtfMemberType.getSelectedItem();
            String password=jtfPassword.getText();
            Member m = MemberControl.selectRecord(memberID);
            
            if(m != null)
            {
                JOptionPane.showMessageDialog(null, "This Customer ID already exists.","Duplicate Record ",JOptionPane.ERROR_MESSAGE);
            }
           else
            {
                m = new Member(memberID,memberName,phoneNumber,emailAddress,Integer.parseInt(cummulativePoint),String.valueOf(memberType),password);
            
                MemberControl.createRecord(m);
               
                JOptionPane.showMessageDialog(null, "New Customer Added.", "Success",JOptionPane.INFORMATION_MESSAGE);
               
                //jtfMemberID.setText("");
                lastest = getLastRowRecord();
                lastestNo = (lastest+i);
                lastestNO = (String.valueOf(lastestNo));                
                
                jtfMemberID.setText(lastestNO);
                jtfMemberName.setText("");
                jtfPhoneNumber.setText("");
               
                jtfEmailAddress.setText("");
                jtfMemberID.requestFocusInWindow();
                            
            }     
        }
    }
   
    private void connection(){
        try{
            conn = DriverManager.getConnection(host,user,password);
            System.out.println("***TRACE:Connection established.");
            stmt = conn.createStatement();
        }catch(Exception ex){
            System.out.println("Error connection Database");
        }
    }
    
    private int getLastRowRecord() {
        
        try{
                    
            String sql = "SELECT memberid FROM MEMBER";
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
            lastest = rs.getInt("MEMBERID");
            }
            
            while(rs.previous()) {
                lastest = rs.getInt(1);
            }     
            rs.last();
        }catch(SQLException ex){
            System.out.println("Error in SQL statement.");
        }    
            return lastest;
        }
    
   
        
}
    
   
