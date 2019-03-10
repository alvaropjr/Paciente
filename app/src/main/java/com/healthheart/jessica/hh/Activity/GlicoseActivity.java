package com.healthheart.jessica.hh.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.healthheart.jessica.hh.DAO.ConfiguracaoFirebase;
import com.healthheart.jessica.hh.Entidades.Glicose;
import com.healthheart.jessica.hh.Helper.PreferenciasAndroid;
import com.healthheart.jessica.hh.R;

import java.util.UUID;


public class GlicoseActivity extends AppCompatActivity {

    private EditText edttxtDataGlicose;
    private EditText edttxtQntGlicose;
    private EditText edttxtQntInsulina;
    private RadioButton rbJejum;
    private RadioButton rbPreRejeicao;
    private RadioButton rbPosRefeicao;
    private RadioButton rbAntesDormir;

    private Button btnSalvarGlicose;
    private Button btnCancGlicose;

    private Button btnHistGlicose;
    private Button btnInfoGlico;

    private Button btnVoltarPrinpal;

    private Glicose glicose;

    //dados usario logado
    private  String idUsuarioLogado;

    private FirebaseAuth autenticacao;

    //Objeto de conexao
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glicose);

        //Voltar Tela principal
        btnVoltarPrinpal = (Button) findViewById(R.id.btnVoltarPrinpal);
        btnVoltarPrinpal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarTelaPrinci = new Intent(GlicoseActivity.this, PrincipalActivity.class);
                startActivity(intentVoltarTelaPrinci);
            }
        });

        btnHistGlicose = (Button) findViewById(R.id.btnHistGlicose) ;
        btnHistGlicose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentIrHist = new Intent(GlicoseActivity.this, HistoricoGlicoseActivity.class);
                startActivity(intentIrHist);
            }
        });

        btnInfoGlico = (Button) findViewById(R.id.btnInfoGlico) ;
        btnInfoGlico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentIrInfo = new Intent(GlicoseActivity.this, InfoGlicoseActivity.class);
                startActivity(intentIrInfo);
            }
        });

        //cancela glicose
        btnCancGlicose  = (Button) findViewById(R.id.btnCancGlicose) ;
        btnCancGlicose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCancGlico = new Intent(GlicoseActivity.this, GlicoseActivity.class);
                startActivity(intentCancGlico);
            }
        });


        //recupera identificador usuario
        PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(GlicoseActivity.this);
        idUsuarioLogado = preferenciasAndroid.getIdentificador();

        //recupera o valor dos campos e botão
        edttxtDataGlicose = (EditText) findViewById(R.id.edttxtDataGlicose);
        edttxtQntGlicose = (EditText) findViewById(R.id.edttxtQntGlicose);
        edttxtQntInsulina = (EditText) findViewById(R.id.edttxtQntInsulina);
        rbJejum = (RadioButton) findViewById(R.id.rbJejum);
        rbPreRejeicao = (RadioButton) findViewById(R.id.rbPreRejeicao);
        rbPosRefeicao = (RadioButton) findViewById(R.id.rbPosRefeicao);
        rbAntesDormir = (RadioButton) findViewById(R.id.rbAntesDormir);
        btnSalvarGlicose = (Button) findViewById(R.id.btnSalvarGlicose);
        btnCancGlicose = (Button) findViewById(R.id.btnCancGlicose);

       //Inicializa banco de dados
        inicializarFirebase();

       btnSalvarGlicose.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (!edttxtDataGlicose.getText().toString().equals("")
                       && !edttxtQntGlicose.getText().toString().equals("")
                       && !edttxtQntInsulina.getText().toString().equals("")){

                   glicose = new Glicose();
                   //  glicose.setIdGlicose(UUID.randomUUID().toString());
                   glicose.setIdUsuario(idUsuarioLogado);
               /*glicose.setDataGlicose(Date.valueOf(edttxtDataGlicose.getText().toString())); */
                   glicose.setDataGlicose(edttxtDataGlicose.getText().toString());
                   glicose.setQntGlicose(Double.parseDouble(edttxtQntGlicose.getText().toString()));
                   glicose.setQntInsulina(Double.parseDouble(edttxtQntInsulina.getText().toString()));
                   // glicose.setQntGlicose(Float.parseFloat(edttxtQntGlicose.getText().toString()));
                   // glicose.setQntInsulina(Float.parseFloat(edttxtQntInsulina.getText().toString()));
                   if(rbJejum.isChecked()){
                       glicose.setStatusGlicose("Jejum");
                   }
                   if(rbPreRejeicao.isChecked()){
                       glicose.setStatusGlicose("Pre Refeição");
                   }
                   if(rbPosRefeicao.isChecked()){
                       glicose.setStatusGlicose("Pos Refeição");
                   }
                   if(rbAntesDormir.isChecked()){
                       glicose.setStatusGlicose("Antes de Dormir");
                   }

                   // databaseReference.child("Glicose").child(glicose.getIdGlicose()).setValue(glicose);
                   databaseReference = ConfiguracaoFirebase.getFirebase().child("Glicose");
                   databaseReference.child(idUsuarioLogado).push().setValue(glicose);

                   Toast.makeText(GlicoseActivity.this, "Glicose registrado com sucesso", Toast.LENGTH_SHORT).show();

               }else{
                   Toast.makeText(GlicoseActivity.this, "Preencha Data, Qtn. Glicose e Insulina e escolha um período", Toast.LENGTH_SHORT).show();
               }


           }
       });



    }



    private void inicializarFirebase() {
        FirebaseApp.initializeApp(GlicoseActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

}
