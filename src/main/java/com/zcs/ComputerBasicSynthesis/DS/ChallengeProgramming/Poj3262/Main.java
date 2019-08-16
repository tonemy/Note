package com.zcs.ComputerBasicSynthesis.DS.ChallengeProgramming.Poj3262;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * Created by 张超帅 on 2019/8/16.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner(System.in);
        FastWriter out = new FastWriter(System.out);

        int n = in.nextInt();
        Cow[] cows = new Cow[n];
        long sum = 0;
        for(int i = 0; i < n; i ++) {
            int t = in.nextInt();
            int d = in.nextInt();
            sum += d;
            cows[i] = new Cow(t, d);
        }
        Arrays.sort(cows, new Comparator<Cow>() {
            @Override
            public int compare(Cow o1, Cow o2) {
                return o2.d * o1.t - o1.d * o2.t ;
            }
        });

        long res = 0;
        for (int i = 0; i < n; i ++) {
           // System.out.println(cows[i].t +"," + cows[i].d);
            if (i != 0)  {
                res += cows[i - 1].t * 2 * sum;
            }
            sum -= cows[i].d;
             //System.out.println(res);
        }
        out.println(res);

    }
}
class Cow {
    int t;
    int d;


    public Cow(int t, int d) {
        this.t = t;
        this.d = d;
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
