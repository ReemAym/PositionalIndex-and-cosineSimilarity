package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String[] files={"doc1.txt","doc2.txt","doc3.txt","doc4.txt","doc5.txt","doc6.txt","doc7.txt","doc8.txt","doc9.txt","doc10.txt"};

//        Scanner sc= new Scanner(System.in);
//        System.out.print("Enter a string: ");
//        String word= sc.next();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String sentence = scanner.nextLine();

        Index ind=new Index();
        ind.positional(files,sentence);

        System.out.println(ind.intersect(ind.term_position.get(0),ind.term_position.get(1)));

//        ind.buildIndex(files,"intelligence");
//        ind.buildIndex(files,word);


//        CosineSimi ss=new CosineSimi();
//        ss.create_vectors(files ,"sara reem information web");
//
//        ss.display_doc_order();

//        PostingList pp =new PostingList(new Node("aa",null));
//        pp.add(new Node("bb",null));
//        pp.remove();
//        System.out.println(pp.get_head().word);

    }
}
