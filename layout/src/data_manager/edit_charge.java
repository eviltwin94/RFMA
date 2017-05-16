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
public class edit_charge {
    
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
    


public void update(String robot_name, double charge) {
        String sql = "UPDATE Robot SET charge = ?  "
                 
                + "WHERE robot_name = ?";

try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setDouble(1, charge);
            
            pstmt.setString(2, robot_name);
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
}

public int fetch_charge(String name){

String sql = "SELECT charge, robot_name FROM Robot";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                
                String aux = rs.getString("robot_name");
                
                if(aux.equals(name)){
                    
                    int temp = rs.getInt("charge");
                    
                    
                return(temp);
                }
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return(-1);
}



}