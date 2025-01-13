
package javaapplication8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class BalanceInquiry extends JFrame implements ActionListener {
    String cardNum,pinNum;
    JButton btn_back;
    JLabel balance_info;

    public BalanceInquiry(String cardNum, String pinNum) {
        this.cardNum = cardNum;
        this.pinNum = pinNum;
        ImageIcon i1 = new ImageIcon("icons/atm.jpg");
        setIconImage(i1.getImage());
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Balance");
        text.setFont(new Font("Raleway", Font.BOLD, 20));
        text.setBounds(300, 300, 700, 35);
        text.setForeground(Color.WHITE);
        image.add(text);
        
        balance_info = new JLabel();
        balance_info.setFont(new Font("Raleway", Font.BOLD, 20));
        balance_info.setBounds(290, 350, 700, 35);
        balance_info.setForeground(Color.WHITE);
        image.add(balance_info);


        btn_back = new JButton("Back");
        btn_back.setBounds(362, 520, 150, 29);
        btn_back.addActionListener(this);
        image.add(btn_back);
        
        balanceShow();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 900);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
    }
    
    public void balanceShow(){
        try {
                    Conn c = new Conn();
                    PreparedStatement pst = c.c.prepareStatement("select balance from bank_account where Card_Number = ? and Pin_Number = ?");
                    pst.setString(1, cardNum);
                    pst.setString(2, pinNum);
                    ResultSet rs = pst.executeQuery();
                    if(rs.next()){
                        double check = rs.getDouble("Balance");
                        balance_info.setText(check+" TK");
                    }
            }
        catch (Exception ex) {
                    System.out.println(ex);
                }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_back) {
            setVisible(false);
            new Transaction(cardNum,pinNum).setVisible(true);
        }
    }


    public static void main(String[] args) {
        new BalanceInquiry("","");
    }
}
