package edu.neu.ikigai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView fullName;
    private EditText email;
    private EditText password;
    private FirebaseAuth mAuth;
    //private DatabaseReference mDatabase;
    private Button et_emailBtn;
    private Button et_passwordBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        // Connect with Firebase
        //mDatabase = FirebaseDatabase.getInstance().getReference();
        // Connect with Firebase Authentication
        mAuth = FirebaseAuth.getInstance();
        //fullName = (TextView) findViewById(R.id.fullname);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        et_emailBtn = (Button) findViewById(R.id.et_email);
        et_passwordBtn = (Button) findViewById(R.id.et_password);

        et_emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateEmail();

            }
        });
        et_passwordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResetPassword();

            }
        });
       getUserDetails();


    }

    private void UpdateEmail() {

    }

    private void getUserDetails() {
        email.setText(mAuth.getCurrentUser().getEmail());

    }
    public void ResetPassword() {
        String userEmail = email.getText().toString();
        if (TextUtils.isEmpty(userEmail)) {
            email.setError("Email cannot be empty");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            email.setError("Please provide valid email!");
            return;

        }
        mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ProfileActivity.this, "Check your email to reset your password", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(ProfileActivity.this, "Reset Password Error: "+ task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
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
}
