package com.healthheart.jessica.hh.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.healthheart.jessica.hh.DAO.ConfiguracaoFirebase;
import com.healthheart.jessica.hh.Entidades.Agua;
import com.healthheart.jessica.hh.Helper.PreferenciasAndroid;
import com.healthheart.jessica.hh.R;

import java.util.UUID;

public class AguaActivity extends AppCompatActivity {

    private EditText edttxtDataAgua;
    private EditText edttxtQntAgua;
    private Button btnSalvarAgua;
    private Button btnCancAgua;
    private Button btnVoltarPrinpalBb;

    private Button btnHisAgua;

    private Agua agua;

    //dados usario logado
    private  String idUsuarioLogado;

    private FirebaseAuth autenticacao;

    //Objeto de conexao
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agua);

        //Voltar Tela principal
        btnVoltarPrinpalBb = (Button) findViewById(R.id.btnVoltarPrinpalBb);
        btnVoltarPrinpalBb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarTelaPrinci = new Intent(AguaActivity.this, BebidaActivity.class);
                startActivity(intentVoltarTelaPrinci);
            }
        });

        // cancelar agua
        btnCancAgua = (Button) findViewById(R.id.btnCancAgua);
        btnCancAgua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCanAgua = new Intent(AguaActivity.this, AguaActivity.class);
                startActivity(intentCanAgua);
            }
        });


        btnHisAgua = (Button) findViewById(R.id.btnHisAgua);
        btnHisAgua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarTelaHistAgua = new Intent(AguaActivity.this, HistoricoAguaActivity.class);
                startActivity(intentVoltarTelaHistAgua);
            }
        });

        //recupera identificador usuario
        PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(AguaActivity.this);
        idUsuarioLogado = preferenciasAndroid.getIdentificador();

        edttxtDataAgua = (EditText) findViewById(R.id.edttxtDataAgua);
        edttxtQntAgua = (EditText) findViewById(R.id.edttxtQntAgua);
        btnSalvarAgua = (Button) findViewById(R.id.btnSalvarAgua);
        btnCancAgua = (Button) findViewById(R.id.btnCancAgua);

        //Inicializa banco
        inicializarFirebase();

        btnSalvarAgua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edttxtDataAgua.getText().toString().equals("") && !edttxtQntAgua.getText().toString().equals("")){
                    agua = new Agua();
                   // agua.setIdAgua(UUID.randomUUID().toString());
                    agua.setIdUsuario(idUsuarioLogado);
                    agua.setDataAgua(edttxtDataAgua.getText().toString());
                    //agua.setQntCopoTomados(Float.parseFloat(edttxtQntAgua.getText().toString()));
                    agua.setQntCopoTomados(Double.parseDouble(edttxtQntAgua.getText().toString()));
                    //databaseReference.child("Agua").child(agua.getIdAgua()).setValue(agua);
                    databaseReference = ConfiguracaoFirebase.getFirebase().child("Agua");
                    databaseReference.child(idUsuarioLogado).push().setValue(agua);
                    Toast.makeText(AguaActivity.this, "Água registrado com sucesso", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AguaActivity.this, "Preenchimento de Todos os campos são Obrigatórios", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(AguaActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}
