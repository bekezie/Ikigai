package edu.neu.ikigai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText et_username;
    private EditText et_password;
    private Button registerBtn;
    private Button submitBtn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registerBtn = (Button) findViewById(R.id.register);
        submitBtn = (Button) findViewById(R.id.submit);
        et_username = (EditText) findViewById(R.id.et_user);
        et_password = (EditText) findViewById(R.id.et_password);
        mAuth = FirebaseAuth.getInstance();
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();

            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getToken();
                login();
            }
        });
    }

    public void login() {
        String email = et_username.getText().toString();
        String password = et_password.getText().toString();
        if (TextUtils.isEmpty(email)) {
            et_username.setError("Email cannot be empty");
        } else if (TextUtils.isEmpty(password)) {
            et_password.setError("Password cannot be empty");
        } else {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Login Successful",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));

                    }else{
                        Toast.makeText(LoginActivity.this, "Login  Error",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
    public void register(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}