package com.healthheart.jessica.hh.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.healthheart.jessica.hh.Adapter.MedicamentoAdapter;
import com.healthheart.jessica.hh.DAO.ConfiguracaoFirebase;
import com.healthheart.jessica.hh.Entidades.Medicamento;
import com.healthheart.jessica.hh.Helper.PreferenciasAndroid;
import com.healthheart.jessica.hh.R;

import java.util.ArrayList;

public class HistoricoMedicamentosActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<Medicamento> adapter;
    private ArrayList<Medicamento> medicamento;
    private  DatabaseReference firebase;
    private ValueEventListener valueEventListenerMedicamento;

    private Button btnAddMedicamento;
    private Button btnHistMedica;
    private Button btnVoltarPrinpalSau;



    //dados usario logado
    private  String idUsuarioLogado;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_medicamentos);

        //Voltar Tela principal
        btnVoltarPrinpalSau = (Button) findViewById(R.id.btnVoltarPrinpalSau);
        btnVoltarPrinpalSau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarTelaPrinci = new Intent(HistoricoMedicamentosActivity.this, SaudeActivity.class);
                startActivity(intentVoltarTelaPrinci);
            }
        });

        //idUsuarioLogado = preferenciasAndroid.getIdentificador();

        btnAddMedicamento = (Button) findViewById(R.id.btnAddMedicamento);
        btnAddMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarAddMedica = new Intent(HistoricoMedicamentosActivity.this, MedicacaoActivity.class);
                startActivity(intentVoltarAddMedica);
            }
        });

        btnHistMedica = (Button) findViewById(R.id.btnHistMedica);
        btnHistMedica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarAddMedica = new Intent(HistoricoMedicamentosActivity.this, MedicacaoActivity.class);
                startActivity(intentVoltarAddMedica);
            }
        });


        medicamento = new ArrayList<>();

        listView = (ListView) findViewById(R.id.lvHistMedicamento);
        adapter = new MedicamentoAdapter(this, medicamento);

        listView.setAdapter(adapter);

        //Recuperar contatos do firebase
        PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(HistoricoMedicamentosActivity.this);
        String identificadorUsuarioLogado = preferenciasAndroid.getIdentificador();

        firebase = ConfiguracaoFirebase.getFirebase()
                .child("Medicamentos").child(identificadorUsuarioLogado);

        valueEventListenerMedicamento = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                medicamento.clear();
                for (DataSnapshot dados : dataSnapshot.getChildren()){
                    //Medicamento medicamentoNovo = dados.getValue(Medicamento.class);
                   Medicamento medicamentoNovo = dados.getValue(Medicamento.class);
                    medicamento.add(medicamentoNovo);
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
        firebase.removeEventListener(valueEventListenerMedicamento);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerMedicamento);
    }
}
