package almacen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InventarioTest {
    private Inventario inventario;

    @BeforeEach
    void setUp() {
        inventario = new Inventario();
    }

    @Test
    void registrarProductoNuevoCreaProductoConStockInicial() {
        assertTrue(inventario.registrar("Bebida 3L", 10));
        assertEquals(10, inventario.buscar("Bebida 3L").getStock());
        assertEquals(1, inventario.getInventario().size());
    }

    @Test
    void registrarProductoExistenteSumaStockSinDuplicar() {
        inventario.registrar("Bebida 3L", 10);
        inventario.registrar("Bebida 3L", 5);
        assertEquals(15, inventario.buscar("Bebida 3L").getStock());
        assertEquals(1, inventario.getInventario().size());
    }

    @Test
    void venderDescuentaStock() {
        inventario.registrar("Bebida 3L", 10);
        assertTrue(inventario.vender("Bebida 3L", 4));
        assertEquals(6, inventario.buscar("Bebida 3L").getStock());
    }

    @Test
    void venderMasQueElStockEsRechazadoYNoDejaStockNegativo() {
        inventario.registrar("Bebida 3L", 3);
        assertFalse(inventario.vender("Bebida 3L", 4));
        assertEquals(3, inventario.buscar("Bebida 3L").getStock());
    }

    @Test
    void stockCriticoIncluyeSoloProductosConCincoOMenos() {
        inventario.registrar("Pan", 5);
        inventario.registrar("Leche", 6);
        inventario.registrar("Arroz", 2);
        assertEquals(2, inventario.getStockCritico().size());
    }
}
