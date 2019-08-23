
### SpatialHadoop 在Hadoop集群中的安装

**注意:**
- 要选择支持Hadoop的相应的版本的进行下载,也就是与Hadoop的版本要兼容,同时还有可进行扩展的jar包
- 我是用的是搭建在 Centos 上的Hadoop集群,所以下面描述的相关命令均在Centos上

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

### Q&A

1) bin/shadoop readfile test.grid 读取文件出错
```shell script
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
2) 绘制索引图时总报这个错误
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
