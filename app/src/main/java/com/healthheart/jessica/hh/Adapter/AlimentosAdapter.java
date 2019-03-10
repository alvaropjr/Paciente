package com.healthheart.jessica.hh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.healthheart.jessica.hh.Entidades.Agua;
import com.healthheart.jessica.hh.Entidades.Alimentos;
import com.healthheart.jessica.hh.R;

import java.util.ArrayList;

/**
 * Created by Jessica on 18/11/2017.
 */

public class AlimentosAdapter extends ArrayAdapter<Alimentos>{

    private ArrayList<Alimentos> alimentos;
    private Context context;

    public AlimentosAdapter(Context c, ArrayList<Alimentos> objects ) {
        super(c,0,objects );
        this.context = c;
        this.alimentos = objects;
    }


    /*public class AguaAdapter extends ArrayAdapter<Agua> {

    */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (alimentos != null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_alimentos, parent, false);

            TextView txtViewDataAli = (TextView) view.findViewById(R.id.txtViewDataAli);
            TextView txtViewPeriodoAli = (TextView) view.findViewById(R.id.txtViewPeriodoAli);
            TextView txtNomeAli = (TextView) view.findViewById(R.id.txtNomeAli);
            TextView txtNomeBeb = (TextView) view.findViewById(R.id.txtNomeBeb);


            Alimentos alimentos2 = alimentos.get(position);

            txtViewDataAli.setText("Data de inserção do alimento: " +  alimentos2.getDataAlimento());
            txtViewPeriodoAli.setText("Período: " +  alimentos2.getPeridoAlimento());
            txtNomeAli.setText("Nome do alimento: " +  alimentos2.getNomeAlimento());
            txtNomeBeb.setText("Nome da Bebida: " +  alimentos2.getNomeBebida());

        }

        return view;
    }
}
