package engineer.davidauza;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import engineer.davidauza.Modelo.Persona;

public class MainActivity extends AppCompatActivity {

    EditText nombre, correo, telefono, cedula, tipoDeSangre;
    Button guardar, siguiente;

    DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        consultarPreferencias();

        nombre = findViewById(R.id.txt_nombre);
        correo = findViewById(R.id.txt_correo);
        telefono = findViewById(R.id.txt_telefono);
        cedula = findViewById(R.id.txt_cedula);
        tipoDeSangre = findViewById(R.id.txt_tipo_sangre);
        guardar = findViewById(R.id.btn_guardar);
        siguiente = findViewById(R.id.btn_siguiente);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarPreferencias();
                guardarFirebase();
            }
        });

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pasarPantalla();
            }
        });
    }

    private void guardarPreferencias() {
        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nombre", nombre.getText().toString());
        editor.putString("correo", correo.getText().toString());
        editor.putString("telefono", telefono.getText().toString());
        editor.putString("cedula", cedula.getText().toString());
        editor.putString("tipoDeSangre", tipoDeSangre.getText().toString());
        editor.apply();
        Toast.makeText(getApplicationContext(), "Se guardaron las preferencias",
                Toast.LENGTH_SHORT).show();
    }

    private void pasarPantalla() {
        Intent intent = new Intent(MainActivity.this, Perfil.class);
        startActivity(intent);
    }

    private void consultarPreferencias() {
        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        String nombre = preferences.getString("nombre", "");
        if (!nombre.equals("")) {
            pasarPantalla();
        }
    }


    private void guardarFirebase() {
        Persona p = new Persona();
        int id = (int) (Math.random() * 10000) + 10;
        p.setId(Integer.toString(id));
        p.setNombre(nombre.getText().toString());
        p.setCorreo(correo.getText().toString());
        p.setTelefono(telefono.getText().toString());
        p.setCedula(cedula.getText().toString());
        p.setTipoDeSangre(tipoDeSangre.getText().toString());
        referencia.child("Persona").child(p.getId()).setValue(p);
    }
}