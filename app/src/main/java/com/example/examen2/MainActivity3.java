package com.example.examen2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity3 extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText usernameInput, passwordInput, ageInput;
    private RadioGroup genderRadioGroup;
    private Button registerButton, selectPhotoButton, showMovieButton, btnMenu;
    private ImageView photoImageView;
    private Bitmap selectedPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // Inicializar vistas
        usernameInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.password_input);
        ageInput = findViewById(R.id.age_input);
        genderRadioGroup = findViewById(R.id.gender_radio_group);
        registerButton = findViewById(R.id.register_button);
        selectPhotoButton = findViewById(R.id.select_photo_button);
        photoImageView = findViewById(R.id.photo_image_view);
        showMovieButton = findViewById(R.id.show_movie_button);
        btnMenu = findViewById(R.id.menu);

        // Configurar el listener para el botón de selección de foto
        selectPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        // Configurar el listener para el botón de registro
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRegistration();
            }
        });

        // Configurar el listener para el botón de mostrar película
        showMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleShowMovie();
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
                startActivity(intent);
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                selectedPhoto = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                photoImageView.setImageBitmap(selectedPhoto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleRegistration() {
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();
        String ageStr = ageInput.getText().toString();

        if (username.isEmpty() || password.isEmpty() || ageStr.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        int age = Integer.parseInt(ageStr);

        int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
        if (selectedGenderId == -1) {
            Toast.makeText(this, "Por favor, seleccione su género", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedPhoto == null) {
            Toast.makeText(this, "Por favor, seleccione una foto", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedGenderRadioButton = findViewById(selectedGenderId);
        String gender = selectedGenderRadioButton.getText().toString();

        // Aquí puedes agregar la lógica para registrar al usuario, por ejemplo:
        // Guardar datos en una base de datos, enviar a un servidor, etc.

        // Mostrar un mensaje de éxito
        String message = "Registro exitoso\nUsuario: " + username + "\nEdad: " + age + "\nGénero: " + gender;
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void handleShowMovie() {
        String ageStr = ageInput.getText().toString();

        if (ageStr.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese su edad", Toast.LENGTH_SHORT).show();
            return;
        }

        int age = Integer.parseInt(ageStr);
        Intent intent;

        if (age >= 0 && age <= 12) {
            showToast("Categoría: 0 - 12 años");
            intent = new Intent(MainActivity3.this, MainActivity5.class);
        } else if (age >= 13 && age <= 17) {
            showToast("Categoría: 13 - 17 años");
            intent = new Intent(MainActivity3.this, MainActivity6.class);
        } else if (age >= 18 && age <= 25) {
            showToast("Categoría: 18 - 25 años");
            intent = new Intent(MainActivity3.this, MainActivity7.class);
        } else {
            showToast("Edad fuera de rango");
            return;
        }

        startActivity(intent);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
