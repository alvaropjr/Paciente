package com.healthheart.jessica.hh.Entidades;

/**
 * Created by Jessica on 14/09/2017.
 */

public class Agua {
    private String IdAgua;
    private String IdUsuario; //chave estrangeira
    private String DataAgua;
    private double QntCopoTomados;

    public Agua() {
    }

    public String getIdAgua() {
        return IdAgua;
    }

    public void setIdAgua(String idAgua) {
        IdAgua = idAgua;
    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getDataAgua() {
        return DataAgua;
    }

    public void setDataAgua(String dataAgua) {
        DataAgua = dataAgua;
    }

    public double getQntCopoTomados() {
        return QntCopoTomados;
    }

    public void setQntCopoTomados(double qntCopoTomados) {
        QntCopoTomados = qntCopoTomados;
    }
}
