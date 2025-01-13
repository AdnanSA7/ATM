
package javaapplication8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class CashWithdraw extends JFrame implements ActionListener {

    String cardNum,pinNum;
    double amount;
    JButton btn_withdraw,btn_back;
    JTextField txt_amount;

    public CashWithdraw(String cardNum, String pinNum) {
        this.cardNum = cardNum;
        this.pinNum = pinNum;
        ImageIcon i1 = new ImageIcon("icons/atm.jpg");
        setIconImage(i1.getImage());
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Please enter the amount you would like to withdraw");
        text.setFont(new Font("Raleway", Font.BOLD, 14));
        text.setBounds(160, 300, 700, 35);
        text.setForeground(Color.WHITE);
        image.add(text);

        txt_amount = new JTextField();
        txt_amount.setFont(new Font("Raleway", Font.BOLD, 14));
        txt_amount.setBounds(176,350, 320, 35);
        image.add(txt_amount);

        btn_withdraw = new JButton("Withdraw");
        btn_withdraw.setBounds(362, 486, 150, 29);
        btn_withdraw.addActionListener(this);
        image.add(btn_withdraw);

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
        else if (e.getSource() == btn_withdraw) {
            amount = Double.parseDouble(txt_amount.getText());
            if(txt_amount.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter the amount you would like to deposit");
            }
            else{
                try {
                    Conn c = new Conn();
                    Date date = new Date();
                    String query1 = "select balance from bank_account where Card_Number = '"+cardNum+"' and Pin_Number = '"+pinNum+"'";
                    ResultSet rs = c.st.executeQuery(query1);
                    
                    if(rs.next()){
                        double check = rs.getDouble("Balance");
                        if(check<amount){
                            JOptionPane.showMessageDialog(null, "Insufficient Balance");
                        }
                        else{
                            String pin;
                            do{
                                pin = JOptionPane.showInputDialog("Please enter your pin");
                                double balance = check - amount;
                                String query2 = "UPDATE bank_account SET Balance = '"+balance+"' where Card_Number = '"+cardNum+"' and Pin_Number = '"+pinNum+"'";
                                String query3 = "INSERT INTO bank_account_transactions (Card_Number, Pin_Number, Date, Type, Amount, Balance) values ('"+cardNum+"','"+pinNum+"','"+date+"', 'Withdraw', '"+amount+"', '"+balance+"')";
                                c.st.executeUpdate(query2);
                                c.st.executeUpdate(query3);
                                JOptionPane.showMessageDialog(null, "Cash Withdrawal Successful");
                                setVisible(false);
                                new Transaction(cardNum, pinNum).setVisible(true);
                            }
                            while(!pin.equals(pinNum));
                        }
                    }
                    
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }
    }

    public static void main(String[] args) {
        new CashWithdraw("","");
    }
}
