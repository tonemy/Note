package com.zcs.ChallengeProgramming.Aizu0525;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * Created by 张超帅 on 2019/8/7.
 */
public class Main {
    public static int res = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        FastWriter out = new FastWriter(System.out);
        while (sc.hasNext()) {
            res = Integer.MIN_VALUE;
            int r = sc.nextInt();
            int c = sc.nextInt();
            if (r == 0 && c == 0) break;
            int[][] sta = new int[r][c];
            for (int i = 0; i < r; i ++) {
                for(int j = 0; j < c; j ++) {
                    sta[i][j] = sc.nextInt();
                }
            }
            dfs(sta, 0);
            out.println(res);
        }
        return;
    }
    public static void dfs(int[][] sta, int r) {

        int sum =  0;
        for (int j = 0; j < sta[0].length; j ++) {
            int count = 0;
            for (int i = 0; i < sta.length; i ++) {
                count += sta[i][j];
            }
           sum += Math.max(count, sta.length - count);
        }
        if(sum > res) {
            res = sum;
        }
        if(r == sta.length) {
            return;
        }
        dfs(sta, r + 1);
        for(int j = 0; j < sta[0].length; j ++) {
            if (sta[r][j] == 0)sta[r][j] = 1;
            else sta[r][j] = 0;
        }
        dfs(sta, r + 1);
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
