package Juego_Buscaminas;
import java.util.Scanner;
import Elementos_Buscaminas.*;

public class Juego {
    private Tablero tablero;
    private boolean juego_Terminado;


    public void iniciar(){
        tablero = new Tablero(10,10,10);
        juego_Terminado = false; 
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Bienvenido al Buscaminas");
        while (!juego_Terminado) {
            tablero.Most_Tablero();
            System.out.println("Ingrese accicion");
            String entrada = scanner.nextLine();
            String[] partes =  entrada.split(" ");
            if (partes.length != 2) {
                System.out.println("Entrada Invalida");
                continue;
            }

            String accion = partes[0];
            String coordenada = partes[1];

            int fila = coordenada.charAt(1) - '1';
            int column = Character.toUpperCase(coordenada.charAt(0)) - 'A';

            if (fila < 0 || fila >= 10 || column < 0 ||  column >= 10) {
                System.out.println("Coordenada fuera de rango");
                continue;
            }

            switch (accion.toLowerCase()) {
                case "descubrir":
                    if (tablero.descubrir_Casilla(fila, column)) {
                        System.out.println("Has pisado una mina. fin del juego");
                        juego_Terminado = true;
                    }else if (tablero.Juego_Ganado()) {
                        System.out.println("Felicidades has descubierto todas las casillas");
                    }
                    break;
                case "marcar":
                    tablero.Marcar_Casilla(fila, column);   
                    break;
                default:
                    System.out.println("Accion desconocida");
                    break;
            }
        }
        tablero.Most_Tablero_Final();
        scanner.close();
    }
}
