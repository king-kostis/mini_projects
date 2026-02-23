package org.example.projects.linked_list;

class Node {
    int data;
    Node next;

    Node(int data){
        this.data = data;
    }
}

class Main{
    private static int countNodes(Node head){
        int count = 1;
        Node current = head;

        while (current.next != null){
            current = current.next;
            count++;
        }
        return count;
    }

    public static void main(String[] args){
        Node head = new Node(6);
        Node nodeB = new Node(7);
        Node nodeC = new Node(8);
        Node nodeD = new Node(9);
        Node nodeE = new Node(10);

        head.next = nodeB;
        nodeB.next = nodeC;
        nodeC.next = nodeD;
        nodeD.next = nodeE;

        System.out.println(countNodes(head));
    }
}