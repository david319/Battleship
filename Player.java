package Projecto_1;

public class Player {

    // Atributos
    private String user;
    private String pass;
    private int score;

    // Constructor
    public Player(String user, String pass) {
        this.user = user;
        this.pass = pass;
        this.score = 0;
    }

    // Getters
    public String getNombre() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public int getPuntos() {
        return score;
    }

    // Setters
    public void setNombre(String user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setPuntos(int score) {
        this.score = score;
    }
}
