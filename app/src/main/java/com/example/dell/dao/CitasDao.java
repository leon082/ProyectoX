package com.example.dell.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;

import com.example.dell.modelo.ModelCitas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Dell on 23/10/2016.
 */
public class CitasDao extends ConexionBD{

    public CitasDao(Context context) {
        super(context);
    }



    public int agregarCita(ModelCitas modelCitas) throws ParseException {
        int fila = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();



        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(TimeZone.getTimeZone("-5GMT"));

        String formattedDate = formatter.format(modelCitas.getFecha());

        values.put("fecha", formattedDate);
        values.put("hora", modelCitas.getHora());
        values.put("clinica", modelCitas.getClinica());
        values.put("cumplimiento", modelCitas.getCumplimiento());
        values.put("id_usuario", modelCitas.getIdUsuario());
        values.put("id_motivo", modelCitas.getIdMotivo());
        fila = (int) db.insert("citas", null, values);
        db.close();
        return fila;
    }

    public ModelCitas[] consultarCitas() throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {"id", "fecha", "clinica", "cumplimiento", "hora", "id_motivo"};

        Cursor cursor = db.query("citas",
                projection,
                null,
                null,
                null,
                null,
                null,
                null);

        //String[] citas = new String[cursor.getCount()];
        int i = 0;

        ModelCitas[] listadoCitas = new ModelCitas[cursor.getCount()];
        while(cursor.moveToNext()){
            ModelCitas cita = new ModelCitas();
            cita.setId(cursor.getInt(0));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            cita.setFecha(format.parse(cursor.getString(1)));
            cita.setClinica(cursor.getString(2));
            cita.setCumplimiento(Boolean.valueOf(cursor.getString(3)));
            cita.setHora(cursor.getString(4));
            cita.setIdMotivo(cursor.getInt(5));
            listadoCitas[i] = cita;
            i++;
        }
        db.close();
        return listadoCitas;
    }

    public void actualizar(Date fecha, String hora, String clinica, boolean cumplimiento, int idUsuario, int idMotivo, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("fecha", String.valueOf(fecha));
        values.put("hora", hora);
        values.put("clinica", clinica);
        values.put("cumplimiento", cumplimiento);
        values.put("id_usuario", idUsuario);
        values.put("id_motivo", idMotivo);

        int i = db.update("citas", values, " id = ?", new String[] {String.valueOf(id)});
        db.close();
    }

    public boolean eliminarCita(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        try{
            db.delete("citas", " id = ?", new String[] {String.valueOf(id)});
            db.close();
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public ModelCitas  consultarPorId(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {"id", "fecha", "clinica", "cumplimiento", "hora", "id_motivo"};

        Cursor cursor = db.query("citas",
                projection,
                " id = ?",
                new String[] {String.valueOf(id)},
                null,
                null,
                null,
                null);
        String registro = "Registro no encontrado";
        ModelCitas cita = new ModelCitas();
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            cita.setId(cursor.getInt(0));
            cita.setFecha(new Date(cursor.getString(1)));
            cita.setClinica(cursor.getString(2));
            cita.setCumplimiento(Boolean.valueOf(cursor.getString(3)));
            cita.setHora(cursor.getString(4));
            cita.setIdMotivo(cursor.getInt(5));
        }
        db.close();
        return cita;
    }

    public ModelCitas consultarFechaCita(String fecha, String hora) throws ParseException {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT id, fecha, clinica, cumplimiento, hora, id_motivo FROM citas WHERE fecha = ? and hora = ?", new String[]{fecha,hora});

        String registro = "Registro no encontrado";
        ModelCitas cita = new ModelCitas();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDate = null;
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            cita.setId(cursor.getInt(cursor.getColumnIndex("id")));
            String fechaCita = cursor.getString(cursor.getColumnIndex("fecha"));
            fechaDate = formato.parse(fechaCita);
            cita.setFecha(fechaDate);
            cita.setClinica(cursor.getString(cursor.getColumnIndex("clinica")));
            cita.setCumplimiento(Boolean.valueOf(cursor.getString(cursor.getColumnIndex("cumplimiento"))));
            cita.setHora(cursor.getString(cursor.getColumnIndex("hora")));
            cita.setIdMotivo(cursor.getInt(cursor.getColumnIndex("id_motivo")));
        }
        db.close();
        return cita;

    }
}
