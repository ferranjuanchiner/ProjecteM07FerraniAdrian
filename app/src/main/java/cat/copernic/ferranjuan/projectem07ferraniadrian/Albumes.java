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
}
