package com.company;

import java.io.*;
import java.util.*;

public class Index {
    HashMap<String, Entry> index;
    ArrayList<PostingList> term_position;  //document's name amd positions list
    static Map<HashSet<Integer>,HashSet<Integer>> two_terms_positions=new HashMap<>();


    public Index() {
        term_position=new ArrayList<>();
    }

    public void search(String s) {
      boolean flag=false;

        for(Map.Entry<String,Entry> pair : index.entrySet())
        {
            if (pair.getKey().equals(s)) {
                flag=true;
                System.out.println("--The term is " + pair.getKey());
                System.out.println("Total frequency of term = " + pair.getValue().term_freq);
                System.out.println("Number of document = " + pair.getValue().doc_freq);

                System.out.println("Documents are: ");

                pair.getValue().pl.display();  //pl contain list of document's names and positions list

                term_position.add( pair.getValue().pl);

//                if(!term_position.containsKey(pair.getKey()))
//                {
//                    term_position.put(pair.getKey(),new ArrayList<>());
//                }
//                term_position.get(pair.getKey()).add(new Node(pair.getValue().pl,null));
//

                System.out.println(" ");
                System.out.println("---------------------------------------------------");
            }
        }

        if(!flag){
            System.out.println("THE TERM ENTERED IS NOT FOUND");
        }
    }


    public static ArrayList<String> intersect(PostingList p1, PostingList p2) {
        ArrayList<String> answer = new ArrayList<String>();
        ArrayList<String> final_doc = new ArrayList<String>();


        while (!p2.empty() && !p1.empty()) {

            int result = p1.get_head().word.compareTo(p2.get_head().word);

            if (result < 0)
            {
                p2.remove();
            }
            else if (result > 0)
            {
                p1.remove();
            }
            else
            {
                answer.add(p1.get_head().word);
                two_terms_positions.put(p1.get_head().positions,p2.get_head().positions);
                p1.remove();
                p2.remove();
            }

        }

        int docnum=0;
        for (Map.Entry<HashSet<Integer>, HashSet<Integer>> entry : two_terms_positions.entrySet())
        {

            System.out.println(entry.getKey()+" -> "+entry.getValue());

            ArrayList<Integer>first=new ArrayList<>();
            first.addAll(entry.getKey());

            ArrayList<Integer>second=new ArrayList<>();
            second.addAll(entry.getValue());


            for(int i: entry.getKey())
              {
                  for(int j:entry.getValue())
                  {
                      if(i+1==j)
                      {
                          final_doc.add(answer.get(docnum));
                          System.out.println(i+" ---- "+j);
                      }
                  }
              }

              docnum++;
        }




            return answer;
    }




    public void buildIndex(String[] files,String s) {
        index = new HashMap<String, Entry>();

        for (String doc : files) {
            try {
              //  int len=0;
                int j=0;
                File file=new File(doc);
                Scanner reader = new Scanner(file);
                while(reader.hasNextLine()) {
                  //  String[] words = reader.nextLine().split("\\W+");
                    String[] words = reader.nextLine().split(" ");

                    for (int i=0 ; i< words.length ; i++,j++) {
                        words[i] =  words[i].toLowerCase();
                        if (!index.containsKey( words[i])) {
                            index.put( words[i], new Entry());
                        }
                        if (!index.get( words[i]).pl.contain(doc)) {
                            index.get( words[i]).doc_freq += 1;
                            index.get( words[i]).pl.add(new Node(doc,null));
                        }
                        index.get( words[i]).term_freq += 1;
                        index.get( words[i]).pl.getNode().positions.add(j+1);
                    //    len+= words.length;
                    }
                }
            } catch (IOException e) {
                System.out.println("File " + doc + " not found. Skip it");
            }
        }
        search(s);
    }

     public void positional(String[] files,String sentence)
     {
         String[] terms = sentence.split("\\W+");
         for(int i=0;i< terms.length;i++)
         {
             buildIndex(files,terms[i]);

         }
     }

}
