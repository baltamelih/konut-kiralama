package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Choose extends JFrame {
    String tckimlik,userName;
    public Choose(String tckimlik, String userName) throws SQLException {
        this.tckimlik =  tckimlik;
        this.userName = userName;
    }
    JFrame jFrame = new JFrame();
    JButton evAraButton = new JButton();
    JButton evKiralaButton = new JButton();
    JButton kiralananEvler = new JButton();



    public void chooseWindow(){
        jFrame.setTitle("Giriş seçenekleri");

        evAraButton.setText("Ev ara");
        evKiralaButton.setText("Ev kirala");
        kiralananEvler.setText("Kiralanmış evler");
        evAraButton.setBounds(100,60,140,30);
        evKiralaButton.setBounds(100,110,140,30);
        kiralananEvler.setBounds(100,160,140,30);
        kiralananEvler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ForRent forRent = new ForRent(tckimlik,userName);
                    forRent.kiralananEv();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });

        evKiralaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    RentAHouse  rentAHouse = new RentAHouse(tckimlik,userName);
                    rentAHouse.rent();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
        evAraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    ForRent forRent = new ForRent(tckimlik,userName);
                    forRent.rent();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });




        jFrame.setVisible(true);
        jFrame.setLayout(null);
        jFrame.setSize(340, 340);
        jFrame.add(kiralananEvler);
        jFrame.add(evAraButton);
        jFrame.add(evKiralaButton);


    }


}
