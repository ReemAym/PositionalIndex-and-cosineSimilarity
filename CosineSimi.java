package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class CosineSimi {

    public int dot_product;
    public double doc_mag;
    public double query_mag;
    public double similarity;
    public double angle;

    public Set<String> all_words ;
    public ArrayList<Integer> word_freq_doc ;
    public ArrayList<Integer> word_freq_query ;
    public TreeMap<Double,ArrayList<String>>all_doc_similarity;

    public CosineSimi(){
         all_words = new HashSet<>();
         word_freq_doc = new ArrayList<>();
         word_freq_query = new ArrayList<>();
        all_doc_similarity= new TreeMap<>();
    }

    double get_similarity(){
        return similarity;
    }

    public int countlist(String[]array,String ele)
    {
        int c=0;
        for(int i=0;i<array.length;i++)
        {
            if(ele.equals(array[i])){
                c++;
            }
        }
        return c;
    }

    public int countArray(ArrayList<String> array,String ele)
    {
        int c=0;
        for(int i=0;i<array.size();i++)
        {
            if(ele.equals(array.get(i))){
                c++;
            }
        }
        return c;
    }

    public void calc_similarity()
    {
        similarity= dot_product/(query_mag*doc_mag);
        double rad= Math.acos(similarity);
        angle=rad*(7.0/22)*180;
        System.out.println("Cosine similarity: "+similarity);
        System.out.println("The angle: "+angle);
    }


    public void calculation()
    {
        double sum1=0;
        double sum2=0;
        dot_product=0;

        for (int i = 0; i < all_words.size(); i++)
        {
            sum1+=Math.pow(word_freq_query.get(i),2);
            sum2+=Math.pow(word_freq_doc.get(i),2);
            dot_product=dot_product+(word_freq_query.get(i)*word_freq_doc.get(i));
        }

        query_mag=Math.sqrt(sum1);
        doc_mag=Math.sqrt(sum2);

        calc_similarity();
    }


    public void create_vectors(String[] files,String query) {
        for (String doc : files) {
            //to carry all words in query uineqly
            String[] query_words = query.split("\\W+");
            Set<String> query_set = new HashSet<>(Arrays.asList(query_words));

            all_words.clear();
            ArrayList<String> doc_words = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader(doc))) {
                String line;
//            String[] lineWords = line.split("\\W+");

                Scanner reader = new Scanner(doc);

                while ((line = br.readLine()) != null) {
                    String[] lineWords = line.split("\\W+");
                    for (String word : lineWords) {
                        if (word != " ") {
                            doc_words.add(word);
                        }
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

            Set<String> doc_set = new HashSet<>(doc_words);

//            Set<String> all_words = new HashSet<>();
            all_words.addAll(query_set);
            all_words.addAll(doc_set);


            Iterator<String> iterator = all_words.iterator();//to iterate in allwords
            while (iterator.hasNext()) {

                String element = iterator.next();
                word_freq_query.add(countlist(query_words, element)); //count num appearance of element in query

                word_freq_doc.add(countArray(doc_words, element));

            }

            for (int i = 0; i < all_words.size(); i++) {
                System.out.print(word_freq_query.get(i) + " ");
            }

            System.out.println(" ");

            for (int i = 0; i < all_words.size(); i++) {
                System.out.print(word_freq_doc.get(i) + " ");
            }



            System.out.println(" ");


            Iterator<String> it = all_words.iterator();
            while (it.hasNext()) {
                String element = it.next();
                System.out.print(element+" ");

            }
            System.out.println(" ");
            calculation();

            if(!all_doc_similarity.containsKey(get_similarity()))
            {
                all_doc_similarity.put(get_similarity(),new ArrayList<>());
            }
            all_doc_similarity.get(get_similarity()).add(doc);

            System.out.println("*****************************************************");

        }
    }

    void display_doc_order()
    {
        System.out.println("------------------------------------------------------------------------");
        System.out.println("the order of document according to similarity ");


        for (Map.Entry<Double, ArrayList<String>> pair : all_doc_similarity.entrySet())
        {
              for(int i=0;i<pair.getValue().size();i++)
              {
                  System.out.println(pair.getValue().get(i));
              }
        }

    }


}


