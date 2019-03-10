package com.healthheart.jessica.hh.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.healthheart.jessica.hh.Entidades.DoencaCardiacas;
import com.healthheart.jessica.hh.Entidades.Peso;
import com.healthheart.jessica.hh.R;

import java.util.ArrayList;

/**
 * Created by Jessica on 30/09/2017.
 */

public class DoencaCardiacaAdapter extends ArrayAdapter<DoencaCardiacas> {

    private ArrayList<DoencaCardiacas> doencacardiaca;
    private Context context;


    public DoencaCardiacaAdapter(Context c, ArrayList<DoencaCardiacas> objects) {
        super(c,0,objects);
        this.context = c;
        this.doencacardiaca = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (doencacardiaca != null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_doencacardiacas, parent, false);

            TextView txtviwArritmia = (TextView) view.findViewById(R.id.txtviwArritmia);
            TextView txtviwAnginaInst = (TextView) view.findViewById(R.id.txtviwAnginaInst);
            TextView txtviwAnginaEsta = (TextView) view.findViewById(R.id.txtviwAnginaEsta);
            TextView txtviwArtrose = (TextView) view.findViewById(R.id.txtviwArtrose);
            TextView txtviwAterosclerose = (TextView) view.findViewById(R.id.txtviwAterosclerose);
            TextView txtviwArterioesclerose = (TextView) view.findViewById(R.id.txtviwArterioesclerose);
            TextView txtviwCardiomiopatia = (TextView) view.findViewById(R.id.txtviwCardiomiopatia);
            TextView txtviwCardiopatia = (TextView) view.findViewById(R.id.txtviwCardiopatia);
            TextView txtviwEndocardite = (TextView) view.findViewById(R.id.txtviwEndocardite);
            TextView txtviwEstenoseMitral = (TextView) view.findViewById(R.id.txtviwEstenoseMitral);
            TextView txtviwEstenosePulmonar = (TextView) view.findViewById(R.id.txtviwEstenosePulmonar);
            TextView txtviwFibrilacaoAtrial = (TextView) view.findViewById(R.id.txtviwFibrilacaoAtrial);
            TextView txtviwHipertensao = (TextView) view.findViewById(R.id.txtviwHipertensao);
            TextView txtviwHipotensao = (TextView) view.findViewById(R.id.txtviwHipotensao);
            TextView txtviwInfarto = (TextView) view.findViewById(R.id.txtviwInfarto);
            TextView txtviwInsuficienciaCardiaca = (TextView) view.findViewById(R.id.txtviwInsuficienciaCardiaca);
            TextView txtviwProlapso = (TextView) view.findViewById(R.id.txtviwProlapso);
            TextView txtviwSopro = (TextView) view.findViewById(R.id.txtviwSopro);
            TextView txtviwTaquicardia = (TextView) view.findViewById(R.id.txtviwTaquicardia);
            TextView txtviwTumorCardiaco = (TextView) view.findViewById(R.id.txtviwTumorCardiaco);
            TextView txtviwDataDoenca = (TextView) view.findViewById(R.id.txtviwDataDoenca);
            TextView txtviwOutrasDoen = (TextView) view.findViewById(R.id.txtviwOutrasDoen);



            DoencaCardiacas doencacardiaca2 = doencacardiaca.get(position);

            if (doencacardiaca2.getDataDoenca() != null){
                txtviwDataDoenca.setText("Data inserção de dados: " + doencacardiaca2.getDataDoenca());
            }

            if (doencacardiaca2.getDataDoenca() != null){
                txtviwOutrasDoen.setText("Outras Doenças: " + doencacardiaca2.getOutrasDoenca());
            }else {
                txtviwOutrasDoen.setText("Paciente não possui outras doenças cardíacas");
            }

            if (doencacardiaca2.getAnginaEstavel() != null){
                txtviwAnginaEsta.setText("Paciente possui a doença: " + doencacardiaca2.getAnginaEstavel());
            }else{
                txtviwAnginaEsta.setText("Paciente Não possui: Angina Estável");
            }

            if (doencacardiaca2.getAnginaInstavel() != null){
                txtviwAnginaInst.setText("Paciente possui a doença: " + doencacardiaca2.getAnginaInstavel());
            }else{
                txtviwAnginaInst.setText("Paciente Não possui: Angina Intável");
            }

            if (doencacardiaca2.getArritmia() != null){
                txtviwArritmia.setText("Paciente possui a doença: " + doencacardiaca2.getArritmia());
            }else{
                txtviwArritmia.setText("Paciente Não possui: Arritmia");
            }
            if (doencacardiaca2.getArtrose() != null){
                txtviwArtrose.setText("Paciente possui a doença: " +  doencacardiaca2.getArtrose());
            }else {
                txtviwArtrose.setText("Paciente Não possui: Artrose");
            }
            if (doencacardiaca2.getAterosclerose() != null){
                txtviwAterosclerose.setText("Paciente possui a doença: " +  doencacardiaca2.getAterosclerose());
            }else{
                txtviwAterosclerose.setText("Paciente Não possui: Aterosclerose");
            }
            if (doencacardiaca2.getArterioesclerose() != null){
                txtviwArterioesclerose.setText("Paciente possui a doença: " + doencacardiaca2.getArterioesclerose());
            }else{
                txtviwArterioesclerose.setText("Paciente Não possui: Arterioesclerose");
            }

            if (doencacardiaca2.getCardiomiopatia() != null){
                txtviwCardiomiopatia.setText("Paciente possui a doença: " + doencacardiaca2.getCardiomiopatia());
            }else{
                txtviwCardiomiopatia.setText("Paciente Não possui: Cardiomiopatia");
            }
            if (doencacardiaca2.getCardiopatia() != null){
                txtviwCardiopatia.setText("Paciente possui a doença: " + doencacardiaca2.getCardiopatia());
            }else{
                txtviwCardiopatia.setText("Paciente Não possui: Cardiopatia congênita");
            }
            if (doencacardiaca2.getEndocardite() != null){
                txtviwEndocardite.setText("Paciente possui a doença: " + doencacardiaca2.getEndocardite());
            }else{
                txtviwEndocardite.setText("Paciente Não possui: Endocardite ");
            }
            if (doencacardiaca2.getEstenoseMitral() != null){
                txtviwEstenoseMitral.setText("Paciente possui a doença: " + doencacardiaca2.getEstenoseMitral());
            }else{
                txtviwEstenoseMitral.setText("Paciente Não possui: Estenose mitral");
            }
            if (doencacardiaca2.getEstenosePulmonar() != null){
                txtviwEstenosePulmonar.setText("Paciente possui a doença: " + doencacardiaca2.getEstenosePulmonar());
            }else{
                txtviwEstenosePulmonar.setText("Paciente Não possui: Estenose pulmonar");
            }
            if (doencacardiaca2.getFibrilacaoAtrial() != null){
                txtviwFibrilacaoAtrial.setText("Paciente possui a doença: " + doencacardiaca2.getFibrilacaoAtrial());
            }else{
                txtviwFibrilacaoAtrial.setText("Paciente Não possui: Fibrilação atrial");
            }
            if (doencacardiaca2.getHipertensao() != null){
                txtviwHipertensao.setText("Paciente possui a doença: " + doencacardiaca2.getHipertensao());
            }else{
                txtviwHipertensao.setText("Paciente Não possui: Hipertensão");
            }
            if (doencacardiaca2.getHipotensao() != null){
                txtviwHipotensao.setText("Paciente possui a doença: " + doencacardiaca2.getHipotensao());
            }else{
                txtviwHipotensao.setText("Paciente Não possui: Hipotensão");
            }
            if (doencacardiaca2.getInfarto() != null){
                txtviwInfarto.setText("Paciente possui a doença: " + doencacardiaca2.getInfarto());
            }else{
                txtviwInfarto.setText("Paciente Não possui: Infarto");
            }
            if (doencacardiaca2.getInsuficienciaCardiaca() != null){
                txtviwInsuficienciaCardiaca.setText("Paciente possui a doença: " + doencacardiaca2.getInsuficienciaCardiaca());
            }else{
                txtviwInsuficienciaCardiaca.setText("Paciente Não possui: Insuficiência cardíaca");
            }
            if (doencacardiaca2.getProlapso() != null){
                txtviwProlapso.setText("Paciente possui a doença: " + doencacardiaca2.getProlapso());
            }else{
                txtviwProlapso.setText("Paciente Não possui: Prolapso");
            }
            if (doencacardiaca2.getSopro() != null){
                txtviwSopro.setText("Paciente possui a doença: " + doencacardiaca2.getSopro());
            }else{
                txtviwSopro.setText("Paciente Não possui: Sopro");
            }
            if (doencacardiaca2.getTaquicardia() != null){
                txtviwTaquicardia.setText("Paciente possui a doença: " + doencacardiaca2.getTaquicardia());
            }else{
                txtviwTaquicardia.setText("Paciente Não possui: Taquicardia");
            }
            if (doencacardiaca2.getTumorCardiaco() != null){
                txtviwTumorCardiaco.setText("Paciente possui a doença: " + doencacardiaca2.getTumorCardiaco());
            }else{
                txtviwTumorCardiaco.setText("Paciente Não possui: Tumor Cardíaco");
            }

        }

        return view;
    }


}
