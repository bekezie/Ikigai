package edu.neu.ikigai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import edu.neu.ikigai.models.TriggeringEvent;
import edu.neu.ikigai.models.WorkSheet;

public class WorksheetEventActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private EditText eventEt;
    private EditText journalEt;
    private Button nextButton;
    private Button saveButton;
    private String worksheetId;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worksheet_event);

        worksheetId = this.getIntent().getStringExtra("worksheetId");
        eventEt = (EditText) findViewById(R.id.eventEditText);
        journalEt = (EditText) findViewById(R.id.journalEditText);
        nextButton = (Button) findViewById(R.id.eventNextButton);
        saveButton = (Button) findViewById(R.id.eventSaveButton);
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
        TriggeringEvent event = new TriggeringEvent(eventEt.getText().toString(), journalEt.getText().toString(), "123" );
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("event", event);
        mDatabase.child("userTest").child(mAuth.getCurrentUser().getUid()).child(worksheetId).updateChildren(map);
    }

    public void next() {
        Intent intent = new Intent(this, WorksheetThoughtActivity.class);
        intent.putExtra("worksheetId", worksheetId);
        startActivity(intent);
    }
}
