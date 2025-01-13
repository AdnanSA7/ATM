package javaapplication8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class Transfer extends JFrame implements ActionListener {
    String cardNum, pinNum, transfer_cardNum;
    JButton btn_transfer,btn_back;
    JTextField txt_cardNum,txt_amount;
    double amount;

    public Transfer(String cardNum, String pinNum){
        this.cardNum = cardNum;
        this.pinNum = pinNum;

        ImageIcon i1 = new ImageIcon("icons/atm.jpg");
        setIconImage(i1.getImage());
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Balance Transfer");
        text.setFont(new Font("Raleway", Font.BOLD, 20));
        text.setBounds(260, 280, 700, 35);
        text.setForeground(Color.WHITE);
        image.add(text);

        JLabel txt1 = new JLabel("Please enter the Card Number where you want to transfer money");
        txt1.setFont(new Font("Raleway", Font.BOLD, 12));
        txt1.setBounds(152, 330, 700, 35);
        txt1.setForeground(Color.WHITE);
        image.add(txt1);

        txt_cardNum = new JTextField();
        txt_cardNum.setFont(new Font("Raleway", Font.PLAIN, 14));
        txt_cardNum.setBounds(185, 370, 300, 35);
        txt_cardNum.setBackground(Color.WHITE);
        image.add(txt_cardNum);

        JLabel l_amount = new JLabel("Amount: ");
        l_amount.setFont(new Font("Raleway", Font.BOLD, 17));
        l_amount.setBounds(185, 415, 110, 35);
        l_amount.setForeground(Color.WHITE);
        image.add(l_amount);

        txt_amount = new JTextField();
        txt_amount.setFont(new Font("Raleway", Font.BOLD, 14));
        txt_amount.setBounds(265, 417, 220, 30);
        image.add(txt_amount);

        btn_transfer = new JButton("Transfer");
        btn_transfer.setBounds(362, 486, 150, 29);
        btn_transfer.addActionListener(this);
        image.add(btn_transfer);

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
        else if (e.getSource() == btn_transfer){
            if(txt_amount.getText().isEmpty() || txt_cardNum.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please fill all the fields");
            } else {
                try {
                    amount = Double.parseDouble(txt_amount.getText());
                    transfer_cardNum = txt_cardNum.getText();
                    Conn conn = new Conn();
                    Date date = new Date();
                    PreparedStatement pst = conn.c.prepareStatement("select balance from bank_account where Card_Number = ?");
                    pst.setString(1, transfer_cardNum);
                    ResultSet rs = pst.executeQuery();
                    PreparedStatement pst1 = conn.c.prepareStatement("select balance from bank_account where Card_Number = ? and Pin_Number = ?");
                    pst1.setString(1, cardNum);
                    pst1.setString(2, pinNum);
                    ResultSet rs1 = pst1.executeQuery();

                    if (rs1.next()) {
                        double check = rs1.getDouble("Balance");
                        if (check < amount) {
                            JOptionPane.showMessageDialog(null, "Insufficient Balance");
                        } else {
                            double own_balance = check - amount;
                            PreparedStatement pst_own_balanceup = conn.c.prepareStatement("UPDATE bank_account SET Balance = ? where Card_Number = ? and Pin_Number = ?");
                            pst_own_balanceup.setDouble(1, own_balance);
                            pst_own_balanceup.setString(2, cardNum);
                            pst_own_balanceup.setString(3, pinNum);
                            pst_own_balanceup.executeUpdate();

                            while (rs.next()){
                                double check1 = rs.getDouble("Balance");
                                double transfer_balance = check1 + amount;
                                PreparedStatement pst_transfer = conn.c.prepareStatement("UPDATE bank_account SET Balance = ? where Card_Number = ?");
                                pst_transfer.setDouble(1, transfer_balance);
                                pst_transfer.setString(2, transfer_cardNum);
                                pst_transfer.executeUpdate();

                                PreparedStatement pst3 = conn.c.prepareStatement("INSERT INTO bank_account_transactions (Card_Number, Pin_Number, Date, Type, Amount, Balance) values (?, ?, ?, ?, ?, ?)");
                                pst3.setString(1,cardNum);
                                pst3.setString(2,pinNum);
                                pst3.setString(3,date.toString());
                                pst3.setString(4,"Transfer To "+transfer_cardNum);
                                pst3.setDouble(5,amount);
                                pst3.setDouble(6,own_balance);
                                pst3.executeUpdate();

                                PreparedStatement pst4 = conn.c.prepareStatement("INSERT INTO bank_account_transactions (Card_Number, Date, Type, Amount, Balance) values (?, ?, ?, ?, ?)");
                                pst4.setString(1,transfer_cardNum);
                                pst4.setString(2,date.toString());
                                pst4.setString(3,"Transfer From "+cardNum);
                                pst4.setDouble(4,amount);
                                pst4.setDouble(5,transfer_balance);
                                pst4.executeUpdate();


                                JOptionPane.showMessageDialog(null, "Cash Transferred Successfully");
                                setVisible(false);
                                new Transaction(cardNum, pinNum).setVisible(true);
                            }
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }
    }

    public static void main(String[] args) {
        new Transfer("","");
    }

}
