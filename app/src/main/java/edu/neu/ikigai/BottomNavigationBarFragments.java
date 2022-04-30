package edu.neu.ikigai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.neu.ikigai.Fragments.EntriesFragment;
import edu.neu.ikigai.Fragments.ExerciseFragment;
import edu.neu.ikigai.Fragments.HomeNoSummaryFragment;
import edu.neu.ikigai.Fragments.HomeSummaryFragment;
import edu.neu.ikigai.Fragments.MoreFragment;

public class BottomNavigationBarFragments extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private String user = "austin"; // HARDCODED @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

    private NavigationBarView.OnItemSelectedListener navListener =
        new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.nav_home:     // Home Fragment
                        selectedFragment = new HomeNoSummaryFragment();
                        break;
                    case R.id.nav_entries:  // Entries Fragment
                        selectedFragment = new EntriesFragment();
                        break;
                    case R.id.nav_exercise: // Exercise Fragment
                        selectedFragment = new ExerciseFragment();
                        break;
                    case R.id.nav_more:     // More Fragment
                        selectedFragment = new MoreFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();

                return true;
            }
        };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_bar_fragments);

        NavigationBarView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(navListener);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Searches the 'history' branch in the database
        mDatabase.child("history").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean hasAlreadyCreatedAnEntryForToday = false;

                // Searches for the user in the 'history' branch
                for (DataSnapshot history_user : snapshot.getChildren()) {
                    // if the user in the 'history' branch is found
                    if (String.valueOf(history_user.getKey()).equalsIgnoreCase(user)) {

                        // Searches for today's date inside the 'history/user' branch
                        for (DataSnapshot history_user_date : history_user.getChildren()) {

                            // if today's date is found inside the 'history/user' branch
                            if (String.valueOf(history_user_date.getKey()).equals(getDate())) {
                                hasAlreadyCreatedAnEntryForToday = true;
                                break;
                            }
                        }
                    }
                }

                // Shows the appropriate home screen once the user has successfully logged in,
                // which depends on whether or not the user has created an entry for today
                if (!hasAlreadyCreatedAnEntryForToday) {
                    // The user has not created an entry for today.
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeNoSummaryFragment()).commit();
                }
                else if (hasAlreadyCreatedAnEntryForToday) {
                    // The user has created an entry for today.
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeSummaryFragment()).commit();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
