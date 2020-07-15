package com.dams.choixuniv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    EditText matric,fulln,mail,pw,cfpw;
    Button smatiere,minfo,submit;
    String Matricule, FullName, Mail, Password, confirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        matric = findViewById(R.id.matricule);
        fulln = findViewById(R.id.full_name);
        mail = findViewById(R.id.register_mail);
        pw = findViewById(R.id.register_pw);
        cfpw = findViewById(R.id.confirm_pw);



    }

}