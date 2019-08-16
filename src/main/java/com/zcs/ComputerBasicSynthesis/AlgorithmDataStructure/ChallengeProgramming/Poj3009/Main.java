package com.zcs.ComputerBasicSynthesis.AlgorithmDataStructure.ChallengeProgramming.Poj3009;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;

/**
 * Created by 张超帅 on 2019/7/31.
 */
public class Main {
    static int[][] num;
    static int[][] next = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int res  ;

    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        while (sc.hasNext()) {
            int w = sc.nextInt();
            int h = sc.nextInt();
            if(h == 0 && w == 0) break;
            num = new int[h][w];
            res =  Integer.MAX_VALUE;
            for (int i = 0; i < h; i ++) {
                for (int j = 0; j < w; j ++) {
                    num[i][j] = sc.nextInt();
                }
            }

            for (int i = 0; i < h; i ++) {
                for (int j = 0; j < w; j ++) {
                    if(num[i][j] == 2){
                        num[i][j] = 0;
                        dfs(i, j , 0);
                    }
                }
            }
            if(res == Integer.MAX_VALUE || res > 10) {
                System.out.println(-1);
            }else
            System.out.println(res);
        }
    }
    public static void dfs(int i, int j, int sum) {
       if(num[i][j] == 3) {
           if(res > sum) {
               res = sum;
               return;
           }
       }
       if(sum > res || sum > 10) return;
       for(int k = 0; k < 4; k ++) {
           int nx = next[k][0] + i;
           int ny = next[k][1] + j;
           while (nx >= 0 && nx < num.length && ny >= 0 && ny < num[0].length && num[nx][ny] != 1) {
               if (num[nx][ny] == 3) {
                   sum ++;
                   if (res > sum) {
                       res = sum;
                       return ;
                   }
               }
               nx += next[k][0];
               ny += next[k][1];
           }
           if((nx == i + next[k][0] && ny == j + next[k][1]) || nx < 0 || nx >= num.length || ny < 0|| ny >= num[0].length){
               continue;
           }
          //System.out.println(nx + "," + ny);
           num[nx][ny] = 0;
           sum ++;
           dfs(nx - next[k][0], ny - next[k][1], sum);
           sum --;
           num[nx][ny] = 1;
       }
    }
}
//2.5sum = 4
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
