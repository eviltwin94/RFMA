package server;

import data_manager.Robot;
import data_manager.insert_operation_data;
import data_manager.task_stats;
import data_manager.OperationData;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Array;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.util.Callback;
import static layout.FXMLDocumentController.root;
import data_manager.OperationData;
import system_manager.RobotView;





public class RequestHandler implements Runnable {

    @FXML
    private TreeTableView<RobotView> table;
    @FXML
    private TreeTableColumn<RobotView, String> col1;
    @FXML
    private TreeTableColumn<RobotView, Number> col2;
    @FXML
    private TreeTableColumn<RobotView, Number> col3;
    @FXML
    private TreeTableColumn<RobotView, Number> col4;
    @FXML
    private TreeTableColumn<RobotView, Number> col5;
    @FXML
    private TreeTableColumn<RobotView, Number> col6;
    @FXML
    private TreeTableColumn<RobotView, Number> col7;
    @FXML
    private TreeTableColumn<RobotView, Number> col8;
    @FXML
    private TreeTableColumn<RobotView, Number> col9;
  
    
    
    
    private final Socket client;
    ServerSocket serverSocket = null;
    public String nome = "vazio";
    public int tipo = 0;

    public RequestHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        //,"US-ASCII"
        try {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream(), "US-ASCII"));
	System.out.println("Thread started with name:" + Thread.currentThread().getName());
	
        String robotInput = "";
        int ch = 'a';
        String retval[] = new String[4];
        
        while(true) {
            StringBuilder sb = new StringBuilder();
            
            while ((ch = in.read()) != (char)0) {
                if (ch == -1) break;
                sb.append((char)ch);
            }
            
            robotInput = sb.toString();
            System.out.println(robotInput);
            
            int msgType = Character.getNumericValue(robotInput.charAt(0));
            
                
                
                
                
                if (msgType == 1) { // 1:<timestamp>;<robot_type>;<robot_name>

                    retval = (robotInput.substring(2)).split(";");
                    int timestamp = Integer.parseInt(retval[0]);
                    int robotType = Integer.parseInt(retval[1]);
                    String robotName = retval[2];
                    System.out.println("From " + Thread.currentThread().getName() + ": msgType=" + msgType);
                    System.out.println("                                   timestamp = " + timestamp);
                    System.out.println("                                   robotType = " + robotType);
                    System.out.println("                                   robotName = " + robotName);

                   
                    nome = getName(robotName);
                    tipo = getRobotType(robotType);

                    insert_operation_data app = new insert_operation_data();
//OperationData set = new OperationData();

                    //set.update_xy(nome, 0,0);
                    if (!(app.verify_existence(nome))) {

                        app.insert(robotName, robotType);

                    }
                    task_stats stats = new task_stats();
                    OperationData op = new OperationData();
                    //When connecting, set to zero all the initial conditions
                    stats.setZeroMoment(nome);
                    op.update_xy(nome, 0, 0);
                    

                } else if (msgType == 2) {  // 2:<timestamp>;<task_type>;<x>,<y>,<theta>;<v>,<w>
                    retval = (robotInput.substring(2)).split(";");
                    double timestamp = Double.parseDouble(retval[0]);
                    int taskType = Integer.parseInt(retval[1]);
                    String robotPos = retval[2];
                    String robotVel = retval[3];
                    retval = robotPos.split(",");
                    double x = Double.parseDouble(retval[0]);
                    //x = sqrt(x*x);
                    double y = Double.parseDouble(retval[1]);
                    //y = sqrt(y*y);
                    double theta = Double.parseDouble(retval[2]);
                    retval = robotVel.split(",");
                    double v = Double.parseDouble(retval[0]);
                    double w = Double.parseDouble(retval[1]);
                    System.out.println("From " + Thread.currentThread().getName() + ": msgType=" + msgType);
                    System.out.println("                                   timestamp = " + timestamp);
                    System.out.println("                                   robotType = " + taskType);
                    System.out.println("                                   robotPos = " + robotPos);
                    System.out.println("                                   robotVel = " + robotVel);
                    System.out.println("                                   x = " + x + ", y = " + y + ", theta = " + theta);
                    System.out.println("                                   v = " + v + ", w = " + w);
                    System.out.println(nome);
                    
                   
        
        //class iniciation
                    task_stats stats = new task_stats();
                    OperationData op = new OperationData();
                    //verify if there is an entry in database with actual robot and task. If it doesn't, it creates one
                    if(!stats.verifyTaskExistence(nome, taskType)){
                    
                    stats.insert(nome, taskType);
                    }
//
                    int previoustask = stats.fetch_previous_task(nome, taskType);
                    
                    double oldtimestamp = op.fetch_oldTimestamp(nome);
                    
                    System.out.println(oldtimestamp);
                    if(timestamp<oldtimestamp){
                    
                    int taskcounter = stats.fetchTaskCounter(nome, taskType);
                    System.out.println( taskcounter);
                    taskcounter++;
                                        
System.out.println( taskcounter);
                    stats.updateTaskCounter(nome, taskcounter, taskType);
                    
                    }
                    
                    op.updateOldTimestamp(nome, timestamp);
                    
                    //If it changes task, set everything to zero 
                    if(!(taskType == previoustask)){
                    stats.setZeroMoment(nome);
                    int taskcounter = stats.fetchTaskCounter(nome, taskType);
                    taskcounter++;
                    stats.updateTaskCounter(nome, taskcounter, taskType);
                    
                    }
                    stats.updatePreviousTask(nome, taskType, taskType);
                    
                    //Time in seconds
                    double RunningTime = timestamp/1000.0;
                    
                    double storedtime = stats.fetch_task_time(nome, taskType);
                    storedtime =storedtime + RunningTime;
                    stats.updateTaskTime(nome, storedtime, taskType);
                    double totaltime = op.fetch_operation_time(nome);
                    totaltime = totaltime + RunningTime;
                    op.updateTotalTime(nome, totaltime);
                    
                    
                    //distances in meters
                    double x0 = op.fetch_x(nome);
                    
                    double y0 = op.fetch_y(nome);
                    
                    double distance = op.distance(x0, x, y0, y);
                    
                     double taskdistance = stats.fetchTaskDistance(nome, taskType);
                    taskdistance = taskdistance + distance;
                    //System.out.println("taskdistance: " + taskdistance);
                    stats.updateTaskDistance(nome, taskdistance, taskType);
                    double totaldistance = op.fetch_total_distance(nome);
                    totaldistance = totaldistance + distance;
                    op.updateTotalDistance(nome, totaldistance);
                    
                    //consume in watts
                    double charge = op.fetch_charge(nome);
                    
                    double capacity = op.fetch_capacity(nome);
                    
                    double a = op.fetch_a(tipo);
                    
                    double b = op.fetch_b(tipo);
           
                    double c = op.fetch_c(tipo);
                    
                    double power = op.consumed_power(a, b, c, v, w);
                    
                    //double deltaconsumption = op.calc_charge(charge, capacity, power, RunningTime);
                    //System.out.println(deltaconsumption+ " delta");
                    double taskconsumption = stats.fetchTaskconsume(nome, taskType);
                    System.out.println("task consumption: " + taskconsumption);
                    //System.out.println(taskconsumption+ " task");
                    taskconsumption = taskconsumption + power;
                    
                    stats.updateTaskConsume(nome, taskconsumption, taskType);
                    double totalconsume = op.fetch_consumed_power(nome);
                    totalconsume = totalconsume + power;
                    op.updateTotalConsumption(nome, totalconsume);
                    
                    //charge
                    
                    double initialcharge = op.fetch_charge(nome);
                    double t0 = stats.fetch_t0(nome, taskType);
                    double deltat = RunningTime - t0;
                    stats.update_t0(nome, RunningTime, taskType);
                    double newcharge = op.calc_charge(initialcharge, capacity, power, deltat);
                    op.update_charge(nome, newcharge);
                   
                    //remaining time
                    
                    double remainingtime = capacity/power;
                    
                    
                    //show in tableview
                    
                    TreeItem <RobotView> a3 = new TreeItem<>(new RobotView(nome,taskType, RunningTime, taskdistance,v,w,taskconsumption,newcharge,remainingtime));
                    TreeItem <RobotView> dummy = new TreeItem<>(new RobotView("dummy",taskType, RunningTime, taskdistance,v,w,taskconsumption,newcharge,remainingtime));
                    root.getChildren().add(dummy);
                    
                    ObservableList<TreeItem<RobotView>> t = root.getChildren();
                    
                    int addflag=0;
                    //System.out.println("funciona_0");
                   for(int i =0; i < t.size(); i++){
                   TreeItem <RobotView> var = t.get(i);
                   RobotView rv = var.getValue();
                   
                   //System.out.println(nomeSimple +"nomsimple");
                   
                   //System.out.println(rv.robotname + "rv.robotname");
                   if(nome.equals(rv.robotname.get())){
                       root.getChildren().remove(dummy);
                       addflag =0;
                   var.setValue(new RobotView(nome,taskType, RunningTime, taskdistance,v,w,taskconsumption,newcharge,remainingtime));
                       
                       
                   }
                   
                   if(!(nome.equals(rv.robotname.get()))){
                   
                   root.getChildren().remove(dummy);
                   addflag =1;
                   }
                   
                                       

                   
                   }
                   if(addflag==1){
                    root.getChildren().add(a3);
                   }
                    
                    
                    
                    
                    
                    /*
                    
                    

                    
                    
                    
                    if(timestamp==0){
                    stats.update_initial_conditions(nome,0,0, taskType, 0);
                    
                    int taskcounter = stats.fetchTaskCounter(nome, taskType);
                    taskcounter++;
                    stats.updateTaskCounter(nome, taskcounter, taskType);
                    System.out.println("timestamp ==0");
                    }
                    
                    double t0 = stats.fetch_t0(nome, taskType);
                    double d0 = stats.fetch_d0(nome, taskType);
                    
                    double d = 0;
                    double dstored =0;
                    
                    if(taskType==previous_task&&(timestamp!=0)){
                    //runningtime = timestamp - t0;
                    
                    double x0 = app.fetch_x(nome);
                    double y0 = app.fetch_y(nome);
                    
                    d = app.distance(x0, x, y0, y);
                    dstored = stats.fetchTaskDistance(nome, taskType);
                    d = d + dstored;
                    stats.updateTaskDistance(nome, d, taskType);
                    //fetch distancia da task, soma e guarda.
                    
                    //fetch consume, somar ao rtime
                    double stored = stats.fetchTaskconsume(nome, tipo);
                    rtconsume = rtconsume + stored;
                    
                    
                    System.out.println("task = previous");
                        
                    }else{
                    int taskcounter = stats.fetchTaskCounter(nome, taskType);
                    taskcounter++;
                    stats.updateTaskCounter(nome, taskcounter, taskType);
                    runningtime = timestamp;
                    System.out.println("else");
                    stats.update_initial_conditions(nome,0,0, taskType, 0);
                    
                    
                    
                    //t0 tem de ser actualizado;
                    }
                    
                    //root.setValue(value);
                    //root.getChildren().
                    //root.getChildren().removeIf(filter)
                    TreeItem <RobotView> a3 = new TreeItem<>(new RobotView(nome,taskType, runningtime, d,v,w,rtconsume,8,9));
                    TreeItem <RobotView> dummy = new TreeItem<>(new RobotView("dummy",taskType, runningtime, d,v,w,rtconsume,8,9));
                    root.getChildren().add(dummy);
                     
                    
                    ObservableList<TreeItem<RobotView>> t = root.getChildren();
                    
                    int addflag=0;
                    //System.out.println("funciona_0");
                   for(int i =0; i < t.size(); i++){
                   TreeItem <RobotView> var = t.get(i);
                   RobotView rv = var.getValue();
                   
                   //System.out.println(nomeSimple +"nomsimple");
                   
                   //System.out.println(rv.robotname + "rv.robotname");
                   if(nome.equals(rv.robotname.get())){
                       root.getChildren().remove(dummy);
                       addflag =0;
                   var.setValue(new RobotView(nome,taskType, runningtime, d,v,w,rtconsume,90,69));
                       //System.out.println(nomeSimple + "funciona1");
                       System.out.println("entrei no if: " + nome + "=" +rv.robotname.get());
                   }
                   
                   if(!(nome.equals(rv.robotname.get()))){
                   System.out.println("entrei no else: " + nome + "/=" +rv.robotname.get());
                   root.getChildren().remove(dummy);
                   addflag =1;
                   }
                   
                                       //System.out.println("funciona_2");

                   
                   }
                   if(addflag==1){
                    root.getChildren().add(a3);
                   }
                    //if(t.contains(a3)){
                    //a3.setValue(new RobotView(nome,taskType, runningtime, d,v,w,consumo,90,69));
                    //}else{
                    
                    //root.getChildren().add(a3);
                    //}
                    
       
        
      
        
       
         
         
                    
        double delta_charge=0;
         
                    if(old_timestamp > timestamp){
                    
                        //significa que esta é uma nova sessão
                        double newcharge =app.calc_charge(carga, capacidade, rtconsume, timestamp);
                        
                        
                        taskTime = taskTime + timestamp;
                        stats.update(nome, taskTime, d, taskType);
                        
                    }else{
                    
                    double delta_time = timestamp - old_timestamp;
                    stats.update(nome, timestamp, d, taskType);
                     delta_charge = app.calc_charge(carga, capacidade, rtconsume, delta_time);
                    }
                    
                    rtconsume = rtconsume + delta_charge;
                    stats.updateTaskConsume(nome, rtconsume, taskType);
                  stats.updatePreviousTask(nome, taskType, taskType);
                    app.update(total_oper_time, d, consumo, timestamp, nome, tipo, x, y);
                    */
                
                }

            }
        } catch (IOException e) {
            System.out.println("I/O exception: " + e);
        } catch (Exception ex) {
            System.out.println("Exception in Thread Run. Exception : " + ex);
        }
    }

    public String getName(String robotName) {

        String nome = robotName;
        return (nome);

    }

    public int getRobotType(int rtype) {

        int type = rtype;
        return (type);

    }

}
