package controller;

import javafx.scene.control.CheckBox;
import model.Fragebogen;
import repository.Repo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private Repo repo;


    public Controller() throws FileNotFoundException {
        this.repo = new Repo();
    }

    /**
     * Erneut das Nummer fur die richtigen Antworten
     */
    public void setRichtigeAntworte() {
        repo.setRichtigeAntworte();
    }

    /**
     * Erneut das Nummer fur die falschen Antworten
     */
    public void setFalscheAntworte() {
        repo.setFalscheAntworte();
    }

    /**
     * Gibt den Anzahl der falschen Antworten zuruck
     * @return
     */
    public int getFalscheAntworten() {
        return repo.getFalscheAntworten();
    }

    /**
     * Gibt den Anzahl der richtigen Antworten zuruck
     * @return
     */
    public int getRichtigeAntworten() {
        return repo.getRichtigeAntworten();
    }


    /**
     * Pruft, ob die gegebenen Antworten die richtigen Antworten sind.
     * @param correctAnswers
     * @param chosenAnswers
     * @return
     */
    public boolean checkAnswers(List<String> correctAnswers, List<String> chosenAnswers) {
        for (String corect : correctAnswers) {
            int ok = 0;
            for (String chosen : chosenAnswers) {
                if (corect.equals(chosen))
                    ok = 1;
            }
            if (ok == 0)
                return false;
        }
        return true;
    }

    /**
     * Gibt denn Fragenbogen zuruck
     * @return
     */
    public Fragebogen getFragebogen() {
        return repo.getFragebogen();
    }

    /**
     * Diese Funktion gibt eine Liste mit die gewahlten Antworten zuruck
     *
     * @param box1
     * @param box2
     * @param box3
     * @param box4
     * @return
     */
    public List<String> Answer(CheckBox box1, CheckBox box2, CheckBox box3, CheckBox box4) {
        List<String> answer = new ArrayList<>();

        if (box1.isSelected())
            answer.add(box1.getText());
        if (box2.isSelected())
            answer.add(box2.getText());
        if (box3.isSelected())
            answer.add(box3.getText());
        if (box4.isSelected())
            answer.add(box4.getText());

        return answer;
    }

}
