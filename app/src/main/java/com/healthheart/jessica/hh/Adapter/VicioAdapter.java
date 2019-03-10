package com.healthheart.jessica.hh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.healthheart.jessica.hh.Entidades.Vicios;
import com.healthheart.jessica.hh.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Jessica on 21/10/2017.
 */

public class VicioAdapter extends ArrayAdapter<Vicios> {
    private ArrayList<Vicios> vicios;
    private Context context;

    public VicioAdapter(Context c, ArrayList<Vicios> objects){
        super(c, 0, objects);
        this.context = c;
        this.vicios = objects;
    }

    @Override
   // public View getView(int position, View convertView, ViewGroup parent) {
    public View getView(int position, View convertView, ViewGroup parent){
        View view = null;
        if (vicios != null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_vicio, parent, false);

            //TextView txtViewDataMedicamento = (TextView) view.findViewById(R.id.txtViewDataMedicamento);
            TextView txtVicioDroga = (TextView) view.findViewById(R.id.txtVicioDroga);
            TextView txtNomeDrogas = (TextView) view.findViewById(R.id.txtNomeDrogas);
            TextView txtVicioAlcool = (TextView) view.findViewById(R.id.txtVicioAlcool);
            TextView txtNomeAlcool = (TextView) view.findViewById(R.id.txtNomeAlcool);
            TextView txtVicioMedi = (TextView) view.findViewById(R.id.txtVicioMedi);
            TextView txtNomeMedi = (TextView) view.findViewById(R.id.txtNomeMedi);

            Vicios vicios2 = vicios.get(position);


            txtVicioDroga.setText(vicios2.getVicioDrogas());
            txtNomeDrogas.setText("Nome da Drogas: " + vicios2.getDrogas());
            txtVicioAlcool.setText(vicios2.getVicioAlcool());
            txtNomeAlcool.setText("Nome Bebidas Alcoólicas: " + vicios2.getBebidaAlcool());
            txtVicioMedi.setText(vicios2.getVicioMedica());
            txtNomeMedi.setText("Nome da Medicação: " + vicios2.getMedica());
        }
        return view;
    }
}
