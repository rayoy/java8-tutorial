package com.rayoy.learn.algorithm.google;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BeautifulNumberLarge {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        Long cases = in.nextLong();
        for (int i = 1; i <= cases; i++) {
            long n = in.nextLong();
            System.out.println("Case #" + i + ": " + beautiful(n));
        }

    }

    private static long beautiful(long n) {
        for (int bits = 64; bits >= 2; bits--) {
            long radix = getRadix(n, bits);
            if (radix != -1) {
                return radix;
            }
        }
        //should not reach here
        //return n - 1;
        throw new IllegalStateException("should not reach here");
    }

    /**
     * @description: Get Radix so that n is 111...1(bits 1 in total) in that radix
     * @param: [n, bits]
     * @return: long   the radix. -1 if there.s no such radix.
     */
    private static long getRadix(long n, int bits) {
        long minRadix = 2;
        long maxRadix = n;
        while (minRadix < maxRadix) {
            long mid = minRadix + (maxRadix - minRadix) / 2;

            long sum = convert(mid, bits);
            if (sum == n) {
                return mid;
            } else if (sum < n) {
                minRadix = mid + 1;
            } else {
                maxRadix = mid;
                ;
            }
        }
        return -1;
    }

    private static long convert(long radix, int bits) {
        long sum = 0;
        long component = 1;
        for (int i = 0; i < bits; i++) {
            if (Long.MAX_VALUE - sum < component) {
                return Long.MAX_VALUE;
            } else {
                sum += component;
            }

            if (Long.MAX_VALUE / component < radix) {
                component = Long.MAX_VALUE;
            } else {
                component *= radix;
            }
        }
        return sum;

    }

}