package edu.neu.ikigai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import edu.neu.ikigai.models.AutomaticThought;
import edu.neu.ikigai.models.TriggeringEvent;

public class WorksheetThoughtActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_worksheet_thought);

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
    @Override
    protected void  onStart() {
        super.onStart();
        FirebaseUser curruser = mAuth.getCurrentUser();
        if (curruser == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    public void save() {
        AutomaticThought thought = new AutomaticThought(thoughtEt.getText().toString(), journalEt.getText().toString());
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("thought", thought);
        mDatabase.child("userTest").child(mAuth.getCurrentUser().getUid()).child(worksheetId).updateChildren(map);
    }

    public void next() {
        // TODO
    }

}

