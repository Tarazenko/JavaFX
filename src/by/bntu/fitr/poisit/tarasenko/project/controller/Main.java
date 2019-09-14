package by.bntu.fitr.poisit.tarasenko.project.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
       // Parent root = FXMLLoader.load(getClass().getResource("../view/mainWindow.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/by/bntu/fitr/poisit/tarasenko/project/view/mainWindow.fxml"));
        primaryStage.setTitle("Train schedule");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
