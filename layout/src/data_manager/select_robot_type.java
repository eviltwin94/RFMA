/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_manager;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.ChoiceBox;

/**
 *
 * @author gabriel
 */
public class select_robot_type {

    public ArrayList<Integer> robotTypeList;
    /**
     * Connect to the test.db database
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
     * select all rows in the warehouses table
     * @return 
     */
    public void selectAll(ChoiceBox teste){
        String sql = "SELECT robot_type_id FROM RobotType";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            

            
            // loop through the result set
            while (rs.next()) {
                
                /*
                System.out.println(rs.getInt("robot_type_id"));
                */
                
                int aux = rs.getInt("robot_type_id");
                
                teste.getItems().add(aux);
                /*
               robotTypeList.add(rs.getInt(robot_type_id)) ;
                */
            }
            

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
    
}

    
    
    /*
     public static void main(String[] args) {
        select_robot_type app = new select_robot_type();
        ArrayList<Integer> teste = app.selectAll();
        
                
    }
*/
    }

 
