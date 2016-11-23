package com.example.dell.proyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.modelo.ModelCitas;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Dell on 22/11/2016.
 */
public class CitasAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ModelCitas> listaCitas;
    LayoutInflater inflater;

    public CitasAdapter(Context context, ArrayList<ModelCitas> listaCitas) {
        this.context = context;
        this.listaCitas = listaCitas;
    }


    @Override
    public int getCount() {
        return listaCitas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View item = inflater.inflate(R.layout.custom_list_citas, null);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


        if(position%2==0){
            item.setBackgroundColor(Color.parseColor("#9AB2F5"));
        }else{
            item.setBackgroundColor(Color.parseColor("#E7EBF6"));
        }


        TextView id = (TextView) item.findViewById(R.id.textViewIdCita);
        id.setText(id.getText().toString()+": "+listaCitas.get(position).getId());

        TextView fecha = (TextView) item.findViewById(R.id.textViewFechaCita);
        fecha.setText(fecha.getText().toString()+": "+formatter.format(listaCitas.get(position).getFecha()));

        TextView motivo = (TextView) item.findViewById(R.id.textViewMotivo);
        motivo.setText(motivo.getText().toString()+": "+listaCitas.get(position).getIdMotivo());

        TextView clinica = (TextView) item.findViewById(R.id.textViewClinicaCita);
        clinica.setText(clinica.getText().toString()+": "+listaCitas.get(position).getClinica());

        TextView hora = (TextView) item.findViewById(R.id.textViewHora);
        hora.setText(hora.getText().toString()+": "+listaCitas.get(position).getHora());

        return item;
    }
}
