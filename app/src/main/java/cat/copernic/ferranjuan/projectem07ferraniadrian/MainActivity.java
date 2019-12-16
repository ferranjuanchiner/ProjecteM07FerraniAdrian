package cat.copernic.ferranjuan.projectem07ferraniadrian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String TAG = "login";


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
                    conectaUsuario(etUser.getText().toString(), etPasssword.getText().toString());
                }
            }
        });

    }



    public void launchRegister(View view) {
        Intent intent = new Intent(this, Register.class);
        intent.putExtra("VAR INT", 1);
        startActivityForResult(intent, TEXT_REQUEST);
    }
    private void conectaUsuario(String email, String password) {

    }

}
