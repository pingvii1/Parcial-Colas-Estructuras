package co.edu.unipiloto.estdatos.colas.interfaz;

import co.edu.unipiloto.estdatos.colas.mundo.*;
import co.edu.unipiloto.estdatos.colas.estructuras.ListaDobleEncadenada;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Interfaz de línea de comandos para el sistema McDonalds.
 * Cumple con el requerimiento de 12 opciones y el reto de múltiples productos.
 */
public class McDonaldsCLI {
    private AdministracionMcDonalds admin;
    private Scanner in;

    public McDonaldsCLI() {
        admin = new AdministracionMcDonalds();
        in = new Scanner(System.in);
    }

    public void mainMenu() throws InterruptedException {
        boolean finish = false;
        while (!finish) {
            System.out.println("\n------------------------------------------");
            System.out.println("-       MCDONALDS - SISTEMA DINÁMICO     -");
            System.out.println("------------------------------------------");
            System.out.println("1. Agregar cliente a la cola");
            System.out.println("2. Atender cliente (Venta Local - Múltiples productos)");
            System.out.println("3. Entregar pedido (Cocina)");
            System.out.println("4. Cantidad de clientes en cola");
            System.out.println("5. Ver cola de pedidos en cocina");
            System.out.println("6. Agregar domicilio"); 
            System.out.println("7. Despachar domicilio"); 
            System.out.println("8. Ver cantidad de domicilios"); 
            System.out.println("9. Ver catálogo de productos"); 
            System.out.println("10. Agregar producto al catálogo");
            System.out.println("11. Eliminar producto del catálogo"); 
            System.out.println("12. Salir");
            
            System.out.print("\nSeleccione una opción: ");
            int opt = in.nextInt(); in.nextLine();

            switch (opt) {
                case 1: agregarCliente(); break;
                case 2: atenderCliente(); break;
                case 3: entregarPedido(); break;
                case 4: System.out.println("Clientes esperando: " + admin.clientesEnFila()); break;
                case 5: verPedidosCocina(); break;
                case 6: gestionarDomicilio(); break;
                case 7: despacharDomicilio(); break;
                case 8: System.out.println("Domicilios pendientes: " + admin.domiciliosEnEspera()); break;
                case 9: verCatalogo(); break;
                case 10: agregarProducto(); break;
                case 11: eliminarProducto(); break;
                case 12: finish = true; break;
                default: System.out.println("Opción no válida.");
            }
        }
    }

    private void agregarCliente() {
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = in.nextLine();
        admin.agregarCliente(nombre);
        System.out.println("Cliente " + nombre + " agregado a la fila.");
    }

    private void atenderCliente() {
        if (admin.clientesEnFila() == 0) {
            System.out.println("No hay clientes en la fila para atender.");
            return;
        }

        Pedido nuevoPedido = new Pedido("Local", false);
        boolean agregando = true;

        while (agregando) {
            verCatalogo();
            System.out.print("Seleccione el ID del producto para agregar al pedido (0 para finalizar): ");
            int index = in.nextInt(); in.nextLine();

            if (index == 0) {
                agregando = false;
            } else {
                Producto p = buscarProductoPorIndex(index);
                if (p != null) {
                    nuevoPedido.agregarProductoAlPedido(p);
                    System.out.println("-> " + p.getNombre() + " agregado.");
                } else {
                    System.out.println("Producto no encontrado.");
                }
            }
        }

        if (!nuevoPedido.getProductos().isEmpty()) {
            String nombreCliente = admin.atenderCliente(nuevoPedido);
            System.out.println("Pedido registrado para: " + nombreCliente);
        } else {
            System.out.println("Pedido cancelado (sin productos).");
        }
    }

    private void entregarPedido() {
        System.out.println("Preparando pedido...");
        String resultado = admin.entregarPedido();
        if (resultado != null) {
            System.out.println("ENTREGADO: " + resultado);
        } else {
            System.out.println("No hay pedidos pendientes en la cocina.");
        }
    }

    private void verPedidosCocina() {
        System.out.println("\n--- PEDIDOS EN COCINA ---");
        Iterator<Pedido> it = admin.getIteratorPedidos();
        if (!it.hasNext()) System.out.println("Cocina vacía.");
        while (it.hasNext()) {
            Pedido p = it.next();
            System.out.print("- Pedido: ");
            for(Producto prod : p.getProductos()) System.out.print("[" + prod.getNombre() + "] ");
            System.out.println();
        }
    }

    private void gestionarDomicilio() {
        System.out.print("Ingrese dirección de entrega: ");
        String dir = in.nextLine();
        verCatalogo();
        System.out.print("Seleccione el producto para el domicilio: ");
        int index = in.nextInt(); in.nextLine();
        
        Producto p = buscarProductoPorIndex(index);
        if (p != null) {
            Pedido dom = new Pedido(dir, true);
            dom.agregarProductoAlPedido(p);
            admin.registrarDomicilio(dom);
            System.out.println("Domicilio registrado hacia: " + dir);
        }
    }

    private void despacharDomicilio() {
        System.out.println("Despachando domiciliario...");
        String res = admin.despacharDomicilio();
        if (res != null) {
            System.out.println("DESPACHADO: " + res);
        } else {
            System.out.println("No hay domicilios pendientes.");
        }
    }

    private void verCatalogo() {
        System.out.println("\n--- CATÁLOGO DE PRODUCTOS ---");
        int i = 1;
        for (Producto p : admin.getCatalogo()) {
            System.out.println(i + ". " + p.getNombre() + " | Precio: $" + p.getCosto() + " | Tiempo: " + p.getTiempoPreparacion() + "ms");
            i++;
        }
    }

    private void agregarProducto() {
        System.out.print("Nombre del nuevo producto: "); String n = in.nextLine();
        System.out.print("Tiempo de preparación (ms): "); long t = in.nextLong();
        System.out.print("Costo: "); double c = in.nextDouble();
        admin.agregarProductoAlCatalogo(n, t, c);
        System.out.println("Producto '" + n + "' añadido al menú.");
    }

    private void eliminarProducto() {
        System.out.print("Ingrese el nombre exacto del producto a eliminar: ");
        String nombre = in.nextLine();
        if (admin.eliminarProductoDelCatalogo(nombre)) {
            System.out.println("Producto eliminado exitosamente.");
        } else {
            System.out.println("No se encontró el producto.");
        }
    }

    private Producto buscarProductoPorIndex(int index) {
        int i = 1;
        for (Producto p : admin.getCatalogo()) {
            if (i == index) return p;
            i++;
        }
        return null;
    }
}