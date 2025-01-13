
package javaapplication8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {

    String cardNum,pinNum;
    double amount;
    JButton btn_deposit,btn_back;
    JTextField txt_amount;

    public Deposit(String cardNum, String pinNum) {
        this.cardNum = cardNum;
        this.pinNum = pinNum;
        ImageIcon i1 = new ImageIcon("icons/atm.jpg");
        setIconImage(i1.getImage());
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Please enter the amount you would like to deposit");
        text.setFont(new Font("Raleway", Font.BOLD, 14));
        text.setBounds(160, 300, 700, 35);
        text.setForeground(Color.WHITE);
        image.add(text);

        txt_amount = new JTextField();
        txt_amount.setFont(new Font("Raleway", Font.BOLD, 14));
        txt_amount.setBounds(176,350, 320, 35);
        image.add(txt_amount);

        btn_deposit = new JButton("Deposit");
        btn_deposit.setBounds(362, 486, 150, 29);
        btn_deposit.addActionListener(this);
        image.add(btn_deposit);

        btn_back = new JButton("Back");
        btn_back.setBounds(362, 520, 150, 29);
        btn_back.addActionListener(this);
        image.add(btn_back);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 900);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_back) {
            setVisible(false);
            new Transaction(cardNum,pinNum).setVisible(true);
        }
        else if (e.getSource() == btn_deposit) {
            if(txt_amount.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter the amount you would like to deposit");
            } else{
                try {
                    amount = Double.parseDouble(txt_amount.getText());
                    Conn c = new Conn();
                    Date date = new Date();
                    PreparedStatement pst = c.c.prepareStatement("select balance from bank_account where Card_Number = ? and Pin_Number = ?");
                    pst.setString(1, cardNum);
                    pst.setString(2, pinNum);
                    ResultSet rs = pst.executeQuery();
                    
                    if(rs.next()){
                        double check = rs.getDouble("Balance");
                        double balance = check + amount;
                        String query2 = "Update bank_account SET Balance = '"+balance+"' where Card_Number = '"+cardNum+"'";
                        String query3 = "INSERT INTO bank_account_transactions (Card_Number, Pin_Number, Date, Type, Amount, Balance) values ('"+cardNum+"','"+pinNum+"','"+date+"', 'Deposit', '"+amount+"', '"+balance+"')";
                        c.st.executeUpdate(query2);
                        c.st.executeUpdate(query3);
                        JOptionPane.showMessageDialog(null, "Cash Deposited Successfully");
                        setVisible(false);
                        new Transaction(cardNum, pinNum).setVisible(true);
                    }
                    
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }

        }

    }

    public static void main(String[] args) {
        new Deposit("","");
    }
}
