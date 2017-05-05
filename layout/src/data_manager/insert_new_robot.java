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
import java.util.Date;

/**
 *
 * @author gabriel
 */
public class insert_new_robot {
    
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
    
    public void insert(String robot_name, int robot_type, double charge, double capacity, int birthday, String robot_description) {
        String sql = "INSERT INTO Robot(robot_name, robot_type, charge, capacity, birthday,  robot_description) VALUES(?,?,?,?,?,?)";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1,robot_name);
            pstmt.setInt(2,robot_type);
            pstmt.setDouble(3, charge);
            pstmt.setDouble(4, capacity);
            pstmt.setDouble(5, birthday);
            pstmt.setString(6,robot_description);

            

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    
    
    
}
