package com.healthheart.jessica.hh.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.healthheart.jessica.hh.Entidades.Agua;
import com.healthheart.jessica.hh.Entidades.Peso;
import com.healthheart.jessica.hh.R;

import java.util.ArrayList;

/**
 * Created by Jessica on 30/09/2017.
 */

public class AguaAdapter extends ArrayAdapter<Agua> {

    private ArrayList<Agua> agua;
    private Context context;

    public AguaAdapter(Context c, ArrayList<Agua> objects ) {
        super(c,0,objects );
        this.context = c;
        this.agua = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (agua != null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_agua, parent, false);

            TextView txtviwDataAgua = (TextView) view.findViewById(R.id.txtviwDataAgua);
            TextView txtviwQntAgua = (TextView) view.findViewById(R.id.txtviwQntAgua);


            Agua agua2 = agua.get(position);

            txtviwDataAgua.setText("Data de inserção da quantidade de água: " +  agua2.getDataAgua());
            txtviwQntAgua.setText("Quantidade de copos consumido: " +  agua2.getQntCopoTomados());
         }

        return view;
    }

}
