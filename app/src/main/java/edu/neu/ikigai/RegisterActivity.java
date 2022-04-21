package edu.neu.ikigai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button submitBtn;
    private Button loginBtn;
    private EditText et_username;
    private EditText et_password;
    private EditText et_confirmedPass;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private String token;
    private String user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_username = (EditText) findViewById(R.id.et_user);
        et_password = (EditText) findViewById(R.id.et_password);
        et_confirmedPass = (EditText) findViewById(R.id.et_confirmed);
        loginBtn = (Button) findViewById(R.id.login);
        submitBtn = (Button) findViewById(R.id.submit);
        mAuth = FirebaseAuth.getInstance();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLogin();
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
        String email = et_username.getText().toString();
        String password = et_password.getText().toString();
        String confirmedPassword = et_confirmedPass.getText().toString();
        if(TextUtils.isEmpty(email)){
            et_username.setError("Email cannot be empty");
        }else if(TextUtils.isEmpty(password)){
            et_password.setError("Password cannot be empty");
        }else if(TextUtils.isEmpty(confirmedPassword)){
            et_confirmedPass.setError("Password cannot be empty");
        } else if(!password.equalsIgnoreCase(confirmedPassword)){
            Toast.makeText(RegisterActivity.this, "Passwords have to match",Toast.LENGTH_LONG).show();
        }else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Sign up Successful", Toast.LENGTH_LONG).show();
                        goToLogin();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration Error: "+ task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        Log.e(TAG,task.getException().getMessage());
                    }
                }
            });

        }

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

    public void  goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        //intent.putExtra("SENDER_USERNAME", user);

        startActivity(intent);
    }


}