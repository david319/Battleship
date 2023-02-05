package Projecto_1;

import java.util.Scanner;

public class Class_Battleship {

    // Scanner
    private static final Scanner leer = new Scanner(System.in).useDelimiter("\n");

    // Variables
    private static int dificultad = 1;
    private static int modoJ = 1;
    private static boolean gameOver = false;
    private static boolean piezaValida1 = false;
    private static boolean piezaValida2 = false;
    private static final String[][] tablero = new String[8][8];

    // Inicializar tablero
    public static void inicializarTablero() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = "[  ]";
            }
        }
    }

}
