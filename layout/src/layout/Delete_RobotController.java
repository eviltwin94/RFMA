/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import data_manager.Robot;
import data_manager.delete_robot_type;
import data_manager.select_robot_type;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author gabriel
 */
public class Delete_RobotController implements Initializable {

    @FXML
    private Text main;
    @FXML
    private ChoiceBox locomotion_type;
    @FXML
    private AnchorPane Delete_Robot_Type_Pane;
    @FXML
    private Button deleteButton;

    /**
     * Initializes the controller class.
     */
     @Override
        public void initialize(URL location, ResourceBundle resources) {
            
            
            select_robot_type lista = new select_robot_type();
            
            /*
        ArrayList<Integer> robotTypeList = lista.selectAll();
            */
        
    locomotion_type.getItems().removeAll(locomotion_type.getItems());
    lista.selectAll(locomotion_type);
          
    /*
        locomotion_type.getItems().add(lista.selectAll());
*/
    
    /*
    locomotion_type.getItems().addAll("robot 1", "robot 2", "robot 3", "robot 4", "robot 5", "robot 6");
    locomotion_type.getSelectionModel().select("robot 1");
    */
     Robot testi = new Robot();
        testi.verify_Robot_Charge();
    }     

    @FXML
    private void load_main(MouseEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Delete_Robot_Type_Pane.getChildren().setAll(pane);
    }

    @FXML
    private void apagar_Robot_Type(ActionEvent event) {
        
        
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Delete robot type");
alert.setHeaderText("Are you sure you want to delete a robot type ?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
    
     int i = (Integer) locomotion_type.getValue();
        
        
        delete_robot_type app = new delete_robot_type();
        // delete the row with id 3
        app.delete(i);
        
         Alert success = new Alert(AlertType.INFORMATION);
success.setTitle("Confirmation");
success.setHeaderText(null);
success.setContentText("The delection was successfull");

success.showAndWait();
    
    
} else {
    // ... user chose CANCEL or closed the dialog
}
        
        
        
    }
    
}
