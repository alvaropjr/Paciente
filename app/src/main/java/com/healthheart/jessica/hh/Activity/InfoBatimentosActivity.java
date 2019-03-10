package com.healthheart.jessica.hh.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.healthheart.jessica.hh.R;

public class InfoBatimentosActivity extends AppCompatActivity {
    private Button btnVoltarPrinpalBati;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_batimentos);

        //Voltar Tela principal
        btnVoltarPrinpalBati = (Button) findViewById(R.id.btnVoltarPrinpalBati);
        btnVoltarPrinpalBati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarTelaPrinci = new Intent(InfoBatimentosActivity.this, BatimentosActivity.class);
                startActivity(intentVoltarTelaPrinci);
            }
        });
    }
}
