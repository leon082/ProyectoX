package com.example.dell.proyecto;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.dell.dao.UsuariosDao;

/**
 * Created by LuisLeon on 20/10/2016.
 */
public class Cancelar_Cuenta extends android.app.Fragment {

    View myview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myview = inflater.inflate(R.layout.cancelar_cuenta, container, false);

        //Boton Registrar
        Button botonCancel = (Button) myview.findViewById(R.id.cancel);
        botonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuariosDao bd = new UsuariosDao(myview.getContext());
                EditText user = (EditText) myview.findViewById(R.id.useri);
                EditText contra = (EditText) myview.findViewById(R.id.cont);

                if(bd.consultarUsuarioLoguin(String.valueOf(user.getText()),String.valueOf(contra.getText()))){
                   bd.eliminarUsuario(String.valueOf(user.getText()));
                    Toast toast = Toast.makeText(myview.getContext(), "Cuenta Eliminada", Toast.LENGTH_SHORT);
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
