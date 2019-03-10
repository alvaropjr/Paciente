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
import com.healthheart.jessica.hh.Entidades.DoencaCardiacas;
import com.healthheart.jessica.hh.Helper.PreferenciasAndroid;
import com.healthheart.jessica.hh.R;

public class DoencaCardActivity extends AppCompatActivity {
    private Button btnVoltarPrinpalSau;
    private Button btnHisDoencaCard2;
    private Button btnInfoDoencaCardi;

    private CheckBox chcboxAnginaInstavel;
    private CheckBox chcboxAnginaEstavel;
    private CheckBox chcboxArritmia;
    private CheckBox chcboxArtrose;
    private CheckBox chcboxAterosclerose;
    private CheckBox chcboxArterioesclerose;
    private CheckBox chcboxCardiomiopatia;
    private CheckBox chcboxCardiopatia;
    private CheckBox chcboxEndocardite;
    private CheckBox chcboxEstenoseMitral;
    private CheckBox chcboxEstenosePulmonar;
    private CheckBox chcboxFibrilacaoAtrial;
    private CheckBox chcboxHipertensao;
    private CheckBox chcboxHipotensao;
    private CheckBox chcboxInfarto;
    private CheckBox chcboxInsuficienciaCardiaca;
    private CheckBox chcboxPericardite;
    private CheckBox chcboxProlapso;
    private CheckBox chcboxSopro;
    private CheckBox chcboxTaquicardia;
    private CheckBox chcboxTumorCardiaco;
    private EditText edttxtOutrasDoenca;
    private EditText edttxtDataDoenca;
    private Button btnSalvaDoenca;

    private DoencaCardiacas doencacardicas;


    private Button btnCancDoencaCardi;

    //dados usario logado
    private  String idUsuarioLogado;

    private FirebaseAuth autenticacao;

