/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Koneksi;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ACER
 */
public class kon {
    static Connection conn;
    public static  Connection conn(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_perpustakaan", "root", "");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
     public static void main (String args[]){
        System.out.println(kon.conn());
    }
    
}
