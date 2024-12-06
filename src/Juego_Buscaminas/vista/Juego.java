package Juego_Buscaminas.vista;

import java.util.Scanner;

import Juego_Buscaminas.controlador.IJuego;
import Juego_Buscaminas.controlador.Tablero;
import java.io.*;
public class Juego implements IJuego{
	private Tablero tablero;
	private boolean juego_Terminado;

	public void iniciar() {
		// Inicializa el trablero con un numero de filas, columnas y cantidad de minas.
		tablero = new Tablero(10, 10, 10);
		juego_Terminado = false;
		Scanner scanner = new Scanner(System.in);

		
		System.out.println("\n============Bienvenido al Buscaminas===========");
		System.out.println("Opciones: 'descubrir', 'marcar', 'guardar', 'cargar'");
		
		System.out.println("INSTRUCCIONES");
		System.out.println("Para dar instrucciones en que casilla ubicarse se de deber colocar la palabra: 'descubrir' y las cordenas");
		System.out.println("Para marcar una casilla colocar la palabra marcar seguido de la cordenada");
		while (!juego_Terminado) {
			tablero.Most_Tablero();
			System.out.println("Ingrese accion y coordenadas (Ej: descubrir E5)");
			String entrada = scanner.nextLine();
			String[] partes = entrada.split(" ");
			
			if(partes.length == 1) {
				if(partes[0].equalsIgnoreCase("guardar")) {
					guardarEstado("Estado_juego.dat");
					System.out.println("Estado guardado");
					continue;
				}else if(partes[0].equalsIgnoreCase("cargar")) {
					cargarEstado("Estado_juego.dat");
					System.out.println("Estado cargado.");
					continue;
				}
			}
			// Validacion que el usuario haya ingresado los dos valors solicitados.
			if (partes.length != 2) {
				System.out.println("Entrada Invalida. Usa el formato 'acción coordenada' (Ejemplo: descubrir A5)");
				continue;
			}

			String accion = partes[0];// obtine la accion a realizar
			String coordenada = partes[1];// obtiene las cordenas para ubicar en el tablero.

			int fila = coordenada.charAt(0) - 'A'; //Se realiza el mapeo de la fila del valor ingresado menos el valor ascci de 'A'
			int column;
			try {
				column = Integer.parseInt(coordenada.substring(1)) - 1;
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.println("Coordenada Invalida. Usa el fomrato A5.");
				continue;
			}

			// valida las filas y las columnas que existen en el tablero.
			if (fila < 0 || fila >= 10 || column < 0 || column >= 10) {
				System.out.println("Coordenada fuera de rango");
				continue;
			}
			/*
			
			String acionARealizar = accion.toLowerCase();
			if (acionARealizar.equals("descubrir")) {
				if (tablero.descubrir_Casilla(fila, column)) {
					System.out.println("Has pisado una mina. fin del juego");
					juego_Terminado = true;
				} else if (tablero.Juego_Ganado()) {
					System.out.println("Felicidades has descubierto todas las casillas");
					juego_Terminado = true;
				}
			}

			if (acionARealizar.equals("marcar")) {
				tablero.Marcar_Casilla(fila, column);
			}

			if (!acionARealizar.equals("marcar") && !acionARealizar.equals("descubrir")) {
				System.out.println("Accion desconocida. Usa 'descubrir' o 'marcar'");
			}*/
			switch(accion.toLowerCase()) {
			case "decubrir":
				if(tablero.descubrir_Casilla(fila, column)) {
					System.out.println("Has pisado una mina. Fin del juego");
					juego_Terminado = true;
				}else if (tablero.Juego_Ganado()){
					System.out.println("Felicidades!! Has ganado");
					juego_Terminado = true;
				}
				break;
				
			case "marcar":
				tablero.Marcar_Casilla(fila, column);
				break;
			default:
				System.out.println("Accion desconocida");
			}
	

		}
		tablero.Most_Tablero_Final();// mmuestra el tablero final
		scanner.close();
	}
	private void guardarEstado(String archivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(tablero);
        } catch (IOException e) {
            System.out.println("Error al guardar el estado del juego: " + e.getMessage());
        }
    }
	private void cargarEstado(String archivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            tablero = (Tablero) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar el estado del juego: " + e.getMessage());
        }
	}
}
