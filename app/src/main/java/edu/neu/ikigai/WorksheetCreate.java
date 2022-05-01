package edu.neu.ikigai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.neu.ikigai.models.AutomaticThought;
import edu.neu.ikigai.models.TriggeringEvent;
import edu.neu.ikigai.models.WorkSheet;

public class WorksheetCreate extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private Button addEventBtn;
    private Button addThoughtBtn;
    private String worksheetId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worksheet_create);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        worksheetId = mDatabase.child("worksheet").child("minh").push().getKey();
        WorkSheet ws = new WorkSheet();
        ws.setEvent(new TriggeringEvent("","", ""));
        ws.setThought(new AutomaticThought("", ""));

        mDatabase.child("worksheet").child("minh").child(worksheetId).setValue(ws);

        addEventBtn = (Button) findViewById(R.id.addEventButton);

        addEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                advanceToActivity("event");
            }
        });

        addThoughtBtn = (Button) findViewById(R.id.addThoughtButton);

        addThoughtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                advanceToActivity("thought");
            }
        });

    }

    public void advanceToActivity(String act) {
        switch (act) {
            case ("event"):
                Intent intentEvent = new Intent(this, WorksheetEventActivity.class);
                intentEvent.putExtra("worksheetId", worksheetId);
                startActivity(intentEvent);
                break;
            case ("thought"):
                Intent intentThought = new Intent(this, WorksheetThoughtActivity.class);
                intentThought.putExtra("worksheetId", worksheetId);
                startActivity(intentThought);
                break;

            default:
                break;
        }
    }
}