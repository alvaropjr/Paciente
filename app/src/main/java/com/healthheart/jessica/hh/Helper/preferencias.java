package com.healthheart.jessica.hh.Helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jessica on 31/08/2017.
 */

//Salva quando o usuario esta já logado no app

public class preferencias {

    private Context context;
    private SharedPreferences preferences;
    private String NOME_ARQUIVO = "hh.preferencias";
    private  int MODE = 0;
    private SharedPreferences.Editor editor;

    private final String CHAVE_IDENTIFICADOR = "identificadorUsuarioLogado";
    private final String CHAVE_NOME = "nomeUsuarioLogado";

    public preferencias(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(NOME_ARQUIVO, MODE);

        editor = preferences.edit();
    }



    //Cria metodo para salvar
    public void salvarUsuarioPreferencias(String identificadorUsuario, String nomeUsuario){
        editor.putString(CHAVE_IDENTIFICADOR, identificadorUsuario);
        editor.putString(CHAVE_NOME, nomeUsuario);
        editor.commit();
    }


    public String getIdentificador (){
        return preferences.getString(CHAVE_IDENTIFICADOR, null);
    }

    public String getNome (){
        return preferences.getString(CHAVE_NOME, null);
    }

}
