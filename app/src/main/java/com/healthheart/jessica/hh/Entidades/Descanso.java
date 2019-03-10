package com.healthheart.jessica.hh.Entidades;

/**
 * Created by Jessica on 14/09/2017.
 */

public class Descanso {
    private String IdDescanso;
    private String IdUsuario;
    private String PeriodoDescanso;
    private String HorasDescanso;
    private String StatusDescanso;
    private String DataDescanso;
    private Integer QntVezAcordou;

    public Descanso() {
    }

    public String getHorasDescanso() {
        return HorasDescanso;
    }

    public void setHorasDescanso(String horasDescanso) {
        HorasDescanso = horasDescanso;
    }

    public String getIdDescanso() {
        return IdDescanso;
    }

    public void setIdDescanso(String idDescanso) {
        IdDescanso = idDescanso;
    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getDataDescanso() {
        return DataDescanso;
    }

    public void setDataDescanso(String dataDescanso) {
        DataDescanso = dataDescanso;
    }

    public Integer getQntVezAcordou() {
        return QntVezAcordou;
    }

    public void setQntVezAcordou(Integer qntVezAcordou) {
        QntVezAcordou = qntVezAcordou;
    }

    public String getPeriodoDescanso() {
        return PeriodoDescanso;
    }

    public void setPeriodoDescanso(String periodoDescanso) {
        PeriodoDescanso = periodoDescanso;
    }

    public String getStatusDescanso() {
        return StatusDescanso;
    }

    public void setStatusDescanso(String statusDescanso) {
        StatusDescanso = statusDescanso;
    }
}
