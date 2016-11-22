package com.example.dell.ext;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.dell.dao.CitasDao;
import com.example.dell.modelo.ModelCitas;
import com.example.dell.proyecto.Citas;
import com.example.dell.proyecto.Principal;
import com.example.dell.proyecto.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Dell on 26/10/2016.
 */
public class MiServicio extends Service {
    EjecutarNotificacion ejecutarNotificacion;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Servicio creado!", Toast.LENGTH_SHORT).show();
        ejecutarNotificacion = new EjecutarNotificacion(this);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ejecutarNotificacion.execute();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Servicio destruido!", Toast.LENGTH_SHORT).show();
    }


    /**
     * Created by Dell on 26/10/2016.
     */
    private class EjecutarNotificacion extends AsyncTask<String, String, String> {
        Context context;
        SimpleDateFormat dateFormat, horaFormat;
        boolean stop;

        public EjecutarNotificacion(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... params) {

            while(stop){
                try {


                    dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    horaFormat = new SimpleDateFormat("HH:mm");

                    CitasDao citasDao = new CitasDao(context);
                    Calendar calendar = Calendar.getInstance();
                    Date date = new Date();
                    calendar.setTime(date);
                    calendar.add(Calendar.HOUR, 1);
                    Date hora = calendar.getTime();

                    ModelCitas cita = citasDao.consultarFechaCita(dateFormat.format(new Date()), horaFormat.format(hora));

                    if(cita.getId() != 0){
                        publishProgress(cita.getClinica(), "Cita de control");
                    }


                    //publishProgress(dateFormat.format(new Date()), horaFormat.format(hora), "test");

                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            stop = true;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);


                Intent intent = new Intent(context, Principal.class);

                //Se crea el PendingIntent
                PendingIntent pendingIntent = PendingIntent.getActivity(MiServicio.this, 0, intent, 0);

                //Se crea el objeto NotificationCompact.
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MiServicio.this);

                //se Define el icono pequeño que se mostrara en la barra
                builder.setSmallIcon(android.R.drawable.sym_def_app_icon);
                //Se define que intent se lanzara desde la notificación

                builder.setContentIntent(pendingIntent);
                builder.setAutoCancel(true);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.sym_def_app_icon));

                builder.setContentTitle("Alerta cita");

                builder.setContentText("Lugar Cita: " + values[0] + " Motivo: " + values[1]);

                builder.setSubText("Presiona para ver el contenido");


                NotificationManager notificationManager = (NotificationManager) getSystemService(

                        NOTIFICATION_SERVICE);

                notificationManager.notify(1, builder.build());

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            stop = false;
        }
    }


}
