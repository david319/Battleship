package Projecto_1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Scanner
        Scanner leer = new Scanner(System.in).useDelimiter("\n");

        // Variables
        int opcion;
        String user, pass;

        // Menu
        System.out.println("Bienvenido a BattleShip");
        do {
            System.out.println("""
                    Menu de opciones
                    1. Login
                    2. Crear Player
                    3. Salir
                    Selecciona una opcion:""");
            opcion = leer.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese su usuario:");
                    user = leer.next();
                    System.out.println("Ingrese su contraseña:");
                    pass = leer.next();
                    break;
                case 2:
                    System.out.println("Ingrese su usuario:");
                    user = leer.next();
                    System.out.println("Ingrese su contraseña:");
                    pass = leer.next();
                    break;
                case 3:
                    System.out.println("Gracias por jugar");
                    break;
            }
        } while (opcion != 3);
    }
}
