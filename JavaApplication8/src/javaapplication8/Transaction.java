
package javaapplication8;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JFrame;

public class Transaction extends JFrame implements ActionListener {

    String cardNum,pinNum;
    JButton btn_deposit, btn_withdraw, btn_pin_change, btn_balance, btn_transfer, btn_mini_statement, btn_exit;

    public Transaction(String cardNum, String pinNum){
        this.cardNum = cardNum;
        this.pinNum = pinNum;
//        setTitle("Transaction");
        setLayout(null);

        ImageIcon i1 = new ImageIcon("icons/atm.jpg");
        setIconImage(i1.getImage());
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Please select your Transaction");
        text.setFont(new Font("Raleway", Font.BOLD, 16));
        text.setBounds(220, 300, 700, 35);
        text.setForeground(Color.WHITE);
        image.add(text);

        btn_deposit = new JButton("Deposit");
        btn_deposit.setBounds(161, 417, 150, 29);
        btn_deposit.addActionListener(this);
        image.add(btn_deposit);

        btn_withdraw = new JButton("Cash Withdraw");
        btn_withdraw.setBounds(362, 417, 150, 29);
        btn_withdraw.addActionListener(this);
        image.add(btn_withdraw);

        btn_pin_change = new JButton("Pin Change");
        btn_pin_change.setBounds(161, 451, 150, 29);
        btn_pin_change.addActionListener(this);
        image.add(btn_pin_change);

        btn_balance = new JButton("Balance Inquiry");
        btn_balance.setBounds(362, 451, 150, 29);
        btn_balance.addActionListener(this);
        image.add(btn_balance);

        btn_transfer = new JButton("Transfer");
        btn_transfer.setBounds(161, 486, 150, 29);
        btn_transfer.addActionListener(this);
        image.add(btn_transfer);

        btn_mini_statement = new JButton("Mini Statement");
        btn_mini_statement.setBounds(362, 486, 150, 29);
        btn_mini_statement.addActionListener(this);
        image.add(btn_mini_statement);

        btn_exit = new JButton("Exit");
        btn_exit.setBounds(362, 520, 150, 29);
        btn_exit.addActionListener(this);
        image.add(btn_exit);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 900);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btn_deposit){
            setVisible(false);
            new Deposit(cardNum,pinNum).setVisible(true);
        }
        else if(e.getSource() == btn_withdraw){
            setVisible(false);
            new CashWithdraw(cardNum,pinNum).setVisible(true);
        }
        else if(e.getSource() == btn_pin_change){
            setVisible(false);
            new PinChange(cardNum,pinNum).setVisible(true);
        }
        else if(e.getSource() == btn_balance){
            setVisible(false);
            new BalanceInquiry(cardNum, pinNum).setVisible(true);
        }
        else if(e.getSource() == btn_transfer){
            setVisible(false);
            new Transfer(cardNum,pinNum).setVisible(true);
        }
        else if(e.getSource() == btn_mini_statement){
            new MiniStatement(cardNum, pinNum).setVisible(true);
        }
        else if(e.getSource() == btn_exit){
            System.exit(0);
        }

    }

    public static void main(String[] args) {
        new Transaction("","");
    }
}
