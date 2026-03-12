package co.edu.unipiloto.estdatos.colas.mundo;

/**
 * Representa un producto individual del catalogo de McDonalds.
 *
 * @author pingvii
 */
public class Producto {

    private String nombre;
    private long tiempoPreparacion;
    private double costo;

    public Producto(String nombre, long tiempoPreparacion, double costo) {
        this.nombre = nombre;
        this.tiempoPreparacion = tiempoPreparacion;
        this.costo = costo;
    }

    public String getNombre() {
        return nombre;
    }

    public long getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public double getCosto() {
        return costo;
    }
}
