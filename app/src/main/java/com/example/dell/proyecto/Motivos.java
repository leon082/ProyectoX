package com.example.dell.proyecto;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.dell.dao.MotivosDao;
import com.example.dell.modelo.ModelMotivos;
import java.text.ParseException;

/**
 * Created by aalvarado on 26/10/2016.
 */
public class Motivos extends Fragment {

    View myView;
    MotivosDao motivosDao;
    Spinner spinner;
    ListView listViewMotivos;
    ArrayAdapter<ModelMotivos> adapterMotivos;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_motivos, container, false);
        motivosDao = new MotivosDao(myView.getContext());
        listViewMotivos = (ListView) myView.findViewById(R.id.listViewMotivos);

        consultarMotivos();

        Button botonGuardar = (Button) myView.findViewById(R.id.btnGuardarMotivos);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ModelMotivos modelMotivos = new ModelMotivos();
                    EditText edtDescripcion = (EditText) myView.findViewById(R.id.edtDescripcion);
                    modelMotivos.setDescripcion(edtDescripcion.getText().toString());
                    int id = motivosDao.agregarMotivo(modelMotivos);

                    Toast toast = Toast.makeText(myView.getContext(), "Registro almacenado con exito, id = "+id, Toast.LENGTH_SHORT);
                    toast.show();
                    consultarMotivos();
                } catch (Exception e) {
                    Toast toast = Toast.makeText(myView.getContext(), "Error " + e.getMessage(), Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        return myView;
    }
    public void consultarMotivos(){

        ModelMotivos[] listadoMotivos = null;
        try {
            listadoMotivos = motivosDao.consultarMotivos();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        adapterMotivos = new ArrayAdapter<ModelMotivos>(myView.getContext(), android.R.layout.simple_list_item_1, listadoMotivos);
        listViewMotivos.setAdapter(adapterMotivos);
    }
}
