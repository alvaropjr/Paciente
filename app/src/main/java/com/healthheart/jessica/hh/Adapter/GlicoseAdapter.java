package com.healthheart.jessica.hh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.healthheart.jessica.hh.Entidades.Glicose;
import com.healthheart.jessica.hh.R;

import java.util.ArrayList;

/**
 * Created by Jessica on 12/11/2017.
 */

public class GlicoseAdapter extends ArrayAdapter<Glicose> {

    private ArrayList<Glicose> glicose;
    private Context context;

    public GlicoseAdapter(Context c, ArrayList<Glicose> objects) {
        super(c, 0, objects);
        this.context = c;
        this.glicose = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (glicose != null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_glicose, parent, false);

            TextView txtDataGlicose = (TextView) view.findViewById(R.id.txtDataGlicose);
            TextView txtQntGlicose = (TextView) view.findViewById(R.id.txtQntGlicose);
            TextView txtQntInsulina = (TextView) view.findViewById(R.id.txtQntInsulina);
            TextView txtstaus = (TextView) view.findViewById(R.id.txtstaus);
            TextView txtViewGlicemia = (TextView) view.findViewById(R.id.txtViewGlicemia);
            TextView txtViewObsglicose = (TextView) view.findViewById(R.id.txtViewObsglicose);
            //  TextView txtJejum = (TextView) view.findViewById(R.id.txtQntInsulina);
            // TextView txtPreRejeicao = (TextView) view.findViewById(R.id.txtPreRejeicao);
            // TextView txtPosRefeicao = (TextView) view.findViewById(R.id.txtPosRefeicao);


            Glicose glicose2 = glicose.get(position);

            txtDataGlicose.setText("Data da Medição: " + glicose2.getDataGlicose());
            txtQntGlicose.setText("Quantidade de Glicose: " + glicose2.getQntGlicose() + "mg/dL");
            txtQntInsulina.setText("Quantidade de Isulina: " + glicose2.getQntInsulina());
            txtstaus.setText("Status Medição da Glicose: " + glicose2.getStatusGlicose());
            //txtJejum.setText("Status Medição da Glicose: " +  glicose2.getStatusGlicose());
            //txtPreRejeicao.setText("Status Medição da Glicose: " +  glicose2.getStatusGlicose());
            //txtPosRefeicao.setText("Status Medição da Glicose: " +  glicose2.getStatusGlicose());


            //fontes
            // glicemia é a concentração de glicose
            //http://comotersaude.com/o-que-e-glicose-alta-ou-glicemia-alta/
            //https://www.news-medical.net/health/Blood-Sugar-Normal-Values-(Portuguese).aspx
            //https://taxanormalglicoseaposrefeicoes.000webhostapp.com
            //http://www.doctoralia.com.br/provamedica/niveis+de+glicose+no+sangue-19533

            if (glicose2.getStatusGlicose().equals("Jejum")) {
                if (glicose2.getQntGlicose() < 100) {
                    txtViewGlicemia.setText("Níveis de açucar normais");
                } else {
                    if (glicose2.getQntGlicose() >= 110 && glicose2.getQntGlicose() < 126) {
                        txtViewGlicemia.setText("Glicemia alterada - Procure Orientação médica de saúde se os números se repitirem");
                    } else {
                        if (glicose2.getQntGlicose() >= 126) {
                            txtViewGlicemia.setText("Glicemia muito alterada possível risco de diabetes - Procure um profissional de saúde, caso esse número se repita por muitas vezes");
                        }
                    }
                }
            }
            if (glicose2.getStatusGlicose().equals("Pre Refeição")) {
                //70 a 99
                if (glicose2.getQntGlicose() >= 70 && glicose2.getQntGlicose() <= 99) {
                    txtViewGlicemia.setText("Níveis normais de açucar no sangue ");
                    txtViewObsglicose.setText("\nObs.Caso possua diabetes, a American Diabetes Association acoselha manter seus níveis de açúcares no sangue antes das refeições de 80-130 mg/dl");
                } else {
                    txtViewGlicemia.setText("Níveis de açucar no sangue alterados - Procure Orientação médica se os números se repitirem");
                    txtViewObsglicose.setText("\nObs.Caso possua diabetes, a American Diabetes Association acoselha manter seus níveis de açúcares no sangue antes das refeições de 80-130 mg/dl");
                }
            }
            if (glicose2.getStatusGlicose().equals("Pos Refeição")) {
                //<140
                if (glicose2.getQntGlicose() <= 140) {
                    txtViewGlicemia.setText("Níveis normais de açucar no sangue  ");
                    txtViewObsglicose.setText("\nObs.Caso possua diabetes, a American Diabetes Association acoselha manter seus níveis de açúcares no sangue após as refeições menores que 180 mg/dl");
                } else {
                    txtViewGlicemia.setText("Níveis  de açucar no sangue alterados - Procure Orientação médica se os números se repitirem ");
                    txtViewObsglicose.setText("\nObs.Caso possua diabetes, a American Diabetes Association acoselha manter seus níveis de açúcares no sangue após as refeições menores que 180 mg/dl");
                }
            }

            if (glicose2.getStatusGlicose().equals("Antes de Dormir")) {
                if (glicose2.getQntGlicose() >= 126 && glicose2.getQntGlicose() < 180) {
                    //O nível de glicose no sangue na hora de dormir deve ser entre 126-180 mg/dl
                    txtViewGlicemia.setText("Níveis normais de açucar no sangue ");
                } else {
                    txtViewGlicemia.setText("Níveis  de açucar no sangue alterados - Procure Orientação médica se os números se repitirem ");
                }

            }

        }
        return view;
    }
}
