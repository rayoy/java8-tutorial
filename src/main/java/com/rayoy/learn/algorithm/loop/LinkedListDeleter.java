package com.rayoy.learn.algorithm.loop;

import java.util.ArrayList;
import java.util.Arrays;

import com.rayoy.learn.algorithm.common.Node;
import com.rayoy.learn.algorithm.recursion.LinkedListCreator;

public class LinkedListDeleter {

    public static void main(String[] args) {
        LinkedListDeleter linkedListDeleter = new LinkedListDeleter();
        LinkedListCreator creator = new LinkedListCreator();
        Node.printLinkedList(
                linkedListDeleter.deleteIfEquals(creator.createLinkedList(Arrays.asList(1, 2, 3, 2, 5)), 2));
        Node.printLinkedList(
                linkedListDeleter.deleteIfEquals(creator.createLinkedList(Arrays.asList(2, 2, 3)), 2));
        Node.printLinkedList(
                linkedListDeleter.deleteIfEquals(null, 2));
        Node.printLinkedList(
                linkedListDeleter.deleteIfEquals(creator.createLinkedList(Arrays.asList(2, 2, 2, 2)), 2));
        Node.printLinkedList(
                linkedListDeleter.deleteIfEquals(creator.createLinkedList(new ArrayList<>()), 2));
    }

    public Node deleteIfEquals(Node head, int value) {
        Node prev = head;
        // Loop invariant:
        // list nodes from head up to prev has been processed
        // (Nodes with values equals to value are deleted)

        while (head != null && head.getValue() == value) {
            head = head.getNext();
        }

        if (head == null) {
            return null;
        }

        while (prev.getNext() != null) {

            if (prev.getNext().getValue() == value) {
                // delete it
                prev.setNext(prev.getNext().getNext());
            } else {
                prev = prev.getNext();
            }

        }
        return head;
    }

}