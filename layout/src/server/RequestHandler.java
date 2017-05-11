package server;

import data_manager.insert_operation_data;
import data_manager.update_operation_data;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import static java.lang.Math.sqrt;

import java.net.ServerSocket;
import java.net.Socket;
import system_manager.OperationData;

public class RequestHandler implements Runnable {

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

                    /*
              OperationData aux = new OperationData();
              
              aux.name = robotName;
              aux.type = robotType;
                     */
                    nome = getName(robotName);
                    tipo = getRobotType(robotType);

                    insert_operation_data app = new insert_operation_data();
update_operation_data set = new update_operation_data();
                    
                    set.update_xy(nome, 0,0);
                    if (!(app.verify_existence(nome))) {

                        app.insert(robotName, robotType);

                    }

                } else if (msgType == 2) {  // 2:<timestamp>;<task_type>;<x>,<y>,<theta>;<v>,<w>
                    retval = (robotInput.substring(2)).split(";");
                    int timestamp = Integer.parseInt(retval[0]);
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
                    
                    if(old_timestamp > timestamp){
                    
                        //significa que esta é uma nova sessão
                        app.calc_charge(carga, capacidade, rtconsume, timestamp);
                    }else{
                    
                    double delta_time = timestamp - old_timestamp;
                    
                    app.calc_charge(carga, capacidade, rtconsume, delta_time);
                    }
                    
                  
                    
                    //numero de cargas e descargas
                    
                    
                    
                    
                    
                    
                    
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
