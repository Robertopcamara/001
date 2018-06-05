package com.example.roberto.teste001;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.roberto.teste001.R.id.editTextNome;

public class RetrieveOnLongClickListener implements View.OnLongClickListener {
   Context context;
   String id;


    @Override
    public boolean onLongClick(View view) {

        context = view.getContext();
        id = view.getTag().toString();

        final CharSequence[] itens = {"Editar","Deletar"};

        new AlertDialog.Builder(context).setTitle("Detalhes do Dispositivo").setItems(itens, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (item ==0){

                    editDispositivoPeloId(Integer.parseInt(id));

                } else if (item ==1){

                }

            }
        }).show();

        Toast.makeText(view.getContext(),"Clicado em um item da lista",Toast.LENGTH_SHORT).show();




        return false;
    }

    public void editDispositivoPeloId(final int dispositivoID){

        final DispositivoController dispositivoController = new DispositivoController((context));

        final Dispositivo dispositivo = dispositivoController.buscarpeloid(dispositivoID);

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        final View formDispositivo = inflater.inflate(R.layout.dispositivo_form,null,false);

        final EditText editTextNome = (EditText)formDispositivo.findViewById(R.id.editTextNome);

        editTextNome.setText(dispositivo.getNome());

        new AlertDialog.Builder(context)
                .setView(formDispositivo)
                .setTitle("Editar")
                .setPositiveButton("Atualizar dados",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                Dispositivo novoDispositivo = new Dispositivo();
                                novoDispositivo.setId(dispositivoID);
                                novoDispositivo.setNome(editTextNome.getText().toString());

                                boolean isUpdate = dispositivoController.update(novoDispositivo);

                                if (isUpdate){
                                    Toast.makeText(context,"Dados atualizados com sucesso...",Toast.LENGTH_SHORT).show();

                                    ((Main2Activity) context).atualizarListaDeContatos();

                                }else {
                                    Toast.makeText(context,"Falha ao salvar dispositivo",Toast.LENGTH_SHORT).show();

                                }
                                dialog.cancel();
                            }
                        }).show();
    }

}
