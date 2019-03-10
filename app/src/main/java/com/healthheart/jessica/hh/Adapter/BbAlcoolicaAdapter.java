package com.healthheart.jessica.hh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.healthheart.jessica.hh.Entidades.Agua;
import com.healthheart.jessica.hh.Entidades.BebidaAlcoolica;
import com.healthheart.jessica.hh.R;

import java.util.ArrayList;

/**
 * Created by Jessica on 15/11/2017.
 */

public class BbAlcoolicaAdapter extends ArrayAdapter<BebidaAlcoolica> {

    private ArrayList<BebidaAlcoolica> bebidaAlcoolica;
    private Context context;

    public BbAlcoolicaAdapter(Context c, ArrayList<BebidaAlcoolica> objects ) {
        super(c,0,objects );
        this.context = c;
        this.bebidaAlcoolica = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (bebidaAlcoolica != null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_bb_alcoo, parent, false);

            TextView txtDataBbAlco = (TextView) view.findViewById(R.id.txtDataBbAlco);
            TextView txtNomeBbAlco = (TextView) view.findViewById(R.id.txtNomeBbAlco);
            TextView txtQntBbAlco = (TextView) view.findViewById(R.id.txtQntBbAlco);
            TextView txtPeriBbAlco = (TextView) view.findViewById(R.id.txtPeriBbAlco);


            BebidaAlcoolica bebidaAlcoolica2 = bebidaAlcoolica.get(position);

            txtDataBbAlco.setText("Data de inserção da quantidade de bebida: " +  bebidaAlcoolica2.getDataBbAlcoo());
            txtNomeBbAlco.setText("Nome da bebida: " +  bebidaAlcoolica2.getNomeBbAlcoo());
            txtQntBbAlco.setText("Quantidade de bebida: " +  bebidaAlcoolica2.getQntCopoTomadosBbAlcoo());
            txtPeriBbAlco.setText("Período do consumo da bebida: " +  bebidaAlcoolica2.getPeriodoBbAlcoo());

        }

        return view;
    }

}


