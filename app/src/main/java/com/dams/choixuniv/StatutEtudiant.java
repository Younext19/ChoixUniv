package com.dams.choixuniv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class StatutEtudiant extends AppCompatActivity {

    TextView matr,fname,mai,specia,moy1,moy2,status;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;
    String UserId;
    Button refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statut_etudiant);
        // TODO HNA YJO LES DONNEES TA3 ETUDIANT

        matr = findViewById(R.id.matricule2);
        fname = findViewById(R.id.full_name2);
        mai = findViewById(R.id.mail2);
        specia = findViewById(R.id.favoris);
        moy1 = findViewById(R.id.Moy1);
        moy2 = findViewById(R.id.Moy2);
        status = findViewById(R.id.status);
        refresh = findViewById(R.id.refresh);
        mAuth = FirebaseAuth.getInstance();
        UserId = mAuth.getCurrentUser().getUid();

        mFirestore = FirebaseFirestore.getInstance();

        fname.setText("Nom Et Prénom : "+UserId);
        mFirestore.collection("Students").document(UserId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();

                    String new_matric = documentSnapshot.getString("matricule");
                    String new_fname = documentSnapshot.getString("fullname");
                    String new_mai = documentSnapshot.getString("mail");
                    String new_specia = documentSnapshot.getString("field");
                    String new_moy1 = documentSnapshot.getString("moy1");
                    String new_moy2 = documentSnapshot.getString("moy2");
                    //new_status = documentSnapshot.getString("");

                    matr.setText("Matricule : "+new_matric);
                    fname.setText("Nom Et Prénom : "+new_fname);
                    mai.setText("Email : "+new_mai);
                    specia.setText("Spécialité Favorable : "+new_specia);
                    if(new_specia == "Math" || new_specia == "Info"){
                        moy1.setText("Moyenne Math : "+new_moy1);
                        moy2.setText("Moyenne Info : "+new_moy2);

                    }else{
                        moy1.setText("Moyenne Physique : "+new_moy1);
                        moy2.setText("Moyenne Chimie : "+new_moy2);
                    }
                } else {
                    Toast.makeText(StatutEtudiant.this, "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
         /*

            }
        });*/
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatutEtudiant.this, StatutEtudiant.class);
                startActivity(intent);
            }
        });


    }
}