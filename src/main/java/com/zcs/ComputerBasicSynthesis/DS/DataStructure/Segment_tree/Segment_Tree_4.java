package com.zcs.ComputerBasicSynthesis.DS.DataStructure.Segment_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @Author: 张今天
 * @Date: 2019/9/2 14:28
 */
public class Segment_Tree_4 {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        int len = arr.length;
        int[] sum = new int[2 * len + 4];
        int[] add = new int[2 * len + 4];
        int M = 1;
        for(; M < len; M <<= 1) ;
        for(int i = M + 1; i <= M + len; i ++) {
            sum[i] = arr[i - M - 1];
        }
        BuildTree(sum, M);
        for(int i = 1; i < sum.length; i ++) {
            System.out.println("i = " + i +" , " + sum[i]);
        }
        Modify(sum, add, M, 1, 5, 1);

        for(int i = 1; i < sum.length; i ++) {
            System.out.println("i = " + i +" , " + sum[i]);
        }
        System.out.println("add~~~");
        for(int i = 1; i < sum.length; i ++) {
            System.out.println("i = " + i +" , " + add[i]);
        }

         int res = Query(sum, add, M, 2, 5);
         System.out.println("Query : " + res);


    }

    private static int Query(int[] sum, int[] add, int M, int l, int r) {
        int res = 0;
        int lcnt = 0, rcnt = 0, cnt = 1;
        for(l = M + l - 1, r = M + r + 1; (l ^ r ^ 1) != 0; l >>= 1, r >>= 1, cnt <<= 1) {
            if(add[l] > 0) {
                res += add[l] * lcnt;
            }
            if(add[r] > 0) {
                res += add[r] * rcnt;
            }
            if(((~l) & 1) == 1) {
                res += sum[l ^ 1];
                lcnt += cnt;
            }
            if((r & 1) == 1) {
                res += sum[r ^ 1];
                rcnt += cnt;
            }
        }
        for(; l != 0 ; l >>= 1, r>>= 1) {
            res += add[l] * lcnt;
            res += add[r] * rcnt;
        }
        return res;
    }

    private static void Modify(int[] sum, int[] add, int M, int l, int r, int val) {
         int lcnt = 0, rcnt = 0, cnt = 1;

         for(l = M + l - 1, r = M + r + 1; (l ^ r ^ 1) != 0; l >>= 1, r >>=1, cnt <<= 1) {
             sum[l] += val * lcnt;
             sum[r] += val * rcnt;
             if( ((~l) & 1) == 1) {
                 add[l ^ 1] += val;
                 sum[l ^ 1] += val * cnt;
                 lcnt += cnt;
             }
             if((r & 1) == 1) {
                 add[r ^ 1] += val;
                 sum[r ^ 1] += val * cnt;
                 rcnt += cnt;
             }
         }
         for(; l != 0 ; l >>= 1, r >>= 1) {
             sum[l] += val * lcnt;
             sum[r] += val * rcnt;
         }
    }

    private static void BuildTree(int[] sum, int M) {
        for(int i = M - 1; i >= 1; i --) {
            sum[i] = sum[i << 1] + sum[i << 1 | 1];
        }
    }

    static class InputReader {
        BufferedReader reader = null;
        StringTokenizer tokenizer = null;
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
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
