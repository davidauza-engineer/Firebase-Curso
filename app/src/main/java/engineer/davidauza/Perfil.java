package engineer.davidauza;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Perfil extends AppCompatActivity {

    TextView nombre, correo, telefono, titulo, cedula, tipoDeSangre;
    Button borrar;
    DatabaseReference referencia = FirebaseDatabase.getInstance().getReference("Persona");
    DatabaseReference refHijo = referencia.child("2931");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        nombre = findViewById(R.id.lbl_nombrePreferencia);
        correo = findViewById(R.id.lbl_correoPreferencia);
        telefono = findViewById(R.id.lbl_telefonoPreferencia);
        cedula = findViewById(R.id.lbl_cedulaPreferencia);
        tipoDeSangre = findViewById(R.id.lbl_tipo_sangrePreferencia);
        borrar = findViewById(R.id.btn_borrar);
        titulo = findViewById(R.id.lbl_titulo_usuario);

//        consultarPreferencias();

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                borrarPreferencias();
            }
        });
    }

//    private void consultarPreferencias() {
//        SharedPreferences preferences = getSharedPreferences("datos", MODE_PRIVATE);
//        nombre.setText(preferences.getString("nombre", ""));
//        correo.setText(preferences.getString("correo", ""));
//        telefono.setText(preferences.getString("telefono", ""));
//    }

    private void borrarPreferencias() {
        SharedPreferences preferences = getSharedPreferences("datos", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        Toast.makeText(this, "Se borraron las preferencias", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.refHijo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("nombre").getValue(String.class);
                String email = dataSnapshot.child("correo").getValue(String.class);
                String phone = dataSnapshot.child("telefono").getValue(String.class);
                String document = dataSnapshot.child("cedula").getValue(String.class);
                String bloodType = dataSnapshot.child("tipoDeSangre").getValue(String.class);
                titulo.setText("Bienvenido, " + name);
                nombre.setText(name);
                correo.setText(email);
                telefono.setText(phone);
                cedula.setText(document);
                tipoDeSangre.setText(bloodType);
                Toast.makeText(getApplicationContext(),
                        "Se cargó de la base de datos", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("TAG", "LoadPost: onCanceled", databaseError.toException());
            }
        });
    }
}
