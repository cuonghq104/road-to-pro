package hash;

import java.util.Scanner;

public class SUBSTR {

    static int BASE = 31;
    static long[] basePows;
    static long[] hashes;

    static String txtA;
    static String txtB;
    static final long MOD = 10000003;

    static long getHash(int l, int r) {
        long res =  (hashes[r + 1] - hashes[l] * basePows[r - l + 1] + MOD * MOD) % MOD;
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        txtA = sc.next();
        txtB = sc.next();
        init();

        long hashB = 0;
        for (int i = 0; i < txtB.length(); i++) {
            hashB = (hashB * BASE + txtB.charAt(i) - 'a' + 1) % MOD;
        }

        for (int i = 0; i < txtA.length() - txtB.length() + 1; i++) {
            if (hashB == getHash(i, i + txtB.length() - 1)) {
                System.out.print((i + 1) + " ");
            }
        }
    }

    private static void init() {
        int aLength = txtA.length();

        basePows = new long[aLength + 1];

        basePows[0] = 1;
        for (int i = 0; i < aLength; i++) {
            basePows[i + 1] = (basePows[i] * BASE) % MOD;
        }

        hashes = new long[aLength + 1];

        hashes[0] = 0;
        for (int i = 0; i < aLength; i++) {
            hashes[i + 1] = (hashes[i] * BASE + txtA.charAt(i) - 'a' + 1) % MOD;
        }
    }
}
