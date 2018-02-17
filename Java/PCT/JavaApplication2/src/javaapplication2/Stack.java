/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;
import java.util.Scanner;

/**
 *
 * @author Admin
 */




public class Stack {
int top = -1;
int[] inputArray;
//    static int ArraySize;
    
    public Stack()
    {
     inputArray = new int[100];
     for(int i =0; i< inputArray.length; i++)
         inputArray[i] = -1;
    
     
    }
    
    public static void main(String[] args)
    {
//    System.out.println("Enter the size of the array");
//    Scanner sc = new Scanner(System.in);    
//    ArraySize = sc.nextInt();
//    inputArray = new int[ArraySize];
//    
//    
//    System.out.println();
//    
//    System.out.println("Enter the " + ArraySize + "elements");
//    
//    for(int i = 0;i <ArraySize;i++)
//    {
//       inputArray[i] = sc.nextInt();
//    }
//    
//    System.out.println("Following are the elements");
//    
//    for(int i = 0;i <ArraySize;i++)
//    {
//        System.out.print(inputArray[i] + " "); 
//    }
    
        Stack stck = new Stack();
        
        stck.push(1);
                
        stck.push(2);
                
        stck.push(3);
                
        stck.push(4);        
        stck.push(5);        
        stck.push(6);        
        stck.push(7);        
        stck.push(8);        
        stck.push(9);        
        stck.push(10);
        stck.pop();
        stck.pop();
                
        stck.push(0);        
        stck.push(0);        
        stck.push(0);        
        stck.push(0);
        
        
    
    
        stck.printarray();
    }
    
    
    public void printarray()
    {
        for(int i=0; i<inputArray.length; i++)
        {   
            if(inputArray[i]!= -1)
                System.out.print(inputArray[i] + " ");
        }
    }
    
    public void push(int element)
    {
     if(top <= inputArray.length - 1)
     {
         top++;
         inputArray[top] = element;
         System.out.println("pushed element " + element);
     }
       else
         System.out.println("Push failed as reached the last element, top " + top + " element" + element);       
    }
    
    public void pop()
    {
    if(top != -1)
    {
      System.out.println("popped " + inputArray[top--]);
    }
        else
     System.out.println("Cannot pop and reached the bottom");
   }
}
