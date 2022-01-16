package com.company;

import com.mysql.cj.protocol.Resultset;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import javax.swing.table.DefaultTableModel;
import javax.xml.transform.Result;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DbAdd extends RentAHouse{
    public DbAdd(String tckimlik, String userName) throws SQLException {
        super(tckimlik, userName);
    }
    String mahalleDb,sokakDb,binaDb,katDb,noDb,toplamKatDb,binaYilDb,esyaDurumuDb,metrekareDb,odaSayisiDb,isitmaDb,evSahibiTelDb,fiyatDb,aciklamaDb;
    DbHelper dbHelper = new DbHelper();


    int count=0;

    public void konumaGoreFiltre(String il, String ilce) throws SQLException {
        Connection connection = null;
        connection = dbHelper.getConnection();
        Statement st = connection.createStatement();
        String query = "SELECT * FROM rentahouse WHERE il = ? && ilce = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,il);
        preparedStatement.setString(2,ilce);
        ResultSet rs = preparedStatement.executeQuery();
        String[] dizi =new String[50];
        int i=0;
            while (rs.next()) {

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
                dizi[i]=mahalleDb+sokakDb+binaDb;
                i++;
            }

            for (int j=0 ;j< dizi.length;j++){
                System.out.println(dizi[j]);
            }


    }

    public void fiyataGore(String fiyat) throws SQLException {
        Connection connection = null;
        connection = dbHelper.getConnection();
        Statement st = connection.createStatement();
        String query = "SELECT * FROM rentahouse WHERE fiyat<? ";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,fiyat);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
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
    }


}
