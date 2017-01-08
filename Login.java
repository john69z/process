/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import control.*;
import domain.Member;
import javax.swing.JPasswordField;
import ui.MemberButton.CreateMemberButton.*;


/**
 *
 * @author user
 */
public class Login extends JFrame{
      private JTextField jtfLogin = new JTextField ();
      
    private JPasswordField jtfPassword = new JPasswordField();
    private JButton jbtLogin = new JButton("Login");
    private JButton jbtRegister = new JButton("Register");
    private MaintainMember memberContorl=new MaintainMember();
    public Login(){
         JPanel jpCenter = new JPanel(new GridLayout(2,2));
         jpCenter.add(new JLabel("Login ID  :"));
         jpCenter.add(jtfLogin);
         jpCenter.add(new JLabel("Password  :"));
         
         jpCenter.add(jtfPassword);
         add(jpCenter);
         JPanel jpSouth = new JPanel();
          
        jpSouth.add(jbtLogin);
        jpSouth.add(jbtRegister);
          add(jpSouth,BorderLayout.SOUTH);
          jbtLogin.addActionListener(new CreateListener());
          jbtRegister.addActionListener(new ActionListener()    {
            public void actionPerformed(ActionEvent e) {
               new ui.MemberButton.CreateMemberButton();
            }
        });
          
           setTitle("Fastfood4U");
        setSize(600,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
       private class CreateListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String ID=jtfLogin.getText();
            String password=jtfPassword.getText();
            
            if(ID.equals("")){
                JOptionPane.showMessageDialog(null, "This Login ID cannot be null.","Record Not Found ",JOptionPane.ERROR_MESSAGE);
            }
              if(password.equals("")){
                JOptionPane.showMessageDialog(null, "This password cannot be null.","Record Not Found ",JOptionPane.ERROR_MESSAGE);
            }else{
                  
                  Member m=memberContorl.selectRecord(ID);
                  
                  if(m==null){
                       JOptionPane.showMessageDialog(null, "This member is not exist.","Record Not Found ",JOptionPane.ERROR_MESSAGE);
                  }else{
               
                          if(m.getPassword().equals(password)){
                              new MainPage();
                              setVisible(false);
                              
                          }else{
                               JOptionPane.showMessageDialog(null, "This password not match.","Password not match ",JOptionPane.ERROR_MESSAGE);
                          }
                          
                     
                          
                      
                  }
              }
            
            
            
        }

       
       }
      public static void main(String[] args) {
     Login login123=new Login();
    }
}
