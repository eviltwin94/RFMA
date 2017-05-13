/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system_manager;

import javafx.scene.control.TreeItem;

/**
 *
 * @author gabriel
 */
public class TreeviewHandler {
    
    
    
    public void addTreeView(TreeItem <RobotView> root){
    
    TreeItem <RobotView> a1 = new TreeItem<>(new RobotView("NASM",2,3,4,5,6,7,8,9));
    root.getChildren().add(a1);
    
    }
    

    public void setTree(){
    
    
    }
    
}


