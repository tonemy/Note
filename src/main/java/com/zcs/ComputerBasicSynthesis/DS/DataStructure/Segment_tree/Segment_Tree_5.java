package com.zcs.ComputerBasicSynthesis.DS.DataStructure.Segment_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @Author: 张今天
 * @Date: 2019/9/4 9:01
 */
public class Segment_Tree_5 {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        int len = arr.length;
        int[] tree = new int[2 * len + 4];
        int M = 1;
        for(; M <= len; M <<= 1);

        BuildTree(tree, arr, M);

        for(int i = 0; i < tree.length; i ++) {
            System.out.println("i = " + i+" ,"+ tree[i]);
        }

        ModifyTree(tree, arr, M, 1, 5, 1);

        for(int i = 0; i < tree.length; i ++) {
            System.out.println("i = " + i+" ,"+ tree[i]);
        }

        int res = QueryTree(tree,  M, 1, 5);
        System.out.println("res = " + res);
    }

    private static int QueryTree(int[] tree,  int M, int l, int r) {
        int res = 0, hi = 0, ho = 0;
        l += M;
        r += M;
        if((l ^ r) != 0) {
            for(; (l ^ r ^ 1) != 0; l >>= 1, r >>= 1) {
              hi += tree[l];
              ho += tree[r];
              if(((~l) & 1) != 0) {
                  hi = Math.max(hi, tree[l ^ 1]);
              }
              if((r & 1) != 0) {
                  ho = Math.max(ho, tree[r ^ 1]);
              }
            }
        }
        res = Math.max(hi + tree[l], ho + tree[r]);
        while(l > 1) res += tree[l >>= 1];
        return res;
    }

    private static void ModifyTree(int[] tree, int[] arr, int M, int l, int r, int val) {
        int tmp;
        for(l = l + M - 1, r = r + M + 1; (l ^ r ^ 1) != 0; l >>= 1, r >>= 1) {
            if(((~l) & 1) != 0) {
                tree[l ^ 1] += val;
            }
            if((r & 1) != 0) {
                tree[r ^ 1] += val;
            }
            tmp = Math.max(tree[l], tree[l ^ 1]);
            tree[l] -= tmp;
            tree[l ^ 1] -= tmp;
            tree[l >> 1] += tmp;
            tmp = Math.max(tree[r], tree[r ^ 1]);
            tree[r] -= tmp;
            tree[r ^ 1] -= tmp;
            tree[r >> 1] += tmp;
        }
        for(; l > 1; l >>= 1) {
            tmp = Math.max(tree[l], tree[l ^ 1]);
            tree[l] -= tmp;
            tree[l ^ 1] -= tmp;
            tree[l >> 1] += tmp;
        }
    }

    private static void BuildTree(int[] tree, int[] arr, int M) {
        for(int i = M + 1; i <= arr.length + M; i ++) {
            tree[i] = arr[i - M - 1];
        }
        for(int i = M - 1; i >= 1; i --) {
            tree[i] = Math.max(tree[i << 1], tree[i << 1 | 1]);
            tree[i << 1] -= tree[i];
            tree[i << 1 | 1] -= tree[i];
        }
    }

    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;
        public void InputReader(InputStream stream) {
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
