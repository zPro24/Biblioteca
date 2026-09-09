package main;

import java.util.Scanner;

/**
 * Clase ejecutable que contiene la interfaz de consola mediante un menu iterativo.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Se instancia el gestor de la biblioteca con 5 usuarios maximo, 4 estantes y 5 secciones
        GestionBiblioteca biblioteca = new GestionBiblioteca(5, 4, 5);

        int opcion = -1;

        do {
            System.out.println("\n=============================================");
            System.out.println("    SISTEMA DE GESTION DE BIBLIOTECA (UTS)");
            System.out.println("=============================================");
            System.out.println("1. Registrar nuevo usuario (Arreglo 1D)");
            System.out.println("2. Mostrar usuarios registrados");
            System.out.println("3. Buscar usuario por ID");
            System.out.println("4. Mostrar mapa visual de estantes (Matriz 2D)");
            System.out.println("5. Actualizar estado de prestamo (Matriz 2D)");
            System.out.println("6. Analisis 1: Estadisticas globales de disponibilidad");
            System.out.println("7. Analisis 2: Estante con mayor numero de libros");
            System.out.println("0. Salir del programa");
            System.out.print("Seleccione una opcion: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    biblioteca.registrarUsuario();
                    break;
                case 2:
                    biblioteca.mostrarUsuarios();
                    break;
                case 3:
                    biblioteca.buscarUsuarioPorId();
                    break;
                case 4:
                    biblioteca.mostrarMapaEstantes();
                    break;
                case 5:
                    biblioteca.cambiarEstadoPrestamo();
                    break;
                case 6:
                    biblioteca.calcularEstadisticasGlobales();
                    break;
                case 7:
                    biblioteca.estanteConMayorDisponibilidad();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opcion invalida. Intente de nuevo.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}