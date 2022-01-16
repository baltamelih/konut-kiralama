package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class RentAHouse extends JFrame {
    String tckimlik,userName;
    private String telefonNo;
    public RentAHouse(String tckimlik,String userName) throws SQLException {
        this.tckimlik =  tckimlik;
        this.userName = userName;
    }
    public String getTelefonNo(){
        return telefonNo;
    }
    public void setTelefonNo() throws SQLException {
        DbHelper dbHelper = new DbHelper();
        Connection connection = null;
        connection = dbHelper.getConnection();
        String query = "SELECT numberPhone From register WHERE tckimlik = ? && password =?";
        Statement st = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,tckimlik);
        preparedStatement.setString(2,userName);
        ResultSet rs = preparedStatement.executeQuery();
        String tel = null;
        while (rs.next()) {
            tel =rs.getString("numberPhone");
        }
        evSahibiTelTF.setText(tel);
    }
    JFrame jFrame = new JFrame();
    JLabel il = new JLabel();
    JLabel ilce = new JLabel();
    JLabel mahalle = new JLabel();
    JLabel sokak = new JLabel();
    JLabel bina = new JLabel();
    JLabel no = new JLabel();
    JLabel kat = new JLabel();
    JLabel toplamKat = new JLabel();
    JLabel binaYil = new JLabel();
    JLabel esyaDurumu = new JLabel();
    JLabel metrekare = new JLabel();
    JLabel isitma = new JLabel();
    JLabel evSahibiTel = new JLabel();
    JLabel fiyat = new JLabel();
    JLabel odaSayisi = new JLabel();
    JLabel aciklama = new JLabel();
    JButton kayit = new JButton();
    JComboBox ilList = new JComboBox(iller().toArray());
    JComboBox ilceList= new JComboBox();
    JTextField mahalleTF = new JTextField();
    JTextField sokakTF = new JTextField();JTextField binaYilTF = new JTextField();
    JTextField binaTF = new JTextField();JCheckBox esyali = new JCheckBox("Var");
    JCheckBox esyasiz = new JCheckBox("Yok");JComboBox odaSayisiCB = new JComboBox();
    JTextField noTF = new JTextField();JComboBox isitmaCB = new JComboBox();
    JTextField katTF = new JTextField();JTextField evSahibiTelTF = new JTextField();
    JTextField toplamKatTF = new JTextField();JTextField fiyatTF = new JTextField();
    JTextField metrekareTF = new JTextField();JTextField aciklamaTF = new JTextField();
    public int random(){
        Random random = new Random();
        int id = random.nextInt(100000,1000000);
        return id;
    }
    public void addDataBase() throws SQLException {
        try {
            DbHelper dbHelper = new DbHelper();
            Connection connection = null;
            connection = dbHelper.getConnection();
            Statement st = connection.createStatement();
            String sql = String.format("insert into rentahouse values ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                    random(),ilList.getSelectedItem(), ilceList.getSelectedItem(),mahalleTF.getText(), sokakTF.getText(),
                    binaTF.getText(), katTF.getText(), noTF.getText(), toplamKatTF.getText(),
                    binaYilTF.getText(), esyali.getText(), metrekareTF.getText(), odaSayisiCB.getSelectedItem(),
                    isitmaCB.getSelectedItem(), evSahibiTelTF.getText(), fiyatTF.getText(),
                    aciklamaTF.getText());
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(jFrame, "Kayıt başarılı bir şekilde gerçekleşti...\nTeşekkür ederiz!");

        }catch (Exception exception){
            JOptionPane.showMessageDialog(jFrame, exception);
        }

    }


    public ArrayList<String> iller() throws SQLException {
        DbHelper dbHelper = new DbHelper();
        Connection connection = null;
        ArrayList <String> ilListDb = new ArrayList<>();
        connection = dbHelper.getConnection();
        Statement st = connection.createStatement();
        String query = "SELECT sehiradi from iller";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        ResultSet rs =preparedStatement.executeQuery();
        while (rs.next()){
            ilListDb.add(rs.getString("sehiradi"));
        }
        return ilListDb;
    }


    public void ilceler()throws SQLException {

        DbHelper dbHelper = new DbHelper();
        ilList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedID = null;
                String selected;
                try{
                    ilceList.removeAllItems();
                    selected = ilList.getSelectedItem().toString();
                    System.out.println(selected);
                    System.out.println("MySQL'e Bağlandı...");
                    Connection connection = null;
                    connection=dbHelper.getConnection();
                    String query2 = "SELECT id from iller WHERE sehiradi=? ";
                    Statement st = connection.createStatement();
                PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
                preparedStatement2.setString(1, selected);
                ResultSet rs2 = preparedStatement2.executeQuery();
                while (rs2.next()) {
                    selectedID =rs2.getString("id");
                }
                    System.out.println(selectedID);
                String query = "SELECT ilceadi from ilceler WHERE sehirid = ?";
                Statement st2 = connection.createStatement();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, Integer.parseInt(selectedID));
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    ilceList.addItem(rs.getString("ilceadi"));

                }
                }
                catch (Exception exception){
                    System.out.println(exception);
                }

            }
        });
    }


    public void  rent() throws SQLException {
        ilceler();
        setTelefonNo();

        kayit.setText("Kayıt");
        il.setText("İl");
        ilce.setText("İlçe");
        mahalle.setText("Mahalle");
        sokak.setText("Sokak");
        bina.setText("Bina");
        no.setText("No");
        kat.setText("Bulunduğu kat");
        toplamKat.setText("Toplam kat");
        binaYil.setText("Bina yılı");
        esyaDurumu.setText("Eşya durumu");
        metrekare.setText("Metrekare");
        isitma.setText("Isıtma biçimi");
        evSahibiTel.setText("Ev sahibi tel");
        fiyat.setText("Fiyat");
        odaSayisi.setText("Oda sayısı");
        aciklama.setText("Açıklama");

        il.setBounds(10,10,100,30);
        ilce.setBounds(260,10,100,30);
        mahalle.setBounds(480,10,100,30);
        sokak.setBounds(10,50,100,30);
        bina.setBounds(260,50,100,30);
        no.setBounds(480,50,100,30);
        kat.setBounds(10,90,100,30);
        toplamKat.setBounds(260,90,100,30);
        binaYil.setBounds(480,90,100,30);
        esyaDurumu.setBounds(10,130,100,30);
        metrekare.setBounds(260,130,100,30);
        isitma.setBounds(480,130,100,30);
        evSahibiTel.setBounds(10,170,100,30);
        fiyat.setBounds(260,170,100,30);
        odaSayisi.setBounds(480,170,100,30);
        aciklama.setBounds(10,210,100,30);
        kayit.setBounds(300,600,100,30);
        ilList.setBounds(120,10,100,30);
        ilceList.setBounds(380,10,100,30);
        mahalleTF.setBounds(600,10,100,30);
        sokakTF.setBounds(120,50,100,30);
        binaTF.setBounds(380,50,100,30);
        katTF.setBounds(120,90,100,30);
        noTF.setBounds(600,50,100,30);
        toplamKatTF.setBounds(380,90,100,30);
        binaYilTF.setBounds(600,90,100,30);
        esyali.setBounds(120,130,60,30);
        esyasiz.setBounds(190,130,60,30);
        metrekareTF.setBounds(380,130,100,30);
        odaSayisiCB.setBounds(600,170,100,30);
        evSahibiTelTF.setBounds(120,170,100,30);
        fiyatTF.setBounds(380,170,100,30);
        aciklamaTF.setBounds(120,210,580,100);
        isitmaCB.setBounds(600,130,100,30);


        isitmaCB.addItem("Doğalgaz");
        isitmaCB.addItem("Kalorifer");
        isitmaCB.addItem("Soba");
        odaSayisiCB.addItem("1+0");
        odaSayisiCB.addItem("1+1");
        odaSayisiCB.addItem("2+0");
        odaSayisiCB.addItem("2+1");
        odaSayisiCB.addItem("2+2");
        odaSayisiCB.addItem("3+1");
        odaSayisiCB.addItem("3+0");
        odaSayisiCB.addItem("3+2");
        odaSayisiCB.addItem("4+1");
        odaSayisiCB.addItem("4+2");
        odaSayisiCB.addItem("5+");
        evSahibiTelTF.setEnabled(false);
        kayit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addDataBase();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });



        jFrame.setVisible(true);
        jFrame.setLayout(null);
        jFrame.setSize(750, 700);

        jFrame.add(il);
        jFrame.add(ilce);
        jFrame.add(mahalle);
        jFrame.add(sokak);
        jFrame.add(bina);
        jFrame.add(no);
        jFrame.add(kat);
        jFrame.add(toplamKat);
        jFrame.add(binaYil);
        jFrame.add(esyaDurumu);
        jFrame.add(metrekare);
        jFrame.add(isitma);
        jFrame.add(evSahibiTel);
        jFrame.add(fiyat);
        jFrame.add(odaSayisi);
        jFrame.add(aciklama);
        jFrame.add(kayit);
        jFrame.add(ilList);
        jFrame.add(ilceList);
        jFrame.add(mahalleTF);
        jFrame.add(sokakTF);
        jFrame.add(binaTF);
        jFrame.add(katTF);
        jFrame.add(noTF);
        jFrame.add(toplamKatTF);
        jFrame.add(binaYilTF);
        jFrame.add(esyali);
        jFrame.add(esyasiz);
        jFrame.add(metrekareTF);
        jFrame.add(odaSayisiCB);
        jFrame.add(evSahibiTelTF);
        jFrame.add(fiyatTF);
        jFrame.add(aciklamaTF);
        jFrame.add(isitmaCB);
    }
}
