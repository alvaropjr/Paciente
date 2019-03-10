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
import com.healthheart.jessica.hh.Entidades.Descanso;
import com.healthheart.jessica.hh.Helper.PreferenciasAndroid;
import com.healthheart.jessica.hh.R;

import java.util.UUID;

public class DescansoActivity extends AppCompatActivity {
    private EditText edttxtDataDescanso;
    private EditText edttxtQntAcordou;
    private EditText edttxtHorasDormidas;
    private Button btnSalvarDescanso;
    private Button btnCancDescanso;

    private RadioButton rbManha;
    private RadioButton rbTarde;
    private RadioButton rbNoite;
    private RadioButton rbBom;
    private RadioButton rbRegular;
    private RadioButton rbPessima;

    private Button btnVoltarPrinpal;
    private Button btnHisDescanso;
    private Descanso descanso;


    //dados usario logado
    private  String idUsuarioLogado;

    private FirebaseAuth autenticacao;

    //Objeto de conexao
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descanso);

        //Voltar Tela principal
        btnVoltarPrinpal = (Button) findViewById(R.id.btnVoltarPrinpal);
        btnVoltarPrinpal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarTelaPrinci = new Intent(DescansoActivity.this, PrincipalActivity.class);
                startActivity(intentVoltarTelaPrinci);
            }
        });
        btnHisDescanso = (Button) findViewById(R.id.btnHisDescanso);
        btnHisDescanso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentIrHisDescanso = new Intent(DescansoActivity.this, HistoricoDescansoActivity.class);
                startActivity(intentIrHisDescanso);
            }
        });

        //cancela descanso
        btnCancDescanso  = (Button) findViewById(R.id.btnCancDescanso);
        btnCancDescanso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCanDescanso = new Intent(DescansoActivity.this, DescansoActivity.class);
                startActivity(intentCanDescanso);
            }
        });

        //recupera identificador usuario
        PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(DescansoActivity.this);
        idUsuarioLogado = preferenciasAndroid.getIdentificador();

        edttxtDataDescanso  = (EditText) findViewById(R.id.edttxtDataDescanso);
        edttxtQntAcordou  = (EditText) findViewById(R.id.edttxtQntAcordou);
        edttxtHorasDormidas  = (EditText) findViewById(R.id.edttxtHorasDormidas);
        rbManha = (RadioButton) findViewById(R.id.rbManha);
        rbTarde = (RadioButton) findViewById(R.id.rbTarde);
        rbNoite = (RadioButton) findViewById(R.id.rbNoite);
        rbBom = (RadioButton) findViewById(R.id.rbBom);
        rbRegular = (RadioButton) findViewById(R.id.rbRegular);
        rbPessima = (RadioButton) findViewById(R.id.rbPessima);
        btnSalvarDescanso  = (Button) findViewById(R.id.btnSalvarDescanso);
        btnCancDescanso  = (Button) findViewById(R.id.btnCancDescanso);

        //Inicializa banco de dados
        inicializarFirebase();

        btnSalvarDescanso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edttxtDataDescanso.getText().toString().equals("")
                        && !edttxtQntAcordou.getText().toString().equals("")
                        && !edttxtHorasDormidas.getText().toString().equals("")){
                    //Intancia
                    descanso = new Descanso();
                    //descanso.setIdDescanso(UUID.randomUUID().toString());
                    descanso.setIdUsuario(idUsuarioLogado);
                    descanso.setDataDescanso(edttxtDataDescanso.getText().toString());
                    descanso.setHorasDescanso(edttxtHorasDormidas.getText().toString());
                    descanso.setQntVezAcordou(Integer.parseInt(edttxtQntAcordou.getText().toString()));
                    if(rbManha.isChecked()){
                        descanso.setPeriodoDescanso("Manhã");
                    }
                    if(rbTarde.isChecked()){
                        descanso.setPeriodoDescanso("Tarde");
                    }
                    if(rbNoite.isChecked()){
                        descanso.setPeriodoDescanso("Noite");
                    }
                    if(rbBom.isChecked()){
                        descanso.setStatusDescanso("Bom");
                    }
                    if(rbRegular.isChecked()){
                        descanso.setStatusDescanso("Regular");
                    }
                    if(rbPessima.isChecked()){
                        descanso.setStatusDescanso("Pessímo");
                    }
                    //databaseReference.child("Descanso").child(descanso.getIdDescanso()).setValue(descanso);
                    databaseReference = ConfiguracaoFirebase.getFirebase().child("Descanso");
                    databaseReference.child(idUsuarioLogado).push().setValue(descanso);
                    Toast.makeText(DescansoActivity.this, "Descanso registrado com sucesso", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(DescansoActivity.this, "Preenchimento de Todos os campos são Obrigatórios", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(DescansoActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}
