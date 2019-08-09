package com.zcs.ChallengeProgramming.Poj1328;

import java.io.*;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * Created by 张超帅 on 2019/8/9.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner(System.in);
        FastWriter out = new FastWriter(System.out);
        int k = 0;
        while (in.hasNext()) {
            int n = in.nextInt();
            int r = in.nextInt();
            k ++;
            if (n == 0 && r == 0) return;
            Pos[] pos = new Pos[n];
            for (int i = 0; i < n; i ++) {
               int x = in.nextInt();
               int y = in.nextInt();
               pos[i] = new Pos(x, y);
            }
            in.nextLine();
            Arrays.sort(pos, new Comparator<Pos>() {
                @Override
                public int compare(Pos o1, Pos o2) {
                    return o1.x - o2.x;
                }
            });
            int res = 0;
            for (int i = 0; i < n; ) {
                if(pos[i].y > r ) {
                    res = -1;
                    break;
                }
                System.out.println(pos[i].x +"," + pos[i].y);
                double fx = Math.sqrt(r*r - pos[i].y * pos[i].y) + pos[i].x;
              //  System.out.println("fx = " + fx);
                if (Double.doubleToLongBits(Math.sqrt(Math.pow(pos[i].x - fx, 2) + Math.pow(pos[i].y, 2)))
                        <= Double.doubleToLongBits(Math.sqrt(Math.pow(r, 2)))){
                    while (i < n && Double.doubleToLongBits(Math.sqrt(Math.pow(pos[i].x - fx, 2) + Math.pow(pos[i].y, 2)))
                            <= Double.doubleToLongBits(Math.sqrt(Math.pow(r, 2)))){
                        System.out.println(Math.sqrt(Math.pow(pos[i].x - fx, 2) + Math.pow(pos[i].y, 2)) );
                      //  System.out.println(Double.doubleToLongBits(Math.sqrt(Math.pow(pos[i].x - fx, 2) + Math.pow(pos[i].y, 2)))
                       //         <= Double.doubleToLongBits(Math.sqrt(Math.pow(r, 2))));
                        i ++;
                    }
                }else {
                    i ++;
                }
                res ++;
            }
            out.println("Case "+k+": "+res);
        }
    }
}
class Pos {
    int x;
    int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
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