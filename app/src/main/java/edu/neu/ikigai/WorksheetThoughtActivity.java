package edu.neu.ikigai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import edu.neu.ikigai.models.Thought;
import edu.neu.ikigai.models.TriggeringEvent;

public class WorksheetThoughtActivity extends AppCompatActivity {
    private static final String TAG = WorksheetEventActivity.class.getSimpleName();
    private DatabaseReference mDatabase;
    private EditText thoughtEt;
    private EditText journalEt;
    private Button nextButton;
    private Button saveButton;
    private String worksheetId;
    private FirebaseAuth mAuth;
    //private String updateWorksheet;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worksheet_thought);
        //updateWorksheet = "-N15WtJgaMhoCcvT0v5F";
        // updateWorksheet = this.getIntent().getStringExtra("updateWorksheet");
        worksheetId = this.getIntent().getStringExtra("worksheetId");
        thoughtEt = (EditText) findViewById(R.id.thoughtEditText);
        journalEt = (EditText) findViewById(R.id.journalEditText);
        nextButton = (Button) findViewById(R.id.thoughtNextButton);
        saveButton = (Button) findViewById(R.id.thoughtSaveButton);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        //Log.w(TAG, "Update worksheet " + updateWorksheet);

//        if (worksheetId != null) {
//            SavedWorksheet();
//            saveButton.setText("Update");
//            nextButton.setText("Cancel");
//
//        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(worksheetId == null){
                    save();
                }
                next();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser curruser = mAuth.getCurrentUser();
        if (curruser == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    public void SavedWorksheet() {
        // Write a message to the database
        DatabaseReference myRef = mDatabase.child("worksheet").child(mAuth.getCurrentUser().getUid()).child(worksheetId).child("thought");
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String thought = (String) snapshot.child("thought").getValue();
                    String journal = (String) snapshot.child("journal").getValue();
                    thoughtEt.setText(thought);
                    journalEt.setText(journal);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });
    }

    public void save() {
        Thought thought = new Thought(thoughtEt.getText().toString(), journalEt.getText().toString());
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("autoThought", thought);
        mDatabase.child("worksheet").child(mAuth.getCurrentUser().getUid()).child(worksheetId).updateChildren(map);
    }

    public void next() {
        Intent intent = new Intent(this, WorksheetDistortionsActivity.class);
        intent.putExtra("worksheetId", worksheetId);
        startActivity(intent);
    }
}