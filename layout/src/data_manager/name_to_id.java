/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_manager;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.ChoiceBox;

/**
 *
 * @author gabriel
 */
public class name_to_id {
    
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
     
     
                
          public int selectAll(String name, int aux){
        String sql = "SELECT robot_type_name, robot_type_id FROM RobotType";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            

            
            // loop through the result set
            while (rs.next()) {
                
                /*
                System.out.println(rs.getInt("robot_type_id"));
                */
                if(rs.getString("robot_type_name").equals(name)){
                
                 aux = rs.getInt("robot_type_id");
                 
                 
                
                }
                
               
                /*
               robotTypeList.add(rs.getInt(robot_type_id)) ;
                */
                
                
               
            }
            

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
         return (aux);
    
}
            
}
