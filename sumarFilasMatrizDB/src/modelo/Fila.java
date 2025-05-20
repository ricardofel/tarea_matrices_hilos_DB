package modelo;

// Ricardo Fabian Espinosa Largo

public class Fila {
    private String nombreHilo; // Nombre del hilo
    private int[] datos;       // Cada numero de la fila
    private int sumatoria;     // Sumatoria
    private int orden;         // Orden en que finalizaron los hilos 

    public Fila(String nombreHilo, int[] datos, int sumatoria, int orden) {
        this.nombreHilo = nombreHilo;
        this.datos = datos;
        this.sumatoria = sumatoria;
        this.orden = orden;
    }

    // Getters
    public String getNombreHilo() {
        return nombreHilo;
    }

    public int[] getDatos() {
        return datos;
    }

    public int getSumatoria() {
        return sumatoria;
    }

    public int getOrden() {
        return orden;
    }
}