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
import com.healthheart.jessica.hh.Adapter.PressaoAdapter;
import com.healthheart.jessica.hh.DAO.ConfiguracaoFirebase;
import com.healthheart.jessica.hh.Entidades.Pressao;
import com.healthheart.jessica.hh.Helper.PreferenciasAndroid;
import com.healthheart.jessica.hh.R;

import java.util.ArrayList;

public class HistoricoPressaoActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<Pressao> adapter;
    private ArrayList<Pressao> pressao;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerPressao;

    //dados usario logado
    private  String idUsuarioLogado;

    private Button btnAddPressao;
    private Button btnVoltarPrinpal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_pressao);

        btnAddPressao = (Button) findViewById(R.id.btnAddPressao);
        btnVoltarPrinpal = (Button) findViewById(R.id.btnVoltarPrinpal);

        btnAddPressao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrieTelaAddPressao = new Intent(HistoricoPressaoActivity.this, PressaoActivity.class);
                startActivity(intentAbrieTelaAddPressao);
            }
        });

        btnVoltarPrinpal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrieTelaPrinc = new Intent(HistoricoPressaoActivity.this, PrincipalActivity.class);
                startActivity(intentAbrieTelaPrinc);
            }
        });

        pressao = new ArrayList<>();
        listView = (ListView) findViewById(R.id.lvHistPressao);
        adapter = new PressaoAdapter(this, pressao);

        listView.setAdapter(adapter);

        //Recuperar contatos do firebase
        PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(HistoricoPressaoActivity.this);
        String identificadorUsuarioLogado = preferenciasAndroid.getIdentificador();

        firebase = ConfiguracaoFirebase.getFirebase().child("Pressao").child(identificadorUsuarioLogado);

        valueEventListenerPressao = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pressao.clear();

                for (DataSnapshot dados : dataSnapshot.getChildren()){
                    Pressao pressaoNova = dados.getValue(Pressao.class);

                    pressao.add(pressaoNova);
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
        firebase.removeEventListener(valueEventListenerPressao);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerPressao);
    }
}
