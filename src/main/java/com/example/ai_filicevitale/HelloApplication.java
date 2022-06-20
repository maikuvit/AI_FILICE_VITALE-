package com.example.ai_filicevitale;

import com.example.ai_filicevitale.Controller.HelloController;
import com.example.ai_filicevitale.Model.MossaManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        HelloController contr = fxmlLoader.getController();
        contr.setStarting();

        MossaManager.setup(contr);
        solver.setup();

        Scene scene = new Scene(root, 900, 800);
        stage.setTitle("Mahjong o come si scrive lui!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}