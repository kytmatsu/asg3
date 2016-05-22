/*
 * Kyle Matsumoto
 * kytmatsu
 * CMPS012B
 * 11/21/14
 * The queue class that holds the implementor as well.
 * */
import java.util.Iterator; 
import java.util.NoSuchElementException; 
 
 
 class Queue <items> implements Iterable <items> { 

    private class node 
    { 
       items item; 
       node link; 
    } 
    
    
    private node head = null; 
    private node tail = null; 
 
 
    public boolean isempty () 
    { 
       return (head == null); 
    } 
 
 
    public void insert (items newitem) 
    { 
        
       node temp = new node(); 
       temp.item = newitem; 
       temp.link = null; 
 
 
       if(head == null)
       { 
          head = temp; 
          tail = temp; 
       }
       else
       { 
          tail.link = temp; 
          tail = temp; 
       } 
    } 
 
 
    
    
    public Iterator <items> iterator ()
    { 
       return new iterate (); 
    }  
    
    class iterate implements Iterator <items> 
    { 
       node next = head; 
       
       
       public boolean hasNext () 
       { 
          return next != null; 
       }
        
       public items next () { 
          if (! hasNext ())
           throw new NoSuchElementException (); 
          
          items result = next.item; 
          next = next.link; 
          return result; 
       } 
       
       public void remove () 
       { 
          throw new UnsupportedOperationException (); 
       } 
    } 
 
 } 
 
