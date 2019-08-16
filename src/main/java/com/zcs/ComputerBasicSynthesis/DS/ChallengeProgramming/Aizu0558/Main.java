package com.zcs.ComputerBasicSynthesis.DS.ChallengeProgramming.Aizu0558;


import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Created by 张超帅 on 2019/7/30.
 */
public class Main {
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int h = sc.nextInt();
        int w = sc.nextInt();
        int n = sc.nextInt();
        char[][] map = new char[h][w];
        boolean[][] vis = new boolean[h][w];
        int[][] dp = new int[h][w];
        Queue<Node> que = new LinkedList<Node>();
        int[][] next = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        for(int i = 0; i < h; i ++) {
            map[i] = sc.next().toCharArray();
        }
        for (int i = 0; i < h; i ++) {
            for (int j = 0; j < w; j ++) {
                if (map[i][j] == 'S'){
                    Node node = new Node(i, j);
                    vis[i][j] = true;
                    map[i][j] = '.';
                    que.offer(node);
                }

            }
        }
        int start = 1;
        int res = 0;

        for (int i = 0; i < n; i ++) {
            Node first = null;
            while (!que.isEmpty()){
                Node node = que.poll();

                for (int k = 0; k < 4; k ++) {
                    int nx = next[k][0] + node.x;
                    int ny = next[k][1] + node.y;

                    if(nx >= 0 && ny >= 0 && nx < h && ny < w && !vis[nx][ny] ) {
                        int num = map[nx][ny] - '0';
                        if (map[nx][ny] == '.' || (num <= n && num >= 1)) {
                            dp[nx][ny] = dp[node.x][node.y] + 1;
                            vis[nx][ny] = true;
                            Node nextNode = new Node(nx, ny);
                            que.offer(nextNode);
                            if (num <= start && num >= 1) {
                                //System.out.println(  "nx =" + nx+ "ny = "+ ny +" start " +start);
                                k = 4;
                                map[nx][ny] = '.';
                                first = new Node(nx, ny);
                                que.clear();
                                start ++;
                                for (int l = 0; l < h; l++) {
                                    for (int j = 0; j < w; j++) {
                                        vis[l][j] = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (first != null){
                vis[first.x][first.y] = true;
                res += dp[first.x][first.y];
                que.offer(new Node(first.x, first.y));
             //   System.out.println("i = " + i + " res= " + res +", x =" + first.x+ "y = "+ first.y + "size "+que.size());
                for (int l = 0; l < h; l++) {
                    for (int j = 0; j < w; j++) {
                        dp[l][j] = 0;
                    }
                }
            }
        }
        System.out.println(res);
    }
}
class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
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

