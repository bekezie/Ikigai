package edu.neu.ikigai.EntriesHistory;

public class EntryItem {
    private String worksheetEntryDate;
    private String worksheetEntryNumber;
    private String firebaseKey;

    public EntryItem(String entryDate, String entryNumber, String key) {
        this.worksheetEntryDate = entryDate;
        this.worksheetEntryNumber = entryNumber;
        this.firebaseKey = key;
    }

    public String getWorksheetEntryDate() {
        return worksheetEntryDate;
    }

    public void setWorksheetEntryDate(String date) {
        worksheetEntryDate = date;
    }

    public String getWorksheetEntryNumber() {
        return worksheetEntryNumber;
    }

    public void setWorksheetEntryNumber(String number) {
        worksheetEntryNumber = number;
    }

    public String getFirebaseKey() {
        return firebaseKey;
    }

    public void setFirebaseKey(String key) {
        firebaseKey = key;
    }
}
