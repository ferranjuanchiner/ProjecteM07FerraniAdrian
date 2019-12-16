package cat.copernic.ferranjuan.projectem07ferraniadrian;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {
EditText usuario;
EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        usuario = findViewById(R.id.etUser);
        pass = findViewById(R.id.etPassword);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void guardarPreferencias(){
        SharedPreferences preferences = getSharedPreferences
                ("credenciales", Context.MODE_PRIVATE);
        String user = usuario.getText().toString();
        String contrasena = pass.getText().toString();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user",user);
        editor.putString("password",contrasena);
        editor.commit();
    }
}
