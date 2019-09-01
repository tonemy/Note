package com.zcs.ComputerBasicSynthesis.DS.DataStructure.SuffixArray;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @Author: 张今天
 * @Date: 2019/8/27 14:44
 * @Function To solve related String's problem
 *
 * TestCase : aabaaaab
 * Limit Time : 5min
 * result : failure
 */
public class Main {
    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        PrintWriter writer = new PrintWriter(System.out);
        Task task = new Task();
        task.solve(reader, writer);
        writer.close();
    }
    static class Task {
        public void solve(InputReader reader, PrintWriter writer){
            String str = reader.next();
            int len = str.length();
            int[] r = new int[len + 1];
            for(int i = 0; i < len ; i ++) {
                r[i] = str.charAt(i) - '0';
            }
            r[len] = 0;
            int[] sa = getSa(r, len + 1);
            writer.println(Arrays.toString(sa));
            int[] h = getHeight(sa, r, str.length());
            writer.println(Arrays.toString(h));
        }
        public int[] getHeight(int[] sa, int[] r, int n) {
            int k = 0,i, j;
            int[] rank = new int[n];
            int[] height = new int[n + 1];
            for(i = 1; i <= n; i ++) {
               rank[sa[i]] = i;
            }
            System.out.println("rank"+Arrays.toString(rank));
            for(i = 0; i < n; height[rank[i ++]] = k) {
               for(k = (k == 0) ? 0 : k - 1,j = sa[rank[i] - 1]; r[j + k] == r[i + k]; k ++);
            }
            return height;
        }
        public int[] getSa(int[] r,  int n) {
            int m = 255, p, j, i, k;
            int[] ws = new int[m];
            int[] wv = new int[n];
            int[] sa = new int[n];
            int[] x = new int[n];
            int[] y = new int[n];
            int[] t;
            for(i = 0; i < m; i ++) {
                ws[i] = 0;
            }
            for(i = 0; i < n; i ++) {
                ws[x[i] = r[i]] ++;
            }
            for(i = 1; i < m; i ++) {
                ws[i] += ws[i-1];
            }
            for(i = n - 1; i >= 0; i --) {
                sa[--ws[x[i]]] = i;
            }
            for( k = 1; k < n; k *= 2, m = p) {
                for(p = 0, j = n - k; j < n; j ++) {
                    y[p ++] = j;
                }
                for(j = 0; j < n; j ++) if(sa[j] >= k) {
                    y[p++] = sa[j] - k;
                }
                for(j = 0; j < n; j ++) {
                    wv[j] = x[y[j]];
                }
                for(j = 0; j < m; j ++) {
                    ws[j] = 0;
                }
                for(j = 0; j < n; j ++) {
                    ws[wv[j]] ++;
                }
                for(j = 1; j < m; j ++) {
                    ws[j] += ws[j-1];
                }
                for(j = n-1; j >= 0; j --) {
                    sa[--ws[wv[j]]] = y[j];
                }
                for(t = x, x = y, y = t, p = 1, x[sa[0]] = 0, j = 1; j < n; j ++) {
                    x[sa[j]] = (y[sa[j-1]] == y[sa[j]] && y[sa[j-1] + k] == y[sa[j] + k]) ? p - 1 : p ++;
                }
            }
            return sa;
        }
    }
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
        public  InputReader(InputStream stream){
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

