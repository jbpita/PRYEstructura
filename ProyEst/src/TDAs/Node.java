/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs;

/**
 *
 * @author erwin
 */
public class Node<E> {
    private E content;
    private Node<E> next;
    public Node(E content) {
        this.content = content;
        this.next = null;
    }

    public E getContent() {
        return content;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }
    

    
    
    
    
}
