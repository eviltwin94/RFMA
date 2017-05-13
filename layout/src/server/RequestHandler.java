package server;

import data_manager.insert_operation_data;
import data_manager.task_stats;
import data_manager.update_operation_data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.util.Callback;
import static layout.FXMLDocumentController.root;
import system_manager.OperationData;
import system_manager.RobotView;
import system_manager.TreeviewHandler;




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
    
    int taskType =0;
          double  runningtime =0;
            double d=0;
           double v=0;
            double w=0;
            double consumo =0;
        
    
    
    
    private final Socket client;
    ServerSocket serverSocket = null;
    public String nome = "vazio";
    public int tipo = 0;

    public RequestHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream())); /*BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));*/) {
            System.out.println("Thread started with name:" + Thread.currentThread().getName());
            String robotInput;
            while ((robotInput = in.readLine()) != null) {

                String retval[] = new String[4];
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
update_operation_data set = new update_operation_data();

                    TreeItem <RobotView> a3 = new TreeItem<>(new RobotView(nome,taskType, runningtime, d,v,w,consumo,8,9));
                    root.getChildren().add(a3);
                    
                    set.update_xy(nome, 0,0);
                    if (!(app.verify_existence(nome))) {

                        app.insert(robotName, robotType);

                    }

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
                    
                   
        
        
                    task_stats stats = new task_stats();
                    
                    if(!stats.verifyTaskExistence(nome, taskType)){
                    
                    stats.insert(nome, taskType);
                    }

                    update_operation_data app = new update_operation_data();
                    //bloco referente ao tempo de operação em segundos
                    double oper_time = app.fetch_operation_time(nome);
                    double total_oper_time = oper_time + timestamp / (1000);

                    //bloco referente ao total de energia consumida
                    double consumo = app.fetch_consumed_power(nome);
                    double a1 = app.fetch_a(tipo);
                    double b2 = app.fetch_b(tipo);
                    double c3 = app.fetch_c(tipo);
                    

                    double rtconsume = app.consumed_power(a1, b2, c3, v, w);

                    consumo = consumo + rtconsume;

                    //bloco referente a distância
                    
                    double x0 = app.fetch_x(nome);
                    double y0 = app.fetch_y(nome);
                    
                    double d = app.distance(x0, x, y0, y);
                    
                    
                    //actualizar a carga
                    
                    double carga = app.fetch_charge(nome);
                    double capacidade = app.fetch_capacity(nome);
                    double old_timestamp = app.fetch_oldTimestamp(nome);
                    
                    double taskTime = stats.fetch_task_time(nome, taskType);
                    
                    //vamos buscar a task anterior para garantir que estamos na mesma task para os calculos a apresentar no ecrã
                    
                    double previous_task = stats.fetch_previous_task(nome, taskType);
                    double runningtime=0;
                    
                    if(timestamp==0){
                    stats.update_initial_conditions(nome,0,0, taskType);
                    }
                    
                    double t0 = stats.fetch_t0(nome, taskType);
                    double d0 = stats.fetch_d0(nome, taskType);
                    
                    if(taskType==previous_task&&(timestamp!=0)){
                    runningtime = timestamp - t0;
                    d = d - d0;
                        
                    }else{
                    
                    runningtime = timestamp;
                    
                    stats.update_initial_conditions(nome,0,0, taskType);
                    //t0 tem de ser actualizado;
                    }
                    
                    /*
                    root.getChildren().removeAll();
                    TreeItem <RobotView> a3 = new TreeItem<>(new RobotView(nome,taskType, runningtime, d,v,w,consumo,8,9));
                    root.getChildren().add(a3);
                    */
                    
                    col1.setCellValueFactory((TreeTableColumn.CellDataFeatures<RobotView, String> param) -> (param.getValue().getValue().getrobotname()));
        
        col2.setCellValueFactory((TreeTableColumn.CellDataFeatures<RobotView, Number> param) -> (param.getValue().getValue().gettask()));
        
        col3.setCellValueFactory((TreeTableColumn.CellDataFeatures<RobotView, Number> param) -> (param.getValue().getValue().getrunningtime()));
        
        col4.setCellValueFactory((TreeTableColumn.CellDataFeatures<RobotView, Number> param) -> (param.getValue().getValue().getdistance()));
        
        col5.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<RobotView, Number>, ObservableValue<Number>>(){
            @Override
            public ObservableValue<Number> call(TreeTableColumn.CellDataFeatures<RobotView, Number> param) {
                return (param.getValue().getValue().getlinearvelocity());
            }
    
    });
        
        col6.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<RobotView, Number>, ObservableValue<Number>>(){
            @Override
            public ObservableValue<Number> call(TreeTableColumn.CellDataFeatures<RobotView, Number> param) {
                return (param.getValue().getValue().getangularvelocity());
            }
    
    });
        
         col7.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<RobotView, Number>, ObservableValue<Number>>(){
            @Override
            public ObservableValue<Number> call(TreeTableColumn.CellDataFeatures<RobotView, Number> param) {
                return (param.getValue().getValue().getconsumption());
            }
    
    });
         
         col8.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<RobotView, Number>, ObservableValue<Number>>(){
            @Override
            public ObservableValue<Number> call(TreeTableColumn.CellDataFeatures<RobotView, Number> param) {
                return (param.getValue().getValue().getcharge());
            }
    
    });
         
         col9.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<RobotView, Number>, ObservableValue<Number>>(){
            @Override
            public ObservableValue<Number> call(TreeTableColumn.CellDataFeatures<RobotView, Number> param) {
                return (param.getValue().getValue().getremainingtime());
            }
    
    });
                    
                    if(old_timestamp > timestamp){
                    
                        //significa que esta é uma nova sessão
                        double newcharge =app.calc_charge(carga, capacidade, rtconsume, timestamp);
                        
                        
                        taskTime = taskTime + timestamp;
                        stats.update(nome, taskTime, d, taskType);
                        
                    }else{
                    
                    double delta_time = timestamp - old_timestamp;
                    stats.update(nome, delta_time, d, taskType);
                    double delta_charge = app.calc_charge(carga, capacidade, rtconsume, delta_time);
                    }
                    
                  
                    app.update(total_oper_time, d, consumo, timestamp, nome, tipo, x, y);
                    
                
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
