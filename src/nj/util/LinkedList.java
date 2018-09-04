package nj.util;

public class LinkedList {

    static public class Node{
        public int value;
        public Node prev;
        public Node next;

        public Node(int value,Node prev,Node next){
            this.value=value;
            this.prev=prev;
            this.next=next;
        }
    }

    public Node head=null;
    public Node tail=null;

    public void add(int value){
        Node curr=null;

        if(tail==null){
            curr=new Node(value,null,null);
            head=curr;
            tail=curr;
        }else{
            curr=new Node(value,tail,null);
            tail.next=curr;
            tail=curr;
        }
    }

    public void delete(Node node){

        if(node==null)
            return;
        if(head==tail){
            head=null;
            tail=null;
        }
        if(node==head){
            head=node.next;
            head.prev=null;
        }else if(node==tail){
            tail=node.prev;
            tail.next=null;
        }else{
            node.prev.next=node.next;
            node.next.prev=node.prev;
        }
    }
}
