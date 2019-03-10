package com.healthheart.jessica.hh.Entidades;

/**
 * Created by Jessica on 13/09/2017.
 */

public class Pressao {

    private String IdPressao;
    private String IdUsuario; //chave estrangeira
    private String DataPressao;
    private double Sistolica;
    private double Diastolica;

    public Pressao() {
    }

    public String getIdPressao() {
        return IdPressao;
    }

    public void setIdPressao(String idPressao) {
        IdPressao = idPressao;
    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getDataPressao() {
        return DataPressao;
    }

    public void setDataPressao(String dataPressao) {
        DataPressao = dataPressao;
    }

    public double getSistolica() {
        return Sistolica;
    }

    public void setSistolica(double sistolica) {
        Sistolica = sistolica;
    }

    public double getDiastolica() {
        return Diastolica;
    }

    public void setDiastolica(double diastolica) {
        Diastolica = diastolica;
    }
}
