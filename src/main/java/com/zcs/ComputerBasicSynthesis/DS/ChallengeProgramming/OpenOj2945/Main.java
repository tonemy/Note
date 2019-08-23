package com.zcs.ComputerBasicSynthesis.DS.ChallengeProgramming.OpenOj2945;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @Author: 张今天
 * @Date: 2019/8/20 22:10
 */

public class Main {
    public static void main(String[] args){
        InputReader in = new InputReader( System.in);
        PrintWriter out = new PrintWriter(System.out);
        Task task = new Task();
        task.solve(in, out);
        // 测试数据 :
        // 8
        // 3 2 1 4 5 6 7 8 (注意先后的顺序)
        out.close();
    }
    static class Task {
        public void solve (InputReader in, PrintWriter out) {
            int cmax = Integer.MIN_VALUE;
            int index = 0;
            int n = in.nextInt();
            int[] v = new int[n];
            int[] c = new int[n];
            for (int i = 0; i < n; i++) {
                c[i] = 1;
                v[i] = in.nextInt();
            }
            for (int i = 1 ; i < n; i ++) {
                for (int j = i - 1; j >= 0; j --) {
                    if (v[i] <= v[j] ) {
                        c[i] = Math.max(c[i], c[j] + 1);
                        if(c[i] > cmax) {
                            cmax = c[i];
                            index = i;
                        }
                    }
                }
            }
            out.print(c[index]);
        }
    }
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
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
