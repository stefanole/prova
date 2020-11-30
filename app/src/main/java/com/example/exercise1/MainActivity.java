package com.example.exercise1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String[] users = { "Italy", "Germany", "France", "Poland", "Spain" };
    private Button imageBtn, btnRegister;
    private EditText name,email,password,password2;
    private RadioButton male,female,other;
    private final String TAG = "MyActivity";
    private CheckBox cbAgree;
    private String gender;
    private int positionCountry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initialize();

        /*if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fra, ExampleFragment.class, null)
                    .commit();
        }*/


        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
                Toast.makeText(MainActivity.this,"Image picker clicked",Toast.LENGTH_LONG).show();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validationForm()) Toast.makeText(MainActivity.this, "Compila i campi!", Toast.LENGTH_LONG).show();
                else if (!validatePassword()) Toast.makeText(MainActivity.this, "Le password sono diverse!", Toast.LENGTH_LONG).show();
                else if (!isChecked()) Toast.makeText(MainActivity.this, "Accetta la licenza", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(MainActivity.this,"Register Button clicked",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    updateData(intent);
                    startActivity(intent);
                    
                }
            }
        });

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "Maschio";
                Toast.makeText(getApplicationContext(),"Male selected",Toast.LENGTH_SHORT).show();
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "Femmina";
                Toast.makeText(getApplicationContext(),"Female selected",Toast.LENGTH_SHORT).show();
            }
        });

        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "Sconosciuto";
                Toast.makeText(getApplicationContext(),"Other selected",Toast.LENGTH_SHORT).show();
            }
        });




        Spinner spin = (Spinner) findViewById(R.id.spinnerCountries);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);
    }

    private void initialize(){
        //UI Elements declaration
        //BUTTONS
        imageBtn = (Button) findViewById(R.id.btnPick);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        //EDIT INPUT
        name = (EditText) findViewById(R.id.editTxtName);
        email = (EditText) findViewById(R.id.editTxtEmail);
        password = (EditText) findViewById(R.id.editTxtPassword);
        password2 = (EditText) findViewById(R.id.editTxtPassword2);

        //RADIO BUTTONS
        male = (RadioButton) findViewById(R.id.radioBtnMale);
        female = (RadioButton) findViewById(R.id.radioBtnFemale);
        other = (RadioButton) findViewById(R.id.radioBtnOther);

        //CHECKBOX
        cbAgree = findViewById(R.id.cbAgree);

        //Default gender
        gender = "Maschio";
        //default Nation
        positionCountry = 0;

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(getApplicationContext(),"Selected country: " + users[position], Toast.LENGTH_LONG).show();
        positionCountry = position;
        Log.i(TAG, "MyClass.getView() — get item number " + position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private boolean validationForm(){
        if(name.getText().toString().length() == 0) {
            name.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            name.setHintTextColor(Color.RED);
            return false;
        } else {
            name.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
            name.setHintTextColor(Color.GRAY);
        }
        if(email.getText().toString().length() == 0) {
            email.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            email.setHintTextColor(Color.RED);
            return false;
        } else {
            email.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
            email.setHintTextColor(Color.GRAY);
        }
        if(password.getText().toString().length() == 0) {
            password.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            password.setHintTextColor(Color.RED);
            return false;
        } else {
            password.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
            password.setHintTextColor(Color.GRAY);
        }
        if(password2.getText().toString().length() == 0) {
            password2.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            password2.setHintTextColor(Color.RED);
            return false;
        } else {
            password2.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
            password2.setHintTextColor(Color.GRAY);
        }
        return true;
    }

    private boolean validatePassword(){
        if(password2.getText().toString().compareTo(password.getText().toString()) == 0) return true;
        else return false;
    }

    private boolean isChecked() {
        if(cbAgree.isChecked()) {
            return true;
        } else return false;

    }

    private void updateData(Intent intent){
        intent.putExtra("NOME",name.getText().toString());
        intent.putExtra("EMAIL",email.getText().toString());
        intent.putExtra("GENERE",gender);
        intent.putExtra("NAZIONE",users[positionCountry]);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}