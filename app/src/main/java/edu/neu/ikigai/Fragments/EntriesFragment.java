package edu.neu.ikigai.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import edu.neu.ikigai.EntriesHistory.EntriesHistoryRecyclerAdapter;
import edu.neu.ikigai.EntriesHistory.EntryItem;
import edu.neu.ikigai.R;

public class EntriesFragment extends Fragment {

    private View view;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    // RecyclerView and RecyclerAdapter
    private RecyclerView mRecyclerView;
    private EntriesHistoryRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    ArrayList<EntryItem> entriesHistoryList = new ArrayList<EntryItem>();

    private String user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_entries, container, false);

        user = mAuth.getInstance().getCurrentUser().getUid();

        entriesHistoryList = new ArrayList<>();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Searches the 'worksheet' branch in the database
        mDatabase.child("worksheet").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // Searches for the user in the 'history' branch
                for (DataSnapshot worksheet_user : snapshot.getChildren()) {
                    // if the user in the 'history' branch is found
                    if (String.valueOf(worksheet_user.getKey()).equalsIgnoreCase(user)) {

                        int worksheetNumber = 1;

                        // Searches for the keys inside the 'worksheet/user' branch path
                        for (DataSnapshot worksheet_user_firebase_key : worksheet_user.getChildren()) {

                            long timestamp = decode(worksheet_user_firebase_key.getKey());

                            DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                            String date = dateFormat.format(new Date(timestamp));
                            // Log.d("date:", date);

                            // "Worksheet 1", "Worksheet 2", "Worksheet 3", etc.
                            String worksheetName = "Worksheet " + worksheetNumber;

                            entriesHistoryList.add(new EntryItem(date, worksheetName, worksheet_user_firebase_key.getKey()));
                            worksheetNumber++;
                        }
                    }
                }
                buildRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }

    public void buildRecyclerView() {
        // RecyclerView and Adapter
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getContext());
        mAdapter = new EntriesHistoryRecyclerAdapter(entriesHistoryList);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    public long decode(String id) {
        String PUSH_CHARS = "-0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz";

        id = id.substring(0,8);
        long timestamp = 0;
        for (int i=0; i < id.length(); i++) {
            char c = id.charAt(i);
            timestamp = timestamp * 64 + PUSH_CHARS.indexOf(c);
        }
        return timestamp;
    }

    /**
     * Gets the current date in MM-dd-YYYY format.
     * @return the current date.
     */
    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date date = new Date();

        String currentDate = dateFormat.format(date);
        // Toast.makeText(BottomNavigationBarFragments.this,currentDate,Toast.LENGTH_LONG).show();
        return currentDate;
    }

    /**
     * Get the name of the user currently logged in.
     *
     * @return the sender's name.
     */
    public String getSender() {
        Intent intent = getActivity().getIntent();
        String sender = intent.getStringExtra("SENDER_USERNAME");
        return sender;
    }
}
