package Projecto_1;

import java.util.Objects;
import java.util.Scanner;

import static Projecto_1.Boat.obtenerBarco;
import static Projecto_1.Class_Player.*;

public class Class_Battleship {

    // Scanner
    private static final Scanner leer = new Scanner(System.in).useDelimiter("\n");

    // Variables
    private static int dificultad = 1;
    private static int modoJ = 2;
    private static boolean gameOver = false;
    private static boolean piezaValida1 = false;
    private static boolean player1 = true;
    private static boolean piezaValida2 = false;
    private static boolean player2 = false;
    private static boolean turn1 = true;
    private static boolean turn2 = false;
    private static int cantidadBarcos;
    private static int cantidadBarcos1 = 0;
    private static int cantidadBarcos2 = 0;
    private static final String[][] tablero = new String[8][8];
    private static final Boat[][] tableroPlayer1 = new Boat[8][8];
    private static final Boat[][] tableroPlayer2 = new Boat[8][8];

    // Inicializar tablero
    public static void inicializarTablero() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = "[ X ]";
            }
        }

        for (int i = 0; i < tableroPlayer1.length; i++) {
            for (int j = 0; j < tableroPlayer1[i].length; j++) {
                tableroPlayer1[i][j] = null;
            }
        }

        for (int i = 0; i < tableroPlayer2.length; i++) {
            for (int j = 0; j < tableroPlayer2[i].length; j++) {
                tableroPlayer2[i][j] = null;
            }
        }
    }

    // Imprimir tablero
    public static void imprimirTablero() {
        System.out.println("  1  2  3  4  5  6  7  8");
        for (int i = 0; i < tablero.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j]);
            }
            System.out.println(" ");
        }
    }

    // Imprimir tablero player 1
    public static void imprimirTableroPlayer1() {
        System.out.println("  1  2  3  4  5  6  7  8");
        for (int i = 0; i < tableroPlayer1.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < tableroPlayer1[i].length; j++) {
                if (tableroPlayer1[i][j] != null) {
                    System.out.print("[ " + tableroPlayer1[i][j].getName() + " ]");
                } else {
                    System.out.print("[ X ]");
                }
            }
            System.out.println(" ");
        }
    }

    // Imprimir tablero player 2
    public static void imprimirTableroPlayer2() {
        System.out.println("  1  2  3  4  5  6  7  8");
        for (int i = 0; i < tableroPlayer2.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < tableroPlayer2[i].length; j++) {
                if (tableroPlayer2[i][j] != null) {
                    System.out.print("[ " + tableroPlayer2[i][j].getName() + " ]");
                } else {
                    System.out.print("[ X ]");
                }
            }
            System.out.println(" ");
        }
    }

    // Imprimir fallo en el disparo
    public static void imprimirFallo(int x, int y) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (i == x && j == y) {
                    tablero[i][j] = "[ F ]";
                } else {
                    tablero[i][j] = tablero[i][j];
                }
            }
        }
    }

    // Dificultad
    public static void Dificultad(int dificultad) {
        Class_Battleship.dificultad = dificultad;
    }

    public static String getDificultad() {
        if (dificultad == 1) {
            return "Facil";
        } else if (dificultad == 2) {
            return "Normal";
        } else if (dificultad == 3) {
            return "Expert";
        } else {
            return "Genius";
        }
    }

    // Modo de juego
    public static void modoJ(int modoJ) {
        Class_Battleship.modoJ = modoJ;
    }

    // Barcos no se permiten dos barcos en la misma posicion y no se permiten barcos fuera del tablero
    public static void piezaValida(int x, int y) {
        if (x > 0 && x < 9 && y > 0 && y < 9) {
            if (player1) {
                if (Objects.equals(tableroPlayer1[x - 1][y - 1], null)) {
                    piezaValida1 = true;
                } else {
                    System.out.println("No se puede colocar el barco en esa posicion");
                }
            } else if (!player2) {
                if (Objects.equals(tableroPlayer2[x - 1][y - 1], null)) {
                    piezaValida2 = true;
                } else {
                    System.out.println("No se puede colocar el barco en esa posicion");
                }
            } else {
                System.out.println("No se puede colocar el barco fuera del tablero");
            }
        }
    }

    // Cambiar turno
    public static void cambiarTurno() {
        if (turn1) {
            turn1 = false;
            turn2 = true;
        } else if (turn2) {
            turn1 = true;
            turn2 = false;
        }
    }

    // Si un jugador da en un barco del otro jugador, se cambian de posicion de forma aleatoria de 0 a 7 de todos los barcos
    public static void cambiarPosicionBarcos() {
        int x, y;
        if (turn1) {
            for (int i = 0; i < tableroPlayer2.length; i++) {
                for (int j = 0; j < tableroPlayer2[i].length; j++) {
                    if (tableroPlayer2[i][j] != null) {
                        x = (int) (Math.random() * 7);
                        y = (int) (Math.random() * 7);
                        tableroPlayer2[i][j] = tableroPlayer1[x][y];
                        tableroPlayer1[x][y] = null;
                    }
                }
            }
        } else if (turn2) {
            for (int i = 0; i < tableroPlayer1.length; i++) {
                for (int j = 0; j < tableroPlayer1[i].length; j++) {
                    if (tableroPlayer1[i][j] != null) {
                        x = (int) (Math.random() * 7);
                        y = (int) (Math.random() * 7);
                        tableroPlayer1[i][j] = tableroPlayer2[x][y];
                        tableroPlayer2[x][y] = null;
                    }
                }
            }
        }
    }


    // Empieza el juego
    public static void playGame() {
        int x, y;

        inicializarTablero();

        // Barcos player 1
        Boat.PA portaAviones = new Boat.PA();
        Boat.AZ acorazado = new Boat.AZ();
        Boat.SM submarino = new Boat.SM();
        Boat.DT destructor = new Boat.DT();

        int cantPA;
        int cantAZ;
        int cantSM;
        int cantDT;

        // Barcos player 2
        int cantPA2;
        int cantAZ2;
        int cantSM2;
        int cantDT2;

        if (dificultad == 1) {
            cantPA = 1;
            cantAZ = 1;
            cantSM = 1;
            cantDT = 2;

            cantPA2 = 1;
            cantAZ2 = 1;
            cantSM2 = 1;
            cantDT2 = 2;
        } else {
            cantPA = 1;
            cantAZ = 1;
            cantSM = 1;
            cantDT = 1;

            cantPA2 = 1;
            cantAZ2 = 1;
            cantSM2 = 1;
            cantDT2 = 1;
        }


        // Modo de juego
        if (modoJ == 1) {
            // Modo de juego modo arcade
            inicializarTablero();
            while (!gameOver) {
                // Imprimir tablero
                imprimirTablero();

                // Colocar barcos

                if (dificultad == 1) {
                    cantidadBarcos = 5;
                } else if (dificultad == 2) {
                    cantidadBarcos = 4;
                } else if (dificultad == 3) {
                    cantidadBarcos = 2;
                } else {
                    cantidadBarcos = 1;
                }

                do {
                    System.out.println("Turno del jugador 1");
                    System.out.println("Quedan " + cantidadBarcos + " barcos por colocar");
                    System.out.println("Que tipo de barco desea colocar?");
                    System.out.println("1. Portaaviones");
                    System.out.println("2. Acorazado");
                    System.out.println("3. Submarino");
                    System.out.println("4. Destructor");
                    System.out.println("Seleccione una opcion");
                    int tipoBarco = leer.nextInt();
                    System.out.println("Ingrese la coordenada X");
                    x = leer.nextInt();
                    System.out.println("Ingrese la coordenada Y");
                    y = leer.nextInt();

                    if (tipoBarco == 1) {
                        for (int i = 0; tableroPlayer1.length > i; i++) {
                            for (int j = 0; tableroPlayer1[i].length > j; j++) {
                                if (tableroPlayer1[i][j] == tableroPlayer1[x][y] && tableroPlayer1[i][j] != null) {
                                    System.out.println("Ya hay un barco en esa posicion");
                                } else {
                                    if (cantPA == 0 && i == tableroPlayer1.length - 1 && j == tableroPlayer1[i].length - 1) {
                                        System.out.println("Ya hay un portaaviones");
                                    } else {
                                        tableroPlayer1[i][j] = new Boat.PA();
                                        cantidadBarcos--;
                                        cantPA--;
                                    }
                                }
                            }
                        }
                    } else if (tipoBarco == 2) {
                        for (int i = 0; tableroPlayer1.length > i; i++) {
                            for (int j = 0; tableroPlayer1[i].length > j; j++) {
                                if (tableroPlayer1[i][j] == tableroPlayer1[x][y] && tableroPlayer1[i][j] != null) {
                                    System.out.println("Ya hay un barco en esa posicion");
                                } else {
                                    if (cantAZ == 0 && i == tableroPlayer1.length - 1 && j == tableroPlayer1[i].length - 1) {
                                        System.out.println("Ya hay un acorazado");
                                    } else {
                                        tableroPlayer1[i][j] = new Boat.AZ();
                                        cantidadBarcos--;
                                        cantAZ--;
                                    }
                                }
                            }
                        }
                    } else if (tipoBarco == 3) {
                        for (int i = 0; tableroPlayer1.length > i; i++) {
                            for (int j = 0; tableroPlayer1[i].length > j; j++) {
                                if (tableroPlayer1[i][j] == tableroPlayer1[x][y] && tableroPlayer1[i][j] != null) {
                                    System.out.println("Ya hay un barco en esa posicion");
                                } else {
                                    if (cantSM == 0 && i == tableroPlayer1.length - 1 && j == tableroPlayer1[i].length - 1) {
                                        System.out.println("Ya hay un submarino");
                                    } else {
                                        tableroPlayer1[i][j] = new Boat.SM();
                                        cantidadBarcos--;
                                        cantSM--;
                                    }
                                }
                            }
                        }
                    } else if (tipoBarco == 4) {
                        for (int i = 0; tableroPlayer1.length > i; i++) {
                            for (int j = 0; tableroPlayer1[i].length > j; j++) {
                                if (tableroPlayer1[i][j] == tableroPlayer1[x][y] && tableroPlayer1[i][j] != null) {
                                    System.out.println("Ya hay un barco en esa posicion");
                                } else {
                                    if (cantDT == 0 && i == tableroPlayer1.length - 1 && j == tableroPlayer1[i].length - 1) {
                                        System.out.println("Ya hay un destructor");
                                    } else {
                                        tableroPlayer1[i][j] = new Boat.DT();
                                        cantidadBarcos--;
                                        cantDT--;
                                    }
                                }
                            }
                        }
                    } else {
                        System.out.println("Opcion no valida");
                    }
                    if (cantidadBarcos == 0) {
                        System.out.println("Ya no hay mas barcos");
                        break;
                    } else {
                        System.out.println("Quedan " + cantidadBarcos + " barcos por colocar");
                    }
                    // cuando cantidadBarcos sea 0, se sale del bucle
                } while (cantidadBarcos != 0);

                if (dificultad == 1) {
                    cantidadBarcos = 5;
                } else if (dificultad == 2) {
                    cantidadBarcos = 4;
                } else if (dificultad == 3) {
                    cantidadBarcos = 2;
                } else {
                    cantidadBarcos = 1;
                }

                do {
                    System.out.println("Jugador 2");
                    if (cantidadBarcos == 0) {
                        System.out.println("Ya no hay mas barcos");
                        break;
                    } else {
                        System.out.println("Quedan " + cantidadBarcos + " barcos por colocar");
                    }
                    System.out.println("Que tipo de barco desea colocar?");
                    System.out.println("1. Portaaviones");
                    System.out.println("2. Acorazado");
                    System.out.println("3. Submarino");
                    System.out.println("4. Destructor");
                    System.out.println("Seleccione una opcion");
                    int tipoBarco = leer.nextInt();
                    System.out.println("Ingrese la coordenada X");
                    x = leer.nextInt();
                    System.out.println("Ingrese la coordenada Y");
                    y = leer.nextInt();

                    if (tipoBarco == 1) {
                        for (int i = 0; tableroPlayer2.length > i; i++) {
                            for (int j = 0; tableroPlayer2[i].length > j; j++) {
                                if (tableroPlayer2[i][j] == tableroPlayer2[x][y] && tableroPlayer2[i][j] != null) {
                                    System.out.println("Ya hay un barco en esa posicion");
                                } else {
                                    if (cantPA2 == 0 && i == tableroPlayer2.length - 1 && j == tableroPlayer2[i].length - 1) {
                                        System.out.println("Ya hay un portaaviones");
                                    } else {
                                        tableroPlayer2[i][j] = new Boat.PA();
                                        cantidadBarcos--;
                                        cantPA2--;
                                    }
                                }
                            }
                        }
                    } else if (tipoBarco == 2) {
                        for (int i = 0; tableroPlayer2.length > i; i++) {
                            for (int j = 0; tableroPlayer2[i].length > j; j++) {
                                if (tableroPlayer2[i][j] == tableroPlayer2[x][y] && tableroPlayer2[i][j] != null) {
                                    System.out.println("Ya hay un barco en esa posicion");
                                } else {
                                    if (cantAZ2 == 0 && i == tableroPlayer2.length - 1 && j == tableroPlayer2[i].length - 1) {
                                        System.out.println("Ya hay un acorazado");
                                    } else {
                                        tableroPlayer2[i][j] = new Boat.AZ();
                                        cantidadBarcos--;
                                        cantAZ2--;
                                    }
                                }
                            }
                        }
                    } else if (tipoBarco == 3) {
                        for (int i = 0; tableroPlayer2.length > i; i++) {
                            for (int j = 0; tableroPlayer2[i].length > j; j++) {
                                if (tableroPlayer2[i][j] == tableroPlayer2[x][y] && tableroPlayer2[i][j] != null) {
                                    System.out.println("Ya hay un barco en esa posicion");
                                } else {
                                    if (cantSM2 == 0 && i == tableroPlayer2.length - 1 && j == tableroPlayer2[i].length - 1) {
                                        System.out.println("Ya hay un submarino");
                                    } else {
                                        tableroPlayer2[i][j] = new Boat.SM();
                                        cantidadBarcos--;
                                        cantSM2--;
                                    }
                                }
                            }
                        }
                    } else if (tipoBarco == 4) {
                        for (int i = 0; tableroPlayer2.length > i; i++) {
                            for (int j = 0; tableroPlayer2[i].length > j; j++) {
                                if (tableroPlayer2[i][j] == tableroPlayer2[x][y] && tableroPlayer2[i][j] != null) {
                                    System.out.println("Ya hay un barco en esa posicion");
                                } else {
                                    if (cantDT2 == 0 && i == tableroPlayer2.length - 1 && j == tableroPlayer2[i].length - 1) {
                                        System.out.println("Ya hay un destructor");
                                    } else {
                                        tableroPlayer2[i][j] = new Boat.DT();
                                        cantidadBarcos--;
                                        cantDT2--;
                                    }
                                }
                            }
                        }
                    } else {
                        System.out.println("Opcion no valida");
                    }

                } while (cantidadBarcos != 0);


                // Turno de los jugadores
                do {
                    if (player1) {

                        for (int i = 0; i < tablero.length; i++) {
                            for (int j = 0; j < tablero[i].length; j++) {
                                if (tablero[i][j].equals("[ F ]")) {
                                    tablero[i][j] = "[ X ]";
                                }
                            }
                        }

                        imprimirTablero();
                        System.out.println("Turno del jugador 1");
                        System.out.println("Ingrese la coordenada X");
                        x = leer.nextInt();
                        if (x == -1) {
                            System.out.println("Estas seguro que deseas salir del juego? (1)Si (2)No");
                            int salir = leer.nextInt();
                            if (salir == 1) {
                                System.out.println(obtenerPlayer1() + " Se ha retirado del juego" + " " + obtenerPlayer2() + " ha ganado");
                                add_Reporte(obtenerPlayer1(), " Se ha retirado del juego" + " " + obtenerPlayer2() + " ha ganado");
                                add_puntos(obtenerPlayer2(), 3);
                                gameOver = true;
                            } else {
                                System.out.println("Ingrese la coordenada X");
                                x = leer.nextInt();
                            }
                        }
                        System.out.println("Ingrese la coordenada Y");
                        y = leer.nextInt();
                        if (y == -1) {
                            System.out.println("Estas seguro que deseas salir del juego? (1)Si (2)No");
                            int salir = leer.nextInt();
                            if (salir == 1) {
                                System.out.println(obtenerPlayer1() + " Se ha retirado del juego" + " " + obtenerPlayer2() + " ha ganado");
                                add_Reporte(obtenerPlayer1(), " Se ha retirado del juego" + " " + obtenerPlayer2() + " ha ganado");
                                add_puntos(obtenerPlayer2(), 3);
                                gameOver = true;
                            } else {
                                System.out.println("Ingrese la coordenada Y");
                                y = leer.nextInt();
                            }
                        }
                        if (obtenerBarco(x, y, tableroPlayer2)) {
                            System.out.println("Has acertado");

                            for (int i = 0; i < 10; i++) {
                                for (int j = 0; j < 10; j++) {
                                    if (tableroPlayer2[i][j] != null) {
                                        if (tableroPlayer2[i][j].tipoBarco() == 1) {
                                            cambiarPosicionBarcos();
                                            if (tableroPlayer2[i][j].getHealth() == 0) {
                                                System.out.println("Has hundido un portaaviones");
                                            }
                                        } else if (tableroPlayer2[i][j].tipoBarco() == 2) {
                                            cambiarPosicionBarcos();
                                            if (tableroPlayer2[i][j].getHealth() == 0) {
                                                System.out.println("Has hundido un acorazado");
                                            }
                                        } else if (tableroPlayer2[i][j].tipoBarco() == 3) {
                                            cambiarPosicionBarcos();
                                            if (tableroPlayer2[i][j].getHealth() == 0) {
                                                System.out.println("Has hundido un submarino");
                                            }
                                        } else if (tableroPlayer2[i][j].tipoBarco() == 4) {
                                            cambiarPosicionBarcos();
                                            if (tableroPlayer2[i][j].getHealth() == 0) {
                                                System.out.println("Has hundido un destructor");
                                            }
                                        }
                                        cantidadBarcos2++;
                                    }
                                }
                            }
                            if (cantidadBarcos2 == 0) {
                                System.out.println("El jugador" + obtenerPlayer1() + "ha ganado");
                                add_Reporte(obtenerPlayer1(), "Ha ganado al hundir todos los barcos de " + obtenerPlayer2()
                                        + " en Modo " + getDificultad());
                                add_puntos(obtenerPlayer1(), 3);
                                gameOver = true;
                            } else {
                                System.out.println("El jugador 2 tiene " + cantidadBarcos2 + " barcos");
                            }
                        } else if (!obtenerBarco(x, y, tableroPlayer2)) {
                            System.out.println("Has fallado");
                            imprimirFallo(x, y);
                        }
                        cambiarTurno();
                    } else if (!player2) {

                        for (int i = 0; i < tablero.length; i++) {
                            for (int j = 0; j < tablero[i].length; j++) {
                                if (tablero[i][j].equals("[ F ]")) {
                                    tablero[i][j] = "[ X ]";
                                }
                            }
                        }

                        imprimirTablero();

                        System.out.println("Turno del jugador 2");
                        System.out.println("Ingrese la coordenada X");
                        x = leer.nextInt();
                        if (x == -1) {
                            System.out.println("Estas seguro que deseas salir del juego? (1)Si (2)No");
                            int salir = leer.nextInt();
                            if (salir == 1) {
                                System.out.println(obtenerPlayer2() + " Se ha retirado del juego" + " " + obtenerPlayer1() + " ha ganado");
                                add_Reporte(obtenerPlayer2(), " Se ha retirado del juego" + " " + obtenerPlayer1() + " ha ganado");
                                add_puntos(obtenerPlayer1(), 3);
                            } else {
                                System.out.println("Ingrese la coordenada X");
                                x = leer.nextInt();
                            }
                        }
                        System.out.println("Ingrese la coordenada Y");
                        y = leer.nextInt();
                        if (y == -1) {
                            System.out.println("Estas seguro que deseas salir del juego? (1)Si (2)No");
                            int salir = leer.nextInt();
                            if (salir == 1) {
                                System.out.println(obtenerPlayer2() + " Se ha retirado del juego" + " " + obtenerPlayer1() + " ha ganado");
                                add_Reporte(obtenerPlayer2(), " Se ha retirado del juego" + " " + obtenerPlayer1() + " ha ganado");
                                add_puntos(obtenerPlayer1(), 3);
                            } else {
                                System.out.println("Ingrese la coordenada Y");
                                y = leer.nextInt();
                            }
                        }
                        if (obtenerBarco(x, y, tableroPlayer1)) {
                            System.out.println("Has acertado");

                            for (int i = 0; i < 10; i++) {
                                for (int j = 0; j < 10; j++) {
                                    if (tableroPlayer1[i][j] != null) {
                                        if (tableroPlayer1[i][j].tipoBarco() == 1) {
                                            cambiarPosicionBarcos();
                                            if (tableroPlayer1[i][j].getHealth() == 0) {
                                                System.out.println("Has hundido un portaaviones");
                                            }
                                        } else if (tableroPlayer1[i][j].tipoBarco() == 2) {
                                            cambiarPosicionBarcos();
                                            if (tableroPlayer1[i][j].getHealth() == 0) {
                                                System.out.println("Has hundido un acorazado");
                                            }
                                        } else if (tableroPlayer1[i][j].tipoBarco() == 3) {
                                            cambiarPosicionBarcos();
                                            if (tableroPlayer1[i][j].getHealth() == 0) {
                                                System.out.println("Has hundido un submarino");
                                            }
                                        } else if (tableroPlayer1[i][j].tipoBarco() == 4) {
                                            cambiarPosicionBarcos();
                                            if (tableroPlayer1[i][j].getHealth() == 0) {
                                                System.out.println("Has hundido un destructor");
                                            }
                                        }
                                        cantidadBarcos1++;
                                    }
                                }
                            }
                            if (cantidadBarcos1 == 0) {
                                System.out.println("El jugador" + obtenerPlayer2() + "ha ganado");
                                add_Reporte(obtenerPlayer2(), "Ha ganado al hundir todos los barcos de " + obtenerPlayer1()
                                        + " en Modo " + getDificultad());
                                add_puntos(obtenerPlayer2(), 3);
                                gameOver = true;
                            } else {
                                System.out.println("El jugador 2 tiene " + cantidadBarcos1 + " barcos");
                            }

                        } else if (!obtenerBarco(x, y, tableroPlayer1)) {
                            System.out.println("Has fallado");
                        }
                        cambiarTurno();
                    }
                } while (!gameOver);
            }
        } else if (modoJ == 2) {
            // Lo mismo que el modo 1, pero este muestra los barcos en el tablero
            // del jugador 1
            while (!gameOver) {

                imprimirTablero();
                if (dificultad == 1) {
                    cantidadBarcos = 5;
                } else if (dificultad == 2) {
                    cantidadBarcos = 4;
                } else if (dificultad == 3) {
                    cantidadBarcos = 2;
                } else {
                    cantidadBarcos = 1;
                }

                do {
                    System.out.println("Jugador 1");
                    System.out.println("Quedan " + cantidadBarcos + " barcos por colocar");
                    System.out.println("Que tipo de barco desea colocar?");
                    System.out.println("1. Portaaviones");
                    System.out.println("2. Acorazado");
                    System.out.println("3. Submarino");
                    System.out.println("4. Destructor");
                    System.out.println("Seleccione una opcion");
                    int tipoBarco = leer.nextInt();
                    System.out.println("Ingrese la coordenada X");
                    x = leer.nextInt();
                    System.out.println("Ingrese la coordenada Y");
                    y = leer.nextInt();

                    if (tipoBarco == 1) {
                        for (int i = 0; tableroPlayer1.length > i; i++) {
                            for (int j = 0; tableroPlayer1[i].length > j; j++) {
                                if (tableroPlayer1[i][j] == tableroPlayer1[x][y] && tableroPlayer1[i][j] != null) {
                                    System.out.println("Ya hay un barco en esa posicion");
                                } else {
                                    if (cantPA == 0 && i == tableroPlayer1.length - 1 && j == tableroPlayer1[i].length - 1) {
                                        System.out.println("Ya hay un portaaviones");
                                    } else if (cantPA == 1 && i == tableroPlayer1.length - 1 && j == tableroPlayer1[i].length - 1) {
                                        tableroPlayer1[x][y] = new Boat.PA();
                                        cantidadBarcos--;
                                        cantPA--;
                                    }
                                }
                            }
                        }
                    } else if (tipoBarco == 2) {
                        for (int i = 0; tableroPlayer1.length > i; i++) {
                            for (int j = 0; tableroPlayer1[i].length > j; j++) {
                                if (tableroPlayer1[i][j] == tableroPlayer1[x][y] && tableroPlayer1[i][j] != null) {
                                    System.out.println("Ya hay un barco en esa posicion");
                                } else {
                                    if (cantAZ == 0 && i == tableroPlayer1.length - 1 && j == tableroPlayer1[i].length - 1) {
                                        System.out.println("Ya hay un acorazado");
                                    } else if (cantAZ == 1 && i == tableroPlayer1.length - 1 && j == tableroPlayer1[i].length - 1) {
                                        tableroPlayer1[x][y] = new Boat.AZ();
                                        cantidadBarcos--;
                                        cantAZ--;
                                    }
                                }
                            }
                        }
                    } else if (tipoBarco == 3) {
                        for (int i = 0; tableroPlayer1.length > i; i++) {
                            for (int j = 0; tableroPlayer1[i].length > j; j++) {
                                if (tableroPlayer1[i][j] == tableroPlayer1[x][y] && tableroPlayer1[i][j] != null) {
                                    System.out.println("Ya hay un barco en esa posicion");
                                } else {
                                    if (cantSM == 0 && i == tableroPlayer1.length - 1 && j == tableroPlayer1[i].length - 1) {
                                        System.out.println("Ya hay un submarino");
                                    } else if (cantSM == 1 && i == tableroPlayer1.length - 1 && j == tableroPlayer1[i].length - 1) {
                                        tableroPlayer1[x][y] = new Boat.SM();
                                        cantidadBarcos--;
                                        cantSM--;
                                    }
                                }
                            }
                        }
                    } else if (tipoBarco == 4) {
                        for (int i = 0; tableroPlayer1.length > i; i++) {
                            for (int j = 0; tableroPlayer1[i].length > j; j++) {
                                if (tableroPlayer1[i][j] == tableroPlayer1[x][y] && tableroPlayer1[i][j] != null) {
                                    System.out.println("Ya hay un barco en esa posicion");
                                } else {
                                    if (cantDT == 0 && i == tableroPlayer1.length - 1 && j == tableroPlayer1[i].length - 1) {
                                        System.out.println("Ya hay un destructor");
                                    } else if (cantDT == 1 || cantDT == 2 && i == tableroPlayer1.length - 1 && j == tableroPlayer1[i].length - 1) {
                                        tableroPlayer1[x][y] = new Boat.DT();
                                        cantidadBarcos--;
                                        cantDT--;
                                    }
                                }
                            }
                        }
                    } else {
                        System.out.println("Opcion no valida");
                    }
                } while (cantidadBarcos != 0);

                if (dificultad == 1) {
                    cantidadBarcos = 5;
                } else if (dificultad == 2) {
                    cantidadBarcos = 4;
                } else if (dificultad == 3) {
                    cantidadBarcos = 2;
                } else {
                    cantidadBarcos = 1;
                }

                do {
                    System.out.println("Jugador 2");
                    System.out.println("Quedan " + cantidadBarcos + " barcos por colocar");
                    System.out.println("Que tipo de barco desea colocar?");
                    System.out.println("1. Portaaviones");
                    System.out.println("2. Acorazado");
                    System.out.println("3. Submarino");
                    System.out.println("4. Destructor");
                    System.out.println("Seleccione una opcion");
                    int tipoBarco = leer.nextInt();
                    System.out.println("Ingrese la coordenada X");
                    x = leer.nextInt();
                    System.out.println("Ingrese la coordenada Y");
                    y = leer.nextInt();

                    if (tipoBarco == 1) {
                        for (int i = 0; tableroPlayer2.length > i; i++) {
                            for (int j = 0; tableroPlayer2[i].length > j; j++) {
                                if (tableroPlayer2[i][j] == tableroPlayer2[x][y] && tableroPlayer2[i][j] != null) {
                                    System.out.println("Ya hay un barco en esa posicion");
                                } else {
                                    if (cantPA2 == 0 && i == tableroPlayer2.length - 1 && j == tableroPlayer2[i].length - 1) {
                                        System.out.println("Ya hay un portaaviones");
                                    } else if (cantPA2 == 1 && i == tableroPlayer2.length - 1 && j == tableroPlayer2[i].length - 1) {
                                        tableroPlayer2[x][y] = new Boat.PA();
                                        cantidadBarcos--;
                                        cantPA2--;
                                    }
                                }
                            }
                        }
                    } else if (tipoBarco == 2) {
                        for (int i = 0; tableroPlayer2.length > i; i++) {
                            for (int j = 0; tableroPlayer2[i].length > j; j++) {
                                if (tableroPlayer2[i][j] == tableroPlayer2[x][y] && tableroPlayer2[i][j] != null) {
                                    System.out.println("Ya hay un barco en esa posicion");
                                } else {
                                    if (cantAZ2 == 0 && i == tableroPlayer2.length - 1 && j == tableroPlayer2[i].length - 1) {
                                        System.out.println("Ya hay un acorazado");
                                    } else if (cantAZ2 == 1 && i == tableroPlayer2.length - 1 && j == tableroPlayer2[i].length - 1) {
                                        tableroPlayer2[x][y] = new Boat.AZ();
                                        cantidadBarcos--;
                                        cantAZ2--;
                                    }
                                }
                            }
                        }
                    } else if (tipoBarco == 3) {
                        for (int i = 0; tableroPlayer2.length > i; i++) {
                            for (int j = 0; tableroPlayer2[i].length > j; j++) {
                                if (tableroPlayer2[i][j] == tableroPlayer2[x][y] && tableroPlayer2[i][j] != null) {
                                    System.out.println("Ya hay un barco en esa posicion");
                                } else {
                                    if (cantSM2 == 0 && i == tableroPlayer2.length - 1 && j == tableroPlayer2[i].length - 1) {
                                        System.out.println("Ya hay un submarino");
                                    } else if (cantSM2 == 1 && i == tableroPlayer2.length - 1 && j == tableroPlayer2[i].length - 1) {
                                        tableroPlayer2[x][y] = new Boat.SM();
                                        cantidadBarcos--;
                                        cantSM2--;
                                    }
                                }
                            }
                        }
                    } else if (tipoBarco == 4) {
                        for (int i = 0; tableroPlayer2.length > i; i++) {
                            for (int j = 0; tableroPlayer2[i].length > j; j++) {
                                if (tableroPlayer2[i][j] == tableroPlayer2[x][y] && tableroPlayer2[i][j] != null) {
                                    System.out.println("Ya hay un barco en esa posicion");
                                } else {
                                    if (cantDT2 == 0 && i == tableroPlayer2.length - 1 && j == tableroPlayer2[i].length - 1) {
                                        System.out.println("Ya hay un destructor");
                                    } else if (cantDT2 == 1 || cantDT2 == 2 && i == tableroPlayer2.length - 1 && j == tableroPlayer2[i].length - 1) {
                                        tableroPlayer2[x][y] = new Boat.DT();
                                        cantidadBarcos--;
                                        cantDT2--;
                                    }
                                }
                            }
                        }
                    } else {
                        System.out.println("Opcion no valida");
                    }
                } while (cantidadBarcos != 0);

                imprimirTableroPlayer2();
                System.out.println("Turno del jugador 1");
                System.out.println("Ingrese la coordenada X");
                x = leer.nextInt();
                if (x == -1) {
                    System.out.println("Estas seguro que deseas salir del juego? (1)Si (2)No");
                    int salir = leer.nextInt();
                    if (salir == 1) {
                        System.out.println(obtenerPlayer1() + " Se ha retirado del juego" + " " + obtenerPlayer2() + " ha ganado");
                        add_Reporte(obtenerPlayer1(), " Se ha retirado del juego" + " " + obtenerPlayer2() + " ha ganado");
                        add_puntos(obtenerPlayer2(), 3);
                        gameOver = true;
                    } else {
                        System.out.println("Ingrese la coordenada X");
                        x = leer.nextInt();
                    }
                }
                System.out.println("Ingrese la coordenada Y");
                y = leer.nextInt();
                if (y == -1) {
                    System.out.println("Estas seguro que deseas salir del juego? (1)Si (2)No");
                    int salir = leer.nextInt();
                    if (salir == 1) {
                        System.out.println(obtenerPlayer1() + " Se ha retirado del juego" + " " + obtenerPlayer2() + " ha ganado");
                        add_Reporte(obtenerPlayer1(), " Se ha retirado del juego" + " " + obtenerPlayer2() + " ha ganado");
                        add_puntos(obtenerPlayer2(), 3);
                        gameOver = true;
                    } else {
                        System.out.println("Ingrese la coordenada Y");
                        y = leer.nextInt();
                    }
                }
                if (obtenerBarco(x, y, tableroPlayer2)) {
                    System.out.println("Has acertado");

                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 10; j++) {
                            if (tableroPlayer2[i][j] != null) {
                                if (tableroPlayer2[i][j].tipoBarco() == 1) {
                                    cambiarPosicionBarcos();
                                    if (tableroPlayer2[i][j].getHealth() == 0) {
                                        System.out.println("Has hundido un portaaviones");
                                    }
                                } else if (tableroPlayer2[i][j].tipoBarco() == 2) {
                                    cambiarPosicionBarcos();
                                    if (tableroPlayer2[i][j].getHealth() == 0) {
                                        System.out.println("Has hundido un acorazado");
                                    }
                                } else if (tableroPlayer2[i][j].tipoBarco() == 3) {
                                    cambiarPosicionBarcos();
                                    if (tableroPlayer2[i][j].getHealth() == 0) {
                                        System.out.println("Has hundido un submarino");
                                    }
                                } else if (tableroPlayer2[i][j].tipoBarco() == 4) {
                                    cambiarPosicionBarcos();
                                    if (tableroPlayer2[i][j].getHealth() == 0) {
                                        System.out.println("Has hundido un destructor");
                                    }
                                }
                                cantidadBarcos2++;
                            }
                        }
                    }
                    if (cantidadBarcos2 == 0) {
                        System.out.println("El jugador" + obtenerPlayer1() + "ha ganado");
                        add_Reporte(obtenerPlayer1(), "Ha ganado al hundir todos los barcos de " + obtenerPlayer2()
                                + " en Modo " + getDificultad());
                        add_puntos(obtenerPlayer1(), 3);
                        gameOver = true;
                    } else {
                        System.out.println("El jugador 2 tiene " + cantidadBarcos2 + " barcos");
                    }
                } else if (!obtenerBarco(x, y, tableroPlayer2)) {
                    System.out.println("Has fallado");
                    imprimirFallo(x, y);
                }
                cambiarTurno();

                for (int i = 0; i < tablero.length; i++) {
                    for (int j = 0; j < tablero[i].length; j++) {
                        if (tablero[i][j].equals("[ F ]")) {
                            tablero[i][j] = "[ X ]";
                        }
                    }
                }

                imprimirTableroPlayer1();

                System.out.println("Turno del jugador 2");
                System.out.println("Ingrese la coordenada X");
                x = leer.nextInt();
                if (x == -1) {
                    System.out.println("Estas seguro que deseas salir del juego? (1)Si (2)No");
                    int salir = leer.nextInt();
                    if (salir == 1) {
                        System.out.println(obtenerPlayer2() + " Se ha retirado del juego" + " " + obtenerPlayer1() + " ha ganado");
                        add_Reporte(obtenerPlayer2(), " Se ha retirado del juego" + " " + obtenerPlayer1() + " ha ganado");
                        add_puntos(obtenerPlayer1(), 3);
                        gameOver = true;
                    } else {
                        System.out.println("Ingrese la coordenada X");
                        x = leer.nextInt();
                    }
                }
                System.out.println("Ingrese la coordenada Y");
                y = leer.nextInt();
                if (y == -1) {
                    System.out.println("Estas seguro que deseas salir del juego? (1)Si (2)No");
                    int salir = leer.nextInt();
                    if (salir == 1) {
                        System.out.println(obtenerPlayer2() + " Se ha retirado del juego" + " " + obtenerPlayer1() + " ha ganado");
                        add_Reporte(obtenerPlayer2(), " Se ha retirado del juego" + " " + obtenerPlayer1() + " ha ganado");
                        add_puntos(obtenerPlayer1(), 3);
                        gameOver = true;
                    } else {
                        System.out.println("Ingrese la coordenada Y");
                        y = leer.nextInt();
                    }
                }
                if (obtenerBarco(x, y, tableroPlayer1)) {
                    System.out.println("Has acertado");

                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 10; j++) {
                            if (tableroPlayer1[i][j] != null) {
                                if (tableroPlayer1[i][j].tipoBarco() == 1) {
                                    cambiarPosicionBarcos();
                                    if (tableroPlayer1[i][j].getHealth() == 0) {
                                        System.out.println("Has hundido un portaaviones");
                                    }
                                } else if (tableroPlayer1[i][j].tipoBarco() == 2) {
                                    cambiarPosicionBarcos();
                                    if (tableroPlayer1[i][j].getHealth() == 0) {
                                        System.out.println("Has hundido un acorazado");
                                    }
                                } else if (tableroPlayer1[i][j].tipoBarco() == 3) {
                                    cambiarPosicionBarcos();
                                    if (tableroPlayer1[i][j].getHealth() == 0) {
                                        System.out.println("Has hundido un submarino");
                                    }
                                } else if (tableroPlayer1[i][j].tipoBarco() == 4) {
                                    cambiarPosicionBarcos();
                                    if (tableroPlayer1[i][j].getHealth() == 0) {
                                        System.out.println("Has hundido un destructor");
                                    }
                                }
                                cantidadBarcos1++;
                            }
                        }
                    }
                    if (cantidadBarcos1 == 0) {
                        System.out.println("El jugador" + obtenerPlayer2() + "ha ganado");
                        add_Reporte(obtenerPlayer2(), "Ha ganado al hundir todos los barcos de " + obtenerPlayer1()
                                + " en Modo " + getDificultad());
                        add_puntos(obtenerPlayer2(), 3);
                        gameOver = true;
                    } else {
                        System.out.println("El jugador 2 tiene " + cantidadBarcos1 + " barcos");
                    }

                } else if (!obtenerBarco(x, y, tableroPlayer1)) {
                    System.out.println("Has fallado");
                }
                cambiarTurno();
            }
        }
        while (!gameOver) ;
    }
}


