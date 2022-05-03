package edu.neu.ikigai;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import edu.neu.ikigai.models.Thought;
import edu.neu.ikigai.models.WorkSheet;

public class WorksheetReasonableThoughtActivity extends AppCompatActivity {
    private static final String CLASS_TAG = WorksheetReasonableThoughtActivity.class.getSimpleName();
    private DatabaseReference mDatabase;
    private EditText thoughtEt;
    private EditText journalEt;
    private Button nextButton;
    private Button saveButton;
    private String worksheetId;
    private FirebaseAuth mAuth;
    private WorkSheet ws;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worksheet_reasonable);

        worksheetId = this.getIntent().getStringExtra("worksheetId");
        thoughtEt = (EditText) findViewById(R.id.thoughtEditText);
        journalEt = (EditText) findViewById(R.id.journalEditText);
        nextButton = (Button) findViewById(R.id.thoughtNextButton);
        saveButton = (Button) findViewById(R.id.thoughtSaveButton);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
                next();
            }
        });

        init(savedInstanceState);
        // if saved instance state == null
        // pull from db
        // else use what's in saved instance state
    }

    // Todo: might not be necessary for edit text views
//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        outState.putString("reasonThoughtText", thoughtEt.getText().toString());
//        outState.putString("reasonJournalText", journalEt.getText().toString());
//        super.onSaveInstanceState(outState);
//    }

    public void save() {
        Thought thought = new Thought(thoughtEt.getText().toString(), journalEt.getText().toString());
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("reasonableThought", thought);
        // Todo: uncomment
        mDatabase.child("worksheet").child(mAuth.getCurrentUser().getUid()).child(worksheetId).updateChildren(map);
        //mDatabase.child("worksheet").child("ndabe").child(worksheetId).updateChildren(map);
    }

    public void next() {
        // TODO: temporarily going straight to distortions instead of emotions
        Intent intent = new Intent(this, WorksheetSummary.class);
        intent.putExtra("worksheetId", worksheetId);
        startActivity(intent);
    }

    //maintain state in the event of a rotation
    //pull data from database everytime a rotation occurs?
    private void init(Bundle savedInstanceState) {
        initialItemData(savedInstanceState);

    }

//    //maintain state in the event of a rotation
//    //pull data from database everytime a rotation occurs?
//    private void init(Bundle savedInstanceState) {
//        initialItemData(savedInstanceState);
//    }

    private void initialItemData(Bundle savedInstanceState) {
        // there might be an issue of it pulling from the database whenever screen is rotated
        // but the person is editing that text
        // check to see what is in savedInstanceState by default
        // otherwise. Could check what's in database vs. what's in edit text view to see what to do?

        // if there is saved instance state
        if (savedInstanceState == null) {
            getSaved(worksheetId);
        }
        // set edit text values with that state
        // else no saved instance state (maybe else-if)
        // pull from db using worksheet id
        // if worksheet.reasonThought not empty
        // set edit text values with data from db
    }

//    private void initialItemData(Bundle savedInstanceState) {
//        // if there is saved instance state
//        if (savedInstanceState != null) {
//            if (savedInstanceState.containsKey("reasonThoughtText")) {
//                thoughtEt.setText(savedInstanceState.getString("reasonThoughtText"));
//            }
//            if (savedInstanceState.containsKey("reasonJournalText")) {
//                thoughtEt.setText(savedInstanceState.getString("reasonJournalText"));
//            }
//        }
//        // set edit text values with that state
//        // else no saved instance state (maybe else-if)
//        // pull from db using worksheet id
//        // if worksheet.reasonThought not empty
//        // set edit text values with data from db
//    }

    public void getSaved(String sheetId) {
        // Todo: change back
        DatabaseReference ref =  mDatabase.child("worksheet").child(mAuth.getCurrentUser().getUid()).child(sheetId);
       // DatabaseReference ref = mDatabase.child("worksheet").child("ndabe").child(worksheetId);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ws = snapshot.getValue(WorkSheet.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(CLASS_TAG, "saved data retrieval error", error.toException());
            }
        });
    }
}
