package repository;

import model.Fragebogen;
import model.Fragen;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Repo {

    private List<Fragen> meineFragen = new ArrayList<>();

    private Fragebogen fragebogen = new Fragebogen(0, 0, meineFragen);

    public Repo() throws FileNotFoundException {
        readFromFile();
        generateFragebogen();
    }

    /**
     * Reads from file the question, all answers and the correct ones for each question
     *
     * @throws FileNotFoundException
     */
    private void readFromFile() throws FileNotFoundException {
        String path = "C:\\Users\\Andreea\\IdeaProjects\\lab5Fragebogen\\src\\repository\\Fragen";
        File file = new File(path);
        Scanner sc = new Scanner(file);
        int ID = 1;
        while (sc.hasNextLine()) {

            String linie;
            linie = sc.nextLine();

            String words[] = linie.split("[|]"); //ptc este caracter special

            String frage = words[0];
            List<String> allAnswers = new ArrayList<>();
            allAnswers.add(words[1]);
            allAnswers.add(words[2]);
            allAnswers.add(words[3]);
            allAnswers.add(words[4]);
            List<String> correctAnswers = new ArrayList<>(Arrays.asList(words).subList(5, words.length));

            Fragen questions = new Fragen(ID, frage, allAnswers, correctAnswers);
            meineFragen.add(questions);
            ID++;
        }
    }

    /**
     * Diese Funktion generiert einen Fragebogen mit 26 zufalligen Fragen
     */
    public void generateFragebogen() {
        List<Fragen> fragen = this.getMeineFragen();
        List<Fragen> generatedFragen = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i <= 26; i++) {
            int randomIndex = random.nextInt(fragen.size() - 1);
            Fragen fragen1 = fragen.get(randomIndex);
            fragen1.setId(i);
            generatedFragen.add(fragen1);
            fragen.remove(randomIndex);
        }

        this.fragebogen.setFragen(generatedFragen);
    }


    /**
     * Gibt eine Liste mit allen Fragen zuruck
     *
     * @return
     */
    public List<Fragen> getMeineFragen() {
        return meineFragen;
    }


    /**
     * Gibt denn Fragenbogen zuruck
     *
     * @return
     */
    public Fragebogen getFragebogen() {
        return fragebogen;
    }

    /**
     * Erneut das Nummer fur die richtigen Antworten
     */
    public void setRichtigeAntworte() {
        this.fragebogen.setRichtigeAntworten(this.fragebogen.getRichtigeAntworten() + 1);
    }

    /**
     * Erneut das Nummer fur die falschen Antworten
     */
    public void setFalscheAntworte() {
        this.fragebogen.setFalscheAntworten(this.fragebogen.getFalscheAntworten() + 1);
    }

    /**
     * Gibt den Anzahl der falschen Antworten zuruck
     * @return
     */
    public int getFalscheAntworten() {
        return this.fragebogen.getFalscheAntworten();
    }

    /**
     * Gibt den Anzahl der richtigen Antworten zuruck
     * @return
     */
    public int getRichtigeAntworten() {
        return this.fragebogen.getRichtigeAntworten();
    }

}
