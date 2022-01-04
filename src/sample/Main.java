package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage)  {
        try {


        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            primaryStage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/831/831319.png"));
//            primaryStage.getStyle().addAll(this.getClass().getResource("Stylesheet.css").toExternalForm());
            primaryStage.setTitle("weather");
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.show();
    }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
