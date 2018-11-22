/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodosnumericos;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Jorge
 */
public class MetodosNumericos extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        stage.setTitle("Proyecto - Métodos Numéricos");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        Parent root = FXMLLoader.load(getClass().getResource("MetodosNumericosMain.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setWidth(1366);
        stage.setHeight(720);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}