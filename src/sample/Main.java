package sample;

import UI.UI;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Main extends Application {


    public static void main(String[] args) throws FileNotFoundException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        UI ui = new UI(primaryStage);
        ui.introductionTest();
    }


}