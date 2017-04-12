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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author gabriel
 */
public class Add_New_RobotController implements Initializable {

    @FXML
    private AnchorPane Add_New_Robot_pane;
    @FXML
    private Text main;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void load_main(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Add_New_Robot_pane.getChildren().setAll(pane);
    }
    
}
