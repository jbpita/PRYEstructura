/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs;


import java.util.Iterator;


/**
 *
 * @author erwin
 */
public class LinkedList<E> implements List<E> {

    private Node<E> header;
    private Node<E> last;

    public LinkedList() {
        this.header = null;
        this.last = null;
    }

    @Override
    public boolean addFirst(E e) {
        if (e == null) {
            System.out.println("No se pueden recibir nulos");
            return false;
        }
        Node<E> n = new Node(e);
        if (this.header == null) {
            this.header = n;
            this.last = n;
            return true;
        } else {
            n.setNext(this.header);
            this.header = n;
            return true;
        }

    }

    @Override
    public boolean addLast(E e) {
        if (e == null) {
            System.out.println("No se pueden recibir nulos");
            return false;
        }
        Node<E> n = new Node(e);
        if (this.header == null) {
            this.header = n;
            this.last = n;
            return true;
        } else {
            this.last.setNext(n);
            this.last = n;
            return true;
        }
    }

    @Override
    public E removeFirst() {
        if (this.header == null) {
            System.out.println("La lista está Vacia");
            return null;
        }

        if (this.header == this.last) {
            Node<E> removed = this.header;
            this.header = null;
            this.last = null;
            return removed.getContent();
        } else {
            Node<E> removed = this.header;
            this.header = this.header.getNext();
            return removed.getContent();
        }

    }

    @Override
    public E removeLast() {
        Node<E> removed = null;
        if (this.header == null) {
            return null;
        }
        if (this.header == this.last) {
            removed = this.header;
            this.header = null;
            this.last = null;
            return removed.getContent();

        }
        for (Node<E> n = this.header; n != null; n = n.getNext()) {

            if (n.getNext() == this.last) {
                removed = this.last;
                this.last = n;
            }

        }
        return removed.getContent();
    }

    @Override
    public int size() {
        int size = 0;
        for (Node<E> n = this.header; n != null; n = n.getNext()) {
            size++;
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (this.header == null) {
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        this.header = null;
        this.last = null;
    }

    @Override
    public void add(int index, E element) {
        if (index > this.size() || index < 0) {
            System.out.println("Índice fuera de rango");

        } else if (element == null) {
            System.out.println("No se puede agregar elementos nulos");
        } else {
            if (index == 0) {
                this.addFirst(element);
            } else {
                Node<E> no = new Node(element);
                int i = 0;
                for (Node<E> n = this.header; n != null; n = n.getNext()) {
                    if (i + 1 == index) {
                        no.setNext(n.getNext());
                        n.setNext(no);
                        break;
                    }
                    i++;

                }

            }

        }

    }

    @Override
    public E remove(int index) {
        if (index > this.size() || index < 0) {
            System.out.println("Índice fuera de rango");
            return null;
        } else {
            if (index == 0) {
              E retur=this.removeFirst();
                return retur;

            } 
            if (index==this.size()-1) {
                E retur = this.removeLast();
                        return retur; 
            } else {
                Node<E> no ;
                int i = 0;
                for (Node<E> n = this.header; n != null; n = n.getNext()) {
                    if (i + 1 == index) {
                        n.setNext(n.getNext().getNext());
                        return n.getNext().getContent();
                        
                    }
                    i++;

                }

            }
        }
        return null;
    }

    @Override
    public E get(int index) {
        if (index > this.size() || index < 0) {
            System.out.println("Índice fuera de rango");
            return null;
        } else {
            int i = 0;
            for (Node<E> n = this.header; n != null; n = n.getNext()) {
                if (i == index) {
                    return n.getContent();
                }
                i++;
            }
        }
        return null;
    }

    @Override
    public E set(int index, E element) {
           if (index > this.size() || index < 0) {
            System.out.println("Índice fuera de rango");
            return null;
        } else {
            int i = 0;
            for (Node<E> n = this.header; n != null; n = n.getNext()) {
                if (i == index) {
                    E value = n.getContent();
                    n.setContent(element);
                    return value;
                }
                i++;
            }
        }
        return null;
    }
    

    @Override
    public String toString() {
        String Value = "[";
        if (this.header == null) {
            return null;
        }
        for (Node<E> n = this.header; n != this.last; n = n.getNext()) {
            Value = Value + n.getContent().toString() + ",";
        }
        Value = Value + this.last.getContent().toString() + "]";
        return Value;

    }

    @Override
    public Iterator<E> iterator() {
        
        Iterator<E> it =new Iterator(){
            Node<E> node =header;
            @Override
            public boolean hasNext() {
                if(node==null){
                  
                    return false;
                }else{
                   
                return true;
                }
                
                }

            @Override
            public E next() {
                E value = node.getContent();
                node=node.getNext();
                return value;
            }
        
        };
        return it;
    }

}
