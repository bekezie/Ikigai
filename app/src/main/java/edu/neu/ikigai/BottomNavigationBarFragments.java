package edu.neu.ikigai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;

import edu.neu.ikigai.Fragments.EntriesFragment;
import edu.neu.ikigai.Fragments.ExerciseFragment;
import edu.neu.ikigai.Fragments.HomeNoSummaryFragment;
import edu.neu.ikigai.Fragments.MoreFragment;

public class BottomNavigationBarFragments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_bar_fragments);

        NavigationBarView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(navListener);

        // TODO: Query Firebase to see if the user has already created an entry for the current date:
        // 1) If the user has not created an entry, show the home screen with no summary.
        // 2) If the user has created an entry, show the home screen with summary.

        // Shows the home screen once the user has successfully logged in.
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeNoSummaryFragment()).commit();
    }

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
}
