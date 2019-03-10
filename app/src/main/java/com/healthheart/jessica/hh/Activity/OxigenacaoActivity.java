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
import com.healthheart.jessica.hh.Entidades.Oxigenacao;
import com.healthheart.jessica.hh.Helper.PreferenciasAndroid;
import com.healthheart.jessica.hh.R;

public class OxigenacaoActivity extends AppCompatActivity {
    private Button btnVoltarPrinpal;


    private EditText edttxtDataOxi;
    private EditText edttxtOxigenacao;
    private Button btnSalvarOxigenacao;
    private Button btnCancOxigenacao;
    private Button btnHisOxi;
    private Button btnInfoOxi;

    private RadioButton rbManhaOxi;
    private RadioButton rbTardeOxi;
    private RadioButton rbNoiteOxi;

    private Oxigenacao oxigenacao;

    //dados usario logado
    private  String idUsuarioLogado;

    private FirebaseAuth autenticacao;

    //Objeto de conexao
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxigenacao);

        //Voltar Tela principal
        btnVoltarPrinpal = (Button) findViewById(R.id.btnVoltarPrinpal);
        btnVoltarPrinpal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarTelaPrinci = new Intent(OxigenacaoActivity.this, PrincipalActivity.class);
                startActivity(intentVoltarTelaPrinci);
            }
        });

        btnHisOxi = (Button) findViewById(R.id.btnHisOxi);
        btnHisOxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarTelaPrinci = new Intent(OxigenacaoActivity.this, HistoricoOxigenacaoActivity.class);
                startActivity(intentVoltarTelaPrinci);
            }
        });

        btnCancOxigenacao = (Button) findViewById(R.id.btnCancOxigenacao);
        btnCancOxigenacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCanOxi = new Intent(OxigenacaoActivity.this, OxigenacaoActivity.class);
                startActivity(intentCanOxi);
            }
        });

        btnInfoOxi = (Button) findViewById(R.id.btnInfoOxi);
        btnInfoOxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentIrInfoOxi = new Intent(OxigenacaoActivity.this, InfoOxigenacaoActivity.class);
                startActivity(intentIrInfoOxi);
            }
        });


        //recupera identificador usuario
        PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(OxigenacaoActivity.this);
        idUsuarioLogado = preferenciasAndroid.getIdentificador();

        //recupera o valor dos campos e botão
        edttxtDataOxi = (EditText) findViewById(R.id.edttxtDataOxi);
        edttxtOxigenacao = (EditText) findViewById(R.id.edttxtOxigenacao);
        btnSalvarOxigenacao = (Button) findViewById(R.id.btnSalvarOxigenacao);

        rbManhaOxi = (RadioButton) findViewById(R.id.rbManhaOxi);
        rbTardeOxi = (RadioButton) findViewById(R.id.rbTardeOxi);
        rbNoiteOxi = (RadioButton) findViewById(R.id.rbNoiteOxi);

        //Inicializa banco de dados
        inicializarFirebase();

        //btnSalvarPeso.setOnClickListener(new View.OnClickListener()
        btnSalvarOxigenacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edttxtDataOxi.getText().toString().equals("") && !edttxtOxigenacao.getText().toString().equals("")){
                    oxigenacao = new Oxigenacao();
                    oxigenacao.setIdUsuario(idUsuarioLogado); //Chave estrangeira
                    oxigenacao.setDataOxigenacao(edttxtDataOxi.getText().toString());
                    oxigenacao.setOxigenacao(Double.parseDouble(edttxtOxigenacao.getText().toString()));
                    if(rbManhaOxi.isChecked()){
                        oxigenacao.setPeridoOxigenacao("Manhã");
                    }
                    if(rbTardeOxi.isChecked()){
                        oxigenacao.setPeridoOxigenacao("Tarde");
                    }
                    if(rbNoiteOxi.isChecked()){
                        oxigenacao.setPeridoOxigenacao("Noite");
                    }
                    databaseReference = ConfiguracaoFirebase.getFirebase().child("Oxigenacao");
                    databaseReference.child(idUsuarioLogado).push().setValue(oxigenacao);
                    Toast.makeText(OxigenacaoActivity.this, "Oxigenação registradas com sucesso", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(OxigenacaoActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(OxigenacaoActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}
