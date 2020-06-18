package UI;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Fragebogen;
import model.Fragen;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UI {
    private Controller controller;
    private Stage window;
    private int currentTime = 0;
    private int availableTime = 30*60;

    public UI(Stage window) throws FileNotFoundException {
        this.controller = new Controller();
        this.window = window;
    }

    /**
     * Diese Funktion zeigt das START-Fenster
     */
    public void introductionTest() {
        window.setTitle("Fuhrerscheinprufung");

        Button buttonStart = new Button("Start die Fuhrerscheinprufung!");
        StackPane layout = new StackPane();
        layout.getChildren().add(buttonStart);

        Scene sceneStart = new Scene(layout, 300, 275);

        buttonStart.setOnAction(e -> {
            window.close();
            try {
                startTest();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        });

        window.setScene(sceneStart);
        window.show();
    }

    /**
     * Diese Funktion wechselt das Fenster fur jede Frage
     */
    public void startTest() throws InterruptedException {
        this.currentTime = 0;
        MyThread countdown = new MyThread(currentTime);
        Thread thread = new Thread(countdown);
        thread.start();
        Fragebogen fragebogen = controller.getFragebogen();
        List<Fragen> fragen = fragebogen.getFragen();
        AtomicInteger nrFrage = new AtomicInteger(1);

        for (Fragen f : fragen) {
            if (countdown.getCurrentTime() == availableTime) {
                break;
            } else {
                Stage questionWindow = new Stage();

                if (controller.getFalscheAntworten() > 4 ) {
                    window.setTitle("Nicht Bestanden");

                    Label introMessage = new Label("Sie haben die Prufung NICHT bestanden!");
                    Button closeButton = new Button("CLOSE");

                    VBox layout = new VBox();
                    layout.setAlignment(Pos.CENTER);
                    layout.getChildren().addAll(introMessage, closeButton);

                    Scene startScene = new Scene(layout, 400, 200);

                    closeButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.exit(0);
                        }
                    });
                    window.setScene(startScene);
                    window.show();

                    break;
                }
                questionWindow.setTitle("Frage " + nrFrage);

                Label time = new Label("Seconds passed: " + countdown.getCurrentTime());

                Label nrAntworte = new Label("Anzahl der beantworten Frage: " + nrFrage + "; Anzahl der richtigen Antworten: " + controller.getRichtigeAntworten() + ";  Anzahl der falschen Antworten: " + controller.getFalscheAntworten());

                //textul intrebarii
                Label frageText = new Label(f.getQuestionText());
                List<String> answers = f.getAnswers();
                CheckBox ans1 = new CheckBox(answers.get(0));
                CheckBox ans2 = new CheckBox(answers.get(1));
                CheckBox ans3 = new CheckBox(answers.get(2));
                CheckBox ans4 = new CheckBox(answers.get(3));


                Button submitButton = new Button("SUBMIT");

                VBox options = new VBox();
                options.getChildren().addAll(frageText, ans1, ans2, ans3, ans4);
                options.setAlignment(Pos.CENTER_LEFT);
                options.setSpacing(10);


                VBox questionLayout = new VBox();
                questionLayout.getChildren().addAll(time, nrAntworte, options, submitButton);
                questionLayout.setAlignment(Pos.CENTER);
                questionLayout.setSpacing(20);

                Scene questionScene = new Scene(questionLayout, 800, 500);

                submitButton.setOnAction(e -> {
                    List<String> correctAnswers = f.getCorrectAnswers();
                    boolean check = this.controller.checkAnswers(correctAnswers, controller.Answer(ans1, ans2, ans3, ans4));
                    if (!check) {
                        this.controller.setFalscheAntworte();
                    } else
                        this.controller.setRichtigeAntworte();
                    if (this.currentTime <= availableTime) {
                        nrFrage.getAndIncrement();
                    }
                    questionWindow.close();
                });

                questionWindow.setScene(questionScene);
                questionWindow.showAndWait();
            }
        }

        if (countdown.getCurrentTime() == availableTime) {
            window.setTitle("Nicht Bestanden");

            Label introMessage = new Label("Sie haben die Prufung NICHT bestanden! Die Zeit ist abgelaufen");
            Button closeButton = new Button("CLOSE");

            VBox layout = new VBox();
            layout.setAlignment(Pos.CENTER);
            layout.getChildren().addAll(introMessage, closeButton);

            Scene startScene = new Scene(layout, 400, 200);

            closeButton.setOnAction(e -> window.close());

            window.setScene(startScene);
            window.show();
            countdown.stop();
        } else if (controller.getFalscheAntworten() <= 4 && controller.getRichtigeAntworten() >= 22) {
            window.setTitle("Bestanden");

            Label introMessage = new Label("Sie haben die Prufung bestanden!");
            Button closeButton = new Button("CLOSE");

            VBox layout = new VBox();
            layout.setAlignment(Pos.CENTER);
            layout.getChildren().addAll(introMessage, closeButton);

            Scene startScene = new Scene(layout, 400, 200);

            closeButton.setOnAction(e -> window.close());

            window.setScene(startScene);
            window.show();
        }
    }



}



