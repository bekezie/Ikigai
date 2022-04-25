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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.security.auth.login.LoginException;

import edu.neu.ikigai.models.User;
public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button submitBtn;
    private TextView loginBtn;
    private EditText et_fullName;
    private EditText et_email;
    private EditText et_password;
    private EditText et_confirmedPass;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private String token;
    private String user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Connect with Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // Connect with Firebase Authentication
        mAuth = FirebaseAuth.getInstance();
        et_fullName = (EditText) findViewById(R.id.et_fullName);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        et_confirmedPass = (EditText) findViewById(R.id.et_confirmed);
        loginBtn = (TextView) findViewById(R.id.login);
        submitBtn = (Button) findViewById(R.id.submit);
        //progressBar = (ProgressBar) findViewById(R.id.progress_bar);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getToken();
                register();
            }
        });
    }

    public void register() {
        // Connect with firebase
        String fullName  =  et_fullName.getText().toString();
        String password = et_password.getText().toString();
        String confirmedPassword = et_confirmedPass.getText().toString();
        String email = et_email.getText().toString();
        if(TextUtils.isEmpty(email)){
            et_fullName.setError("Users name cannot be empty!");
            return;

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            et_email.setError("Please provide valid email!");
            return;

        }
        if(TextUtils.isEmpty(email)){
            et_password.setError("Email cannot be empty!");
            return;

        }
        if(TextUtils.isEmpty(password)){
            et_password.setError("Password cannot be empty!");
            return;

        }
        if(TextUtils.isEmpty(confirmedPassword)){
            et_confirmedPass.setError("Password cannot be empty!");
            return;
        }
        if(!password.equalsIgnoreCase(confirmedPassword)){
            Toast.makeText(RegisterActivity.this, "Passwords have to match",Toast.LENGTH_LONG).show();
            return;

        }
        if(password.length() < 6){
            et_password.setError("Min password length should be 6 characters!");
           return;
        }
        if(confirmedPassword.length() < 6){
            et_confirmedPass.setError("Min password length should be 6 characters!");
           return;
        }

        //progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    User user = new User(fullName, email);
                    mDatabase.child("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "Sign up successful", Toast.LENGTH_LONG).show();
                                        //progressBar.setVisibility(View.GONE);
                                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                    }else{
                                        Toast.makeText(RegisterActivity.this, "Registration Database Error: "+ task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                        //progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });

                } else {
                    Toast.makeText(RegisterActivity.this, "Registration Error: "+ task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    Log.e(TAG,task.getException().getMessage());
                }
            }
        });



//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        mDatabase.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                boolean isValid = true;
//                for (DataSnapshot u : snapshot.getChildren()) {
//                    if (String.valueOf(u.getKey()).equalsIgnoreCase(et_username.getText().toString())) {
//                        Toast.makeText(Register.this,"invalid username",Toast.LENGTH_LONG).show();
//                        isValid = false;
//                        break;
//                    }
//                }
//                if (isValid && et_password.getText().toString().equalsIgnoreCase(et_confirmedPass.getText().toString())) {
//                    user = et_username.getText().toString();
//                    //checks if token needs to be updated
//                    mDatabase.child("user").child(user).push().setValue(et_password.getText().toString());
//                    //updateToken(snapshot);
//                    Toast.makeText(Register.this,"Login Successful",Toast.LENGTH_LONG).show();
//                    getHome();
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }

//    public void updateToken(DataSnapshot dataSnapshot) {
//        mDatabase.child("user")
//                .child(user)
//                .runTransaction(new Transaction.Handler() {
//                    @Override
//                    public Transaction.Result doTransaction(MutableData mutableData) {
//
//                        String oldToken = mutableData.getValue(String.class);
//                        if (!oldToken.isEmpty()) {
//                            return Transaction.success(mutableData);
//                        }
//
//                        mutableData.setValue(token);
//
//
//                        return Transaction.success(mutableData);
//                    }
//
//                    @Override
//                    public void onComplete(DatabaseError databaseError, boolean b,
//                                           DataSnapshot dataSnapshot) {
//                        // Transaction completed
//                        Log.d(TAG, "postTransaction:onComplete:" + databaseError);
//                        Toast.makeText(getApplicationContext()
//                                , "DBError: " + databaseError, Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
//    public void getToken(){
//        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
//            @Override
//            public void onComplete(@NonNull Task<String> task) {
//                if(!task.isSuccessful()){
//                    Log.e(TAG,"Failed to get the token");
//                    return;
//                }
//                token = task.getResult();
//                Log.d(TAG,"Token : "+token);
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.e(TAG,"Failed to get the token : "+e.getLocalizedMessage());
//            }
//        });
//    }


}