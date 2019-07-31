
**参考地址:** https://cn.vjudge.net/article/46

 
###  第一章: 蓄势待发
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
 - [ ] POJ 3669
 - [ ] Aizu 0121
 - [ ]   POJ 2718
 - [ ] POJ 3187
 - [ ] POJ 3050
 - [ ] Aizu 0525


 
### 附录: 笔记
- POJ 1852 Ants : 
  本题的测试案例中，属于多输入案例，但在每组的第二行数据中，有多少行数据不知道。本来我不经常用Scanner来读取数据，常常使用readLine来读取数据，这次在`不知道多少行只知道多少个时，不知道怎么读取数据`。 
- POJ 1979 Red and Black:
  本题，我做的思路是没有问题的，检查了好久一直没找到错误，哎~~~~！一直运行错误。
### 附录：代码
 - 快速读取数据
```java
class FastScanner {
    private final InputStream in = System.in;
    private final byte[] buffer = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;
    private boolean hasNextByte() {
        if (ptr < buflen) {
            return true;
        }else{
            ptr = 0;
            try {
                buflen = in.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (buflen <= 0) {
                return false;
            }
        }
        return true;
    }
    private int readByte() { if (hasNextByte()) return buffer[ptr++]; else return -1;}
    private static boolean isPrintableChar(int c) { return 33 <= c && c <= 126;}
    private void skipUnprintable() { while(hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;}
    public boolean hasNext() { skipUnprintable(); return hasNextByte();}
    public String next() {
        if (!hasNext()) throw new NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while(isPrintableChar(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }
    public long nextLong() {
        if (!hasNext()) throw new NoSuchElementException();
        long n = 0;
        boolean minus = false;
        int b = readByte();
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        if (b < '0' || '9' < b) {
            throw new NumberFormatException();
        }
        while(true){
            if ('0' <= b && b <= '9') {
                n *= 10;
                n += b - '0';
            }else if(b == -1 || !isPrintableChar(b)){
                return minus ? -n : n;
            }else{
                throw new NumberFormatException();
            }
            b = readByte();
        }
    }
     
    public int nextInt() {
        return (int)nextLong();
    }
     
    public double nextDouble() {
        return Double.parseDouble(next());
    }
     
 
}
```
 