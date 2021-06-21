/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapImplementation;

import java.util.Map;
import java.util.Objects;

/**
 *
 * @author amnwaqar
 */
public class HashNode<K,V> implements Map.Entry<K,V>{
    private final K key;
    private V value;
    private HashNode<K,V> next, previous, pointsTo, previousPointer;

    HashNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = this.previous = null;
        this.pointsTo  = this.previousPointer = null;
    }

    @Override
    public final K getKey(){ 
        return key; 
    }
        
    @Override
    public final V getValue(){ 
        return value; 
    }
    
    @Override
    public final V setValue(V newValue) {
        V oldValue = value;
        value = newValue;
        return oldValue;
    }

    public HashNode<K, V> getNext() {
        return next;
    }

    public void setNext(HashNode<K, V> next) {
        this.next = next;
    }
    
    public HashNode<K, V> getPrevious() {
        return previous;
    }
    
    public void setPrevious(HashNode<K, V> previous) {
        this.previous = previous;
    }
    
    public HashNode<K, V> getPointsTo() {
        return pointsTo;
    }

    public void setPointsTo(HashNode<K, V> pointsTo) {
        this.pointsTo = pointsTo;
    }

    public HashNode<K, V> getPreviousPointer() {
        return previousPointer;
    }

    public void setPreviousPointer(HashNode<K, V> previousPointer) {
        this.previousPointer = previousPointer;
    }
    
    public boolean hasNext()
    {
        return (next != null);
    }
        
    @Override
    public final String toString(){ 
        return key + "=" + value; 
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.key);
        hash = 97 * hash + Objects.hashCode(this.value);
        return hash;
    }

    @Override
    public final boolean equals(Object o) {
        if (o == this)
            return true;
        if (o instanceof Map.Entry) {
            Map.Entry<?,?> e = (Map.Entry<?,?>)o;
            if (Objects.equals(key, e.getKey()) && Objects.equals(value, e.getValue()))
                return true;
        }
        return false;
    }
}

