/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_manager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author gabriel
 */
public class Robot {
    
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
    
    public void verify_Robot_Charge() {


String sql = "SELECT charge, robot_name FROM Robot";
OperationData app = new OperationData();        

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                
                    String aux = rs.getString("robot_name");
                    double temp = rs.getDouble("charge");
                    System.out.println(temp);
                    System.out.println("estou a funcionar dentro do resultset");
                    if(temp<10){
                        
                       
                        
        Alert alert = new Alert(AlertType.WARNING);
alert.setTitle("Warning Dialog");
alert.setHeaderText("You have a robot with less than 10% charge");
alert.setContentText("Robot " + aux + " is almost out of charge!");

alert.showAndWait();

                        
                        
                       
                    
                    }
                
                
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

      


}

    
    }
    

