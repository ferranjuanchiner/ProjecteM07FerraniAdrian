package cat.copernic.ferranjuan.projectem07ferraniadrian;

public class Albumes {
    private String title;
    private final int imageResource;
    public Cancion cancion;
    public Albumes(String title, int imageResource, Cancion cancion) {
        this.title = title;
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
