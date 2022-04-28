package edu.neu.ikigai.models;

public class WorkSheet {
    private TriggeringEvent event;
    private AutomaticThought thought;
    private Distortion distortions;

    public WorkSheet() { }
    public WorkSheet(TriggeringEvent event, AutomaticThought thought, Distortion distortions) {
        this.event = event;
        this.thought = thought;
        this.distortions = distortions;
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

    public Distortion getDistortions() {
        return distortions;
    }

    public void setDistortions(Distortion distortions) {
        this.distortions = distortions;
    }
}
