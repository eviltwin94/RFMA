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
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;

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
    @FXML
    private ImageView home;


    
  

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
        
         
         Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Add New Task");
alert.setHeaderText("Are you sure you want to add a new task ?");
//alert.setContentText("Are you sure you want to add a new task ?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
    // ... user chose OK
    
    String tf_task_name = task_name_tf.getText();
         int tf_task_id = Integer.parseInt(task_id_tf.getText());
         String tf_task_description = task_description_tf.getText();

         
         
        app.insert(tf_task_id, tf_task_name, tf_task_description);
    
} else {
    // ... user chose CANCEL or closed the dialog
}
         
/*
         
         
         String tf_task_name = task_name_tf.getText();
         int tf_task_id = Integer.parseInt(task_id_tf.getText());
         String tf_task_description = task_description_tf.getText();

         
         
        app.insert(tf_task_id, tf_task_name, tf_task_description);

*/
    }

    @FXML
    private void load_main(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Add_New_Task_pane.getChildren().setAll(pane);
    }
    
}
