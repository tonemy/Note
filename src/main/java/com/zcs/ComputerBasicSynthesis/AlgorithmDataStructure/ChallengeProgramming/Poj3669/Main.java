package com.zcs.ComputerBasicSynthesis.AlgorithmDataStructure.ChallengeProgramming.Poj3669;


import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by 张超帅 on 2019/7/30.
 */
public class Main {
    static int[][] next = {{0, 1},{0, -1}, {1, 0}, {-1, 0}, {0, 0}};
    static int max_n = 305;
    static int[][] map = new int[max_n][max_n];
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int m = sc.nextInt();
        for (int i = 0; i < max_n; i ++) {
            for (int j = 0; j < max_n; j ++) {
                map[i][j] = -1;
            }
        }
        for(int i = 0; i < m; i ++) {
           int xi = sc.nextInt();
           int yi = sc.nextInt();
           int ti = sc.nextInt();
           for (int k = 0; k < 5; k ++) {
               int nx = next[k][0] + xi;
               int ny = next[k][1] + yi;
               if(nx >= 0 && nx < max_n && ny >= 0 && ny < max_n) {
                   if (map[nx][ny] == -1) {
                       map[nx][ny] = ti;
                   }else {
                       map[nx][ny] = Math.min(map[nx][ny], ti);
                   }
               }
           }
        }
        int res = bfs();
        System.out.println(res);
    }
    public static int bfs(){
        Queue<Node> que = new LinkedList<Node>();
        que.offer(new Node(0, 0, 0));
        if(map[0][0] == -1) {
            return 0;
        }
        if(map[0][0] == 0) {
            return -1;
        }
        while (!que.isEmpty()) {
            Node node = que.poll();
            for (int k = 0; k < 4; k++) {
                int nx = node.x + next[k][0];
                int ny = node.y + next[k][1];
                int nt = node.t + 1;
                if (nx >= 0  && ny >= 0 && nx < max_n && ny < max_n) {
                    //System.out.println(node.t);
                    if(map[nx][ny] == -1) {
                        return nt;
                    }
                    if (map[nx][ny] <= nt) {
                        continue;
                    }
                    map[nx][ny] = nt;
                    que.offer(new Node(nx, ny, nt));
                }
            }
        }
        return -1;
    }
}
class Node {
    int x;
    int y;
    int t;
    public Node(int x, int y, int t) {
        this.x = x;
        this.y = y;
        this.t = t;
    }
}
class FastScanner {
    private final InputStream in = System.in;
    private final byte[] buffer = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;
    private boolean hasNextByte() {
        if (ptr < buflen) {
            return true;
        }else{
            ptr = 0;
            try {
                buflen = in.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (buflen <= 0) {
                return false;
            }
        }
        return true;
    }
    private int readByte() { if (hasNextByte()) return buffer[ptr++]; else return -1;}
    private static boolean isPrintableChar(int c) { return 33 <= c && c <= 126;}
    private void skipUnprintable() { while(hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;}
    public boolean hasNext() { skipUnprintable(); return hasNextByte();}
    public String next() {
        if (!hasNext()) throw new NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while(isPrintableChar(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }
    public long nextLong() {
        if (!hasNext()) throw new NoSuchElementException();
        long n = 0;
        boolean minus = false;
        int b = readByte();
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        if (b < '0' || '9' < b) {
            throw new NumberFormatException();
        }
        while(true){
            if ('0' <= b && b <= '9') {
                n *= 10;
                n += b - '0';
            }else if(b == -1 || !isPrintableChar(b)){
                return minus ? -n : n;
            }else{
                throw new NumberFormatException();
            }
            b = readByte();
        }
    }

    public int nextInt() {
        return (int)nextLong();
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }
}


