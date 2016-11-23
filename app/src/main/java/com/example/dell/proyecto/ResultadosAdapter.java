package com.example.dell.proyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.modelo.ModelResultados;

import java.util.ArrayList;

/**
 * Created by Dell on 22/11/2016.
 */
public class ResultadosAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ModelResultados> resultados;
    LayoutInflater inflater;

    public ResultadosAdapter(Context context, ArrayList<ModelResultados> resultados) {
        this.context = context;
        this.resultados = resultados;
    }

    @Override
    public int getCount() {
        return resultados.size();
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

        View item = inflater.inflate(R.layout.custom_list_ecografias, null);

        ImageView imagen = (ImageView) item.findViewById(R.id.imageView1);
        Bitmap bitmap = BitmapFactory.decodeFile(resultados.get(position).getAdjunto());
        imagen.setImageBitmap(bitmap);

        TextView nombre = (TextView) item.findViewById(R.id.textViewEcografia);
        nombre.setText(String.valueOf(resultados.get(position).getFecha()));

        return item;
    }
}
