/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapImplementation;

import java.util.*;

/**
 *
 * @author amnwaqar
 */

public class LinkedHashMapWithChaining<K,V> implements Map<K,V> {
    private final double loadLimit;
    private int size;
    private ArrayList<HashNode<K, V> > hashTable;
    private int firstIndex;
    private int capacity;

    public LinkedHashMapWithChaining() {
        hashTable = new ArrayList<>();
        loadLimit = 0.75;
        size = 0;
        capacity = 20;
        
        for (int i = 0; i < capacity; i++)
        {
            hashTable.add(null);
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++)
        {
            hashTable.set(i, null);
        }
        size = 0;
    }

    @Override
    public boolean containsKey(Object key) {
        int index = getIndex((K)key);
        HashNode<K,V> head = hashTable.get(index);
        
        while (head != null)
        {
            if (head.getKey().equals(key))
            {
                return true;
            }
            head = head.getNext();
        }
        
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < capacity; i++)
        {
            HashNode<K,V> head = hashTable.get(i);
            
            while (head != null)
            {
                if (head.getValue().equals(value))
                {
                    return true;
                }
                head = head.getNext();
            }
        }
        return false;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entrySet = new LinkedHashSet<>();
        
        if (!isEmpty())
        {
            HashNode<K,V> current = hashTable.get(firstIndex);
            
            while (current.getPointsTo() != null)
            {
                entrySet.add(current);
                current = current.getPointsTo();
            }
            entrySet.add(current);
        }
        
        return entrySet;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LinkedHashMapWithChaining<?, ?> other = (LinkedHashMapWithChaining<?, ?>) obj;
        return true;
    }
    
    @Override
    public V get(Object key) {
        int index = getIndex((K)key);
        HashNode<K,V> head = hashTable.get(index);
        
        
        while (head != null)
        {
            if (head.getKey().equals(key))
            {
                return head.getValue();
            }
            head = head.getNext();
        }
        return null;
    }
    
    @Override
    public int hashCode(){
        return Objects.hashCode(this);
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }
    
    @Override
    public Set<K> keySet() {
        Set<K> keySet = new LinkedHashSet<>();
        
        if (!isEmpty())
        {
            HashNode<K,V> current = hashTable.get(firstIndex);
            
            keySet.add(current.getKey());
            
            while (current.getPointsTo() != null)
            {
                current = current.getPointsTo();
                keySet.add(current.getKey());
            }
        }
        
        return keySet;
    }
    
    @Override
    public V put(K key, V value) {
        int index = getIndex(key);
        HashNode<K,V> node = new HashNode(key, value);
        HashNode<K,V> head = hashTable.get(index);
        
        if (!isEmpty())
        {
            HashNode<K,V> current = hashTable.get(firstIndex);
            
            while (current.getPointsTo() != null)
            {
                current = current.getPointsTo();
            }
            
            current.setPointsTo(node);
            node.setPreviousPointer(current);
        }
        else
        {
            firstIndex = index;
        }
        
        if (head != null)
        {
            while (head.hasNext())
            {
                if (head.getKey().equals(key))
                {
                    return head.setValue(value);
                }

                head = head.getNext();
            }
            
            head.setNext(node);
            node.setPrevious(head);
            //hashTable.set(index, node);
        }
        else
        {
            hashTable.set(index, node);
        }
        size++;
        
        if (loadFactor() > loadLimit)
        {
            expandCapacity();
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> t) {
       Set<Entry<? extends K, ? extends V>> set = new LinkedHashSet<>();
       set.addAll(t.entrySet());
       
       Iterator<Entry<? extends K, ? extends V>> iterator = set.iterator();
       
       while (iterator.hasNext())
       {
           Entry<? extends K, ? extends V> entry = iterator.next();
           put(entry.getKey(), entry.getValue());
       }
    }
    
    @Override
    public V remove(Object key) {
        int index = getIndex((K)key);
        
        HashNode<K,V> head = hashTable.get(index);
        
        while (head != null)
        {
            HashNode<K,V> first = null;
            
            if (head.getPrevious() == null)
            {
               first = head;
            }
            
            if (head.getKey().equals(key))
            {
                if (head.getPreviousPointer() != null)
                {
                    head.getPreviousPointer().setPointsTo(head.getPointsTo());
                    
                    if (head.getPointsTo() != null)
                    {
                        head.getPointsTo().setPreviousPointer(head.getPreviousPointer());
                    }
                }
                else
                {
                    if (head.getPointsTo() != null)
                    {
                        head.getPointsTo().setPreviousPointer(null);
                    }
                }
                
                if (head.getPrevious() != null)
                {
                    head.getPrevious().setNext(head.getNext());
                    
                    if (head.getNext() != null)
                    {
                        head.getNext().setPrevious(head.getPrevious());
                    }
                    //hashTable.set(index, first);
                }
                else
                {
                    if (head.getNext() != null)
                    {
                        head.getNext().setPrevious(null);
                        hashTable.set(index, head.getNext());
                    }
                    else
                    {
                        hashTable.set(index, null);
                    }
                }
                size--;
                return head.getValue();
            }
            head = head.getNext();
        }
        
        throw new NullPointerException("Key " +key+ " does not exist in map!");
    }
    
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();
        
        if (!isEmpty())
        {
            HashNode<K,V> current = hashTable.get(firstIndex);
            
            while (current.getPointsTo() != null)
            {
                values.add(current.getValue());
                current = current.getPointsTo();
            }
            values.add(current.getValue());
        }
        
        return values;
    }

    public void expandCapacity()
    {
        int newCapacity = capacity * 2;
        size = 0;
        ArrayList<HashNode<K, V> > temp = hashTable;
        hashTable = new ArrayList<>();
        
        for (int i = 0; i < newCapacity; i++)
        {
            hashTable.add(null);
        }
        
        for (int i = 0; i < capacity; i++)
        {
            HashNode<K,V> current = temp.get(i);
            
            while (current != null)
            {
                HashNode<K,V> node = current;
                put(node.getKey(), node.getValue());
                current = node.getNext();
            }
        }
        
        capacity = newCapacity;
    }
    
    private final int hashCode (K key) {
        return Objects.hashCode(key);
    }
    
    public double loadFactor() {
        return (1.0 * size)/capacity;
    }
    
    public int getIndex(K key)
    {
        int hashCode = hashCode(key);
        int index = hashCode % capacity;
        
        if (index < 0)
        {
            index = index * -1;
        }
        return index;
    }
    
    @Override
    public String toString() {
        String text = "{";
        
        if (!isEmpty())
        {
            HashNode<K,V> current = hashTable.get(firstIndex);
            
            while (current.getPointsTo() != null)
            {
                text += current;
                current = current.getPointsTo();
                text += ", ";
            }
            text += current;
        }
        
        text += "}";
       
        return text;
    }
}
