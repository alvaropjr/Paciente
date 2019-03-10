package com.healthheart.jessica.hh.Activity;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.healthheart.jessica.hh.R;

import java.util.Set;

/**
 * Created by Jessica on 25/10/2017.
 */

public class ListaDispositivos extends ListActivity {

    private BluetoothAdapter meuBluetoothAdapter2 = null;

    static String ENDERECO_MAC = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> ArrayBluettoth = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        //ArrayAdapter<String> ArrayBluettoth = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        //      LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        //view = inflater.inflate(R.layout.lista_batimentos, parent, false);

        meuBluetoothAdapter2 = BluetoothAdapter.getDefaultAdapter();

        Set<BluetoothDevice> dispositivosPareados = meuBluetoothAdapter2.getBondedDevices();

        if(dispositivosPareados.size() > 0){
            for(BluetoothDevice dispositivo : dispositivosPareados){
                String nomeBt = dispositivo.getName();
                String macBt = dispositivo.getAddress();
                ArrayBluettoth.add(nomeBt + "\n" + macBt);
            }
        }
        setListAdapter(ArrayBluettoth);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String informacaoGeral = ((TextView) v).getText().toString();
        //Toast.makeText(getApplicationContext(), "Info: " + informacaoGeral,Toast.LENGTH_LONG).show();

        String enderecoMac = informacaoGeral.substring(informacaoGeral.length() - 17);
        //Toast.makeText(getApplicationContext(), "Mac: " + enderecoMac,Toast.LENGTH_LONG).show();

        Intent retornaMac = new Intent();
        retornaMac.putExtra(ENDERECO_MAC, enderecoMac);
        setResult(RESULT_OK, retornaMac);
        finish();
    }

}
