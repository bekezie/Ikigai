package edu.neu.ikigai.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

import edu.neu.ikigai.LoginActivity;
import edu.neu.ikigai.MainActivity;
import edu.neu.ikigai.ProfileActivity;
import edu.neu.ikigai.R;

public class MoreFragment extends Fragment {

    private Button logoutBtn;
    private Button profileBtn;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_more, container, false);

        mAuth = FirebaseAuth.getInstance();

        profileBtn = (Button) view.findViewById(R.id.profile);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MoreFragment.this.getActivity(), ProfileActivity.class));
            }
        });
        logoutBtn = (Button) view.findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(MoreFragment.this.getActivity(), LoginActivity.class));
            }
        });

        return view;
    }
}
