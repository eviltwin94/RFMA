/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import data_manager.insert_new_robot;
import data_manager.select_robot_type;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
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
    @FXML
    private ChoiceBox locomotion_type;
    @FXML
    private TextField tf_robot_name;
    @FXML
    private TextField tf_birthday;
    @FXML
    private TextField tf_capacity;
    @FXML
    private TextField tf_charge;
    @FXML
    private TextField tf_a;
    @FXML
    private TextField tf_b;
    @FXML
    private TextField tf_c;
    @FXML
    private TextField tf_d;
    @FXML
    private TextField tf_description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        select_robot_type lista = new select_robot_type();
        locomotion_type.getItems().removeAll(locomotion_type.getItems());
    lista.selectAll(locomotion_type);
        
    }    

    
    @FXML
    private void load_main(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Add_New_Robot_pane.getChildren().setAll(pane);
    }

    @FXML
    private void adicionar_robot(ActionEvent event) {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Add New Robot");
alert.setHeaderText("Are you sure you want to add a new robot ?");
alert.setContentText(null);

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
    // ... user chose OK
    
    insert_new_robot app = new insert_new_robot();
        
         String robot_name_tf = tf_robot_name.getText();
         
                 int robot_type_tf = (Integer) locomotion_type.getValue();

         
         double charge_tf = Double.parseDouble(tf_charge.getText());
         double capacity_tf = Double.parseDouble(tf_capacity.getText());
         int birthday_tf = Integer.parseInt(tf_birthday.getText());
         
         String description_tf = tf_description.getText();
         
                 app.insert(robot_name_tf, robot_type_tf, charge_tf, capacity_tf, birthday_tf, description_tf );

                 Alert success = new Alert(AlertType.INFORMATION);
success.setTitle("Confirmation");
success.setHeaderText(null);
success.setContentText("You added a new robot successfully");

success.showAndWait();
    
} else {
    // ... user chose CANCEL or closed the dialog
}
        /*
        insert_new_robot app = new insert_new_robot();
        
         String robot_name_tf = tf_robot_name.getText();
         
                 int robot_type_tf = (Integer) locomotion_type.getValue();

         
         double charge_tf = Double.parseDouble(tf_charge.getText());
         double capacity_tf = Double.parseDouble(tf_capacity.getText());
         int birthday_tf = Integer.parseInt(tf_birthday.getText());
         double a_tf = Double.parseDouble(tf_a.getText());
         double b_tf = Double.parseDouble(tf_b.getText());
         double c_tf = Double.parseDouble(tf_c.getText());
         double d_tf = Double.parseDouble(tf_d.getText());
         String description_tf = tf_description.getText();

         
         
        app.insert(robot_name_tf, robot_type_tf, charge_tf, capacity_tf, birthday_tf, a_tf, b_tf, c_tf, d_tf, description_tf );
        */
        
    }
    
}
