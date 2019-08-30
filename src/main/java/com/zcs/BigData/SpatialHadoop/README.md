
### 1. SpatialHadoop 在Hadoop集群中的安装

**注意:**
- 我选择的是支持Hadoop2.x的相应的版本的进行下载,也就是与Hadoop2.x的版本要兼容,同时还有可进行扩展的jar包
- 我是用的是搭建在 Centos 上的Hadoop集群,所以下面描述的相关命令均在Centos上

#### 1.1 方式一
1) SpatialHadoop 下载地址: [点击这里](http://spatialhadoop.cs.umn.edu/#downloads)

2) 下载后,上传到Hadoop集群中的各个节点下的hadoop的主目录下【scp -r spatialhadoop-2.4.2-bin.tar.gz MHadoop@SlaveX.Hadoop:/xx/xx/hadoop】，
   进行解压【tar -vxf spatialhadoop-2.4.2-bin.tar.gz】:

```
LICENSE.txt
README.md
etc/
etc/hadoop/
bin/
etc/hadoop/spatial-site.xml.template
bin/shadoop
share/hadoop/common/lib/spatialhadoop-2.4.2.jar
share/hadoop/etc/hadoop/spatial-site.xmlcommon/lib/jts-1.13.jar
share/hadoop/common/lib/esri-geometry-api-1.2.1.jar
share/hadoop/common/lib/javax.mail-1.5.5.jar
share/hadoop/common/lib/javax.mail-api-1.5.5.jar
```
3) 成功后，你就可以在上面显示的路径中看到相应的文件
4) 在`etc/hadoop/hadoop-env.sh`中配置java的环境变量
5) 重启hadoop集群，确保lib库中的jar包已被添加.
6) 下面就可以在Hadoop集群运行的情况下,尝试一下官网给的案例了

#### 1.2 方式二
1) 下载其源码, [地址](https://github.com/aseldawy/spatialhadoop2)(不需要直接下载,直接看第二步骤)
2) 使用IDEA中的git插件来下载源码, [教程,看这儿](https://blog.csdn.net/my_springlove/article/details/80184560)
3) 可以利用IDEA中的maven的plugin进行打包jar包, 在IDEA右侧栏有Maven的功能窗口,打开,找到 **package**,点击一下,就开把整个项目进行打包了
    打包成功后,就可以在工程中的target目录下找到.
4) 将其jar包上传至hadoop集群中, 借助远程工具上传即可,如: Xshell(最好使用老版本的)
5) 使用如下命令运行,这个test2.grid是我已经进行索引过了,你需要先尝试下官网上的前两个命令

```
hadoop jar spatialhadoop-2.4.3-SNAPSHOT.jar readfile test2.grid
```

6) 其 hadoop jar spatialhadoop-2.4.3-SNAPSHAT.jar 就相当于 方式一中的bin/shadoop命令.官网上给的 命令都可以尝试一下

#### 1.3方式三
**注意的问题:**

- 这种方式还是以方式二为基础的,只不过换了个操作系统,`Windows 10 -> Centos 7`,主要因为在windows上hadoop的相关依赖在打包时一直报错
- 在使用maven进行编译时会出现一些问题,我遇到的问题已经在附录中进行了补充.

1) 下载可以运行在centos系统的jar包,[apache-maven-3.6.1-bin.tar.gz](http://mirror.bit.edu.cn/apache/maven/maven-3/3.6.1/binaries/apache-maven-3.6.1-bin.tar.gz)
2) 安装方式: [点击这里](https://maven.apache.org/install.html)
3) 前提是你已经配置好了jdk的环境变量,配置Maven的环境变量,在`/etc/profile `或`/etc/bashrc`中添加如下配置:

    ```
    #set maven
    
    export MAVEN_HOME=/usr/hadoop/datas/apache-maven-3.6.1
    export PATH=$PATH:$MAVEN_HOME/bin
    
    ```

