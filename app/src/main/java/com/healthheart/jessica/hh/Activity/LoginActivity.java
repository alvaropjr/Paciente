package com.healthheart.jessica.hh.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.healthheart.jessica.hh.DAO.ConfiguracaoFirebase;
import com.healthheart.jessica.hh.Entidades.Usuario;
import com.healthheart.jessica.hh.Helper.Base64Custom;
import com.healthheart.jessica.hh.Helper.PreferenciasAndroid;
import com.healthheart.jessica.hh.R;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnLogar;
    private FirebaseAuth autenticacao;
    private Usuario usuario;

    private Button btnNaotemlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        verificarUsuarioLogado();


        btnNaotemlogin = (Button) findViewById(R.id.btnNaotemLogin);

        btnNaotemlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrieTelaCadastra = new Intent(LoginActivity.this, CriarLoginActivity.class);
                startActivity(intentAbrieTelaCadastra);
            }
        });

        //Recupera valores
        //txt_NaoTemLogin = (TextView) findViewById(R.id.txt_NaoTemLogin);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnLogar = (Button) findViewById(R.id.bnt_Logar);

        //evento de clique para o botão
        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Testa se o usuario inputou os valores nos campos email e senha
                if (!edtEmail.getText().toString().equals("") && !edtSenha.getText().toString().equals("")){
                    //instanciar usuarios
                    usuario = new Usuario();
                    usuario.setEmail(edtEmail.getText().toString());
                    usuario.setSenha(edtSenha.getText().toString());

                    //chama o metodo validaLogin
                    validarLogin();
                }
                //Toast mostra msg na tela
                else{
                    Toast.makeText(LoginActivity.this, "Preencha os campos de Email e Senha", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void verificarUsuarioLogado() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        if(autenticacao.getCurrentUser() != null){
            abrirTelaPrincipal();
        }

    }


    //Metodo de validação do login
    private void validarLogin(){
        //autenticação recebe a classe de configuração do Firebase
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //testa task para verificar se o usuario logou com sucesso
                if(task.isSuccessful()){
                    PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(LoginActivity.this);
                    String identificadorUsuarioLogado = Base64Custom.codificarBase64(usuario.getEmail());
                    preferenciasAndroid.salvarUsuarioPreferencias(identificadorUsuarioLogado);

                    abrirTelaPrincipal();
                    finish();
                    Toast.makeText(LoginActivity.this, "Login efetuado com sucesso", Toast.LENGTH_SHORT).show();
                }else{
                    //tratar exceções dps
                    Toast.makeText(LoginActivity.this, "Usuário ou senha incorreto", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void abrirTelaPrincipal(){
        Intent intentAbrirTelaPrincipal = new Intent(LoginActivity.this, PrincipalActivity.class);
        startActivity(intentAbrirTelaPrincipal);
    }




}
