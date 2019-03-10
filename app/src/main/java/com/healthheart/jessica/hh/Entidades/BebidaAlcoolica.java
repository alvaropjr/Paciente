package com.healthheart.jessica.hh.Entidades;

/**
 * Created by Jessica on 28/10/2017.
 */

public class BebidaAlcoolica {
    private String IdUsuario; //chave estrangeira
    private String DataBbAlcoo;
    private String NomeBbAlcoo;
    private String PeriodoBbAlcoo;
    private double QntCopoTomadosBbAlcoo;

    public BebidaAlcoolica() {
    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getDataBbAlcoo() {
        return DataBbAlcoo;
    }

    public void setDataBbAlcoo(String dataBbAlcoo) {
        DataBbAlcoo = dataBbAlcoo;
    }

    public String getNomeBbAlcoo() {
        return NomeBbAlcoo;
    }

    public void setNomeBbAlcoo(String nomeBbAlcoo) {
        NomeBbAlcoo = nomeBbAlcoo;
    }

    public String getPeriodoBbAlcoo() {
        return PeriodoBbAlcoo;
    }

    public void setPeriodoBbAlcoo(String periodoBbAlcoo) {
        PeriodoBbAlcoo = periodoBbAlcoo;
    }

    public double getQntCopoTomadosBbAlcoo() {
        return QntCopoTomadosBbAlcoo;
    }

    public void setQntCopoTomadosBbAlcoo(double qntCopoTomadosBbAlcoo) {
        QntCopoTomadosBbAlcoo = qntCopoTomadosBbAlcoo;
    }
}
