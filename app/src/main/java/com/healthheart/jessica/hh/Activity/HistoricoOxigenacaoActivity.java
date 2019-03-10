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
import com.healthheart.jessica.hh.Adapter.OxigenacaoAdapter;
import com.healthheart.jessica.hh.DAO.ConfiguracaoFirebase;
import com.healthheart.jessica.hh.Entidades.Batimentos;
import com.healthheart.jessica.hh.Entidades.Oxigenacao;
import com.healthheart.jessica.hh.Helper.PreferenciasAndroid;
import com.healthheart.jessica.hh.R;

import java.util.ArrayList;

public class HistoricoOxigenacaoActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<Oxigenacao> adapter;
    private ArrayList<Oxigenacao> oxigenacao;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerOxi;


    private Button btnAddOxigenacao;
    private Button btnVoltarPrinpal;

    //dados usario logado
    private  String idUsuarioLogado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_oxigenacao);

        //Voltar Tela principal
        btnVoltarPrinpal = (Button) findViewById(R.id.btnVoltarPrinpal);
        btnVoltarPrinpal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarTelaPrinci = new Intent(HistoricoOxigenacaoActivity.this, PrincipalActivity.class);
                startActivity(intentVoltarTelaPrinci);
            }
        });

        btnAddOxigenacao = (Button) findViewById(R.id.btnAddOxigenacao);
        btnAddOxigenacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarAddOx = new Intent(HistoricoOxigenacaoActivity.this, OxigenacaoActivity.class);
                startActivity(intentVoltarAddOx);
            }
        });


        oxigenacao = new ArrayList<>();
        listView = (ListView) findViewById(R.id.lvHistOxi);
        adapter = new OxigenacaoAdapter(this, oxigenacao);
        listView.setAdapter(adapter);

        //Recuperar contatos do firebase
        PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(HistoricoOxigenacaoActivity.this);
        String identificadorUsuarioLogado = preferenciasAndroid.getIdentificador();

        firebase = ConfiguracaoFirebase.getFirebase()
                .child("Oxigenacao").child(identificadorUsuarioLogado);

        //valueEventListenerMedicamento = new ValueEventListener()
        valueEventListenerOxi = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                oxigenacao.clear();
                for (DataSnapshot dados : dataSnapshot.getChildren()){
                    Oxigenacao oxigenacaoNovo = dados.getValue(Oxigenacao.class);
                    oxigenacao.add(oxigenacaoNovo);
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
        firebase.removeEventListener(valueEventListenerOxi);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerOxi);
    }
}
