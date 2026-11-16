# Almacén - Gestión de inventario (Actividad 2.3)

Aplicación de consola en Java con ArrayList y pruebas JUnit 5.

## Estructura
- `Producto`: nombre y stock.
- `Inventario`: gestiona `ArrayList<Producto>` (registrar, vender, inventario, stock crítico).
- `Main`: menú de consola.
- `InventarioTest`: pruebas JUnit.

## Ejecutar
```
mvn test
mvn compile exec:java -Dexec.mainClass=almacen.Main
```
(También puedes abrirlo en IntelliJ/Eclipse/VS Code y ejecutar `Main` y `InventarioTest`.)
