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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author gabriel
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Button Add_New_Robot;
    @FXML
    private AnchorPane rootpane;
    @FXML
    private Button Add_New_Robot_Type;
    @FXML
    private Button Add_New_Task;
    @FXML
    private Button Edit_Charge;
    @FXML
    private Button Delete_Robot_Type;
    @FXML
    private Button Edit_Authotized_IPList;
    @FXML
    private Button Consult_Operation_Data;
    @FXML
    private Button alerta;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Add_New_Robot_Layout(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Add_New_Robot.fxml"));
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void Add_New_Robot_Type_Layout(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("Add_New_Robot_Type.fxml"));
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void Add_New_Task_Layout(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Add_New_Task.fxml"));
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void Edit_Charge_layout(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Edit_Charge.fxml"));
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void Delete_Robot_Type_Layout(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("Delete_Robot.fxml"));
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void Edit_Authorized_IPList_layout(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("Edit_Authorized_IPlist.fxml"));
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void Consult_Operation_Data_layout(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Consult_Operation_Data.fxml"));
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void alertar(ActionEvent event) {
    }

  
    
}
