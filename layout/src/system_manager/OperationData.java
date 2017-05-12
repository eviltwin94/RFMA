/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system_manager;

import data_manager.update_operation_data;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabriel
 */
public class OperationData {
    
    /*
    public double operationTime;
    public double timeConsumption;
    public double totalDistance;
    public double totalEnergyConsumption;
    public int chargeNumber;
    public int dischargeNumber;
    public String name;
    public int type;
    
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
    
    public void exportCSV(String name) throws IOException {
    
        update_operation_data fetch = new update_operation_data();
        
        
        double operationTime = fetch.fetch_operation_time(name);
        double consumedPower = fetch.fetch_consumed_power(name);
        double totalDistance = fetch.fetch_total_distance(name);
        int chargeNumber = fetch.fetch_charge_number(name);
        int dischargesNumber = fetch.fetch_discharges_number(name);
        
        name = name + ".csv";
        
        FileWriter writer = new FileWriter(name);

writer.append("Operation Time");
writer.append(',');
writer.append("Total Power Consumption");
writer.append(',');
writer.append("Total Distance");
writer.append(',');
writer.append("Charges Number");
writer.append(',');
writer.append("Discharges Number");


writer.append('\n');

writer.append(Double.toString(operationTime));
writer.append(',');
writer.append(Double.toString(consumedPower));
writer.append(',');
writer.append(Double.toString(totalDistance));
writer.append(',');
writer.append(Integer.toString(chargeNumber));
writer.append(',');
writer.append(Integer.toString(dischargesNumber));


writer.flush();
writer.close();
        
        
        
        
    }
    
    /*
    public void exportData(Connection conn,String filename) {
        Statement stmt;
        String query;
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
             
            //For comma separated file
            query = "SELECT task_id, task_name, task_description into OUTFILE  '"+filename+
                    "' FIELDS TERMINATED BY ',' FROM Task";
            stmt.executeQuery(query);
             
        } catch(SQLException e) {
            stmt = null;
        }
    }
    
    
    */
    
    
    public static void main(String[] args) throws IOException {
    
        OperationData app = new OperationData();
        Connection conn = app.connect();
        
         app.exportCSV("teste");
    }
    
}
    

