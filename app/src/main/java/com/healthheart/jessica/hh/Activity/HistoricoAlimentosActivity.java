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
import com.healthheart.jessica.hh.Adapter.AlimentosAdapter;
import com.healthheart.jessica.hh.DAO.ConfiguracaoFirebase;
import com.healthheart.jessica.hh.Entidades.Agua;
import com.healthheart.jessica.hh.Entidades.Alimentos;
import com.healthheart.jessica.hh.Entidades.Medicamento;
import com.healthheart.jessica.hh.Helper.PreferenciasAndroid;
import com.healthheart.jessica.hh.R;

import java.util.ArrayList;

public class HistoricoAlimentosActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<Alimentos> adapter;
    private ArrayList<Alimentos> alimentos;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerAlimentos;

    private Button btnVoltarPrinpal;
    private Button btnAddAlimento;

    //dados usario logado
    private  String idUsuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_alimentos);

        //Voltar Tela principal
        btnVoltarPrinpal = (Button) findViewById(R.id.btnVoltarPrinpal);
        btnAddAlimento = (Button) findViewById(R.id.btnAddAlimento);


        btnVoltarPrinpal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarTelaPrinci = new Intent(HistoricoAlimentosActivity.this, PrincipalActivity.class);
                startActivity(intentVoltarTelaPrinci);
            }
        });

        btnAddAlimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltadd = new Intent(HistoricoAlimentosActivity.this, AlimentosActivity.class);
                startActivity(intentVoltadd);
            }
        });


    alimentos = new ArrayList<>();
        listView = (ListView) findViewById(R.id.lvHistAlimentos);
        adapter = new AlimentosAdapter(this, alimentos);

        listView.setAdapter(adapter);

        //Recuperar contatos do firebase
        PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(HistoricoAlimentosActivity.this);
        String identificadorUsuarioLogado = preferenciasAndroid.getIdentificador();

        firebase = ConfiguracaoFirebase.getFirebase().child("Alimentos").child(identificadorUsuarioLogado);

        valueEventListenerAlimentos = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                alimentos.clear();
                for (DataSnapshot dados : dataSnapshot.getChildren()){

                    Alimentos alimentosNovo = dados.getValue(Alimentos.class);
                    alimentos.add(alimentosNovo);
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
        firebase.removeEventListener(valueEventListenerAlimentos);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerAlimentos);
    }
}
