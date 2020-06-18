package model;

import java.util.List;

public class Fragebogen {
    private int ID;
    private int falscheAntworten;
    private int richtigeAntworten;
    private List<Fragen> fragen;

    public Fragebogen( int falscheAntworten, int richtigeAntworten, List<Fragen> fragen) {
        this.falscheAntworten = falscheAntworten;
        this.richtigeAntworten = richtigeAntworten;
        this.fragen = fragen;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {this.ID = ID;}

    public int getFalscheAntworten() {
        return falscheAntworten;
    }

    public void setFalscheAntworten(int falscheAntworten) {
        this.falscheAntworten = falscheAntworten;
    }

    public int getRichtigeAntworten() {
        return richtigeAntworten;
    }

    public void setRichtigeAntworten(int richtigeAntworten) {
        this.richtigeAntworten = richtigeAntworten;
    }

    public List<Fragen> getFragen() {
        return fragen;
    }

    public void setFragen(List<Fragen> fragen) {
        this.fragen = fragen;
    }

}
