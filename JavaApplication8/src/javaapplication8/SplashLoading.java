
package javaapplication8;

import javax.swing.*;
import java.awt.*;

public class SplashLoading extends JFrame implements Runnable {

    JProgressBar progressBar;
    String cardNum,pinNum;

    @Override
    public void run() {
        try {
            for (int i=0; i <=100; i++){
                Thread.sleep(7);
                progressBar.setValue(i);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

        setVisible(false);
        new Transaction(cardNum,pinNum).setVisible(true);

    }

    public SplashLoading(String cardNum,String pinNum) {
        this.pinNum = pinNum;
        this.cardNum = cardNum;
        setLayout(null);
        JLabel l1 = new JLabel("Welcome");
        l1.setFont(new Font("Raleway", Font.BOLD, 50));
        l1.setBounds(0,100,900,50);
        l1.setHorizontalAlignment(SwingConstants.CENTER);
        add(l1);

        JLabel l2 = new JLabel("To");
        l2.setFont(new Font("Raleway", Font.BOLD, 50));
        l2.setBounds(0,200,900,50);
        l2.setHorizontalAlignment(SwingConstants.CENTER);
        add(l2);

        JLabel l3 = new JLabel("Automated Teller Machine");
        l3.setFont(new Font("Raleway", Font.BOLD, 50));
        l3.setBounds(0,300,900,50);
        l3.setHorizontalAlignment(SwingConstants.CENTER);
        add(l3);

        progressBar = new JProgressBar();
        progressBar.setBounds(125,500,650,30);
        progressBar.setIndeterminate(true);
        progressBar.setStringPainted(true);
        add(progressBar);

        Thread thread = new Thread(this);
        thread.start();

        setSize(900, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.CYAN);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SplashLoading("","");
    }
}
