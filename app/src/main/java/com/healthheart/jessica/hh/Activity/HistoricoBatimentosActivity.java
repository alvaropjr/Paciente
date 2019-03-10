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
import com.healthheart.jessica.hh.Adapter.BatimentosAdapter;
import com.healthheart.jessica.hh.DAO.ConfiguracaoFirebase;
import com.healthheart.jessica.hh.Entidades.Batimentos;
import com.healthheart.jessica.hh.Entidades.Medicamento;
import com.healthheart.jessica.hh.Helper.PreferenciasAndroid;
import com.healthheart.jessica.hh.R;


import java.util.ArrayList;

public class HistoricoBatimentosActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<Batimentos> adapter;
    private ArrayList<Batimentos> batimentos;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerBatimentos;

    private Button btnAddBatimentos;
    private Button btnVoltarPrinpal;

    //dados usario logado
    private  String idUsuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_batimentos);

        //Voltar Tela principal
        btnVoltarPrinpal = (Button) findViewById(R.id.btnVoltarPrinpal);
        btnVoltarPrinpal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarTelaPrinci = new Intent(HistoricoBatimentosActivity.this, PrincipalActivity.class);
                startActivity(intentVoltarTelaPrinci);
            }
        });

        //Voltar add Batimentos
        btnAddBatimentos = (Button) findViewById(R.id.btnAddBatimentos);
        btnAddBatimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarAddBati= new Intent(HistoricoBatimentosActivity.this, BatimentosActivity.class);
                startActivity(intentVoltarAddBati);
            }
        });

        batimentos = new ArrayList<>();
        listView = (ListView) findViewById(R.id.lvHistBatimentos);
        adapter = new BatimentosAdapter(this, batimentos);
        listView.setAdapter(adapter);

        //Recuperar contatos do firebase
        PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(HistoricoBatimentosActivity.this);
        String identificadorUsuarioLogado = preferenciasAndroid.getIdentificador();

        firebase = ConfiguracaoFirebase.getFirebase()
                .child("Batimentos").child(identificadorUsuarioLogado);

        //valueEventListenerMedicamento = new ValueEventListener()
        valueEventListenerBatimentos = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                batimentos.clear();
                for (DataSnapshot dados : dataSnapshot.getChildren()){
                    Batimentos batimentosNovo = dados.getValue(Batimentos.class);
                    batimentos.add(batimentosNovo);
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
        firebase.removeEventListener(valueEventListenerBatimentos);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerBatimentos);
    }
}
