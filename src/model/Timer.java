package model;

public class Timer { 
    Acao acao;   
    final double limite;  
    final boolean repete;
    double tempo = 0.0; 
    boolean fim = false;
    
    public Timer(double limite, boolean repete, Acao acao) {
        this.limite = limite; 
        this.acao = acao; 
        this.repete = repete; 
    }
    

    public void tique(double dt) {
        
        if(this.fim) return;
        this.tempo += dt;
        
        if(this.tempo >= limite) {
            this.acao.executa();//método que adiciona mais um cano no jogo
            
            if(repete) 
                this.tempo= 0.0;
            else
                this.fim = true;
        }
    }
}


/* 
import java.util.*;
  
public class GFG {
    public static void main(String[] args)
    {
        // Create an empty hash map
        HashMap<String, Integer> map
            = new HashMap<>();
  
        // Add elements to the map
        map.put("vishal", 10);
        map.put("sachin", 30);
        map.put("vaibhav", 20);
  
        // Finding the Set of keys from
        // the HashMap
        Set<String> keySet = map.keySet();
  
        // Creating an ArrayList of keys
        // by passing the keySet
        ArrayList<String> listOfKeys = new ArrayList<String>(keySet);
  
        // Getting Collection of values from HashMap
        Collection<Integer> values = map.values();
  
        // Creating an ArrayList of values
        ArrayList<Integer> listOfValues
            = new ArrayList<>(values);
  
        System.out.println("The Keys of the Map are "
                           + listOfKeys);
  
        System.out.println("The Values of the Map are "
                           + listOfValues);
    }
}
*/
