package edu.neu.ikigai.EntriesHistory;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import edu.neu.ikigai.BottomNavigationBarFragments;
import edu.neu.ikigai.LoginActivity;
import edu.neu.ikigai.R;
import edu.neu.ikigai.WorksheetSummary;

import static androidx.core.content.ContextCompat.startActivity;

public class EntriesHistoryRecyclerHolder extends RecyclerView.ViewHolder {

    public TextView worksheetEntryDate;
    public TextView worksheetEntryNumber;
    public CardView worksheetEntryCard;

    public EntriesHistoryRecyclerHolder(View entryCardView, View.OnClickListener listener) {
        super(entryCardView);
        worksheetEntryDate = entryCardView.findViewById(R.id.worksheet_entry_date);
        worksheetEntryNumber = entryCardView.findViewById(R.id.worksheet_entry_number);


        // sets a 'setOnClickerListener' on an entry card in RecyclerView
//        worksheetEntryCard = entryCardView.findViewById(R.id.worksheet_entry_card_view);
//        worksheetEntryCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Log.d("EntriesHistoryRecyclerHolder", "Clicked: " + worksheetEntryNumber.getText());
//
//                Intent summaryIntent = new Intent(entryCardView.getContext(), WorksheetSummary.class);
//
//                entryCardView.getContext().startActivity(summaryIntent);
//            }
//        });
    }
}
