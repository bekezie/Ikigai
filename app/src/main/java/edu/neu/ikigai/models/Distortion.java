package edu.neu.ikigai.models;

public class Distortion {
    private String distortions;
    private String journal;

    public Distortion(String distortions, String journal) {
        setDistortions(distortions);
        setJournal(journal);
    }

    public String getDistortions() {
        return distortions;
    }

    public String getJournal() {
        return journal;
    }

    public void setDistortions(String distortions) {
        this.distortions = distortions;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }
}
