package com.healthheart.jessica.hh.DAO;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Jessica on 28/08/2017.
 */

public class ConfiguracaoFirebase {

    private  static DatabaseReference referenciaFirebase;
    private static FirebaseAuth  autenticacao;



    public static DatabaseReference getFirebase(){
        //testa para saber se a referencia já foi instanciar, se não cria instanciar
        if (referenciaFirebase == null){
            //Cria referfencia
            referenciaFirebase = FirebaseDatabase.getInstance().getReference();
        }
        //se a referencia já foi feita retorna refererenceDataBase
        return referenciaFirebase;

        }

        //metodo autenticação
        public  static FirebaseAuth getFirebaseAutenticacao(){
            if (referenciaFirebase == null){
                autenticacao = FirebaseAuth.getInstance();
            }
            return autenticacao;
        }



}
