package com.healthheart.jessica.hh.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.healthheart.jessica.hh.DAO.ConfiguracaoFirebase;
import com.healthheart.jessica.hh.Helper.PreferenciasAndroid;
import com.healthheart.jessica.hh.R;
import com.healthheart.jessica.hh.Entidades.Pressao;

import java.util.UUID;

public class PressaoActivity extends AppCompatActivity {

    private EditText edttexDataPressao;
    private EditText edttxtSistolica;
    private EditText edttxtDiastolica;
    private Button btnSalvarPressao;
    private Button btnCancPressao;

    private Button btnVoltarPrinpal;
    private Button btnInfoPressao;

    private Button btnAbrirActivityHistPressao;

    private Pressao pressao;

    //dados usario logado
    private  String idUsuarioLogado;

    private FirebaseAuth autenticacao;


    //Objeto de conexao
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressao);

        //recupera a imgageButton batimentos
       btnAbrirActivityHistPressao = (Button) findViewById(R.id.btnHisPressao);

        btnAbrirActivityHistPressao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrieTelaHistPressao = new Intent(PressaoActivity.this, HistoricoPressaoActivity.class);
                startActivity(intentAbrieTelaHistPressao);
            }
        });

        //Voltar Tela principal
             btnVoltarPrinpal = (Button) findViewById(R.id.btnVoltarPrinpal);
        btnVoltarPrinpal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarTelaPrinci = new Intent(PressaoActivity.this, PrincipalActivity.class);
                startActivity(intentVoltarTelaPrinci);
            }
        });

        btnInfoPressao = (Button) findViewById(R.id.btnInfoPressao);
        btnInfoPressao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentIrInfoPre = new Intent(PressaoActivity.this, InfoPressaoActivity.class);
                startActivity(intentIrInfoPre);
            }
        });

        btnCancPressao = (Button) findViewById(R.id.btnCancPressao);
        btnCancPressao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCanPre = new Intent(PressaoActivity.this, PressaoActivity.class);
                startActivity(intentCanPre);
            }
        });



        //recupera identificador usuario
        PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(PressaoActivity.this);
        idUsuarioLogado = preferenciasAndroid.getIdentificador();

        //recupera o valor dos campos e botão

        edttexDataPressao = (EditText) findViewById(R.id.edttexDataPressao);
        edttxtSistolica = (EditText) findViewById(R.id.edttxtSistolica);
        edttxtDiastolica = (EditText) findViewById(R.id.edttxtDiastolica);
        btnSalvarPressao  = (Button) findViewById(R.id.btnSalvarPressao);
        btnCancPressao = (Button) findViewById(R.id.btnCancPressao);


        //Inicializa banco de dados
        inicializarFirebase();

        //salvar campos
        btnSalvarPressao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edttexDataPressao.getText().toString().equals("") && !edttxtSistolica.getText().toString().equals("") && !edttxtDiastolica.getText().toString().equals("")){
                        //Instancia
                    pressao = new Pressao();
                   // pressao.setIdPressao(UUID.randomUUID().toString());
                    pressao.setIdUsuario(idUsuarioLogado); //chave estrangeira
                    pressao.setDataPressao(edttexDataPressao.getText().toString());
                    pressao.setSistolica(Double.parseDouble(edttxtSistolica.getText().toString()));
                    pressao.setDiastolica(Double.parseDouble(edttxtDiastolica.getText().toString()));
                   // databaseReference.child("Pressao").child(pressao.getIdPressao()).setValue(pressao);
                    databaseReference = ConfiguracaoFirebase.getFirebase().child("Pressao");
                    databaseReference.child(idUsuarioLogado).push().setValue(pressao);

                    Toast.makeText(PressaoActivity.this, "Pressão registrado com sucesso", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(PressaoActivity.this, "Preenchimento de Todos os campos são Obrigatórios", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(PressaoActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}
