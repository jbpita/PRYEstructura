/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs;

import TDAs.*;
import java.util.Iterator;

/**
 *
 * @author erwin
 */
public class LCDE<E> implements List<E> {

    private LCDEnodo<E> last;

    public LCDE() {
        this.last = null;

    }

    @Override
    public boolean addFirst(E e) {
        if (e == null) {
            return false;
        }
        LCDEnodo nuevo = new LCDEnodo(e);
        if (this.isEmpty()) {
            this.setLast((LCDEnodo<E>) nuevo);
            this.getLast().enlazar(nuevo);

            return true;
        } else {
            this.getLast().getNext().setPrevious(nuevo);
            nuevo.setNext(this.getLast().getNext());
            nuevo.setPrevious(this.getLast());
            this.getLast().setNext(nuevo);

            return true;

        }

    }

    @Override
    public boolean addLast(E e) {
        if (e == null) {
            return false;
        }
        LCDEnodo nuevo = new LCDEnodo(e);
        if (this.isEmpty()) {
            this.setLast((LCDEnodo<E>) nuevo);
            this.getLast().enlazar(nuevo);
            return true;
        } else {
            this.getLast().getNext().setPrevious(nuevo);
            nuevo.setNext(this.getLast().getNext());
            this.getLast().setNext(nuevo);
            nuevo.setPrevious(this.getLast());

            this.setLast((LCDEnodo<E>) nuevo);
            return true;
        }

    }

    @Override
    public E removeFirst() {
        LCDEnodo<E> removed = this.getLast().getNext();
        this.getLast().getNext().getNext().setPrevious(this.getLast());
        this.getLast().setNext(this.getLast().getNext().getNext());

        return removed.getContent();
    }

    @Override
    public E removeLast() {
        LCDEnodo<E> removed = this.getLast();
        this.getLast().getPrevious().setNext(this.getLast().getNext());
        this.getLast().getNext().setPrevious(this.getLast().getPrevious());
        this.setLast(this.getLast().getPrevious());
        return removed.getContent();
    }

    @Override
    public int size() {
        if (this.isEmpty()) {
            return 0;
        }
        if (this.getLast() == this.getLast().getNext()) {
            return 1;
        } else {
            int contador = 1;
            LCDEnodo<E> n = this.getLast().getNext();

            for (n = this.getLast().getNext().getNext(); n != this.getLast().getNext(); n = n.getNext()) {
                contador = contador + 1;
            }
            return contador;
        }

    }

    @Override
    public boolean isEmpty() {
        return this.getLast() == null;
    }

    @Override
    public void clear() {
        this.setLast(null);
    }

    @Override
    public void add(int index, E element) {
        if (element == null) {
            System.out.println("No se puede añadir nulos");
        } else if (index > this.size() || index < 0) {
            System.out.println("Indice incorrecto");
        } else if (index == 0) {
            this.addFirst(element);
        } else if (index == this.size()) {
            this.addLast(element);
        } else {
            int contador = 1;
            LCDEnodo<E> n = this.getLast().getNext();
            LCDEnodo<E> nuevo = new LCDEnodo(element);
            while (n.getNext() != this.getLast().getNext()) {
                if (contador == index) {
                    nuevo.setNext(n.getNext());
                    n.getNext().setPrevious(nuevo);
                    nuevo.setPrevious(n);
                    n.setNext(nuevo);
                    break;

                }
                contador++;
                n = n.getNext();

            }

        }

    }

    @Override
    public E remove(int index) {
        if (index > this.size() - 1 || index < 0) {
            System.out.println("Indice incorrecto");
        } else if (index == 0) {
            return this.removeFirst();
        } else if (index == this.size() - 1) {
            return this.removeLast();
        } else {
            int contador = 0;
            LCDEnodo<E> n = this.getLast().getNext();

            while (n.getNext() != this.getLast().getNext()) {
                if (contador == index) {
                    n.getPrevious().setNext(n.getNext());
                    n.getNext().setPrevious(n.getPrevious());
                    return n.getContent();

                }
                contador++;
                n = n.getNext();

            }

        }
        return null;

    }

    @Override
    public E get(int index) {
        if (index > this.size() - 1 || index < 0) {
            System.out.println("Indice incorrecto");
        } else if (index == 0) {
            return this.getLast().getNext().getContent();
        } else if (index == this.size() - 1) {
            return this.getLast().getContent();
        } else {
            int contador = 0;
            LCDEnodo<E> n = this.getLast().getNext();

            while (n.getNext() != this.getLast().getNext()) {
                if (contador == index) {
                    return n.getContent();

                }
                contador++;
                n = n.getNext();

            }

        }
        return null;
    }

    @Override
    public E set(int index, E element) {
        LCDEnodo<E> nodo = new LCDEnodo(this.get(index));
         if (index > this.size() - 1 || index < 0) {
            System.out.println("Indice incorrecto");
        } else if (index == 0) {
            this.getLast().getNext().setContent(element);
            return nodo.getContent();
        } else if (index == this.size() - 1) {
            this.getLast().setContent(element);
            return nodo.getContent();
        } else {
            int contador = 0;
            LCDEnodo<E> n = this.getLast().getNext();

            while (n.getNext() != this.getLast().getNext()) {
                if (contador == index) {
                   n.setContent(element);
                   return nodo.getContent();

                }
                contador++;
                n = n.getNext();

            }

        }
         return null;
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void moverAtras(){
        this.setLast(this.getLast().getNext());
    }
    public void  moverAdelante(){
        this.setLast(this.getLast().getPrevious());
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return null;
        }
        String s = "[";

        LCDEnodo<E> n = this.getLast().getNext();

//        for ( LCDEnodo<E>  n = this.last.getNext(); n.getNext()!= this.last.getNext(); n = n.getNext()) {
//            s = s + n.getContent().toString() + ",";
//        }
        while (n.getNext() != this.getLast().getNext()) {
            s = s + n.getContent() + ",";
            n = n.getNext();
        }
        s = s + n.getContent() + "]";

        return s;

    }

    /**
     * @return the last
     */
    public LCDEnodo<E> getLast() {
        return last;
    }

    /**
     * @param last the last to set
     */
    public void setLast(LCDEnodo<E> last) {
        this.last = last;
    }

}
