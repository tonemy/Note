package com.zcs.ComputerBasicSynthesis.DS.DataStructure.Segment_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @Author: 张今天
 * @Date: 2019/9/1 16:56
 */
public class Segment_Tree_2 {
    /**
     * 线段树的构造
     * @param tree
     * @param arr
     * @param node
     * @param start
     * @param end
     */
    public static void BuildTree(int[] tree, int[] arr, int node, int start, int end) {
        if(start == end) {
            tree[node] = arr[start];
            return;
        }
        BuildTree(tree, arr, node *2 + 1, start, (start + end) / 2);
        BuildTree(tree, arr, node *2 + 2, (start + end) / 2 + 1, end);
        tree[node] = tree[node * 2 + 1] + tree[node * 2 + 2];
        return;
    }

    /**
     * 对其左右节点进行标记
     * @param tree
     * @param tag
     * @param node
     * @param start
     * @param end
     * @param mid
     */
    public static void PushDown(int[] tree, int[] tag, int node, int start, int end, int mid) {
        if(tag[node] == 0) {
            return;
        }
        tag[node * 2 + 1] += tag[node];
        tag[node * 2 + 2] += tag[node];
        tree[node * 2 + 1] += (mid - start + 1) * tag[node];
        tree[node * 2 + 2] += (end - mid) * tag[node];
        tag[node] = 0;
        return;
    }

    /**
     * 线段树的区间修改
     * @param tree
     * @param tag
     * @param node
     * @param start
     * @param end
     * @param L
     * @param R
     * @param val
     */
    public static void Modify(int[] tree, int[] tag, int node, int start, int end, int L, int R, int val) {
        if(L <= start && R >= end) {
            tree[node] += (end - start + 1) * val;
            tag[node] += val;
            return;
        }
        if(start == end) return;
        int mid = (start + end) / 2;
        PushDown(tree, tag, node, start, end, mid);
        Modify(tree, tag, node * 2 + 1, start, mid, L, R, val);
        Modify(tree, tag, node * 2 + 2, mid + 1, end, L, R, val);
        tree[node] = tree[node * 2 + 1] + tree[node * 2 + 2];
        return;
    }

    /**
     * 线段树的区间查询
     * @param tree
     * @param tag
     * @param node
     * @param start
     * @param end
     * @param L
     * @param R
     * @return
     */
    public static int Query(int[] tree, int[] tag, int node, int start, int end, int L, int R) {
        if(L > end || R < start) {
            return 0;
        }
        if(L <= start && R >= end) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        PushDown(tree, tag, node, start, end, mid);
        int sum = 0;
        if(L <= mid) {
            sum += Query(tree, tag, node * 2 + 1, start, mid, L, R);
        }
        if(R >= mid + 1) {
            sum += Query(tree, tag, node * 2 + 2, mid + 1, end, L, R);
        }
        return sum ;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        int len = arr.length;
        int[] tree = new int[4 * len];
        int[] tag = new int[8 * len];
        BuildTree(tree, arr, 0, 0, len - 1);
        for(int i = 0; i < tree.length; i ++) {
            System.out.println("i = "+i+" ," + tree[i]);
        }
        System.out.println();
        Modify(tree, tag, 0, 0, len - 1, 2, len - 1, 1);

        for(int i = 0; i < tree.length; i ++) {
            System.out.println("i = "+i+" ," + tree[i]);
        }

        int res = Query(tree, tag, 0, 0, len - 1, 4, len - 1);
        System.out.println(res);

        for(int i = 0; i < tree.length; i ++) {
            System.out.println("i = "+i+" ," + tree[i]);
        }
    }

    static class InputReader{
        BufferedReader reader = null;
        StringTokenizer tokenizer = null;
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
            tokenizer = null;
        }
        public String next() {
            if(tokenizer == null || tokenizer.hasMoreTokens()) {
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
