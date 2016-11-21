package com.example.dell.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.dell.dao.UsuariosDao;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LuisLeon on 19/10/2016.
 */
public class Login  extends AppCompatActivity {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //Boton Ingresar
        Button botonLogin = (Button) findViewById(R.id.ingre);
        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuariosDao bd = new UsuariosDao(getApplicationContext());
                EditText user = (EditText) findViewById(R.id.user);
                EditText contra = (EditText) findViewById(R.id.contra);

                if(bd.consultarUsuarioLoguin(String.valueOf(user.getText()),String.valueOf(contra.getText()))){
                    Intent registrar = new Intent(getApplicationContext(),Principal.class);
                    startActivity(registrar);
                    Toast toast = Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Login no Valido", Toast.LENGTH_SHORT);
                    toast.show();

                }

            }
        });
        //Registrar

        Button botonRe = (Button) findViewById(R.id.registro);
        botonRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regis = new Intent(getApplicationContext(),Regis.class);
                startActivity(regis);

            }
        });
























    }





}
