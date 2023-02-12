package Projecto_1;

public class Boat {

    // Atributos
    private int health;
    private String name;

    // Constructor
    public Boat(int health, String name) {
        this.health = health;
        this.name = name;
    }

    // Getters
    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    // Setters
    public void setHealth(int health) {
        this.health = health;
    }

    public void colocarBarco(int x, int y, Boat[][] tablero) {
        tablero[x][y] = this;
    }

    public int tipoBarco() {
        if (this instanceof PA) {
            return 1;
        } else if (this instanceof AZ) {
            return 2;
        } else if (this instanceof SM) {
            return 3;
        } else if (this instanceof DT) {
            return 4;
        } else {
            return 0;
        }
    }

    public static boolean obtenerBarco(int x, int y, Boat[][] tablero) {
        if (tablero[x][y] != null) {
            tablero[x][y].setHealth(tablero[x][y].getHealth() - 1);
            if (tablero[x][y].getHealth() == 0) {
                tablero[x][y] = null;
                return true;
            }
            return true;
        } else {
            System.out.println("No hay barco en esa posición");
            return false;
        }
    }


    public static class PA extends Boat {

        // Constructor
        public PA() {
            super(5, "PA");
        }
    }

    public static class AZ extends Boat {

        // Constructor
        public AZ() {
            super(4, "AZ");
        }
    }

    public static class SM extends Boat {

        // Constructor
        public SM() {
            super(3, "SM");
        }
    }

    public static class DT extends Boat {

        // Constructor
        public DT() {
            super(3, "DT");
        }
    }
}
