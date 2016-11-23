package com.example.dell.proyecto;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.dell.dao.ResultadosDao;
import com.example.dell.modelo.ModelResultados;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by aalvarado on 31/10/2016.
 */
public class Resultados extends Fragment {

    View myView;
    ResultadosDao resultadosDao;
    Spinner spinner;
    ListView listViewResultados;
    ResultadosAdapter adapterResultados;
    int id_cita;
    EditText seleccionarFoto;
    String pathImage;
    EditText fechaResultado;
    private int year, month, day;
    Calendar calendar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        id_cita = getArguments().getInt("id_cita");
        myView = inflater.inflate(R.layout.fragment_resultados, container, false);
        resultadosDao = new ResultadosDao(myView.getContext());
        listViewResultados = (ListView) myView.findViewById(R.id.listViewResultados);
        fechaResultado = (EditText) myView.findViewById(R.id.edtFecha_res);


        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);
        fechaResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(myView.getContext(), myDateListener, year, month, day).show();
            }
        });

        seleccionarFoto = (EditText) myView.findViewById(R.id.edtAdjunto);

        seleccionarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPikerIntent = new Intent(Intent.ACTION_PICK);
                photoPikerIntent.setType("image/*");
                startActivityForResult(photoPikerIntent, 100);
            }
        });

        consultarResultados();

        Button botonGuardar = (Button) myView.findViewById(R.id.btnGuardarResultados);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ModelResultados modelResultados = new ModelResultados();
                    EditText edtAdjunto = (EditText) myView.findViewById(R.id.edtAdjunto);
                    EditText edtFecha = (EditText) myView.findViewById(R.id.edtFecha_res);
                    modelResultados.setAdjunto(pathImage);
                    modelResultados.setFecha(new Date(edtFecha.getText().toString()));
                    modelResultados.setId_cita(id_cita);
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

        ArrayList<ModelResultados> listadoResultados = new ArrayList<ModelResultados>();
        try {
            listadoResultados = resultadosDao.consultarPorIdCitas(id_cita);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        adapterResultados = new ResultadosAdapter(myView.getContext(), listadoResultados);
        listViewResultados.setAdapter(adapterResultados);
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        fechaResultado.setText(new StringBuilder().append(year).append("/")
                .append(month).append("/").append(day));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SubirFoto subirFoto = new SubirFoto();
        pathImage = subirFoto.guardarFoto(requestCode, resultCode, data, myView);
        seleccionarFoto.setText(pathImage);
    }
}
