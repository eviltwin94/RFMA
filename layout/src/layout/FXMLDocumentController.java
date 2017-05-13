/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static java.util.logging.Logger.global;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import static jdk.nashorn.internal.objects.NativeRegExp.global;
import server.RequestHandler;
import system_manager.RobotView;
import system_manager.TreeviewHandler;


/**
 *
 * @author gabriel
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Button Add_New_Robot;
    @FXML
    private AnchorPane rootpane;
    @FXML
    private Button Add_New_Robot_Type;
    @FXML
    private Button Add_New_Task;
    @FXML
    private Button Edit_Charge;
    @FXML
    private Button Delete_Robot_Type;
    @FXML
    private Button Edit_Authotized_IPList;
    @FXML
    private Button Consult_Operation_Data;
    @FXML
    private Button alerta;
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
    /*
  TreeItem <RobotView> a1 = new TreeItem<>(new RobotView("NASM",2,3,4,5,6,7,8,9));
  TreeItem <RobotView> a2 = new TreeItem<>(new RobotView("NASM",2,3,4,5,6,7,8,9));
  TreeItem <RobotView> a3 = new TreeItem<>(new RobotView("NASM",2,3,4,5,6,7,8,9));
  */
  public static TreeItem <RobotView> root = new TreeItem<>(new RobotView("tetas",2,3,4,5,6,7,8,9));
  
  
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
        root.getChildren().setAll(a1, a2, a3);
        
        root.getChildren().add(a1);
        */
        
        //TreeviewHandler hand = new TreeviewHandler();
        
        //hand.addTreeView(root);
        
        table.setRoot(root);
        
        col1.setCellValueFactory((TreeTableColumn.CellDataFeatures<RobotView, String> param) -> (param.getValue().getValue().getrobotname()));
        
        col2.setCellValueFactory((TreeTableColumn.CellDataFeatures<RobotView, Number> param) -> (param.getValue().getValue().gettask()));
        
        col3.setCellValueFactory((TreeTableColumn.CellDataFeatures<RobotView, Number> param) -> (param.getValue().getValue().getrunningtime()));
        
        col4.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<RobotView, Number>, ObservableValue<Number>>(){
            @Override
            public ObservableValue<Number> call(TreeTableColumn.CellDataFeatures<RobotView, Number> param) {
                return (param.getValue().getValue().getdistance());
            }
    
    });
        
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
        
        //TreeView<String> tree = new TreeView<>(dummyRoot);
    table.setShowRoot(false);
     root.setExpanded(true);
        
        /*
        
          teste t = new teste();
          
          t.testar();
      
      */
     
    
        
        
    }    

    @FXML
    private void Add_New_Robot_Layout(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Add_New_Robot.fxml"));
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void Add_New_Robot_Type_Layout(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("Add_New_Robot_Type.fxml"));
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void Add_New_Task_Layout(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Add_New_Task.fxml"));
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void Edit_Charge_layout(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Edit_Charge.fxml"));
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void Delete_Robot_Type_Layout(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("Delete_Robot.fxml"));
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void Edit_Authorized_IPList_layout(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("Edit_Authorized_IPlist.fxml"));
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void Consult_Operation_Data_layout(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Consult_Operation_Data.fxml"));
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void alertar(ActionEvent event) {
    }

  
    
}
