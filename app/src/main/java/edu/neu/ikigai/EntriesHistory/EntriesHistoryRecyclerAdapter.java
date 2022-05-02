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

    public EntriesHistoryRecyclerAdapter(ArrayList<EntryItem> receivedEmojiList, Resources res, String packName) {
        this.entriesHistoryList = receivedEmojiList;
    }

    public EntriesHistoryRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entry_card, parent, false);
        return new EntriesHistoryRecyclerHolder(view);
    }

    public void onBindViewHolder(EntriesHistoryRecyclerHolder holder, int position) {
        EntryItem currentCard = entriesHistoryList.get(position);

        holder.title.setText(currentCard.getTitle());
        holder.user_input.setText(currentCard.getUserInput());
    }

    public int getItemCount() {
        return entriesHistoryList.size();
    }
}
