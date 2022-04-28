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
    private String worksheetId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worksheet_create);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        worksheetId = mDatabase.child("user").child("minh").push().getKey();
        WorkSheet ws = new WorkSheet();
        ws.setEvent(new TriggeringEvent("","", ""));
        ws.setThought(new AutomaticThought("", ""));

        mDatabase.child("user").child("minh").child(worksheetId).setValue(ws);

        addEventBtn = (Button) findViewById(R.id.addEventButton);

        addEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                advanceToActivity("event");
            }
        });

    }

    public void advanceToActivity(String act) {
        switch (act) {
            // Todo: change back to WorksheetEventActivity.class
            //  WorksheetDistortionsActivity just for testing purposes
            case ("event"):
                Intent intent = new Intent(this, WorksheetDistortionsActivity.class);
                intent.putExtra("worksheetId", worksheetId);
                startActivity(intent);
        }
    }
}