/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unipiloto.estdatos.colas.mundo;

import co.edu.unipiloto.estdatos.colas.estructuras.ListaDobleEncadenada;
import java.util.Date;

/**
 *
 * @author pingvii
 */
public class Pedido {
    private Date horaPedido;
    private ListaDobleEncadenada<Producto> productos; 
    private String direccion;
    private boolean esDomicilio; 

    public Pedido(String direccion, boolean esDomicilio) {
        this.horaPedido = new Date();
        this.productos = new ListaDobleEncadenada<>();
        this.direccion = direccion;
        this.esDomicilio = esDomicilio;
    }

    public void agregarProductoAlPedido(Producto p) {
        this.productos.addLast(p);
    }

    public ListaDobleEncadenada<Producto> getProductos() {
        return productos;
    }

    public String getDireccion() {
        return direccion;
    }

    public boolean isEsDomicilio() {
        return esDomicilio;
    }

    public long getTiempoTotal() {
        long total = 0;
        for (Producto p : productos) {
            total += p.getTiempoPreparacion();
        }
        return total;
    }
}
