package com.healthheart.jessica.hh.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.healthheart.jessica.hh.DAO.ConfiguracaoFirebase;
import com.healthheart.jessica.hh.Entidades.BebidaAlcoolica;
import com.healthheart.jessica.hh.Helper.PreferenciasAndroid;
import com.healthheart.jessica.hh.R;

public class BbAlcoolicaActivity extends AppCompatActivity {

    private EditText edttxtDataBbAlcoo;
    private EditText edttxtNomeBbAlcoo;
    private EditText edttxtQntBbAlcoo;
    private Button btnSalvaBbAlcoolica;
    private Button btnCancBbAlcolica;

    private RadioButton rbManhaBbAlcoo;
    private RadioButton rbTardeBbAlcoo;
    private RadioButton rbNoiteBbAlcoo;

    private Button btnVoltarPrinpalBb;
    private Button btnHisBbAlcolica;

    private BebidaAlcoolica bebidaAlcoolica;


    //dados usario logado
    private  String idUsuarioLogado;

    private FirebaseAuth autenticacao;

    //Objeto de conexao
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bb_alcoolica);


        //Voltar Tela principal
        btnVoltarPrinpalBb = (Button) findViewById(R.id.btnVoltarPrinpalBb);
        btnVoltarPrinpalBb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarTelaPrinci = new Intent(BbAlcoolicaActivity.this, BebidaActivity.class);
                startActivity(intentVoltarTelaPrinci);
            }
        });

        btnCancBbAlcolica = (Button) findViewById(R.id.btnCancBbAlcolica);
        btnCancBbAlcolica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCancBb = new Intent(BbAlcoolicaActivity.this, BbAlcoolicaActivity.class);
                startActivity(intentCancBb);
            }
        });

        btnHisBbAlcolica  = (Button) findViewById(R.id.btnHisBbAlcolica);
        btnHisBbAlcolica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHisBb = new Intent(BbAlcoolicaActivity.this, HistoricoBbAlcooActivity.class);
                startActivity(intentHisBb);
            }
        });


        //recupera identificador usuario
        PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(BbAlcoolicaActivity.this);
        idUsuarioLogado = preferenciasAndroid.getIdentificador();

        edttxtDataBbAlcoo  = (EditText) findViewById(R.id.edttxtDataBbAlcoo);
        edttxtNomeBbAlcoo  = (EditText) findViewById(R.id.edttxtNomeBbAlcoo);
        edttxtQntBbAlcoo  = (EditText) findViewById(R.id.edttxtQntBbAlcoo);
        rbManhaBbAlcoo = (RadioButton) findViewById(R.id.rbManhaBbAlcoo);
        rbTardeBbAlcoo = (RadioButton) findViewById(R.id.rbTardeBbAlcoo);
        rbNoiteBbAlcoo = (RadioButton) findViewById(R.id.rbNoiteBbAlcoo);
        btnSalvaBbAlcoolica  = (Button) findViewById(R.id.btnSalvaBbAlcoolica);


        //Inicializa banco de dados
        inicializarFirebase();

        btnSalvaBbAlcoolica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edttxtDataBbAlcoo.getText().toString().equals("")
                        && !edttxtNomeBbAlcoo.getText().toString().equals("")
                        && !edttxtQntBbAlcoo.getText().toString().equals("")){
                    bebidaAlcoolica = new BebidaAlcoolica();
                    bebidaAlcoolica.setIdUsuario(idUsuarioLogado);
                    bebidaAlcoolica.setDataBbAlcoo(edttxtDataBbAlcoo.getText().toString());
                    bebidaAlcoolica.setNomeBbAlcoo(edttxtNomeBbAlcoo.getText().toString());
                    bebidaAlcoolica.setQntCopoTomadosBbAlcoo(Double.parseDouble(edttxtQntBbAlcoo.getText().toString()));
                    if(rbManhaBbAlcoo.isChecked()){
                        bebidaAlcoolica.setPeriodoBbAlcoo("Manhã");
                    }
                    if(rbTardeBbAlcoo.isChecked()){
                        bebidaAlcoolica.setPeriodoBbAlcoo("Tarde");
                    }
                    if(rbNoiteBbAlcoo.isChecked()){
                        bebidaAlcoolica.setPeriodoBbAlcoo("Noite");
                    }
                    databaseReference = ConfiguracaoFirebase.getFirebase().child("BebidasAlcoolicas");
                    databaseReference.child(idUsuarioLogado).push().setValue(bebidaAlcoolica);
                    Toast.makeText(BbAlcoolicaActivity.this, "Bebidas Alcoolicas registrado com sucesso", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(BbAlcoolicaActivity.this, "Preenchimento de Todos os campos são Obrigatórios", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(BbAlcoolicaActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}
