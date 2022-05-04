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
    private Bundle currentState;

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
        currentState = savedInstanceState;
        init(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        init(currentState);
    }

    public void save() {
        Thought thought = new Thought(thoughtEt.getText().toString(), journalEt.getText().toString());
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("reasonableThought", thought);
        // Todo: uncomment
        mDatabase.child("worksheet").child(mAuth.getCurrentUser().getUid()).child(worksheetId).updateChildren(map);
    }

    public void next() {
        save();
        Intent intent = new Intent(this, BottomNavigationBarFragments.class);
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
        DatabaseReference ref =  mDatabase.child("worksheet").child(mAuth.getCurrentUser().getUid()).child(sheetId).child("reasonableThought");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String thought = (String) snapshot.child("thought").getValue();
                String journal = (String) snapshot.child("journal").getValue();
                thoughtEt.setText(thought);
                journalEt.setText(journal);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(CLASS_TAG, "saved data retrieval error", error.toException());
            }
        });
    }

}
