package edu.neu.ikigai.models;

public class TriggeringEvent {
    public String event;
    public String journal;
    public String location;

    public TriggeringEvent(String event, String journal, String location) {
        this.event = event;
        this.journal = journal;
        this.location = location;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
