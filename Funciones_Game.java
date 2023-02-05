package Projecto_1;

import java.util.ArrayList;
import java.util.Scanner;

public class Funciones_Game {

    // Scanner
    private static final Scanner leer = new Scanner(System.in).useDelimiter("\n");
    // Atributos
    private static boolean verificador = false;
    private static final ArrayList<Player> Jugadores = new ArrayList<>();
    private static final String[] reportes = new String[10];
    private static final String[] nameJugando = new String[2];

    // Funciones
    public static void Register_Player(String nombre) {
        if (!verificador) {
            System.out.println("Ingrese una contraseña:");
            String Password = leer.next();
            Player new_player = new Player(nombre, Password);
            Jugadores.add(new_player);
        }
    }

    public static void add_puntos(String nombre, int puntos) {
        for (Player player : Jugadores) {
            if (player.getNombre().equals(nombre)) {
                player.setPuntos(player.getPuntos() + puntos);
            }
        }
    }

    public static void Delete_Player(String nombre, String Password) {
        for (Player player : Jugadores) {
            if (player.getNombre().equals(nombre) && player.getPass().equals(Password)) {
                Jugadores.remove(player);
                System.out.println("El jugador ha sido eliminado");
                break;
            } else {
                System.out.println("El usuario o contraseña es incorrecto");
            }
        }
    }

    public static boolean verify_Player(String nombre, String Password) {
        for (Player player : Jugadores) {
            if (player.getNombre().equals(nombre) && player.getPass().equals(Password)) {
                verificador = true;
                nameJugando[0] = nombre;
                return true;
            } else {
                System.out.println("El jugador no existe");
            }
        }
        return verificador;
    }

    public static void buscar_Player(String nombre) {
        for (Player player : Jugadores) {
            if (player.getNombre().equals(nombre)) {
                System.out.println("El jugador ya existe");
                verificador = true;
                return;
            }
        }
    }

    public static String obtenerPlayer1() {
        return nameJugando[0];
    }

    public static void setPlayer2() {
        System.out.println("Ingrese el nombre del jugador 2:");
        String nombre = leer.next();
        nameJugando[1] = nombre;
    }

    public static String obtenerPlayer2() {
        return nameJugando[1];
    }

    public static void Ranking() {
        for (Player player : Jugadores) {
            System.out.printf("""
                    Nombre: %s
                    Puntos: %d
                    """, player.getNombre(), player.getPuntos());
        }
    }

    public static void Perfil() {
        for (Player player : Jugadores) {
            System.out.printf("""
                    Nombre: %s
                    contraseña: %s
                    Puntos: %d
                    """, player.getNombre(), player.getPass(), player.getPuntos());
        }
    }

    public static void cambiarC(String nombre, String Password) {
        for (Player player : Jugadores) {
            if (player.getNombre().equals(nombre) && player.getPass().equals(Password)) {
                System.out.println("Ingrese la nueva contraseña:");
                String newPassword = leer.next();
                player.setPass(newPassword);
            }
        }
    }

    public static void reportes() {
        for (int i = 0; i < 10; i++) {
            if (reportes[i] != null) {
                System.out.println(reportes[i]);
            }
        }
    }

    public static void add_Reporte(String nombre, String reporte) {
        for (int i = 9; i < 10 && i >= 0; i--) {
            if (reportes[i] == null) {
                reportes[i] = nombre + ": " + reporte;
                break;
            }
        }
    }
}