    //Objeto de conexao
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doenca_card);
        //Voltar Tela principal
        btnVoltarPrinpalSau = (Button) findViewById(R.id.btnVoltarPrinpalSau);
        btnVoltarPrinpalSau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarTelaPrinci = new Intent(DoencaCardActivity.this, SaudeActivity.class);
                startActivity(intentVoltarTelaPrinci);
            }
        });

        btnHisDoencaCard2 = (Button) findViewById(R.id.btnHisDoencaCard2);
        btnHisDoencaCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentIrHistCardi = new Intent(DoencaCardActivity.this, HistoricoDoencaCardiActivity.class);
                startActivity(intentIrHistCardi);
            }
        });

        //cancela doencas cardi
        btnCancDoencaCardi  = (Button) findViewById(R.id.btnCancDoencaCardi);
        btnCancDoencaCardi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCanCardi = new Intent(DoencaCardActivity.this, DoencaCardActivity.class);
                startActivity(intentCanCardi);
            }
        });

        btnInfoDoencaCardi  = (Button) findViewById(R.id.btnInfoDoencaCardi);
        btnInfoDoencaCardi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentInfoCard = new Intent(DoencaCardActivity.this, InfoDoencaCardiActivity.class);
                startActivity(intentInfoCard);
            }
        });

        //recupera identificador usuario
        PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(DoencaCardActivity.this);
        idUsuarioLogado = preferenciasAndroid.getIdentificador();


        edttxtDataDoenca = (EditText) findViewById(R.id.edttxtDataDoenca);
        chcboxAnginaInstavel = (CheckBox) findViewById(R.id.chcboxAnginaInstavel);
        chcboxAnginaEstavel = (CheckBox) findViewById(R.id.chcboxAnginaEstavel);
        chcboxArritmia = (CheckBox) findViewById(R.id.chcboxArritmia);
        chcboxArtrose = (CheckBox) findViewById(R.id.chcboxArtrose);
        chcboxAterosclerose = (CheckBox) findViewById(R.id.chcboxAterosclerose);
        chcboxArterioesclerose = (CheckBox) findViewById(R.id.chcboxArterioesclerose);
        chcboxCardiomiopatia = (CheckBox) findViewById(R.id.chcboxCardiomiopatia);
        chcboxCardiopatia = (CheckBox) findViewById(R.id.chcboxCardiopatia);
        chcboxEndocardite = (CheckBox) findViewById(R.id.chcboxEndocardite);
        chcboxEstenoseMitral = (CheckBox) findViewById(R.id.chcboxEstenoseMitral);
        chcboxEstenosePulmonar = (CheckBox) findViewById(R.id.chcboxEstenosePulmonar);
        chcboxFibrilacaoAtrial = (CheckBox) findViewById(R.id.chcboxFibrilacaoAtrial);
        chcboxHipertensao = (CheckBox) findViewById(R.id.chcboxHipertensao);
        chcboxHipotensao = (CheckBox) findViewById(R.id.chcboxHipotensao);
        chcboxInfarto = (CheckBox) findViewById(R.id.chcboxInfarto);
        chcboxInsuficienciaCardiaca = (CheckBox) findViewById(R.id.chcboxInsuficienciaCardiaca);
        chcboxPericardite = (CheckBox) findViewById(R.id.chcboxPericardite);
        chcboxProlapso = (CheckBox) findViewById(R.id.chcboxProlapso);
        chcboxSopro = (CheckBox) findViewById(R.id.chcboxSopro);
        chcboxTaquicardia = (CheckBox) findViewById(R.id.chcboxTaquicardia);
        chcboxTumorCardiaco = (CheckBox) findViewById(R.id.chcboxTumorCardiaco);
        edttxtOutrasDoenca = (EditText) findViewById(R.id.edttxtOutrasDoenca);
        btnSalvaDoenca = (Button) findViewById(R.id.btnSalvaDoenca);
        //btnSalvarDoencaCardi  = (Button) findViewById(R.id.btnSalvarMedicacao);
        btnCancDoencaCardi = (Button) findViewById(R.id.btnCancDoencaCardi);

        //Inicializa banco de dados  new View.OnClickListener
        inicializarFirebase();

        btnSalvaDoenca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doencacardicas = new DoencaCardiacas();
                doencacardicas.setIdUsuario(idUsuarioLogado);
                doencacardicas.setDataDoenca(edttxtDataDoenca.getText().toString());
                //if (!edttxtDrogas.getText().toString().equals(""))
                if (!edttxtOutrasDoenca.getText().toString().equals("")){
                    doencacardicas.setOutrasDoenca(edttxtOutrasDoenca.getText().toString());
                }
                if(chcboxAnginaInstavel.isChecked()){
                    doencacardicas.setAnginaInstavel("Angina Instável");
                }
                if(chcboxAnginaEstavel.isChecked()){
                    doencacardicas.setAnginaEstavel("Angina Estavel");
                }
                if(chcboxArritmia.isChecked()){
                    doencacardicas.setArritmia("Arritmia");
                }
                if(chcboxArtrose.isChecked()){
                    doencacardicas.setArtrose("Artrose");
                }
                if(chcboxAterosclerose.isChecked()){
                    doencacardicas.setAterosclerose("Aterosclerose");
                }
                if(chcboxArterioesclerose.isChecked()){
                    doencacardicas.setArterioesclerose("Arterioesclerose");
                }
                if(chcboxCardiomiopatia.isChecked()){
                    doencacardicas.setCardiomiopatia("Cardiomiopatia");
                }
                if(chcboxCardiopatia.isChecked()){
                    doencacardicas.setCardiopatia("Cardiopatia");
                }
                if(chcboxEndocardite.isChecked()){
                    doencacardicas.setEndocardite("Endocardite");
                }
                if(chcboxEstenoseMitral.isChecked()){
                    doencacardicas.setEstenoseMitral("EstenoseMitral");
                }
                if(chcboxEstenosePulmonar.isChecked()){
                    doencacardicas.setEstenosePulmonar("EstenosePulmonar");
                }
                if(chcboxFibrilacaoAtrial.isChecked()){
                    doencacardicas.setFibrilacaoAtrial("Fibrilacao Atrial");
                }
                if(chcboxHipertensao.isChecked()){
                    doencacardicas.setHipertensao("Hipertensao");
                }
                if(chcboxHipotensao.isChecked()){
                    doencacardicas.setHipotensao("Hipotensao");
                }
                if(chcboxInfarto.isChecked()){
                    doencacardicas.setInfarto("Infarto");
                }
                if(chcboxInsuficienciaCardiaca.isChecked()){
                    doencacardicas.setInsuficienciaCardiaca("Insuficiencia Cardiaca");
                }
                if(chcboxPericardite.isChecked()){
                    doencacardicas.setPericardite("Pericardite");
                }
                if(chcboxProlapso.isChecked()){
                    doencacardicas.setProlapso("Prolapso");
                }
                if(chcboxSopro.isChecked()){
                    doencacardicas.setSopro("Sopro");
                }
                if(chcboxTaquicardia.isChecked()){
                    doencacardicas.setTaquicardia("Taquicardia");
                }
                if(chcboxTumorCardiaco.isChecked()){
                    doencacardicas.setTumorCardiaco("Tumor Cardiaco");
                }
                databaseReference = ConfiguracaoFirebase.getFirebase().child("DoencasCardiacas");
                databaseReference.child(idUsuarioLogado).push().setValue(doencacardicas);

                Toast.makeText(DoencaCardActivity.this, "Doenças registrado com sucesso", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(DoencaCardActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}
