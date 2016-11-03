package com.example.dell.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.dell.modelo.ModelResultados;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;


/**
 * Created by aalvarado on 31/10/2016.
 */
public class ResultadosDao extends ConexionBD{
    public ResultadosDao(Context context) {
        super(context);
    }

    public int agregarResultado(ModelResultados modelResultados) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(TimeZone.getTimeZone("-5GMT"));
        String formattedDate = formatter.format(modelResultados.getFecha());

        int fila = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("adjunto", modelResultados.getAdjunto());
        values.put("fecha", formattedDate);
        fila = (int) db.insert("resultados", null, values);
        db.close();
        return fila;
    }

    public ModelResultados[] consultarResultados() throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {"id", "adjunto", "fecha"};

        Cursor cursor = db.query("resultados",
                projection,
                null,
                null,
                null,
                null,
                null,
                null);

        int i = 0;
        ModelResultados resultado = new ModelResultados();
        ModelResultados[] listadoResultados = new ModelResultados[cursor.getCount()];
        while(cursor.moveToNext()){
            resultado.setId(cursor.getInt(0));
            resultado.setAdjunto(cursor.getString(1));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");;
            resultado.setFecha(format.parse(cursor.getString(2)));
            listadoResultados[i] = resultado;
            i++;
        }
        db.close();
        return listadoResultados;
    }


}
