package com.zcs.ComputerBasicSynthesis.AlgorithmDataStructure.ChallengeProgramming.Poj2386;

/**
 * Created by 张超帅 on 2019/8/7.
 */
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;
public class Main {
    static int[][] next = {{0, 1},{1, 0},{-1, 0},{0, -1},{1, 1},{1, -1},{-1, 1},{-1, -1}};
    public static void main(String[] args) throws IOException  {
        // TODO Auto-generated method stub
        InputReader in = new InputReader();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] s = new char[n][m];
        for(int i = 0; i < n; i ++) {
            char[] tmp =in.next().toCharArray();
            for(int j = 0; j < m; j ++) {
                s[i][j] = tmp[j];
            }
        }
        int ans = 0;
        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < m; j ++) {
                if(s[i][j] == 'W') {
                    dfs(s, i, j);
                    ans ++;
                }
            }
        }
        out.write(ans+"");
        out.flush();
    }
    public static void dfs (char[][] s, int x, int y) {
        s[x][y] = '.';
        for(int k = 0; k < 8; k ++) {
            int nx = next[k][0] + x;
            int ny = next[k][1] + y;
            if(check(s, nx, ny)) {
                dfs(s, nx, ny);
            }
        }
    }
    private static boolean check(char[][] s, int nx, int ny) {
        // TODO Auto-generated method stub
        if(nx < 0 || ny < 0 || nx >= s.length || ny >= s[0].length || s[nx][ny] == '.') {
            return false;
        }
        return true;
    }
}
class InputReader
{
    BufferedReader buf;
    StringTokenizer tok;
    InputReader()
    {
        buf = new BufferedReader(new InputStreamReader(System.in));
    }
    boolean hasNext()
    {
        while(tok == null || !tok.hasMoreElements())
        {
            try
            {
                tok = new StringTokenizer(buf.readLine());
            }
            catch(Exception e)
            {
                return false;
            }
        }
        return true;
    }
    String next()
    {
        if(hasNext()) return tok.nextToken();
        return null;
    }
    int nextInt()
    {
        return Integer.parseInt(next());
    }
    long nextLong()
    {
        return Long.parseLong(next());
    }
    double nextDouble()
    {
        return Double.parseDouble(next());
    }
    BigInteger nextBigInteger()
    {
        return new BigInteger(next());
    }
    BigDecimal nextBigDecimal()
    {
        return new BigDecimal(next());
    }
}
