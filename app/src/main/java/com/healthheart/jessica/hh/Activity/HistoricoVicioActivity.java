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
import com.healthheart.jessica.hh.Adapter.VicioAdapter;
import com.healthheart.jessica.hh.DAO.ConfiguracaoFirebase;
import com.healthheart.jessica.hh.Entidades.Vicios;
import com.healthheart.jessica.hh.Helper.PreferenciasAndroid;
import com.healthheart.jessica.hh.R;

import java.util.ArrayList;

public class HistoricoVicioActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<Vicios> adapter;
    private ArrayList<Vicios> vicios;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerVicios;

    private Button btnAddVicios;
    private Button btnVoltarPrinpalSau;

    //dados usario logado
    private  String idUsuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_vicio);

        //Voltar Tela principal
        btnVoltarPrinpalSau = (Button) findViewById(R.id.btnVoltarPrinpalSau);
        btnVoltarPrinpalSau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarTelaPrinci = new Intent(HistoricoVicioActivity.this, SaudeActivity.class);
                startActivity(intentVoltarTelaPrinci);
            }
        });

        //idUsuarioLogado = preferenciasAndroid.getIdentificador();

        btnAddVicios = (Button) findViewById(R.id.btnAddVicios);
        btnAddVicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarAddVicio = new Intent(HistoricoVicioActivity.this, VicioActivity.class);
                startActivity(intentVoltarAddVicio);
            }
        });

        /*

        //Recuperar contatos do firebase
        PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(HistoricoMedicamentosActivity.this);
        String identificadorUsuarioLogado = preferenciasAndroid.getIdentificador();

        firebase = ConfiguracaoFirebase.getFirebase()
                .child("Medicamentos").child(identificadorUsuarioLogado);*/

        vicios = new ArrayList<>();
        listView = (ListView) findViewById(R.id.lvHistVicio);
        adapter = new VicioAdapter(this, vicios);

        listView.setAdapter(adapter);

        //Recuperar contatos do firebase
        PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(HistoricoVicioActivity.this);
        String identificadorUsuarioLogado = preferenciasAndroid.getIdentificador();


        firebase = ConfiguracaoFirebase.getFirebase()
                .child("Vicios").child(identificadorUsuarioLogado);

        // valueEventListenerMedicamento = new ValueEventListener()
        valueEventListenerVicios = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                vicios.clear();
                for (DataSnapshot dados : dataSnapshot.getChildren()){
                    Vicios viciosNovo = dados.getValue(Vicios.class);
                    vicios.add(viciosNovo);
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
        firebase.removeEventListener(valueEventListenerVicios);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerVicios);
    }


}
