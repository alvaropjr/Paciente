package com.healthheart.jessica.hh.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.healthheart.jessica.hh.Adapter.PesoAdapter;
import com.healthheart.jessica.hh.Adapter.PressaoAdapter;
import com.healthheart.jessica.hh.DAO.ConfiguracaoFirebase;
import com.healthheart.jessica.hh.Entidades.Peso;
import com.healthheart.jessica.hh.Entidades.Pressao;
import com.healthheart.jessica.hh.Helper.PreferenciasAndroid;
import com.healthheart.jessica.hh.R;

import java.util.ArrayList;

public class HistoricoPesoActivity extends AppCompatActivity {


    private ListView listView;
    private ArrayAdapter<Peso> adapter;
    private ArrayList<Peso> peso;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerPeso;


    private TextView txtViewImc;


    private Button btnVoltarPrinpal;
    private Button btnAddPeso;

    private Peso peso3;

    //dados usario logado
    private  String idUsuarioLogado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_peso);

        txtViewImc = (TextView) findViewById(R.id.txtViewImc);




        //Voltar Tela principal
        btnVoltarPrinpal = (Button) findViewById(R.id.btnVoltarPrinpal);
        btnVoltarPrinpal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarTelaPrinci = new Intent(HistoricoPesoActivity.this, PrincipalActivity.class);
                startActivity(intentVoltarTelaPrinci);
            }
        });


        btnAddPeso = (Button) findViewById(R.id.btnAddPeso);
        btnAddPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltaPeso = new Intent(HistoricoPesoActivity.this, PesoActivity.class);
                startActivity(intentVoltaPeso);
            }
        });

        peso = new ArrayList<>();
        listView = (ListView) findViewById(R.id.lvHistPeso);
        adapter = new PesoAdapter(this, peso);

        listView.setAdapter(adapter);

        //Recuperar contatos do firebase
        PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(HistoricoPesoActivity.this);
        String identificadorUsuarioLogado = preferenciasAndroid.getIdentificador();

        firebase = ConfiguracaoFirebase.getFirebase().child("Peso").child(identificadorUsuarioLogado);


        valueEventListenerPeso = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                peso.clear();

                for (DataSnapshot dados : dataSnapshot.getChildren()){
                    Peso pesoNova = dados.getValue(Peso.class);

                    peso.add(pesoNova);
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
        firebase.removeEventListener(valueEventListenerPeso);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerPeso);
    }
}
