package com.healthheart.jessica.hh.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.healthheart.jessica.hh.Entidades.Peso;

import com.healthheart.jessica.hh.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ImcCircuferenciaActivity extends AppCompatActivity {

    private Peso peso;

    private TextView txtViewPeso;
    private TextView txtViewAltura;
    private TextView txtViewImc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc_circuferencia);

        txtViewPeso = (TextView) findViewById(R.id.txtViewPeso);
        txtViewAltura = (TextView) findViewById(R.id.txtViewAltura);
        txtViewImc = (TextView) findViewById(R.id.txtViewImc);

    }
}
