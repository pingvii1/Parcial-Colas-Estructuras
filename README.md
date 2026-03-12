# 🍟 Sistema de Gestión McDonalds - Estructuras de Datos

Este proyecto es una simulación integral de la operación de un restaurante McDonalds, desarrollada para el Laboratorio de Estructuras de Datos. Utiliza estructuras lineales personalizadas para gestionar flujos de clientes, pedidos en cocina y un sistema de domicilios.

## 🚀 Características Principales

### 1. Gestión de Colas (FIFO)
Se implementaron colas genéricas para tres flujos independientes:
* **Fila de Clientes:** Gestión de personas en espera de ser atendidas.
* **Cola de Cocina:** Pedidos locales pendientes de preparación.
* **Cola de Domicilios:** Gestión de despachos para pedidos externos (Requerimiento 2.1).

### 2. Catálogo Dinámico de Productos
A diferencia de un sistema estático, este proyecto utiliza una **Lista Doble Encadenada** para el catálogo, permitiendo:
* Agregar nuevos combos/productos en tiempo de ejecución.
* Eliminar productos existentes del menú.
* Navegación eficiente mediante iteradores personalizados.

### 3. Reto de Múltiples Productos (20% Adicional) 🏆
Se rediseñó la lógica de la clase `Pedido` para que cada cliente pueda solicitar **múltiples productos** en una sola orden. 
* **Estructura:** El pedido almacena los productos en una `ListaDobleEncadenada`.
* **Cálculo de Tiempo:** El tiempo de preparación en cocina se calcula dinámicamente sumando el tiempo de cada producto individual dentro del pedido.

## 🛠️ Estructuras de Datos Utilizadas

| Estructura | Uso en el Proyecto |
| :--- | :--- |
| **Cola Genérica** | Control de turnos y procesos de preparación/despacho. |
| **Lista Doble Encadenada** | Catálogo de productos y lista de ítems por pedido. |
| **Nodos Genéricos** | Bloques constructores para garantizar la flexibilidad de tipos. |

## 💻 Instalación y Ejecución

1. Clonar el repositorio.
2. Abrir el proyecto en **NetBeans IDE**.
3. Ejecutar la clase `Main.java` ubicada en el paquete `co.edu.unipiloto.estdatos.colas.interfaz`.
4. Seguir las instrucciones del menú interactivo (12 opciones).

## 👤 Autor
* **pingvii1** - *Estudiante de Ingeniería - Universidad Piloto de Colombia*
