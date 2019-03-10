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

import com.healthheart.jessica.hh.Entidades.Medicamento;
import com.healthheart.jessica.hh.R;

import java.util.ArrayList;

/**
 * Created by Jessica on 21/09/2017.
 */

public class MedicamentoAdapter extends ArrayAdapter<Medicamento> {
    private ArrayList<Medicamento> medicamento;
    private Context context;

    public MedicamentoAdapter(Context c, ArrayList<Medicamento> objects) {
        super(c, 0, objects);
        this.context = c;
        this.medicamento = objects;
    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        View view = null;
        if (medicamento != null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_medicamentos, parent, false);

            TextView txtViewDataMedicamento = (TextView) view.findViewById(R.id.txtViewDataMedicamento);
            TextView txtViewNomeMedicamento = (TextView) view.findViewById(R.id.txtViewNomeMedicamento);
            TextView txtViewDoseMedicamento = (TextView) view.findViewById(R.id.txtViewDoseMedicamento);
            TextView txtViewQntMedicamento = (TextView) view.findViewById(R.id.txtViewQntMedicamento);

            Medicamento medicamento2 = medicamento.get(position);

            txtViewNomeMedicamento.setText("Nome do Medicamento:" +  medicamento2.getNomeMedicamento());
            txtViewDataMedicamento.setText("Data que usou o Medicamento:" +  medicamento2.getDataMedicamento());
            txtViewDoseMedicamento.setText("Dose do Medicamento:" +  medicamento2.getDoseRemedio());
            txtViewQntMedicamento.setText("Quantidade de Medicamento:" +  medicamento2.getQnRemedioPorDia());


       }

        return view;
    }
}
