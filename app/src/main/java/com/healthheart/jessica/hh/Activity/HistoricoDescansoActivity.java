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
import com.healthheart.jessica.hh.Adapter.DescansaAdapter;
import com.healthheart.jessica.hh.Adapter.MedicamentoAdapter;
import com.healthheart.jessica.hh.Adapter.PressaoAdapter;
import com.healthheart.jessica.hh.DAO.ConfiguracaoFirebase;
import com.healthheart.jessica.hh.Entidades.Descanso;
import com.healthheart.jessica.hh.Entidades.Medicamento;

import com.healthheart.jessica.hh.Helper.PreferenciasAndroid;
import com.healthheart.jessica.hh.R;

import java.util.ArrayList;

public class HistoricoDescansoActivity extends AppCompatActivity {
    private Button btnAddDescanso;

    private Button  btnVoltarPrinpal;

    private ListView listView;
    private ArrayAdapter<Descanso> adapter;
    private ArrayList<Descanso> descansos;
    private  DatabaseReference firebase;
    private ValueEventListener valueEventListenerDescaso;

    //dados usario logado
    private  String idUsuarioLogado;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_historico_descanso);


       //Voltar Tela principal
       btnVoltarPrinpal = (Button) findViewById(R.id.btnVoltarPrinpal);
       btnVoltarPrinpal.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intentVoltarTelaPrinci = new Intent(HistoricoDescansoActivity.this, PrincipalActivity.class);
               startActivity(intentVoltarTelaPrinci);
           }
       });

       btnAddDescanso = (Button) findViewById(R.id.btnAddDescanso);
       btnAddDescanso.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intentVoltarAddDesca = new Intent(HistoricoDescansoActivity.this, DescansoActivity.class);
               startActivity(intentVoltarAddDesca);
           }
       });


       descansos = new ArrayList<>();

       listView = (ListView) findViewById(R.id.lvHistDescanso);
       adapter = new DescansaAdapter(this, descansos);

       listView.setAdapter(adapter);

       //Recuperar contatos do firebase
       PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(HistoricoDescansoActivity.this);
       String identificadorUsuarioLogado = preferenciasAndroid.getIdentificador();

       firebase = ConfiguracaoFirebase.getFirebase()
               .child("Descanso").child(identificadorUsuarioLogado);

       valueEventListenerDescaso = new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               descansos.clear();
               for (DataSnapshot dados : dataSnapshot.getChildren()){
                   Descanso descansoNovo = dados.getValue(Descanso.class);
                   descansos.add(descansoNovo);
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
        firebase.removeEventListener(valueEventListenerDescaso);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerDescaso);
    }
}
