package javaapplication8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {

    JButton login, clear, signUp;
    JTextField card_no_txt;
    JPasswordField pin_txt;

    Login(){
        setTitle("ATM Management System");

        setLayout(null);

        ImageIcon i1 = new ImageIcon("icons/logo.jpg");
        setIconImage(i1.getImage());
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(100, 20, 100, 100);
        add(label);

        JLabel text = new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward", Font.BOLD, 40));
        text.setBounds(210, 25, 400, 100);
        add(text);

        JLabel card_no = new JLabel("Card No: ");
        card_no.setFont(new Font("Raleway", Font.BOLD, 28));
        card_no.setBounds(110, 150, 150, 40);
        add(card_no);

        card_no_txt = new JTextField();
        card_no_txt.setBounds(280, 155, 275, 30);
        card_no_txt.setFont(new Font("Arial", Font.BOLD, 14));
        add(card_no_txt);

        JLabel pin = new JLabel("Pin: ");
        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        pin.setBounds(110, 200, 250, 40);
        add(pin);

        pin_txt = new JPasswordField();
        pin_txt.setBounds(280, 205, 275, 30);
        pin_txt.setFont(new Font("Arial", Font.BOLD, 14));
        add(pin_txt);

        login = new JButton("Sign In");
        login.setBounds(250, 280, 110, 40);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        login.setEnabled(false);
        add(login);

        pin_txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (pin_txt.getText().length()>=4){
                    login.setEnabled(true);
                } else {
                    login.setEnabled(false);
                }
            }
        });

        clear = new JButton("Clear");
        clear.setBounds(410, 280, 110, 40);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);

        signUp = new JButton("Sign Up");
        signUp.setBounds(250, 350, 270, 40);
        signUp.setBackground(Color.BLACK);
        signUp.setForeground(Color.WHITE);
        signUp.addActionListener(this);
        add(signUp);

        getContentPane().setBackground(Color.WHITE);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String cardNum = card_no_txt.getText();
        String pinNum = pin_txt.getText();
        if (e.getSource() == login) {
            try{
                Conn c = new Conn();
                PreparedStatement pst = c.c.prepareStatement("select * from bank_account where Card_Number = ? and Pin_Number = ?");
                pst.setString(1,cardNum);
                pst.setString(2,pinNum);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    setVisible(false);
                    new SplashLoading(cardNum,pinNum).setVisible(true);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Invalid Card Number or Pin");
                }

            } catch (Exception ex) {
                System.out.println(ex);
            }

        }
        else if (e.getSource() == clear) {
            card_no_txt.setText("");
            pin_txt.setText("");
        }
        else if (e.getSource() == signUp) {
            setVisible(false);
            new SignUpPage().setVisible(true);
        }

    }

    public static void main(String[] args) {
        new Login();
    }
}
