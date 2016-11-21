package com.example.dell.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dell.dao.UsuariosDao;
import com.example.dell.modelo.Usuario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LuisLeon on 21/11/2016.
 */
public class Regis  extends AppCompatActivity implements  AdapterView.OnItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_mama);

       // myview = inflater.inflate(R.layout.registro_mama, container, false);
        String [] Meses = {"1","2","3","4", "5", "6", "7", "8", "9"};
        final Spinner spinner = (Spinner) findViewById(R.id.meses);


        ArrayAdapter adapter= new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, Meses);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        //Boton Registrar
        Button botonRegistrar = (Button) findViewById(R.id.registrar);
        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    UsuariosDao bd = new UsuariosDao(getApplicationContext());
                    EditText nombre = (EditText) findViewById(R.id.nombre);
                    EditText apellido = (EditText) findViewById(R.id.apellido);
                    EditText correo = (EditText) findViewById(R.id.correo);
                    EditText usuariov = (EditText) findViewById(R.id.usuario);
                    Spinner meses = (Spinner) findViewById(R.id.meses);
                    EditText contraseña = (EditText) findViewById(R.id.contraseña);


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

                            cod = bd.agregarUsuario(usuario, getApplicationContext());

                            Toast toast = Toast.makeText(getApplicationContext(), "Acaba de Registrar el Usuario con ID:   " + cod, Toast.LENGTH_SHORT);
                            toast.show();
                            nombre.setText("");
                            apellido.setText("");
                            correo.setText("");
                            usuariov.setText("");
                            contraseña.setText("");
                            Intent login = new Intent(getApplicationContext(),Login.class);
                            startActivity(login);


                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Correo Invalido", Toast.LENGTH_SHORT);
                            toast.show();
                        }

                    } else {
                        if (!mat.find()) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Datos Incorrectos (Correo, nombres y/o Apellido", Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "Nombre y apellido solo deben contener LETRAS", Toast.LENGTH_SHORT);
                            toast.show();
                        }

                    }
                } catch (Exception e) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Error" + e.getMessage(), Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });

        //Boton Volver
        Button botonVolver = (Button) findViewById(R.id.vovler);
        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(getApplicationContext(),Login.class);
                startActivity(login);

            }
        });





















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
