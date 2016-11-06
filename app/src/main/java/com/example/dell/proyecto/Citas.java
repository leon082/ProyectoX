package com.example.dell.proyecto;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dell.dao.CitasDao;
import com.example.dell.modelo.ModelCitas;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Dell on 23/10/2016.
 */
public class Citas extends Fragment {

    View myView;
    CitasDao citasDao;
    Spinner spinner;
    ListView listViewCitas;
    ArrayAdapter<ModelCitas> adapterCitas;
    EditText fechaCita, edtHora;
    Calendar calendar;
    private int year, month, day;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_citas, container, false);
        citasDao = new CitasDao(myView.getContext());

        String motivos[] = {"motivo 1", "motivo 2", "motivo 3", "motivo 4"};
        ArrayAdapter adapter = new ArrayAdapter(myView.getContext(), android.R.layout.simple_spinner_dropdown_item, motivos);
        spinner = (Spinner) myView.findViewById(R.id.spinner_motivo);
        spinner.setAdapter(adapter);

        listViewCitas = (ListView) myView.findViewById(R.id.listViewCitas);

        fechaCita = (EditText) myView.findViewById(R.id.edtFecha);
        edtHora = (EditText) myView.findViewById(R.id.edtHora);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        fechaCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(myView.getContext(), myDateListener, year, month, day).show();
            }
        });

        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        edtHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(myView.getContext(), mTimePicker, hour, minute, true).show();
            }
        });

        consultarCitas();


        Button botonGuardar = (Button) myView.findViewById(R.id.btnGuardarCita);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ModelCitas modelCitas = new ModelCitas();
                    fechaCita = (EditText) myView.findViewById(R.id.edtFecha);
                    edtHora = (EditText) myView.findViewById(R.id.edtHora);
                    EditText edtClinica = (EditText) myView.findViewById(R.id.edtClinica);
                    String motivo = spinner.getSelectedItem().toString();
                    //consultar el id del motivo por el  nombre
                    int idMotivo = spinner.getSelectedItemPosition();
                    modelCitas.setFecha(new Date(fechaCita.getText().toString()));
                    modelCitas.setHora(edtHora.getText().toString());
                    modelCitas.setClinica(edtClinica.getText().toString());
                    modelCitas.setCumplimiento(false);
                    modelCitas.setIdUsuario(1);
                    modelCitas.setIdMotivo(idMotivo);
                    int id = citasDao.agregarCita(modelCitas);

                    Toast toast = Toast.makeText(myView.getContext(), "Registro almacenado con exito, id = "+id, Toast.LENGTH_SHORT);
                    toast.show();
                    consultarCitas();
                } catch (Exception e) {
                    Toast toast = Toast.makeText(myView.getContext(), "Error " + e.getMessage(), Toast.LENGTH_SHORT);
                    toast.show();
                }


            }
        });
        return myView;
    }

    private TimePickerDialog.OnTimeSetListener mTimePicker = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            edtHora.setText( hourOfDay + ":" + minute);
        }
    };

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
        fechaCita.setText(new StringBuilder().append(year).append("/")
                .append(month).append("/").append(day));
    }
    public void consultarCitas(){

        ModelCitas[] listadoCitas = null;
        try {
            listadoCitas = citasDao.consultarCitas();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        adapterCitas = new ArrayAdapter<ModelCitas>(myView.getContext(), android.R.layout.simple_list_item_1, listadoCitas);

        listViewCitas.setAdapter(adapterCitas);
    }
}
