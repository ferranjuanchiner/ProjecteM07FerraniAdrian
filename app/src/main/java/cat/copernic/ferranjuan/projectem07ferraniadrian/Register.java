package cat.copernic.ferranjuan.projectem07ferraniadrian;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Register extends AppCompatActivity {
    String TAG = "Musikapp_register";
    EditText usuario;
    EditText pass;
    EditText email;
    EditText nom;
    EditText cognom;
    Button btnRegistrarse;
    Button btnCancelar;
    RadioGroup genere;
    private FirebaseAuth mAuth;
    DatabaseReference myRef;
    private FirebaseAuth.AuthStateListener mAuthListener;
    CheckBox cBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        usuario = findViewById(R.id.etUser);
        pass = findViewById(R.id.etpassword);
        nom = findViewById(R.id.etNom);
        btnRegistrarse = findViewById(R.id.btnRegistro);
        btnCancelar = findViewById(R.id.btnCancelar);
        cBox = findViewById(R.id.cbTerminos);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.etEmail);
        cognom = findViewById(R.id.etApellido);
        genere = findViewById(R.id.radioGroup);

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(email.toString().isEmpty() || pass.getText().toString().isEmpty()){
                Toast.makeText(Register.this,
                        R.string.errorregistre,
                        Toast.LENGTH_SHORT).show();
            }else if(cBox.isChecked()==false){
                Toast.makeText(Register.this,R.string.errortermes,Toast.LENGTH_SHORT).show();
            }else if(pass.length()< 6){
                Toast.makeText(Register.this,R.string.contrasenaIncorrecta,Toast.LENGTH_SHORT).show();
            }
            else {
                /*final String username = usuario.getText().toString();
                final String password = pass.getText().toString();
                    saveLoginSharedPreferences(username, password);
                    Toast.makeText(Register.this,R.string.mssgsaveduser,Toast.LENGTH_SHORT).show();
                    finish();*/
                crearUsuario(email.getText().toString(), pass.getText().toString());
            }
        }
    });
    btnCancelar.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View View){
            finish();
        }
    });}
    void crearUsuario(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(Register.this,R.string.registreCorrecte,Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            añadirDatos();
                            finish();
                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Register.this, "Failed! try again",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });

    }

    private void updateUI(FirebaseUser user) {
        if (user != null) { //estos valores se podrían usar en el programa
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            //Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
        }
    }
    private void saveLoginSharedPreferences(String username, String password){
        SharedPreferences sharedPref = getSharedPreferences("MyPref",0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username",username);
        editor.putString("password", password);
        editor.commit();
    }
    public void cambiaCat(View view) {
        Locale catalan = new Locale("ca", "CA");
        Locale.setDefault(catalan);
        Configuration config = new Configuration();
        config.locale = catalan;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        Intent cat = new Intent(getApplicationContext(), MainActivity.class);
        cat.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(cat);
    }
    public void cambiaEng(View view) {
        Locale ingles = new Locale("en", "EN");
        Locale.setDefault(ingles);
        Configuration config = new Configuration();
        config.locale = ingles;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        Intent eng = new Intent(getApplicationContext(), MainActivity.class);
        eng.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(eng);
    }
    public void cambiaEsp(View view) {
        Locale espanol = new Locale("es", "ES");
        Locale.setDefault(espanol);
        Configuration config = new Configuration();
        config.locale = espanol;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        Intent esp = new Intent(getApplicationContext(), MainActivity.class);
        esp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(esp);
    }
    private void añadirDatos() {
        int radioButtonId = genere.getCheckedRadioButtonId();
        View radioButton = genere.findViewById(radioButtonId);
        int indice = genere.indexOfChild(radioButton);
        RadioButton rb = (RadioButton)  genere.getChildAt(indice);
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("usuarios/" + mAuth.getUid());
        Map<String, Object> datosUsuario = new HashMap<>();
        datosUsuario.put("nombre",nom.getText().toString());
        datosUsuario.put("cognom",cognom.getText().toString());
        datosUsuario.put("genere",rb.getText().toString());
        myRef.child("datos").push().setValue(datosUsuario);

        Toast.makeText(getApplicationContext(), "Successful sign up!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }
}
