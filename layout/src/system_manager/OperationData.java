/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system_manager;

/**
 *
 * @author gabriel
 */
public class OperationData {
    
    public double operationTime;
    public double timeConsumption;
    public double totalDistance;
    public double totalEnergyConsumption;
    public int chargeNumber;
    public int dischargeNumber;
    public String name;
    public int type;
    
    
    
    
    
    
    public void update(OperationData x, int timestamp){
    
    operationTime = operationTime + timestamp ;
    
    
    }
    
    
}
