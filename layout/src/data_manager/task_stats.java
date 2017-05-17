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
                    
                    String aux3 = idtoname(aux2);
                    
                    //idtoname
                    set1.getData().add(new XYChart.Data(aux3, temp/3600.0));
                }
                
                
                
                //h.getData().addAll(set1);
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
    };
    
    
    public void update_initial_conditions( String robot_name, double t0, double d0, int task, double consume ) {
        String sql = "UPDATE TaskStats SET t0 = ?,  "
                 + "d0 = ?, "
                + "consume = ? "
                + "WHERE task = ? AND robot_name = ?";
                //+ "WHERE robot_name = ?";
                

try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
           
            pstmt.setDouble(1, t0);
            pstmt.setDouble(2, d0);
            pstmt.setDouble(3, consume);
            
            pstmt.setInt(4, task);

            pstmt.setString(5, robot_name);
            //pstmt.setInt(6, id_operation);
            //pstmt.setInt(8, type);
            
            
            
            
            
            
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
}
    
    public int fetch_previous_task(String name, int task){

String sql = "SELECT time, robot_name, task, previous_task FROM TaskStats";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                
                String aux = rs.getString("robot_name");
                int aux2 = rs.getInt("task");
                
                if(aux.equals(name)&&aux2==task){
                    
                    int temp = rs.getInt("previous_task");
                    
                    
                return(temp);
                }
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return(-1);
}
    
    public double fetch_t0(String name, int task){

String sql = "SELECT t0, robot_name, task FROM TaskStats";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                
                String aux = rs.getString("robot_name");
                int aux2 = rs.getInt("task");
                
                if(aux.equals(name)&&aux2==task){
                    
                    double temp = rs.getDouble("t0");
                    System.out.println("teste de validação");
                    
                return(temp);
                }
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return(-1);
}
    
    
    public double fetch_d0(String name, int task){

String sql = "SELECT d0, robot_name, task FROM TaskStats";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                
                String aux = rs.getString("robot_name");
                int aux2 = rs.getInt("task");
                
                if(aux.equals(name)&&aux2==task){
                    
                    double temp = rs.getDouble("d0");
                    System.out.println("teste de validação");
                    
                return(temp);
                }
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return(-1);
}
    
    
    
    
    
    
    
    public String idtoname(int id){
        String sql = "SELECT task_id, task_name FROM Task";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            

            
            // loop through the result set
            while (rs.next()) {
                
                /*
                System.out.println(rs.getInt("robot_type_id"));
                */
                if(rs.getInt("task_id")==id){
                
                 String temp = rs.getString("task_name");
                 
                 return(temp);
                
                }
                
               
                /*
               robotTypeList.add(rs.getInt(robot_type_id)) ;
                */
                
                
               
            }
            

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
         return ("error");
    
}
    
    
    public int fetchTaskCounter(String name, int task){

String sql = "SELECT task_counter, robot_name, task FROM TaskStats";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                
                String aux = rs.getString("robot_name");
                
                int aux2 = rs.getInt("task");
                
                if(aux.equals(name)&&aux2==task){
                    int temp = rs.getInt("task_counter");
                    
                   
                return(temp);
                }
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return(-1);

}
    
    
    public void updateTaskCounter( String robot_name, int tcounter, int task) {
        String sql = "UPDATE TaskStats SET task_counter = ?  "
                 
                + "WHERE task = ? AND robot_name = ?";
                
                

try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
           
            
            pstmt.setInt(1, tcounter);
            pstmt.setInt(2, task);

            pstmt.setString(3, robot_name);
            //pstmt.setInt(6, id_operation);
            //pstmt.setInt(8, type);
            
            
            
            
            
            
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
}
    
    public void updatePreviousTask( String robot_name, int task, int t ) {
        String sql = "UPDATE TaskStats SET previous_task = ?  "
                 
                + "WHERE task = ? AND robot_name = ?";
                //+ "WHERE robot_name = ?";
                

try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
           
            pstmt.setInt(1, t);
           
            pstmt.setInt(2, task);

            pstmt.setString(3, robot_name);
            //pstmt.setInt(6, id_operation);
            //pstmt.setInt(8, type);
            
            
            
            
            
            
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
}
    
    
    public void taskCounterHistogram( String robot_name, XYChart.Series set1 ){
    
    String sql = "SELECT task_counter, robot_name, task FROM TaskStats";
            
            
            
    
        
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
               
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                
                String aux = rs.getString("robot_name");
                //XYChart.Series set1 = new XYChart.Series();
                
                if(aux.equals(robot_name)){
                    
                    double temp = rs.getDouble("task_counter");
                    int aux2 = rs.getInt("task");
                    String aux3 = idtoname(aux2);
                    
                    
                    
                    
                    
                    //idtoname
                    set1.getData().add(new XYChart.Data(aux3, temp));
                }
                
                
                
                //h.getData().addAll(set1);
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
    };
    
    
    
    public void distanceHistogram( String robot_name, XYChart.Series set1 ){
    
    String sql = "SELECT distance, robot_name, task FROM TaskStats";
            
            
            
    
        
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
               
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                
                String aux = rs.getString("robot_name");
                //XYChart.Series set1 = new XYChart.Series();
                
                if(aux.equals(robot_name)){
                    
                    double temp = rs.getDouble("distance");
                    int aux2 = rs.getInt("task");
                    String aux3 = idtoname(aux2);
                    
                    
                    
                    
                    
                    //idtoname
                    set1.getData().add(new XYChart.Data(aux3, temp));
                }
                
                
                
                //h.getData().addAll(set1);
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
    };
    
    
    public void updateTaskConsume( String robot_name, double consume, int task) {
        String sql = "UPDATE TaskStats SET consume = ?  "
                 
                + "WHERE task = ? AND robot_name = ?";
                
                

try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
           
            
            pstmt.setDouble(1, consume);
            pstmt.setInt(2, task);

            pstmt.setString(3, robot_name);
            //pstmt.setInt(6, id_operation);
            //pstmt.setInt(8, type);
            
            
            
            
            
            
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
}
    
    public void updateTaskTime( String robot_name, double time, int task) {
        String sql = "UPDATE TaskStats SET time = ?  "
                 
                + "WHERE task = ? AND robot_name = ?";
                
                

try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
           
            
            pstmt.setDouble(1, time);
            pstmt.setInt(2, task);

            pstmt.setString(3, robot_name);
            //pstmt.setInt(6, id_operation);
            //pstmt.setInt(8, type);
            
            
            
            
            
            
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
}
    
     public double fetchTaskconsume(String name, int task){

String sql = "SELECT task, robot_name, consume FROM TaskStats";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                
                String aux = rs.getString("robot_name");
                
                int aux2 = rs.getInt("task");
                
                if(aux.equals(name)&&aux2==task){
                    
                    double temp = rs.getDouble("consume");
                    
                    
                return(temp);
                }
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
System.out.println("asneira aqui");
        return(-1);

}
    
    public double fetchTaskDistance(String name, int task){

String sql = "SELECT task, robot_name, distance FROM TaskStats";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                
                String aux = rs.getString("robot_name");
                
                int aux2 = rs.getInt("task");
                
                if(aux.equals(name)&&aux2==task){
                    
                    double temp = rs.getDouble("distance");
                    
                    
                return(temp);
                }
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return(-1);

}
     
    public void updateTaskDistance( String robot_name, double distance, int task) {
        String sql = "UPDATE TaskStats SET distance = ?  "
                 
                + "WHERE task = ? AND robot_name = ?";
                
                

try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
           
            
            pstmt.setDouble(1, distance);
            pstmt.setInt(2, task);

            pstmt.setString(3, robot_name);
            //pstmt.setInt(6, id_operation);
            //pstmt.setInt(8, type);
            
            
            
            
            
            
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
} 
    
    
    public void update_t0( String robot_name, double t, int task) {
        String sql = "UPDATE TaskStats SET t0 = ?  "
                 
                + "WHERE task = ? AND robot_name = ?";
                
                

try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
           
            
            pstmt.setDouble(1, t);
            pstmt.setInt(2, task);

            pstmt.setString(3, robot_name);
            //pstmt.setInt(6, id_operation);
            //pstmt.setInt(8, type);
            
            
            
            
            
            
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
} 
    
public void setZeroMoment( String robot_name) {
        String sql = "UPDATE TaskStats SET t0 = ?,  "
                 + "d0 = ?, "
                + "time = ?, "
                + "distance = ?, "
                + "consume = ? "
                + "WHERE  robot_name = ?";
                
                

try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
           
            
            pstmt.setDouble(1, 0);
            pstmt.setDouble(2, 0);
            pstmt.setDouble(3, 0);
            pstmt.setDouble(4, 0);
            pstmt.setDouble(5, 0);

            pstmt.setString(6, robot_name);
            //pstmt.setInt(6, id_operation);
            //pstmt.setInt(8, type);
            
            
            
            
            
            
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
} 
    
    
}
