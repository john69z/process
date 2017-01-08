/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.PromotionButton;

import control.MaintainMenu;
import control.MaintainPromotion;
import domain.Promotion;
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

/**
 *
 * @author user
 */
public class DeletePromotionButton extends JFrame{
    private JTextField jtfPromotionID = new JTextField ();
    private JTextField jtfPromotionDiscount = new JTextField();
   
    private JButton jbtDelete = new JButton("Delete");
    private JButton jbtBack= new JButton("Back");
    
    private MaintainPromotion PromotionControl;
     
    public DeletePromotionButton() {
        
        PromotionControl = new MaintainPromotion();
                          
        JPanel jpCenter = new JPanel(new GridLayout(2,2));
        
        jpCenter.add(new JLabel("Promotion Code : "));
        jpCenter.add(jtfPromotionID);
        
        jpCenter.add(new JLabel("Promotion Discount :"));
        jpCenter.add(jtfPromotionDiscount);
        
        
        add(jpCenter);
        
         JPanel jpSouth = new JPanel();
        jpSouth.add(jbtDelete);
       jpSouth.add(jbtBack);
        
        add(jpSouth,BorderLayout.SOUTH);
          
        jbtDelete.addActionListener(new DeleteListener());
          jbtBack.addActionListener(new ActionListener()    {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        
        
        setTitle("Promotion Detail");
        setSize(600,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
        private class DeleteListener implements ActionListener
    {
            public void actionPerformed(ActionEvent e)
        {
            String promotionID = jtfPromotionID.getText();
              
            Promotion m = PromotionControl.selectRecord(promotionID);
            
            if(m != null)
            {
                jtfPromotionID.setText(m.getPromotionCode());
                jtfPromotionDiscount.setText(String.valueOf(m.getPromotionDiscount()));
                
                int option =JOptionPane.showConfirmDialog(null,"Are you sure to delete");
                 
                if(option == JOptionPane.YES_OPTION)
                {
                    PromotionControl.deleteRecord(promotionID);
                    JOptionPane.showMessageDialog(null, "Record deleted");
                    clearText();
                }
            }
           else
            {
                JOptionPane.showMessageDialog(null, "This Promotion code not exists.","Duplicate Record ",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
         private void clearText()
    {
        jtfPromotionID.setText("");
        jtfPromotionDiscount.setText("");
       
        
    }
}

