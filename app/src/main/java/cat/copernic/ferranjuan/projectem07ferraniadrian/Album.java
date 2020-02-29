package cat.copernic.ferranjuan.projectem07ferraniadrian;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Album {
    public String nom;
    public String nomCanço;
    int img;
    int canço;


    DatabaseReference myRef;
    private FirebaseAuth mAuth;

    //public String title;
    //private int imageResource;
    //public Cancion cancion;


    /*public Album(String title, int imageResource, Cancion cancion) {
        this.title = title;
        this.imageResource = imageResource;
        this.cancion = cancion;
    }*/

    public Album(String nom, int img, String nomCanço,int canço) {
        this.nom = nom;
        this.img = img;
        this.nomCanço = nomCanço;
        this.canço = canço;
    }
    public String getNom(){
        return nom;
    }
    public String getNomCanço(){
        return nomCanço;
    }
    public int getImg(){
        return img;
    }
    public int getCanço(){
        return canço;
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






    /*public String getTitle() {
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
        }*/

}

