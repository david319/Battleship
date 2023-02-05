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
