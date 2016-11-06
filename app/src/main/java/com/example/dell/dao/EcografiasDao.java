package com.example.dell.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dell.modelo.ModelEcografias;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 23/10/2016.
 */
public class EcografiasDao extends ConexionBD {
    public EcografiasDao(Context context) {
        super(context);
    }

    public int agregarEcografia(ModelEcografias modelEcografias){
        int fila = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("imagen", modelEcografias.getImagen());
        values.put("mes", modelEcografias.getMes());
        values.put("id_cita", modelEcografias.getIdCita());
        fila = (int) db.insert("ecografias", null, values);
        db.close();
        return fila;
    }

    public ModelEcografias[] consultarEcografias(){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {"id", "imagen", "mes", "id_cita"};

        Cursor cursor = db.query("ecografias",
                projection,
                null,
                null,
                null,
                null,
                null,
                null);

        //String[] ecografias = new String[cursor.getCount()];
        int i = 0;

        ModelEcografias[] listadoEcografias = new ModelEcografias[cursor.getCount()];
        while(cursor.moveToNext()){
            ModelEcografias ecografia = new ModelEcografias();
            ecografia.setId(cursor.getInt(0));
            ecografia.setImagen(cursor.getString(1));
            ecografia.setMes(cursor.getInt(2));
            ecografia.setIdCita(cursor.getInt(3));
            listadoEcografias[i] = ecografia;
            i++;
        }
        db.close();
        return listadoEcografias;
    }

    public void actualizar(ModelEcografias modelEcografias, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("imagen", modelEcografias.getImagen());
        values.put("mes", modelEcografias.getMes());
        values.put("id_cita", modelEcografias.getIdCita());

        int i = db.update("ecografias", values, " id = ?", new String[] {String.valueOf(id)});
        db.close();
    }

    public boolean eliminarEcografia(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.delete("ecografias", " id = ?", new String[] {String.valueOf(id)});
            db.close();
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public ModelEcografias consultarPorId(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {"id", "imagen", "mes", "id_ecografia"};

        Cursor cursor = db.query("ecografias",
                projection,
                " id = ?",
                new String[] {String.valueOf(id)},
                null,
                null,
                null,
                null);
        String registro = "Registro no encontrado";
        ModelEcografias ecografia = new ModelEcografias();
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            ecografia.setId(cursor.getInt(0));
            ecografia.setImagen(cursor.getString(1));
            ecografia.setMes(cursor.getInt(2));
            ecografia.setIdCita(cursor.getInt(3));
        }
        db.close();
        return ecografia;
    }
}
