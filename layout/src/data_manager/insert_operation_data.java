/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author gabriel
 */
public class insert_operation_data {
    
    
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:DB_RFMA/RFMA_DB.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return conn;
    }
 
    
    public void insert( String name, int type) {
        String sql = "INSERT INTO Operation(operation_time, total_distance, total_energy_consumption, charges_number, discharges_number,  name, type) VALUES(?,?,?,?,?,?,?)";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            /*
            pstmt.setDouble(2,operation_time);
            pstmt.setDouble(3, total_distance);
            pstmt.setDouble(4, total_energy_consumption);
            pstmt.setInt(5, charge_number);
            pstmt.setInt(6, discharge_number);

*/
            pstmt.setString(6, name);
            pstmt.setInt(7, type);

                        



            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 
    
    public boolean verify_existence(String name){
    
    String sql = "SELECT name FROM Operation";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            

            
            // loop through the result set
            while (rs.next()) {
                
                
                
                String aux = rs.getString("name");
                
                if(aux.equals(name)){
                    
                    
                return(true);
                }
                
                
            }
            

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
        
    return(false);
    
    }
    
    
    
    
    
    
    
}
