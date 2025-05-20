package logica;

// Ricardo Fabian Espinosa Largo

import db.DBfila;
import modelo.Fila;

import java.sql.SQLException;

public class logicaFila {

    // Instancia del gestor de base de datos
    DBfila resultadoDB = new DBfila();
    
    // Metodo para insertar una fila a la base de datos
    public void insertarFila(Fila result)
            throws ClassNotFoundException, SQLException {
        resultadoDB.insertarResultado(result);
    }
}