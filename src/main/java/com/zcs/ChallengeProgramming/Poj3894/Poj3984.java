package com.zcs.ChallengeProgramming.Poj3894;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

public class Poj3984 {
    static int[] prev = new int[5 * 5];

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        Queue<Node> que = new LinkedList<Node>();
        boolean[][] vis = new boolean[5][5];
        int[][] map = new int[5][5];
        int[][] next = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for(int i = 0; i < 5; i ++) {
            for (int j = 0; j < 5; j ++) {
              map[i][j] = sc.nextInt();
            }
        }
        Node first = new Node(0, 0);
        vis[0][0] = true;
        que.offer(first);
        while (!que.isEmpty()){
            Node node = que.poll();
            for(int k = 0; k < 4; k ++) {
                int nx = next[k][0] + node.x;
                int ny = next[k][1] + node.y;
                if(nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && vis[nx][ny] == false && map[nx][ny] != 1) {
                    prev[nx * 5 + ny] = node.x * 5 + node.y;
                    vis[nx][ny] = true;
                    Node nextNode = new Node(nx, ny);
                    que.offer(nextNode);
                }
            }
        }
        Stack<Node> st = new Stack<Node>();
        int end = 24;
        while (end != 0) {
            Node node = new Node(end / 5, end % 5);
            st.push(node);
            end = prev[end];
        }
        System.out.println("(0, 0)");
        while (!st.isEmpty()) {
            System.out.println("(" + st.peek().x +", " +st.peek().y+")");
            st.pop();
        }

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
