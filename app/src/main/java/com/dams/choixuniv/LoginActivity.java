package com.dams.choixuniv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText mail,pw;
    Button submite;
    TextView forget,create;
    FirebaseAuth mAuth;
    String email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mail = findViewById(R.id.loginmail);
        pw = findViewById(R.id.login_password);
        submite = findViewById(R.id.submit);
        forget = findViewById(R.id.forget_pw);
        create = findViewById(R.id.register_acc);
        mAuth = FirebaseAuth.getInstance();

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        submite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : HNA LI YETCONNECTA
                email = mail.getText().toString().trim();
                password = pw.getText().toString().trim();
                if (TextUtils.isEmpty(email)){
                    mail.setError("Email Is Required");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    pw.setError("Password Is Required");
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            Intent intent2 = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent2);
                        }
                        else {

                            Toast.makeText(LoginActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}