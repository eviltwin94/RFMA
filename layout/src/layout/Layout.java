/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

/*

Para fazer quinta-feira:

fazer função editar charge que mexe com a base de dados, para aprender a actualizar tabelas existentes.
Depois, actualizar as informações de tipo 2
*/


import data_manager.Robot;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import server.RequestHandler;
//import layout.FXMLDocumentController.rootpane;



/**
 *
 * @author gabriel
 */
public class  Layout extends Application {
    
    
    
    
    /**
     * 
     * 
     * cuidado daqui para baixo
     */
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        
        
        stage.setScene(scene);
        stage.show();
        
        /*
         scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
 public void handle(final KeyEvent keyEvent) {
   if (keyEvent.getCode() == KeyCode.F5) {
       try {
           System.out.println("F5 pressed");
           
           AnchorPane pane = FXMLLoader.load(getClass().getResource("Add_New_Robot.fxml"));
           rootpane.getChildren().setAll(pane);
           
           
           //FXMLDocumentController.Add_New_Robot_Layout();
           
           
           keyEvent.consume();
       } catch (IOException ex) {
           Logger.getLogger(Layout.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
 }
});
       


          */  
            
            
            
            
            
        };

    /*
    @Override
    public void init ()
   {
      System.out.printf("init() called on thread %s%n", 
                        Thread.currentThread());
      
      System.out.println("Start of main");
//    if (args.length < 1) {
//      System.err.println("Usage: java EchoServer <port number>");
//      System.exit(1);
//    }
    int portNumber = 10227; //Integer.parseInt(args[0]);
    ExecutorService executor = null;
    try (ServerSocket serverSocket = new ServerSocket(portNumber);) {
      //serverSocket.setSoTimeout(10000);  
      executor = Executors.newFixedThreadPool(5);
      System.out.println("Waiting for clients on port " + portNumber);
      while (true) {
        Socket clientSocket = serverSocket.accept();
        Runnable worker = new RequestHandler(clientSocket);
        executor.execute(worker);
      }
    }catch (IOException e) {
       System.out.println("Exception caught when trying to listen on port "
	+ portNumber + " or listening for a connection");
       System.out.println(e.getMessage());
       
    }
    finally {
        if (executor != null) {
	executor.shutdown();
	}
    }
   }
*/
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
       
        Task<Integer> task = new Task<Integer>() {
    @Override protected Integer call() throws Exception {
        int portNumber = 10227;
        
        ExecutorService executor = null;
    try (ServerSocket serverSocket = new ServerSocket(portNumber);) {
      //serverSocket.setSoTimeout(10000);  
      executor = Executors.newFixedThreadPool(20);
      System.out.println("Waiting for clients on port " + portNumber);
      while (true) {
        Socket clientSocket = serverSocket.accept();
        Runnable worker = new RequestHandler(clientSocket);
        executor.execute(worker);
        
      }
    }catch (IOException e) {
       System.out.println("Exception caught when trying to listen on port "
	+ portNumber + " or listening for a connection");
       System.out.println(e.getMessage());
       
    }
    finally {
        if (executor != null) {
	executor.shutdown();
	}
    }
        
        return portNumber;
    }
};
        
        
       
        
      
        
    
    
    
         Thread th = new Thread(task);
th.setDaemon(true);
th.start();







        launch(args);
        
    }
        
        
}
        
                

        
    

    
        
          
           
      
      
     
            
        
        
   
        
           
      
      
        
    
        
       
        
       
        
     
    


    

