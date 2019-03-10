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
import com.healthheart.jessica.hh.Adapter.DoencaCardiacaAdapter;
import com.healthheart.jessica.hh.DAO.ConfiguracaoFirebase;
import com.healthheart.jessica.hh.Entidades.Batimentos;
import com.healthheart.jessica.hh.Entidades.DoencaCardiacas;
import com.healthheart.jessica.hh.Helper.PreferenciasAndroid;
import com.healthheart.jessica.hh.R;

import java.util.ArrayList;

public class HistoricoDoencaCardiActivity extends AppCompatActivity {
    private Button btnAddDoencasCardiacas;
    private Button btnVoltarPrinpalSau;

    //dados usario logado
    private  String idUsuarioLogado;

    private ListView listView;
    private ArrayAdapter<DoencaCardiacas> adapter;
    private ArrayList<DoencaCardiacas> doencaCardiacas;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerDoencaCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_doenca_cardi);

        //Voltar Tela principal
        btnVoltarPrinpalSau = (Button) findViewById(R.id.btnVoltarPrinpalSau);
        btnVoltarPrinpalSau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarTelaPrinci = new Intent(HistoricoDoencaCardiActivity.this, SaudeActivity.class);
                startActivity(intentVoltarTelaPrinci);
            }
        });

        btnAddDoencasCardiacas  = (Button) findViewById(R.id.btnAddDoencasCardiacas);
        btnAddDoencasCardiacas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarAddDoen = new Intent(HistoricoDoencaCardiActivity.this, DoencaCardActivity.class);
                startActivity(intentVoltarAddDoen);
            }
        });


        doencaCardiacas = new ArrayList<>();
        listView = (ListView) findViewById(R.id.lvHistDoencaCardi);
        adapter = new DoencaCardiacaAdapter(this, doencaCardiacas);
        listView.setAdapter(adapter);

        //Recuperar contatos do firebase
        PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(HistoricoDoencaCardiActivity.this);
        String identificadorUsuarioLogado = preferenciasAndroid.getIdentificador();

        firebase = ConfiguracaoFirebase.getFirebase()
                .child("DoencasCardiacas").child(identificadorUsuarioLogado);


        //valueEventListenerMedicamento = new ValueEventListener()
        valueEventListenerDoencaCar = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                doencaCardiacas.clear();
                for (DataSnapshot dados : dataSnapshot.getChildren()){
                    DoencaCardiacas doencaCardiacasNovo = dados.getValue(DoencaCardiacas.class);
                    doencaCardiacas.add(doencaCardiacasNovo);
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
        firebase.removeEventListener(valueEventListenerDoencaCar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerDoencaCar);
    }
}
