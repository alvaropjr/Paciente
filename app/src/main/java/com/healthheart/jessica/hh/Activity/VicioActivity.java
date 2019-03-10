package com.healthheart.jessica.hh.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.healthheart.jessica.hh.DAO.ConfiguracaoFirebase;
import com.healthheart.jessica.hh.Entidades.Vicios;
import com.healthheart.jessica.hh.Helper.PreferenciasAndroid;
import com.healthheart.jessica.hh.R;

public class VicioActivity extends AppCompatActivity {

    private Button btnVoltarPrinpalSau;
    private Button btnAddVicios;
    private Button btnHisVicio;
    private Button btnInfoVicios;
    private Button btnSalvaVicios;
    private Button btnCancVicios;
    private CheckBox chcboxVicioDrogas;
    private EditText edttxtDrogas;
    private CheckBox chcboxVicioAlcool;
    private EditText edttxtAlcool;
    private CheckBox chcboxVicioMedica;
    private EditText edttxtVicioMedica;

    private Vicios vicios;

    //dados usario logado
    private  String idUsuarioLogado;

    private FirebaseAuth autenticacao;

    //Objeto de conexao
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vicio);

        //Voltar Tela principal
        btnVoltarPrinpalSau = (Button) findViewById(R.id.btnVoltarPrinpalSau);
        btnVoltarPrinpalSau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarTelaPrinci = new Intent(VicioActivity.this, SaudeActivity.class);
                startActivity(intentVoltarTelaPrinci);
            }
        });

        btnHisVicio = (Button) findViewById(R.id.btnHisVicio);
        btnHisVicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentIrHistVicio = new Intent(VicioActivity.this, HistoricoVicioActivity.class);
                startActivity(intentIrHistVicio);
            }
        });

        btnCancVicios = (Button) findViewById(R.id.btnCancVicios);
        btnCancVicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCanVicio = new Intent(VicioActivity.this, VicioActivity.class);
                startActivity(intentCanVicio);
            }
        });


        //recupera identificador usuario
        PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(VicioActivity.this);
        idUsuarioLogado = preferenciasAndroid.getIdentificador();

        //recupera o valor dos campos e botÃ£o
       // btnAddVicios = (Button) findViewById(R.id.btnAddVicios);

        //btnInfoVicios = (Button) findViewById(R.id.btnInfoVicios);
        btnSalvaVicios = (Button) findViewById(R.id.btnSalvaVicios);
        //btnCancVicios = (Button) findViewById(R.id.btnCancVicios);
        chcboxVicioDrogas = (CheckBox) findViewById(R.id.chcboxVicioDrogas);
        edttxtDrogas = (EditText) findViewById(R.id.edttxtDrogas);
        chcboxVicioAlcool = (CheckBox) findViewById(R.id.chcboxVicioAlcool);
        edttxtAlcool = (EditText) findViewById(R.id.edttxtAlcool);
        chcboxVicioMedica = (CheckBox) findViewById(R.id.chcboxVicioMedica);
        edttxtVicioMedica = (EditText) findViewById(R.id.edttxtVicioMedica);


        btnSalvaVicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (!edttxtNomeMedicamento.getText().toString().equals("")){
                if (!chcboxVicioDrogas.getText().toString().equals("") && !edttxtDrogas.getText().toString().equals("") ||
                        !chcboxVicioAlcool.getText().toString().equals("") && !edttxtAlcool.getText().toString().equals("") ||
                        !chcboxVicioMedica.getText().toString().equals("") && !edttxtVicioMedica.getText().toString().equals("") ){
                    vicios = new Vicios();
                    vicios.setIdUsuario(idUsuarioLogado);
                    if (chcboxVicioDrogas.isChecked()){
                        vicios.setVicioDrogas("Vicio em Drogas");
                    }
                    if (!edttxtDrogas.getText().toString().equals("")){
                        vicios.setDrogas(edttxtDrogas.getText().toString());
                    }

                    if (chcboxVicioAlcool.isChecked()){
                        vicios.setVicioAlcool("Vicio em Álcool");
                    }
                    if (!edttxtAlcool.getText().toString().equals("")){
                        vicios.setBebidaAlcool(edttxtAlcool.getText().toString());
                    }

                    if (chcboxVicioMedica.isChecked()){
                        vicios.setVicioMedica("Vicio em Medicamentos");
                    }
                    if (!edttxtVicioMedica.getText().toString().equals("")){
                        vicios.setMedica(edttxtVicioMedica.getText().toString());
                    }
                    databaseReference = ConfiguracaoFirebase.getFirebase().child("Vicios");
                    databaseReference.child(idUsuarioLogado).push().setValue(vicios);
                    //mDatabase.updateChildren(childUpdates);
                    Toast.makeText(VicioActivity.this, "Vicios registrado com sucesso", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(VicioActivity.this, "Preenchimento ao menos um conjunto de Vicios", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Inicializa banco de dados
        inicializarFirebase();

    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(VicioActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

}
