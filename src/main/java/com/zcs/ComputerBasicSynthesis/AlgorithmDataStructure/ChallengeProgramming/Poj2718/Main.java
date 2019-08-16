package com.zcs.ComputerBasicSynthesis.AlgorithmDataStructure.ChallengeProgramming.Poj2718;


import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;
/**
 * Created by 张超帅 on 2019/8/3.
 */
public class Main {
    static int mid = 0;
    static int res = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        FastWriter so = new FastWriter(System.out);
        int m = sc.nextInt();
        /*  使用 Scanner 读取数据
          for(int i = 0; i < m; i ++) {
                res = Integer.MAX_VALUE;
                if (i == 0)sc.nextLine(); //读取 \n
                String[] str = sc.nextLine().trim().split(" ");
                int[] num = new int[str.length];
                for(int j = 0; j < str.length; j ++) {
                    num[j] = Integer.parseInt(str[j]);
                }
                if(num.length == 2) {
                    System.out.println(Math.abs(num[0] - num[1]));
                    continue;
                }
                mid = num.length / 2;
                dfs(num, 0, num.length - 1);
                System.out.println(res);
            }
        */
        for(int i = 0; i < m; i ++) {
            res = Integer.MAX_VALUE;
            String[] str = sc.nextLine().trim().split(" ");
            int[] num = new int[str.length];
            for(int j = 0; j < str.length; j ++) {
                num[j] = Integer.parseInt(str[j]);
            }
            if(num.length == 2) {
                so.println(Math.abs(num[0] - num[1]));

                continue;
            }
            mid = num.length / 2;
            dfs(num, 0, num.length - 1);
            so.println(res);
        }

    }
    public static void dfs(int[]num, int start, int end) {
        if(start == end) {
            if( num[0] == 0 || num[mid] == 0) {
                return;
            }
            int v_0 = 0, v_1 = 0;
            for (int i = 0; i < mid; i ++) {
                v_0 *= 10;
                v_0 += num[i];
            }
            for (int i = mid; i <= end; i ++) {
                v_1 *= 10;
                v_1 += num[i];
            }
            if(res > Math.abs(v_0 - v_1)){
                res = Math.abs(v_0 - v_1);
                //System.out.println(Arrays.toString(num));
            }
            return;
        }
        for(int i = start; i <= end; i ++) {
            swap(num, i, start);
            dfs(num, start + 1, end);
            swap(num, i, start);
        }
        return;
    }
    public static void swap(int[] num, int i, int j) {
        int tmp = num[i];
        num[i] = num[j];
        num[j] = tmp;
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