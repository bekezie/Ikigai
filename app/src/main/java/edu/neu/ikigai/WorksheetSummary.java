package edu.neu.ikigai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class WorksheetSummary extends AppCompatActivity {

    TextView distortions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_summary);
        distortions.setText("Emotional Reasoning\nAll or Nothing Thinking\n...");
    }
}