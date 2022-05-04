package edu.neu.ikigai.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import edu.neu.ikigai.MainActivity;
import edu.neu.ikigai.ProfileActivity;
import edu.neu.ikigai.R;
import edu.neu.ikigai.WorksheetCreate;
import edu.neu.ikigai.WorksheetDistortionsActivity;
import edu.neu.ikigai.WorksheetEventActivity;
import edu.neu.ikigai.WorksheetReasonableThoughtActivity;
import edu.neu.ikigai.WorksheetThoughtActivity;

public class HomeSummaryFragment extends Fragment {
    private TextView event;
    private TextView cognitive_distortions;
    private TextView automatic_thought;
    private TextView reasonable_thought;
    private DatabaseReference mDatabase;
    private String worksheetId;

    private FirebaseAuth mAuth;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_summary, container, false);
        worksheetId = "-N15WtJgaMhoCcvT0v5F";
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        getWorksheetDetails();
        reasonable_thought = (TextView) view.findViewById(R.id.primary_emotion);

        reasonable_thought.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(HomeSummaryFragment.this.getActivity(), WorksheetReasonableThoughtActivity.class);
                intent.putExtra("worksheetId", worksheetId);
                getActivity().startActivity(intent);

            }
        });
        automatic_thought = (TextView) view.findViewById(R.id.automatic);
        automatic_thought.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(HomeSummaryFragment.this.getActivity(), WorksheetEventActivity.class);
                intent.putExtra("worksheetId", worksheetId);
                getActivity().startActivity(intent);

            }
        });
        cognitive_distortions = (TextView) view.findViewById(R.id.cognitive);
        cognitive_distortions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(HomeSummaryFragment.this.getActivity(), WorksheetDistortionsActivity.class);
                intent.putExtra("worksheetId", worksheetId);
                getActivity().startActivity(intent);

            }
        });
        event = (TextView) view.findViewById(R.id.event);
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(HomeSummaryFragment.this.getActivity(), WorksheetEventActivity.class);
                intent.putExtra("worksheetId", worksheetId);
                getActivity().startActivity(intent);

            }
        });
        return view;
    }

    private void getWorksheetDetails() {
        // Write a message to the database
        DatabaseReference myRef = mDatabase.child("worksheet").child(mAuth.getCurrentUser().getUid()).child(worksheetId);
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String thought = (String) snapshot.child("thought").child("thought").getValue();
                String eventTrigger = (String) snapshot.child("event").child("event").getValue();
                String reasonThought = (String) snapshot.child("reasonableThought").child("thought").getValue();
                event.setText(eventTrigger);
                automatic_thought.setText(thought);
                reasonable_thought.setText(reasonThought);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Getting Post failed, log a message

            }
        });
    }


}
