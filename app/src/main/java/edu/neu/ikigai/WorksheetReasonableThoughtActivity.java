package edu.neu.ikigai;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import edu.neu.ikigai.models.Thought;

public class WorksheetReasonableThoughtActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private EditText thoughtEt;
    private EditText journalEt;
    private Button nextButton;
    private Button saveButton;
    private String worksheetId;
    private FirebaseAuth mAuth;

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
    }

    public void save() {
        Thought thought = new Thought(thoughtEt.getText().toString(), journalEt.getText().toString());
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("reasonableThought", thought);
        mDatabase.child("worksheet").child(mAuth.getCurrentUser().getUid()).child(worksheetId).updateChildren(map);
    }

    public void next() {
        // TODO: temporarily going straight to distortions instead of emotions
        Intent intent = new Intent(this, WorksheetSummary.class);
        intent.putExtra("worksheetId", worksheetId);
        startActivity(intent);
    }
}
