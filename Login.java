package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JButton login =new JButton();
    JLabel userName = new JLabel();
    JLabel password = new JLabel();
    JTextField userNameTF = new JTextField();
    JPasswordField passwordPF = new JPasswordField();
    JFrame jFrame = new JFrame();
    JButton register = new JButton();
    public void login(){
        jFrame.setTitle("Konut Kiralama Sistemi");
        register.setText("Register");
        register.setBounds(500,570,100,30);
        login.setText("Login");
        login.setBounds(400,280,100,30);
        userName.setText("User Name");
        userName.setBounds(200,200,100,30);
        password.setText("Password");
        password.setBounds(200,240,100,30);
        userNameTF.setBounds(300,200,200,30);
        passwordPF.setBounds(300,240,200,30);


        jFrame.setVisible(true);
        jFrame.setLayout(null);
        jFrame.setSize(700, 700);

        jFrame.add(login);
        jFrame.add(userName);
        jFrame.add(password);
        jFrame.add(userNameTF);
        jFrame.add(passwordPF);
        jFrame.add(register);

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DbHelper dbHelper = new DbHelper();
                Connection connection = null;
                try{
                    boolean control = false;
                    String userNameS = null;
                    connection = dbHelper.getConnection();
                    System.out.println("MySQL'e Bağlandı...");

                    Statement st = connection.createStatement();
                    String query = "SELECT tckimlik,password from register WHERE tckimlik=?";
                    PreparedStatement preparedStatement=connection.prepareStatement(query);
                    preparedStatement.setString(1,userNameTF.getText());
                    ResultSet rs =preparedStatement.executeQuery();
                    while(rs.next()) {
                        if (rs.getString("tckimlik").equals(userNameTF.getText()) && rs.getString("password").equals(passwordPF.getText())) {
                            userNameS =rs.getString("tckimlik");
                            control=true;
                            break;
                        }
                    }
                    if (control==true){
                        System.out.println(userNameS);
                        Choose choose = new Choose(userNameTF.getText(),passwordPF.getText());
                        choose.chooseWindow();
                        jFrame.dispose();


                    }
                    else{
                        JOptionPane.showMessageDialog(jFrame,"Kullanıcı adı veya şifreyi yanlış girdiniz...\nTekrar Deneyiniz");
                    }

                }
                catch(Exception exception){
                    System.out.println(exception);
                }
                finally {
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register register = new Register();
                try {
                    register.registerFrame();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
