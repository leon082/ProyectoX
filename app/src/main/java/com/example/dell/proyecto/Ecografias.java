package com.example.dell.proyecto;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.dao.CitasDao;
import com.example.dell.dao.EcografiasDao;
import com.example.dell.modelo.ModelCitas;
import com.example.dell.modelo.ModelEcografias;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by Dell on 24/10/2016.
 */
public class Ecografias extends Fragment {

    View myView;
    EcografiasDao ecografiasDao;
    Spinner spinner;
    EditText seleccionarFoto;
    String pathImage;
    ArrayAdapter<ModelEcografias> adapterEcografias;
    ListView listViewEcografias;
    int id_cita;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_ecografias, container, false);

        id_cita =getArguments().getInt("id_cita");
        ecografiasDao = new EcografiasDao(myView.getContext());

        String mes[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        ArrayAdapter adapter = new ArrayAdapter(myView.getContext(), android.R.layout.simple_spinner_dropdown_item, mes);
        spinner = (Spinner) myView.findViewById(R.id.spinnerMes);
        spinner.setAdapter(adapter);

        listViewEcografias = (ListView) myView.findViewById(R.id.listViewEcografias);

        consultarEcografias();

        seleccionarFoto = (EditText) myView.findViewById(R.id.edtSeleccionar);

        seleccionarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPikerIntent = new Intent(Intent.ACTION_PICK);
                photoPikerIntent.setType("image/*");
                startActivityForResult(photoPikerIntent, 100);
            }
        });

        Button botonGuardar = (Button) myView.findViewById(R.id.btnGuardarEcografia);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ModelEcografias modelEcografias = new ModelEcografias();
                    String mes = spinner.getSelectedItem().toString();
                    modelEcografias.setImagen(pathImage);
                    modelEcografias.setMes(Integer.parseInt(mes));
                    modelEcografias.setIdCita(id_cita);
                   
                    int id = ecografiasDao.agregarEcografia(modelEcografias);

                    Toast toast = Toast.makeText(myView.getContext(), "Registro almacenado con exito, id = "+id, Toast.LENGTH_SHORT);
                    toast.show();
                    consultarEcografias();
                } catch (Exception e) {
                    Toast toast = Toast.makeText(myView.getContext(), "Error " + e.getMessage(), Toast.LENGTH_SHORT);
                    toast.show();
                }


            }
        });
        return myView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

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
                    createDirectoryAndSaveFile(yourSelectImage, "imagen.jpg");

                }
        }
    }

    public void createDirectoryAndSaveFile(Bitmap imageToSave, String fileName) {

        File direct = new File(Environment.getExternalStorageDirectory() + "/DirName");

        if (!direct.exists()) {
            File wallpaperDirectory = new File("/sdcard/DirName/");
            wallpaperDirectory.mkdirs();
        }

        File file = new File(new File("/sdcard/DirName/"), fileName);
        pathImage = file.getAbsolutePath();
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
    }

    public void consultarEcografias(){

        ModelEcografias[] listadoEcografias = null;
        listadoEcografias = ecografiasDao.consultarPorIdCitas(id_cita);
        //adapterEcografias = new ArrayAdapter<ModelEcografias>(myView.getContext(), android.R.layout.simple_list_item_1, listadoEcografias);
        adapterEcografias = new EcografiasAdapter(getActivity(), listadoEcografias);
        listViewEcografias.setAdapter(adapterEcografias);
    }
}
