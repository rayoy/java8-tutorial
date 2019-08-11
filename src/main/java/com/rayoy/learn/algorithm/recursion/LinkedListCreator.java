package com.rayoy.learn.algorithm.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.rayoy.learn.algorithm.common.Node;

/**
 * create LinkedList.
 */
public class LinkedListCreator {

    /**
     * Creates a linked list.
     *
     * @param: [data]   the data to create the list
     * @return: 1. head of the linked list.
     * 2. The returned linked list ends with last node with getNext() == null
     */
    public Node createLinkedList(List<Integer> data) {
        if (data.isEmpty()) {
            return null;
        }
        Node firstNode = new Node(data.get(0));

        // 去掉第一号元素后剩下的所有元素
        Node headOfSublist = createLinkedList(data.subList(1, data.size()));
        firstNode.setNext(headOfSublist);
        return firstNode;
    }

    /**
     * Creates large linked list.
     *
     * @param: [size]
     */
    public Node createLargeLinkedList(int size) {

        Node prev = null;
        Node head = null;

        for (int i = 0; i <= size; i++) {
            Node node = new Node(i);
            if (prev != null) {
                prev.setNext(node);
            } else {
                head = node;
            }
            prev = node;
        }
        return head;
    }

    public static void main(String[] args) {
        LinkedListCreator creator = new LinkedListCreator();
        Node.printLinkedList(
                creator.createLinkedList(new ArrayList<>()));
        Node.printLinkedList(
                creator.createLinkedList(Arrays.asList(1)));
        Node.printLinkedList(
                creator.createLinkedList(Arrays.asList(1, 2, 3, 4, 5)));
        Node.printLinkedList(
                creator.createLargeLinkedList(100));
    }
}