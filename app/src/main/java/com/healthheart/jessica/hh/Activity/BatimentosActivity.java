package com.healthheart.jessica.hh.Activity;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Message;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.healthheart.jessica.hh.DAO.ConfiguracaoFirebase;
import com.healthheart.jessica.hh.Entidades.Batimentos;
import com.healthheart.jessica.hh.Helper.PreferenciasAndroid;
import com.healthheart.jessica.hh.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class BatimentosActivity extends AppCompatActivity {
    private Button btnVoltarPrinpal;
    private Button btnConexao;
    private TextView textBluetooth;

    private RadioButton rbManhaBat;
    private RadioButton rbTardeBati;
    private RadioButton rbNoiteBati;


    //Button btnConexao;
     // TextView textBluetooth;

    private static final int SOLICITA_ATIVACAO = 1;
    private static final int SOLICITA_CONEXAO = 2;
    private static final int MESSAGE_READ = 3;

    ConnectedThread connectedThread;

    Handler mHandler;

    StringBuilder dadosBluetooth = new StringBuilder();   //acumular os dados recebidos

    BluetoothAdapter meuBluetoothAdapter = null;
    BluetoothDevice meuDevice = null;
    BluetoothSocket meuSocket = null;

    boolean conexao = false;

    private static String MAC = null;

    UUID MEU_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    //private TextView textBluetooth;
    private EditText edttxtDataBati;
    private Button btnSalvarBtimentos;
    private Button btnHisPressao;
    private Button btnCancBatimentos;
    private Button btnInfoPressao;

    private Batimentos batimentos;

    //dados usario logado
    private  String idUsuarioLogado;

    private FirebaseAuth autenticacao;

    //Objeto de conexao
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batimentos);

        //Voltar Tela principal
        btnVoltarPrinpal = (Button) findViewById(R.id.btnVoltarPrinpal);
        btnVoltarPrinpal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVoltarTelaPrinci = new Intent(BatimentosActivity.this, PrincipalActivity.class);
                startActivity(intentVoltarTelaPrinci);
            }
        });

        btnHisPressao = (Button) findViewById(R.id.btnHisPressao);
        btnHisPressao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentIrHisBat = new Intent(BatimentosActivity.this, HistoricoBatimentosActivity.class);
                startActivity(intentIrHisBat);
            }
        });

        //cancela batimentos
        btnCancBatimentos = (Button) findViewById(R.id.btnCancBatimentos);
        btnCancBatimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCanBat = new Intent(BatimentosActivity.this, BatimentosActivity.class);
                startActivity(intentCanBat);
            }
        });

        //Info
        btnInfoPressao  = (Button) findViewById(R.id.btnInfoPressao);
        btnInfoPressao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentInfBat = new Intent(BatimentosActivity.this, InfoBatimentosActivity.class);
                startActivity(intentInfBat);
            }
        });


        //btnConexao = (Button) findViewById(R.id.btnConexao);


        textBluetooth = (TextView) findViewById(R.id.textBluetooth);

        meuBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if(meuBluetoothAdapter == null){
            Toast.makeText(getApplicationContext(), "Seu dispositivo não possui bluetooth", Toast.LENGTH_SHORT).show();
        } else if(!meuBluetoothAdapter.isEnabled()){
            Intent ativaBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(ativaBluetooth, SOLICITA_ATIVACAO);
        }

        btnConexao = (Button) findViewById(R.id.btnConexao);

        btnConexao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(conexao){
                    //desconectar
                    try {
                        meuSocket.close();
                        conexao = false;
                        btnConexao.setText("Conectar pulseira");
                        Toast.makeText(getApplicationContext(), "Bluetooth foi desconectado", Toast.LENGTH_SHORT).show();
                    } catch (IOException erro){
                        Toast.makeText(getApplicationContext(), "Ocorreu um erro: " + erro, Toast.LENGTH_SHORT).show();
                    }
                } else{
                    //conectar
                    Intent abreLista = new Intent(BatimentosActivity.this, ListaDispositivos.class);
                    startActivityForResult(abreLista, SOLICITA_CONEXAO);
                }


            }
        });

        mHandler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                if(msg.what == MESSAGE_READ){
                    String recebidos = (String) msg.obj;    //recebo os dados
                    dadosBluetooth.append(recebidos);       // juntos os dados

                    int fimInformacao = dadosBluetooth.indexOf("}");

                    if(fimInformacao > 0){
                        String dadosCompletos = dadosBluetooth.substring(0, fimInformacao);
                        int tamInformacao = dadosCompletos.length();

                        if(dadosBluetooth.charAt(0) == '{'){

                            String dadosFinais = dadosBluetooth.substring(1, tamInformacao);

                            Log.d("Recebidos", dadosFinais);
                            textBluetooth.setText(dadosFinais);
                        }
                        dadosBluetooth.delete(0, dadosBluetooth.length());
                    }
                }
            }
        };



        //recupera identificador usuario
        PreferenciasAndroid preferenciasAndroid = new PreferenciasAndroid(BatimentosActivity.this);
        idUsuarioLogado = preferenciasAndroid.getIdentificador();

        //recupera o valor dos campos e botÃ£o
        textBluetooth = (TextView) findViewById(R.id.textBluetooth);
        edttxtDataBati = (EditText) findViewById(R.id.edttxtDataBati);
        btnSalvarBtimentos = (Button) findViewById(R.id.btnSalvarBtimentos);
        rbManhaBat = (RadioButton) findViewById(R.id.rbManhaBat);
        rbTardeBati = (RadioButton) findViewById(R.id.rbTardeBati);
        rbNoiteBati = (RadioButton) findViewById(R.id.rbNoiteBati);

        //Inicializa banco de dados
        inicializarFirebase();

        // btnSalvarMedicacao.setOnClickListener(new View.OnClickListener()
        btnSalvarBtimentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textBluetooth.getText().toString().equals("") && !edttxtDataBati.getText().toString().equals("")){
                    batimentos = new Batimentos();
                    batimentos.setIdUsuario(idUsuarioLogado);
                    batimentos.setDataBatimento(edttxtDataBati.getText().toString());
                    batimentos.setBatimentos(Double.parseDouble(textBluetooth.getText().toString()));
                    if(rbManhaBat.isChecked()){
                        batimentos.setPeridoBatimento("Manhã");
                    }
                    if(rbTardeBati.isChecked()){
                        batimentos.setPeridoBatimento("Tarde");
                    }
                    if(rbNoiteBati.isChecked()){
                        batimentos.setPeridoBatimento("Noite");
                    }

                    databaseReference = ConfiguracaoFirebase.getFirebase().child("Batimentos");
                    databaseReference.child(idUsuarioLogado).push().setValue(batimentos);
                    Toast.makeText(BatimentosActivity.this, "Batimentos registrado com sucesso", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(BatimentosActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case SOLICITA_ATIVACAO:
                if(resultCode == Activity.RESULT_OK){
                    Toast.makeText(getApplicationContext(), "Bluetooth ativado", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(getApplicationContext(), "Bluetooth não foi ativado", Toast.LENGTH_SHORT).show();
                }
                break;

            case SOLICITA_CONEXAO:
                if(resultCode == Activity.RESULT_OK){
                    MAC = data.getExtras().getString(ListaDispositivos.ENDERECO_MAC);
                    //Toast.makeText(getApplicationContext(), "MAC FINAL: " + MAC, Toast.LENGTH_SHORT).show();
                    meuDevice = meuBluetoothAdapter.getRemoteDevice(MAC);

                    try {
                        meuSocket = meuDevice.createRfcommSocketToServiceRecord(MEU_UUID);

                        meuSocket.connect();
                        conexao = true;

                        connectedThread = new ConnectedThread(meuSocket);
                        connectedThread.start();

                        btnConexao.setText("Desconectar pulseira");

                        Toast.makeText(getApplicationContext(), "Você foi conectado com: " + MAC, Toast.LENGTH_SHORT).show();
                    } catch (IOException erro){

                        conexao = false;
                        Toast.makeText(getApplicationContext(), "Ocorreu um erro: " + erro, Toast.LENGTH_SHORT).show();

                    }


                } else {
                    Toast.makeText(getApplicationContext(), "Falha ao obter o MAC", Toast.LENGTH_SHORT).show();
                }
        }
    }

    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        //Recebe os dados do arduino
        public void run() {
            byte[] buffer = new byte[1024];  // buffer store for the stream
            int bytes; // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    // Read from the InputStream
                    bytes = mmInStream.read(buffer);

                    String dadosBt = new String(buffer, 0, bytes);

                    // Send the obtained bytes to the UI activity
                    mHandler.obtainMessage(MESSAGE_READ, bytes, -1, dadosBt).sendToTarget();

                } catch (IOException e) {
                    break;
                }
            }
        }

        //Envia dados pro arduino
        /* Call this from the main activity to send data to the remote device */
        public void enviar(String dadosenviar) {
            byte[] msgBuffer = dadosenviar.getBytes();
            try {
                mmOutStream.write(msgBuffer);
            } catch (IOException e) { }
        }

    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(BatimentosActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}
