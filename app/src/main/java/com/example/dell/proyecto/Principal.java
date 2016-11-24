package com.example.dell.proyecto;

import android.app.Fragment;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.example.dell.dao.TipsDao;
import com.example.dell.modelo.Tips;

import com.example.dell.ext.MiServicio;

public class Principal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    /**
     * Instancia del drawer
     */
    private DrawerLayout drawerLayout;

    /**
     * Titulo inicial del drawer
     */
    private String drawerTitle;
    private ActionBarDrawerToggle mDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navegacion);
        navigationView.setNavigationItemSelectedListener(this);

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.abrir, R.string.cerrar);
        drawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ///final Button botonIniciar = (Button) findViewById(R.id.btnIniciar);

        //botonIniciar.setOnClickListener(this);
        TipsDao bd = new TipsDao(this);
        if(!bd.consultarTips()){
            bd.agregarTips(llenarTips());

        }else{

        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.citas:
                callFragment(item, new Citas());
                return true;

           /* case R.id.registrar:

                callFragment(item, new Registro_Mama());
                return true;
            case R.id.login:

                callFragment(item, new Login());
                return true;*/
            case R.id.salir:
                finish();
                return true;

            case R.id.tips:

                callFragment(item, new Tips_Mama());
                return true;
            case R.id.cancelar:

                callFragment(item, new Cancelar_Cuenta());
                return true;
            case R.id.motivos:

                callFragment(item, new Motivos());
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void callFragment(MenuItem item, Fragment frag){
        android.app.FragmentManager fragmentManager = getFragmentManager();
        item.setChecked(true);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.contenido_fragment);
        relativeLayout.removeAllViews();
        fragmentManager.beginTransaction().replace(R.id.contenido_fragment, frag).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, MiServicio.class);
        startService(i);

    }

    public Tips[] llenarTips(){
        Tips[] tips=new Tips[10];

        int index=0;
        Tips tip1 =new Tips();
        tip1.setMes("1");
        tip1.setTip("Puede que sienta extraño su cuerpo, Es normal !!");
        tips[index]=tip1;
        index ++;
        Tips tip2 =new Tips();
        tip2.setMes("2");
        tip2.setTip("Puede que sienta con mas frecuencia ganas de orinar, NO LAS AGUANTE!");
        tips[index]=tip2;
        index ++;
        Tips tip3 =new Tips();
        tip3.setMes("3");
        tip3.setTip("Ahora deberia usar ropa algo mas suelta.");
        tips[index]=tip3;
        index ++;
        Tips tip4 =new Tips();
        tip4.setMes("4");
        tip4.setTip("El consumo de calcio es muy importante!");
        tips[index]=tip4;
        index ++;
        Tips tip5 =new Tips();
        tip5.setMes("5");
        tip5.setTip("Puede notar que tiene mas energia, las nasueas ya no estan, aproveche!!");
        tips[index]=tip5;
        index ++;
        Tips tip6 =new Tips();
        tip6.setMes("6");
        tip6.setTip("Debiste aumentar unas 3 o 4 libras, trata de mantener en reposo");
        tips[index]=tip6;
        index ++;
        Tips tip7 =new Tips();
        tip7.setMes("7");
        tip7.setTip("Las guias pre partos son de gran ayuda!");
        tips[index]=tip7;
        index ++;
        Tips tip8 =new Tips();
        tip8.setMes("8");
        tip8.setTip("Si tiene problemas para dormir, beber algo caliente puede ayudar");
        tips[index]=tip8;
        index ++;
        Tips tip9 =new Tips();
        tip9.setMes("9");
        tip9.setTip("Prepara todo para tu estaida en el hospital, tener a mano todo es de gran ayuda cuando el bebe llega");
        tips[index]=tip9;
        index ++;

        return tips;

    }
}
