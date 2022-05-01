package edu.neu.ikigai.EntriesHistory;

public class EntryItem {
    private String title;
    private String userInput;

    public EntryItem(String worksheet_title, String userInputText) {
        this.title = worksheet_title;
        this.userInput = userInputText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String text) {
        title = text;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String text) {
        userInput = text;
    }
}
