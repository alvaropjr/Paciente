package com.healthheart.jessica.hh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.healthheart.jessica.hh.Activity.FaixasImc;
import com.healthheart.jessica.hh.Entidades.Peso;
import com.healthheart.jessica.hh.Entidades.Pressao;
import com.healthheart.jessica.hh.R;


import java.util.ArrayList;

/**
 * Created by Jessica on 26/09/2017.
 */

public class PesoAdapter extends ArrayAdapter<Peso> {

    private ArrayList<Peso> peso;
    private Context context;

    public PesoAdapter(Context c, ArrayList<Peso> objects) {
        super(c,0,objects);
        this.context = c;
        this.peso = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (peso != null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_peso, parent, false);


            TextView txtDataPeso = (TextView) view.findViewById(R.id.txtDataPeso);
            TextView txtPeso = (TextView) view.findViewById(R.id.txtPeso);
            TextView txtAltura = (TextView) view.findViewById(R.id.txtAltura);
            TextView txtCircuCintura = (TextView) view.findViewById(R.id.txtCircuCintura);
            TextView txtViewImc = (TextView) view.findViewById(R.id.txtViewImc);
           // TextView txtViewImcNormal = (TextView) view.findViewById(R.id.txtViewImcNormal);
           // TextView txtViewImcSobrepeso = (TextView) view.findViewById(R.id.txtViewImcSobrepeso);
          //  TextView txtViewObeso = (TextView) view.findViewById(R.id.txtViewObeso);
            TextView txtViewCircu = (TextView) view.findViewById(R.id.txtViewCircu);
            TextView txtsexo = (TextView) view.findViewById(R.id.txtsexo);

            Peso peso2 = peso.get(position);

            txtDataPeso.setText("Data de inserção das Medidas: " +  peso2.getDataPeso());
            txtPeso.setText("Peso: " +  peso2.getPeso());
            txtAltura.setText("Altura: " +  peso2.getAltura());
            //txtCircuCintura.setText("Circunferencia da Cintura: " +  peso2.getCircuCintura());
            txtsexo.setText("Sexo: " +  peso2.getSexo().toString());


            //calculo imc
           double imc;
            imc =  peso2.getPeso() / (peso2.getAltura() * peso2.getAltura());

            //Baixo peso < 18,5
            if(imc < Peso.FaixaImcAbaixoPeso){
                txtViewImc.setText("Abaixo do Peso: " + imc);
            }else{
                //Peso adequado ≥ 18,5 e < 25
                if (imc >= Peso.FaixaImcAbaixoPeso && imc < Peso.FaixaImcPesoNormal){
                    txtViewImc.setText("Peso Nomal: " + imc);
                }
            }

            //Sobrepeso ≥ 25 e < 30
            if (imc >= Peso.FaixaImcPesoNormal && imc < Peso.FaixaImcSobrePeso){
                txtViewImc.setText("Sobrepeso: " + imc);
            }else {
                //obesidade >30
                if (imc >= Peso.FaixaImcSobrePeso) {
                    txtViewImc.setText("Obesidade: " + imc);
                }
            }


            //circunferencia
            //https://drauziovarella.com.br/obesidade/circunferencia-abdominal/
            //  http://www.sbh.org.br/geral/noticias.asp?id=61

            if (peso2.getSexo() == "Masculino"){
                  txtViewCircu.setText("homem");
                if(peso2.getCircuCintura() >= 94 && peso2.getCircuCintura() < 102){
                    txtViewCircu.setText("Circunferência: Acima do limite normal "  + peso2.getCircuCintura());
                }else{
                    if (peso2.getCircuCintura() >= 102) {
                        txtViewCircu.setText("Circunferência: Muito acima do limite normal "  + peso2.getCircuCintura());
                    }else{
                        txtViewCircu.setText("Circunferência: Dentro dos limetes normais: " + peso2.getCircuCintura());
                    }
                }
            }else{
                txtViewCircu.setText("mulher");
                if (peso2.getCircuCintura() >= 80 &&  peso2.getCircuCintura() < 88){
                    txtViewCircu.setText("Circunferência: Acima do limite normal: " + peso2.getCircuCintura());
                }else{
                    if (peso2.getCircuCintura() >= 88){
                        txtViewCircu.setText("Circunferência: Muito acima do limite normal: " + peso2.getCircuCintura());
                    }else {
                        txtViewCircu.setText("Circunferência: Dentro dos limetes normais: " + peso2.getCircuCintura());
                    }
                }
            }


        }

        return view;
    }
}
