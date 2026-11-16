package almacen;

/** Representa un producto del almacén: nombre y stock disponible. */
public class Producto {
    private final String nombre;
    private int stock;

    public Producto(String nombre, int stock) {
        this.nombre = nombre;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public int getStock() {
        return stock;
    }

    /** Suma unidades al stock actual. */
    public void agregarStock(int cantidad) {
        this.stock += cantidad;
    }

    /** Resta unidades. Devuelve false (y no cambia nada) si no alcanza el stock. */
    public boolean restarStock(int cantidad) {
        if (cantidad <= 0 || cantidad > stock) {
            return false;
        }
        this.stock -= cantidad;
        return true;
    }

    @Override
    public String toString() {
        return nombre + " - Stock: " + stock;
    }
}
