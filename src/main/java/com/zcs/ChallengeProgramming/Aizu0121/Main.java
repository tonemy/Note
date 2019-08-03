package com.zcs.ChallengeProgramming.Aizu0121;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by 张超帅 on 2019/8/3.
 */
public class Main {
    static StringBuilder ori = new StringBuilder("01234567");
    static StringBuilder tmp = null;
    static Map<String, Integer> map = new HashMap<String, Integer>();
    static Queue<StringBuilder> que = new LinkedList<StringBuilder>();
    static int[] next = {-1, 1, 4, -4};
    public static void main(String[] args) {

        FastScanner sc = new FastScanner();
        bfs();
        while (sc.hasNext()) {
            StringBuilder cur = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                cur.append(sc.nextInt());
            }
            System.out.println(map.get(cur.toString()));
        }
    }
    public static void bfs() {

        map.put(ori.toString(), 0);
        que.offer(ori);
        while (!que.isEmpty()) {
            ori = que.poll();
            int x = ori.indexOf("0");
            for(int k = 0; k < 4; k ++) {
                int nx = x + next[k];
                if((nx >= 0 && nx < 8) && !(k == 0 && (x == 0 || x == 4)) && !(k == 1 && (x == 3 || x == 7))){

                    tmp = new StringBuilder(ori.toString());
                    tmp.deleteCharAt(x);
                    tmp.insert(x, ori.substring(nx, nx + 1));
                    tmp.deleteCharAt(nx);
                    tmp.insert(nx, ori.substring(x, x + 1));

                    if(!map.containsKey(tmp.toString())) {
                        map.put(tmp.toString(), map.get(ori.toString()) + 1);
                        que.offer(tmp);
                    }
                }
            }
        }
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
