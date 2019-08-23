package com.zcs.ComputerBasicSynthesis.DS.ChallengeProgramming.Poj2385;

import javafx.concurrent.Task;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @Author: 张今天
 * @Date: 2019/8/23 10:21
 */
public class Main {
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        Task task = new Task();
        task.solve(in, out);
        out.close();
    }
    static class Task {
        public static void solve(InputReader in, PrintWriter out) {
            int t = in.nextInt();
            int w = in.nextInt();
            int[] num = new int[t + 5];
            int res = Integer.MIN_VALUE;
            for(int i = 1; i <= t; i ++) {
                num[i] = in.nextInt();
            }
            int[][] dp = new int[t + 5][w + 5];
            if(num[1] == 1) {
                dp[1][0] = 1;
                dp[1][1] = 0;
            }else {
                dp[1][0] = 0;
                dp[1][1] = 1;
            }
            for(int i = 2; i <= t; i ++) {
                for(int j = 0; j <= w; j ++) {
                    if(j == 0) {
                        dp[i][j] = dp[i - 1][j] + num[i] % 2;
                    }else {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]);
                        if(j % 2 + 1 == num[i]) {
                            dp[i][j] += 1;
                        }
                    }
                }
            }
            for(int i = 1; i <= w; i ++) {

                res = Math.max(res, dp[t][i]);
            }
            out.println(res);
        }
    }
    static class InputReader   {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream),32768);
            tokenizer = null;
        }
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()){

                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
            return tokenizer.nextToken();
        }
        public int nextInt(){
            return Integer.parseInt(next());
        }
    }
}
