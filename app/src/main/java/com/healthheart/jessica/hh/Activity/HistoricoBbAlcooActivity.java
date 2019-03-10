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
import com.healthheart.jessica.hh.Adapter.AguaAdapter;
import com.healthheart.jessica.hh.Adapter.BbAlcoolicaAdapter;
import com.healthheart.jessica.hh.DAO.ConfiguracaoFirebase;
import com.healthheart.jessica.hh.Entidades.Agua;
import com.healthheart.jessica.hh.Entidades.BebidaAlcoolica;
import com.healthheart.jessica.hh.Entidades.Descanso;
import com.healthheart.jessica.hh.Helper.PreferenciasAndroid;
import com.healthheart.jessica.hh.R;

import java.util.ArrayList;

public class HistoricoBbAlcooActivity extends AppCompatActivity {


    private ListView listView;
    private ArrayAdapter<BebidaAlcoolica> adapter;
    private ArrayList<BebidaAlcoolica> bebidaAlcoolica;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerBbAlco;


    private Button btnVoltarPrinpalBb;
    private Button btnAddBbAlcolica;


    //dados usario logado
    private  String idUsuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_bb_alcoo);


        //Voltar Tela principal
        btnVoltarPrinpalBb = (Button) findViewById(R.id.btnVoltarPrinpalBb);
        btnVoltarPrinpalBb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarTelaPrinci = new Intent(HistoricoBbAlcooActivity.this, BebidaActivity.class);
                startActivity(intentVoltarTelaPrinci);
            }
        });

        btnAddBbAlcolica = (Button) findViewById(R.id.btnAddBbAlcolica);
        btnAddBbAlcolica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarAddbb = new Intent(HistoricoBbAlcooActivity.this, BbAlcoolicaActivity.class);
                startActivity(intentVoltarAddbb);
            }
        });


         bebidaAlcoolica = new ArrayList<>();
        listView = (ListView) findViewById(R.id.lvHistBbAlco);
        adapter = new BbAlcoolicaAdapter (this, bebidaAlcoolica);

        listView.setAdapter(adapter);

        //Recuperar contatos do firebase
        PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(HistoricoBbAlcooActivity.this);
        String identificadorUsuarioLogado = preferenciasAndroid.getIdentificador();

        firebase = ConfiguracaoFirebase.getFirebase().child("BebidasAlcoolicas").child(identificadorUsuarioLogado);

        valueEventListenerBbAlco = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                bebidaAlcoolica.clear();

                for (DataSnapshot dados : dataSnapshot.getChildren()){
                    BebidaAlcoolica bebidaAlcoolicaNova = dados.getValue(BebidaAlcoolica.class);

                    bebidaAlcoolica.add(bebidaAlcoolicaNova);
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
        firebase.removeEventListener(valueEventListenerBbAlco);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerBbAlco);
    }

}
