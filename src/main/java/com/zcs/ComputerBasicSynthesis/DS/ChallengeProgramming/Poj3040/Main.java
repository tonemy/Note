package com.zcs.ComputerBasicSynthesis.DS.ChallengeProgramming.Poj3040;

/**
 * Created by 张超帅 on 2019/8/19.
 */
import java.io.*;
import java.util.*;
import java.math.*;
public class Main {
    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner(System.in);
        FastWriter out = new FastWriter(System.out);
        int n = in.nextInt();
        int c = in.nextInt();
        int sum = 0;
        Coin[] coins = new Coin[n];
        for (int i = 0; i < n; i ++) {
            int v = in.nextInt();
            int q = in.nextInt();
            coins[i] = new Coin(v, q);
        }
        Arrays.sort(coins, new Comparator<Coin>() {
            @Override
            public int compare(Coin o1, Coin o2) {
                return o1.value - o2.value;
            }
        });

        sum = solve(coins, n, c);
        out.println(sum);
    }
    public static int solve(Coin[] coins, int n, int c) {
        int res = 0;
        for (int i = 0; i < coins.length; i ++) {
            if(coins[i].value >= c) {
                res += coins[i].quantity;
                coins[i].quantity = 0;
            }
        }
        while (true) {

            int tmp = c;
            //System.out.println(c + "," );
            int[] vis = new int[n];
            for (int i = n - 1; i >= 0; i --) {
              //  System.out.println(coins[i].value);
                if(coins[i].quantity > 0) {
                    int r = Math.min(tmp / coins[i].value, coins[i].quantity);
                    tmp -= r * coins[i].value;
                    vis[i] = r;
                }
            }
            //System.out.println("tmp 0 : "+tmp);
            if(tmp > 0) {
                for(int i = 0; i < n; i ++) {
                    if(coins[i].quantity > 0 && coins[i].value >= tmp) {
                        vis[i] += 1;
                        tmp -= coins[i].value;
                        break;
                    }
                }
            }
            //System.out.println("tmp 1 : "+tmp);
            if(tmp > 0) {
                break;
            }
             /*
            3 10
            1 1
            2 1
            4 1
            */
            int ans = Integer.MAX_VALUE;
            for(int i = 0; i < n; i ++) {
                if(vis[i] > 0) {
                    ans = Math.min(ans, coins[i].quantity / vis[i]);
                }
            }
            if(ans == 0) {
                break;
            }
            res += ans;
            for(int i = 0; i < n; i ++) {
                if(vis[i] > 0) {
                    coins[i].quantity -= ans * vis[i];
                }
            }
        }
        return res;
    }
}
class Coin {
    int value;
    int quantity;

    public Coin(int value, int quantity) {
        this.value = value;
        this.quantity = quantity;
    }
}
class  FastScanner  implements Closeable {
    private BufferedReader reader;
    private StringTokenizer tokenizer;
    public FastScanner(InputStream inputStream) {
        reader = new BufferedReader(new InputStreamReader(inputStream));
        tokenizer = new StringTokenizer("");
    }
    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException ex) {
            return null;
        }
    }
    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }
    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }
    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }
    public int nextInt() {
        return Integer.valueOf(next());
    }
    public double nextDouble() {
        return Double.valueOf(next());
    }
    public BigInteger nextBigInteger() {
        return new BigInteger(next());
    }
    public void close() throws IOException {
        reader.close();
    }
}
class FastWriter implements Closeable {
    private BufferedWriter writer;
    public FastWriter(OutputStream outputStream) {
        writer = new BufferedWriter(new OutputStreamWriter(outputStream));
    }
    public void print(Object object) throws IOException {
        writer.write(object.toString());
        writer.flush();
    }
    public void println(Object object) throws IOException {
        writer.write(object.toString());
        writer.write("\n");
        writer.flush();
    }
    public void close() throws IOException {
        writer.close();
    }
}