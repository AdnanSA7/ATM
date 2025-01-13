package javaapplication8;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

public class MiniStatement extends JFrame implements ActionListener {

    String cardNum;
    String pinNum;

    public MiniStatement(String cardNum, String pinNum) {
        this.cardNum = cardNum;
        this.pinNum = pinNum;
        setTitle("MiniStatement");
        setLayout(null);

        JLabel bank = new JLabel("ATM Bank");
        bank.setFont(new Font("Raleway", Font.BOLD, 25));
        bank.setBounds(165,20,150,25);
        bank.setHorizontalAlignment(SwingConstants.CENTER);
        add(bank);

        JLabel card = new JLabel();
        card.setFont(new Font("Raleway", Font.BOLD, 20));
        card.setBounds(20,80,400,20);
        add(card);

        try {
            Conn conn = new Conn();
            PreparedStatement pst = conn.c.prepareStatement("select * from bank_account where Pin_Number=?");
            pst.setString(1, pinNum);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                card.setText("Card Number: "+rs.getString("Card_Number").substring(0,4)+"XXXXXXXX"+rs.getString("Card_Number").substring(12));
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }

        DefaultTableModel d = new DefaultTableModel();
        d.addColumn("Transaction Date");
        d.addColumn("Transaction Type");
        d.addColumn("Transaction Amount");
        JTable show_table = new JTable(d);
        try {
            Conn conn = new Conn();
            PreparedStatement pst_show = conn.c.prepareStatement("select Date, Type, Amount from bank_account_transactions where Card_Number = ?");
            pst_show.setString(1,cardNum);
            ResultSet rs_show = pst_show.executeQuery();
//            show_table.setModel(d);
//            ResultSetMetaData rsmd = rs_show.getMetaData();
//            int col = rsmd.getColumnCount();
            d.setRowCount(0);

            while (rs_show.next()){
                Vector<String> v = new Vector<>();
                    v.add(rs_show.getString("Date"));
                    v.add(rs_show.getString("Type"));
                    v.add(rs_show.getString("Amount"));
                    d.addRow(v);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
//        show_table.setModel(d);
//        d.fireTableDataChanged();

//        show_table.setVisible(true);
        JScrollPane jsp = new JScrollPane(show_table);
        jsp.setBounds(20,170,450,200);
        add(jsp);


        setSize(500, 500);
        getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new MiniStatement("","");
    }
}
