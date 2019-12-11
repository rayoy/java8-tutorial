package com.rayoy.learn.algorithm.google;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BeautifulNumber {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        long cases = in.nextLong();
        for (int i = 1; i <= cases; i++) {
            long n = in.nextLong();
            System.out.println("Case #" + i + ": " + beautiful(n));
        }

    }

    private static long beautiful(long n) {
        //从二进制开始试
        for (long radix = 2; radix < n; radix++) {
            // if n is 11 radix,return radix
            if (conformBeautiful(n, radix)) {
                return radix;
            }

        }
        //    return n - 1;
        throw new IllegalStateException("should not reach here");

    }

    private static boolean conformBeautiful(long n, long radix) {
        while (n > 0) {
            long bit = n % radix;
            if (bit != 1) {
                return false;
            }
            n /= radix;
        }
        return true;
    }

}