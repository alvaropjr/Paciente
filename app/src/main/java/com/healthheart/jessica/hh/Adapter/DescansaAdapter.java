package com.healthheart.jessica.hh.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.healthheart.jessica.hh.Entidades.Descanso;
import com.healthheart.jessica.hh.Entidades.Glicose;
import com.healthheart.jessica.hh.Entidades.Peso;
import com.healthheart.jessica.hh.Entidades.Pressao;
import com.healthheart.jessica.hh.R;

import java.util.ArrayList;

/**
 * Created by Jessica on 30/09/2017.
 */

public class DescansaAdapter extends ArrayAdapter<Descanso> {

    private ArrayList<Descanso> descanso;
    private Context context;

    public DescansaAdapter(Context c, ArrayList<Descanso> objects) {
            super(c,0,objects);
            this.context = c;
            this.descanso = objects;
    }
 @Override
    public  View getView(int position, View converView, ViewGroup parent) {
        View view = null;
        if (descanso != null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_descanso, parent, false);

            TextView txtManha = (TextView) view.findViewById(R.id.txtManha);
            TextView txtBom = (TextView) view.findViewById(R.id.txtBom);
            TextView txtDataDescanso = (TextView) view.findViewById(R.id.txtDataDescanso);
            TextView txtHoradesca = (TextView) view.findViewById(R.id.txtHoradesca);
            TextView txtQntAcordou = (TextView) view.findViewById(R.id.txtQntAcordou);


            Descanso descanso2 = descanso.get(position);

            txtManha.setText("Período do Descanso: " + descanso2.getPeriodoDescanso());
            txtBom.setText("Status do Descanso: " + descanso2.getStatusDescanso());
            txtDataDescanso.setText("Data de inserção do Descanso: " + descanso2.getDataDescanso());
            txtHoradesca.setText("Quantidade de horas de Descanso: " + descanso2.getHorasDescanso());
            txtQntAcordou.setText("Quantidade de vez que acordou: " + descanso2.getQntVezAcordou());

            //fonte http://www.bbc.com/portuguese/noticias/2015/02/150210_sono_idade_lgb

        }
        return view;
    }
    }