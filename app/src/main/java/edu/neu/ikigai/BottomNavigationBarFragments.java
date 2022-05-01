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

    private NavigationBarView.OnItemSelectedListener navListener = new NavigationBarView.OnItemSelectedListener() {
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

        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_container, new HomeNoSummaryFragment()).commit();
    }

}
