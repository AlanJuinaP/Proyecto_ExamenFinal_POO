package Juego_Buscaminas;

import Juego_Buscaminas.controlador.IJuego;
import Juego_Buscaminas.vista.Juego;

public class Buscaminas {
    public static void main(String[] args) {
    	
    	//Instancia el objeto para inicializar el juego. 
        IJuego juego = new Juego();
        juego.iniciar();
    }
}
