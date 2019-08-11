package com.rayoy.learn.algorithm.recursion;

import java.util.ArrayList;
import java.util.Arrays;

import com.rayoy.learn.algorithm.common.Node;

public class LinkedListReverser {

    public static void main(String[] args) {
        LinkedListCreator creator = new LinkedListCreator();
        LinkedListReverser reverser = new LinkedListReverser();
        Node.printLinkedList(reverser.reverseLinkedList(
                creator.createLinkedList(new ArrayList<>())));
        Node.printLinkedList(reverser.reverseLinkedList(
                creator.createLinkedList(Arrays.asList(1))));
        Node.printLinkedList(reverser.reverseLinkedList(
                creator.createLinkedList(Arrays.asList(1, 2, 3, 4, 5))));

    }

    /**
     * @description: reverses a linked list
     * @param: [head] the linked list to reverse
     * @return: com.hao.interview.common.Node head of the reversed linked list
     */
    public Node reverseLinkedList(Node head) {
        //size == 0
        if (head == null) {
            return null;
        }
        //size == 1
        if (head.getNext() == null) {
            return head;
        }

        Node newHead = reverseLinkedList(head.getNext());
        //第二个节点的下一跳指向头
        head.getNext().setNext(head);
        head.setNext(null);
        return newHead;

    }

}