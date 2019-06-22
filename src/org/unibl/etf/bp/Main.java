package org.unibl.etf.bp;

import java.io.File;
import javafx.application.Application;
import javafx.stage.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.image.Image;


public class Main extends Application {
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        try
        {
            Parent root=FXMLLoader.load(getClass().getResource("/org/unibl/etf/bp/view/Login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("СТУДЕНТСКА ЗАДРУГА");
            primaryStage.getIcons().add(new Image("file:resources/sljem.jpg"));
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setResizable(false);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
}