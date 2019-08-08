package com.zcs.ChallengeProgramming.Poj3069;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by 张超帅 on 2019/8/8.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        FastWriter out = new FastWriter(System.out);
        while (sc.hasNext()) {
            int r = sc.nextInt();
            int n = sc.nextInt();
            int res = 0;
            if(r == -1 && n == -1) {
                break;
            }
            int[] num = new int[n];

            for (int i = 0; i < n; i ++) {
                num[i] = sc.nextInt();
            }
            Arrays.sort(num);
            int index = 0;
            while (index < n) {
                int start = num[index];
                while (index < n &&  num[index] <= start + r) {
                    index ++;
                }
                start = num[index - 1];
                while (index <n &&  num[index] <= start + r) {
                    index ++;
                }
                res ++;
            }
            out.println(res);
        }
    }
}
class  FastScanner  implements Closeable {
    private BufferedReader reader;
    private StringTokenizer tokenizer;
    public FastScanner(InputStream inputStream) {
        reader = new BufferedReader(new InputStreamReader(inputStream));
        tokenizer = new StringTokenizer("");
    }
    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException ex) {
            return null;
        }
    }
    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }
    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }
    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }
    public int nextInt() {
        return Integer.valueOf(next());
    }
    public double nextDouble() {
        return Double.valueOf(next());
    }
    public BigInteger nextBigInteger() {
        return new BigInteger(next());
    }
    public void close() throws IOException {
        reader.close();
    }
}
class FastWriter implements Closeable {
    private BufferedWriter writer;
    public FastWriter(OutputStream outputStream) {
        writer = new BufferedWriter(new OutputStreamWriter(outputStream));
    }
    public void print(Object object) throws IOException {
        writer.write(object.toString());
        writer.flush();
    }
    public void println(Object object) throws IOException {
        writer.write(object.toString());
        writer.write("\n");
        writer.flush();
    }
    public void close() throws IOException {
        writer.close();
    }
}