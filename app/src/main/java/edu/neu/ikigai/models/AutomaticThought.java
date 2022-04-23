package edu.neu.ikigai.models;

public class AutomaticThought {
    private String thought;
    private String journal;

    public AutomaticThought(String thought, String journal) {
        this.thought = thought;
        this.journal = journal;
    }

    public String getThought() {
        return thought;
    }

    public void setThought(String thought) {
        this.thought = thought;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

}
