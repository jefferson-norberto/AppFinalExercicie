package cin.ufpe.finalapp;

public class Application {
    private int id;
    private String name;
    private boolean enable;

    public Application(int id, String name, boolean enable) {
        this.id = id;
        this.name = name;
        this.enable = enable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
