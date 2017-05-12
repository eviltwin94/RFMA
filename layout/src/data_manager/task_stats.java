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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

/**
 *
 * @author gabriel
 */
public class task_stats {
    
    
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
    
    
    public boolean verifyTaskExistence(String robot_name, int task){
    
    String sql = "SELECT robot_name, task FROM TaskStats";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            

            
            // loop through the result set
            while (rs.next()) {
                
                
                
                String aux = rs.getString("robot_name");
                int aux2 = rs.getInt("task");
                
                if(aux.equals(robot_name)&&(aux2 == task)){
                    System.out.println("sucesso");
                    
                return(true);
                }
                
                
            }
            

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
        System.out.println("insucesso");
    return(false);
    
    }
    
    public void insert( String name, int task) {
        String sql = "INSERT INTO TaskStats(robot_name, task, id) VALUES(?,?,?)";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, name);
            pstmt.setInt(2, task);
            
            /*
            pstmt.setDouble(2,operation_time);
            pstmt.setDouble(3, total_distance);
            pstmt.setDouble(4, total_energy_consumption);
            pstmt.setInt(5, charge_number);
            pstmt.setInt(6, discharge_number);

*/
            //pstmt.setString(6, name);
           // pstmt.setInt(7, type);

                        



            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void update( String robot_name, double time, double distance, int task ) {
        String sql = "UPDATE TaskStats SET time = ?,  "
                 + "distance = ? "
                + "WHERE task = ? AND robot_name = ?";
                //+ "WHERE robot_name = ?";
                

try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
           
            pstmt.setDouble(1, time);
            pstmt.setDouble(2, distance);
            pstmt.setInt(3, task);

            pstmt.setString(4, robot_name);
            //pstmt.setInt(6, id_operation);
            //pstmt.setInt(8, type);
            
            
            
            
            
            
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
}
    
    public double fetch_task_time(String name, int task){

String sql = "SELECT time, robot_name, task FROM TaskStats";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                
                String aux = rs.getString("robot_name");
                int aux2 = rs.getInt("task");
                
                if(aux.equals(name)&&aux2==task){
                    
                    double temp = rs.getDouble("time");
                    System.out.println("teste de validação");
                    
                return(temp);
                }
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return(-1);
}
    
    public void timeHistogram( String robot_name, XYChart.Series set1 ){
    
    String sql = "SELECT time, robot_name, task FROM TaskStats";
            
            
            
    
        
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
               
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                
                String aux = rs.getString("robot_name");
                //XYChart.Series set1 = new XYChart.Series();
                
                if(aux.equals(robot_name)){
                    
                    double temp = rs.getDouble("time");
                    int aux2 = rs.getInt("task");
                    set1.getData().add(new XYChart.Data(aux, temp));
                }
                
                
                
                //h.getData().addAll(set1);
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
    };
    
    
    public static void main(String[] args) {
        
       
        task_stats app = new task_stats();
        
        app.update("exemplo", 1, 2, 99);
        double x = app.fetch_task_time("exemplo", 99);
        System.out.println(x);
    }
    
    
}
