package com.example.appmovil_delvallefarrera;

import static com.example.appmovil_delvallefarrera.MainActivity.KEY_TEXT_REPLY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.RemoteInput;

import android.content.Intent;
import android.widget.TextView;
import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity {

    TextView respuesta;
    private static final String KEY_TEXT_REPLY = "id_respuesta";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        respuesta = findViewById(R.id.txtRespuesta);
        respuesta.setText(obtenerMensaje(getIntent()));
    }

    private CharSequence obtenerMensaje(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            return remoteInput.getCharSequence(KEY_TEXT_REPLY);
        }
        return null;
    }
}