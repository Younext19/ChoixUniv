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
    String Matricule, FullName, Mail, Password, confirmPassword;
    FirebaseFirestore db;
    private FirebaseAuth mAuth;
    float i,j;
    String field, moy1, moy2;

    String field_final;
    TextView lwl, zawja;
    Button somatiere,moinfo,spec1,spec2;
    EditText first,second;
    Button app;
    private String UserId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // INITIALISATION
        somatiere = findViewById(R.id.sm);
        moinfo = findViewById(R.id.mi);
        spec1 = findViewById(R.id.spe1);
        spec2 = findViewById(R.id.spe2);
        first = findViewById(R.id.moyinfo);
        second = findViewById(R.id.moymath);
        lwl = findViewById(R.id.txt3);
        zawja = findViewById(R.id.txt4);
        UserId = mAuth.getCurrentUser().getUid();

        init();
        haveaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        somatiere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                somatiere.setText("Science De La Matiere");
                field = "SM";
                spec1.setText("Chimie");
                spec2.setText("Physique");
                lwl.setText("Moyenne Unité Chimie");
                zawja.setText("Moyenne Unité Physique");
                moinfo.setVisibility(View.GONE);
                // HNA SWALEH TA3 SM
                // TODO NGET EDITTEXT CHHAL NERSELEHOM L BDD


                spec1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        spec2.setVisibility(View.GONE);
                        // HNA SIYED KHTAR CHIMIE
                        field = "Chimie";
                    }
                });
                spec2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        spec1.setVisibility(View.GONE);
                        field = "Physique";
                    }
                });


            }
        });
        moinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moinfo.setText("Math Et Informatique");
                field = "MI";
                somatiere.setVisibility(View.GONE);
                spec1.setText("Math");
                spec2.setText("Info");
                lwl.setText("Moyenne Unité Math");
                zawja.setText("Moyenne Unité Info");

                spec1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        spec2.setVisibility(View.GONE);
                        field = "Math";
                    }
                });
                spec2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        spec1.setVisibility(View.GONE);
                        field = "Info";
                    }
                });

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
                moy1 = first.getText().toString();
                moy2 = second.getText().toString();
                i =  Float.parseFloat("moy1");
                j = Float.parseFloat("moy2");
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
                if(i>20 || i<0 || j>20 || j<0 ){
                    first.setError("Une Des Moyenne Est Incorrecte");
                    second.setError("Une Des Moyenne Est Incorrecte");
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

                                            System.out.println("hadi" +field+" whadi "+moy1+" whadi "+moy2);
                                            CollectionReference dbstudents = db.collection("Students");
                                            Data data = new Data(
                                                    Matricule,
                                                    FullName,
                                                    Mail,
                                                    Password,
                                                    field,
                                                    moy1,
                                                    moy2);
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