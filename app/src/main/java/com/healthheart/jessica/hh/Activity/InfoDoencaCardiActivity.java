package com.healthheart.jessica.hh.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.healthheart.jessica.hh.R;

public class InfoDoencaCardiActivity extends AppCompatActivity {

    private Button btnVoltarPrinpalDoenCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_doenca_cardi);

        btnVoltarPrinpalDoenCar  = (Button) findViewById(R.id.btnVoltarPrinpalDoenCar);
        btnVoltarPrinpalDoenCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltaDoenCard = new Intent(InfoDoencaCardiActivity.this, DoencaCardActivity.class);
                startActivity(intentVoltaDoenCard);
            }
        });
    }
}
