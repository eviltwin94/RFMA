/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system_manager;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author gabriel
 */
public class RobotView {

    public SimpleStringProperty robotname;
    SimpleIntegerProperty task;
    SimpleDoubleProperty runningtime;
    SimpleDoubleProperty distance;
    SimpleDoubleProperty linearvelocity;
    SimpleDoubleProperty angularvelocity;
    SimpleDoubleProperty consumption;
    SimpleDoubleProperty charge;
    SimpleDoubleProperty remainingtime;

    public RobotView(String name, int task, double runningtime, double distance, double linearvelocity, double angularvelocity, double consumption, double charge, double remainingtime) {

        this.robotname = new SimpleStringProperty(name);
        this.task = new SimpleIntegerProperty(task);
        this.runningtime = new SimpleDoubleProperty(runningtime);
        

        this.distance = new SimpleDoubleProperty(distance);

        this.linearvelocity = new SimpleDoubleProperty(linearvelocity);

        this.angularvelocity = new SimpleDoubleProperty(angularvelocity);
        this.consumption = new SimpleDoubleProperty(consumption);
        this.charge = new SimpleDoubleProperty(charge);
        this.remainingtime = new SimpleDoubleProperty(remainingtime);

    }

    public SimpleStringProperty getrobotname(){
    return robotname;
    }
    
    public SimpleIntegerProperty gettask(){
    return task;
    }
    
    public SimpleDoubleProperty getrunningtime(){
    return runningtime;
    }
    
    public SimpleDoubleProperty getdistance(){
    return distance;
    }
    
    public SimpleDoubleProperty getlinearvelocity(){
    return linearvelocity;
    }
    
    public SimpleDoubleProperty getangularvelocity(){
    return angularvelocity;
    }
    
    public SimpleDoubleProperty getconsumption(){
    return consumption;
    }
    
    public SimpleDoubleProperty getcharge(){
    return charge;
    }
    
    public SimpleDoubleProperty getremainingtime(){
    return remainingtime;
    }
    
    
    
}
