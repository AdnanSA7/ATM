
package javaapplication8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class SignUpPage2 extends JFrame implements ActionListener {

    String formNo;
    JComboBox<String> religion_details, income_details;
    JTextField txt_acc_title, txt_dep_currency,txt_dep_amount,txt_phone, txt_nid;
    JCheckBox acc_type_checkbox1, acc_type_checkbox2, acc_type_checkbox3, acc_type_checkbox4, acc_type_checkbox5;
    JCheckBox currency_checkbox1, currency_checkbox2, currency_checkbox3, currency_checkbox4, currency_checkbox5;
    JCheckBox agree;
    JButton submit;

    public SignUpPage2(String formNo) {
        this.formNo = formNo;
        setTitle("Sign Up Page");
        setLayout(null);

        JLabel other_details_details = new JLabel("Other Details...");
        other_details_details.setFont(new Font("Raleway", Font.BOLD, 40));
        other_details_details.setBounds(300,50,400,30);
        add(other_details_details);

        JLabel religion = new JLabel("Religion");
        religion.setFont(new Font("Raleway", Font.BOLD, 20));
        religion.setBounds(100,150,200,30);
        add(religion);

        String [] religion_values = {"Islam", "Hindu", "Christian", "Others"};
        religion_details = new JComboBox<>(religion_values);
        religion_details.setBounds(280,150,400,30);
        religion_details.setSelectedIndex(0);
        add(religion_details);

        JLabel income = new JLabel("Income");
        income.setFont(new Font("Raleway", Font.BOLD, 20));
        income.setBounds(100,200,200,30);
        add(income);

        String [] income_values = {">50000",">1,00,000",">2,00,000",">5,00,000",">10,00,000"};
        income_details = new JComboBox<>(income_values);
        income_details.setBounds(280,200,400,30);
        income_details.setSelectedIndex(0);
        add(income_details);

        JLabel acc_title = new JLabel("Title of Account: ");
        acc_title.setFont(new Font("Raleway", Font.BOLD, 20));
        acc_title.setBounds(100,250,200,30);
        add(acc_title);

        txt_acc_title = new JTextField();
        txt_acc_title.setFont(new Font("Raleway", Font.BOLD, 14));
        txt_acc_title.setBounds(280,250,400,30);
        add(txt_acc_title);

        JLabel acc_type = new JLabel("Type of Account: ");
        acc_type.setFont(new Font("Raleway", Font.BOLD, 20));
        acc_type.setBounds(100,300,200,30);
        add(acc_type);

        acc_type_checkbox1 = new JCheckBox("Savings");
        acc_type_checkbox1.setFont(new Font("Raleway", Font.BOLD, 12));
        acc_type_checkbox1.setBounds(280,300,80,30);
        acc_type_checkbox1.setBackground(Color.WHITE);
        add(acc_type_checkbox1);

        acc_type_checkbox2 = new JCheckBox("Current");
        acc_type_checkbox2.setFont(new Font("Raleway", Font.BOLD, 12));
        acc_type_checkbox2.setBounds(360,300,80,30);
        acc_type_checkbox2.setBackground(Color.WHITE);
        add(acc_type_checkbox2);

        acc_type_checkbox3 = new JCheckBox("Fixed");
        acc_type_checkbox3.setFont(new Font("Raleway", Font.BOLD, 12));
        acc_type_checkbox3.setBounds(440,300,80,30);
        acc_type_checkbox3.setBackground(Color.WHITE);
        add(acc_type_checkbox3);

        acc_type_checkbox4 = new JCheckBox("NRI Acc.");
        acc_type_checkbox4.setFont(new Font("Raleway", Font.BOLD, 12));
        acc_type_checkbox4.setBounds(520,300,80,30);
        acc_type_checkbox4.setBackground(Color.WHITE);
        add(acc_type_checkbox4);

        acc_type_checkbox5 = new JCheckBox("Others");
        acc_type_checkbox5.setFont(new Font("Raleway", Font.BOLD, 12));
        acc_type_checkbox5.setBounds(600,300,80,30);
        acc_type_checkbox5.setBackground(Color.WHITE);
        add(acc_type_checkbox5);

        ButtonGroup bg_checkbox1 = new ButtonGroup();
        bg_checkbox1.add(acc_type_checkbox1);
        bg_checkbox1.add(acc_type_checkbox2);
        bg_checkbox1.add(acc_type_checkbox3);
        bg_checkbox1.add(acc_type_checkbox4);
        bg_checkbox1.add(acc_type_checkbox5);

        JLabel currency = new JLabel("Currency: ");
        currency.setFont(new Font("Raleway", Font.BOLD, 20));
        currency.setBounds(100,350,200,30);
        add(currency);

        currency_checkbox1 = new JCheckBox("Taka");
        currency_checkbox1.setFont(new Font("Raleway", Font.BOLD, 12));
        currency_checkbox1.setBounds(280,350,80,30);
        currency_checkbox1.setBackground(Color.WHITE);
        add(currency_checkbox1);

        currency_checkbox2 = new JCheckBox("USD");
        currency_checkbox2.setFont(new Font("Raleway", Font.BOLD, 12));
        currency_checkbox2.setBounds(360,350,80,30);
        currency_checkbox2.setBackground(Color.WHITE);
        add(currency_checkbox2);

        currency_checkbox3 = new JCheckBox("EUR");
        currency_checkbox3.setFont(new Font("Raleway", Font.BOLD, 12));
        currency_checkbox3.setBounds(440,350,80,30);
        currency_checkbox3.setBackground(Color.WHITE);
        add(currency_checkbox3);

        currency_checkbox4 = new JCheckBox("GBP");
        currency_checkbox4.setFont(new Font("Raleway", Font.BOLD, 12));
        currency_checkbox4.setBounds(520,350,80,30);
        currency_checkbox4.setBackground(Color.WHITE);
        add(currency_checkbox4);

        currency_checkbox5 = new JCheckBox("Others");
        currency_checkbox5.setFont(new Font("Raleway", Font.BOLD, 12));
        currency_checkbox5.setBounds(600,350,80,30);
        currency_checkbox5.setBackground(Color.WHITE);
        add(currency_checkbox5);

        ButtonGroup bg_checkbox2 = new ButtonGroup();
        bg_checkbox2.add(currency_checkbox1);
        bg_checkbox2.add(currency_checkbox2);
        bg_checkbox2.add(currency_checkbox3);
        bg_checkbox2.add(currency_checkbox4);
        bg_checkbox2.add(currency_checkbox5);

        JLabel initial_deposit = new JLabel("Initial Deposit: ");
        initial_deposit.setFont(new Font("Raleway", Font.BOLD, 20));
        initial_deposit.setBounds(100,400,200,30);
        add(initial_deposit);

//        JPanel deposit_panel = new JPanel();
//        deposit_panel.setLayout(null);
//        deposit_panel.setBackground(Color.WHITE);
//        deposit_panel.setBounds(280,400,400,30);

        JLabel dep_currency = new JLabel("Currency: ");
        dep_currency.setFont(new Font("Raleway", Font.BOLD, 16));
        dep_currency.setBounds(280,400,90,30);
        dep_currency.setBackground(Color.WHITE);
        dep_currency.setForeground(Color.BLACK);
//        deposit_panel.add(dep_currency);
        add(dep_currency);

        txt_dep_currency = new JTextField();
        txt_dep_currency.setFont(new Font("Raleway", Font.BOLD, 14));
        txt_dep_currency.setBounds(370,400,100,30);
//        deposit_panel.add(txt_dep_currency);
        add(txt_dep_currency);

        JLabel dep_amount = new JLabel("Amount: ");
        dep_amount.setFont(new Font("Raleway", Font.BOLD, 16));
        dep_amount.setBounds(490,400,90,30);
//        deposit_panel.add(dep_amount);
        add(dep_amount);

        txt_dep_amount = new JTextField();
        txt_dep_amount.setFont(new Font("Raleway", Font.BOLD, 14));
        txt_dep_amount.setBounds(570,400,110,30);
//        deposit_panel.add(txt_dep_amount);
        add(txt_dep_amount);

//        add(deposit_panel);

        JLabel phone = new JLabel("Phone");
        phone.setFont(new Font("Raleway", Font.BOLD, 20));
        phone.setBounds(100,450,200,30);
        add(phone);

        txt_phone = new JTextField();
        txt_phone.setFont(new Font("Raleway", Font.BOLD, 14));
        txt_phone.setBounds(280,450,400,30);
        add(txt_phone);

        JLabel nid = new JLabel("NID");
        nid.setFont(new Font("Raleway", Font.BOLD, 20));
        nid.setBounds(100,500,200,30);
        add(nid);

        txt_nid = new JTextField();
        txt_nid.setFont(new Font("Raleway", Font.BOLD, 14));
        txt_nid.setBounds(280,500,400,30);
        add(txt_nid);

        agree = new JCheckBox("I agree to terms & conditions");
        agree.setFont(new Font("Raleway", Font.BOLD, 16));
        agree.setBounds(70,600,300,30);
        agree.setBackground(Color.WHITE);
        add(agree);

        submit = new JButton("Submit");
        submit.setFont(new Font("Raleway", Font.BOLD, 20));
        submit.setBounds(600,600,130,40);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);



        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850,800);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String religion = Objects.requireNonNull(religion_details.getSelectedItem()).toString();
        String income = Objects.requireNonNull(income_details.getSelectedItem()).toString();
        String acc_title = txt_acc_title.getText();
        String acc_type = null;
        if (acc_type_checkbox1.isSelected()) {
            acc_type = "Savings";
        }
        else if (acc_type_checkbox2.isSelected()) {
            acc_type = "Current";
        }
        else if (acc_type_checkbox3.isSelected()) {
            acc_type = "Fixed";
        }
        else if (acc_type_checkbox4.isSelected()) {
            acc_type = "NRI Acc.";
        }
        else if (acc_type_checkbox5.isSelected()) {
            acc_type = "Others";
        }

        String currency = null;
        if (currency_checkbox1.isSelected()) {
            currency = "Taka";
        }
        else if (currency_checkbox2.isSelected()) {
            currency = "USD";
        }
        else if (currency_checkbox3.isSelected()) {
            currency = "EUR";
        }
        else if (currency_checkbox4.isSelected()) {
            currency = "GBP";
        }
        else if (currency_checkbox5.isSelected()) {
            currency = "Others";
        }

        String dep_currency = txt_dep_currency.getText();
        double dep_amount = Double.parseDouble(txt_dep_amount.getText());
        String phone = txt_phone.getText();
        String nid = txt_nid.getText();
        
        Random ran = new Random();
        String cardNum = "" + Math.abs((ran.nextLong() % 90000000L)+ 7732065000000000L);
        String pinNum = "" + Math.abs((ran.nextLong() % 9000L)+ 1000L);

        try {
            if (religion.isEmpty() || income.isEmpty() || acc_title.isEmpty() || acc_type == null || currency == null || dep_currency.isEmpty() || txt_dep_amount.getText().isEmpty() || phone.isEmpty() || nid.isEmpty() || !agree.isSelected()) {
                JOptionPane.showMessageDialog(null, "Please fill all the fields");
            }
            else {
                try {
                    Conn c = new Conn();
                    PreparedStatement pst1 = c.c.prepareStatement("INSERT INTO other_details (Form_Number, Religion, Income, Acc_Title, Acc_Type, Currency, Deposit_Currency, Deposit_Amount, Phone, NID) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                    pst1.setString(1,formNo);
                    pst1.setString(2,religion);
                    pst1.setString(3,income);
                    pst1.setString(4,acc_title);
                    pst1.setString(5,acc_type);
                    pst1.setString(6,currency);
                    pst1.setString(7,dep_currency);
                    pst1.setDouble(8,dep_amount);
                    pst1.setString(9,phone);
                    pst1.setString(10,nid);
                    pst1.executeUpdate();

                    PreparedStatement pst2 = c.c.prepareStatement("INSERT INTO bank_account (Form_Number, Card_Number, Pin_Number, Balance) values (?, ?, ?, ?)");
                    pst2.setString(1, formNo);
                    pst2.setString(2, cardNum);
                    pst2.setString(3, pinNum);
                    pst2.setDouble(4, dep_amount);
                    pst2.executeUpdate();

                    PreparedStatement pst = c.c.prepareStatement("select Card_Number from bank_account where Card_Number = ?");
                    pst.setString(1,cardNum);
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()){
                        cardNum = "" + Math.abs((ran.nextLong() % 90000000L)+ 7732065000000000L);
                    }
                    
                    JOptionPane.showMessageDialog(null, "Your Card Number is: "+cardNum+ "\n Your Pin Number is: "+pinNum);

                    setVisible(false);
                    new Login().setVisible(true);

                }
                catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }


    }


    public static void main(String[] args) {
        new SignUpPage2("");
    }

}
