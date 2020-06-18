import controller.Controller;
import model.Fragebogen;
import model.Fragen;
import org.junit.Assert;
import org.junit.Test;
import repository.Repo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tests {

    @Test
    public void repoTests() throws FileNotFoundException {
        Repo repo = new Repo();
        Assert.assertEquals(26, repo.getFragebogen().getFragen().size());
        Assert.assertEquals(0, repo.getFalscheAntworten());
        Assert.assertEquals(0, repo.getRichtigeAntworten());

        repo.setFalscheAntworte();
        repo.setFalscheAntworte();
        repo.setFalscheAntworte();
        Assert.assertEquals(3, repo.getFalscheAntworten());

        repo.setRichtigeAntworte();
        repo.setRichtigeAntworte();
        repo.setRichtigeAntworte();
        repo.setRichtigeAntworte();
        repo.setRichtigeAntworte();
        Assert.assertEquals(5, repo.getRichtigeAntworten());

        String frage = "Was ist eine geregelte Kreuzung?";
        List<String> allAnswers = new ArrayList<>();
        allAnswers.add("a). Eine Kreuzung mit Vorrangzeichen");
        allAnswers.add("b). Eine durch Armzeichen geregelte Kreuzung");
        allAnswers.add("c). Eine durch Lichtzeichen geregelte Kreuzung");
        allAnswers.add("d). Eine Kreuzung mit gelb blinkendem Licht");
        List<String> correctAnswers = new ArrayList<>();
        correctAnswers.add("b). Eine durch Armzeichen geregelte Kreuzung");
        correctAnswers.add("c). Eine durch Lichtzeichen geregelte Kreuzung");

        Fragen questions = new Fragen(1, frage, allAnswers, correctAnswers);
        Assert.assertEquals(questions.getAnswers(), repo.getMeineFragen().get(0).getAnswers());
        Assert.assertEquals(questions.getCorrectAnswers(), repo.getMeineFragen().get(0).getCorrectAnswers());
        Assert.assertEquals(questions.getQuestionText(), repo.getMeineFragen().get(0).getQuestionText());
    }

    @Test
    public void controllerTests() throws FileNotFoundException {
        Controller controller = new Controller();

        Assert.assertEquals(0, controller.getFalscheAntworten());
        Assert.assertEquals(0, controller.getRichtigeAntworten());
        Assert.assertEquals(26, controller.getFragebogen().getFragen().size());

        List<String> correct = new ArrayList<>();
        correct.add("Mama are mere");
        correct.add("Tata are pere");

        List<String> gegebeneAntworte = new ArrayList<>();
        gegebeneAntworte.add("Mama are mere");

        Assert.assertFalse(controller.checkAnswers(correct, gegebeneAntworte));

        gegebeneAntworte.add("Tata are pere");
        Assert.assertTrue(controller.checkAnswers(correct, gegebeneAntworte));
    }
}
