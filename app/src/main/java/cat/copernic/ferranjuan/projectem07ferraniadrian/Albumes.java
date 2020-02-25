package cat.copernic.ferranjuan.projectem07ferraniadrian;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Albumes {
    DatabaseReference myRef;
    private FirebaseAuth mAuth;

    public String title;
    private int imageResource;
    public Cancion cancion;

    public Albumes(String title, final int imageResource, Cancion cancion) {
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference( "albums/"+mAuth.getUid());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //dataSnapshot viene de leer "usuarios/" + user.getUid() + "/comidas"
                for (DataSnapshot datos : dataSnapshot.getChildren()) {
                    String nom = datos.child("nom").getValue(String.class);
                    String nomCanço = datos.child("nomCanço").getValue(String.class);
                    int img = datos.child("img").getValue(Integer.class);
                    int canço = datos.child("canço").getValue(Integer.class);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Failed to read value
                Log.w("perfil", "Failed to read value.", databaseError.toException());

            }
        });
        this.title = title ;
        this.imageResource = imageResource;
        this.cancion = cancion;
    }
    public String getTitle() {
        return title;
    }

    public int getImageResource() {
        return imageResource;
    }
    public  Cancion getCancion(){return cancion;}

    public static class Cancion{
        String nom;
        int file;
        public Cancion(String nom, int file){
            this.nom = nom;
            this.file = file;
        }
        public String getNom(){
            return nom;
        }
        public int getFile(){
            return file;
        }
    }
}
