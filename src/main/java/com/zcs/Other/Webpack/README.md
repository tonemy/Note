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
    
### 附录:

--- 
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


