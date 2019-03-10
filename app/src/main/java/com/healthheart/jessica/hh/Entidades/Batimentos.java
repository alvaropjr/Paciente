package com.healthheart.jessica.hh.Entidades;

/**
 * Created by Jessica on 21/10/2017.
 */

public class Batimentos {
    private String IdUsuario; //chave estrangeira
    private String DataBatimento;
    private String PeridoBatimento;
    private double Batimentos;

    public Batimentos() {
    }

    public String getPeridoBatimento() {
        return PeridoBatimento;
    }

    public void setPeridoBatimento(String peridoBatimento) {
        PeridoBatimento = peridoBatimento;
    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getDataBatimento() {
        return DataBatimento;
    }

    public void setDataBatimento(String dataBatimento) {
        DataBatimento = dataBatimento;
    }

    public double getBatimentos() {
        return Batimentos;
    }

    public void setBatimentos(double batimentos) {
        Batimentos = batimentos;
    }
}
