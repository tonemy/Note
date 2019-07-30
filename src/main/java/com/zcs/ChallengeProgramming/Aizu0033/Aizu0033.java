package com.zcs.ChallengeProgramming.Aizu0033;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Created by 张超帅 on 2019/7/29.
 */
public class Aizu0033 {
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            Stack<Integer> st = new Stack<Integer>();
            int[] num = new int[10];
            for (int j = 0; j < 10; j++) {
                num[j] = sc.nextInt();
            }
            int top = num[0];
            for (int j = 1; j < 10; j++) {
                if (num[j] > top) {
                    //   System.out.println(top);
                    top = num[j];
                } else {
                    st.push(num[j]);
                }
            }
            boolean res = true;
            int top0 = 0;
            if (!st.isEmpty()) top0 = st.pop();
            // System.out.println(top0);
            while (!st.isEmpty()) {
                if (top0 < st.peek()) {
                    res = false;
                    break;
                }
                top0 = st.peek();
                st.pop();
            }
            if (res) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}

class FastScanner {
    private final InputStream in = System.in;
    private final byte[] buffer = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;

    private boolean hasNextByte() {
        if (ptr < buflen) {
            return true;
        } else {
            ptr = 0;
            try {
                buflen = in.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (buflen <= 0) {
                return false;
            }
        }
        return true;
    }

    private int readByte() {
        if (hasNextByte()) return buffer[ptr++];
        else return -1;
    }

    private static boolean isPrintableChar(int c) {
        return 33 <= c && c <= 126;
    }

    private void skipUnprintable() {
        while (hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;
    }

    public boolean hasNext() {
        skipUnprintable();
        return hasNextByte();
    }

    public String next() {
        if (!hasNext()) throw new NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while (isPrintableChar(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    public long nextLong() {
        if (!hasNext()) throw new NoSuchElementException();
        long n = 0;
        boolean minus = false;
        int b = readByte();
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        if (b < '0' || '9' < b) {
            throw new NumberFormatException();
        }
        while (true) {
            if ('0' <= b && b <= '9') {
                n *= 10;
                n += b - '0';
            } else if (b == -1 || !isPrintableChar(b)) {
                return minus ? -n : n;
            } else {
                throw new NumberFormatException();
            }
            b = readByte();
        }
    }

    public int nextInt() {
        return (int) nextLong();
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }
}