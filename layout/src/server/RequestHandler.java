package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.net.ServerSocket;
import java.net.Socket;

public class RequestHandler implements Runnable {
  private final Socket client;
  ServerSocket serverSocket = null;

    public RequestHandler(Socket client) {
      this.client = client;
    }

    @Override
    public void run() {
      try ( BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
	/*BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));*/) {
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
              
          } else if (msgType == 2) {  // 2:<timestamp>;<task_type>;<x>,<y>,<theta>;<v>,<w>
              retval = (robotInput.substring(2)).split(";");
              int timestamp = Integer.parseInt(retval[0]);
              int taskType = Integer.parseInt(retval[1]);
              String robotPos = retval[2];
              String robotVel = retval[3];
              retval = robotPos.split(",");
              double x = Double.parseDouble(retval[0]);
              double y = Double.parseDouble(retval[1]);
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
          }

          
	  //userInput=userInput.replaceAll("[^A-Za-z0-9 ]", "");
          //System.out.println("From " + Thread.currentThread().getName() + ": msgType=" + msgType);
	  //writer.write("You entered : " + robotInput);
	  //writer.newLine();
	  //writer.flush();
	}
      } catch (IOException e) {
           System.out.println("I/O exception: " + e);
        } catch (Exception ex) {
	   System.out.println("Exception in Thread Run. Exception : " + ex);
	  }
    }

}


