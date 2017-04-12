/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author gabriel
 */
public class Add_New_Robot_TypeController implements Initializable {

    @FXML
    private ChoiceBox locomotion_type;
    @FXML
    private AnchorPane Add_New_Robot_Type_pane;
    @FXML
    private Text main;
    

    
    
    @Override
public void initialize(URL location, ResourceBundle resources) {
    locomotion_type.getItems().removeAll(locomotion_type.getItems());
    locomotion_type.getItems().addAll("2 Wheel differential drive with one castor wheel", "Omnidirectionl kinematics with swedish wheels", "tricycle robot drive", "4 ackerman wheels", "skidster kinematics with wheels or tracks", "with legs");
    locomotion_type.getSelectionModel().select("tricycle robot drive");
}

    @FXML
    private void load_main(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Add_New_Robot_Type_pane.getChildren().setAll(pane);
    }
    
    
    /**
     * Initializes the controller class.
     */
       
    
}
