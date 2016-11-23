package com.example.dell.proyecto;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dell on 22/11/2016.
 */
public class SubirFoto {

    public String guardarFoto(int requestCode, int resultCode, Intent data, View myView){
        String path = "";
        switch (requestCode){
            case 100:
                if(resultCode == Activity.RESULT_OK){
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = myView.getContext().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();
                    Bitmap yourSelectImage = BitmapFactory.decodeFile(filePath);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_m_s");
                    path = createDirectoryAndSaveFile(yourSelectImage, "imagen"+dateFormat.format(new Date())+".jpg", myView);


                }
        }

        return path;
    }

    public String createDirectoryAndSaveFile(Bitmap imageToSave, String fileName, View myView) {

        File direct = new File(Environment.getExternalStorageDirectory() + "/Ecografias");

        if (!direct.exists()) {
            File wallpaperDirectory = new File("/sdcard/Ecografias/");
            wallpaperDirectory.mkdirs();
        }

        File file = new File(new File("/sdcard/Ecografias/"), fileName);
        String pathImage = file.getAbsolutePath();
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            Toast toast = Toast.makeText(myView.getContext(), "Error " + e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();

        }
        return pathImage;
    }
}
