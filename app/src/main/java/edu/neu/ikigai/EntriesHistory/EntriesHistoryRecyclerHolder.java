package edu.neu.ikigai.EntriesHistory;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import edu.neu.ikigai.R;

public class EntriesHistoryRecyclerHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView user_input;

    public EntriesHistoryRecyclerHolder(View entryCardView) {
        super(entryCardView);
        title = entryCardView.findViewById(R.id.title_textview);
        user_input = entryCardView.findViewById(R.id.user_input_textview);
    }
}
