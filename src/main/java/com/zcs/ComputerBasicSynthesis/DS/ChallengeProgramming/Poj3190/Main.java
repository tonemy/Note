package com.zcs.ComputerBasicSynthesis.DS.ChallengeProgramming.Poj3190;


import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by 张超帅 on 2019/8/10.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner(System.in);
        FastWriter out = new FastWriter(System.out);
        int n = in.nextInt();
        Cow[] cow = new Cow[n];
        int[] pos = new int[n];
        for(int i = 0; i < n; i ++) {
            int s = in.nextInt();
            int e = in.nextInt();
            cow[i] = new Cow(s, e, i);
        }
        Arrays.sort(cow, new Comparator<Cow>() {
            @Override
            public int compare(Cow o1, Cow o2) {
                return o1.start - o2.start;
            }
        });
        PriorityQueue<Stall> queue = new PriorityQueue<Stall>(n, new Comparator<Stall>() {
            @Override
            public int compare(Stall o1, Stall o2) {
                return o1.over - o2.over;
            }
        });
        int tol = 0;
        for(int i = 0; i < n; i ++) {
            if(queue.isEmpty()) {
                tol ++;
                queue.offer(new Stall(cow[i].end, tol));
                pos[cow[i].oriPos]= tol;
            }else{
                Stall top = queue.peek();
                if(top.over < cow[i].start) {
                    queue.poll();
                    queue.offer(new Stall(cow[i].end, top.num));
                    pos[cow[i].oriPos] = top.num;
                }else {
                    tol ++;
                    queue.offer(new Stall(cow[i].end, tol));
                    pos[cow[i].oriPos] = tol;
                }
            }
        }
        out.println(tol);
        for (int i = 0; i < n; i ++) {
            out.println(pos[i]);
        }
    }
}
class Stall {
    int over;
    int num;
    public Stall(int over, int num) {
        this.over = over;
        this.num = num;
    }
}
class  Cow {
    int start;
    int end;
    int oriPos;
    public Cow(int start, int end, int oriPos) {
        this.start = start;
        this.end = end;
        this.oriPos = oriPos;
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