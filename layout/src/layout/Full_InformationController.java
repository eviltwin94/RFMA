/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import data_manager.task_stats;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author gabriel
 */
public class Full_InformationController implements Initializable {

    @FXML
    private AnchorPane rootpane;
    @FXML
    private BarChart<?, ?> h1;
    @FXML
    private NumberAxis y1;
    @FXML
    private CategoryAxis x1;
    @FXML
    private BarChart<String, Integer> h2;
    @FXML
    private NumberAxis y2;
    @FXML
    private CategoryAxis x2;
    @FXML
    private BarChart<?, ?> h3;
    @FXML
    private NumberAxis y3;
    @FXML
    private CategoryAxis x3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        XYChart.Series set1 = new XYChart.Series<>();
        
        task_stats stats = new task_stats();
        
         stats.timeHistogram( "Pioneer_P3DX_nr4", set1);
        h2.getData().addAll(set1);
        
        /*
        set1.getData().add(new XYChart.Data("James", 5000));
set1.getData().add(new XYChart.Data("Alice", 10000));
set1.getData().add(new XYChart.Data("Alex", 2000));
        */
//h2.getData().addAll(set1);

    }    

    
}
