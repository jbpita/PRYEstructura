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
 * @param <E>
 */
public class ArrayList<E> implements List<E> {

    private E[] elements = null;
    private int BaseCapacity = 100;
    private int EffectiveSize = 0;

    private boolean ArrayFull() {
        return this.elements.length == this.EffectiveSize;
    }

    private void addCapacity() {
        this.BaseCapacity = this.BaseCapacity * 2;
        E[] temp = (E[]) (new Object[this.BaseCapacity]);
        for (int i = 0; i < this.EffectiveSize; i++) {
            temp[i] = this.elements[i];

        }
        this.elements = temp;
    }

    public void iterarArray() {
        for (int i = 0; i < this.size(); i++) {
            System.out.println(this.elements[i]);
        }
    }

    public ArrayList() {
        this.elements = (E[]) (new Object[BaseCapacity]);
        this.EffectiveSize = 0;
    }

    @Override
    public boolean addFirst(E e) {
        if (e == null) {
            return false;
        }

        if (this.ArrayFull()) {
            this.addCapacity();
        }
        E[] temp = (E[]) new Object[this.BaseCapacity];
        for (int i = 0; i < this.size() + 1; i++) {
            if (i > 0) {
                temp[i] = this.elements[i - 1];
            }
        }
        temp[0] = e;
        this.elements = temp;
        this.EffectiveSize++;
        return true;
    }

    @Override
    public boolean addLast(E e) {
        if (e == null) {
            return false;
        }
        if (this.ArrayFull()) {
            this.addCapacity();
        }
        int indx = this.size();
        this.elements[indx] = e;

        this.EffectiveSize++;
        return true;
    }

    @Override
    public E removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        E eliminado = this.elements[0];
        E[] temp = (E[]) new Object[this.BaseCapacity];
        for (int i = 0; i < this.size(); i++) {
            if (i > 0) {
                temp[i - 1] = this.elements[i];
            }
        }

        this.elements = temp;
        this.EffectiveSize--;

        return eliminado;

    }

    @Override
    public E removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        int indx = this.size() - 1;
        E valor = this.elements[indx];
        this.elements[indx] = null;
        this.EffectiveSize--;
        return valor;
    }

    @Override
    public int size() {
        return this.EffectiveSize;

    }

    @Override
    public boolean isEmpty() {
        return this.EffectiveSize == 0;
    }

    @Override
    public void clear() {
        ArrayList<E> limpio = new ArrayList();
        this.BaseCapacity = limpio.BaseCapacity;
        this.EffectiveSize = limpio.EffectiveSize;
        this.elements = limpio.elements;
    }

    @Override
    public String toString() {
        String text = "[";
        for (int i = 0; i < this.size(); i++) {
            if (i == this.size() - 1) {
                text = text + this.elements[i].toString() + "]";
            } else {
                text = text + this.elements[i].toString() + ",";
            }
        }
        return text;
    }

    @Override
    public void add(int index, E element) {
        if (element == null) {
            System.out.println("No se puede agregar nulos");
        } else {
            if (this.ArrayFull()) {
                this.addCapacity();
            }
            E temp = this.elements[index];
            for (int i = index; i < this.EffectiveSize + 1; i++) {
                if (i >= index + 1) {
                    //this.elements[i]=temp;
                    temp = this.set(i, temp);
                }
            }
            this.elements[index] = element;
            this.EffectiveSize++;
        }

    }

    @Override
    public E remove(int index) {
        if (index > this.EffectiveSize || index < 0) {
            System.out.println("Error índice fuera de rango");
            return null;
        } else {
            E value = this.elements[index];

            for (int i = index + 1; i < this.EffectiveSize; i++) {
                this.elements[i - 1] = this.elements[i];
            }
            this.EffectiveSize--;
            return value;
        }
    }
 
    @Override
    public E get(int index) {
        if (index > this.EffectiveSize || index < 0) {
            System.out.println("Error índice fuera de rango");
            return null;
        } else {
            E value = this.elements[index];

            return value;
        }
    }

    @Override
    public E set(int index, E element) {
        if (element == null) {
            return null;
        }
        if (index > this.EffectiveSize || index < 0) {
            System.out.println("Error índice fuera de rango");
            return null;
        } else {
            E value = this.elements[index];
            this.elements[index] = element;
            return value;
        }
    }

    @Override
    public Iterator<E> iterator() {
        int size= this.size();
        
        Iterator<E> it =new Iterator(){
          int index=0;
            @Override
            public boolean hasNext() {
                if(index>size-1){
                return false;
                }
                
                return true;
                
            }

            @Override
            public E next() {
                E value = elements[index];
                index++;
                return value;
                 
            }
            
          
        
        };
        
        
        return it;
    }

 
}
