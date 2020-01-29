package cat.copernic.ferranjuan.projectem07ferraniadrian;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
EditText usuario;
EditText pass;
    Button btnRegistrarse;
    Button btnCancelar;
CheckBox cBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        usuario = findViewById(R.id.etUser);
        pass = findViewById(R.id.etpassword);
        btnRegistrarse = findViewById(R.id.btnRegistro);
        btnCancelar = findViewById(R.id.btnCancelar);
        cBox = findViewById(R.id.cbTerminos);

    btnRegistrarse.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(usuario.toString().isEmpty() || pass.getText().toString().isEmpty()){
                Toast.makeText(Register.this,
                        R.string.errorregistre,
                        Toast.LENGTH_SHORT).show();
            }else if(cBox.isChecked()==false){
                Toast.makeText(Register.this,R.string.errortermes,Toast.LENGTH_SHORT).show();
            }
            else {
                final String username = usuario.getText().toString();
                final String password = pass.getText().toString();
                    saveLoginSharedPreferences(username, password);
                    Toast.makeText(Register.this,R.string.mssgsaveduser,Toast.LENGTH_SHORT).show();
                    finish();
            }
        }
    });
    btnCancelar.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View View){
            finish();
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
