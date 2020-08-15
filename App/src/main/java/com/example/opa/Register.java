package com.example.opa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    private EditText regisemail, regisPassword;
    private FirebaseAuth mAuth;
    private ProgressBar regisProgress;
    private Button btnRegis, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        regisemail = findViewById(R.id.EmailRegister);
        regisPassword =  findViewById(R.id.passwordRegister);
        regisProgress = findViewById(R.id.progressRegister);
        btnRegis = findViewById(R.id.ButtonRegister);
        btnLogin = findViewById(R.id.Buttonlgn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });

        regisProgress.setVisibility(View.GONE);
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = regisemail.getText().toString();
                String password = regisPassword.getText().toString();

                if (username.equals("")) {
                    Toast.makeText(Register.this, "Silahkan masukan username atau email anda",
                            Toast.LENGTH_SHORT).show();
                } else if (password.equals("")) {
                    Toast.makeText(Register.this, "Silahkan masukan password anda",
                            Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(regisemail.getText().toString(), regisPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            regisProgress.setVisibility(View.VISIBLE);
                            if (task.isSuccessful()) {
                                Toast.makeText(Register.this, "Registered successfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Register.this, Login.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Register.this, "Register Failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
