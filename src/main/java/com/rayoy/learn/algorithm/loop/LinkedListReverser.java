package com.rayoy.learn.algorithm.loop;

import com.rayoy.learn.algorithm.common.Node;
import com.rayoy.learn.algorithm.recursion.LinkedListCreator;

public class LinkedListReverser {

    public static void main(String[] args) {

        LinkedListCreator creator = new LinkedListCreator();
        LinkedListReverser reverser = new LinkedListReverser();
        Node.printLinkedList(
                reverser.linkedListReverser(creator.createLargeLinkedList(5)));
        Node.printLinkedList(
                reverser.linkedListReverser(creator.createLargeLinkedList(0)));
        Node.printLinkedList(
                reverser.linkedListReverser(creator.createLargeLinkedList(100)));

        reverser.linkedListReverser(creator.createLargeLinkedList(1000000));
        System.out.println("done");
    }

    /**
     * @param: []
     * @return: void
     */
    public Node linkedListReverser(Node head) {
        Node newHead = null;
        Node currentHead = head;

        //loop Invariant
        //newHead points to the linkedList already reversed
        //currentHead points to the linkedList not yet reversed
        while (currentHead != null) {
            //loop invariant holds
            Node next = currentHead.getNext();
            currentHead.setNext(newHead);
            newHead = currentHead;
            currentHead = next;

        }
        return newHead;

    }
}