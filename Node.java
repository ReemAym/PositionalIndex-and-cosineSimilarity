package com.company;


import java.util.HashSet;

public class Node {
   public String word;
   public HashSet<Integer> positions;
 //   Doc_and_Position term;
    Node next;

    public Node(String word,Node next){
        positions=new HashSet<Integer>();
        this.word=word;
        this.next=next;
    }

    public void displayPositions(){
        System.out.println("Positions: ");
        for(int pos:positions)
        {
            System.out.print(pos+", ");
        }
        System.out.println(" ");
    }
}
