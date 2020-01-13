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
    String hi;


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
                    if(checkLoginSharedPreferences(username, password)){
                        Intent i = new Intent(getApplicationContext(), NavigationActivity.class);
                        i.putExtra("usuarioactual", username);
                        startActivity(i);
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

    private boolean checkLoginSharedPreferences(String username, String password){
        SharedPreferences sharedPref = getSharedPreferences("MyPref",0);
      String user = sharedPref.getString("username",null);
      String pass = sharedPref.getString("password", null);
      if (username.equals(user) && password.equals(pass)){
          return true;
      }
      else return false;
    }




}
