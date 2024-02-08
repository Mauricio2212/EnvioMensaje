package com.example.appmovil_delvallefarrera;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;

import com.example.appmovil_delvallefarrera.R;

public class MainActivity extends Activity {

    static final String KEY_TEXT_REPLY = "id_respuesta";
    public static final int requestCode = 1000;
    public EditText texto;
    public Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto = findViewById(R.id.texto);
        boton = findViewById(R.id.boton);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enviarNotificacion();

            }
        });

    }

    public void enviarNotificacion()
    {
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        CharSequence nombreCanal = "Nombre del canal";
        int importancia = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel canal = new NotificationChannel("001", nombreCanal, importancia);
        notificationManager.createNotificationChannel(canal);

        RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                .build();

        //Intent para recibir el dato de respuesta

        Intent respuesta = new Intent(this, MainActivity2.class);
        respuesta.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 10, respuesta, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),"001")
                .setSmallIcon(android.R.drawable.stat_notify_chat)
                .setContentTitle("Nueva notificacion")
                .setContentText(texto.getText().toString())
                .setAutoCancel(true);

        NotificationCompat.Action responder = new NotificationCompat.Action.Builder(
                android.R.drawable.sym_action_chat, "Responder", resultPendingIntent)
                .addRemoteInput(remoteInput)
                .build();
        builder.addAction(responder);

        notificationManager.notify(requestCode,
                builder.build());

    }
}