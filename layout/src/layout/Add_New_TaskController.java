/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import data_manager.insert_task;

/**
 * FXML Controller class
 *
 * @author gabriel
 */
public class Add_New_TaskController implements Initializable {

    @FXML
    private Text rfma;
    @FXML
    private AnchorPane Add_New_Task_pane;
    @FXML
    private TextField task_name_tf;
    @FXML
    private TextField task_id_tf;
    @FXML
    private TextField task_description_tf;


    
  

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

    @FXML
    private void adicionar_tarefa(ActionEvent event) {
        
         insert_task app = new insert_task();
        
         String tf_task_name = task_name_tf.getText();
         int tf_task_id = Integer.parseInt(task_id_tf.getText());
         String tf_task_description = task_description_tf.getText();

         
         
        app.insert(tf_task_id, tf_task_name, tf_task_description);
    }
    
}
