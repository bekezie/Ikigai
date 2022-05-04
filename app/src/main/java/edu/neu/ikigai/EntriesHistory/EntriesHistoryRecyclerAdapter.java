package edu.neu.ikigai.EntriesHistory;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.neu.ikigai.R;

public class EntriesHistoryRecyclerAdapter extends RecyclerView.Adapter<EntriesHistoryRecyclerHolder> {

    private ArrayList<EntryItem> entriesHistoryList;
    private View.OnClickListener mListener;

    // public interface OnClickListener {
    //     void onClick(int position);
    // }

    // public void setOnClickListener(View.OnClickListener listener) {
    //     mListener = listener;
    // }

    public EntriesHistoryRecyclerAdapter(ArrayList<EntryItem> list) {
        this.entriesHistoryList = list;
    }

    @NonNull
    @Override
    public EntriesHistoryRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entry_card, parent, false);
        return new EntriesHistoryRecyclerHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EntriesHistoryRecyclerHolder holder, int position) {
        EntryItem currentCard = entriesHistoryList.get(position);

        holder.worksheetEntryDate.setText(currentCard.getWorksheetEntryDate());
        holder.worksheetEntryNumber.setText(currentCard.getWorksheetEntryNumber());
    }

    public int getItemCount() {
        return entriesHistoryList.size();
    }
}
