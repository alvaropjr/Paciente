package com.healthheart.jessica.hh.Entidades;

/**
 * Created by Jessica on 18/11/2017.
 */

public class Alimentos {

    private String IdAlimentos;
    private String IdUsuario; //chave estrangeira
    private String DataAlimento;
    private String PeridoAlimento;
    private String NomeAlimento;
    private String NomeBebida;

    public Alimentos() {
    }

    public String getIdAlimentos() {
        return IdAlimentos;
    }

    public void setIdAlimentos(String idAlimentos) {
        IdAlimentos = idAlimentos;
    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getDataAlimento() {
        return DataAlimento;
    }

    public void setDataAlimento(String dataAlimento) {
        DataAlimento = dataAlimento;
    }

    public String getPeridoAlimento() {
        return PeridoAlimento;
    }

    public void setPeridoAlimento(String peridoAlimento) {
        PeridoAlimento = peridoAlimento;
    }

    public String getNomeAlimento() {
        return NomeAlimento;
    }

    public void setNomeAlimento(String nomeAlimento) {
        NomeAlimento = nomeAlimento;
    }

    public String getNomeBebida() {
        return NomeBebida;
    }

    public void setNomeBebida(String nomeBebida) {
        NomeBebida = nomeBebida;
    }
}
