package edu.neu.ikigai.models;

public class WorkSheet {
    private TriggeringEvent event;
    private AutomaticThought thought;

    public WorkSheet() { }
    public WorkSheet(TriggeringEvent event, AutomaticThought thought) {
        this.event = event;
        this.thought = thought;
    }

    public TriggeringEvent getEvent() {
        return event;
    }

    public void setEvent(TriggeringEvent event) {
        this.event = event;
    }

    public AutomaticThought getThought() {
        return thought;
    }

    public void setThought(AutomaticThought thought) {
        this.thought = thought;
    }
}
