package cat.copernic.ferranjuan.projectem07ferraniadrian;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String TAG = "login" ;


    Button btnLogin;
    Button btnRegistre;
    EditText etUser;
    EditText etPasssword;
    public static final int TEXT_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.btnlogin);
        btnRegistre = findViewById(R.id.btnregister);
        etUser = findViewById(R.id.etUsername);
        etPasssword = findViewById(R.id.etPassword);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etUser.toString().isEmpty() || etPasssword.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,
                            "Missing fields",
                            Toast.LENGTH_SHORT).show();
                } else {
                    final String username = etUser.getText().toString();
                    final String password = etPasssword.getText().toString();
                    if(username.equals("admin")&&password.equals("admin")){
                        saveLoginSharedPreferences(username);
                        Toast.makeText(MainActivity.this,"Login correcto",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this,"Login Incorrecto",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        btnRegistre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);
            }
        });

    }

    private void saveLoginSharedPreferences(String username){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username",username);
        editor.apply();
    }




}
