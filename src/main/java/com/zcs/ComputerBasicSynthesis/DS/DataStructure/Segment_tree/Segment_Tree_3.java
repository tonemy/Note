package com.zcs.ComputerBasicSynthesis.DS.DataStructure.Segment_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @Author: 张今天
 * @Date: 2019/9/2 10:26
 */
public class Segment_Tree_3 {

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        int len = arr.length;
        int[] val = new int[4 * len + 1];
        int M = 0;
        for(M = 1; M < len; M <<= 1);
        for(int i = M + 1; i <= M + len; i ++) {
            val[i] = arr[i - M - 1];
        }

        BuildTree(val, arr, M, len);
        for(int i = 1; i <= 4 * len; i ++) {
            System.out.println("i = " + i + ", " + val[i]);
        }

        ModifyTree(val, M, 5, 7);

        for(int i = 1; i <= 4 * len; i ++) {
            System.out.println("i = " + i + ", " + val[i]);
        }

        int res = QueryTree(val, M,  1, 3);
        System.out.println(res);
    }
    private static int QueryTree(int[] val, int M, int l, int r) {
        int res = 0;
        for(l = l + M - 1, r = r + M + 1; (l ^ r ^ 1) != 0; l >>= 1, r >>= 1) {
            if(((~l) & 1) == 1) {
                res = Math.max(res, val[l ^ 1]);
            }
            if((r & 1) == 1) {
                res = Math.max(res, val[r ^ 1]);
            }
        }
        return res;
    }

    private static void ModifyTree(int[] val, int M, int pos, int x) {
        val[M + pos] += x;
        for(int i = (pos + M) >> 1; i >= 1; i >>= 1) {
            val[i] = Math.max(val[i << 1] , val[i << 1 | 1]);
        }
    }

    private static void BuildTree(int[] val, int[] arr, int M, int n) {
        for(int i = M - 1; i >= 1; i --) {
            val[i] = Math.max(val[i << 1], val[i << 1 | 1]);
        }
    }

    class InputReader{
        BufferedReader reader = null;
        StringTokenizer tokenizer = null;
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
