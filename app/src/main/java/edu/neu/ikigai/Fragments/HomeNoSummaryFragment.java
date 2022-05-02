package edu.neu.ikigai.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.neu.ikigai.R;
import edu.neu.ikigai.WorksheetCreate;

public class HomeNoSummaryFragment extends Fragment {

    private Button createWorksheetStartButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_no_summary, container, false);

        createWorksheetStartButton = view.findViewById(R.id.fragment_home_start_button);
        createWorksheetStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeNoSummaryFragment.this.getActivity(), WorksheetCreate.class));
            }
        });
        
        return view;
    }
}
