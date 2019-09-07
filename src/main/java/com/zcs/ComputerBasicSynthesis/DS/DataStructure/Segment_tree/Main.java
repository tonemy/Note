package com.zcs.ComputerBasicSynthesis.DS.DataStructure.Segment_tree;

/**
 * @Author: 张今天
 * @Date: 2019/9/3 18:27
 */
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int[] a = new int[1000010];
    static int[] b = new int[1000010];
    static int[] c = new int[10];
    static void radixSort(int n){
        int exp = 1;
        int max_a = 0;
        for( int i=1 ; i<=n ; i++ )
            max_a = Math.max( max_a , a[i] );
        while(exp<=max_a){
            for( int i=0 ; i<10 ; i++ )
                c[i] = 0;
            for( int i=1 ; i<=n ; i++ )
                c[(a[i]/exp)%10]++;
            for( int i=1 ; i<10 ; i++ )
                c[i] += c[i-1];
            for( int i=n ; i>=1 ; i-- )
                b[c[(a[i]/exp)%10]--] = a[i];
            for( int i=1 ; i<=n ; i++ )
                a[i] = b[i];
            if( exp==1000000000 ) break;
            exp *= 10;
        }
    }
    public static void main(String[] args){
        InputReader scan = new InputReader(System.in);
        PrintWriter writer = new PrintWriter(System.out);
        int n = scan.nextInt();
        for( int i=1 ; i<=n ; i++ )
            a[i] = scan.nextInt();
        radixSort(n);
        int ans = 0;
        for( int i=1 ; i< n ; i++ )
            ans = Math.max( ans , a[i+1]-a[i] );
        writer.println(ans);
        writer.close();
    }
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
            tokenizer = null;
        }
        public String next() {
            if(tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}