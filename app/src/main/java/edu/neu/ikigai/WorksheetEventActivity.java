package edu.neu.ikigai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.*;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import edu.neu.ikigai.Fragments.HomeNoSummaryFragment;
import edu.neu.ikigai.Fragments.HomeSummaryFragment;
import edu.neu.ikigai.models.TriggeringEvent;
import edu.neu.ikigai.models.WorkSheet;

public class WorksheetEventActivity extends AppCompatActivity {
    private static final String TAG = WorksheetEventActivity.class.getSimpleName();
    private static final int REQUEST_LOCATION = 1;
    private DatabaseReference mDatabase;
    private EditText eventEt;
    private EditText journalEt;
    private ImageButton locationBtn;
    private Button nextButton;
    private Button saveButton;
    private String worksheetId;
    private TextView locationName;
    private FirebaseAuth mAuth;
    //private String updateWorksheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worksheet_event);

        ActivityCompat.requestPermissions( this,
                new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        worksheetId = this.getIntent().getStringExtra("worksheetId");
        //worksheetId = "-N15WtJgaMhoCcvT0v5F";
        //updateWorksheet = "-N15WtJgaMhoCcvT0v5F";
        eventEt = (EditText) findViewById(R.id.eventEditText);
        journalEt = (EditText) findViewById(R.id.journalEditText);
        locationBtn = (ImageButton) findViewById(R.id.locationBtn);
        locationName = (TextView)  findViewById(R.id.locationName);
        nextButton = (Button) findViewById(R.id.eventNextButton);
        saveButton = (Button) findViewById(R.id.eventSaveButton);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        //Log.w(TAG, "Update worksheet "+ updateWorksheet);

//        if(worksheetId != null){
//            SavedWorksheet();
//            saveButton.setText("Update");
//            nextButton.setText("Cancel");
//
//        }
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(worksheetId == null){
                    save();
                }
                next();
            }
        });

        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocation();
            }
        });
    }
    public void SavedWorksheet(){
        // Write a message to the database
        DatabaseReference myRef = mDatabase.child("worksheet").child(mAuth.getCurrentUser().getUid()).child(worksheetId).child("event");
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String event = (String) snapshot.child("event").getValue();
                String journal = (String) snapshot.child("journal").getValue();

                        eventEt.setText(event);

                        journalEt.setText(journal);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });
    }





    @Override
    protected void  onStart() {
        super.onStart();
        FirebaseUser curruser = mAuth.getCurrentUser();
        if (curruser == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    public void save() {
        TriggeringEvent event = new TriggeringEvent(eventEt.getText().toString(), journalEt.getText().toString(), "123" );
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("event", event);
        mDatabase.child("worksheet").child(mAuth.getCurrentUser().getUid()).child(worksheetId).updateChildren(map);
        mDatabase.child("worksheet").child(mAuth.getCurrentUser().getUid()).child(worksheetId).child("event").child("location").setValue(locationName.getText().toString());
    }

    public void next() {
        if(worksheetId == null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, WorksheetThoughtActivity.class);
            intent.putExtra("worksheetId", worksheetId);
            startActivity(intent);
        }

    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                WorksheetEventActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                WorksheetEventActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location locationGPS = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        System.out.println("LOCATION: " + locationGPS);
        if (locationGPS != null) {
            locationName.setText(getAndress(locationGPS.getLatitude(), locationGPS.getLatitude()));
//            locationName.setText("Your Location: " + "\n" + "Latitude: " + String.valueOf(locationGPS.getLatitude()) + "\n" + "Longitude: " + String.valueOf(locationGPS.getLongitude()) );
        } else {
            Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
        }

    }

    private String getAndress(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        String res = "";
        try {
            List<Address> addresses ;
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            res = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
//            String city = addresses.get(0).getLocality();
//            String state = addresses.get(0).getAdminArea();
//            String country = addresses.get(0).getCountryName();
//            String postalCode = addresses.get(0).getPostalCode();
//            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
//            System.out.println("ADRESSS: "  + address + " " + city);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;

    }
}