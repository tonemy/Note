### Webpack 

---

### 1. Webpack 的安装

- 需要安装`node.js`环境,详细教程[点击这里](https://www.runoob.com/nodejs/nodejs-install-setup.html);
- 新版的`node.js`集成了`npm`,相关命令
    - `npm -v`,查看版本
    - `npm install npm -g`, npm升级为最新的版本
    - `npm install -g cnpm --registry=https://registry.npm.taobao.org`,使用淘宝镜像的命令
    - `npm install express`, 本地安装
    - `npm install express -g`, 全局安装
- webpack项目构建
    - `mkdir webpack-demo && cd webpack-demo`
    - `npm init -y`
    - `npm install webpack webpack-cli --save-dev`  
    - `cnpm i @babel/core @babel/preset-env babel-loader -D` :这个命令就比较有趣了, `i` 是 `install`简称, -D 是`--save-dev`的简称, 主要安装了解析es6的相关包
       

### 2. 资源管理    


### 3. es6语法

[参考地址](http://caibaojian.com/es6/string.html)

### 4. 文件指纹

### 5. 文件压缩
- html 
- CSS
- JS


### 6. CSS 自动补齐前缀处理
 

### 7. 移动端css px转rem

### 8. 资源内联

### 9. 多页面通用打包

### 10. sourcemap 的使用

### 11. 提取页面公共资源

### 12. tree shaking

### 13. 代码分割和动态import

### 14. webpack 使用eslint

### 附录:

---
#### 问题1.1
**问题描述:**

怎么确定什么时候使用`cnpm install --save`,什么时候使用`cnpm install --save-dev` ?

**问题解决:**



#### 问题1.2

**问题描述：**

使用命令`webpack runoob1.js bundle.js`
**错误信息：**

``` 

D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app>webpack runoob1.js bundle.js

D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app>"node"  "C:\Users\gistide\AppData\Roaming\npm\\node_modules\webpack\bin\webpack.js" runoob1.js bu
ndle.js
Hash: dc36b68cb945931428f8
Version: webpack 4.40.2
Time: 332ms
Built at: 2019-09-23 17:40:56
 1 asset
Entrypoint main = main.js
[0] multi ./runoob1.js bundle.js 40 bytes {0} [built]
[1] ./runoob1.js 27 bytes {0} [built]

WARNING in configuration
The 'mode' option has not been set, webpack will fallback to 'production' for this value. Set 'mode' option to 'development' or 'production' to enable de
faults for each environment.
You can also set it to 'none' to disable any default behavior. Learn more: https://webpack.js.org/configuration/mode/

ERROR in multi ./runoob1.js bundle.js
Module not found: Error: Can't resolve 'bundle.js' in 'D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app'
 @ multi ./runoob1.js bundle.js main[1]

```
**问题解决:**

我的webpack版本过高的原因，原来的命令已经不适用了。更换一下打包命令即可,`webpack runoob1.js -o bundle.js`

#### 问题1.3
**问题描述**

在编写webpack.config.js 文件后时，运行webpack打包时出现的错误

**错误信息**

```
D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app>webpack

D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app>"node"  "C:\Users\gistide\AppData\Roaming\npm\\node_modules\webpack\bin\webpack.js"
Invalid configuration object. Webpack has been initialised using a configuration object that does not match the API schema.
 - configuration.module has an unknown property 'loaders'. These properties are valid:
   object { defaultRules?, exprContextCritical?, exprContextRecursive?, exprContextRegExp?, exprContextRequest?, noParse?, rules?, strictExportPresence?,
 strictThisContextOnImports?, unknownContextCritical?, unknownContextRecursive?, unknownContextRegExp?, unknownContextRequest?, unsafeCache?, wrappedCont
extCritical?, wrappedContextRecursive?, wrappedContextRegExp? }
   -> Options affecting the normal modules (`NormalModuleFactory`).
```

**问题解决**

将webpack.config.js 文件中的loaders 改为 rules即可。

#### 问题2.1

**问题描述** 

在webpack中文网中的起步和管理资源部分的`index.html`的引入的js部分,有些不理解`main.js`和`bundle.js`的区别

- 管理资源的`index.html`
```$xslt
<!doctype html>
  <html>
    <head>
-    <title>Getting Started</title>
+    <title>Asset Management</title>
    </head>
    <body>
      <script src="./bundle.js"></script>
    </body>
  </html>
```

- 起步中的`index.html`

```$xslt
  <!doctype html>
  <html>
   <head>
     <title>起步</title>
-    <script src="https://unpkg.com/lodash@4.16.6"></script>
   </head>
   <body>
-    <script src="./src/index.js"></script>
+    <script src="main.js"></script>
   </body>
  </html>
```

#### 问题2.2

**问题描述:**

在webpack中的资源管理的加载图片部分,案例给出的`index.js`和`style.css`,的元素div标签中都添加了一个图片
是不是重复了?

#### 问题2.3
**问题描述:**
在尝试***webpack官网文档中的管理输出***中的插件`clean-webpack-plugin` ***清理/dist文件夹***出现错误。

**错误信息:**

`CleanWebpackPlugin is not a constructor`

**问题解决:**
```html

// 正确写法
 
const { CleanWebpackPlugin } = require("clean-webpack-plugin");
 
...
 
plugins: [
    new CleanWebpackPlugin()
]

```

#### 问题2.4
**问题描述:**

在webpack官方文档中的***开发***的***调整文本编辑器***，在`IntelliJ`找不到提到的`IntelliJ - 在首选项(preferences)中使用搜索，查找到 "safe write" 并且禁用它`。

**问题解决:**
- `File/Setting/Appeaance&Behavior/System setting` 页面中就可以看到这个信息:`Use "safe write" (save changes to a temporary file first)`,剩下的就看悟性了.
- [参考地址](https://jingyan.baidu.com/article/eae07827d8fb075fec5485bf.html)

#### 问题2.5
**问题描述:**

在webpack的官方文档中的生产环境的构建

**报错信息:**

ERROR in app.bundle.js from UglifyJs
Unexpected token: name «src_element», expected: punc «;» [app.bundle.js:546,4]
Child html-webpack-plugin for "index.html":

**问题解决:**
- 修改`webpack.config.js`配置文件

```
    {
        test: /\.js$/,
        use: [{
          loader: 'babel-loader',
          options: {
             presets: ['es2015']
          }
        }],
        exclude: /node_modules/
    }
```
- 根目录下添加[.babelrc]文件

``` 
{
  "presets": ["es2015"]
}
```
#### 问题 2.6

**问题描述：**
在尝试webpack文档中的`代码分离`的`防止重复`时，使用插件`CommonsChunkPlugin`爆出这个错误。

Error: webpack.optimize.CommonsChunkPlugin has been removed, please use config.optimization.splitChunks instead.

**问题解决：**

webpack4 移除了 CommonsChunkPlugin，所以需要作相应的修改。由于官方文档并没有及时更新 。

``` 
-  const webpack = require('webpack');
 
......
  module.exports = {
    plugins: [
-     new webpack.optimize.CommonsChunkPlugin({
-       name: 'common' // 指定公共 bundle 的名称。
-     })
    ],
 
+   optimization: {
+     splitChunks: {
+       name: 'common'
+     }
+   },

```

#### 2.7引入字体的问题

**问题描述:**

不知字体文件是什么，在哪儿下载的？下面的两个字体下载网址好像不太对


- [下载字体网站1](http://sc.chinaz.com/)
- [下载字体网站2](http://www.fonts.net.cn/fonts-zh-1.html)
- [字体转换的网站](https://www.fontke.com/tool/convfont/)

#### 2.8 webPack中的懒加载

**问题描述:**

对于懒加载还是不理解，而且我在尝试懒加载的例子时总报错

``` 
  button.onclick = e => import(/* webpackChunkName: "print" */ './print').then(module => {
                       ^
      var print = module.default;
     print();
  });

```

#### 3.1 webpack中的__filename 和__dirname的区别

**问题描述:**

不知道两者的区别是什么，表达的意思是什么？


- `__dirname`: 是node中的一个全局变量,可以获取当前文件的完整目录
- `__filename`: 是node中的一个全局变量，可以获取当前文件的绝对路径

#### 3.2 解析es6 是什么玩意?为什么要解析它?


#### 4.1 

**错误信息**
```
Invalid configuration object. Webpack has been initialised using a configuration object that does not match the API schema.
    - configuration.module.rules[2].use[0] has an unknown property 'filename'. These properties are valid:
      object { ident?, loader?, options?, query? }

```

#### 4.2 

**错误信息**

```
 Replace Autoprefixer browsers option to Browserslist config.
  Use browserslist key in package.json or .browserslistrc file.

  Using browsers option cause some error. Browserslist config 
  can be used for Babel, Autoprefixer, postcss-normalize and other tools.

  If you really need to use option, rename it to overrideBrowserslist.

  Learn more at:
  https://github.com/browserslist/browserslist#readme
  https://twitter.com/browserslist

```
**问题解决:**

- 最新的 autoprefixer 版本里面建议把 browserslist 写在 package.json 或者 . browserslistrc 文件里面去了（刚刚更新，😓）。
不过你可以直接把browsers改成overrideBrowserslist也可以哈
