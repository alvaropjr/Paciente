package com.healthheart.jessica.hh.Entidades;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

/**
 * Created by Jessica on 09/09/2017.
 */

public class Glicose {


    /*    private String Idmedicamento;
    private String IdUsuario; //chave estrangeira
    private String DataMedicamento;
    private String NomeMedicamento;
    private Float DoseRemedio;
    private Integer QnRemedioPorDia;*/

    private String IdGlicose;
    private String IdUsuario; //chave estrangeira
    private String DataGlicose;
    private double QntGlicose;
    private double QntInsulina;
    private String StatusJejum;
    private String StatusPreRefeicao;
    private String StatusPosRefeicao;
    private String StatusAntesDormir;
    private String StatusGlicose;

    public Glicose() {
    }

    public String getIdGlicose() {
        return IdGlicose;
    }

    public void setIdGlicose(String idGlicose) {
        IdGlicose = idGlicose;
    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getDataGlicose() {
        return DataGlicose;
    }

    public void setDataGlicose(String dataGlicose) {
        DataGlicose = dataGlicose;
    }

    public double getQntGlicose() {
        return QntGlicose;
    }

    public void setQntGlicose(double qntGlicose) {
        QntGlicose = qntGlicose;
    }

    public double getQntInsulina() {
        return QntInsulina;
    }

    public void setQntInsulina(double qntInsulina) {
        QntInsulina = qntInsulina;
    }

    public String getStatusJejum(String aTrue) {
        return StatusJejum;
    }

    public void setStatusJejum(String statusJejum) {
        StatusJejum = statusJejum;
    }

    public String getStatusPreRefeicao() {
        return StatusPreRefeicao;
    }

    public void setStatusPreRefeicao(String statusPreRefeicao) {
        StatusPreRefeicao = statusPreRefeicao;
    }

    public String getStatusPosRefeicao() {
        return StatusPosRefeicao;
    }

    public void setStatusPosRefeicao(String statusPosRefeicao) {
        StatusPosRefeicao = statusPosRefeicao;
    }

    public String getStatusAntesDormir() {
        return StatusAntesDormir;
    }

    public void setStatusAntesDormir(String statusAntesDormir) {
        StatusAntesDormir = statusAntesDormir;
    }

    public String getStatusGlicose() {
        return StatusGlicose;
    }

    public void setStatusGlicose(String statusGlicose) {
        StatusGlicose = statusGlicose;
    }
}
