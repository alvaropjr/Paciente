package com.healthheart.jessica.hh.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.healthheart.jessica.hh.DAO.ConfiguracaoFirebase;
import com.healthheart.jessica.hh.Entidades.Alimentos;
import com.healthheart.jessica.hh.Entidades.Descanso;
import com.healthheart.jessica.hh.Helper.PreferenciasAndroid;
import com.healthheart.jessica.hh.R;

public class AlimentosActivity extends AppCompatActivity {
    private EditText edttxtDataAli;
    private EditText edttxtNomeAli;
    private EditText edttxtNomedaBebida;
    private Button btnSalvarAlim;
    private Button btnCancAli;
    private Button btnHisAlimento;

    private Button btnVoltarPrinpal;

    private RadioButton rbCafeManha;
    private RadioButton rbAlmoco;
    private RadioButton rbJantar;

    private RadioButton rbLancheManha;
    private RadioButton rbLancheTarde;
    private RadioButton rbLancheNoite;

    private Alimentos alimentos;

    //dados usario logado
    private  String idUsuarioLogado;

    private FirebaseAuth autenticacao;

    //Objeto de conexao
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimentos);

        //Voltar Tela principal
        btnVoltarPrinpal = (Button) findViewById(R.id.btnVoltarPrinpal);
        btnVoltarPrinpal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarTelaPrinci = new Intent(AlimentosActivity.this, PrincipalActivity.class);
                startActivity(intentVoltarTelaPrinci);
            }
        });


        btnHisAlimento = (Button) findViewById(R.id.btnHisAlimento);
        btnHisAlimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentIrHis = new Intent(AlimentosActivity.this, HistoricoAlimentosActivity.class);
                startActivity(intentIrHis);
            }
        });

        //cancela
        btnCancAli  = (Button) findViewById(R.id.btnCancAli);
        btnCancAli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCan = new Intent(AlimentosActivity.this, AlimentosActivity.class);
                startActivity(intentCan);
            }
        });

        //recupera identificador usuario
        PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(AlimentosActivity.this);
        idUsuarioLogado = preferenciasAndroid.getIdentificador();

        edttxtDataAli  = (EditText) findViewById(R.id.edttxtDataAli);
        edttxtNomeAli  = (EditText) findViewById(R.id.edttxtNomeAli);
        edttxtNomedaBebida  = (EditText) findViewById(R.id.edttxtNomedaBebida);

        rbCafeManha = (RadioButton) findViewById(R.id.rbCafeManha);
        rbAlmoco = (RadioButton) findViewById(R.id.rbAlmoco);
        rbJantar = (RadioButton) findViewById(R.id.rbJantar);

        rbLancheManha = (RadioButton) findViewById(R.id.rbLancheManha);
        rbLancheTarde = (RadioButton) findViewById(R.id.rbLancheTarde);
        rbLancheNoite = (RadioButton) findViewById(R.id.rbLancheNoite);

        btnSalvarAlim  = (Button) findViewById(R.id.btnSalvarAlim);

        //Inicializa banco de dados
        inicializarFirebase();

        btnSalvarAlim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edttxtDataAli.getText().toString().equals("")
                        && !edttxtNomeAli.getText().toString().equals("")
                        && !edttxtNomedaBebida.getText().toString().equals("")){

                    alimentos = new Alimentos();

                    alimentos.setIdUsuario(idUsuarioLogado);
                    alimentos.setDataAlimento(edttxtDataAli.getText().toString());
                    alimentos.setNomeAlimento(edttxtNomeAli.getText().toString());
                    alimentos.setNomeBebida(edttxtNomedaBebida.getText().toString());

                    if(rbCafeManha.isChecked()){
                        alimentos.setPeridoAlimento("Café da Manhã");
                    }

                    if(rbAlmoco.isChecked()){
                        alimentos.setPeridoAlimento("Almoço");
                    }

                    if(rbJantar.isChecked()){
                        alimentos.setPeridoAlimento("Jantar");
                    }

                    if(rbLancheManha.isChecked()){
                        alimentos.setPeridoAlimento("Lanche da Manhã");
                    }

                    if(rbLancheTarde.isChecked()){
                        alimentos.setPeridoAlimento("Lanche da Tarde");
                    }

                    if(rbLancheNoite.isChecked()){
                        alimentos.setPeridoAlimento("Lanche da Noite");
                    }
                    databaseReference = ConfiguracaoFirebase.getFirebase().child("Alimentos");
                    databaseReference.child(idUsuarioLogado).push().setValue(alimentos);
                    Toast.makeText(AlimentosActivity.this, "Alimentos registrado com sucesso", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AlimentosActivity.this, "Preencha todos os campos e selecione um período", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


private void inicializarFirebase() {
    FirebaseApp.initializeApp(AlimentosActivity.this);
    firebaseDatabase = FirebaseDatabase.getInstance();
    databaseReference = firebaseDatabase.getReference();
}

}
