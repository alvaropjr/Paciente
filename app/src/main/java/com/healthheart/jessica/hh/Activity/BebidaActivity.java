package com.healthheart.jessica.hh.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.healthheart.jessica.hh.Entidades.BebidaAlcoolica;
import com.healthheart.jessica.hh.R;

public class BebidaActivity extends AppCompatActivity {

    private ImageButton imgbtnAbriActivityAgua;
    private ImageButton imgbtnOutrasBebidas;
    private Button btnVoltarPrinpal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bebida);


        //Voltar Tela principal
        btnVoltarPrinpal = (Button) findViewById(R.id.btnVoltarPrinpal);
        btnVoltarPrinpal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarTelaPrinci = new Intent(BebidaActivity.this, PrincipalActivity.class);
                startActivity(intentVoltarTelaPrinci);
            }
        });

        //recupera agua
        imgbtnAbriActivityAgua = (ImageButton) findViewById(R.id.imgbtnAgua);

        imgbtnAbriActivityAgua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrirTelaAgua = new Intent(BebidaActivity.this, AguaActivity.class);
                startActivity(intentAbrirTelaAgua);
            }
        });

        imgbtnOutrasBebidas = (ImageButton) findViewById(R.id.imgbtnOutrasBebidas);

        imgbtnOutrasBebidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrirTelaBbAlcoo = new Intent(BebidaActivity.this, BbAlcoolicaActivity.class);
                startActivity(intentAbrirTelaBbAlcoo);
            }
        });

    }
}
