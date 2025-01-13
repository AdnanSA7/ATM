package javaapplication8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class PinChange extends JFrame implements ActionListener {
    String cardNum,pinNum;
    JButton btn_save,btn_back;
    JPasswordField txt_old_pin,txt_new_pin,txt_confirm_pin;

    public PinChange(String cardNum, String pinNum) {
        this.cardNum = cardNum;
        this.pinNum = pinNum;

        ImageIcon i1 = new ImageIcon("icons/atm.jpg");
        setIconImage(i1.getImage());
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Pin Change");
        text.setFont(new Font("Raleway", Font.BOLD, 20));
        text.setBounds(280, 300, 700, 35);
        text.setForeground(Color.WHITE);
        image.add(text);

        JLabel old_pin = new JLabel("Old Pin: ");
        old_pin.setFont(new Font("Raleway", Font.BOLD, 17));
        old_pin.setBounds(190, 335, 110, 35);
        old_pin.setForeground(Color.WHITE);
        image.add(old_pin);

        txt_old_pin = new JPasswordField();
        txt_old_pin.setFont(new Font("Raleway", Font.BOLD, 14));
        txt_old_pin.setBounds(290, 340, 180, 25);
        image.add(txt_old_pin);

        JLabel new_pin = new JLabel("New Pin: ");
        new_pin.setFont(new Font("Raleway", Font.BOLD, 17));
        new_pin.setBounds(190, 375, 110, 35);
        new_pin.setForeground(Color.WHITE);
        image.add(new_pin);

        txt_new_pin = new JPasswordField();
        txt_new_pin.setFont(new Font("Raleway", Font.BOLD, 14));
        txt_new_pin.setBounds(290, 380, 180, 25);
        image.add(txt_new_pin);

        JLabel confirm_pin = new JLabel("Confirm Pin: ");
        confirm_pin.setFont(new Font("Raleway", Font.BOLD, 17));
        confirm_pin.setBounds(190, 415, 110, 35);
        confirm_pin.setForeground(Color.WHITE);
        image.add(confirm_pin);

        txt_confirm_pin = new JPasswordField();
        txt_confirm_pin.setFont(new Font("Raleway", Font.BOLD, 14));
        txt_confirm_pin.setBounds(290, 420, 180, 25);
        image.add(txt_confirm_pin);

        btn_save = new JButton("Save");
        btn_save.setBounds(362, 486, 150, 29);
        btn_save.addActionListener(this);
        image.add(btn_save);

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
        else if (e.getSource() == btn_save) {
            String old_pin = txt_old_pin.getText();
            String new_pin = txt_new_pin.getText();
            String confirm_pin = txt_confirm_pin.getText();
            if (old_pin.isEmpty() || new_pin.isEmpty() || confirm_pin.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields are empty");
            } else {
                if (old_pin.equals(pinNum) && new_pin.equals(confirm_pin)) {
                    try{
                        Conn conn = new Conn();
                        String query_login = "UPDATE login set Pin_Number = ? where Card_Number = ?";
                        String query_bank_account = "UPDATE bank_account set Pin_Number = ? where Card_Number = ?";
                        PreparedStatement pst_login = conn.c.prepareStatement(query_login);
                        pst_login.setString(1, new_pin);
                        pst_login.setString(2, cardNum);
                        pst_login.executeUpdate();
                        PreparedStatement pst_bank_account = conn.c.prepareStatement(query_bank_account);
                        pst_bank_account.setString(1, new_pin);
                        pst_bank_account.setString(2, cardNum);
                        pst_bank_account.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Pin Changed Successfully");
                        setVisible(false);
                        new Transaction(cardNum,pinNum).setVisible(true);
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Pin Number");
                    txt_old_pin.setText("");
                    txt_new_pin.setText("");
                    txt_confirm_pin.setText("");
                }
            }
        }
    }

    public static void main(String[] args) {
        new PinChange("", "");
    }
}
