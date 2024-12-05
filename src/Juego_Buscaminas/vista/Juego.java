package Juego_Buscaminas.vista;

import java.util.Scanner;

import Juego_Buscaminas.controlador.Tablero;

public class Juego {
	private Tablero tablero;
	private boolean juego_Terminado;

	public void iniciar() {
		// Inicializa el trablero con un numero de filas, columnas y cantidad de minas.
		tablero = new Tablero(10, 10, 10);
		juego_Terminado = false;
		Scanner scanner = new Scanner(System.in);

		System.out.println("\n============Bienvenido al Buscaminas===========");
		while (!juego_Terminado) {
			tablero.Most_Tablero();
			System.out.println("Ingrese accion y coordenadas (Ej: descubrir E5)");
			String entrada = scanner.nextLine();
			String[] partes = entrada.split(" ");
			for (String parte : partes) {
				System.out.println(parte);
			}

			// Validacion que el usuario haya ingresado los dos valors solicitados.
			System.out.println(partes.length);
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

			String acionARealizar = accion.toLowerCase();
			System.out.println("acionARealizar "+ acionARealizar);
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
			}

			/*
			 * switch (accion.toLowerCase()) { case "descubrir": if
			 * (tablero.descubrir_Casilla(fila, column)) {
			 * System.out.println("Has pisado una mina. fin del juego"); juego_Terminado =
			 * true; }else if (tablero.Juego_Ganado()) {
			 * System.out.println("Felicidades has descubierto todas las casillas");
			 * juego_Terminado=true;
			 * 
			 * } break; case "marcar": tablero.Marcar_Casilla(fila, column); break; default:
			 * System.out.println("Accion desconocida. Usa 'descubrir' o 'marcar'"); break;
			 * }
			 */
		}
		tablero.Most_Tablero_Final();// mmuestra el tablero final
		scanner.close();
	}
}
