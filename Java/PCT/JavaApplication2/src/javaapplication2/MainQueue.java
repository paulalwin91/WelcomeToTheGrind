/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;
import javaapplication2.Queue;
/**
 *
 * @author Admin
 */
public class MainQueue {

    public static void main(String[] args) 
    {
        Queue queue = new Queue(1000);
           
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
       
        System.out.println(queue.dequeue() + 
                     " dequeued from queue\n");
       
        System.out.println("Front item is " + 
                               queue.front());
          
        System.out.println("Rear item is " + 
                                queue.rear());
    }
} 
