package vista;

// Ricardo Fabian Espinosa Largo
import java.util.concurrent.atomic.AtomicInteger;
import logica.logicaFila;
import modelo.Fila;

public class Consola {

    public static void iniciar() {
        // Aquí defino el tamaño de la matriz: 5 filas y 6 columnas
        // Las 5 primeras columnas son datos, la última es para guardar la suma de cada fila
        int filas = 5;
        int columnas = 6;

        // Genero la matriz con valores aleatorios
        int[][] matriz = generarMatriz(filas, columnas);

        // Este contador me va a servir para registrar en qué orden se van completando los hilos
        AtomicInteger contadorOrden = new AtomicInteger(0);

        // Instancio el objeto que me va a permitir insertar datos en la base
        logicaFila objLogFila = new logicaFila();

        // Creo un arreglo de hilos, uno por cada fila de la matriz
        Thread[] hilos = new Thread[filas];

        // Recorro cada fila y le creo su hilo correspondiente
        for (int i = 0; i < filas; i++) {
            final int index = i; // Guardo el índice porque lo voy a usar dentro del hilo

            // Armo el hilo usando una expresión lambda
            hilos[i] = new Thread(() -> {
                // Le pongo un nombre identificador al hilo
                // Esto solo funciona dentro de lambda: Thread.currentThread().getName();
                final String nombreHilo = Thread.currentThread().getName();

                // Tomo la fila que le toca a este hilo
                int[] fila = matriz[index];

                // Hago la sumatoria de los primeros 5 elementos de la fila
                int suma = 0;
                for (int j = 0; j < fila.length - 1; j++) {
                    suma += fila[j];
                }

                // Guardo la suma en la última posición de la fila
                fila[fila.length - 1] = suma;

                // Copio los primeros 5 elementos a un nuevo arreglo para pasarlos a la base
                int[] datos = new int[5];
                System.arraycopy(fila, 0, datos, 0, 5);

                // Uso el contador atómico para saber en qué orden terminó este hilo
                int orden = contadorOrden.incrementAndGet();

                // Armo el objeto Resultado con todo lo que necesito guardar
                Fila newfila = new Fila(nombreHilo, datos, suma, orden);
                try {
                    // Intento insertar ese newfila en la base de datos
                    objLogFila.insertarFila(newfila);

                    // Si todo va bien, muestro en consola que el hilo terminó y qué suma generó
                    System.out.println(nombreHilo + " insertado (orden " + orden + ") con suma = " + suma);
                } catch (Exception e) {
                    // Si algo falla, lo capturo y muestro un error sin detener el programa
                    System.out.println("Error en " + nombreHilo + ": " + e.getMessage());
                }
            });

            // Arranco el hilo actual
            hilos[i].start();
        }
        
        // Esperar que todos los hilos terminen
        for (int i = 0; i < filas; i++) {
            try {
                hilos[i].join(); 
            } catch (InterruptedException e) {
                System.out.println("Error esperando hilo " + (i + 1));
            }
        }
        mostrarMatriz("Matriz insertada", matriz);
    }

    // Metodo para llenar la matriz con datos aleatorios
    private static int[][] generarMatriz(int filas, int columnas) {
        int[][] matriz = new int[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas - 1; j++) {
                matriz[i][j] = (int) (Math.random() * 10);
            }
        }
        return matriz;
    }

    // Metodo para imprimir la matriz en consola
    public static void mostrarMatriz(String titulo, int[][] matriz) {
        System.out.println("\n" + titulo + ":");
        for (int[] fila : matriz) {
            for (int val : fila) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }

    // Metodo para calcular la suma total de la ultima columa
    public static void mostrarSumaTotal(int[][] matriz) {
        int suma = 0;
        int colSuma = matriz[0].length - 1;
        for (int[] fila : matriz) {
            suma += fila[colSuma];
        }
        System.out.println("Suma total (última columna): " + suma);
    }
}