4) 保存生效:【source /etc/bashrc】
5) 检测Maven是否安装成功:【mvn -v】

    ```
    [root@master ~]# mvn -v
    Apache Maven 3.6.1 (d66c9c0b3152b2e69ee9bac180bb8fcc8e6af555; 2019-04-05T03:00:29+08:00)
    Maven home: /usr/hadoop/datas/apache-maven-3.6.1
    Java version: 1.7.0_80, vendor: Oracle Corporation, runtime: /usr/java/jdk1.7.0_80/jre
    Default locale: zh_CN, platform encoding: UTF-8
    OS name: "linux", version: "3.10.0-693.el7.x86_64", arch: "amd64", family: "unix"
    
    ```
6) 从git上下载spatialHadoop的源码文件,[地址](https://github.com/aseldawy/spatialhadoop2)
7) 修改pom.xml的文件内容,主要修改hadoop的版本信息,要和你的Hadoop版本相同
8) 进入spatialHadoop目录下,使用maven编译【mvn compile】,打包【mvn assembly:assembly】,成功后就可以看到如下jar包
    ```
      [MHadoop@master spatialhadoop2]$ cd target/
        [MHadoop@master target]$ ls
        apidocs                 maven-status
        archive-tmp             spatialhadoop-2.4.3-SNAPSHOT-bin.tar.gz
        classes                 spatialhadoop-2.4.3-SNAPSHOT.jar
        generated-sources       spatialhadoop-2.4.3-SNAPSHOT-javadoc.jar
        generated-test-sources  spatialhadoop-2.4.3-SNAPSHOT-sources.jar
        javadoc-bundle-options  spatialhadoop-2.4.3-SNAPSHOT-uber.jar
        maven-archiver          test-classes
    ```


### 2. spatialHadoop的各种操作
**注意的问题：** 
- 这个的操作前提是前面能够安装成功,可以通过几个简单的命令进行检测,[官网](http://spatialhadoop.cs.umn.edu/)上给的命令拿来检测一下即可.
- 输入的文件的默认格式是什么 
- 输入的文件的格式如何进行扩展的?
- 如果有的参数与值之间有冒号,则这个冒号与其无空格.
- 若想要绘图则需要先进行索引.

1) `bin/shadoop index <inpath> <outpath> sindex:<sindex> shape:<shape> blocksize:<size> -overwrite ` 
   - 各个参数: 
     - `<inpath>`: 所需要输入的文件的路径(注意:这个文件需要在hdfs上),文件中常见的默认格式point【X,Y】; rect【X, Y, Width, Height】; polygon【points (0,0), (1,1) and (1,0)表示为 3,0,0,1,1,1,0】,其中3代表点的个数)
     - `<outpath>`: 输出的文件的路径
     - `<sindex>` : 空间索引过程中是按哪种方式进行的( grid, rtree, r+tree, r*tree, r*tree+, str, str+, quadtree, kdtree, zcurve, or hilbert)
     - `<shape>` : 可以使用简短的名字的有`point`、`rectangle`,还有一些需要使用完整的类名[数据类型](http://spatialhadoop.cs.umn.edu/doc-2.3/edu/umn/cs/spatialHadoop/core/Shape.html),这些数据类型的使用主要根据应用场景而决定.
     - `blocksize:<size>`: 生成文件的块的大小, 默认情况使用默认的块的大小(我觉得默认情况下和hadoop配置块大小有关)
     - `-overwrite` : 如果命令中有这个参数,则在文件输出时,如果有和它重名的则会直接覆盖.   

2) ` bin/shadoop gplot <input> <output> shape:<input format> width:<w> height:<h> -keep-ratio color:<c> -vflip -overwrite`
    - 主要参数
        - `<output>` : 要输出的图片的路径,其实也就是文件的名字,生成的图片为png文件
        - `<shape>` : 
        - `<w> <h>`:默认为1000
        - `<c>`: 绘图时数据显示的颜色:colors: red, pink, blue, cyan, green, black, gray, and orange.
        - `-vflip` : 垂直翻转整个图片
    - 关于绘图还有一种方式:使用[QGIS](https://www.qgis.org/en/site/forusers/download.html)  
        - Centos 安装QGIS的命令: 【 sudo yum install qgis qgis-python qgis-grass qgis-mapserver】
        - 安装成功后,在进行索引后产生的目录中,将后缀为.kwt的文件使用命令【hadoop fs -get /user/MHadoop/test2_Output/xxx.kwt】拷贝到本地
        - 打开QGIS,点击左侧栏中的`Add Delimited Text Layer`按钮
        - 使用`Browse`打开刚才从hadoop上下载的.kwt文件,设置剩余按钮的属性
            - 勾选 `tab`
            - 勾选 `First record has field names`
            - 勾选 `Well Known Text (WKT)" geometry definition`
            - 勾选 `Boundaries" as a Geometry field.`
            - 点击OK
            - 选择 `WGS 84`
            
3)  hadoop jar spatialhadoop-2.4.3-SNAPSHOT.jar hadoopviz

