package com.example.dell.ext;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Dell on 30/10/2016.
 */
public class Autoarranque extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, MiServicio.class);
        context.startService(service);
    }
}
