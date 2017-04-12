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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author gabriel
 */
public class Add_New_TaskController implements Initializable {

    @FXML
    private TextField teste;
    @FXML
    private Text rfma;
    @FXML
    private AnchorPane Add_New_Task_pane;


    
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void main(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Add_New_Task_pane.getChildren().setAll(pane);
    }
    
}
