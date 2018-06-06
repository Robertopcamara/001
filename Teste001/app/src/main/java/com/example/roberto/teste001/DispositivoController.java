package com.example.roberto.teste001;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DispositivoController extends DataBaseAdapter {

    public  DispositivoController(Context context){
        super(context);

    }

    public boolean create(Dispositivo dispositivo){

        ContentValues values = new ContentValues();

        values.put("nome", dispositivo.getNome());
        values.put("SerialNumber", dispositivo.getSerialNumber());
        values.put("estado", dispositivo.isEstado());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean isCreate = db.insert("dispositivos",null,values) > 0;
        db.close();

        return isCreate;
    }


    public List<Dispositivo> listarDispositivos(){

        List<Dispositivo> dispositivos = new ArrayList<>();

        String sql = "SELECT * FROM dispositivos ORDER by id DESC";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql,null);

        if (cursor.moveToFirst()){

            do {

                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));

                String nome = cursor.getString(cursor.getColumnIndex("nome"));
                String SerialNumber = cursor.getString(cursor.getColumnIndex("SerialNumber"));
                //Boolean State = cursor.get

                Dispositivo dispositivo = new Dispositivo();

                dispositivo.setId(id);
                dispositivo.setNome(nome);
                dispositivo.setSerialNumber(SerialNumber);

                dispositivos.add(dispositivo);



            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return dispositivos;
    }

    public Dispositivo buscarpeloid(int dispositivoID){

        Dispositivo dispositivo = null;

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM dispositivos WHERE id= "+dispositivoID;

        Cursor cursor = db.rawQuery(sql,null);

        if (cursor.moveToFirst()){

            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String SerialNumber =cursor.getString(cursor.getColumnIndex("SerialNumber"));
            Boolean estado;

            dispositivo = new Dispositivo();

            dispositivo.setId(dispositivoID);
            dispositivo.setNome(nome);
            dispositivo.setSerialNumber(SerialNumber);
            }


        return dispositivo;
    }

    public boolean update(Dispositivo dispositivo){

        ContentValues values = new ContentValues();
        values.put("nome",dispositivo.getNome());

        String where = "id = ?";

        String [] whereArgs = {Integer.toString(dispositivo.getId())};

        SQLiteDatabase db = this.getWritableDatabase();

        boolean isUpdate = db.update("dispositivo", values,where,whereArgs)>0;

        db.close();



        return isUpdate;
    }

    public boolean delete(int dispositivoID){

        boolean isDeletado = false;

        SQLiteDatabase db = this.getWritableDatabase();
        isDeletado = db.delete("dispositivo","id ='"+ dispositivoID +"'",null)>0;
        db.close();


        return isDeletado;
    }




}
