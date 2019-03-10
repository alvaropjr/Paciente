package com.healthheart.jessica.hh.Helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jessica on 31/08/2017.
 */

public class PreferenciasAndroid {

    private Context context;
    private SharedPreferences preferences;
    private String NOME_ARQUIVO = "hh.preferencias";
    private  int MODE = 0;
    private SharedPreferences.Editor editor;

    private final String CHAVE_IDENTIFICADOR = "identificadorUsuarioLogado";
    private final String CHAVE_NOME = "nomeUsuarioLogado";

    public PreferenciasAndroid(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(NOME_ARQUIVO, MODE);

        editor = preferences.edit();
    }



    //Cria metodo para salvar usuario
    public void salvarUsuarioPreferencias(String identificadorUsuario){
        editor.putString(CHAVE_IDENTIFICADOR, identificadorUsuario);
       //editor.putString(CHAVE_NOME, nomeUsuario);
        editor.commit();
    }





    public String getIdentificador (){
        return preferences.getString(CHAVE_IDENTIFICADOR, null);
    }

    public String getNome (){
        return preferences.getString(CHAVE_NOME, null);
    }
}
