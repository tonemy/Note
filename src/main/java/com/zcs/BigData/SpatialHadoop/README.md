
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

### 2. spatialHadoop的各种操作
**注意的问题：** 
- 这个的操作前提是前面能够安装成功,可以通过几个简单的命令进行检测,[官网](http://spatialhadoop.cs.umn.edu/)上给的命令拿来检测一下即可.
- 输入的文件的默认格式是什么?
- 输入的文件的格式如何进行扩展的?
- 如果有的参数与值之间有冒号,则这个冒号与其无空格

1) `bin/shadoop index <inpath> <outpath> sindex:<sindex> shape:<shape> blocksize:<size> -overwrite ` 
   - 各个参数: 
     - `<inpath>`: 所需要输入的文件的路径(注意:这个文件需要在hdfs上)
     - `<outpath>`: 输出的文件的路径
     - `<sindex>` : 空间索引过程中是按哪种方式进行的( grid, rtree, r+tree, r*tree, r*tree+, str, str+, quadtree, kdtree, zcurve, or hilbert)
     - `<shape>` : 可以使用简短的名字的有`point`、`rectangle`,还有一些需要使用完整的类名[数据类型](http://spatialhadoop.cs.umn.edu/doc-2.3/edu/umn/cs/spatialHadoop/core/Shape.html),这些数据类型的使用主要根据应用场景而决定.
     - `blocksize:<size>`: 生成文件的块的大小, 默认情况使用默认的块的大小(我觉得默认情况下和hadoop配置块大小有关)
     - `-overwrite` : 如果命令中有这个参数,则在文件输出时,如果有和它重名的则会直接覆盖.   
     

2)  
3)  hadoop jar spatialhadoop-2.4.3-SNAPSHOT.jar hadoopviz

3) 其它的参考: [这个吧](https://github.com/aseldawy/spatialhadoop2/wiki/A-list-of-most-operations-in-SpatialHadoop)





### Q&A
1) 使用git下载spatialhadoop后, 在右侧栏中的maven功能窗口总出现这个错误: `- Missing artifact jdk.tools:jdk.tools:jar:1.6)`

   - 解决办法 
      - [参考网址](http://www.it1352.com/843130.html)
      - 主要在pom.xml中添加了相关的依赖,我系统本身jdk是1.8,但我按照这个网址上的添加后依然会报错提示:`1.6 和1.8 有矛盾`,然后我的添加配置如下
      - 关于那个<systemPath> 我使用,${JAVA_HOME}还是获取不到,而且我的系统环境变量配置的无错误,但依旧获取不到,然后我就改为了绝对路径.
      ```
      <dependency>
          <groupId>jdk.tools</groupId>
          <artifactId>jdk.tools</artifactId>
          <version>1.6</version>
          <scope>system</scope>
          <systemPath>D:/java/lib/tools.jar</systemPath>
      </dependency>
      ```
1) bin/shadoop readfile test.grid 读取文件出错

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
   - 解决办法:
       - 一开始,我在hadoop集群中的一个master节点中，中解压的spatialHadoop的jar包的,奇怪的是，官网上的前两个命令可以运行,剩下的就不可以了
        就报和上面类似的错误,后来我看了一篇老外写的博客[地址](http://aseldawy.blogspot.com/2015/01/installing-spatialhadoop-on-existing.html).这篇写了spatailHadoop是如何安装在Hadoop的？
        写了两种方式,我使用的是和官网给出的方式一样,还有一种是使用hadoop jar 运行的方式。从这篇博客得到spatailHadoop是需要部署到每个节点的。:)
       - 哈哈，后来觉得这个错误的真正原因是: 这个文件是空的.
2) 绘制图出错
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
3)
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
**解决办法:**
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

3) 绘制索引图时总报这个错误
```shell script
 
    19/08/22 10:10:04 INFO mapreduce.Job: Task Id : attempt_1566384496226_0016_m_000093_1, Status : FAILED
    Error: java.lang.NumberFormatException: empty String
        at sun.misc.FloatingDecimal.readJavaFormatString(FloatingDecimal.java:1020)
        at java.lang.Double.parseDouble(Double.java:540)
        at edu.umn.cs.spatialHadoop.io.TextSerializerHelper.consumeDouble(TextSerializerHelper.java:182)
        at edu.umn.cs.spatialHadoop.core.Rectangle.fromText(Rectangle.java:286)
        at edu.umn.cs.spatialHadoop.operations.Sampler$Map.map(Sampler.java:122)
        at edu.umn.cs.spatialHadoop.operations.Sampler$Map.map(Sampler.java:69)
        at org.apache.hadoop.mapred.MapRunner.run(MapRunner.java:54)
        at org.apache.hadoop.mapred.MapTask.runOldMapper(MapTask.java:450)
        at org.apache.hadoop.mapred.MapTask.run(MapTask.java:343)
        at org.apache.hadoop.mapred.YarnChild$2.run(YarnChild.java:163)
        at java.security.AccessController.doPrivileged(Native Method)
        at javax.security.auth.Subject.doAs(Subject.java:415)
        at org.apache.hadoop.security.UserGroupInformation.doAs(UserGroupInformation.java:1692)
        at org.apache.hadoop.mapred.YarnChild.main(YarnChild.java:158)

```
