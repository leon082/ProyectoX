package com.example.dell.proyecto;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dell.dao.TipsDao;

import com.example.dell.modelo.Tips;

/**
 * Created by LuisLeon on 20/10/2016.
 */

    public class Tips_Mama extends android.app.Fragment implements AdapterView.OnItemSelectedListener{
    private Tips[] tipsMama;
    private int index=0;
    View myview;
    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        myview= inflater.inflate(R.layout.tips_mama,container,false);
        Button boton = (Button) myview.findViewById(R.id.avanzar);
        TipsDao bd = new TipsDao(myview.getContext());
        String [] Meses = {"1","2","3","4", "5", "6", "7", "8", "9"};
        final Spinner mesS = (Spinner) myview.findViewById(R.id.mesSpinner);


        ArrayAdapter adapter= new ArrayAdapter(myview.getContext(),android.R.layout.simple_spinner_dropdown_item, Meses);
        mesS.setAdapter(adapter);
        mesS.setOnItemSelectedListener(this);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tipMama = (TextView) myview.findViewById(R.id.tipMama);
                if(index <= tipsMama.length-1) {
                    if (tipsMama[index] != null) {

                        tipMama.setText(tipsMama[index].getTip());


                        index++;

                    }
                }else{
                    index=0;
                    tipMama.setText(tipsMama[index].getTip());


                    index++;

                }



            }
        });
        return myview;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String dato = (String) parent.getItemAtPosition(position);
        TipsDao bd = new TipsDao(myview.getContext());
        tipsMama=bd.consultarTipsByMes(dato);
        TextView tipMama = (TextView) myview.findViewById(R.id.tipMama);
        tipMama.setText(tipsMama[0].getTip());






    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
