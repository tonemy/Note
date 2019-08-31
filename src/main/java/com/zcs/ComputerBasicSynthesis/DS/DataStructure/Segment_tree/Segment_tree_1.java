package com.zcs.ComputerBasicSynthesis.DS.DataStructure.Segment_tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @Author: 张今天
 * @Date: 2019/8/31 20:56
 */

public class Segment_tree_1 {
    public static void  BuildTree(int[]tree, int[] arr, int node, int start, int end) {
        if(start == end) {
            tree[node] = arr[start];
            return;
        }
        int mid = (start + end) >> 1;
        int node_left = node * 2;
        int node_right = node * 2 + 1;
        BuildTree(tree, arr, node_left, start, mid);
        BuildTree(tree, arr, node_right, mid + 1, end);
        tree[node] = tree[node_left] + tree[node_right];
        return;
    }
    public static void Modify(int[] tree, int[] arr, int node, int start, int end, int pos, int val) {
        if(start == end) {
            tree[node] = val;
            return;
        }
        int mid = (start + end) >> 1;
        int node_left = node * 2;
        int node_right = node * 2 + 1;
        if(pos <= mid) {
            Modify(tree, arr, node_left, start, mid, pos, val);
        }else {
            Modify(tree, arr, node_right, mid + 1, end, pos, val);
        }
        tree[node] = tree[node_left] + tree[node_right];
        return;
    }
    public static int Query(int[] tree, int[] arr, int node, int start, int end, int L, int R) {
        //System.out.println("start : " + start+ ", end : " + end);
        if(start == end) {
            return tree[node];
        }
        if(end < L || start > R) {
            return 0;
        }
        if(L <= start && R >= end) {
            return tree[node];
        }
        int mid = (start + end) >> 1;
        int node_left = 2 * node;
        int node_right = 2 * node + 1;
        int sum_left = Query(tree, arr, node_left, start, mid,  L, R);
        int sum_right = Query(tree, arr, node_right, mid + 1, end, L, R);
        return sum_left + sum_right;
    }
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        int len = arr.length;
        int[] tree = new int[4*len + 1];
        BuildTree(tree, arr, 1, 0, len - 1);
        for(int i = 0; i < tree.length; i ++) {
            System.out.println("node =" + i +","+tree[i]);
        }
        System.out.println();

        Modify(tree, arr, 1, 0, len - 1, 4, 6);
        for(int i = 0; i < tree.length; i ++) {
            System.out.println("node =" + i +","+tree[i]);
        }

        System.out.println();
        int res = Query(tree, arr, 1, 0, len - 1, 2, 5);
        System.out.println("sum = " +res);
    }
    static class InputReader {
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
