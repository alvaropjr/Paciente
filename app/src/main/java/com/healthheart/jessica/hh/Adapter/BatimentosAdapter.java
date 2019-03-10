package com.healthheart.jessica.hh.Adapter;

/**
 * Created by Jessica on 21/10/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.healthheart.jessica.hh.Entidades.Batimentos;
import com.healthheart.jessica.hh.R;

import java.util.ArrayList;

public class BatimentosAdapter extends ArrayAdapter<Batimentos> {
    private ArrayList<Batimentos> batimentos;
    private Context context;

    public BatimentosAdapter(Context c, ArrayList<Batimentos> objects){
        super(c, 0, objects);
        this.context = c;
        this.batimentos = objects;
    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent){
        View view = null;
        if (batimentos != null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_batimentos, parent, false);

            TextView txtDataBatimento = (TextView) view.findViewById(R.id.txtDataBatimento);
            TextView txtPerioBatimento = (TextView) view.findViewById(R.id.txtPerioBatimento);
            TextView txtBatimentos = (TextView) view.findViewById(R.id.txtBatimentos);
            TextView txtViewInfBat = (TextView) view.findViewById(R.id.txtViewInfBat);
            TextView txtViewObsBat = (TextView) view.findViewById(R.id.txtViewObsBat);
            TextView txtViewObsBatInf = (TextView) view.findViewById(R.id.txtViewObsBatInf);

            Batimentos batimentos2 = batimentos.get(position);

            txtDataBatimento.setText("Data dos Batimentos: " +  batimentos2.getDataBatimento());
            txtPerioBatimento.setText("Período que foi medido os Batimentos: " +  batimentos2.getPeridoBatimento());
            txtBatimentos.setText("Batimentos:" +  batimentos2.getBatimentos() + "BPM");


            //fonte http://saude.ig.com.br/bemestar/calculando-a-frequencia-cardiaca/n1596962277487.html
            //10 a 50 bpm       Bradicardia        Sensação de fraqueza, com espaço de tempo maior entre os batimentos cardíacos
            if (batimentos2.getBatimentos() >=10 && batimentos2.getBatimentos() < 50 ){
                txtViewInfBat.setText("Bradicardia: Retardamento do ritmo cardíaco abaixo de uma frequência de 60 batimentos por minuto");
                txtViewObsBat.setText("Caso os seu batimentos continue nesse ritmo procure ajuda Médica");
            }else{
                //60 a 100 bpm normal
                if(batimentos2.getBatimentos() >= 60 && batimentos2.getBatimentos() <= 100 ){
                    txtViewInfBat.setText("Batimentos Normais");
                    txtViewObsBatInf.setText("Obs. Parâmetros normais para adultos acima de 20 anos, sem doenças cardicas");
                }else{
                    //110 a 140     Taquicardia        Sensação de coração acelerado, com batimentos cardíacos mais rápidos
                    if (batimentos2.getBatimentos() >= 110 && batimentos2.getBatimentos() >= 140 );
                    txtViewInfBat.setText("Taquicardia: Sensação de coração acelerado, com batimentos cardíacos mais rápido");
                    txtViewObsBat.setText("Caso os seu batimentos continue nesse ritmo procure ajuda Médica");
                }
            }
        }


        return view;
    }

}
