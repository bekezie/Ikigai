package edu.neu.ikigai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;




import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button submitBtn;
    private EditText et_username;
    private DatabaseReference mDatabase;
    private String token;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_username = (EditText) findViewById(R.id.et_user);
        submitBtn = (Button) findViewById(R.id.submit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getToken();
                login();
            }
        });
    }

    public void login() {
        // Connect with firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
//        // Update the score in realtime
//        mDatabase.child("user").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                Log.d(TAG,"CHILD ADDEDDDDDDDDD");
//                Auth(snapshot);
//                //Log.e(TAG, "onChildAdded: dataSnapshot = " + snapshot.getValue().toString());
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        mDatabase.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean isValid = false;
                for (DataSnapshot u : snapshot.getChildren()) {
                    if (String.valueOf(u.getKey()).equalsIgnoreCase(et_username.getText().toString())) {
                        user = et_username.getText().toString();
                        //checks if token needs to be updated
                        updateToken(snapshot);
                        Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_LONG).show();
                        isValid = true;
                        getHomeScreenActivity();
                        // getWorksheetSummary();
                    }
                }
                if (!isValid) {
                    Toast.makeText(MainActivity.this,"invalid username",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updateToken(DataSnapshot dataSnapshot) {
        mDatabase.child("user")
                .child(user)
                .runTransaction(new Transaction.Handler() {
                    @Override
                    public Transaction.Result doTransaction(MutableData mutableData) {

                        String oldToken = mutableData.getValue(String.class);
                        Log.d(TAG, "oldToken is: @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + oldToken);
                        if (!oldToken.isEmpty()) {
                            return Transaction.success(mutableData);
                        }

                        mutableData.setValue(token);

                        return Transaction.success(mutableData);
                    }

                    @Override
                    public void onComplete(DatabaseError databaseError, boolean b,
                                           DataSnapshot dataSnapshot) {
                        // Transaction completed
                        Log.d(TAG, "postTransaction:onComplete:" + databaseError);
                        Toast.makeText(getApplicationContext()
                                , "DBError: " + databaseError, Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void getToken(){
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if(!task.isSuccessful()){
                    Log.e(TAG,"Failed to get the token");
                    return;
                }
                token = task.getResult();
                Log.d(TAG,"Token : "+token);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG,"Failed to get the token : "+e.getLocalizedMessage());
            }
        });
    }

//        public void Auth(DataSnapshot dataSnapshot) {
//            Log.e(TAG,"key: "+ dataSnapshot.getKey());
//            Log.e(TAG,"input: "+ et_username.getText().toString());
//            if (dataSnapshot.getKey().equalsIgnoreCase(et_username.getText().toString())) {
//                user = et_username.getText().toString();
//                //checks if token needs to be updated
//                updateToken(dataSnapshot);
//                Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_LONG).show();
//                getMessagePage();
//
//            } else {
//                //toast invalid username
//                Toast.makeText(MainActivity.this,"invalid username",Toast.LENGTH_LONG).show();
//            }
//
//    };

    public void getHomeScreenActivity() {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }

    public void getWorksheetSummary() {
        Intent intent = new Intent(this, WorksheetSummary.class);
        intent.putExtra("SENDER_USERNAME", user);

        startActivity(intent);
    }




}