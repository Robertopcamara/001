package com.example.roberto.teste001;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class Main2Activity extends AppCompatActivity {
    Button btnCadastrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnCadastrar = (Button)findViewById(R.id.add_button);
        btnCadastrar.setOnClickListener(new CreateDispositivoOnClickListener());

        atualizarListaDeContatos();

        }

    public void atualizarListaDeContatos(){
        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.objetosDispositivos);
        linearLayoutRecords.removeAllViews();
        List<Dispositivo> dispositivos = new DispositivoController(this).listarDispositivos();

        if (dispositivos.size() >0){

            for (Dispositivo obj : dispositivos){

                int id = obj.getId();
                String nome = obj.getNome();
                String SerialNumber = obj.getSerialNumber();

                String txtViewDispositivos = nome+ "-" + SerialNumber;

                TextView textViewDispositivoItem = new TextView(this);
                textViewDispositivoItem.setPadding(0, 10, 0, 10);
                textViewDispositivoItem.setText(txtViewDispositivos);
                textViewDispositivoItem.setTag(Integer.toString(id));

                linearLayoutRecords.addView(textViewDispositivoItem);
                textViewDispositivoItem.setOnLongClickListener(new RetrieveOnLongClickListener());

            }

        }

    }

}

