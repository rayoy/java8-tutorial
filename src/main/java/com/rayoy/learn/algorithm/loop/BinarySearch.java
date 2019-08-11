package com.rayoy.learn.algorithm.loop;

public class BinarySearch {

    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        System.out.println(bs.binarySearch(new int[] {1, 2, 3}, 2));
        System.out.println(bs.binarySearch(new int[] {1, 2, 3, 4, 6, 9}, 3));
        System.out.println(bs.binarySearch(new int[] {1, 2, 3, 4, 20, 200}, -5));
        System.out.println(bs.binarySearch(new int[] {1, 2, 3, 4, 20, 200}, 203));
        System.out.println(bs.binarySearch(new int[] {1, 2, 3, 4, 20, 200}, 5));
        System.out.println(bs.binarySearch(new int[] {1}, 5));
        System.out.println(bs.binarySearch(new int[] {5}, 5));
        System.out.println(bs.binarySearch(new int[] {1, 200}, 5));
        System.out.println(bs.binarySearch(new int[] {1, 200}, 1));
        System.out.println(bs.binarySearch(new int[] {1, 200}, 200));
    }

    public int binarySearch(int[] arr, int key) {
        int min = 0;
        int max = arr.length;

        // Loop invariant: [ min, max) is a valid range. ( min <= max )
        // key may only be within range [a, b).
        while (min < max) {
            // notice overflow!! min + max
            // int m = min + (max - min) / 2;
            int m = min + ((max - min) >>> 1);
            if (key < arr[m]) { // [min,max) + [max,c) = [min,c)
                // max - min = len([min,max))
                // [min,min) ==> empty range
                max = m;
            } else if (key > arr[m]) {
                min = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }
}