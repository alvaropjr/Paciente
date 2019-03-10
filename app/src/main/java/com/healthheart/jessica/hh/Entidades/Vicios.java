package com.healthheart.jessica.hh.Entidades;

/**
 * Created by Jessica on 21/10/2017.
 */

public class Vicios {
    private String IdUsuario; //chave estrangeira
    private String VicioDrogas;
    private String Drogas;
    private String VicioAlcool;
    private String BebidaAlcool;
    private String VicioMedica;
    private String Medica;

    public Vicios() {
    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getVicioDrogas() {
        return VicioDrogas;
    }

    public void setVicioDrogas(String vicioDrogas) {
        VicioDrogas = vicioDrogas;
    }

    public String getDrogas() {
        return Drogas;
    }

    public void setDrogas(String drogas) {
        Drogas = drogas;
    }

    public String getVicioAlcool() {
        return VicioAlcool;
    }

    public void setVicioAlcool(String vicioAlcool) {
        VicioAlcool = vicioAlcool;
    }

    public String getBebidaAlcool() {
        return BebidaAlcool;
    }

    public void setBebidaAlcool(String bebidaAlcool) {
        BebidaAlcool = bebidaAlcool;
    }

    public String getVicioMedica() {
        return VicioMedica;
    }

    public void setVicioMedica(String vicioMedica) {
        VicioMedica = vicioMedica;
    }

    public String getMedica() {
        return Medica;
    }

    public void setMedica(String medica) {
        Medica = medica;
    }
}
