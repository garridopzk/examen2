package com.example.examen2;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {

    private Button button0_12;
    private Button button13_17;
    private Button button18_25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        button0_12 = findViewById(R.id.button_0_12);
        button13_17 = findViewById(R.id.button_13_17);
        button18_25 = findViewById(R.id.button_18_25);

        button0_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Categoría: 0 - 12 años");
                Intent intent = new Intent(MainActivity4.this, MainActivity5.class);
                startActivity(intent);
            }
        });

        button13_17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Categoría: 13 - 17 años");
                Intent intent = new Intent(MainActivity4.this, MainActivity6.class);
                startActivity(intent);
            }
        });

        button18_25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Categoría: 18 - 25 años");
                Intent intent = new Intent(MainActivity4.this, MainActivity7.class);
                startActivity(intent);
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
