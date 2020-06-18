package model;

import java.util.List;

public class Fragen {
    private String questionText;
    private List<String> answers;
    private List<String> correctAnswers;
    private int id;


    public Fragen(int id, String questionText, List<String> answers, List<String> correctAnswers) {
        this.id=id;
        this.questionText = questionText;
        this.answers = answers;
        this.correctAnswers = correctAnswers;
    }

    /*GETTERS UND SETTERS*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public List<String> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(List<String> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }
}
