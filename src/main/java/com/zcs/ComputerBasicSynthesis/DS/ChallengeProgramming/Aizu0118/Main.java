package com.zcs.ComputerBasicSynthesis.DS.ChallengeProgramming.Aizu0118;

/**
 * Created by 张超帅 on 2019/8/7.
 */
import java.io.*;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {
    static int[][] next = {{0, 1},{1, 0},{-1, 0},{0, -1}};

    public static void main(String[] args) throws IOException  {
        // TODO Auto-generated method stub
        FastScanner in = new FastScanner(System.in);
        FastWriter out = new FastWriter(System.out);
        while(true) {
            int ans = 0;
            int n = in.nextInt();
            int m = in.nextInt();
            if(n == 0 && m == 0) break;
            char[][] s = new char[n][m];
            for(int i = 0; i < n; i ++) {
                char[] tmp =in.next().toCharArray();
                for(int j = 0; j < m; j ++) {
                    s[i][j] = tmp[j];
                }
            }
            for(int i = 0; i < n; i ++) {
                for(int j = 0; j < m; j ++) {
                    if(s[i][j] != '.') {
                        Queue<Point> que = new LinkedList<Point>();
                        que.offer(new Point(i, j, s[i][j]));
                        s[i][j] = '.';
                        while (!que.isEmpty()) {
                            Point tmp = que.poll();
                            for (int k = 0; k < 4; k ++) {
                                int nx = next[k][0] + tmp.x;
                                int ny = next[k][1] + tmp.y;
                                if(nx >= 0 && ny >= 0 && nx < s.length && ny < s[0].length) {
                                    //System.out.println(s[nx][ny]);
                                    if (s[nx][ny] == tmp.val) {
                                        que.offer(new Point(nx, ny, s[nx][ny]));
                                        s[nx][ny] = '.';
                                    }
                                }
                            }
                        }
                        ans ++ ;
                    }
                }
            }
             out.println(ans);
        }
    }
}
class Point{
    int x;
    int y;
    char val;
    public Point(int x, int y, char val) {
        this.x = x;
        this.y = y;
        this.val = val;
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