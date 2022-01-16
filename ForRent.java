package com.company;

import org.w3c.dom.Text;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ForRent extends JFrame{
    String tckimlik,userName;
    public ForRent(String tckimlik, String userName) throws SQLException {
        this.tckimlik =  tckimlik;
        this.userName = userName;
    }

    String ilDb,ilceDb,mahalleDb,sokakDb,binaDb,katDb,noDb,toplamKatDb,binaYilDb,esyaDurumuDb,metrekareDb,odaSayisiDb,isitmaDb,evSahibiTelDb,fiyatDb,aciklamaDb,idDb;

    JFrame jFrame = new JFrame();
    JComboBox chooseComboBox = new JComboBox();
    RentAHouse rentAHouse = new RentAHouse(tckimlik,userName);
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
    JComboBox ilList = new JComboBox(rentAHouse.iller().toArray());
    JComboBox ilceList= new JComboBox();
    JTextField mahalleTF = new JTextField();
    JTextField sokakTF = new JTextField();JTextField binaYilTF = new JTextField();
    JTextField binaTF = new JTextField();
    JTextField esyaDurumuL = new JTextField();JTextField odaSayisiCB = new JTextField();
    JTextField noTF = new JTextField();JTextField isitmaCB = new JTextField();
    JTextField katTF = new JTextField();JTextField evSahibiTelTF = new JTextField();
    JTextField toplamKatTF = new JTextField();JTextField fiyatTF = new JTextField();
    JTextField metrekareTF = new JTextField();JTextField aciklamaTF = new JTextField();
    JLabel ilanNo = new JLabel(); JTextField ilanNoTF = new JTextField();
    JButton goster = new JButton();
    JButton kiralık = new JButton();



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
    public void kiralananEv() throws SQLException{
        DbHelper dbHelper = new DbHelper();
        try {
            JFrame jFrame1 = new JFrame();

            jFrame1.setTitle("Kiralanan ev ekranı");
            JTable table = new JTable();
            Connection connection = null;
            connection = dbHelper.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM kiralananevler");

            String column[] = {"Id","Ev Sahibi Ad","Ev Sahibi Soyad","Ev Sahibi Telefon","Kiracı Ad","Kiracı Soyad"};
            int sutunSayisi=rs.getMetaData().getColumnCount();
            DefaultTableModel dtm2 = new DefaultTableModel();
            for(int j=0;j<sutunSayisi;j++){
                dtm2.addColumn(column[j]);
            }
            while(rs.next()){
                Object[]row = new Object[sutunSayisi];
                for(int i=1; i<= sutunSayisi;i++){
                    row[i-1]=rs.getObject(i);
                }
                dtm2.addRow(row);
            }
            table.setModel(dtm2);
            rs.close();
            connection.close();
            table.setBounds(1,10,600,400);
            JScrollPane jsp = new JScrollPane(table);
            jFrame1.add(jsp);
            jFrame1.setSize(700, 500);
            jFrame1.setVisible(true);


        }
        catch (Exception exception){
            System.out.println(exception);
        }






    }
public void rent() throws SQLException {
        ilceler();
jFrame.setTitle("Ev kiralama ekranı");
    DbHelper dbHelper = new DbHelper();
    chooseComboBox.addItem("Tümü");
    chooseComboBox.addItem("Konuma Göre");
    chooseComboBox.addItem("Fiyata Göre");

    kiralık.setText("Kirala");
    goster.setText("Göster");
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
    ilanNo.setText("İlan numarası");


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
    esyaDurumuL.setBounds(120,130,100,30);

    metrekareTF.setBounds(380,130,100,30);
    odaSayisiCB.setBounds(600,170,100,30);
    evSahibiTelTF.setBounds(120,170,100,30);
    fiyatTF.setBounds(380,170,100,30);
    aciklamaTF.setBounds(120,210,580,100);
    isitmaCB.setBounds(600,130,100,30);
    ilanNo.setBounds(120,390,100,30);
    ilanNoTF.setBounds(310,390,100,30);
    chooseComboBox.setBounds(120,350,150,25);
    goster.setBounds(450,350,100,30);
    kiralık.setBounds(450,390,100,30);




    kiralık.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                int result = JOptionPane.showConfirmDialog(jFrame,"Kiralamak üzeresiniz, emin misiniz?","Emin misin?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(result==JOptionPane.YES_OPTION){
                    Connection connection = null;
                    connection = dbHelper.getConnection();
                    String query = "SELECT evsahibitelefon FROM rentahouse WHERE id_rentahouse = ?";
                    Statement st = connection.createStatement();
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, ilanNoTF.getText());
                    ResultSet rs = preparedStatement.executeQuery();
                    String telefonNumber=null;
                    while (rs.next()) {
                        telefonNumber = rs.getString("evsahibitelefon");
                    }

                    String query1 = "SELECT name,surname FROM register WHERE numberPhone=?";
                    Statement st1 = connection.createStatement();
                    PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
                    preparedStatement1.setString(1,telefonNumber);
                    ResultSet rs1 = preparedStatement1.executeQuery();
                    String name = null;
                    String surname= null;
                    String Kname= null;
                    String Ksurname=null;
                    while (rs1.next()) {
                        name = rs1.getString("name");
                        surname = rs1.getString("surname");
                    }

                    String query2 = "SELECT name,surname FROM register WHERE tckimlik =?";
                    Statement st2 = connection.createStatement();
                    PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
                    preparedStatement.setString(1, tckimlik);
                    ResultSet rs2 = preparedStatement1.executeQuery();

                    while (rs2.next()) {
                        Kname = rs2.getString("name");
                        Ksurname = rs2.getString("surname");
                    }

                    Statement st3 = connection.createStatement();
                    String query3 = String.format("insert into kiralananevler values('%d','%s','%s','%s','%s','%s')", Integer.parseInt(ilanNoTF.getText()), name, surname, telefonNumber,Kname,Ksurname);
                    st3.executeUpdate(query3);
                    Statement st4 = connection.createStatement();
                    String query4 = "DELETE FROM rentahouse WHERE id_rentahouse =?";
                    PreparedStatement preparedStatement3 = connection.prepareStatement(query4);
                    preparedStatement3.setString(1,ilanNoTF.getText());
                    preparedStatement3.execute();
                    JOptionPane.showMessageDialog(jFrame,"Kiralama başarılı bir şekilde gerçekleşti...\nTeşekkür ederiz!");
                    jFrame.dispose();
                }
                else if (result == JOptionPane.NO_OPTION){
                    JOptionPane.showMessageDialog(jFrame,"Ev kiralanamadı!");
                }



            }
            catch (Exception exception){
                System.out.println(exception);
            }
        }
    });


    goster.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {


            try {
                ilList.setEnabled(false);
                ilceList.setEnabled(false);
                mahalleTF.setEnabled(false);
                sokakTF.setEnabled(false);
                binaTF.setEnabled(false);
                katTF.setEnabled(false);
                noTF.setEnabled(false);
                toplamKatTF.setEnabled(false);
                binaYilTF.setEnabled(false);
                metrekareTF.setEnabled(false);
                esyaDurumuL.setEnabled(false);
                odaSayisiCB.setEnabled(false);
                evSahibiTelTF.setEnabled(false);
                fiyatTF.setEnabled(false);
                aciklamaTF.setEnabled(false);
                isitmaCB.setEnabled(false);




                Connection connection = null;
                connection = dbHelper.getConnection();
                String query ="SELECT * FROM rentahouse WHERE id_rentahouse = ?";
                Statement st = connection.createStatement();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,ilanNoTF.getText());
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()){
                    ilDb = rs.getString("il");
                    ilceDb = rs.getString("ilce");
                    mahalleDb = rs.getString("mahalle");
                    sokakDb = rs.getString("sokak");
                    binaDb = rs.getString("bina");
                    katDb = rs.getString("kat");
                    noDb = rs.getString("no");
                    toplamKatDb = rs.getString("toplamkat");
                    binaYilDb = rs.getString("binayil");
                    esyaDurumuDb = rs.getString("esyadurumu");
                    metrekareDb = rs.getString("metrekare");
                    odaSayisiDb = rs.getString("odasayisi");
                    isitmaDb = rs.getString("isitma");
                    evSahibiTelDb = rs.getString("evsahibitelefon");
                    fiyatDb = rs.getString("fiyat");
                    aciklamaDb = rs.getString("aciklama");
                }
                ilList.setSelectedItem(ilDb);
                ilceList.setSelectedItem(ilceDb);
                mahalleTF.setText(mahalleDb);
                sokakTF.setText(sokakDb);
                binaTF.setText(binaDb);
                katTF.setText(katDb);
                noTF.setText(noDb);
                toplamKatTF.setText(toplamKatDb);
                binaYilTF.setText(binaYilDb);
                esyaDurumuL.setText(esyaDurumuDb);
                metrekareTF.setText(metrekareDb);
                odaSayisiCB.setText(odaSayisiDb);
                isitmaCB.setText(isitmaDb);
                evSahibiTelTF.setText(evSahibiTelDb);
                fiyatTF.setText(fiyatDb);
                aciklamaTF.setText(aciklamaDb);



            } catch (SQLException ex) {
                ex.printStackTrace();
            }




        }
    });

    chooseComboBox.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            if(chooseComboBox.getSelectedIndex()==0){
                try{
                                                                                            System.out.println("Tümü");

                    try {
                        JFrame newJframe = new JFrame();
                        JTable table = new JTable();
                        Object[][] veri;
                        Connection connection = null;
                        connection = dbHelper.getConnection();
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery("SELECT id_rentahouse,il,ilce,mahalle,kat,metrekare,fiyat,evsahibitelefon FROM rentahouse");

                        String column[] = {"Id","İl","İlçe","Mahalle","Kat","Metrekare","Fiyat","Ev Sahibi Telefon"};
                        int sutunSayisi=rs.getMetaData().getColumnCount();
                        DefaultTableModel dtm = new DefaultTableModel();
                        for(int j=0;j<sutunSayisi;j++){
                            dtm.addColumn(column[j]);
                        }
                        while(rs.next()){
                            Object[]row = new Object[sutunSayisi];
                            for(int i=1; i<= sutunSayisi;i++){
                                row[i-1]=rs.getObject(i);
                            }
                            dtm.addRow(row);
                        }
                        table.setModel(dtm);
                        rs.close();
                        connection.close();
                        table.setBounds(1,10,950,950);
                        JScrollPane jsp = new JScrollPane(table);
                        newJframe.add(jsp);
                        newJframe.setSize(800,600);
                        newJframe.setVisible(true);

                    }
                    catch (Exception exception){
                        System.out.println(exception);
                    }



                }

                catch (Exception exception){
                    System.out.println(exception);
                }
            }
            else if (chooseComboBox.getSelectedIndex()==1) {
                                                                    System.out.println("Konuma Göre");
                try {
                    JFrame newJframe = new JFrame();
                    JTable table = new JTable();
                    Object[][] veri;
                    Connection connection = null;
                    connection = dbHelper.getConnection();
                    String query ="SELECT id_rentahouse,il,ilce,mahalle,kat,metrekare,fiyat,evsahibitelefon FROM rentahouse WHERE il=? && ilce =?";
                    Statement st = connection.createStatement();
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1,(String) ilList.getSelectedItem());
                    preparedStatement.setString(2,(String) ilceList.getSelectedItem());
                    ResultSet rs = preparedStatement.executeQuery();
                        String column[] = {"Id","İl","İlçe","Mahalle","Kat","Metrekare","Fiyat","Ev Sahibi Telefon"};
                    int sutunSayisi=rs.getMetaData().getColumnCount();
                    DefaultTableModel dtm = new DefaultTableModel();
                    for(int j=0;j<sutunSayisi;j++){
                        dtm.addColumn(column[j]);
                    }
                    while(rs.next()){
                        Object[]row = new Object[sutunSayisi];
                        for(int i=1; i<= sutunSayisi;i++){
                            row[i-1]=rs.getObject(i);
                        }
                        dtm.addRow(row);
                    }
                    table.setModel(dtm);
                    rs.close();
                    connection.close();
                    table.setBounds(1,10,950,950);
                    JScrollPane jsp = new JScrollPane(table);
                    newJframe.add(jsp);
                    newJframe.setSize(800,600);
                    newJframe.setVisible(true);

                }
                catch (Exception exception){
                    System.out.println(exception);
                }



            }
            else if (chooseComboBox.getSelectedIndex()==2){
                try {
                                                                System.out.println("Fiyata Göre");
                    JFrame newJframe = new JFrame();
                    JTable table = new JTable();
                    Object[][] veri;
                    Connection connection = null;
                    connection = dbHelper.getConnection();
                    String query ="SELECT id_rentahouse,il,ilce,mahalle,kat,metrekare,fiyat,evsahibitelefon FROM rentahouse WHERE fiyat<=?";
                    Statement st = connection.createStatement();
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1,(String) fiyatTF.getText());
                    ResultSet rs = preparedStatement.executeQuery();
                    String column[] = {"Id","İl","İlçe","Mahalle","Kat","Metrekare","Fiyat","Ev Sahibi Telefon"};
                    int sutunSayisi=rs.getMetaData().getColumnCount();
                    DefaultTableModel dtm = new DefaultTableModel();
                    for(int j=0;j<sutunSayisi;j++){
                        dtm.addColumn(column[j]);
                    }
                    while(rs.next()){
                        Object[]row = new Object[sutunSayisi];
                        for(int i=1; i<= sutunSayisi;i++){
                            row[i-1]=rs.getObject(i);
                        }
                        dtm.addRow(row);
                    }
                    table.setModel(dtm);
                    rs.close();
                    connection.close();
                    table.setBounds(1,10,950,950);
                    JScrollPane jsp = new JScrollPane(table);
                    newJframe.add(jsp);
                    newJframe.setSize(800,600);
                    newJframe.setVisible(true);

                }
                catch (Exception exception){
                    System.out.println(exception);
                }
            }

        }
    });

    jFrame.setVisible(true);
    jFrame.setLayout(null);
    jFrame.setSize(800, 800);
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
    jFrame.add(ilList);
    jFrame.add(ilceList);
    jFrame.add(mahalleTF);
    jFrame.add(sokakTF);
    jFrame.add(binaTF);
    jFrame.add(katTF);
    jFrame.add(noTF);
    jFrame.add(toplamKatTF);
    jFrame.add(binaYilTF);
    jFrame.add(esyaDurumuL);
    jFrame.add(metrekareTF);
    jFrame.add(odaSayisiCB);
    jFrame.add(evSahibiTelTF);
    jFrame.add(fiyatTF);
    jFrame.add(aciklamaTF);
    jFrame.add(isitmaCB);
    jFrame.add(chooseComboBox);
    jFrame.add(ilanNoTF);
    jFrame.add(ilanNo);
    jFrame.add(goster);
    jFrame.add(kiralık);


}
}
