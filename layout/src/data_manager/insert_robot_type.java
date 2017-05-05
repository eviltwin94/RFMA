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
public class insert_robot_type {
    
    
      /**
     * Connect to the test.db database
     *
     * @return the Connection object
     */
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
 
    
    public void insert(String robot_type_name, double payload, String locomotion_type, String manufactor_name, double a, double b, double c, double d) {
        String sql = "INSERT INTO RobotType(robot_type_name, payload, locomotion_type, manufactor_name, a, b, c, d, robot_type_id) VALUES(?,?,?,?,?,?,?,?,?)";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1,robot_type_name);
            pstmt.setDouble(2, payload);
            pstmt.setString(3, locomotion_type);
            pstmt.setString(4, manufactor_name);
            pstmt.setDouble(5, a);
            pstmt.setDouble(6, b);
            pstmt.setDouble(7, c);
            pstmt.setDouble(8, d);

                                                            

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
