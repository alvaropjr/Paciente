package com.healthheart.jessica.hh.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.healthheart.jessica.hh.R;

public class InfoOxigenacaoActivity extends AppCompatActivity {
    private Button btnVoltarPrinpalOxi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_oxigenacao);

        //Voltar Tela principal
        btnVoltarPrinpalOxi = (Button) findViewById(R.id.btnVoltarPrinpalOxi);
        btnVoltarPrinpalOxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarTelaPrinci = new Intent(InfoOxigenacaoActivity.this, OxigenacaoActivity.class);
                startActivity(intentVoltarTelaPrinci);
            }
        });
    }
}
