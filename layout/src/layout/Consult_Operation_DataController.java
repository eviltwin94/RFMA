/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import data_manager.Robot;
import data_manager.select_robot;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import data_manager.OperationData;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.util.Callback;
import static layout.FXMLDocumentController.root;
import system_manager.RobotView;


/**
 * FXML Controller class
 *
 * @author gabriel
 */
public class Consult_Operation_DataController implements Initializable {

    @FXML
    private AnchorPane Consult_Operation_Data;
    @FXML
    private ChoiceBox locomotion_type;
    @FXML
    private Text main1;
    @FXML
    private Button deleteButton;

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
    
    /**
     * Initializes the controller class.
     */
     @Override
        public void initialize(URL location, ResourceBundle resources) {
            /**
    locomotion_type.getItems().removeAll(locomotion_type.getItems());
    locomotion_type.getItems().addAll("robot 1", "robot 2", "robot 3", "robot 4", "robot 5", "robot 6");
    locomotion_type.getSelectionModel().select("robot 1");
     */
            
            
          select_robot lista = new select_robot();
            
            /*
        ArrayList<Integer> robotTypeList = lista.selectAll();
            */
        
    locomotion_type.getItems().removeAll(locomotion_type.getItems());
    lista.selectOp(locomotion_type);  
            
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
    
    
            Robot testi = new Robot();
        testi.verify_Robot_Charge();
            
    }     

    @FXML
    private void load_main(MouseEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Consult_Operation_Data.getChildren().setAll(pane);
    }

    @FXML
    private void information(ActionEvent event) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Full_Information.fxml"));
Parent root1 = (Parent) fxmlLoader.load();
Stage stage = new Stage();
stage.setScene(new Scene(root1));  
stage.show();
    }

    @FXML
    private void export(ActionEvent event) throws IOException {
        
        String aux = (String) locomotion_type.getValue();
        
        OperationData app = new OperationData();
        app.exportCSV(aux);
   
        
        
    }
    
}
