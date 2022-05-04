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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UpdateEmail extends AppCompatActivity {
    private static final String TAG = UpdateEmail.class.getSimpleName();
    private TextView et_email;
    private EditText et_newEmail;
    private EditText et_password;
    private Button updateBtn;
    private Button cancelBtn;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_email);
        updateBtn = (Button) findViewById(R.id.updateBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);
        et_email = (TextView) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        et_newEmail = (EditText) findViewById(R.id.et_newEmail);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mAuth = FirebaseAuth.getInstance();
        et_email.setText(mAuth.getCurrentUser().getEmail());
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateEmail.this, ProfileActivity.class));
            }

        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateUserEmail();
            }

        });
    }

    private void UpdateUserEmail() {
        String password = et_password.getText().toString();
        String newEmail = et_newEmail.getText().toString();
        String email = et_email.getText().toString();
//        if(TextUtils.isEmpty(email)){
//            et_fullName.setError("Users name cannot be empty!");
//            return;
//
//        }


        if (TextUtils.isEmpty(newEmail)) {
            et_newEmail.setError("Email cannot be empty!");
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(newEmail).matches()) {
            et_newEmail.setError("Please provide valid email!");
            return;

        }
        if (TextUtils.isEmpty(password)) {
            et_password.setError("Password cannot be empty!");
            return;

        }

        progressBar.setVisibility(View.VISIBLE);
        FirebaseUser user = mAuth.getCurrentUser();
        // Get auth credentials from the user for re-authentication
        AuthCredential credential = EmailAuthProvider
                .getCredential(email, password); // Current Login Credentials \\
        // Prompt the user to re-provide their sign-in credentials
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d(TAG, "User re-authenticated.");
                        //Now change your email address \\
                        //----------------Code for Changing Email Address----------\\
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        user.updateEmail(newEmail)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Log.d(TAG, "User email address updated.");
                                            Toast.makeText(UpdateEmail.this, "User email address updated", Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);
                                            startActivity(new Intent(UpdateEmail.this, ProfileActivity.class));
                                        }else {
                                            Toast.makeText(UpdateEmail.this, " Email address updated Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    }
                                });
                        //----------------------------------------------------------\\
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