/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Graphs {

     LinkedList<Integer> adjcyList[];
    public Graphs(int vertices)
    {
      adjcyList = new LinkedList[vertices];
      
      
      for(int i=0; i<vertices; i++)
      {
         adjcyList[i] = new LinkedList<Integer>();
      } 
    }
    
    public void addGraph(Graphs grp, int start, int end)
    {
     
        
         adjcyList[start].addLast(end);
        
        
    }
    public void printGraph(Graphs grp, int start, int end)
    {
       for(int i=0; i< adjcyList.length; i++)
       {
          System.out.print(i + " ->" );
          
          for(int j = 0; j< adjcyList[i].size() ; j++)
          {
             System.out.print(adjcyList[i].get(j) + " ->");
          
          }
                        System.out.println("\\");
                        
  
       }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Graphs grph = new Graphs(4);
        
        grph.addGraph(grph, 0, 1);
        grph.addGraph(grph, 0, 2);
        grph.addGraph(grph, 1, 0);
        grph.addGraph(grph, 1, 2);
        grph.addGraph(grph, 1, 3);
        grph.addGraph(grph, 2, 0);
        grph.addGraph(grph, 2, 1);
        grph.addGraph(grph, 2, 3);
        grph.addGraph(grph, 3, 1);
        grph.addGraph(grph, 3, 2);
        
        grph.printGraph(grph, 0, 0);
        
    }
    
}
