package almacen;

import java.util.ArrayList;
import java.util.Iterator;

/** Gestiona la colección de productos del almacén (lógica de negocio). */
public class Inventario {
    public static final int STOCK_CRITICO = 5;

    private final ArrayList<Producto> productos = new ArrayList<>();

    /** Busca un producto por nombre (sin distinguir mayúsculas). Devuelve null si no existe. */
    public Producto buscar(String nombre) {
        Iterator<Producto> it = productos.iterator();
        while (it.hasNext()) {
            Producto p = it.next();
            if (p.getNombre().equalsIgnoreCase(nombre.trim())) {
                return p;
            }
        }
        return null;
    }

    /**
     * Caso de uso 1: registrar. Si el producto existe, suma al stock; si no, lo crea.
     * Devuelve false si el nombre está vacío o la cantidad no es positiva.
     */
    public boolean registrar(String nombre, int cantidad) {
        if (nombre == null || nombre.trim().isEmpty() || cantidad <= 0) {
            return false;
        }
        Producto existente = buscar(nombre);
        if (existente != null) {
            existente.agregarStock(cantidad);
        } else {
            productos.add(new Producto(nombre.trim(), cantidad));
        }
        return true;
    }

    /**
     * Caso de uso 2: vender. Devuelve false si el producto no existe,
     * la cantidad es inválida o no hay stock suficiente.
     */
    public boolean vender(String nombre, int cantidad) {
        Producto p = buscar(nombre);
        if (p == null) {
            return false;
        }
        return p.restarStock(cantidad);
    }

    /** Caso de uso 3: inventario completo. */
    public ArrayList<Producto> getInventario() {
        return new ArrayList<>(productos);
    }

    /** Caso de uso 4: productos con 5 unidades o menos. */
    public ArrayList<Producto> getStockCritico() {
        ArrayList<Producto> criticos = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getStock() <= STOCK_CRITICO) {
                criticos.add(p);
            }
        }
        return criticos;
    }
}
