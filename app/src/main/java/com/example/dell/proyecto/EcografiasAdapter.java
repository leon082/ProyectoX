package com.example.dell.proyecto;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.modelo.ModelEcografias;
import java.util.ArrayList;

/**
 * Created by Dell on 22/11/2016.
 */
public class EcografiasAdapter extends ArrayAdapter {

    private Context context;
    private ModelEcografias[] ecografias;

    public EcografiasAdapter(Context context, ModelEcografias[] ecografias) {
        super(context, R.layout.custom_list_ecografias, ecografias);
        this.context = context;
        this.ecografias = ecografias;
    }

    @Override
    public int getCount() {
        return 0;
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
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.custom_list_ecografias, null);

        ImageView imagen = (ImageView) item.findViewById(R.id.imageView1);
        Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/"+ecografias[position].getImagen());
        imagen.setImageBitmap(bitmap);

        TextView nombre = (TextView) item.findViewById(R.id.textViewEcografia);
        nombre.setText("Mes Ecografia: "+ecografias[position].getMes());

        return item;
    }
}
