/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs;

/**
 *
 * @author PC
 */
public class LCDEnodo<E>{
    
    
    private E content;
    private LCDEnodo<E> next;
    private LCDEnodo<E> previous;
    
    public LCDEnodo(E content){
        this.content =content;
        this.next = null;
        this.previous = null;
    }

    /**
     * @return the content
     */
    public E getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(E content) {
        this.content = content;
    }

    /**
     * @return the next
     */
    public LCDEnodo<E> getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(LCDEnodo<E> next) {
        this.next = next;
    }

    /**
     * @return the previous
     */
    public LCDEnodo<E> getPrevious() {
        return previous;
    }

    /**
     * @param previus the previus to set
     */
    public void setPrevious(LCDEnodo<E> previous) {
        this.previous = previous;
    }
    public void enlazar (LCDEnodo<E> nuevo){
        nuevo.setPrevious(this);
        this.setNext(nuevo);
        
        
    }
    
    
}
