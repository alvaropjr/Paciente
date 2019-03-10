package com.healthheart.jessica.hh.Entidades;


/**
 * Created by Jessica on 11/09/2017.
 */

public class Peso {
    public static final double FaixaImcAbaixoPeso = 18.5; // abaixo do peso
    public static final double FaixaImcPesoNormal = 25.0; //peso normal Peso adequado ≥ 18,5 e < 25
    public static final double FaixaImcSobrePeso = 30.0; //Sobrepeso ≥ 25 e < 30 Obesidade ≥ 30

    private String IdPeso;
    private String IdUsuario; //chave estrangeira
    private String DataPeso;
    private double Peso;
    private double Altura;
    private double CircuCintura;
    private double IMC;
    private String Sexo;


    public Peso() {
    }

    public String getIdPeso() {
        return IdPeso;
    }

    public void setIdPeso(String idPeso) {
        IdPeso = idPeso;
    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getDataPeso() {
        return DataPeso;
    }

    public void setDataPeso(String dataPeso) {
        DataPeso = dataPeso;
    }

    public double getPeso() {
        return Peso;
    }

    public void setPeso(double peso) {
        Peso = peso;
    }

    public double getAltura() {
        return Altura;
    }

    public void setAltura(double altura) {
        Altura = altura;
    }

    public double getCircuCintura() {
        return CircuCintura;
    }

    public void setCircuCintura(double circuCintura) {
        CircuCintura = circuCintura;
    }

    public double getIMC() {
        return IMC;
    }

    public void setIMC(double IMC) {
        this.IMC = IMC;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }
}
