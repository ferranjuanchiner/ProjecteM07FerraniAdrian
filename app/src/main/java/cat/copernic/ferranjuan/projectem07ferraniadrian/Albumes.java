package cat.copernic.ferranjuan.projectem07ferraniadrian;

public class Albumes {
    private String title;
    private final int imageResource;
    public Albumes(String title, int imageResource) {
        this.title = title;
        this.imageResource = imageResource;
    }
    public String getTitle() {
        return title;
    }

    public int getImageResource() {
        return imageResource;
    }

    public class Cancion{
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
