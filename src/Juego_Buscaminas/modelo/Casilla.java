package Juego_Buscaminas.modelo;

public class Casilla {
	
	private static final long serialVersionUID = 1L; // Necesario para la serialización
	
    private boolean mina;
    private boolean descubierta;
    private boolean marcada;
    private int num;

    public Casilla(){
        mina = false;
        descubierta = false;
        marcada = false;
        num = 0;
    }

    public void Poner_Mina(){
        mina = true;
    }

    public boolean es_mina(){
        return mina;
    }

    public void descubrir(){
        descubierta = true;
    }

    public boolean es_descubierta(){
        return descubierta;
    }

    public void Marcar(){
        marcada = !marcada;
    }

    public void Incrementar_Num(){
        num++;
    }

    public int getNum() {
        return num;
    }

    public String mostrar() {
        if (descubierta) {
            return mina ? "X" : (num > 0 ? String.valueOf(num) : "V");
        }
        return marcada ? "M" : "■";
    }

    public String Most_Final(){
        return mina ? "X" : (num > 0 ? String.valueOf(num) : "V");
    }
}