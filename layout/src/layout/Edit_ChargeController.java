/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author gabriel
 */
public class Edit_ChargeController implements Initializable {

    @FXML
    private Text main;
    @FXML
    private ChoiceBox locomotion_type;
    @FXML
    private AnchorPane Edit_Charge_pane;

   
         
     
    
    
    
    
    
    
    
    
    @Override
        public void initialize(URL location, ResourceBundle resources) {
    locomotion_type.getItems().removeAll(locomotion_type.getItems());
    locomotion_type.getItems().addAll("robot 1", "robot 2", "robot 3", "robot 4", "robot 5", "robot 6");
    locomotion_type.getSelectionModel().select("robot 1");
    }    

    @FXML
    private void load_main(MouseEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Edit_Charge_pane.getChildren().setAll(pane);
    }

   
    
}
