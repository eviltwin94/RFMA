/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import data_manager.Robot;
import data_manager.insert_robot_type;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import static layout.FXMLDocumentController.root;
import system_manager.RobotView;

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
    private TextField robot_type_tf;
    @FXML
    private TextField manufactor_name_tf;
    @FXML
    private TextField payload_tf;
    @FXML
    private TextField a_tf;
    @FXML
    private TextField b_tf;
    @FXML
    private TextField c_tf;
    @FXML
    private TextField d_tf;
    @FXML
    private TextField robot_name_tf;
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
    

    
    
    @Override
public void initialize(URL location, ResourceBundle resources) {
    locomotion_type.getItems().removeAll(locomotion_type.getItems());
    locomotion_type.getItems().addAll("2 Wheel differential drive with one castor wheel", "Omnidirectionl kinematics with swedish wheels", "tricycle robot drive", "4 ackerman wheels", "skidster kinematics with wheels or tracks", "with legs");
    locomotion_type.getSelectionModel().select("tricycle robot drive");
    
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
    private void load_main(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Add_New_Robot_Type_pane.getChildren().setAll(pane);
    }

    @FXML
    private void adicionar_robot_type(ActionEvent event) {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Add new robot type");
alert.setHeaderText("Are you sure you want to add a new robot type ?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
    // ... user chose OK
    
    insert_robot_type app = new insert_robot_type();
        
         String tf_manufactor_name = manufactor_name_tf.getText();
         String tf_robot_name = (robot_name_tf.getText());
         double tf_payload = Double.parseDouble(payload_tf.getText());
         String tf_locomotion_type = (String) locomotion_type.getValue();
         double tf_a = Double.parseDouble(a_tf.getText());
         double tf_b = Double.parseDouble(b_tf.getText());
         double tf_c = Double.parseDouble(c_tf.getText());
         double tf_d = Double.parseDouble(d_tf.getText());

         
         
        app.insert(tf_robot_name, tf_payload, tf_locomotion_type, tf_manufactor_name, tf_a, tf_b, tf_c, tf_d);
        
         Alert success = new Alert(AlertType.INFORMATION);
success.setTitle("Confirmation");
success.setHeaderText(null);
success.setContentText("You added a new robot type successfully");

success.showAndWait();
    
    
} else {
    // ... user chose CANCEL or closed the dialog
}
        
        
        
        
        
    }
    
    
    /**
     * Initializes the controller class.
     */
       
    
}
