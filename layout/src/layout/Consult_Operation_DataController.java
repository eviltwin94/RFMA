/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
public class Consult_Operation_DataController implements Initializable {

    @FXML
    private AnchorPane Consult_Operation_Data;
    @FXML
    private Text main;
    @FXML
    private ChoiceBox locomotion_type;

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
    }     

    @FXML
    private void load_main(MouseEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Consult_Operation_Data.getChildren().setAll(pane);
    }
    
}
