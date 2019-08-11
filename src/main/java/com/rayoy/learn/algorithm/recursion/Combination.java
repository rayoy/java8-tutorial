package com.rayoy.learn.algorithm.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combination {

    public static void main(String[] args) {
        Combination comb = new Combination();
        comb.combinations(new ArrayList<>(), Arrays.asList(1, 2, 3, 4, 5, 6, 7), 3);
        System.out.println("===========>");
        comb.combinations(new ArrayList<>(), Arrays.asList(1, 2, 3, 4), 0);
        System.out.println("===========>");
        comb.combinations(new ArrayList<>(), new ArrayList<>(), 2);
    }

    /**
     * @description: Generates all combinations and output them
     * select n elements from data
     * @param: [source, n]
     * @return: void
     */
    public void combinations(List<Integer> selected, List<Integer> data, int n) {
        //init value for recursion
        //how to select elements
        //how to output

        if (n == 0) {
            for (Integer integer : selected) {
                System.out.print(integer);
                System.out.print("  ");
            }
            System.out.println();
            return;
        }

        if (data.size() == 0) {
            return;
        }
        //select element 0
        selected.add(data.get(0));
        combinations(selected, data.subList(1, data.size()), n - 1);

        //un-select element 0
        selected.remove(selected.size() - 1);
        combinations(selected, data.subList(1, data.size()), n);

    }
}