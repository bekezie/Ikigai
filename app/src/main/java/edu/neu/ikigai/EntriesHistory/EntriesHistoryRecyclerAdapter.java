package edu.neu.ikigai.EntriesHistory;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.neu.ikigai.R;

public class EntriesHistoryRecyclerAdapter extends RecyclerView.Adapter<EntriesHistoryRecyclerHolder> {

    private final ArrayList<EntryItem> entriesHistoryList;
    private OnLinkClickListener mListener;

    public interface OnLinkClickListener {
        void onLinkClick(int position);
    }

    public EntriesHistoryRecyclerAdapter(ArrayList<EntryItem> list) {
        this.entriesHistoryList = list;
    }

    public EntriesHistoryRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entry_card, parent, false);
        return new EntriesHistoryRecyclerHolder(view);
    }

    public void onBindViewHolder(EntriesHistoryRecyclerHolder holder, int position) {
        EntryItem currentCard = entriesHistoryList.get(position);

        holder.worksheetEntryDate.setText(currentCard.getWorksheetEntryDate());
        holder.worksheetEntryNumber.setText(currentCard.getWorksheetEntryNumber());
    }

    public int getItemCount() {
        return entriesHistoryList.size();
    }
}
