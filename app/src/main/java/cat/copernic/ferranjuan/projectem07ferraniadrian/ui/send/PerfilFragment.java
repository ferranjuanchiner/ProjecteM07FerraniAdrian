package cat.copernic.ferranjuan.projectem07ferraniadrian.ui.send;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cat.copernic.ferranjuan.projectem07ferraniadrian.R;

public class PerfilFragment extends Fragment {

    private SendViewModel sendViewModel;
    Switch sw;
    String espanol;
    DatabaseReference myRef;
    private FirebaseAuth mAuth;
    FirebaseUser user;
    public EditText nombd,cognombd,mailbd,generebd,usuaribd;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);
        //final TextView textView = root.findViewById(R.id.text_send);
        nombd = root.findViewById(R.id.tvNomBd);
        cognombd = root.findViewById(R.id.tvCognomBD);
        usuaribd = root.findViewById(R.id.tvUsuariBD);
        mailbd = root.findViewById(R.id.tvEmailBD);
        generebd = root.findViewById(R.id.tvGenereBD);
        sendViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
              //  textView.setText(s);
            }
        });
        espanol = root.getResources().getString(R.string.es);
        sw = root.findViewById(R.id.swColor);

        inicialitzaDades();
        return root;
    }

    private void inicialitzaDades() {
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference( "usuarios/");
        myRef.orderByChild("email").equalTo(user.getEmail()).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //dataSnapshot viene de leer "usuarios/" + user.getUid() + "/comidas"
                for (DataSnapshot datos : dataSnapshot.getChildren()) {
                    String cognom = datos.child("cognom").getValue(String.class);
                    String email = datos.child("email").getValue(String.class);
                    String genere = datos.child("genere").getValue(String.class);
                    String nombre = datos.child("nombre").getValue(String.class);
                    String usuari = datos.child("usuari").getValue(String.class);

                    nombd.setText(datos.child("nombre").getValue(String.class));
                    cognombd.setText(datos.child("cognom").getValue(String.class));
                    usuaribd.setText(datos.child("usuari").getValue(String.class));
                    mailbd.setText(datos.child("email").getValue(String.class));
                    generebd.setText(datos.child("genere").getValue(String.class));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Failed to read value
                Log.w("perfil", "Failed to read value.", databaseError.toException());
                Toast.makeText(getActivity(), "Error al leer", Toast.LENGTH_SHORT);

            }
        });
    }

    public void onClick(View view) {
        final int uiModeNight;
        if (view.getId()== R.id.swColor){
            if (sw.isChecked()){
                uiModeNight = Configuration.UI_MODE_NIGHT_YES;
            }else {
                uiModeNight= Configuration.UI_MODE_NIGHT_NO;
            }
        }
    }
}