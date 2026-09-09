package main;

import javax.swing.JOptionPane;

/**
 * Clase principal del TDA que administra la lógica del proyecto,
 * arreglos unidimensionales y matrices usando JOptionPane.
 */
public class GestionBiblioteca {

    // Arreglo Unidimensional para almacenar los usuarios registrados
    private Usuario[] arregloUsuarios;
    private int contadorUsuarios;

    // Matriz Bidimensional: filas = Estantes, columnas = Secciones
    // Valor 1 = Libro Disponible, Valor 0 = Libro Prestado / Ausente
    private int[][] matrizEstantes;

    public GestionBiblioteca(int capacidadUsuarios, int filasEstantes, int columnasSecciones) {
        this.arregloUsuarios = new Usuario[capacidadUsuarios];
        this.contadorUsuarios = 0;
        this.matrizEstantes = new int[filasEstantes][columnasSecciones];
        
        // Inicializar la matriz con disponibilidad por defecto (1 = disponible)
        inicializarMatriz();
    }

    /**
     * Llena la matriz indicando que al inicio todos los espacios tienen libros disponibles.
     */
    private void inicializarMatriz() {
        for (int i = 0; i < matrizEstantes.length; i++) {
            for (int j = 0; j < matrizEstantes[i].length; j++) {
                matrizEstantes[i][j] = 1; // 1 representa Disponible
            }
        }
    }

