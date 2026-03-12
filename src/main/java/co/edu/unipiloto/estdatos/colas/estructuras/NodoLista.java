/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unipiloto.estdatos.colas.estructuras;

/**
 *
 * @author pingvii
 */
public class NodoLista<E> {

    private E elemento;
    private NodoLista<E> siguiente;
    private NodoLista<E> anterior;

    public NodoLista(E elemento) {
        this.elemento = elemento;
    }

    public E getElement() {
        return elemento;
    }

    public void setElement(E element) {
        this.elemento = element;
    }

    public NodoLista<E> getNext() {
        return siguiente;
    }

    public void setNext(NodoLista<E> next) {
        this.siguiente = next;
    }

    public NodoLista<E> getPrev() {
        return anterior;
    }

    public void setPrev(NodoLista<E> prev) {
        this.anterior = prev;
    }
}
