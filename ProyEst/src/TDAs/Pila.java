/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs ;

import java.util.Deque;
import java.util.Iterator;

/**
 *
 * @author erwin
 */
public class Pila<E>  {
    
    private ArrayList<E> content;
    private int iTop;
    

    public Pila( ) {
        ArrayList<E> array = new ArrayList<>();
        this.content = array;
        this.iTop=-1;
        
       
    }
    public void push(E element){
        if(element==null){
            System.out.println("No se reciben valores nulos");
        }else{
            this.content.addLast(element);
            this.iTop++;
        }
        
    }
    public E pop(){
        if(this.content.isEmpty()){
        return null;
        }
        this.iTop--;
        return this.content.removeLast();
        
    }
    public E peek(){
        if(this.content.isEmpty()){
        return null;
        }
        return this.content.get(iTop);
        
    }
    

   public Iterator<E> iterator(){
   
       return this.content.iterator();
   
   
   }

    @Override
    public String toString() {
        return this.content.toString();
    }
    
    
    
    
    
}
