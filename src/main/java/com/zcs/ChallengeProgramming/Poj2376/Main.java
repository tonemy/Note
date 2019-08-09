package com.zcs.ChallengeProgramming.Poj2376;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * Created by 张超帅 on 2019/8/8.
 */

public class Main {
    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner(System.in);
        FastWriter out = new FastWriter(System.out);
        int n = in.nextInt();
        int t = in.nextInt();
        Node[] times = new Node[n];
        for(int i = 0; i < n; i ++) {
            int s = in.nextInt();
            int e = in.nextInt();
            times[i] = new Node(s, e);
        }
        Arrays.sort(times, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.start != o2.start) {
                    return o1.start - o2.start;
                }else {
                    return o1.end - o2.end;
                }
            }
        });
        int res = 0, k = 0, s = 0;
        while (k < n) {
            if(times[k].start <= s + 1) {
                res ++;
                int mx = -1;
                while (k < n && times[k].start <= s + 1){
                    mx = Math.max(mx, times[k].end);
                    k ++;
                }
                s = mx;
                if (s >= t) break;
            }else {
                k ++;
            }
        }
        if (s >= t) {
            out.println(res);
        }else {
            out.println(-1);
        }


    }
}
class Node  {
    int start;
    int end;

    public Node(int start, int end) {
        this.start = start;
        this.end = end;
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