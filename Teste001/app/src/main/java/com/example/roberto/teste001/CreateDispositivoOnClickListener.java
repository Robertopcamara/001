package com.example.roberto.teste001;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateDispositivoOnClickListener implements View.OnClickListener{

    @Override
    public void onClick(View v) {

        final Context context = v.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final  View formElementsView = inflater.inflate(R.layout.dispositivo_form, null, false);

        final EditText editTextNome = (EditText) formElementsView.findViewById(R.id.editTextNome);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Adicionar Dispositivo")
                .setPositiveButton("Incluir",
                        new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int id){
                        String dispositivoNome = editTextNome.getText().toString();

                        Dispositivo dispositivo = new Dispositivo();
                        dispositivo.setNome(dispositivoNome);

                        boolean criadoComSucesso = new DispositivoController(context).create(dispositivo);

                        if (criadoComSucesso){
                            Toast.makeText(context, "Um novo dispositivo foi adicionado",Toast.LENGTH_SHORT).show();

                            ((Main2Activity) context).atualizarListaDeContatos();
                        }else {
                            Toast.makeText(context,"Não foi possível adicionar o dispositivo",Toast.LENGTH_SHORT).show();

                        }

                        dialog.cancel();

                    }
                        }).show();


    }
}
