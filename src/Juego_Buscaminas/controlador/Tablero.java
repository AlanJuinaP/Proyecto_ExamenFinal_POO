package Juego_Buscaminas.controlador;

import java.util.Random;

import Juego_Buscaminas.modelo.Casilla;

public class Tablero {
	 private static final long serialVersionUID = 1L;
	
	private final Casilla[][] casillas;
	private final int filas;
	private final int columnas;
	private final int num_Minas;

	public Tablero(int filas, int columnas, int num_Minas) {
		this.filas = filas;
		this.columnas = columnas;
		this.num_Minas = num_Minas;
		casillas = new Casilla[filas][columnas];
		inicializa_el_Tablero();
	}

	private void inicializa_el_Tablero() {
		// crear casillas
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				casillas[i][j] = new Casilla();
			}
		}

		// Colocar minas aleatoriamente
		Random random = new Random();
		int Minas_Colocadas = 0;
		while (Minas_Colocadas < num_Minas) {
			int fila = random.nextInt(filas);
			int colum = random.nextInt(columnas);
			if (!casillas[fila][colum].es_mina()) {
				casillas[fila][colum].Poner_Mina();
				Minas_Colocadas++;
				Update_Num_adyacente(fila, colum);
			}
		}
	}

	private void Update_Num_adyacente(int fila, int colum) {
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				int Fla_Ady = fila + i;
				int Colum_Ady = colum + j;
				if (Es_Valida(Fla_Ady, Colum_Ady) && !casillas[Fla_Ady][Colum_Ady].es_mina()) {
					casillas[Fla_Ady][Colum_Ady].Incrementar_Num();
				}
			}
		}
	}

	private boolean Es_Valida(int fila, int colum) {
		return fila >= 0 && fila < filas && colum >= 0 && colum < columnas;
	}

	public void Most_Tablero() {
		System.out.println(" ");
		for (int j = 1; j <= columnas; j++) {
			System.out.print(j + " ");
		}
		System.out.println();
		for (int i = 0; i < filas; i++) {
			System.out.print((char) ('A' + i) + " ");
			for (int j = 0; j < columnas; j++) {
				System.out.print(casillas[i][j].mostrar() + " ");
			}
			System.out.println();
		}
	}

	public void Most_Tablero_Final() {
		System.out.print(" ");
		for (int j = 1; j <= columnas; j++) {
			System.out.print(j + " ");
		}
		System.out.println();
		for (int i = 0; i < filas; i++) {
			System.out.print((char) ('A' + i) + " ");
			for (int j = 0; j < columnas; j++) {
				System.out.print(casillas[i][j].Most_Final() + " ");
			}
			System.out.println();
		}
	}

	public boolean descubrir_Casilla(int fila, int colum) {
		if (!Es_Valida(fila, colum) || casillas[fila][colum].es_descubierta()) {
			return false;
		}

		casillas[fila][colum].descubrir();
		if (casillas[fila][colum].es_mina()) {
			return true;// la mina es descubierta
		}
		if (casillas[fila][colum].getNum() == 0) {
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					descubrir_Casilla(fila + i, colum + j);
				}
			}
		}
		return false;
	}

	public void Marcar_Casilla(int fila, int colum) {
		if (Es_Valida(fila, colum)) {
			casillas[fila][colum].Marcar();
		}
	}

	public boolean Juego_Ganado() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				if (!casillas[i][j].es_mina() && !casillas[i][j].es_descubierta()) {
					return false;
				}
			}
		}
		return false;
	}
}
