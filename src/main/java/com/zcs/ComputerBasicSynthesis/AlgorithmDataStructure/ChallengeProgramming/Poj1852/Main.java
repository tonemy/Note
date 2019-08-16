package com.zcs.ComputerBasicSynthesis.AlgorithmDataStructure.ChallengeProgramming.Poj1852;

/**
 * Created by 张超帅 on 2019/8/7.
 */
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;
public class Main {

    public static void main(String[] args) throws IOException  {
        // TODO Auto-generated method stub
        InputReader in = new InputReader();
        PrintWriter out = new PrintWriter(System.out);
        int t = in.nextInt() ;
        for(int i = 0; i < t; i ++) {
            int l = in.nextInt();
            int n =  in.nextInt();
            int early_time = 0 , latest_time = 0;
            for (int j = 0; j < n; j++) {
                int dis =  in.nextInt();
                early_time = Math.max(early_time, Math.min(l - dis, dis));
                latest_time = Math.max(latest_time, Math.max(l - dis, dis));
            }
            out.print(early_time + " " + latest_time +"\r\n");
            out.flush();
        }
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
