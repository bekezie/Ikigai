package edu.neu.ikigai;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.neu.ikigai.models.TriggeringEvent;

public class WorksheetDistortionsActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private ArrayList<String> distortions;
    private String worksheetId;

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
    }

    //Todo: perhaps set onClick listener to avoid passing view unnecessarily
    public void onDistortionClicked(View view) {
        CheckBox checkbox= (CheckBox) view;
        boolean checked = checkbox.isChecked();
        String name = checkbox.getText().toString();
        if (checked)
            distortions.add(name);
        else
            distortions.remove(name);
    }

    public void onDistortionSave(View view) {
        Map<String, Object> map = new HashMap<>();
        map.put("distortions", distortions);
        mDatabase.child("worksheet").child("austin").child(worksheetId).updateChildren(map);
    }

    public void onDistortionNext(View view) {
        onDistortionSave(view);
        Intent intent = new Intent(this, WorksheetReasonableThoughtActivity.class);
        intent.putExtra("worksheetId", worksheetId);
        startActivity(intent);
    }

}
