package com.example.exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    private TextView txtName, txtEmail, txtNazione, txtGenere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtNazione = findViewById(R.id.txtNazione);
        txtGenere = findViewById(R.id.txtGenere);

        Toast.makeText(this, getIntent().getStringExtra("NOME"), Toast.LENGTH_SHORT).show();

        txtName.setText(getIntent().getStringExtra("NOME"));
        txtEmail.setText(getIntent().getStringExtra("EMAIL"));
        txtGenere.setText(getIntent().getStringExtra("GENERE"));
        txtNazione.setText(getIntent().getStringExtra("NAZIONE"));


    }
}