/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import data_manager.Robot;
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
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import static layout.FXMLDocumentController.root;
import system_manager.RobotView;


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
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        
        Alert success = new Alert(AlertType.INFORMATION);
success.setTitle("Confirmation");
success.setHeaderText(null);
success.setContentText("You added a new task successfully");

success.showAndWait();
    
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
