-- Ricardo Fabian Espinosa Largo
-- Eliminar el esquema si ya existe
DROP SCHEMA IF EXISTS matriz_db;

-- Crear el esquema
CREATE SCHEMA matriz_db
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

-- Usar el esquema creado
USE matriz_db;

CREATE TABLE IF NOT EXISTS resultado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombrehilo VARCHAR(10), -- Ej "h1", "h2", "h3"
    columna1 INT,
    columna2 INT,
    columna3 INT,
    columna4 INT,
    columna5 INT,
    sumatoria INT, -- Resultado de la suma de la fila
    orden_ejecucion INT -- En que orden terminó el hilo
);

select * from resultado;