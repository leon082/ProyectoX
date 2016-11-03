package com.example.dell.proyecto;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.dell.dao.UsuariosDao;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LuisLeon on 19/10/2016.
 */
public class Login  extends android.app.Fragment {
    View myview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myview = inflater.inflate(R.layout.login, container, false);

        //Boton Registrar
        Button botonLogin = (Button) myview.findViewById(R.id.ingre);
        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuariosDao bd = new UsuariosDao(myview.getContext());
                EditText user = (EditText) myview.findViewById(R.id.user);
                EditText contra = (EditText) myview.findViewById(R.id.contra);

                if(bd.consultarUsuarioLoguin(String.valueOf(user.getText()),String.valueOf(contra.getText()))){
                    Toast toast = Toast.makeText(myview.getContext(), "Bienvenido", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Toast toast = Toast.makeText(myview.getContext(), "Login no Valido", Toast.LENGTH_SHORT);
                    toast.show();

                }




            }
        });



















        return myview;
    }





}
