package com.example.ai_filicevitale;

import com.example.ai_filicevitale.Controller.HelloController;
import com.example.ai_filicevitale.Model.MossaManager;
import com.example.ai_filicevitale.Model.Struttura_tessere;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HelloApplication extends Application {

    public static Timeline tl;

    private static boolean HO_FINITO = false;
    public static void stop_game(){
        tl.stop();
        System.out.println("gioco terminato!");
    }


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        HelloController contr = fxmlLoader.getController();
        contr.setStarting();

        MossaManager.setup(contr);
        solver.setup();

        solver.setController(contr);
        Scene scene = new Scene(root, 900, 800);
        stage.setTitle("Mahjong o come si scrive lui!");
        stage.setScene(scene);
        stage.show();

        tl =
                new Timeline(new KeyFrame(Duration.millis(500), e -> {
                    try {
                        execMossa();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();
    }

    private void execMossa() throws Exception {

        //aggiungo le tessere libere ai fatti ...
        Struttura_tessere.getInstance().checkLibere();

        //elaboro la prossima mossa da dlv ...
        solver.prossimaMossa();

    }

    public static void main(String[] args) {
        launch();
    }
}