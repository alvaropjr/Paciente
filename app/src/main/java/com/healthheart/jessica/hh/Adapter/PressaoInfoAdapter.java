package com.healthheart.jessica.hh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.healthheart.jessica.hh.Entidades.Pressao;
import com.healthheart.jessica.hh.Entidades.PressaoInfo;
import com.healthheart.jessica.hh.R;

import java.util.ArrayList;

/**
 * Created by Jessica on 12/10/2017.
 */

public class PressaoInfoAdapter extends ArrayAdapter<PressaoInfo> {

    private ArrayList<PressaoInfo> pressaoInfo;
    private Context context;

    public PressaoInfoAdapter(Context c, ArrayList<PressaoInfo> objects){
        super(c,0,objects);
        this.context = c;
        this.pressaoInfo = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = null;
        if(pressaoInfo != null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_info_pressao, parent, false);

            TextView txtViewTituInfPressao = (TextView) view.findViewById(R.id.txtViewTituInfPressao);
            TextView txtViewTexInfPressao = (TextView) view.findViewById(R.id.txtViewTexInfPressao);

            PressaoInfo pressaoInfo2 = pressaoInfo.get(position);

            txtViewTituInfPressao.setText(pressaoInfo2.getTitulo());
            txtViewTexInfPressao.setText(pressaoInfo2.getTexto());
        }
        return view;
    }
}
