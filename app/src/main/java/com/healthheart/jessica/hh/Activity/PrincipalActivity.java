package com.healthheart.jessica.hh.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.healthheart.jessica.hh.DAO.ConfiguracaoFirebase;
import com.healthheart.jessica.hh.R;

public class PrincipalActivity extends AppCompatActivity {

    //Abrir tela de batimento cardiaco
    private ImageButton imgbtnAbriActivityBat;

    private  ImageButton imgbtnAbriActivityOxi;

    private  ImageButton imgbtnAbriActivityPre;

    private  ImageButton imgbtnAbriActivityPeso;

    private  ImageButton imgbtnAbriActivityGlicoce;

    private  ImageButton imgbtnAbriActivityMedicamento;

    private ImageButton imgbtnAbriActivityDescanso;

    private ImageButton imgbtnAbriActivitySaude;

    private ImageButton imgbtnAlimento;


    private Button btnchat;


    private ImageButton imgbtnBebidas;



    private Button btnDeslogar;
    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        btnDeslogar = (Button) findViewById(R.id.btnDeslogar);
        btnDeslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
                autenticacao.signOut();

                Intent intentDesloga = new Intent(PrincipalActivity.this, LoginActivity.class);
                startActivity(intentDesloga);

            }
        });

        btnchat = (Button) findViewById(R.id.btnchat);

        btnchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrieChat = new Intent(PrincipalActivity.this, ChatPacienteActivity.class);
                startActivity(intentAbrieChat);
            }
        });

        //recupera a imgageButton batimentos
        imgbtnAbriActivityBat = (ImageButton) findViewById(R.id.imgbntBatimento);

        imgbtnAbriActivityBat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrieTelaBatimento = new Intent(PrincipalActivity.this, BatimentosActivity.class);
                startActivity(intentAbrieTelaBatimento);
            }
        });




        //recupera a imgageButton abrir oxigenação
        imgbtnAbriActivityOxi = (ImageButton) findViewById(R.id.imgbntOxigenacao);

        imgbtnAbriActivityOxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrieTelaOxigenacao = new Intent(PrincipalActivity.this, OxigenacaoActivity.class);
                startActivity(intentAbrieTelaOxigenacao);
            }
        });

        //recupera a imgageButton abrir pressao
        imgbtnAbriActivityPre = (ImageButton) findViewById(R.id.imgbtnPressao);

        imgbtnAbriActivityPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrieTelaPressao = new Intent(PrincipalActivity.this, PressaoActivity.class);
                startActivity(intentAbrieTelaPressao);
            }
        });


        //recupera a imgageButton abrir peso
        imgbtnAbriActivityPeso = (ImageButton) findViewById(R.id.imgbtnPeso);

        imgbtnAbriActivityPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrieTelaPeso = new Intent(PrincipalActivity.this, PesoActivity.class);
                startActivity(intentAbrieTelaPeso);
            }
        });


        //recupera a imgageButton abrir glicose
        imgbtnAbriActivityGlicoce = (ImageButton) findViewById(R.id.imgbtnGlicose);

        imgbtnAbriActivityGlicoce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrieTelaGlicose = new Intent(PrincipalActivity.this, GlicoseActivity.class);
                startActivity(intentAbrieTelaGlicose);
            }
        });



        //recupera a imgageButton abrir saude
        imgbtnAbriActivitySaude = (ImageButton) findViewById(R.id.imgbtnSaude);

        imgbtnAbriActivitySaude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrieTelaSaude = new Intent(PrincipalActivity.this, SaudeActivity.class);
                startActivity(intentAbrieTelaSaude);
            }
        });

        //recupera bebida
        imgbtnBebidas = (ImageButton) findViewById(R.id.imgbtnBebidas);

        imgbtnBebidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrirTelaBebida = new Intent(PrincipalActivity.this, BebidaActivity.class);
                startActivity(intentAbrirTelaBebida);
            }
        });


        //recupera descanso
        imgbtnAbriActivityDescanso = (ImageButton) findViewById(R.id.imgbtnDescanso);
        imgbtnAbriActivityDescanso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrirTelaDescanso = new Intent(PrincipalActivity.this, DescansoActivity.class);
                startActivity(intentAbrirTelaDescanso);
            }
        });


        imgbtnAlimento = (ImageButton) findViewById(R.id.imgbtnAlimento);
        imgbtnAlimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrirTelaAlim = new Intent(PrincipalActivity.this, AlimentosActivity.class);
                startActivity(intentAbrirTelaAlim);
            }
        });


    }
}
