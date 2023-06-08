package com.company;


public class PostingList {
     Node head;

     public Node getNode(){
         return this.head;
     }

    public PostingList(Node head){
        this.head=head;
    }

    public void add(Node new_node){
        new_node.next=head;
        head=new_node;
    }


//    public void add_to_end(Node new_node) {
////        Node newNode = new Node(data, set);
//
//        if (head == null) {
//            head = new_node;
//            return;
//        }
//
//        Node current = head;
//        while (current.next != null) {
//            current = current.next;
//        }
//        current.next = new_node;
//    }


    public boolean contain(String s){
        Node n=head;

        while(n!=null){
            if(n.word==s)
            {
                return true;
            }
            n=n.next;
        }
        return false;
    }

    public Node get_head(){
        return head;
    }

    public boolean empty(){
         Node n=head;
         if(n!=null){
             return false;
         }
         return true;
    }

    public void remove(){
        Node temp = head;
        head = head.next;
        temp.next = null;
    }

    public void display() {
        Node n=head;
        while(n!=null)
        {
            System.out.println(n.word);
            n.displayPositions();
            n=n.next;
        }
    }
}
