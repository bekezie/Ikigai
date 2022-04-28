package edu.neu.ikigai;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class WorksheetDistortionsActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private ArrayList<String> distortions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worksheet_distortions);
        // Todo: populate array list with data from database
        //  or create a new array list
        //  set checked to true for checked boxes when saved data is being used
        distortions = new ArrayList<>();
    }

    public void onDistortionClicked(View view) {
        CheckBox checkbox= (CheckBox) view;
        boolean checked = checkbox.isChecked();
        String name = checkbox.getText().toString();
        if (checked)
            distortions.add(name);
        else
            distortions.remove(name);
    }
}
