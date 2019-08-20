package com.zcs.ComputerBasicSynthesis.DS.ChallengeProgramming.Poj2393;

/**
 * Created by 张超帅 on 2019/8/20.
 */
import java.io.*;
import java.util.*;
import java.math.*;
public class Main {
    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner(System.in);
        FastWriter out = new FastWriter(System.out);
        int n = in.nextInt();
        int s = in.nextInt();
        int[] c = new int[n];
        int[] y = new int[n];
        long res = 0;
        for(int i = 0; i < n; i ++) {
            c[i] = in.nextInt();
            y[i] = in.nextInt();
        }
        for (int i = 1; i < n; i ++) {
            c[i] = Math.min(c[i], c[i - 1] + s);
        }
        for(int i = 0; i < n; i ++) {
            res += c[i] * y[i];
        }
        out.println(res);
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