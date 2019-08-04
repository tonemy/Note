
**参考地址:** https://cn.vjudge.net/article/46

 
### 第一章: 蓄势待发
 - [X]    [~~POJ 1852 Ants~~ ](https://cn.vjudge.net/problem/POJ-1852)

### 第二章：初出茅庐
1. 深度优先搜索

 - [X]  [~~POJ 2386 Lake Counting~~](https://cn.vjudge.net/problem/POJ-2386)
 - [X]   [~~POJ 1979 Red and Black~~](https://cn.vjudge.net/problem/POJ-1979)
 - [X] [~~Aizu 0118~~](https://cn.vjudge.net/problem/Aizu-0118)
 - [X] [~~Aizu 0033 Ball~~](https://cn.vjudge.net/problem/Aizu-0033)
 - [X] [~~POJ 3009~~](https://cn.vjudge.net/problem/POJ-3009)
2. 宽度优先搜索
 - [X] [~~POJ3984 迷宫问题~~](https://cn.vjudge.net/problem/POJ-3984)
 - [X] [~~Aizu 0558 Cheess~~](https://cn.vjudge.net/problem/Aizu-0558)
 - [X] [~~POJ 3669 Meteor Shower~~](https://cn.vjudge.net/problem/POJ-3669#author=s19435631)
 - [X] [~~Aizu 0121~~](https://cn.vjudge.net/problem/Aizu-0121)
3. 穷竭搜索
 - [X] [~~POJ 2718 Smallest Difference~~](https://cn.vjudge.net/problem/POJ-2718#author=s19435631)
 - [X] [~~POJ 3187~~](https://cn.vjudge.net/problem/POJ-3187)
 - [ ] POJ 3050
 - [ ] Aizu 0525


 
### 附录: 笔记
- POJ 1852 Ants : 
  本题的测试案例中，属于多输入案例，但在每组的第二行数据中，有多少行数据不知道。本来我不经常用Scanner来读取数据，常常使用readLine来读取数据，这次在`不知道多少行只知道多少个时，不知道怎么读取数据`。 
- POJ 1979 Red and Black:
  本题，我做的思路是没有问题的，检查了好久一直没找到错误，哎~~~~！一直运行错误。
- POJ 3669 Meteor Shower :
  这题好坑哇，java中break只会跳出一层循环，我把bfs的代码单独写出来直接在bsf内部进行return，就可以了
- POJ 2718 Smallest Difference:
  读取案例数据时，Scanner.nextInt 读取第一行的int数据后，还有一个 \n ，再次读取下一行需要先使用sc.nextLine忽略它；
  还有，这题是多测试案例，我的res没有再次重新赋值是一大失误，导致多次wa
### 附录：代码
 - 快速读取数据
```java
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
```
 