package edu.neu.ikigai.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import edu.neu.ikigai.MainActivity;
import edu.neu.ikigai.ProfileActivity;
import edu.neu.ikigai.R;
import edu.neu.ikigai.WorksheetCreate;
import edu.neu.ikigai.WorksheetDistortionsActivity;
import edu.neu.ikigai.WorksheetEventActivity;
import edu.neu.ikigai.WorksheetThoughtActivity;

public class HomeSummaryFragment extends Fragment {
    private TextView event;
    private TextView cognitive_distortions;
    private TextView automatic_thought;
    private TextView reasonable_thought;
    private String worksheetId;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_summary, container, false);
        worksheetId = "-N15WtJgaMhoCcvT0v5F";
        automatic_thought = (TextView) view.findViewById(R.id.automatic);
        automatic_thought.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(HomeSummaryFragment.this.getActivity(), WorksheetEventActivity.class);
                intent.putExtra("worksheetId", worksheetId);
                getActivity().startActivity(intent);

            }
        });
        cognitive_distortions = (TextView) view.findViewById(R.id.cognitive);
        cognitive_distortions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(HomeSummaryFragment.this.getActivity(), WorksheetDistortionsActivity.class);
                intent.putExtra("worksheetId", worksheetId);
                getActivity().startActivity(intent);

            }
        });
        event = (TextView) view.findViewById(R.id.event);
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(HomeSummaryFragment.this.getActivity(), WorksheetEventActivity.class);
                intent.putExtra("worksheetId", worksheetId);
                getActivity().startActivity(intent);

            }
        });
        return view;
    }


}
