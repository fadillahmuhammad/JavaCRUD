/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mahasiswa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author fadil
 */
public class model_mahasiswa {
    String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    String dbUrl = "jdbc:mysql://localhost/mahasiswa_db";
    String user = "root";
    String password = "";
    
    Connection con;
    Statement st;
    ResultSet rs;
    PreparedStatement pst;
    
    boolean respons;
    
    public model_mahasiswa(){
        try {
            Class.forName(jdbcDriver);
            System.out.println("driver load.");
        } catch (ClassNotFoundException ex) {
            System.out.println("driver tidak ditemukan");
            Logger.getLogger(model_mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            con = DriverManager.getConnection(dbUrl, user, password);
            System.out.println("berhasil terkoneksi ke database");
        } catch (SQLException ex) {
            System.out.println("gagal terkoneksi ke database, periksa config mysql");
            Logger.getLogger(model_mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean insertMhs(String nim, String nama, double uts, double uas){
        String query = "insert into tbl_mahasiswa (nim, nama_mahasiswa, n_uts, n_uas) values (?,?,?,?)";
        try {
            pst = con.prepareStatement(query);
            pst.setString(1, nim);
            pst.setString(2, nama);
            pst.setString(3, Double.toString(uts));
            pst.setString(4, Double.toString(uas));
            pst.executeUpdate();
            respons = true;
            System.out.println("sukses insert");
        } catch (SQLException ex) {
            respons = false;
            System.out.println("gagal insert");
            Logger.getLogger(model_mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return respons;
        
    }
    
    public ResultSet getAllMhs(){
        String query = "select * from tbl_mahasiswa";
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(model_mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public boolean updateMhs(String nim, String nama, double uts, double uas){
        String query = "update tbl_mahasiswa set nama_mahasiswa = ?, n_uts = ?, n_uas = ? where NIM = ?";
        try {
            pst = con.prepareStatement(query);
            pst.setString(1, nama);
            pst.setString(2, Double.toString(uts));
            pst.setString(3, Double.toString(uas));
            pst.setString(4, nim);
            pst.executeUpdate();
            respons = true;
            System.out.println("sukses update data");
        } catch (SQLException ex) {
            respons = false;
            System.out.println("gagal update data");
            Logger.getLogger(model_mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respons;   
    }
    
    public void hapusMhs(String nim){
        String query = "delete from tbl_mahasiswa where NIM = ? ";
        try {
            pst = con.prepareStatement(query);
            pst.setString(1, nim);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(model_mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
