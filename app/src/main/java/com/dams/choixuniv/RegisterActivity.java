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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {
    EditText matric,fulln,mail,pw,cfpw;
    Button smatiere,minfo,submit;
    TextView haveaccount;
    String Matricule, FullName, Mail, Password, confirmPassword,Field;
    FirebaseFirestore db;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        init();
        haveaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        smatiere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                smatiere.setText("Science De La Matiere");
                smatiere.getLayoutParams().width=300;
                Field = "SM";
                minfo.setVisibility(View.GONE);
            }
        });
        minfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                minfo.setText("Math Et Informatique");
                minfo.getLayoutParams().width=300;

                Field = "MI";
                smatiere.setVisibility(View.GONE);
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Matricule = matric.getText().toString();
                FullName = fulln.getText().toString();
                Mail = mail.getText().toString().trim();
                Password = pw.getText().toString().trim();
                confirmPassword = cfpw.getText().toString().trim();
                if (TextUtils.isEmpty(Matricule)){
                    matric.setError("Entrez Votre Matricule");
                    Toast.makeText(RegisterActivity.this, "Matricule De Certificat De Scolarité", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(FullName)){
                    fulln.setError("Entrez Votre Nom Et Prénom");
                    Toast.makeText(RegisterActivity.this, "Nom Et Prenom", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(Mail)){
                    mail.setError("Entrez Votre Email");
                    Toast.makeText(RegisterActivity.this, "Email", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(Password)){
                    pw.setError("Entrez Votre Mot De Passe");
                    Toast.makeText(RegisterActivity.this, "Mot De Passe", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(confirmPassword)){
                    cfpw.setError("Confirmation Mot De Passe");
                    Toast.makeText(RegisterActivity.this, "Confirmation Mot De Passe", Toast.LENGTH_SHORT).show();
                }
                if (Password.length()< 8){
                    pw.setError("Password Must Be > 8 Characters");
                    return;
                }
                if (!Password.equals(confirmPassword)){
                    pw.setError("Passwords Are Not Same");
                    cfpw.setError("Passwords Are Not Same");
                }

                else {
                    //TODO : HNA COMPTE TECREEh
                    // 3ANDA 2 TYPE KAYEN SM W MI TSEMA FIHA IF W ELSE
                    //TODO LAZEM F ACTIVITY LJAYA NACCESE DATA W NCHOF WECH KAYEN F MODUJLE
                        mAuth.createUserWithEmailAndPassword(Mail,Password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()){
                                            // HNA TECREA COMPTE LAZEM NSAUVGARDER
                                            // DATA F FIRESTORE WELA REALTIME

                                            CollectionReference dbstudents = db.collection("Students");
                                            Data data = new Data(
                                                    Matricule,
                                                    FullName,
                                                    Mail,
                                                    Field,
                                                    Password);
                                            dbstudents.add(data)
                                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                        @Override
                                                        public void onSuccess(DocumentReference documentReference) {

                                                            Toast.makeText(RegisterActivity.this, "worked", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(RegisterActivity.this, "didnt", Toast.LENGTH_SHORT).show();

                                                }

                                                //startActivity
                                            });
                                            Intent intent2 = new Intent(RegisterActivity.this, Complete.class);
                                            startActivity(intent2);
                                        }else{
                                            Toast.makeText(RegisterActivity.this, "didnt even logged in", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });



                }

            }
        });
    }
    private void init() {
        matric = findViewById(R.id.matricule);
        fulln = findViewById(R.id.full_name);
        mail = findViewById(R.id.register_mail);
        pw = findViewById(R.id.register_pw);
        cfpw = findViewById(R.id.confirm_pw);
        haveaccount = findViewById(R.id.haveit);
        submit = findViewById(R.id.submit);
        smatiere = findViewById(R.id.sm);
        minfo = findViewById(R.id.mi);
    }

}