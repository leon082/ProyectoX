package com.example.dell.proyecto;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dell.modelo.Usuario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.example.dell.dao.UsuariosDao;

/**
 * Created by LuisLeon on 19/10/2016.
 */
public class Registro_Mama extends android.app.Fragment implements  AdapterView.OnItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {
    View myview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myview = inflater.inflate(R.layout.registro_mama, container, false);
        String [] Meses = {"1","2","3","4", "5", "6", "7", "8", "9"};
        final Spinner spinner = (Spinner) myview.findViewById(R.id.meses);


        ArrayAdapter adapter= new ArrayAdapter(myview.getContext(),android.R.layout.simple_spinner_dropdown_item, Meses);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);






        //Boton Registrar
        Button botonRegistrar = (Button) myview.findViewById(R.id.registrar);
        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    UsuariosDao bd = new UsuariosDao(myview.getContext());
                    EditText nombre = (EditText) myview.findViewById(R.id.nombre);
                    EditText apellido = (EditText) myview.findViewById(R.id.apellido);
                    EditText correo = (EditText) myview.findViewById(R.id.correo);
                    EditText usuariov = (EditText) myview.findViewById(R.id.usuario);
                    Spinner meses = (Spinner) myview.findViewById(R.id.meses);
                    EditText contraseña = (EditText) myview.findViewById(R.id.contraseña);


                    Usuario usuario = new Usuario();
                    usuario.setNombre(nombre.getText().toString());
                    usuario.setApellido(apellido.getText().toString());
                    usuario.setCorreo(correo.getText().toString());
                    usuario.setMeses(Integer.valueOf(meses.getSelectedItem().toString()));
                    usuario.setUsuario(usuariov.getText().toString());
                    usuario.setContraseña(contraseña.getText().toString());
                    Pattern pat = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                    Matcher mat = pat.matcher(usuario.getCorreo());
                    if ((usuario.getNombre().matches("([a-z]|[A-Z]|\\s)+")) && (usuario.getApellido().matches("([a-z]|[A-Z]|\\s)+"))) {

                        if (mat.find()) {
                            int cod;

                            cod = bd.agregarUsuario(usuario, myview.getContext());

                            Toast toast = Toast.makeText(myview.getContext(), "Acaba de Registrar el Usuario con ID:   " + cod, Toast.LENGTH_SHORT);
                            toast.show();
                            nombre.setText("");
                            apellido.setText("");
                            correo.setText("");
                            usuariov.setText("");
                            contraseña.setText("");


                        } else {
                            Toast toast = Toast.makeText(myview.getContext(), "Correo Invalido", Toast.LENGTH_SHORT);
                            toast.show();
                        }

                    } else {
                        if (!mat.find()) {
                            Toast toast = Toast.makeText(myview.getContext(), "Datos Incorrectos (Correo, nombres y/o Apellido", Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            Toast toast = Toast.makeText(myview.getContext(), "Nombre y apellido solo deben contener LETRAS", Toast.LENGTH_SHORT);
                            toast.show();
                        }

                    }
                } catch (Exception e) {
                    Toast toast = Toast.makeText(myview.getContext(), "Error" + e.getMessage(), Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });



















        return myview;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }
}





