package com.example.dell.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.dell.modelo.ModelMotivos;
import java.text.ParseException;

/**
 * Created by aalvarado on 26/10/2016.
 */
public class MotivosDao extends ConexionBD{

    public MotivosDao(Context context) {
        super(context);
    }

    public int agregarMotivo(ModelMotivos modelMotivos) throws ParseException {
        int fila = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("descripcion", modelMotivos.getDescripcion());
        fila = (int) db.insert("motivos", null, values);
        db.close();
        return fila;
    }

    public ModelMotivos[] consultarMotivos() throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {"id", "descripcion"};

        Cursor cursor = db.query("motivos",
                projection,
                null,
                null,
                null,
                null,
                null,
                null);

        int i = 0;
        ModelMotivos motivo = new ModelMotivos();
        ModelMotivos[] listadoMotivos = new ModelMotivos[cursor.getCount()];
        while(cursor.moveToNext()){
            motivo.setId(cursor.getInt(0));
            motivo.setDescripcion(cursor.getString(1));
            listadoMotivos[i] = motivo;
            i++;
        }
        db.close();
        return listadoMotivos;
    }

    public void actualizarMotivo(String descripcion, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("descripcion", descripcion);

        int i = db.update("motivos", values, " id = ?", new String[] {String.valueOf(id)});
        db.close();
    }

    public boolean eliminarMotivo(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.delete("motivo", " id = ?", new String[] {String.valueOf(id)});
            db.close();
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public ModelMotivos  consultarPorId(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {"descripcion"};

        Cursor cursor = db.query("motivos",
                projection,
                " id = ?",
                new String[] {String.valueOf(id)},
                null,
                null,
                null,
                null);
        String registro = "Registro no encontrado";
        ModelMotivos motivo = new ModelMotivos();
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            motivo.setId(cursor.getInt(0));
            motivo.setDescripcion(cursor.getString(1));
        }
        db.close();
        return motivo;
    }
}
