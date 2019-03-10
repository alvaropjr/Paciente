package com.healthheart.jessica.hh.Entidades;

/**
 * Created by Jessica on 21/10/2017.
 */

public class Oxigenacao {
    private String IdUsuario; //chave estrangeira
    private String DataOxigenacao;
    private double Oxigenacao;
    private String PeridoOxigenacao;

    public Oxigenacao() {
    }

    public String getPeridoOxigenacao() {
        return PeridoOxigenacao;
    }

    public void setPeridoOxigenacao(String peridoOxigenacao) {
        PeridoOxigenacao = peridoOxigenacao;
    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getDataOxigenacao() {
        return DataOxigenacao;
    }

    public void setDataOxigenacao(String dataOxigenacao) {
        DataOxigenacao = dataOxigenacao;
    }

    public double getOxigenacao() {
        return Oxigenacao;
    }

    public void setOxigenacao(double oxigenacao) {
        Oxigenacao = oxigenacao;
    }
}
