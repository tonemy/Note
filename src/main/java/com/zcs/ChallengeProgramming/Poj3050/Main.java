package com.zcs.ChallengeProgramming.Poj3050;

import edu.princeton.cs.algs4.In;

import java.io.*;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Created by 张超帅 on 2019/8/7.
 */
public class Main {
    public static int[][] next = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        FastWriter out = new FastWriter(System.out);
        int[][] num = new int[5][5];
        for(int i = 0; i < 5; i ++) {
            for (int j = 0; j < 5; j ++) {
                num[i][j] = sc.nextInt();
            }
        }
        Set<String> set = new HashSet<String>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 5; i ++) {
            for (int j = 0; j < 5; j ++) {
                sb.append(num[i][j]);
                dfs(num,i, j, 0, sb, set);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        out.println(set.size());
    }
    public static void dfs(int[][] num, int x, int y, int step,  StringBuilder sb, Set<String> set) {
        if (step == 5) {
            set.add(sb.toString());
            //System.out.println(sb.toString());
            return;
        }
        for(int k = 0; k < 4; k ++) {
            int nx = next[k][0] + x;
            int ny = next[k][1] + y;
            if(nx >= 0 && nx < num.length && ny >= 0 && ny < num[0].length ) {
                sb.append(num[nx][ny]);
                dfs(num, nx, ny, step + 1, sb, set);
                sb.deleteCharAt(sb.length() - 1);
            }
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