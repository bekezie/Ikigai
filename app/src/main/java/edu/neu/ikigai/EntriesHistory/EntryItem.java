package edu.neu.ikigai.EntriesHistory;

public class EntryItem {
    private String worksheetEntryDate;
    private String worksheetEntryNumber;

    public EntryItem(String entryDate, String entryNumber) {
        this.worksheetEntryDate = entryDate;
        this.worksheetEntryNumber = entryNumber;
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
}
