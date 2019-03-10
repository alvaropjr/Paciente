package com.healthheart.jessica.hh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.healthheart.jessica.hh.Entidades.Batimentos;
import com.healthheart.jessica.hh.Entidades.Oxigenacao;
import com.healthheart.jessica.hh.R;

import java.util.ArrayList;

/**
 * Created by Jessica on 21/10/2017.
 */

public class OxigenacaoAdapter extends ArrayAdapter<Oxigenacao> {
    private ArrayList<Oxigenacao> oxigenacao;
    private Context context;

    public OxigenacaoAdapter(Context c, ArrayList<Oxigenacao> objects){
        super(c, 0, objects);
        this.context = c;
        this.oxigenacao = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = null;
        if (oxigenacao != null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_oxigenacao, parent, false);

            TextView txtDataOxi = (TextView) view.findViewById(R.id.txtDataOxi);
            TextView txtOxi = (TextView) view.findViewById(R.id.txtOxi);
            TextView txtTaxaOxi = (TextView) view.findViewById(R.id.txtTaxaOxi);
            TextView txtObsOxi = (TextView) view.findViewById(R.id.txtObsOxi);
            TextView txtAlertaOxi = (TextView) view.findViewById(R.id.txtAlertaOxi);
            TextView txtOxiPeriodo = (TextView) view.findViewById(R.id.txtOxiPeriodo);

           // Batimentos batimentos2 = batimentos.get(position);
            Oxigenacao oxigenacao2 = oxigenacao.get(position);

            txtDataOxi.setText("Data da Oxigenação: " +  oxigenacao2.getDataOxigenacao());
            txtOxi.setText("Oxigenação: " +  oxigenacao2.getOxigenacao() + "%");
            txtOxiPeriodo.setText("Período da Oxigenação Sanguínea: " +  oxigenacao2.getPeridoOxigenacao());

            //if (batimentos2.getBatimentos() >=10 && batimentos2.getBatimentos() < 50 ){
            if(oxigenacao2.getOxigenacao() >= 95 && oxigenacao2.getOxigenacao() <= 100){
                txtTaxaOxi.setText("Oxigenação adequada");
                txtObsOxi.setText("Obs: Parametros normais para adultos saudáveis.");
            }else {
                if(oxigenacao2.getOxigenacao() <= 90){
                    txtTaxaOxi.setText("Oxigenação Inadequada");
                    txtAlertaOxi.setText("Caso sua oxigenaçao sanguínea continue abaixo de 90% procure ajuda Médica");
                }
            }

        }


        return view;
    }


}
