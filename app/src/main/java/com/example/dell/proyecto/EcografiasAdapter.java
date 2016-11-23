package com.example.dell.proyecto;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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
public class EcografiasAdapter extends BaseAdapter  {

    private Context context;
    private ArrayList<ModelEcografias> ecografias;
    LayoutInflater inflater;

    public EcografiasAdapter(Context context, ArrayList<ModelEcografias> ecografias) {
        this.context = context;
        this.ecografias = ecografias;
    }

    @Override
    public int getCount() {
        return ecografias.size();
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

        if(position%2==0){
            item.setBackgroundColor(Color.parseColor("#9AB2F5"));
        }else{
            item.setBackgroundColor(Color.parseColor("#E7EBF6"));
        }

        ImageView imagen = (ImageView) item.findViewById(R.id.imageView1);
        Bitmap bitmap = BitmapFactory.decodeFile(ecografias.get(position).getImagen());
        imagen.setImageBitmap(bitmap);

        TextView nombre = (TextView) item.findViewById(R.id.textViewEcografia);
        nombre.setText("Mes Ecografia: "+ecografias.get(position).getMes());

        return item;
    }
}
