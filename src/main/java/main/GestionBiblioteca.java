package main;

import java.util.Scanner;

/**
 * Clase principal del TDA que administra la logica del proyecto,
 * arreglos unidimensionales y matrices.
 */
public class GestionBiblioteca {

    // Arreglo Unidimensional para almacenar los usuarios registrados
    private Usuario[] arregloUsuarios;
    private int contadorUsuarios;

    // Matriz Bidimensional: filas = Estantes, columnas = Secciones
    // Valor 1 = Libro Disponible, Valor 0 = Libro Prestado / Ausente
    private int[][] matrizEstantes;

    private Scanner scanner;

    public GestionBiblioteca(int capacidadUsuarios, int filasEstantes, int columnasSecciones) {
        this.arregloUsuarios = new Usuario[capacidadUsuarios];
        this.contadorUsuarios = 0;
        this.matrizEstantes = new int[filasEstantes][columnasSecciones];
        this.scanner = new Scanner(System.in);
        
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
     * Registrar un usuario ingresado desde teclado en el arreglo unidimensional.
     */
    public void registrarUsuario() {
        if (contadorUsuarios >= arregloUsuarios.length) {
            System.out.println("-> Error: El registro de usuarios esta lleno.");
            return;
        }

        System.out.print("Ingrese ID del usuario: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese Nombre del usuario: ");
        String nombre = scanner.nextLine();

        arregloUsuarios[contadorUsuarios] = new Usuario(id, nombre);
        contadorUsuarios++;
        System.out.println("-> Usuario registrado exitosamente en el indice " + (contadorUsuarios - 1) + ".");
    }

    /**
     * Muestra el arreglo unidimensional de usuarios almacenado.
     */
    public void mostrarUsuarios() {
        System.out.println("\n--- LISTA DE USUARIOS REGISTRADOS ---");
        if (contadorUsuarios == 0) {
            System.out.println("No hay usuarios registrados actualmente.");
            return;
        }
        for (int i = 0; i < contadorUsuarios; i++) {
            System.out.println("Indice [" + i + "] -> " + arregloUsuarios[i]);
        }
    }

    /**
     * Operacion de Busqueda 1: Búsqueda secuencial en el arreglo unidimensional por ID.
     */
    public void buscarUsuarioPorId() {
        System.out.print("Ingrese el ID del usuario a buscar: ");
        String idBuscado = scanner.nextLine();
        boolean encontrado = false;

        for (int i = 0; i < contadorUsuarios; i++) {
            if (arregloUsuarios[i].getIdUsuario().equalsIgnoreCase(idBuscado)) {
                System.out.println("-> Usuario encontrado en el indice " + i + ": " + arregloUsuarios[i]);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("-> No se encontro ningun usuario con el ID especificado.");
        }
    }

    /**
     * Funcionalidad propia: Muestra la matriz de manera visual en consola.
     */
    public void mostrarMapaEstantes() {
        System.out.println("\n--- MAPA DE ESTANTES Y SECCIONES (MATRIZ) ---");
        System.out.print("           ");
        for (int j = 0; j < matrizEstantes[0].length; j++) {
            System.out.print("Sección " + j + "  ");
        }
        System.out.println();

        for (int i = 0; i < matrizEstantes.length; i++) {
            System.out.print("Estante " + i + " | ");
            for (int j = 0; j < matrizEstantes[i].length; j++) {
                if (matrizEstantes[i][j] == 1) {
                    System.out.print("  [ O ]    "); // O = Disponible
                } else {
                    System.out.print("  [ X ]    "); // X = Prestado
                }
            }
            System.out.println();
        }
        System.out.println("Convencion: [ O ] = Disponible | [ X ] = Prestado");
    }

    /**
     * Operacion de Actualizacion: Modifica el estado en la matriz (1 a 0 o viceversa).
     */
    public void cambiarEstadoPrestamo() {
        System.out.print("Ingrese el numero de Estante (Fila 0 a " + (matrizEstantes.length - 1) + "): ");
        int fila = scanner.nextInt();
        System.out.print("Ingrese el numero de Seccion (Columna 0 a " + (matrizEstantes[0].length - 1) + "): ");
        int columna = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        if (fila >= 0 && fila < matrizEstantes.length && columna >= 0 && columna < matrizEstantes[0].length) {
            System.out.println("Estado actual en [" + fila + "][" + columna + "]: " + 
                    (matrizEstantes[fila][columna] == 1 ? "Disponible" : "Prestado"));
            System.out.print("Seleccione nuevo estado (1 = Disponible, 0 = Prestado): ");
            int nuevoEstado = scanner.nextInt();
            scanner.nextLine();

            if (nuevoEstado == 0 || nuevoEstado == 1) {
                matrizEstantes[fila][columna] = nuevoEstado;
                System.out.println("-> Estado actualizado correctamente.");
            } else {
                System.out.println("-> Estado invalido. Debe ingresar 1 o 0.");
            }
        } else {
            System.out.println("-> Coordenadas fuera de rango.");
        }
    }

    /**
     * Calculo o Analisis 1: Calcula el total de libros disponibles y prestados y su porcentaje.
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

        System.out.println("\n--- ANALISIS 1: ESTADISTICAS GENERALES DE LA BIBLIOTECA ---");
        System.out.println("Total capacidad fisica: " + totalCasillas + " espacios.");
        System.out.println("Libros disponibles: " + totalDisponibles + " (" + String.format("%.2f", porcentajeDisponibles) + "%)");
        System.out.println("Libros prestados: " + totalPrestados + " (" + String.format("%.2f", porcentajePrestados) + "%)");
    }

    /**
     * Calculo o Analisis 2: Determina la fila (Estante) con mayor numero de libros disponibles.
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

        System.out.println("\n--- ANALISIS 2: ESTANTE MAS DESOCUPADO / DISPONIBLE ---");
        System.out.println("El estante (fila) con mas libros disponibles es el Estante " + estanteMayor +
                           " con un total de " + maxDisponibles + " libros disponibles.");
    }
}