package main;

import javax.swing.JOptionPane;

/*
    Clase ejecutable que maneja un menú iterativo mediante JOptionPane.
*/
public class Main {

    public static void main(String[] args) {
        // Se instancia el gestor con 5 usuarios maximo, 4 estantes y 5 secciones
        GestionBiblioteca biblioteca = new GestionBiblioteca(5, 4, 5);

        String menu = "=============================================\n"
                    + "    SISTEMA DE GESTIÓN DE BIBLIOTECA (UTS)    \n"
                    + "=============================================\n"
                    + "1. Registrar nuevo usuario (Arreglo 1D)\n"
                    + "2. Mostrar usuarios registrados\n"
                    + "3. Buscar usuario por ID\n"
                    + "4. Mostrar mapa visual de estantes (Matriz 2D)\n"
                    + "5. Actualizar estado de préstamo (Matriz 2D)\n"
                    + "6. Análisis 1: Estadísticas globales de disponibilidad\n"
                    + "7. Análisis 2: Estante con mayor disponibilidad\n"
                    + "0. Salir del programa\n\n"
                    + "Seleccione una opción:";

        int opcion = -1;

        do {
            String input = JOptionPane.showInputDialog(null, menu, "Menú Principal", JOptionPane.QUESTION_MESSAGE);

            if (input == null) {
                // Si presiona cancelar o cierra la ventana, sale del programa
                opcion = 0;
            } else {
                try {
                    opcion = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    opcion = -1;
                }
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
                    JOptionPane.showMessageDialog(null, 
                            "Saliendo del sistema...", 
                            "Desconexión", 
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, 
                            "Opción inválida. Intente de nuevo.", 
                            "Error de Selección", 
                            JOptionPane.ERROR_MESSAGE);
            }
        } while (opcion != 0);
    }
}