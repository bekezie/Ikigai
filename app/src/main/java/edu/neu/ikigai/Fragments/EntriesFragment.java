package edu.neu.ikigai.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.neu.ikigai.R;

public class EntriesFragment extends Fragment {

    private String user = "austin"; // HARDCODED @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    private DatabaseReference mDatabase;

    @Nullable
    // @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String key = "-N0wZ7olzZ2lP42l6JNK";
        long timestamp = decode(key);

        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date date = new Date(timestamp);
        String test = dateFormat.format(date);
        Log.d("date:", date.toString() + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        Log.d("test", test + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Searches the 'history' branch in the database
        mDatabase.child("history").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // Searches for the user in the 'history' branch
                for (DataSnapshot history_user : snapshot.getChildren()) {
                    // if the user in the 'history' branch is found
                    if (String.valueOf(history_user.getKey()).equalsIgnoreCase(user)) {

                        // Searches for today's date inside the 'history/user' branch
                        for (DataSnapshot history_user_date : history_user.getChildren()) {

                            // if today's date is found inside the 'history/user' branch
                            if (String.valueOf(history_user_date.getKey()).equals(getDate())) {

                                break;
                            }
                        }
                    }
                }

                getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new EntriesFragment()).commit();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return inflater.inflate(R.layout.fragment_entries, container, false);
    }

    public long decode(String id) {
        String PUSH_CHARS = "-0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz";

        id = id.substring(0,8);
        long timestamp = 0;
        for (int i=0; i < id.length(); i++) {
            char c = id.charAt(i);
            timestamp = timestamp * 64 + PUSH_CHARS.indexOf(c);
        }
        return timestamp;
    }

    /**
     * QUESTION: DOES THIS WORK FOR PEOPLE WHO ARE IN DIFFERENT TIME ZONES??????????????????????????
     *
     * Gets the current date in MM-dd-YYYY format.
     * @return the current date.
     */
    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date date = new Date();

        String currentDate = dateFormat.format(date);
        // Toast.makeText(BottomNavigationBarFragments.this,currentDate,Toast.LENGTH_LONG).show();
        return currentDate;
    }
}
