package com.healthheart.jessica.hh.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.healthheart.jessica.hh.Adapter.GlicoseAdapter;
import com.healthheart.jessica.hh.DAO.ConfiguracaoFirebase;
import com.healthheart.jessica.hh.Entidades.Glicose;
import com.healthheart.jessica.hh.Helper.PreferenciasAndroid;
import com.healthheart.jessica.hh.R;

import java.util.ArrayList;

public class HistoricoGlicoseActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<Glicose> adapter;
    private ArrayList<Glicose> glicose;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerGlicose;

    private Button btnVoltarPrinpal;
    private Button btnAddGlicose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_glicose);

        btnVoltarPrinpal = (Button) findViewById(R.id.btnVoltarPrinpal);
        btnAddGlicose = (Button) findViewById(R.id.btnAddGlicose);

        btnVoltarPrinpal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrieTelaPrinc = new Intent(HistoricoGlicoseActivity.this, PrincipalActivity.class);
                startActivity(intentAbrieTelaPrinc);
            }
        });

        btnAddGlicose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbriAddGlico = new Intent(HistoricoGlicoseActivity.this, GlicoseActivity.class);
                startActivity(intentAbriAddGlico);
            }
        });


        glicose = new ArrayList<>();
        listView = (ListView) findViewById(R.id.lvHistGlicose);
        adapter = new GlicoseAdapter(this, glicose);

        listView.setAdapter(adapter);

        //Recuperar contatos do firebase
        PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(HistoricoGlicoseActivity.this);
        String identificadorUsuarioLogado = preferenciasAndroid.getIdentificador();

        firebase = ConfiguracaoFirebase.getFirebase().child("Glicose").child(identificadorUsuarioLogado);

        valueEventListenerGlicose = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                glicose.clear();

                for (DataSnapshot dados : dataSnapshot.getChildren()){
                    Glicose glicoseNova = dados.getValue(Glicose.class);

                    glicose.add(glicoseNova);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

    }

    @Override
    protected void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListenerGlicose);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerGlicose);
    }
}
