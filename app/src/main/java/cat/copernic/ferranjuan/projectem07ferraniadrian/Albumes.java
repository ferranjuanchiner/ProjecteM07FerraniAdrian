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
    public String nom;
    public String nomCanço;
    int img;
    int canço;

    /*public Albumes(String title, int imageResource, Cancion cancion) {
        this.title = title;
        this.imageResource = imageResource;
        this.cancion = cancion;
    }*/

    public Albumes() {
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference( "albums/"+mAuth.getUid());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //dataSnapshot viene de leer "usuarios/" + user.getUid() + "/comidas"
                for (DataSnapshot datos : dataSnapshot.getChildren()) {
                    nom = datos.child("nom").getValue(String.class);
                    nomCanço = datos.child("nomCanço").getValue(String.class);
                    img = datos.child("img").getValue(Integer.class);
                    canço = datos.child("canço").getValue(Integer.class);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Failed to read value
                Log.w("perfil", "Failed to read value.", databaseError.toException());

            }
        });
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNomCanço(String nomCanço) {
        this.nomCanço = nomCanço;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setCanço(int canço) {
        this.canço = canço;
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
