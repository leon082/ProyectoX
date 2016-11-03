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
import com.example.dell.dao.ResultadosDao;
import com.example.dell.modelo.ModelResultados;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by aalvarado on 31/10/2016.
 */
public class Resultados extends Fragment {

    View myView;
    ResultadosDao resultadosDao;
    Spinner spinner;
    ListView listViewResultados;
    ArrayAdapter<ModelResultados> adapterResultados;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_resultados, container, false);
        resultadosDao = new ResultadosDao(myView.getContext());
        listViewResultados = (ListView) myView.findViewById(R.id.listViewResultados);

        consultarResultados();

        Button botonGuardar = (Button) myView.findViewById(R.id.btnGuardarResultados);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ModelResultados modelResultados = new ModelResultados();
                    EditText edtAdjunto = (EditText) myView.findViewById(R.id.edtAdjunto);
                    EditText edtFecha = (EditText) myView.findViewById(R.id.edtFecha_res);
                    modelResultados.setAdjunto(edtAdjunto.getText().toString());
                    modelResultados.setFecha(new Date(edtFecha.getText().toString()));
                    int id = resultadosDao.agregarResultado(modelResultados);

                    Toast toast = Toast.makeText(myView.getContext(), "Registro almacenado con exito, id = "+id, Toast.LENGTH_SHORT);
                    toast.show();
                    consultarResultados();
                } catch (Exception e) {
                    Toast toast = Toast.makeText(myView.getContext(), "Error " + e.getMessage(), Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        return myView;
    }
    public void consultarResultados(){

        ModelResultados[] listadoResultados = null;
        try {
            listadoResultados = resultadosDao.consultarResultados();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        adapterResultados = new ArrayAdapter<ModelResultados>(myView.getContext(), android.R.layout.simple_list_item_1, listadoResultados);
        listViewResultados.setAdapter(adapterResultados);
    }
}
