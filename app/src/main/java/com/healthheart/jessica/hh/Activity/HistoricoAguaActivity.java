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
import com.healthheart.jessica.hh.DAO.ConfiguracaoFirebase;
import com.healthheart.jessica.hh.Entidades.Agua;
import com.healthheart.jessica.hh.Helper.PreferenciasAndroid;
import com.healthheart.jessica.hh.R;

import java.util.ArrayList;

public class HistoricoAguaActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<Agua> adapter;
    private ArrayList<Agua> agua;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerAgua;

    private Button btnVoltarPrinpalBb;
    private Button btnAddAgua;

    //dados usario logado
    private  String idUsuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_agua);

        //Voltar Tela principal
        btnVoltarPrinpalBb = (Button) findViewById(R.id.btnVoltarPrinpalBb);
        btnAddAgua = (Button) findViewById(R.id.btnAddAgua);


        btnVoltarPrinpalBb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarTelaPrinci = new Intent(HistoricoAguaActivity.this, BebidaActivity.class);
                startActivity(intentVoltarTelaPrinci);
            }
        });

        btnAddAgua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltadd = new Intent(HistoricoAguaActivity.this, AguaActivity.class);
                startActivity(intentVoltadd);
            }
        });

        agua = new ArrayList<>();
        listView = (ListView) findViewById(R.id.lvHistAgua);
        adapter = new AguaAdapter(this, agua);

        listView.setAdapter(adapter);

        //Recuperar contatos do firebase
        PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(HistoricoAguaActivity.this);
        String identificadorUsuarioLogado = preferenciasAndroid.getIdentificador();

        firebase = ConfiguracaoFirebase.getFirebase().child("Agua").child(identificadorUsuarioLogado);

        valueEventListenerAgua = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                agua.clear();

                for (DataSnapshot dados : dataSnapshot.getChildren()){
                    Agua aguaNova = dados.getValue(Agua.class);

                    agua.add(aguaNova);
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
        firebase.removeEventListener(valueEventListenerAgua);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerAgua);
    }
}
