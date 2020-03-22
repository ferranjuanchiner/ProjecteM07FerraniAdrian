package cat.copernic.ferranjuan.projectem07ferraniadrian;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//prova solucions commit
public class MainActivity extends AppCompatActivity {

    String TAG = "login" ;
    private FirebaseAuth mAuth;


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
        mAuth = FirebaseAuth.getInstance();
        etPasssword = findViewById(R.id.etPassword);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if (etUser.toString().isEmpty() || etPasssword.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            R.string.errorregistre,
                            Toast.LENGTH_SHORT).show();
                } else {
                    final String username = etUser.getText().toString();
                    final String password = etPasssword.getText().toString();
                    if (checkLoginSharedPreferences(username, password)) {
                        Intent i = new Intent(getApplicationContext(), NavigationActivity.class);
                        i.putExtra("usuarioactual", username);
                        startActivity(i);
                    } else {
                        Toast.makeText(MainActivity.this, R.string.errorlogin, Toast.LENGTH_SHORT).show();
                    }
                }

           */
                if(etUser.toString().isEmpty() || etPasssword.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,
                            "Missing fields",
                            Toast.LENGTH_SHORT).show();
                } else {
                    conectaUsuario(etUser.getText().toString(), etPasssword.getText().toString());
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

        // ATTENTION: This was auto-generated to handle app links.

        FirebaseAuth.AuthStateListener mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                }
            }
        };
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
    private void conectaUsuario(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            Log.d(TAG, "signInWithEmail:success");
                            Toast.makeText(MainActivity.this, R.string.loginCorrecte,
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());


                            Toast.makeText(MainActivity.this, "Login failed.",
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


}
