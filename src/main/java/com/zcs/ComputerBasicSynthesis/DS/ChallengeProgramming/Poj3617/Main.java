package com.zcs.ComputerBasicSynthesis.DS.ChallengeProgramming.Poj3617;


import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * Created by 张超帅 on 2019/8/7.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        FastScanner sc = new FastScanner(System.in);
        FastWriter out = new FastWriter(System.out);
        int n = sc.nextInt();
        if (n == 0) {
            System.out.println();
            return;
        }
        String str = "";
        for(int i = 0; i < n; i ++) {
            str += sc.next();
        }
        StringBuilder sb = new StringBuilder();
        int i = 0, j = str.length() - 1;

        while (i <= j) {
            if (sb.length() % 80 == 0 && sb.length() != 0) {
                out.println(sb.toString());
                sb.delete(0, sb.length());
            }
            if (str.charAt(i) < str.charAt(j)) {
                sb.append(str.charAt(i));
                i ++;
            }else if(str.charAt(i) == str.charAt(j)) {
                int ho = i, hi = j;
                while (ho <= hi && str.charAt(ho) == str.charAt(hi)) {
                    ho ++;
                    hi --;
                }
                if(str.charAt(ho) < str.charAt(hi)) {
                    sb.append(str.charAt(i));
                    i ++;
                }else {
                    sb.append(str.charAt(j));
                    j --;
                }
            }
            else {
                sb.append(str.charAt(j));
                j --;
            }
        }
        out.println(sb.toString());

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