
package javaapplication8;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.Random;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpPage extends JFrame implements ActionListener {

    long randomNum;
    JTextField txt_name, txt_f_name, txt_m_name, txt_email, txt_address, txt_occupation;
    JButton next;
    JDateChooser dateChooser;
    JRadioButton male, female;
    JComboBox<String> dis_option;

    SignUpPage(){

        setTitle("Sign Up Page");
        setLayout(null);

        Random ran = new Random();
        randomNum = Math.abs((ran.nextLong() % 90000L)+ 10000L);
        try {
            Conn conn = new Conn();
            PreparedStatement pst_ran = conn.c.prepareStatement("select Form_Number from bank_account where Form_number = ?");
            pst_ran.setString(1,(""+randomNum));
            ResultSet rs = pst_ran.executeQuery();
            while (rs.next()){
                randomNum = Math.abs((ran.nextLong() % 90000L)+ 10000L);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

        JLabel formNo = new JLabel("Application Form No. "+ randomNum);
        formNo.setFont(new Font("Raleway", Font.BOLD, 40));
        formNo.setBounds(150,20,600,40);
        add(formNo);

        JLabel personalDetails = new JLabel("Personal Details");
        personalDetails.setFont(new Font("Raleway", Font.BOLD, 40));
        personalDetails.setBounds(250,80,400,30);
        add(personalDetails);

        JLabel name = new JLabel("Name: ");
        name.setFont(new Font("Raleway", Font.BOLD, 20));
        name.setBounds(100,150,100,30);
        add(name);

        txt_name = new JTextField();
        txt_name.setFont(new Font("Raleway", Font.BOLD, 14));
        txt_name.setBounds(270,150,400,30);
        add(txt_name);

        JLabel f_name = new JLabel("Father's Name: ");
        f_name.setFont(new Font("Raleway", Font.BOLD, 20));
        f_name.setBounds(100,200,200,30);
        add(f_name);

        txt_f_name = new JTextField();
        txt_f_name.setFont(new Font("Raleway", Font.BOLD, 14));
        txt_f_name.setBounds(270,200,400,30);
        add(txt_f_name);

        JLabel m_name = new JLabel("Mother's Name: ");
        m_name.setFont(new Font("Raleway", Font.BOLD, 20));
        m_name.setBounds(100,250,200,30);
        add(m_name);

        txt_m_name = new JTextField();
        txt_m_name.setFont(new Font("Raleway", Font.BOLD, 14));
        txt_m_name.setBounds(270,250,400,30);
        add(txt_m_name);

        JLabel dob = new JLabel("Date of Birth: ");
        dob.setFont(new Font("Raleway", Font.BOLD, 20));
        dob.setBounds(100,300,200,30);
        add(dob);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(270,300,400,30);
        add(dateChooser);

        JLabel gender = new JLabel("Gender: ");
        gender.setFont(new Font("Raleway", Font.BOLD, 20));
        gender.setBounds(100,350,200,30);
        add(gender);

        male = new JRadioButton("Male");
        male.setFont(new Font("Raleway", Font.BOLD, 14));
        male.setBounds(270,350,60,30);
        male.setBackground(Color.WHITE);
        add(male);

        female = new JRadioButton("Female");
        female.setFont(new Font("Raleway", Font.BOLD, 14));
        female.setBounds(420,350,130,30);
        female.setBackground(Color.WHITE);
        add(female);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        JLabel email = new JLabel("Email: ");
        email.setFont(new Font("Raleway", Font.BOLD, 20));
        email.setBounds(100,400,200,30);
        add(email);

        txt_email = new JTextField();
        txt_email.setFont(new Font("Raleway", Font.BOLD, 14));
        txt_email.setBounds(270,400,400,30);
        add(txt_email);

        JLabel address = new JLabel("Address: ");
        address.setFont(new Font("Raleway", Font.BOLD, 20));
        address.setBounds(100,450,200,30);
        add(address);

        txt_address = new JTextField();
        txt_address.setFont(new Font("Raleway", Font.BOLD, 14));
        txt_address.setBounds(270,450,400,30);
        add(txt_address);

        JLabel district = new JLabel("District: ");
        district.setFont(new Font("Raleway", Font.BOLD, 20));
        district.setBounds(100,500,200,30);
        add(district);

        String [] items = {"Dhaka", "Chittagong", "Barishal", "Sylhet", "Rajshashi", "Dinajpur", "Khulna"};
        dis_option = new JComboBox<>(items);
        dis_option.setBounds(270,500,400,30);
        dis_option.setSelectedItem(items[0]);
        add(dis_option);

        JLabel occupation = new JLabel("Occupation: ");
        occupation.setFont(new Font("Raleway", Font.BOLD, 20));
        occupation.setBounds(100,550,200,30);
        add(occupation);

        txt_occupation = new JTextField();
        txt_occupation.setFont(new Font("Raleway", Font.BOLD, 14));
        txt_occupation.setBounds(270,550,400,30);
        add(txt_occupation);

        next = new JButton("Next");
        next.setFont(new Font("Raleway", Font.BOLD, 20));
        next.setBounds(610,625,100,35);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850,800);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String formNo = "" + randomNum;
        String name = txt_name.getText();
        String f_name = txt_f_name.getText();
        String m_name = txt_m_name.getText();
        String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String gender = null;
        if (male.isSelected()) {
            gender = "Male";
        }
        else if (female.isSelected()) {
            gender = "Female";
        }
//        else {
//            JOptionPane.showMessageDialog(null, "Please select a gender");
//        }
        String occupation = txt_occupation.getText();
        String email = txt_email.getText();
        String address = txt_address.getText();
        String district = Objects.requireNonNull(dis_option.getSelectedItem()).toString();


            try {
                if (name.isEmpty() || f_name.isEmpty() || m_name.isEmpty() || dob.isEmpty() || gender == null || occupation.isEmpty() || email.isEmpty() || address.isEmpty() || district.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields");
                }
                else {
                    try {
                        Conn c = new Conn();
                        PreparedStatement pst = c.c.prepareStatement("INSERT INTO account_details (Form_Number, Name, Father_Name, Mother_Name, Date_of_Birth, Gender, Email, Address, District, Occupation) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                        pst.setString(1,formNo);
                        pst.setString(2,name);
                        pst.setString(3,f_name);
                        pst.setString(4,m_name);
                        pst.setString(5,dob);
                        pst.setString(6,gender);
                        pst.setString(7,email);
                        pst.setString(8,address);
                        pst.setString(9,district);
                        pst.setString(10,occupation);
                        pst.executeUpdate();

                        setVisible(false);
                        new SignUpPage2(formNo).setVisible(true);

                    } catch (Exception ex) {
                        System.out.println(e);
                    }
                }

            }
            catch (Exception ex) {
                System.out.println(ex);
            }


    }

    public static void main(String[] args) {
        new SignUpPage();
    }
}

