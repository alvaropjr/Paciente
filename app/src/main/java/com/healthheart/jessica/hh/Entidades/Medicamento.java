package com.healthheart.jessica.hh.Entidades;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.healthheart.jessica.hh.DAO.ConfiguracaoFirebase;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jessica on 04/09/2017.
 */

public class Medicamento {
    private String Idmedicamento;
    private String IdUsuario; //chave estrangeira
    private String DataMedicamento;
    private String NomeMedicamento;
    private double DoseRemedio;
    private int QnRemedioPorDia;

    public Medicamento() {

    }

  /*  public void salvar(){
        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        referenciaFirebase.child("Medicamentos").child(getIdmedicamento()).setValue(this);
    }*/

    public String getIdmedicamento() {
        return Idmedicamento;
    }

    public void setIdmedicamento(String idmedicamento) {
        Idmedicamento = idmedicamento;
    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getDataMedicamento() {
        return DataMedicamento;
    }

    public void setDataMedicamento(String dataMedicamento) {
        DataMedicamento = dataMedicamento;
    }

    public String getNomeMedicamento() {
        return NomeMedicamento;
    }

    public void setNomeMedicamento(String nomeMedicamento) {
        NomeMedicamento = nomeMedicamento;
    }

    public double getDoseRemedio() {
        return DoseRemedio;
    }

    public void setDoseRemedio(double doseRemedio) {
        DoseRemedio = doseRemedio;
    }

    public int getQnRemedioPorDia() {
        return QnRemedioPorDia;
    }

    public void setQnRemedioPorDia(int qnRemedioPorDia) {
        QnRemedioPorDia = qnRemedioPorDia;
    }
}

