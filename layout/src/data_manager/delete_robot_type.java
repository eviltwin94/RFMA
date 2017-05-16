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
import javafx.scene.control.Alert;

/**
 *
 * @author gabriel
 */
public class delete_robot_type {
    
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
    
    
    /**
     * Delete a warehouse specified by the id
     *
     * @param id
     */
    public void delete(String robot_type_name) {
        String sql = "DELETE FROM RobotType WHERE robot_type_name = ?";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setString(1, robot_type_name);
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 
   public boolean verify_Robot_Activity(int tipo) {


String sql = "SELECT type FROM Operation";
OperationData app = new OperationData();        

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                
                    
                    int temp = rs.getInt("type");
                    
                    
                    
                    if(temp == tipo){
                        
                       return(true);
                        }
                
                
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

      return(false);


}
   
   public int nametoid(String name){
        String sql = "SELECT robot_type_name, robot_type_id FROM RobotType";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            

            int temp =0;
            // loop through the result set
            while (rs.next()) {
                
                /*
                System.out.println(rs.getInt("robot_type_id"));
                */
                if(rs.getString("robot_type_name").equals(name)){
                
                 temp = rs.getInt("robot_type_id");
                 
                 return(temp);
                
                }
                
               
                /*
               robotTypeList.add(rs.getInt(robot_type_id)) ;
                */
                
                
               
            }
            

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
         return (-1);
    
}
   
   
   
}

