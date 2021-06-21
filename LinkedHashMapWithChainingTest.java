/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapImplementation;

/**
 *
 * @author amnwaqar
 */
public class LinkedHashMapWithChainingTest {
    
    public static void main(String[] args) {
        //10 and 50 are stored in the same index
        //1 and 61 are stored in the same index
        
        LinkedHashMapWithChaining<Integer,String> numbers = new LinkedHashMapWithChaining();
        LinkedHashMapWithChaining<Integer,String> numbers2 = new LinkedHashMapWithChaining();
        
        numbers.put(1, "ONE");
        numbers.put(2, "TWO");
        numbers.put(3, "THREE");
        numbers.put(10, "TEN");
        numbers.put(20, "TWENTY");
        System.out.println("\nHash Map is empty? " + numbers.isEmpty() +"\n");
        
        System.out.println(numbers + "\n");
        System.out.println("... REMOVING " +numbers.remove(20) +" ...\n");
        System.out.println(numbers + "\n");
        System.out.println("... REMOVING " +numbers.remove(3) +" ...\n");
        System.out.println(numbers + "\n");
        
        
        numbers.put(50, "FIFTY");
        System.out.println(numbers + "\n");
        
        System.out.println("10 is stored at index: " +numbers.getIndex(10));
        System.out.println("50 is stored at index: " +numbers.getIndex(50));
        
        numbers2.put(15, "FIFTEEN");
        numbers2.put(40, "FORTY");
        
        System.out.println("40 is stored at index: " +numbers.getIndex(40));
        System.out.println("15 is stored at index: " +numbers.getIndex(15));
        
        numbers.putAll(numbers2);
        System.out.println("\n... ADDING NEW MAP TO EXISTING MAP ...\n");
        System.out.println("ArrayList of values:\n" +numbers.values() + "\n");
        System.out.println("Set of keys:\n" +numbers.keySet()+ "\n");
        System.out.println("Set of entries:\n" +numbers.entrySet()+ "\n");
        
        System.out.println("Number of items in Hash Map: " +numbers.size() + "\n");
        
        System.out.println("15 is spelled as: " +numbers.get(15));
        System.out.println("10 is spelled as: " +numbers.get(10));
        System.out.println("50 is spelled as: " +numbers.get(50));
        System.out.println("1 is spelled as: " +numbers.get(1) + "\n");
        
        numbers.put(61, "SIXTY ONE");
        System.out.println("61 is stored at index: " +numbers.getIndex(61));
        System.out.println("1 is stored at index: " +numbers.getIndex(1));
        System.out.println();
        System.out.println(numbers + "\n");
        
        System.out.println("Is key 3 in the Hash Map? " + numbers.containsKey(3));
        System.out.println("Is key 61 in the Hash Map? " + numbers.containsKey(61));
        System.out.println("Is value TWENTY in the Hash Map? " + numbers.containsValue("TWENTY"));
        System.out.println("Is key FIFTEEN in the Hash Map? " + numbers.containsValue("FIFTEEN"));
        
        
        //stored at same index, 10 is head
        System.out.println("\n... REMOVING " +numbers.remove(10) +" ...\n");
        System.out.println("Is key 10 in the Hash Map? " + numbers.containsKey(10));
        System.out.println("50 is spelled as: " +numbers.get(50));
        
        //stored at same index, 1 is head
        System.out.println("\n... REMOVING " +numbers.remove(61) +" ...\n");
        System.out.println("Is key 61 in the Hash Map? " + numbers.containsKey(61));
        System.out.println("1 is spelled as: " +numbers.get(1));
        
        System.out.println("\n... CLEARING HASH MAP ...");
        numbers.clear();
        System.out.println("\nNumber of items in Hash Map: " +numbers.size());
        System.out.println("Hash Map is empty? " + numbers.isEmpty());
        System.out.println(numbers);
        
        numbers.put(8, "EIGHT");
        System.out.println("\nNumber of items in Hash Map: " +numbers.size());
        System.out.println("Hash Map is empty? " + numbers.isEmpty());
        System.out.println(numbers);
    }
}
