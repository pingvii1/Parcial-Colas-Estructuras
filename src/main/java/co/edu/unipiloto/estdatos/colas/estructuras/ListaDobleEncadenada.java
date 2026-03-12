package co.edu.unipiloto.estdatos.colas.estructuras;

import java.util.Iterator;

/**
 * @author pingvii
 * @param <E>
 */
public class ListaDobleEncadenada<E> implements Iterable<E> {

    private NodoLista<E> primero;
    private NodoLista<E> ultimo;
    private int tamanio;

    public int size() {
        return tamanio;
    }

    public boolean isEmpty() {
        return tamanio == 0;
    }

    public void addFirst(E element) {
        NodoLista<E> nuevo = new NodoLista<>(element);
        if (isEmpty()) {
            primero = ultimo = nuevo;
        } else {
            nuevo.setNext(primero);
            primero.setPrev(nuevo);
            primero = nuevo;
        }
        tamanio++;
    }

    public void addLast(E element) {
        NodoLista<E> nuevo = new NodoLista<>(element);
        if (isEmpty()) {
            primero = ultimo = nuevo;
        } else {
            nuevo.setPrev(ultimo);
            ultimo.setNext(nuevo);
            ultimo = nuevo;
        }
        tamanio++;
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        E element = primero.getElement();
        primero = primero.getNext();
        if (primero != null) {
            primero.setPrev(null);
        } else {
            ultimo = null;
        }
        tamanio--;
        return element;
    }

    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        E element = ultimo.getElement();
        ultimo = ultimo.getPrev();
        if (ultimo != null) {
            ultimo.setNext(null);
        } else {
            primero = null;
        }
        tamanio--;
        return element;
    }

    public E first() {
        return (primero != null) ? primero.getElement() : null;
    }

    public E last() {
        return (ultimo != null) ? ultimo.getElement() : null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private NodoLista<E> actual = primero;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public E next() {
                E data = actual.getElement();
                actual = actual.getNext();
                return data;
            }
        };
    }
}
