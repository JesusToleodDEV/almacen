package almacen;

import java.util.ArrayList;
import java.util.Scanner;

/** Menú de consola para los 4 casos de uso. */
public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final Inventario inventario = new Inventario();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Opción: ");
            switch (opcion) {
                case 1 -> registrar();
                case 2 -> vender();
                case 3 -> verInventario();
                case 4 -> verStockCritico();
                case 5 -> System.out.println("Hasta luego.");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 5);
    }

    private static void mostrarMenu() {
        System.out.println("\n===== ALMACÉN =====");
        System.out.println("1. Registrar / reponer producto");
        System.out.println("2. Vender producto");
        System.out.println("3. Ver inventario");
        System.out.println("4. Ver stock crítico (5 o menos)");
        System.out.println("5. Salir");
    }

    private static void registrar() {
        System.out.print("Nombre del producto: ");
        String nombre = sc.nextLine();
        int cantidad = leerEntero("Cantidad recibida: ");
        if (inventario.registrar(nombre, cantidad)) {
            System.out.println("Producto registrado correctamente.");
        } else {
            System.out.println("Error: nombre vacío o cantidad inválida (debe ser mayor a 0).");
        }
    }

    private static void vender() {
        System.out.print("Nombre del producto: ");
        String nombre = sc.nextLine();
        Producto p = inventario.buscar(nombre);
        if (p == null) {
            System.out.println("Error: el producto no existe.");
            return;
        }
        int cantidad = leerEntero("Unidades vendidas: ");
        if (inventario.vender(nombre, cantidad)) {
            System.out.println("Venta realizada. Stock restante: " + p.getStock());
        } else {
            System.out.println("Error: cantidad inválida o stock insuficiente (disponible: " + p.getStock() + ").");
        }
    }

    private static void verInventario() {
        ArrayList<Producto> lista = inventario.getInventario();
        if (lista.isEmpty()) {
            System.out.println("El inventario está vacío.");
            return;
        }
        System.out.println("--- Inventario ---");
        for (Producto p : lista) {
            System.out.println(p);
        }
    }

    private static void verStockCritico() {
        ArrayList<Producto> lista = inventario.getStockCritico();
        if (lista.isEmpty()) {
            System.out.println("No hay productos con stock crítico.");
            return;
        }
        System.out.println("--- Stock crítico (5 o menos) ---");
        for (Producto p : lista) {
            System.out.println(p);
        }
    }

    /** Lee un entero validando que el usuario no escriba texto. */
    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número entero válido.");
            }
        }
    }
}
