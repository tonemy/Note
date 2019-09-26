### 1.基础 
#### 1.1 jQuery安装(下载js文件到本地库的方式)

- [下载地址](https://jquery.com/download/)
- 以前记得总是找到一个网上的jquery的api代码后，我是将代码复制下来,然后再新建一个js文件，复制进去。
  现在其实进入提供的jquery的代码页面，右键->另保存->就可得到jquery的js文件

#### 1.2 Session-1
- jQuery 的选择器,其作用就是可以选择特定需要的元素标签
- 例子: 
    - $(this),选取当前HTML元素
    - $("ul li:first-child")，选取每个 <ul> 元素的第一个 <li> 元素
    - $("tr:even")，选取偶数位置的<tr>元素

#### 1.3 Session-2
- jQuery 的事件，包括点击事件、键盘事件、表单事件、窗口事件
- 这些事件都与常用的点击事件类似，就是使用的场景不同罢了

### 2.效果

- 注意一下这几个效果的表现情况是怎么样的就可以了,还有就是speed参数的规定时长


#### 2.1 淡入淡出
- speed参数的规定效果的时长
    - slow = 600ms
    - normal = 400ms
    - fast = 200ms
- fadeIn、fadeOut、fadeToggle、fadeTo
 
#### 2.2 滑动效果
- slideUp、slideDown、slideToggle

#### 2.3 动画
```
  $(selector).animate({params},speed,callback);
```
- 这个就比较神奇了，可以做标签的大小、颜色等属性的变化


#### 2.5 停止动画
``` 
  $(selector).stop(stopAll,goToEnd);
```

#### 2.6 CallBack 方法

- 注意以下两个代码的区别:没有使用回调函数时，alert会先执行


``` 
    $("button").click(function(){
      $("p").hide("slow",function(){
        alert("段落现在被隐藏了");
      });
    });
```

``` 
    $("button").click(function(){
      $("p").hide(1000);
      alert("段落现在被隐藏了");
    });
```

#### 2.7 链
- 这个就是一个思想，就是将几个事件结合在一起用，展示出来的一个接连起来的效果

### 3 HTML

#### 3.1 捕获
- 捕获各个标签元素中的值
- attr()、val()、text()、html()

#### 3.2 设置
- 设置内容和属性
- attr()、val()、text()、html(),在这几个中加一个callback函数,需注意的是callback的参数：第一个参数为当前元素的下标，第二参数为元素的值

#### 3.3 添加元素
- append、prepend 在被选择元素内部嵌入

- after、before 在被选择元素外面追加

``` 
function afterText(){
    var txt1="<b>I </b>";                    // 使用 HTML 创建元素
    var txt2=$("<i></i>").text("love ");     // 使用 jQuery 创建元素
    var txt3=document.createElement("big");  // 使用 DOM 创建元素
    txt3.innerHTML="jQuery!";
    $("img").after([txt1,txt2,txt3]);          // 在图片后添加文本
}
```

#### 3.4 删除元素

- remove、删除被选元素及子元素
- empty、删除子元素
- 过滤被删除的元素,这个与前两个稍许不同，需注意其使用方法


#### 3.5 CSS类

- 主要是为几个标签添加几个写好的样式

#### 3.6 CSS属性

- 就是为标签添加CSS属性,与上一个的不同之处，前一个侧重于多个,直接调用，后一个可以方便添加


### 4 遍历
- 总结来说就是，纵向遍历与横向遍历
- 纵向遍历
    - 祖先（parent、parents、parentsUtil）
    - 后代（children、find）
- 横向遍历
    - siblings
    - next
    - nextAll
    - nextUntil
    - prev
    - preAll
    - preUntil
    - filter
    - last
    - first
    - eq

### 5 Ajax
- load
- get
- post