package com.healthheart.jessica.hh.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.healthheart.jessica.hh.R;

public class InfoGlicoseActivity extends AppCompatActivity {
   private Button btnVoltarglicose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_glicose);

        //Voltar Tela principal glicose
     /*   btnVoltarglicose = (Button) findViewById(R.id.btnVoltarPrinpal);
        btnVoltarglicose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarglicose = new Intent(InfoGlicoseActivity.this, GlicoseActivity.class);
                startActivity(intentVoltarglicose);
            }
        }); */

        btnVoltarglicose = (Button) findViewById(R.id.btnVoltarglicose);
        btnVoltarglicose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarGlico = new Intent(InfoGlicoseActivity.this, GlicoseActivity.class);
                startActivity(intentVoltarGlico);
            }
        });

    }
}
