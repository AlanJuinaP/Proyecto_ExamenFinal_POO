package test.java;

import org.junit.jupiter.api.Test;

import Juego_Buscaminas.controlador.Tablero;

import static org.junit.jupiter.api.Assertions.*;

public class JuegoTest {

    // Test para descubrir una casilla con mina
    @Test
    void testDescubrirCasillaConMina() {
        Tablero tablero = new Tablero(3, 3, 1);  // Tablero de 3x3 con 1 mina

        // Busca una casilla con mina y pruébala
        boolean minaEncontrada = false;
        for (int i = 0; i < 3 && !minaEncontrada; i++) {
            for (int j = 0; j < 3 && !minaEncontrada; j++) {
                if (tablero.descubrir_Casilla(i, j)) { // Si se descubre una mina
                    minaEncontrada = true;
                }
            }
        }

        assertTrue(minaEncontrada, "No se encontró una mina en el tablero.");
    }

    // Test para descubrir una casilla sin mina
    @Test
    void testDescubrirCasillaSinMina() {
        Tablero tablero = new Tablero(3, 3, 1);  // Tablero de 3x3 con 1 mina

        // Busca una casilla segura (sin mina) y pruébala
        boolean sinMinaDescubierta = false;
        for (int i = 0; i < 3 && !sinMinaDescubierta; i++) {
            for (int j = 0; j < 3 && !sinMinaDescubierta; j++) {
                if (!tablero.descubrir_Casilla(i, j)) { // Si no es una mina
                    sinMinaDescubierta = true;
                }
            }
        }

        assertTrue(sinMinaDescubierta, "No se pudo descubrir una casilla sin mina.");
    }

    // Test para verificar si el juego ha sido ganado
    @Test
    void testJuegoGanado() {
        Tablero tablero = new Tablero(3, 3, 1);  // Tablero de 3x3 con 1 mina

        // Descubre todas las casillas excepto las minas
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!tablero.descubrir_Casilla(i, j) && !tablero.Juego_Ganado()) {
                    // Evita descubrir una casilla con mina
                    assertFalse(tablero.Juego_Ganado(), "El juego debería continuar.");
                }
            }
        }

        // Después de descubrir todo lo seguro, verifica si el juego está ganado
        assertTrue(tablero.Juego_Ganado(), "El juego debería haber sido ganado.");
    }
}
