package edu.neu.ikigai.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import edu.neu.ikigai.MainActivity;
import edu.neu.ikigai.ProfileActivity;
import edu.neu.ikigai.R;
import edu.neu.ikigai.WorksheetEventActivity;
import edu.neu.ikigai.WorksheetThoughtActivity;

public class HomeSummaryFragment extends Fragment {
    private TextView event;
    private String updateWorksheet;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_summary, container, false);
        event = (TextView) view.findViewById(R.id.triggering_event);
        updateWorksheet = "-N15WtJgaMhoCcvT0v5F";
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(getActivity(), WorksheetEventActivity.class);
                intent.putExtra("updateWorksheet", updateWorksheet);
                getActivity().startActivity(intent);

            }
        });
        return view;
    }


}
