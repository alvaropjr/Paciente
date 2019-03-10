package com.healthheart.jessica.hh.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;


import com.google.firebase.auth.FirebaseUser;
import com.healthheart.jessica.hh.DAO.ConfiguracaoFirebase;
import com.healthheart.jessica.hh.Entidades.Usuario;
import com.healthheart.jessica.hh.Helper.Base64Custom;
import com.healthheart.jessica.hh.Helper.PreferenciasAndroid;
import com.healthheart.jessica.hh.R;

public class CriarLoginActivity extends AppCompatActivity {

    private EditText edttxtNome;
    private EditText edttxtEmail;
    private EditText edttxtSenha;
    private Button btnSalvarCadastro;
    private Button btnCanCadastro;
    private Usuario usuario;

    private FirebaseAuth autenticacao;

    //private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_login);

        edttxtNome  = (EditText) findViewById(R.id.edttxtNome);
        edttxtEmail  = (EditText) findViewById(R.id.edttxtEmail);
        edttxtSenha  = (EditText) findViewById(R.id.edttxtSenha);
        btnSalvarCadastro  = (Button) findViewById(R.id.btnSalvarCadastro);


        btnCanCadastro  = (Button) findViewById(R.id.btnCanCadastro);
        btnCanCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCanCriaLogin = new Intent(CriarLoginActivity.this, CriarLoginActivity.class);
                startActivity(intentCanCriaLogin);
            }
        });


        //recupera o valor dos campos e botão
        btnSalvarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = new Usuario();
                usuario.setNome(edttxtNome.getText().toString());
                usuario.setEmail(edttxtEmail.getText().toString());
                usuario.setSenha(edttxtSenha.getText().toString());
                cadastraUsuario();



            }
        });






    }

    private void cadastraUsuario() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(CriarLoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(CriarLoginActivity.this, "Usuario Cadsatrado com Sucesso", Toast.LENGTH_LONG).show();
                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    String identificadorUsuarioLogado = Base64Custom.codificarBase64(usuario.getEmail());
                    usuario.setId(identificadorUsuarioLogado);
                    usuario.salvar();

                    PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(CriarLoginActivity.this);
                    preferenciasAndroid.salvarUsuarioPreferencias(identificadorUsuarioLogado);

                    //Desloga o usuario e envia o mesmo para a tela de login
                    autenticacao.signOut();
                    finish();

                }else{
                    String erroExcecao = "";
                    try{
                        throw task.getException();

                    } catch (FirebaseAuthWeakPasswordException e) {
                        erroExcecao = "Digite uma senha mais forte";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        erroExcecao = "Digite um novo email, email invalido";

                    }catch (FirebaseAuthUserCollisionException e){
                        erroExcecao = "Esse email já foi cadastrado";
                    }
                    catch (Exception e){
                        erroExcecao = "Erro ao efetuar  Cadsatrar";
                        e.printStackTrace();
                    }
                    Toast.makeText(CriarLoginActivity.this, "Erro: " + erroExcecao, Toast.LENGTH_LONG).show();

                }
            }
        });


    }

}
