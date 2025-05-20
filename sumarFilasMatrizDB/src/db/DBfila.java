package db;

// Ricardo Fabian Espinosa Largo

import modelo.Fila;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBfila {

    Conexion con = new Conexion();

    // Método para insertar una fila en la tabla resultado
    public int insertarResultado(Fila result) throws SQLException, ClassNotFoundException {
        String sentencia = "INSERT INTO resultado (nombrehilo, "
                + "columna1, columna2, columna3, columna4, "
                + "columna5, sumatoria, orden_ejecucion) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = con.getConnection().prepareStatement(sentencia);

        // Asignamos los datos
        ps.setString(1, result.getNombreHilo());
        
        // Obtenemos cada valor accediendo a los elementos de arreglo
        ps.setInt(2, result.getDatos()[0]);
        ps.setInt(3, result.getDatos()[1]);
        ps.setInt(4, result.getDatos()[2]);
        ps.setInt(5, result.getDatos()[3]);
        ps.setInt(6, result.getDatos()[4]);
        
        ps.setInt(7, result.getSumatoria());
        ps.setInt(8, result.getOrden());

        return ps.executeUpdate();
    }
}