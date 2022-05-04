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
import java.util.ArrayList;
import java.util.Date;

import edu.neu.ikigai.Fragments.EntriesFragment;
import edu.neu.ikigai.Fragments.ExerciseFragment;
import edu.neu.ikigai.Fragments.HomeNoSummaryFragment;
import edu.neu.ikigai.Fragments.HomeSummaryFragment;
import edu.neu.ikigai.Fragments.MoreFragment;

public class BottomNavigationBarFragments extends AppCompatActivity {

    private int fragmentNumber = 1;

    private NavigationBarView.OnItemSelectedListener navListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.nav_home:     // Home Fragment
                    selectedFragment = new HomeNoSummaryFragment();
                    fragmentNumber = 1;
                    break;
                case R.id.nav_entries:  // Entries Fragment
                    selectedFragment = new EntriesFragment();
                    fragmentNumber = 2;
                    break;
                case R.id.nav_exercise: // Exercise Fragment
                    selectedFragment = new ExerciseFragment();
                    fragmentNumber = 3;
                    break;
                case R.id.nav_more:     // More Fragment
                    selectedFragment = new MoreFragment();
                    fragmentNumber = 4;
                    break;
                default:
                    selectedFragment = new HomeNoSummaryFragment();
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

        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_container, new HomeNoSummaryFragment()).commit();

        // onRestoreInstanceState
        if (savedInstanceState != null) {

            int savedFragmentNumber = savedInstanceState.getInt("fragmentNumber");
            Fragment selectedFragment = null;

            switch (savedFragmentNumber) {
                case 1:     // Home Fragment
                    selectedFragment = new HomeNoSummaryFragment();
                    break;
                case 2:  // Entries Fragment
                    selectedFragment = new EntriesFragment();
                    break;
                case 3: // Exercise Fragment
                    selectedFragment = new ExerciseFragment();
                    break;
                case 4:     // More Fragment
                    selectedFragment = new MoreFragment();
                    break;
                default:
                    selectedFragment = new HomeNoSummaryFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("fragmentNumber", fragmentNumber);
    }
}
