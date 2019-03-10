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
import com.healthheart.jessica.hh.Adapter.PressaoInfoAdapter;
import com.healthheart.jessica.hh.DAO.ConfiguracaoFirebase;
import com.healthheart.jessica.hh.Entidades.Pressao;
import com.healthheart.jessica.hh.Entidades.PressaoInfo;
import com.healthheart.jessica.hh.R;

import java.util.ArrayList;

public class InfoPressaoActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<PressaoInfo> adapter;
    private ArrayList<PressaoInfo> pressaoinfo;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerPressaoInfo;

    private Button btnVoltarpressao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_pressao);

        //Voltar Tela principal
        btnVoltarpressao = (Button) findViewById(R.id.btnVoltarpressao);
        btnVoltarpressao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarPressao = new Intent(InfoPressaoActivity.this, PressaoActivity.class);
                startActivity(intentVoltarPressao);
            }
        });

        pressaoinfo = new ArrayList<>();
        listView = (ListView) findViewById(R.id.lvInfoPressao);
        adapter = new PressaoInfoAdapter(this, pressaoinfo);
        listView.setAdapter(adapter);

        //firebase = ConfiguracaoFirebase.getFirebase().child("PressaoInfo");
        firebase = ConfiguracaoFirebase.getFirebase().child("PressaoInfo");

      // firebase = ConfiguracaoFirebase.getFirebase().child("PressaoInfo").child("IdInfo");
       // Firebase = ConfiguracaoFirebase.IdInfo

        valueEventListenerPressaoInfo = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pressaoinfo.clear();
                for (DataSnapshot dados : dataSnapshot.getChildren()){
                    PressaoInfo pressaoInfoNovo = dados.getValue(PressaoInfo.class);

                    pressaoinfo.add(pressaoInfoNovo);
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
        firebase.removeEventListener(valueEventListenerPressaoInfo);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerPressaoInfo);
    }
}
