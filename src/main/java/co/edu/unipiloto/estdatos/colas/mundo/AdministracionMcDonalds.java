package co.edu.unipiloto.estdatos.colas.mundo;

import co.edu.unipiloto.estdatos.colas.estructuras.Cola;
import co.edu.unipiloto.estdatos.colas.estructuras.ListaDobleEncadenada;
import java.util.Iterator;

/**
 * Clase principal del mundo que administra la lógica del restaurante.
 * @author pingvii
 */
public class AdministracionMcDonalds {
    
    private Cola<String> colaClientes;
    private Cola<Pedido> colaPedidos;
    private Cola<Pedido> colaDomicilios;
    private ListaDobleEncadenada<Producto> catalogoProductos; 

    public AdministracionMcDonalds() {
        colaClientes = new Cola<>();
        colaPedidos = new Cola<>();
        colaDomicilios = new Cola<>();
        catalogoProductos = new ListaDobleEncadenada<>();
        inicializarCatalogo();
    }

    private void inicializarCatalogo() {
        // Datos iniciales requeridos por el taller
        catalogoProductos.addLast(new Producto("Combo Nuggets", 200, 15000));
        catalogoProductos.addLast(new Producto("Combo Big Mac", 300, 25000));
        catalogoProductos.addLast(new Producto("Combo Cuarto de Libra", 250, 22000));
        catalogoProductos.addLast(new Producto("Mc Flurry", 100, 8000));
    }

    // --- GESTIÓN DE CATÁLOGO (Requerimiento 2 del taller) ---
    
    public void agregarProductoAlCatalogo(String nombre, long tiempo, double costo) {
        catalogoProductos.addLast(new Producto(nombre, tiempo, costo));
    }

    public boolean eliminarProductoDelCatalogo(String nombre) {
        if (catalogoProductos.isEmpty()) return false;
        
        // Creamos una lista temporal para reconstruir el catalogo sin el producto eliminado
        ListaDobleEncadenada<Producto> nuevaLista = new ListaDobleEncadenada<>();
        boolean encontrado = false;
        
        for (Producto p : catalogoProductos) {
            if (p.getNombre().equalsIgnoreCase(nombre) && !encontrado) {
                encontrado = true; // Saltamos este producto (lo eliminamos)
            } else {
                nuevaLista.addLast(p);
            }
        }
        
        if (encontrado) {
            this.catalogoProductos = nuevaLista;
        }
        return encontrado;
    }

    // --- GESTION DE CLIENTES Y PEDIDOS ---

    public void agregarCliente(String nombre) {
        colaClientes.encolar(nombre);
    }

    public String atenderCliente(Pedido pedido) {
        if (colaClientes.estaVacio()) return "Nadie en fila";
        colaPedidos.encolar(pedido);
        return colaClientes.desencolar();
    }

    public String entregarPedido() {
        Pedido p = colaPedidos.desencolar();
        if (p == null) return null;
        
        try {
            // Reto: El tiempo de espera depende de la suma de todos los productos del pedido
            Thread.sleep(p.getTiempoTotal());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Retornamos el nombre del primer producto o una descripción
        return p.getProductos().first().getNombre() + (p.getProductos().size() > 1 ? " (y otros)" : "");
    }

    // --- GESTION DE DOMICILIOS (Requerimiento 1 del taller) ---

    public void registrarDomicilio(Pedido pedido) {
        colaDomicilios.encolar(pedido); 
    }

    public String despacharDomicilio() {
        Pedido p = colaDomicilios.desencolar();
        if (p == null) return null;
        
        try {
            Thread.sleep(p.getTiempoTotal());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Pedido para: " + p.getDireccion();
    }

    // --- METODOS DE CONSULTA ---
    
    public int clientesEnFila() { return colaClientes.tamanio(); }
    public int pedidosEnEspera() { return colaPedidos.tamanio(); }
    public int domiciliosEnEspera() { return colaDomicilios.tamanio(); }
    public ListaDobleEncadenada<Producto> getCatalogo() { return catalogoProductos; }
    public Iterator<Pedido> getIteratorPedidos() { return colaPedidos.iterator(); }
}