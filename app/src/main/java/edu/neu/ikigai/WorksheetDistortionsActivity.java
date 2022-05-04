package edu.neu.ikigai;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.neu.ikigai.models.TriggeringEvent;

public class WorksheetDistortionsActivity extends AppCompatActivity {
    private static final String CLASS_TAG = WorksheetReasonableThoughtActivity.class.getSimpleName();
    private DatabaseReference mDatabase;
    private ArrayList<String> distortions;
    private String worksheetId;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worksheet_distortions);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        worksheetId = this.getIntent().getStringExtra("worksheetId");
        // Todo: populate array list with data from database
        //  or create a new array list
        //  set checked to true for checked boxes when saved data is being used

        // Todo: store actual checkboxes so you can activate and deactivate them whenever onCreate called
        distortions = new ArrayList<>();
        // could use distortions var for onResume and init() for onCreate
        init(savedInstanceState);
        // will want to find view by id and check it
        // but want to

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
    //Todo: perhaps set onClick listener to avoid passing view unnecessarily
    public void onDistortionClicked(View view) {
        CheckBox checkbox= (CheckBox) view;
        boolean checked = checkbox.isChecked();
        String name = checkbox.getText().toString();
        if (checked)
            distortions.add(view.getId() + ":" + name);
        else
            distortions.remove(view.getId() + ":" + name);
    }

    public void onDistortionSave(View view) {
        Map<String, Object> map = new HashMap<>();
        map.put("distortions", distortions);
        mDatabase.child("worksheet").child(mAuth.getCurrentUser().getUid()).child(worksheetId).updateChildren(map);
        //mDatabase.child("worksheet").child("austin").child(worksheetId).updateChildren(map);
    }

    public void onDistortionNext(View view) {
        onDistortionSave(view);
        Intent intent = new Intent(this, WorksheetReasonableThoughtActivity.class);
        intent.putExtra("worksheetId", worksheetId);
        startActivity(intent);
    }


    private void init(Bundle savedInstanceState) {
        initialItemData(savedInstanceState);

    }

    private void initialItemData(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSaved(worksheetId);
        }
    }
    public void getSaved(String sheetId) {
        DatabaseReference ref =  mDatabase.child("worksheet").child(mAuth.getCurrentUser().getUid()).child(sheetId).child("distortions");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ss : snapshot.getChildren()) {
                    //System.out.println(ss);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(CLASS_TAG, "saved data retrieval error", error.toException());
            }
        });
    }

}
