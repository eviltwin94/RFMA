/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_manager;

import static java.lang.Math.sqrt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

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
    


public void update( double operation_time, double total_distance, double total_energy_consumption, double timestamp,  String name, int type, double x, double y) {
        String sql = "UPDATE Operation SET operation_time = ?,  "
                 + "total_distance = ?, "
                 + "total_energy_consumption = ?, "
                + "oldTimestamp = ?, "        
                + "x = ?, "
                + "y = ? "
                
                + "WHERE name = ?";
                

try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setDouble(1,operation_time);
            pstmt.setDouble(2, total_distance);
            pstmt.setDouble(3, total_energy_consumption);
            pstmt.setDouble(4, timestamp);
            //pstmt.setInt(5, discharges_number);
                        pstmt.setDouble(5,x);
            pstmt.setDouble(6,y);

            pstmt.setString(7, name);
            //pstmt.setInt(6, id_operation);
            //pstmt.setInt(8, type);
            
            
            
            
            
            
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
}
    


public double fetch_operation_time(String name){

String sql = "SELECT operation_time, name FROM Operation";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                
                String aux = rs.getString("name");
                
                if(aux.equals(name)){
                    
                    double temp = rs.getDouble("operation_time");
                    System.out.println("teste de validação");
                    
                return(temp);
                }
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return(-1);
}
  



public double fetch_consumed_power(String name){

String sql = "SELECT total_energy_consumption, name FROM Operation";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                
                String aux = rs.getString("name");
                
                if(aux.equals(name)){
                    
                    double temp = rs.getDouble("total_energy_consumption");
                    
                    
                return(temp);
                }
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return(-1);


}

public double consumed_power(double a, double b, double c, double v, double w){

double temp = a + b*(sqrt(v*v)) + c*(sqrt(w*w));

return(temp);
}


public double fetch_a(int type){

String sql = "SELECT a, robot_type_id FROM RobotType";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                
                int aux = rs.getInt("robot_type_id");
                
                if(aux == type){
                    
                    double temp = rs.getDouble("a");
                    
                    
                return(temp);
                }
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return(-1);
}

public double fetch_b(int type){

String sql = "SELECT b, robot_type_id FROM RobotType";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                
                int aux = rs.getInt("robot_type_id");
                
                if(aux == type){
                    
                    double temp = rs.getDouble("b");
                    
                    
                return(temp);
                }
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return(-1);
}

public double fetch_c(int type){

String sql = "SELECT c, robot_type_id FROM RobotType";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                
                int aux = rs.getInt("robot_type_id");
                
                if(aux == type){
                    
                    double temp = rs.getDouble("c");
                    
                    
                return(temp);
                }
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return(-1);
}

public double fetch_d(int type){

String sql = "SELECT d, robot_type_id FROM RobotType";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                
                int aux = rs.getInt("robot_type_id");
                
                if(aux == type){
                    
                    double temp = rs.getDouble("d");
                    
                    
                return(temp);
                }
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return(-1);
}

public double distance(double x1, double x2, double y1, double y2){

    double x = x2-x1;
    double y = y2 - y1;
    
    double d = sqrt(x*x + y*y);
    
    return(d);

}

public double fetch_x(String name){

String sql = "SELECT x, name FROM Operation";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                
                String aux = rs.getString("name");
                
                if(aux.equals(name)){
                    
                    double temp = rs.getDouble("x");
                    
                    
                return(temp);
                }
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return(-1);

}

public double fetch_y(String name){

String sql = "SELECT y, name FROM Operation";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                
                String aux = rs.getString("name");
                
                if(aux.equals(name)){
                    
                    double temp = rs.getDouble("y");
                    
                    
                return(temp);
                }
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return(-1);

}

public void update_charge(String name, double charge) {
        String sql = "UPDATE Robot SET charge = ?  "
               
                
                
                + "WHERE robot_name = ?";
                

try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setDouble(1, charge);
            pstmt.setString(2, name);
            
            
            
            
            
            
            
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
}


public double calc_charge(double charge, double capacity, double p, double t){

    //potência x tempo em horas * conversão para percentagem
double aux = p * (t/3600)*(100/capacity);

aux = charge - aux;

return(aux);

}

public double fetch_charge(String name){


String sql = "SELECT charge, robot_name FROM Robot";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                
                String aux = rs.getString("robot_name");
                
                if(aux.equals(name)){
                    
                    double temp = rs.getDouble("charge");
                    
                    
                return(temp);
                }
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return(-1);


}

public double fetch_capacity(String name){
String sql = "SELECT capacity, robot_name FROM Robot";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                
                String aux = rs.getString("robot_name");
                
                if(aux.equals(name)){
                    
                    double temp = rs.getDouble("capacity");
                    
                    
                return(temp);
                }
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return(-1);


}

public double fetch_oldTimestamp(String name){
    
    
String sql = "SELECT oldTimestamp, name FROM Operation";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                
                String aux = rs.getString("name");
                
                if(aux.equals(name)){
                    
                    double temp = rs.getDouble("oldTimestamp");
                    
                    
                return(temp);
                }
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return(-1);

}

public void update_xy(String name, double x, double y) {
        String sql = "UPDATE Operation SET x = ?,  "
               + "y = ? "
                
                
                + "WHERE name = ?";
                

try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setDouble(1, x);
            pstmt.setDouble(2, y);
            pstmt.setString(3, name);
            
            
            
            
            
            
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
}





public static void main(String[] args) {
    update_operation_data app = new update_operation_data();

    
    
    double x = app.distance(0,0,0,0);
    
 System.out.println(x);

    
    }



}
