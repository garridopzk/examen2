package com.example.examen2;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Encuentra las vistas que deseas animar
        ImageView splashImage = findViewById(R.id.splash_image);
        TextView splashText = findViewById(R.id.splash_text);

        // Carga la animación
        Animation zoom = AnimationUtils.loadAnimation(this, R.anim.zoom);

        // Inicia la animación en las vistas
        splashImage.startAnimation(zoom);
        splashText.startAnimation(zoom);

        // Duración del Splash Screen
        int SPLASH_TIME_OUT = 2000; // 2 segundos

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Inicia la MainActivity2
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
