package com.zcs.ComputerBasicSynthesis.DS.ChallengeProgramming.Poj3187;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by 张超帅 on 2019/8/4.
 */
public class Main {
    static boolean flag = false;
    static int s = 0;
    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner(System.in);
        FastWriter out = new FastWriter(System.out);
        int n = in.nextInt();
        s = in.nextInt();
        int[] num = new int[n];
        int[] num1 = new int[n];
        boolean[] vis = new boolean[n];
        for(int i = 0; i < n; i ++) {
            num[i] = i + 1;
        }
        dfs(num, num1, vis, 0);

    }
    public static void dfs(int[] num, int[] num1, boolean[] vis, int index) {
        if(index == num.length) {
            if (!flag && check(num1, s)){
                for (int i = 0; i < num1.length; i ++) {
                    if(i != num1.length)
                        System.out.print(num1[i] + " ");
                    else
                        System.out.println(num1[i]);
                }
                flag = true;
            }
           // System.out.println(Arrays.toString(num1));
        }
        for (int i = 0; i < num.length; i ++) {
            if(!vis[i]) {
                num1[index] = num[i];
                vis[i] = true;
                dfs(num, num1, vis, index + 1);
                vis[i] = false;
            }
        }
    }
//    public static void  dfs(int[] num, int start, int end) {
//        if(start == end) {
//            if (!flag && check(num, s)){
//                for (int i = 0; i < num.length; i ++) {
//                    if(i != num.length)
//                        System.out.print(num[i] + " ");
//                    else
//                        System.out.println(num[i]);
//                }
//                flag = true;
//            }
//            System.out.println(Arrays.toString(num));
//            return;
//        }
//        for(int i = start; i <= end; i ++) {
//            swap(num, i, start);
//            dfs(num, start + 1, end);
//            swap(num, i, start);
//        }
//    }
    public static void swap(int[] num, int i, int j) {
        int tmp = num[i];
        num[i] = num[j];
        num[j] = tmp;
    }
    public static boolean check(int[]num ,int s) {

        int[] cop = Arrays.copyOf(num, num.length);

        int len = num.length;
        while (len > 1) {
            for (int i = 0; i < len - 1; i++) {
                cop[i] = cop[i] + cop[i + 1];
            }
            len --;
        }
      //  System.out.println(cop[0]);
        if(s == cop[0]) {
            return true;
        }
        return false;
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
