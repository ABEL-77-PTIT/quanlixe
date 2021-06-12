/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.monhoc;

/**
 *
 * @author Than
 */
public class DAO {
    private Connection conn;
    
    public DAO(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=thaiphong;"
                    + "username=sa;password=292100an");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean addStudent(monhoc s){
        
        String sql = "INSERT INTO QLSV(ID,name,tc)"
                +"VALUES(?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getID());
            ps.setString(2, s.getName());
            ps.setFloat(3, s.getTc());
            
            return ps.executeUpdate() > 0;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
    public boolean removeStudent() throws SQLException{
        
        String sql = "DELETE FROM  QLSV WHERE id=1";
         try {
              PreparedStatement ps = conn.prepareStatement(sql);
              return ps.executeUpdate()>0;
         } catch (Exception e) {
             e.printStackTrace();
         }
       
        
           
        
        
        return false;
    }
    
    public ArrayList<monhoc> getListStudent(){
        ArrayList<monhoc> list = new ArrayList<>();
        String sql = "SELECT * FROM tblStudent";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                monhoc s = new monhoc();
                s.setID(rs.getString("ID"));
                s.setName(rs.getString("name"));
                
                s.setTc(rs.getFloat("tc"));
                
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    public static void main(String[] args) {
        new DAO();
    }
}
