package edu.neu.ikigai.EntriesHistory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import edu.neu.ikigai.R;

public class EntriesHistoryRecyclerHolder extends RecyclerView.ViewHolder {

    public TextView worksheetEntryDate;
    public TextView worksheetEntryNumber;

    public EntriesHistoryRecyclerHolder(View entryCardView) {
        super(entryCardView);
        worksheetEntryDate = entryCardView.findViewById(R.id.worksheet_entry_date);
        worksheetEntryNumber = entryCardView.findViewById(R.id.worksheet_entry_number);
    }
}