4) 其它的参考: [这个吧](https://github.com/aseldawy/spatialhadoop2/wiki/A-list-of-most-operations-in-SpatialHadoop)
 
 

### Q&A
**参考的网址:**
- [aseldawy的博客](http://aseldawy.blogspot.com/2017/10/visualize-spatialhadoop-indexes.html)
- [aseldawy的git的wiki](https://github.com/aseldawy/spatialhadoop2/wiki/A-list-of-most-operations-in-SpatialHadoop)
- [spatialHadoop的官网](http://spatialhadoop.cs.umn.edu/all_operations.html)

**参考的论文:**

- [Manikis_Michalis.pdf](https://github.com/tonemy/Note/blob/master/src/main/java/com/zcs/BigData/SpatialHadoop/Manikis_Michalis.pdf)
- [SpatialHadoop_A MapReduce Framework for spatial data.pdf](https://github.com/tonemy/Note/blob/master/src/main/java/com/zcs/BigData/SpatialHadoop/SpatialHadoop_A%20MapReduce%20Framework%20for%20spatial%20data.pdf)


**问题一:**

>使用git下载spatialhadoop后, 在右侧栏中的maven功能窗口总出现这个错误: `- Missing artifact jdk.tools:jdk.tools:jar:1.6)`

- **解决办法**
- [参考网址](http://www.it1352.com/843130.html)
主要在pom.xml中添加了相关的依赖,我系统本身jdk是1.8,但我按照这个网址上的添加后依然会报错提示:`1.6 和1.8 有矛盾`,然后我的添加配置如下
关于那个<systemPath> 我使用,${JAVA_HOME}还是获取不到,而且我的系统环境变量配置的无错误,但依旧获取不到,然后我就改为了绝对路径.
- 到最后使用maven在centos上编译成功时,发现这个其实不修改也是可以.

```
<dependency>
  <groupId>jdk.tools</groupId>
  <artifactId>jdk.tools</artifactId>
  <version>1.6</version>
  <scope>system</scope>
  <systemPath>D:/java/lib/tools.jar</systemPath>
</dependency>
```

**问题二:**
> 从git上获取得到spatialHadoop的源文件后,使用Maven的命令【mvn compile】编译源码后未出错,但使用【mvn assembly:assembly】打包时出了好多错误信息.
> 一直没有打包成功.

- **解决办法:**
- [这篇博客提醒了我](https://extendswind.top/posts/technical/spatialhadoop_compile_and_run/)
- 无意中看到这篇博客中提到,这个spatialHadoop的源码在使用【mvn assembly:assembly】打包时,其源码中的测试的相关类会出现错误.
  于是乎,我尝试在打包时忽略这些测试的文件,那么怎么在打包时忽略这些文件呢?那就要找Maven在打包时怎么忽略测试文件就行.在pom.xml中添加
  如下配置.
```
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>2.4.2</version>
        <configuration>
          <skipTests>true</skipTests>
        </configuration>
    </plugin>

```
- 如果打包成功,怎会在项目中的target目录下看到有如下文件:
```
    [MHadoop@master spatialhadoop2]$ cd target/
    [MHadoop@master target]$ ls
    apidocs                 maven-status
    archive-tmp             spatialhadoop-2.4.3-SNAPSHOT-bin.tar.gz
    classes                 spatialhadoop-2.4.3-SNAPSHOT.jar
    generated-sources       spatialhadoop-2.4.3-SNAPSHOT-javadoc.jar
    generated-test-sources  spatialhadoop-2.4.3-SNAPSHOT-sources.jar
    javadoc-bundle-options  spatialhadoop-2.4.3-SNAPSHOT-uber.jar
    maven-archiver          test-classes

```

**问题三:**

>在我使用mvn编译-打包完成后,我将这个`spatialhadoop-2.4.3-SNAPSHOT-bin.tar.gz`解压到hadoop集群的各个节点的$HADOOP_HOME的目录下后,
>准备使用`bin/shadoop`进行测试时,总出现如下面显示的这个错误.
>
>[MHadoop@master hadoop]$ bin/shadoop
>
>: 没有那个文件或目录
>
 

- **解决办法**
- 我在网上搜了一些关于这个问题的解决方法,但和我这个情况不太相符.我把网上说的问题压缩了一下：大多都是在说是这个shadoop文件的编码格式问题.
  于是乎,我把我第一次在官网上下载的jar包中bin下的shadoop文件替换了它,然后它就能运行了.

**问题四:**
> 在我使用 `bin/shadoop hadoopviz`,在前端看可视化的点后,使用`ctrl + Z/C`结束此时的命令后,再次使用`bin/shadoop hadoopviz`
> 就会提示端口8889地址被占用的问题,如何解决呢?如下所示:
- 错误信息
```
[MHadoop@master datas]$ hadoop jar spatialhadoop-2.4.3-SNAPSHOT.jar hadoopviz
19/08/28 17:46:11 INFO mortbay.log: Logging to org.slf4j.impl.Log4jLoggerAdapter(org.mortbay.log) via org.mortbay.log.Slf4jLog
19/08/28 17:46:11 INFO mortbay.log: jetty-6.1.26
19/08/28 17:46:11 WARN mortbay.log: failed SocketConnector@0.0.0.0:8889: java.net.BindException: 地址已在使用
19/08/28 17:46:11 WARN mortbay.log: failed Server@6586f87: java.net.BindException: 地址已在使用
java.net.BindException: 地址已在使用
	at java.net.PlainSocketImpl.socketBind(Native Method)
	at java.net.AbstractPlainSocketImpl.bind(AbstractPlainSocketImpl.java:376)
	at java.net.ServerSocket.bind(ServerSocket.java:376)
	at java.net.ServerSocket.<init>(ServerSocket.java:237)
	at java.net.ServerSocket.<init>(ServerSocket.java:181)
	at org.mortbay.jetty.bio.SocketConnector.newServerSocket(SocketConnector.java:80)
	at org.mortbay.jetty.bio.SocketConnector.open(SocketConnector.java:73)
	at org.mortbay.jetty.AbstractConnector.doStart(AbstractConnector.java:283)
	at org.mortbay.jetty.bio.SocketConnector.doStart(SocketConnector.java:147)
	at org.mortbay.component.AbstractLifeCycle.start(AbstractLifeCycle.java:50)
	at org.mortbay.jetty.Server.doStart(Server.java:235)
	at org.mortbay.component.AbstractLifeCycle.start(AbstractLifeCycle.java:50)
	at edu.umn.cs.spatialHadoop.visualization.HadoopvizServer.startServer(HadoopvizServer.java:78)
	at edu.umn.cs.spatialHadoop.visualization.HadoopvizServer.main(HadoopvizServer.java:383)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
	at org.apache.hadoop.util.ProgramDriver$ProgramDescription.invoke(ProgramDriver.java:71)
	at org.apache.hadoop.util.ProgramDriver.run(ProgramDriver.java:144)
	at org.apache.hadoop.util.ProgramDriver.driver(ProgramDriver.java:152)
	at edu.umn.cs.spatialHadoop.operations.Main.main(Main.java:137)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
	at org.apache.hadoop.util.RunJar.run(RunJar.java:221)
	at org.apache.hadoop.util.RunJar.main(RunJar.java:136)
 
```
- **解决办法:**
 ```
 [MHadoop@master datas]$ lsof -i:8889
 COMMAND  PID    USER   FD   TYPE  DEVICE SIZE/OFF NODE NAME
 java    1301 MHadoop  196u  IPv4 3654346      0t0  TCP *:ddi-tcp-2 (LISTEN)
 java    1301 MHadoop  197u  IPv4 3668461      0t0  TCP master.hadoop:ddi-tcp-2->172.21.3.78:53161 (CLOSE_WAIT)
 java    1301 MHadoop  198u  IPv4 3668462      0t0  TCP master.hadoop:ddi-tcp-2->172.21.3.78:53162 (CLOSE_WAIT)
 java    1301 MHadoop  199u  IPv4 3660926      0t0  TCP master.hadoop:ddi-tcp-2->172.21.3.78:53164 (CLOSE_WAIT)
 java    1301 MHadoop  200u  IPv4 3668463      0t0  TCP master.hadoop:ddi-tcp-2->172.21.3.78:53165 (CLOSE_WAIT)
 java    1301 MHadoop  201u  IPv4 3668464      0t0  TCP master.hadoop:ddi-tcp-2->172.21.3.78:53166 (CLOSE_WAIT)
 java    1301 MHadoop  202u  IPv4 3668465      0t0  TCP master.hadoop:ddi-tcp-2->172.21.3.78:53167 (CLOSE_WAIT)
 [MHadoop@master datas]$ lsof -i:1301
 [MHadoop@master datas]$ kill -9 1301
 [MHadoop@master datas]$ lsof -i:1301
 [1]+  已杀死               hadoop jar spatialhadoop-2.4.3-SNAPSHOT.jar hadoopviz
 [MHadoop@master datas]$ lsof -i:8889
 ```

**问题五:**
 >bin/shadoop readfile test.grid 读取文件出错,
- 报错信息:
```
[MHadoop@master hadoop]$ bin/shadoop readfile test.grid
java.lang.ArithmeticException: / by zero
	at edu.umn.cs.spatialHadoop.operations.LocalSampler.sampleLocal(LocalSampler.java:101)
	at edu.umn.cs.spatialHadoop.operations.LocalSampler.sampleLocal(LocalSampler.java:72)
	at edu.umn.cs.spatialHadoop.OperationsParams.autoDetectShape(OperationsParams.java:621)
	at edu.umn.cs.spatialHadoop.OperationsParams.getShape(OperationsParams.java:341)
	at edu.umn.cs.spatialHadoop.OperationsParams.getShape(OperationsParams.java:346)
	at edu.umn.cs.spatialHadoop.OperationsParams.<init>(OperationsParams.java:92)
	at edu.umn.cs.spatialHadoop.OperationsParams.<init>(OperationsParams.java:85)
	at edu.umn.cs.spatialHadoop.ReadFile.main(ReadFile.java:35)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
	at org.apache.hadoop.util.ProgramDriver$ProgramDescription.invoke(ProgramDriver.java:71)
	at org.apache.hadoop.util.ProgramDriver.run(ProgramDriver.java:144)
	at org.apache.hadoop.util.ProgramDriver.driver(ProgramDriver.java:152)
	at edu.umn.cs.spatialHadoop.operations.Main.main(Main.java:137)
```
- **解决办法:**
   - 一开始,我在hadoop集群中的一个master节点中，中解压的spatialHadoop的jar包的,奇怪的是，官网上的前两个命令可以运行,剩下的就不可以了
    就报和上面类似的错误,后来我看了一篇老外写的博客[地址](http://aseldawy.blogspot.com/2015/01/installing-spatialhadoop-on-existing.html).这篇写了spatailHadoop是如何安装在Hadoop的？
    写了两种方式,我使用的是和官网给出的方式一样,还有一种是使用hadoop jar 运行的方式。从这篇博客得到spatailHadoop是需要部署到每个节点的。:)
   - 哈哈，后来觉得这个错误的真正原因是: 这个文件是空的.
   
**问题六:**   

>使用命令 gplot  绘制索引图时出错
- 报错信息如下:

```
[MHadoop@master datas]$ hadoop jar spatialhadoop-2.4.3-SNAPSHOT.jar gplot test_Output.rtree/_master.rtree 8-28-1.png shape:edu.umn.cs.spatialHadoop.indexing.Partition  color:red
  19/08/28 16:48:56 INFO mapreduce.RTreeRecordReader3: Open a SpatialRecordReader to split: test_Output.rtree/_master.rtree:0+99
  java.lang.RuntimeException: Incorrect signature for RTree
  	at edu.umn.cs.spatialHadoop.mapreduce.RTreeRecordReader3.initialize(RTreeRecordReader3.java:148)
  	at edu.umn.cs.spatialHadoop.visualization.SingleLevelPlot$1.run(SingleLevelPlot.java:559)
  	at edu.umn.cs.spatialHadoop.visualization.SingleLevelPlot$1.run(SingleLevelPlot.java:537)
  	at edu.umn.cs.spatialHadoop.util.Parallel.forEach(Parallel.java:84)
  	at edu.umn.cs.spatialHadoop.util.Parallel.forEach(Parallel.java:65)
  	at edu.umn.cs.spatialHadoop.visualization.SingleLevelPlot.plotLocal(SingleLevelPlot.java:537)
  	at edu.umn.cs.spatialHadoop.visualization.SingleLevelPlot.plot(SingleLevelPlot.java:643)
  	at edu.umn.cs.spatialHadoop.visualization.GeometricPlot.plot(GeometricPlot.java:117)
  	at edu.umn.cs.spatialHadoop.visualization.GeometricPlot.main(GeometricPlot.java:219)
  	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
  	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
  	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
  	at java.lang.reflect.Method.invoke(Method.java:606)
  	at org.apache.hadoop.util.ProgramDriver$ProgramDescription.invoke(ProgramDriver.java:71)
  	at org.apache.hadoop.util.ProgramDriver.run(ProgramDriver.java:144)
  	at org.apache.hadoop.util.ProgramDriver.driver(ProgramDriver.java:152)
  	at edu.umn.cs.spatialHadoop.operations.Main.main(Main.java:137)
  	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
  	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
  	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
  	at java.lang.reflect.Method.invoke(Method.java:606)
  	at org.apache.hadoop.util.RunJar.run(RunJar.java:221)
  	at org.apache.hadoop.util.RunJar.main(RunJar.java:136)
```
- **解决办法:**
- 该进行索引绘图的问题虽没真正解决,但这个`java.lang.RuntimeException: Incorrect signature for RTree`报错也算告一段落了,
   此时的spatialHadoop为官网上给出的jar包,我使用了Maven编译后的jar进行尝试,就没出现这个错误提示了.
   
**问题七:**

>这个错误,出现在我使用yum下载[QGIS](https://www.qgis.org/en/site/forusers/download.html)安装包时爆出的错误.我嫌下载的有点慢,中途直接`ctrl+Z/C`
>停了,后来才知道没网了才这么慢.
>
>/var/run/yum.pid 已被锁定，PID 为 xxxx 的另一个程序正在运行的问题解决

- **解决办法:**
- 运行一下这个命令,`rm -f /var/run/yum.pid`,删除此时被锁的文件



 
 
 
 