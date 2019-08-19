
#### SpatialHadoop 在Hadoop集群中的安装

**注意:**
- 要选择支持Hadoop的相应的版本的进行下载,也就是与Hadoop的版本要兼容,同时还有可进行扩展的jar包
- 我是用的是搭建在 Centos 上的Hadoop集群,所以下面描述的相关命令均在Centos上

1) SpatialHadoop 下载地址: [点击这里](http://spatialhadoop.cs.umn.edu/#downloads)

2) 下载后,上传到Hadoop的目录下，进行解压【tar -vxf spatialhadoop-2.4.2-bin.tar.gz】:

```
LICENSE.txt
README.md
etc/
etc/hadoop/
bin/
etc/hadoop/spatial-site.xml
etc/hadoop/spatial-site.xml.template
bin/shadoop
share/hadoop/common/lib/spatialhadoop-2.4.2.jar
share/hadoop/common/lib/jts-1.13.jar
share/hadoop/common/lib/esri-geometry-api-1.2.1.jar
share/hadoop/common/lib/javax.mail-1.5.5.jar
share/hadoop/common/lib/javax.mail-api-1.5.5.jar
```
3) 成功后，你就可以在上面显示的路径中看到相应的文件
4) 在`etc/hadoop/hadoop-env.sh`中配置java的环境变量
5) 下面就可以在Hadoop集群运行的情况下,尝试一下官网给的案例了