    /**
     * Registrar un usuario ingresado mediante JOptionPane en el arreglo unidimensional.
     */
    public void registrarUsuario() {
        if (contadorUsuarios >= arregloUsuarios.length) {
            JOptionPane.showMessageDialog(null, 
                    "Error: El registro de usuarios está lleno.", 
                    "Capacidad Máxima", 
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = JOptionPane.showInputDialog(null, 
                "Ingrese el ID del usuario:", 
                "Registrar Usuario", 
                JOptionPane.QUESTION_MESSAGE);
        
        if (id == null || id.trim().isEmpty()) return;

        String nombre = JOptionPane.showInputDialog(null, 
                "Ingrese el Nombre del usuario:", 
                "Registrar Usuario", 
                JOptionPane.QUESTION_MESSAGE);

        if (nombre == null || nombre.trim().isEmpty()) return;

        arregloUsuarios[contadorUsuarios] = new Usuario(id, nombre);
        contadorUsuarios++;

        JOptionPane.showMessageDialog(null, 
                "Usuario registrado exitosamente en el índice [" + (contadorUsuarios - 1) + "].", 
                "Registro Exitoso", 
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra el arreglo unidimensional de usuarios almacenado en una ventana emergente.
     */
    public void mostrarUsuarios() {
        if (contadorUsuarios == 0) {
            JOptionPane.showMessageDialog(null, 
                    "No hay usuarios registrados actualmente.", 
                    "Lista Vacía", 
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder reporte = new StringBuilder("--- LISTA DE USUARIOS REGISTRADOS ---\n\n");
        for (int i = 0; i < contadorUsuarios; i++) {
            reporte.append("Índice [").append(i).append("] -> ").append(arregloUsuarios[i]).append("\n");
        }

        JOptionPane.showMessageDialog(null, 
                reporte.toString(), 
                "Usuarios Registrados", 
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Operación de Búsqueda 1: Búsqueda secuencial en el arreglo unidimensional por ID.
     */
    public void buscarUsuarioPorId() {
        String idBuscado = JOptionPane.showInputDialog(null, 
                "Ingrese el ID del usuario a buscar:", 
                "Buscar Usuario", 
                JOptionPane.QUESTION_MESSAGE);

        if (idBuscado == null || idBuscado.trim().isEmpty()) return;

        boolean encontrado = false;

        for (int i = 0; i < contadorUsuarios; i++) {
            if (arregloUsuarios[i].getIdUsuario().equalsIgnoreCase(idBuscado)) {
                JOptionPane.showMessageDialog(null, 
                        "Usuario encontrado en el índice [" + i + "]:\n" + arregloUsuarios[i], 
                        "Búsqueda Exitosa", 
                        JOptionPane.INFORMATION_MESSAGE);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(null, 
                    "No se encontró ningún usuario con el ID especificado.", 
                    "Sin Resultados", 
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Funcionalidad propia: Muestra la matriz de manera visual en formato gráfico dentro de JOptionPane.
     */
    public void mostrarMapaEstantes() {
        StringBuilder mapa = new StringBuilder("--- MAPA DE ESTANTES Y SECCIONES (MATRIZ) ---\n\n");
        
        mapa.append("              ");
        for (int j = 0; j < matrizEstantes[0].length; j++) {
            mapa.append("Sec ").append(j).append("    ");
        }
        mapa.append("\n");

        for (int i = 0; i < matrizEstantes.length; i++) {
            mapa.append("Estante ").append(i).append(" | ");
            for (int j = 0; j < matrizEstantes[i].length; j++) {
                if (matrizEstantes[i][j] == 1) {
                    mapa.append(" [ O ]   "); // O = Disponible
                } else {
                    mapa.append(" [ X ]   "); // X = Prestado
                }
            }
            mapa.append("\n");
        }

        mapa.append("\nConvención:\n [ O ] = Disponible\n [ X ] = Prestado");

        JOptionPane.showMessageDialog(null, 
                mapa.toString(), 
                "Mapa Físico de la Biblioteca", 
                JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Operación de Actualización: Modifica el estado en la matriz (1 a 0 o viceversa).
     */
    public void cambiarEstadoPrestamo() {
        try {
            String inputFila = JOptionPane.showInputDialog(null, 
                    "Ingrese el número de Estante (Fila 0 a " + (matrizEstantes.length - 1) + "):", 
                    "Actualizar Préstamo", 
                    JOptionPane.QUESTION_MESSAGE);
            if (inputFila == null) return;
            int fila = Integer.parseInt(inputFila);

            String inputColumna = JOptionPane.showInputDialog(null, 
                    "Ingrese el número de Sección (Columna 0 a " + (matrizEstantes[0].length - 1) + "):", 
                    "Actualizar Préstamo", 
                    JOptionPane.QUESTION_MESSAGE);
            if (inputColumna == null) return;
            int columna = Integer.parseInt(inputColumna);

            if (fila >= 0 && fila < matrizEstantes.length && columna >= 0 && columna < matrizEstantes[0].length) {
                String estadoActual = (matrizEstantes[fila][columna] == 1) ? "Disponible" : "Prestado";
                
                String inputNuevo = JOptionPane.showInputDialog(null, 
                        "Estado actual en [" + fila + "][" + columna + "]: " + estadoActual + 
                        "\n\nIngrese el nuevo estado:\n1 = Disponible\n0 = Prestado", 
                        "Modificar Estado", 
                        JOptionPane.QUESTION_MESSAGE);
                if (inputNuevo == null) return;
                int nuevoEstado = Integer.parseInt(inputNuevo);

                if (nuevoEstado == 0 || nuevoEstado == 1) {
                    matrizEstantes[fila][columna] = nuevoEstado;
                    JOptionPane.showMessageDialog(null, 
                            "Estado actualizado correctamente a: " + (nuevoEstado == 1 ? "Disponible" : "Prestado"), 
                            "Actualización Exitosa", 
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, 
                            "Estado inválido. Debe ingresar 1 o 0.", 
                            "Error de Entrada", 
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, 
                        "Coordenadas fuera de rango.", 
                        "Error de Rango", 
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, 
                    "Debe ingresar un valor numérico válido.", 
                    "Error de Formato", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Cálculo o Análisis 1: Calcula el total de libros disponibles y prestados y su porcentaje.
     */
    public void calcularEstadisticasGlobales() {
        int totalDisponibles = 0;
        int totalPrestados = 0;
        int totalCasillas = matrizEstantes.length * matrizEstantes[0].length;

        // Recorrido de la matriz con ciclos anidados
        for (int i = 0; i < matrizEstantes.length; i++) {
            for (int j = 0; j < matrizEstantes[i].length; j++) {
                if (matrizEstantes[i][j] == 1) {
                    totalDisponibles++;
                } else {
                    totalPrestados++;
                }
            }
        }

        double porcentajeDisponibles = ((double) totalDisponibles / totalCasillas) * 100;
        double porcentajePrestados = ((double) totalPrestados / totalCasillas) * 100;

        StringBuilder analisis = new StringBuilder("--- ANÁLISIS 1: ESTADÍSTICAS GENERALES ---\n\n");
        analisis.append("Capacidad física total: ").append(totalCasillas).append(" espacios.\n");
        analisis.append("Libros disponibles: ").append(totalDisponibles)
                .append(" (").append(String.format("%.2f", porcentajeDisponibles)).append("%)\n");
        analisis.append("Libros prestados: ").append(totalPrestados)
                .append(" (").append(String.format("%.2f", porcentajePrestados)).append("%)\n");

        JOptionPane.showMessageDialog(null, 
                analisis.toString(), 
                "Análisis Estadístico", 
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Cálculo o Análisis 2: Determina la fila (Estante) con mayor número de libros disponibles.
     */
    public void estanteConMayorDisponibilidad() {
        int estanteMayor = -1;
        int maxDisponibles = -1;

        for (int i = 0; i < matrizEstantes.length; i++) {
            int contadorFila = 0;
            for (int j = 0; j < matrizEstantes[i].length; j++) {
                if (matrizEstantes[i][j] == 1) {
                    contadorFila++;
                }
            }
            if (contadorFila > maxDisponibles) {
                maxDisponibles = contadorFila;
                estanteMayor = i;
            }
        }

        StringBuilder analisis = new StringBuilder("--- ANÁLISIS 2: ESTANTE MÁS DESOCUPADO ---\n\n");
        analisis.append("El estante con mayor número de libros disponibles es el Estante ").append(estanteMayor)
                .append(" con un total de ").append(maxDisponibles).append(" ejemplares disponibles.");

        JOptionPane.showMessageDialog(null, 
                analisis.toString(), 
                "Estante con Mayor Disponibilidad", 
                JOptionPane.INFORMATION_MESSAGE);
    }
}