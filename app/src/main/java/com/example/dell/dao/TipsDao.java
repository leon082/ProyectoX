package com.example.dell.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dell.modelo.Tips;

/**
 * Created by Dell on 2/11/2016.
 */
public class TipsDao extends ConexionBD{
    public TipsDao(Context context) {
        super(context);
    }

    public void agregarTips(Tips[] tips) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            for(int i=0;i<tips.length;i++) {

                ContentValues values = new ContentValues();
                values.put(TIPS_DESCRIPCION, tips[i].getTip());
                values.put(TIPS_MES, tips[i].getMes());
                int fila = (int) db.insert(TABLA_TIPS, null, values);
            }
            db.close();

        } catch (Exception e) {
            System.out.println("Error************" + e);

        }

    }




    public boolean consultarTips() {
        try {
            int  i=0;
            SQLiteDatabase db = this.getReadableDatabase();
            String[] projection = {};

            Cursor cursor =
                    db.query(TABLA_TIPS,
                            projection,
                            "",
                            null,
                            null,
                            null,
                            null,
                            null);

            if (cursor.getCount()>0) {
                return true;

            }else{

                return false;
            }
        } catch (Exception e) {

            return false;

        }

    }

    public Tips[] consultarTipsByMes(String mes) {

        try {
            Tips[] tips;

            SQLiteDatabase db = this.getReadableDatabase();
            String[] projection = {TIPS_ID,TIPS_DESCRIPCION,TIPS_MES};

            Cursor cursor =
                    db.query(TABLA_TIPS,
                            projection,
                            "mes = ?",
                            new String[]{mes},
                            null,
                            null,
                            null,
                            null);

            if(cursor.moveToFirst()) {
                tips = new Tips[cursor.getCount()];
                int i=0;
                do {
                    Tips tip =new Tips();
                    tip.setCodigo(Integer.parseInt(cursor.getString(0)));
                    tip.setTip(cursor.getString(1));
                    tip.setMes(cursor.getString(2));
                    tips[i]=tip;
                    i++;
                }while (cursor.moveToNext());
                db.close();
                return tips;

            } else{
                tips=new Tips[2];
                tips[0]=null;
                return tips;
            }
        } catch (Exception e) {

            Tips [] tips=new Tips[2];
            tips[0]=null;
            return tips;

        }

    }
}
