package com.healthheart.jessica.hh.Activity;


import android.content.Intent;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.healthheart.jessica.hh.DAO.ConfiguracaoFirebase;
import com.healthheart.jessica.hh.Entidades.Medicamento;
import com.healthheart.jessica.hh.Helper.PreferenciasAndroid;
import com.healthheart.jessica.hh.R;

import java.util.UUID;

/*Link crud https://www.youtube.com/watch?v=PE_riDivk6Y&list=PLfs7O23seKWmhdu3tJlrshIXpSczlgmQy*/


public class MedicacaoActivity extends AppCompatActivity {

    private EditText edttxtDataMedicamento;
    private EditText edttxtNomeMedicamento;
    private EditText edttxtDoseMedicamento;
    private EditText edttxtQntDiaMedi;
    private Button btnSalvarMedicacao;
    private Button btnCancMed;

    private Button btnHisMedicamentos;

    private Button btnAddMedicamento;

    private Button btnVoltarPrinpalSau;


    private Medicamento medicamento;

    //dados usario logado
    private  String idUsuarioLogado;

    private FirebaseAuth autenticacao;

    //Objeto de conexao
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicacao);

        //Voltar Tela principal
        btnVoltarPrinpalSau = (Button) findViewById(R.id.btnVoltarPrinpalSau);
        btnVoltarPrinpalSau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarTelaPrinci = new Intent(MedicacaoActivity.this, SaudeActivity.class);
                startActivity(intentVoltarTelaPrinci);
            }
        });

        //recupera identificador usuario
        PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(MedicacaoActivity.this);
        idUsuarioLogado = preferenciasAndroid.getIdentificador();


        //recupera a  histMedicamento
        btnHisMedicamentos = (Button) findViewById(R.id.btnHistMedica);

        btnHisMedicamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbriTelaHistMedica = new Intent(MedicacaoActivity.this, HistoricoMedicamentosActivity.class);
                startActivity(intentAbriTelaHistMedica);
            }
        });


        btnAddMedicamento = (Button) findViewById(R.id.btnAddMedicamento);
        btnAddMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarAddMedica = new Intent(MedicacaoActivity.this, MedicacaoActivity.class);
                startActivity(intentVoltarAddMedica);
            }
        });

        btnCancMed = (Button) findViewById(R.id.btnCancMed);
        btnCancMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCanMedd = new Intent(MedicacaoActivity.this, MedicacaoActivity.class);
                startActivity(intentCanMedd);
            }
        });


        //recupera o valor dos campos e botÃ£o

        edttxtDataMedicamento = (EditText) findViewById(R.id.edttxtDataMedicamento);
        edttxtNomeMedicamento  = (EditText) findViewById(R.id.edttxtNomeMedicamento);
        edttxtDoseMedicamento  = (EditText) findViewById(R.id.edttxtDoseMedicamento);
        edttxtQntDiaMedi  = (EditText) findViewById(R.id.edttxtQntDiaMedi);
        btnSalvarMedicacao  = (Button) findViewById(R.id.btnSalvarMedicacao);


         //Inicializa banco de dados
        inicializarFirebase();

        //salvar campos
        btnSalvarMedicacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edttxtNomeMedicamento.getText().toString().equals("")
                        && !edttxtDataMedicamento.getText().toString().equals("")
                        && !edttxtDoseMedicamento.getText().toString().equals("")
                        && !edttxtQntDiaMedi.getText().toString().equals("")){
                    //Intancia
                    //usuarios = new Usuarios();
                    medicamento = new Medicamento();
                    //medicamento.setIdmedicamento(UUID.randomUUID().toString());
                    medicamento.setIdUsuario(idUsuarioLogado);
                    medicamento.setNomeMedicamento(edttxtNomeMedicamento.getText().toString());
                    medicamento.setDataMedicamento(edttxtDataMedicamento.getText().toString());
                    medicamento.setDoseRemedio(Double.parseDouble(edttxtDoseMedicamento.getText().toString()));
                    medicamento.setQnRemedioPorDia(Integer.parseInt(edttxtQntDiaMedi.getText().toString()));
                    databaseReference = ConfiguracaoFirebase.getFirebase().child("Medicamentos");
                    databaseReference.child(idUsuarioLogado).push().setValue(medicamento);
                    Toast.makeText(MedicacaoActivity.this, "Medicamento registrado com sucesso", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MedicacaoActivity.this, "Preenchimento de Todos os campos sao obrigatorios", Toast.LENGTH_SHORT).show();
                }
                //medicamento.salvar();
               // SalvarMedicamento();

            }
        });


    }


    private void inicializarFirebase() {
        FirebaseApp.initializeApp(MedicacaoActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }
}
