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

import edu.neu.ikigai.models.AutomaticThought;
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
    private String updateWorksheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worksheet_thought);
        //updateWorksheet = "-N15WtJgaMhoCcvT0v5F";
        updateWorksheet = this.getIntent().getStringExtra("updateWorksheet");
        worksheetId = this.getIntent().getStringExtra("worksheetId");
        thoughtEt = (EditText) findViewById(R.id.thoughtEditText);
        journalEt = (EditText) findViewById(R.id.journalEditText);
        nextButton = (Button) findViewById(R.id.thoughtNextButton);
        saveButton = (Button) findViewById(R.id.thoughtSaveButton);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        Log.w(TAG, "Update worksheet " + updateWorksheet);

        if (updateWorksheet != null) {
            UpdateUserWorksheet();
            saveButton.setText("Update");
            nextButton.setText("Cancel");

        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(updateWorksheet == null){
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

    public void UpdateUserWorksheet() {
        // Write a message to the database
        DatabaseReference myRef = mDatabase.child("worksheet").child(mAuth.getCurrentUser().getUid()).child(updateWorksheet).child("thought");
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count = 0;

                for (DataSnapshot ss : snapshot.getChildren()) {
                    String thought = (String) ss.getValue();
                        if(count == 0){
                            thoughtEt.setText(thought);
                        }else if(count == 1){
                            journalEt.setText(thought );
                            break;
                        }
                        count++;


//                    if (map.containsKey("thought")) {
//
//                        thoughtEt.setText(map.get("event"));
//                        journalEt.setText(map.get("journal"));
//                        break;
//
//                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });
    }

    public void save() {
        if (updateWorksheet != null) {
            TriggeringEvent event = new TriggeringEvent(thoughtEt.getText().toString(), journalEt.getText().toString(), "123");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("thought", event);
            mDatabase.child("worksheet").child(mAuth.getCurrentUser().getUid()).child(updateWorksheet).updateChildren(map);
        }else{
            TriggeringEvent thought = new TriggeringEvent(thoughtEt.getText().toString(), journalEt.getText().toString(), "123");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("thought", thought);
            mDatabase.child("worksheet").child(mAuth.getCurrentUser().getUid()).child(worksheetId).updateChildren(map);

        }

    }

    public void next() {
        if(updateWorksheet != null) {
            Intent intent = new Intent(WorksheetThoughtActivity.this, MainActivity.class);
            startActivity(intent);


        } else{
            Intent intent = new Intent(WorksheetThoughtActivity.this, WorksheetDistortionsActivity.class);
            intent.putExtra("worksheetId", worksheetId);
            startActivity(intent);

        }
    }
}