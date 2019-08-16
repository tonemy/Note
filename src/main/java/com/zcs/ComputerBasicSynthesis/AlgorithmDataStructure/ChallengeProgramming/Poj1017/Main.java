package com.zcs.ComputerBasicSynthesis.AlgorithmDataStructure.ChallengeProgramming.Poj1017;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by 张超帅 on 2019/8/12.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner(System.in);
        FastWriter out = new FastWriter(System.out);
        int[] num = new int[6];
        int f = 0;
        while (in.hasNext()) {
            boolean flag = false;
            for(int i = 0; i < 6; i ++) {
                num[i] = in.nextInt();
                if(num[i] != 0) {
                    flag = true;
                }
            }
            if(!flag) break;
            int res = 0;
            //假设h为 : 1 ,则包装最大体积为 6 * 6
            int v = 6 * 6;
            for(int i = 5; i >= 0 ; i --) {
              if(i == 5 && num[i] != 0) {
                  res += num[i];
              }else if(i == 4 && num[i] != 0) {
                  res += num[i];
                  if(num[0] != 0) {
                      int m_1 = num[i] *( 6 * 6 - 5 * 5);
                      if(m_1 > num[0]) {
                          num[0] = 0;
                      }else {
                          num[0] -= m_1;
                      }
                  }
              }else if(i == 3 && num[i] != 0) {
                  res += num[i];
                  if(num[1] != 0) {
                      int m_2 = num[i] *(6 * 6 - 4 * 4) / (2 * 2);
                      if(m_2 > num[1]) {
                          m_2 = num[i] *(6 * 6 - 4 * 4) - num[1] * (2 * 2);
                          num[1] = 0;
                      }else {
                          num[1] -= m_2;
                          m_2 = 0;
                      }
                      if(m_2!= 0 && num[0] != 0) {
                          if(m_2 > num[0]) {
                              num[0] = 0;
                          }else {
                              num[0] -= m_2;
                          }
                      }
                  }
              }else if(i == 2 && num[i] != 0) {
                  int m = num[i] / ( 6 * 6 / (3 * 3));
                  if(num[i] % (6 * 6 / (3 * 3)) != 0) {
                      m ++;
                  }
                // System.out.println( 3 +"  "+m);
                  res += m;
                  int m_2 = 0;
                  int[] m_22 = {5, 3, 1, 0};
                  if(num[1] == 0 && num[i] % (6 * 6 / (3 * 3)) != 0) {
                      m_2 = 6 * 6 - num[i] % (6 * 6 / (3 * 3)) * 3* 3;
                  }
                  if(num[1] != 0 && num[i] % (6 * 6 / (3 * 3)) != 0) {
                      m_2 = m_22[num[i] % (6 * 6 / (3 * 3)) - 1];
                      if(m_2 > num[1]) {
                          m_2 = m * 6 * 6 - 3 * 3 * num[i] - num[1] * (2 * 2);
                          num[1] = 0;
                      }else {
                          num[1] -= m_2;
                          m_2 = m * 6 * 6 - 3 * 3 * num[i] - m_2 * (2 * 2);
                      }
                  }
                 // System.out.println(m_2);
                  if(m_2!= 0 && num[0] != 0) {
                      if(m_2 > num[0]) {
                          num[0] = 0;
                      }else {
                          num[0] -= m_2;
                      }
                  }
              }else if(i == 1 && num[i] != 0) {
                  int m = num[i] /( 6 * 6 / (2 * 2));
                  if(num[i] %( 6 * 6 / (2 * 2)) != 0) {
                      m ++;
                  }
                  res += m;
                  if(num[0] != 0 && num[i] %( 6 * 6 / (2 * 2)) != 0) {
                      int m_1 = m * 6 * 6 - 2 * 2 * num[i];
                      if(m_1 > num[0]) {
                          num[0] = 0;
                      }else {
                          num[0] -= m_1;
                      }
                  }
              }else if(i == 0){
                  int m = num[i] /( 6 * 6 );
                  if(num[i] %( 6 * 6) != 0) {
                      m ++;
                  }
                  res += m;
              }
//               out.println(i +"," + res);
//
//              for(int k = 0; k < 6; k ++) {
//                  out.print(num[k] +",");
//              }
//                out.println("");
            }
            f ++;
            if(f == 1) {
                out.print(res);
            }else {
                out.println("");
                out.print(res);
            }
        }
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