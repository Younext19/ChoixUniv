package com.dams.choixuniv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Admin extends AppCompatActivity {
    private static final String TAG = "tag";

    FirebaseFirestore db;
    FirebaseAuth mAuth;
    String id;
    String s1[];
    int i=1;
    Button valid, refus;
    TextView matr,fname,mai,specia,moy1,moy2,status;
    RecyclerView recyclerView;
    String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        matr = findViewById(R.id.matricule2);
        fname = findViewById(R.id.full_name2);
        mai = findViewById(R.id.mail2);
        specia = findViewById(R.id.favoris);
        moy1 = findViewById(R.id.Moy1);
        moy2 = findViewById(R.id.Moy2);
        status = findViewById(R.id.status);
        valid = findViewById(R.id.valider);
        refus = findViewById(R.id.refuser);

        db = FirebaseFirestore.getInstance();

        db.collection("Students").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<String> list = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        list.add(document.getId());
                    }
                    Log.d(TAG, list.toString());

                    int size = list.size();
                    while(i<=size){
                        //ila kan i zed
                        if (a=="hi"){
                            a = "b";
                            String str = list.get(i);
                            db.collection("Students").document(str).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
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
                                        valid.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                // TODO : HNA F LA BDD TERSEL VALIDe FSTATUT W TBEDEL LUSER

                                                a ="hi";
                                                i++;
                                            }
                                        });

                                        refus.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                // TODO : HNA F LA BDD TERSEL REFUS FSTATUT W TBEDEL LUSER
                                                a="hi";
                                                i++;
                                            }
                                        });


                                    } else {
                                        Toast.makeText(Admin.this, "error", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                        }
                    }


                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });



    }
}