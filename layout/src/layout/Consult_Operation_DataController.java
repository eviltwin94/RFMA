/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

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
import system_manager.OperationData;

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
