
**参考地址:** https://cn.vjudge.net/article/46
 
### 第一章: 蓄势待发
- [X] [~~POJ 1852 Ants~~ ](https://cn.vjudge.net/problem/POJ-1852)
### 第二章：初出茅庐

2.1 深度优先搜索
- [X] [~~POJ 2386 Lake Counting~~](https://cn.vjudge.net/problem/POJ-2386)
- [X] [~~POJ 1979 Red and Black~~](https://cn.vjudge.net/problem/POJ-1979)
- [X] [~~Aizu 0118 Property Distribution~~](https://cn.vjudge.net/problem/Aizu-0118)
- [X] [~~Aizu 0033 Ball~~](https://cn.vjudge.net/problem/Aizu-0033)
- [X] [~~POJ 3009 Curling~~](https://cn.vjudge.net/problem/POJ-3009)
 
2.2 宽度优先搜索
- [X] [~~POJ3984 迷宫问题~~](https://cn.vjudge.net/problem/POJ-3984)
- [X] [~~Aizu 0558 Cheess~~](https://cn.vjudge.net/problem/Aizu-0558)
- [X] [~~POJ 3669 Meteor Shower~~](https://cn.vjudge.net/problem/POJ-3669#author=s19435631)
- [X] [~~Aizu 0121 Seven Puzzle~~](https://cn.vjudge.net/problem/Aizu-0121)

2.3 穷竭搜索
- [X] [~~POJ 2718 Smallest Difference~~](https://cn.vjudge.net/problem/POJ-2718#author=s19435631)
- [X] [~~POJ 3187 Backward Digit Sums~~](https://cn.vjudge.net/problem/POJ-3187)
- [X] [~~POJ 3050 Hopscotch~~](https://cn.vjudge.net/problem/POJ-3050)
- [X] [~~Aizu 0525~~](https://cn.vjudge.net/problem/Aizu-0525)

2.4 贪心
- [X] [~~Poj 2393 Yogurt factory~~](https://cn.vjudge.net/problem/POJ-2393)
- [X] [~~Poj 3617 BestCow Line~~](https://cn.vjudge.net/problem/POJ-3617)
- [X] [~~Poj 3040 Allowance~~](https://cn.vjudge.net/problem/POJ-3040)
- [X] [~~Poj 3069 Saruman's Army~~](https://cn.vjudge.net/problem/POJ-3069)
- [X] [~~Poj 3253 Fence Repair~~](https://cn.vjudge.net/problem/POJ-3253)
 
2.5 区间
- [X] [~~POJ 2376 Cleaning Shifts~~](https://cn.vjudge.net/problem/POJ-2376)
- [X] [~~POJ 1328 Radar Installation~~](https://cn.vjudge.net/problem/POJ-1328)
- [X] [~~POJ 3190 Stall Reservations~~](https://cn.vjudge.net/problem/POJ-3190)

2.6 其它
- [X] [~~POJ 1862 Stripies~~](https://cn.vjudge.net/problem/POJ-1862)
- [X] [~~POJ 3262 Protecting the Flowers~~](https://cn.vjudge.net/problem/POJ-3262)
- [X] [~~POJ 1017 Packets~~](https://cn.vjudge.net/problem/POJ-1017#author=BlueLine) 
    
2.7 动态规划
- [X] [~~OpenOj 2945 导弹拦截~~](https://cn.vjudge.net/problem/315252/origin)

### 附录: 笔记
- POJ 1852 Ants : 
  本题的测试案例中，属于多输入案例，但在每组的第二行数据中，有多少行数据不知道。本来我不经常用Scanner来读取数据，常常使用readLine来读取数据，这次在`不知道多少行只知道多少个时，不知道怎么读取数据`。 
- Aizu 0118 Property Distribution:
  本题，我做的思路是没有问题的，检查了好久一直没找到错误，哎~~~~！一直运行错误。哎 c++ dfs就能过而java的不能，二维数组最大100*100
  使用dfs可能会`爆栈`.
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
 