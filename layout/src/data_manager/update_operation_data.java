/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author gabriel
 */
public class update_operation_data {
    
    
    
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
    


public void update( double operation_time, double total_distance, double total_energy_consumption, int charges_number, int discharges_number, String name, int type) {
        String sql = "UPDATE Operation SET operation_time = ?,  "
                 + "total_distance = ?, "
                 + "total_energy_consumption = ?, "
                + "charges_number = ?, "
                + "discharges_number = ? "
                
                + "WHERE name = ?";
                

try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setDouble(1,operation_time);
            pstmt.setDouble(2, total_distance);
            pstmt.setDouble(3, total_energy_consumption);
            pstmt.setInt(4, charges_number);
            pstmt.setInt(5, discharges_number);
            pstmt.setString(6, name);
            //pstmt.setInt(6, id_operation);
            //pstmt.setInt(8, type);
            
            
            
            
            
            
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
}
    
 public static void main(String[] args) {
        
        update_operation_data app = new update_operation_data();
        // update the warehouse with id 3
        app.update( 1, 1,1,1,1, "Pioneer_P3DX_nr4", 3);
    }


    
}
