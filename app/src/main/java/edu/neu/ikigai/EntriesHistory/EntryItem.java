package edu.neu.ikigai.EntriesHistory;

public class EntryItem {
    private String worksheetEntryDate;
    private String worksheetEntryNumber;
    private String worksheetEntryKey;

    public EntryItem(String entryDate, String entryNumber, String key) {
        this.worksheetEntryDate = entryDate;
        this.worksheetEntryNumber = entryNumber; // "Worksheet 1", "Worksheet 2", etc.
        this.worksheetEntryKey = key;            // worksheet firebase key (e.g. "-N0wZ7olzZ2lP42l6JNK")
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
        return worksheetEntryKey;
    }

    public void setFirebaseKey(String key) {
        worksheetEntryKey = key;
    }
}
