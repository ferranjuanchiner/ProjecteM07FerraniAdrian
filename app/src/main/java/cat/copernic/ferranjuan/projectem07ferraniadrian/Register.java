package cat.copernic.ferranjuan.projectem07ferraniadrian;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
EditText usuario;
EditText pass;
    Button btnRegistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        usuario = findViewById(R.id.etUser);
        pass = findViewById(R.id.etpassword);
        btnRegistrarse = findViewById(R.id.btnRegistro);

    btnRegistrarse.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(usuario.toString().isEmpty() || pass.getText().toString().isEmpty()){
                Toast.makeText(Register.this,
                        "Missing username and password",
                        Toast.LENGTH_SHORT).show();
            } else {
                final String username = usuario.getText().toString();
                final String password = pass.getText().toString();
                    saveLoginSharedPreferences(username, password);
                    Toast.makeText(Register.this,"Saved user data",Toast.LENGTH_SHORT).show();
            }

        }
    });}
    private void saveLoginSharedPreferences(String username, String password){
        SharedPreferences sharedPref = getSharedPreferences("MyPref",0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username",username);
        editor.putString("password", password);
        editor.commit();
    }

}
