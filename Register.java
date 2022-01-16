package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Register extends JFrame implements ActionListener {
    DbHelper helper = new DbHelper();
    Connection connection = null;
    JLabel name = new JLabel();
    JLabel surname = new JLabel();
    JLabel tc_kimlik = new JLabel();
    JLabel numberPhone = new JLabel();
    JLabel email = new JLabel();
    JLabel password = new JLabel();
    JLabel passwordControl = new JLabel();

    JTextField nameTF = new JTextField();
    JTextField surnameTF = new JTextField();
    JTextField tc_kimlikTF = new JTextField();
    JTextField numberPhoneTF = new JTextField();
    JTextField emailTF = new JTextField();
    JPasswordField passwordTF = new JPasswordField();
    JPasswordField passwordControlTF = new JPasswordField();
    JButton registerButton = new JButton();
    JFrame jFrame = new JFrame();
    public void registerFrame() throws SQLException {

        registerButton.setText("Kayıt ol");
        registerButton.setBounds(200,400,100,30);




        name.setText("Name");
        surname.setText("Surname");
        tc_kimlik.setText("TC-Kimlik No");
        numberPhone.setText("Number phone");
        email.setText("E-Mail");
        password.setText("Password");
        passwordControl.setText("Control Password");

        name.setBounds(10,10,100,30);
        surname.setBounds(10,70,100,30);
        tc_kimlik.setBounds(10,130,100,30);
        numberPhone.setBounds(10,190,100,30);
        email.setBounds(10,250,100,30);
        password.setBounds(10,310,100,30);
        passwordControl.setBounds(10,370,100,30);


        nameTF.setBounds(150,10,200,30);
        surnameTF.setBounds(150,70,200,30);
        tc_kimlikTF.setBounds(150,130,200,30);
        numberPhoneTF.setBounds(150,190,200,30);
        emailTF.setBounds(150,250,200,30);
        passwordTF.setBounds(150,310,200,30);
        passwordControlTF.setBounds(150,370,200,30);


        jFrame.setVisible(true);
        jFrame.setLayout(null);
        jFrame.setSize(700, 700);



        jFrame.add(registerButton);
        jFrame.add(name);
        jFrame.add(surname);
        jFrame.add(tc_kimlik);
        jFrame.add(numberPhone);
        jFrame.add(email);
        jFrame.add(nameTF);
        jFrame.add(surnameTF);
        jFrame.add(tc_kimlikTF);
        jFrame.add(numberPhoneTF);
        jFrame.add(emailTF);
        jFrame.add(password);
        jFrame.add(passwordControl);
        jFrame.add(passwordTF);
        jFrame.add(passwordControlTF);

        registerButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            connection = helper.getConnection();
            System.out.println("MySQL'e Bağlandı");
            Statement st = connection.createStatement();

            if (passwordTF.getText().equalsIgnoreCase(passwordControlTF.getText())){
                String sql = String.format("insert into register values('%s','%s','%s','%s','%s','%s')",tc_kimlikTF.getText(),nameTF.getText(),surnameTF.getText(),emailTF.getText(),numberPhoneTF.getText(),passwordTF.getText());
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(jFrame,"Kayıt başarılı bir şekilde gerçekleşti...\nTeşekkür ederiz!");
                jFrame.dispose();
            }
            else {
                System.out.println("Şifreniz eşleşmedi...");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
}
