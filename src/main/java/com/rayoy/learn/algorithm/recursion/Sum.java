package com.rayoy.learn.algorithm.recursion;

public class Sum {
    int sum(int n) {
        if (n == 1) {
            return 1;
        }
        return sum(n - 1) + n;
    }

    public static void main(String[] args) {
        Sum sum = new Sum();
        System.out.println(sum.sum(10));
    }
}
