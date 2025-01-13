

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class TableExample  {
    JFrame frame;
    JTable table;
  
    public TableExample() {
        frame=new JFrame();
        
        
         
      String values [][]=
      {
          {"101","Rahat","Dhaka"},
          {"102","Nahid","Khulna"},
          {"103","Jahid","Barisal"},
          {"101","Rahat","Dhaka"},
          {"102","Nahid","Khulna"},
          {"103","Jahid","Barisal"},
          {"101","Rahat","Dhaka"},
          {"102","Nahid","Khulna"},
          {"103","Jahid","Barisal"},
          {"101","Rahat","Dhaka"},
          {"102","Nahid","Khulna"},
          {"103","Jahid","Barisal"},
          {"101","Rahat","Dhaka"},
          {"102","Nahid","Khulna"},
          {"103","Jahid","Barisal"},
          {"101","Rahat","Dhaka"},
          {"102","Nahid","Khulna"},
          {"103","Jahid","Barisal"},
          {"101","Rahat","Dhaka"},
          {"102","Nahid","Khulna"},
          {"103","Jahid","Barisal"},
          {"101","Rahat","Dhaka"},
          {"102","Nahid","Khulna"},
          {"103","Jahid","Barisal"},
          {"101","Rahat","Dhaka"},
          {"102","Nahid","Khulna"},
          {"103","Jahid","Barisal"},
          {"101","Rahat","Dhaka"},
          {"102","Nahid","Khulna"},
          {"103","Jahid","Barisal"},
          {"101","Rahat","Dhaka"},
          {"102","Nahid","Khulna"},
          {"103","Jahid","Barisal"},
          {"101","Rahat","Dhaka"},
          {"102","Nahid","Khulna"},
          {"103","Jahid","Barisal"},
          {"101","Rahat","Dhaka"},
          {"102","Nahid","Khulna"},
          {"103","Jahid","Barisal"},
          {"101","Rahat","Dhaka"},
          {"102","Nahid","Khulna"},
          {"103","Jahid","Barisal"},
          {"101","Rahat","Dhaka"},
          {"102","Nahid","Khulna"},
          {"103","Jahid","Barisal"},
          {"101","Rahat","Dhaka"},
          {"102","Nahid","Khulna"},
          {"103","Jahid","Barisal"},
          {"101","Rahat","Dhaka"},
          {"102","Nahid","Khulna"},
          {"103","Jahid","Barisal"},
      };
      
      String col_heading []={"ID","Name","Address"};
      table =new JTable(values, col_heading);
      table.setBounds(50, 50, 200, 200);
        JScrollPane sp =new JScrollPane(table);
        
      frame.setBounds(250, 50, 800, 500);
      FlowLayout fw = new FlowLayout();
      frame.add(sp);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setBackground(Color.WHITE);
      frame.setLayout(fw);
       frame.setVisible(true);
         
    }
    public static void main(String[] args) {
        new TableExample();
    }
    
    
    
}
