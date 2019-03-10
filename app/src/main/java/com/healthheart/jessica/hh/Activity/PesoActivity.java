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
import com.healthheart.jessica.hh.Entidades.Medicamento;
import com.healthheart.jessica.hh.Entidades.Peso;
import com.healthheart.jessica.hh.Helper.PreferenciasAndroid;
import com.healthheart.jessica.hh.R;

import java.util.UUID;


public class PesoActivity extends AppCompatActivity {

    float IMC, Altura, PesoUse;

    private EditText edttxtDataPeso;
    private EditText edttxtPeso;
    private EditText edttxtAltura;
    private EditText edttxtImc;
    private EditText edttxtCircuCintura;
    private  EditText edttxtSattusImc;
    private Button btnSalvarPeso;
    private Button btnCancPeso;

    private RadioButton rbMasc;
    private RadioButton rbFem;

    private Button btnVoltarPrinpal;
    private Button btnAbrirInfoPesoCir;

    private Button btnHistPeso;

    private Peso peso;

    //dados usario logado
    private  String idUsuarioLogado;

    private FirebaseAuth autenticacao;

    //Objeto de conexao
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peso);

        //Voltar Tela principal
        btnVoltarPrinpal = (Button) findViewById(R.id.btnVoltarPrinpal);
        btnVoltarPrinpal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarTelaPrinci = new Intent(PesoActivity.this, PrincipalActivity.class);
                startActivity(intentVoltarTelaPrinci);
            }
        });

        btnAbrirInfoPesoCir = (Button) findViewById(R.id.btninfo);
        btnAbrirInfoPesoCir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentIAbriInfo = new Intent(PesoActivity.this, InfoImcCircuActivity.class);
                startActivity(intentIAbriInfo);
            }
        });

        btnHistPeso = (Button) findViewById(R.id.btnHistPeso);
        btnHistPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentIrhisPeso = new Intent(PesoActivity.this, HistoricoPesoActivity.class);
                startActivity(intentIrhisPeso);
            }
        });

        btnCancPeso = (Button) findViewById(R.id.btnCancPeso);
        btnCancPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCanPeso = new Intent(PesoActivity.this, PesoActivity.class);
                startActivity(intentCanPeso);
            }
        });

        //recupera identificador usuario
        PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(PesoActivity.this);
        idUsuarioLogado = preferenciasAndroid.getIdentificador();


        //recupera o valor dos campos e botão

        edttxtDataPeso = (EditText) findViewById(R.id.edttxtDataPeso);
        edttxtPeso = (EditText) findViewById(R.id.edttxtPeso);
        edttxtAltura = (EditText) findViewById(R.id.edttxtAltura);
        edttxtCircuCintura = (EditText) findViewById(R.id.edttxtCircuCintura);
        rbFem = (RadioButton) findViewById(R.id.rbFem);
        rbMasc = (RadioButton) findViewById(R.id.rbMasc);
        btnSalvarPeso = (Button) findViewById(R.id.btnSalvarPeso);


          //Inicializa banco de dados
        inicializarFirebase();

        //salvar campos
        btnSalvarPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edttxtDataPeso.getText().toString().equals("")
                        &&!edttxtPeso.getText().toString().equals("")
                        && !edttxtAltura.getText().toString().equals("")
                        && !edttxtCircuCintura.getText().toString().equals("")){
                    peso = new Peso();
                    //peso.setIdPeso(UUID.randomUUID().toString());
                    peso.setIdUsuario(idUsuarioLogado); //Chave estrangeira
                    peso.setDataPeso(edttxtDataPeso.getText().toString());
                    peso.setPeso(Double.parseDouble(edttxtPeso.getText().toString()));
                    peso.setAltura(Double.parseDouble(edttxtAltura.getText().toString()));
                    peso.setCircuCintura(Double.parseDouble(edttxtCircuCintura.getText().toString()));
                    if(rbFem.isChecked()){
                        peso.setSexo("Feminino");
                    }else{
                        peso.setSexo("Masculino");
                    }
                    //peso.setIMC(Float.parseFloat(edttxtImc.getText().toString()));
                    //databaseReference.child("Peso").child(peso.getIdPeso()).setValue(peso);
                    databaseReference = ConfiguracaoFirebase.getFirebase().child("Peso");
                    databaseReference.child(idUsuarioLogado).push().setValue(peso);

                    Toast.makeText(PesoActivity.this, "Medidas registradas com sucesso", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(PesoActivity.this, "Preencha todos os dados para que o HH possa calcular o IMC e checar sua circuferência", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void inicializarFirebase() {

        FirebaseApp.initializeApp(PesoActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}
