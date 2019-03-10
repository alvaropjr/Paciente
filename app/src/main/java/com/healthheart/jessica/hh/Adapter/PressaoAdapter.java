package com.healthheart.jessica.hh.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.healthheart.jessica.hh.Entidades.Pressao;
import com.healthheart.jessica.hh.R;

import java.util.ArrayList;

/**
 * Created by Jessica on 26/09/2017.
 */

public class PressaoAdapter extends ArrayAdapter<Pressao> {


    private ArrayList<Pressao> pressao;
    private Context context;

    public PressaoAdapter(Context c, ArrayList<Pressao> objects) {
        super(c,0,objects);
        this.context = c;
        this.pressao = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (pressao != null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_pressao, parent, false);

            TextView txtDataPressao = (TextView) view.findViewById(R.id.txtDataPressao);
            TextView txtSistolica = (TextView) view.findViewById(R.id.txtSistolica);
            TextView txtDiastolica = (TextView) view.findViewById(R.id.txtDiastolica);
            TextView txtStatusPressao = (TextView) view.findViewById(R.id.txtStatusPressao);
            TextView txtObsPressaoBaixa = (TextView) view.findViewById(R.id.txtObsPressaoBaixa);
            TextView txtObsPressaoAlta = (TextView) view.findViewById(R.id.txtObsPressaoAlta);

            Pressao pressao2 = pressao.get(position);

            txtDataPressao.setText("Data da Medição da Pressã: " +  pressao2.getDataPressao());
            txtSistolica.setText("Sistolica: " +  pressao2.getSistolica() + "mmHg");
            txtDiastolica.setText("Diastolica: " +  pressao2.getDiastolica() + "mmHg");

            //Pressao baixa
            if (pressao2.getSistolica() <= 100 && pressao2.getDiastolica() <= 60){
                txtStatusPressao.setText("Status: Pressão Arterial Baixa");
                txtObsPressaoBaixa.setText("Obs: Caso não tenha sido diagnosticado com presão arterial baixa procure um médico, caso se repita os números.");
            }else{
                if(pressao2.getSistolica() <= 120 && pressao2.getDiastolica() <= 80){
                    txtStatusPressao.setText("Status: Pressão Arterial Ótima");
                }else {
                    if(pressao2.getSistolica() <= 130 && pressao2.getDiastolica() <= 85){
                        txtStatusPressao.setText("Status: Pressão Arterial Normal");
                    }else{
                        //Pressao alta
                        if (pressao2.getSistolica() >= 140 && pressao2.getDiastolica() >= 90){
                            txtStatusPressao.setText("Status: Pressão Arterial Alta");
                            txtObsPressaoAlta.setText("Obs: Caso não tenha sido diagnosticado com presão arterial alta procure um médico, caso se repita os números .");

                        }
                    }
                }
            }

            //https://coracaoalerta.com.br/fique-alerta/saude-e-afetada-tanto-por-pressao-alta-quanto-por-baixa/



        }

       return view;
    }
}
