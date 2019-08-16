package com.zcs.ComputerBasicSynthesis.DS.ChallengeProgramming.Poj1328;

import java.io.*;
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
            boolean flag = false;
            for (int i = 0; i < n; i ++) {
               int x = in.nextInt();
               int y = in.nextInt();
               if (y > r) {
                   flag = true;
               }
               double fx = Math.sqrt(Math.pow(r, 2) - Math.pow(y, 2));
               pos[i] = new Pos(x - fx, x + fx);
            }
            Arrays.sort(pos, new Comparator<Pos>() {
                @Override
                public int compare(Pos o1, Pos o2) {
                    if (o1.l >= o2.l ){
                        return 1;
                    }else {
                        return - 1;
                    }
                }
            });
            in.nextLine();
//            for(int i = 0; i < n; i ++) {
//                System.out.println(pos[i].l +"," + pos[i].r);
//            }
            int res = 0;
            for (int i = 0; i < n; ) {
                double right =  pos[i].r ;
                if(right >=  pos[i].l)  {
                    while (i < n && right >=  pos[i].l ) {

                        right = Math.min(pos[i].r, right);
                        i++;
                    }
                }else {
                    i ++;
                }
                res ++;
            }
            if(flag) {
               res = -1;
            }
            out.println("Case "+k+": "+res);
        }
    }
}
class Pos {
    double l;
    double r;
    public Pos(double l, double r) {
        this.l = l;
        this.r = r;
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