package com.example.dell.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dell.modelo.Usuario;

/**
 * Created by Dell on 2/11/2016.
 */
public class UsuariosDao extends ConexionBD {
    public UsuariosDao(Context context) {
        super(context);
    }

    public int agregarUsuario(Usuario usuario, Context context) {
        try {
            int fila = 0;
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(COLUMNA_NOMBRE, usuario.getNombre());
            values.put(COLUMNA_APELLIDO, usuario.getApellido());
            values.put(COLUMNA_MESES, String.valueOf(usuario.getMeses()));
            values.put(COLUMNA_CORREO, usuario.getCorreo());
            values.put(COLUMNA_USUARIO, usuario.getUsuario());
            values.put(COLUMNA_CONTRASEÑA, usuario.getContraseña());
            fila = (int) db.insert(TABLA_USUARIOS, null, values);
            db.close();
            return fila;
        } catch (Exception e) {
            System.out.println("Error************" + e);
            return 0;
        }

    }

    public Usuario consultarUsuario(int id) {
        Usuario usuario = new Usuario();
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] projection = {COLUMNA_ID, COLUMNA_NOMBRE, COLUMNA_APELLIDO,  COLUMNA_MESES,COLUMNA_CORREO, COLUMNA_USUARIO, COLUMNA_CONTRASEÑA};

            Cursor cursor =
                    db.query(TABLA_USUARIOS,
                            projection,
                            " _id = ? ",
                            new String[]{String.valueOf(id)},
                            null,
                            null,
                            null,
                            null);

            if (cursor != null) {
                cursor.moveToFirst();
                usuario.setCodigo(Integer.parseInt(cursor.getString(0)));
                usuario.setNombre(cursor.getString(1));
                usuario.setApellido(cursor.getString(2));
                usuario.setMeses(Integer.parseInt(cursor.getString(3)));
                usuario.setCorreo(cursor.getString(4));
                usuario.setUsuario(cursor.getString(5));
                usuario.setContraseña(cursor.getString(6));
                db.close();
                return usuario;
            }else{
                usuario=null;
                return usuario;
            }
        } catch (Exception e) {
            usuario=null;
            return usuario;

        }

    }

    public boolean consultarUsuarioLoguin(String usuario, String contra) {
        boolean res=false;
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            String[] projection = {COLUMNA_ID, COLUMNA_NOMBRE, COLUMNA_APELLIDO,  COLUMNA_MESES,COLUMNA_CORREO, COLUMNA_USUARIO, COLUMNA_CONTRASEÑA};

            Cursor cursor =
                    db.query(TABLA_USUARIOS,
                            projection,
                            " usuario = ? ",
                            new String[]{usuario},
                            null,
                            null,
                            null,
                            null);

            if(cursor.moveToFirst()) {

                do {
                    if(contra.equals(cursor.getString(6))){
                        res=true;
                        break;
                    }

                }while (cursor.moveToNext());
                db.close();
                return res;

            } else{

                return false;
            }

        } catch (Exception e) {

            return false;

        }

    }


    public boolean actualizarUsuario (Usuario usuario){
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();


            values.put(COLUMNA_NOMBRE, usuario.getNombre());
            values.put(COLUMNA_APELLIDO, usuario.getApellido());
            values.put(COLUMNA_MESES, String.valueOf(usuario.getMeses()));
            values.put(COLUMNA_CORREO, usuario.getCorreo());
            values.put(COLUMNA_USUARIO, usuario.getUsuario());
            values.put(COLUMNA_CONTRASEÑA, usuario.getContraseña());
            int fila = db.update(TABLA_USUARIOS,
                    values,
                    " _id = "+usuario.getCodigo(),null  );
            db.close();
            return true;
        }catch (Exception e){
            System.out.println("Error*********"+e.getMessage());
            return false;
        }
    }


    public boolean eliminarUsuario (String user){
        SQLiteDatabase bd=this.getWritableDatabase();
        try {
            bd.delete(TABLA_USUARIOS,
                    " usuario = ?",
                    new String[]{String.valueOf(user)});
            bd.close();
            return true;

        }catch (Exception e){
            return false;
        }

    }
}
