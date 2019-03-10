package com.healthheart.jessica.hh.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.healthheart.jessica.hh.R;

public class SaudeActivity extends AppCompatActivity {

    private ImageButton imgbtnAbriActivityMedicamento;

    private ImageButton imgbtnAbriActivityHisDoeCar;

    private ImageButton imgbntVicio;

    private Button btnVoltarPrinpal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saude);

        //Voltar Tela principal
        btnVoltarPrinpal = (Button) findViewById(R.id.btnVoltarPrinpal);
        btnVoltarPrinpal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarTelaPrinci = new Intent(SaudeActivity.this, PrincipalActivity.class);
                startActivity(intentVoltarTelaPrinci);
            }
        });


        //recupera a imgageButton abrir medicamento
        imgbtnAbriActivityMedicamento = (ImageButton) findViewById(R.id.imgbntMedicamento);

        imgbtnAbriActivityMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrieTelaMedica = new Intent(SaudeActivity.this, MedicacaoActivity.class);
                startActivity(intentAbrieTelaMedica);
            }
        });


        //recupera a imgageButton abrir Historico doenças cardiacas
       imgbtnAbriActivityHisDoeCar = (ImageButton) findViewById(R.id.imgbntHisDoencaCardi);

        imgbtnAbriActivityHisDoeCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrieTelaDoeCar = new Intent(SaudeActivity.this, DoencaCardActivity.class);
                startActivity(intentAbrieTelaDoeCar);
            }
        });

        //imgbntVicio
        imgbntVicio = (ImageButton) findViewById(R.id.imgbntVicio);

        imgbntVicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrieTelaVicio = new Intent(SaudeActivity.this, VicioActivity.class);
                startActivity(intentAbrieTelaVicio);
            }
        });
    }
}
