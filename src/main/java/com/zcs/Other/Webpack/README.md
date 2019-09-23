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
 
``` 

```
#### 问题1.3
- webpack打包时出现的错误
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
#### 问题4

- 错误信息

``` 
D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app>webpack

D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app>"node"  "C:\Users\gistide\AppData\Roaming\npm\\node_modules\webpack\bin\webpack.js"
C:\Users\gistide\AppData\Roaming\npm\node_modules\webpack-cli\bin\cli.js:93
                                throw err;
                                ^

Error: Cannot find module '@webassemblyjs/ast'
Require stack:
- D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app\node_modules\_webpack@4.40.2@webpack\lib\wasm\WebAssemblyParser.js
- D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app\node_modules\_webpack@4.40.2@webpack\lib\wasm\WebAssemblyModulesPlugin.js
- D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app\node_modules\_webpack@4.40.2@webpack\lib\WebpackOptionsApply.js
- D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app\node_modules\_webpack@4.40.2@webpack\lib\webpack.js
- D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app\webpack.config.js
- C:\Users\gistide\AppData\Roaming\npm\node_modules\webpack-cli\bin\utils\convert-argv.js
- C:\Users\gistide\AppData\Roaming\npm\node_modules\webpack-cli\bin\cli.js
- C:\Users\gistide\AppData\Roaming\npm\node_modules\webpack\bin\webpack.js
    at Function.Module._resolveFilename (internal/modules/cjs/loader.js:772:15)
    at Function.Module._load (internal/modules/cjs/loader.js:677:27)
    at Module.require (internal/modules/cjs/loader.js:830:19)
    at require (C:\Users\gistide\AppData\Roaming\npm\node_modules\webpack-cli\node_modules\_v8-compile-cache@2.0.3@v8-compile-cache\v8-compile-cache.js:1
61:20)
    at Object.<anonymous> (D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app\node_modules\_webpack@4.40.2@webpack\lib\wasm\WebAssemblyParser.js:7:1
1)
    at Module._compile (C:\Users\gistide\AppData\Roaming\npm\node_modules\webpack-cli\node_modules\_v8-compile-cache@2.0.3@v8-compile-cache\v8-compile-ca
che.js:192:30)
    at Object.Module._extensions..js (internal/modules/cjs/loader.js:947:10)
    at Module.load (internal/modules/cjs/loader.js:790:32)
    at Function.Module._load (internal/modules/cjs/loader.js:703:12)
    at Module.require (internal/modules/cjs/loader.js:830:19)
    at require (C:\Users\gistide\AppData\Roaming\npm\node_modules\webpack-cli\node_modules\_v8-compile-cache@2.0.3@v8-compile-cache\v8-compile-cache.js:1
61:20)
    at Object.<anonymous> (D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app\node_modules\_webpack@4.40.2@webpack\lib\wasm\WebAssemblyModulesPlugin
.js:8:27)
    at Module._compile (C:\Users\gistide\AppData\Roaming\npm\node_modules\webpack-cli\node_modules\_v8-compile-cache@2.0.3@v8-compile-cache\v8-compile-ca
che.js:192:30)
    at Object.Module._extensions..js (internal/modules/cjs/loader.js:947:10)
    at Module.load (internal/modules/cjs/loader.js:790:32)
    at Function.Module._load (internal/modules/cjs/loader.js:703:12)
    at Module.require (internal/modules/cjs/loader.js:830:19)
    at require (C:\Users\gistide\AppData\Roaming\npm\node_modules\webpack-cli\node_modules\_v8-compile-cache@2.0.3@v8-compile-cache\v8-compile-cache.js:1
61:20)
    at Object.<anonymous> (D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app\node_modules\_webpack@4.40.2@webpack\lib\WebpackOptionsApply.js:11:34)
    at Module._compile (C:\Users\gistide\AppData\Roaming\npm\node_modules\webpack-cli\node_modules\_v8-compile-cache@2.0.3@v8-compile-cache\v8-compile-ca
che.js:192:30)
    at Object.Module._extensions..js (internal/modules/cjs/loader.js:947:10)
    at Module.load (internal/modules/cjs/loader.js:790:32)
    at Function.Module._load (internal/modules/cjs/loader.js:703:12)
    at Module.require (internal/modules/cjs/loader.js:830:19)
    at require (C:\Users\gistide\AppData\Roaming\npm\node_modules\webpack-cli\node_modules\_v8-compile-cache@2.0.3@v8-compile-cache\v8-compile-cache.js:1
61:20)
    at Object.<anonymous> (D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app\node_modules\_webpack@4.40.2@webpack\lib\webpack.js:10:29)
    at Module._compile (C:\Users\gistide\AppData\Roaming\npm\node_modules\webpack-cli\node_modules\_v8-compile-cache@2.0.3@v8-compile-cache\v8-compile-ca
che.js:192:30)
    at Object.Module._extensions..js (internal/modules/cjs/loader.js:947:10)
    at Module.load (internal/modules/cjs/loader.js:790:32)
    at Function.Module._load (internal/modules/cjs/loader.js:703:12) {
  code: 'MODULE_NOT_FOUND',
  requireStack: [
    'D:\\Git仓库\\Note\\src\\main\\java\\com\\zcs\\Other\\Webpack\\app\\node_modules\\_webpack@4.40.2@webpack\\lib\\wasm\\WebAssemblyParser.js',
    'D:\\Git仓库\\Note\\src\\main\\java\\com\\zcs\\Other\\Webpack\\app\\node_modules\\_webpack@4.40.2@webpack\\lib\\wasm\\WebAssemblyModulesPlugin.js',
    'D:\\Git仓库\\Note\\src\\main\\java\\com\\zcs\\Other\\Webpack\\app\\node_modules\\_webpack@4.40.2@webpack\\lib\\WebpackOptionsApply.js',
    'D:\\Git仓库\\Note\\src\\main\\java\\com\\zcs\\Other\\Webpack\\app\\node_modules\\_webpack@4.40.2@webpack\\lib\\webpack.js',
    'D:\\Git仓库\\Note\\src\\main\\java\\com\\zcs\\Other\\Webpack\\app\\webpack.config.js',
    'C:\\Users\\gistide\\AppData\\Roaming\\npm\\node_modules\\webpack-cli\\bin\\utils\\convert-argv.js',
    'C:\\Users\\gistide\\AppData\\Roaming\\npm\\node_modules\\webpack-cli\\bin\\cli.js',
    'C:\\Users\\gistide\\AppData\\Roaming\\npm\\node_modules\\webpack\\bin\\webpack.js'
  ]
}

```

#### 问题5
``` 
D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app>webpack runoob1.js bundle.js

D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app>"node"  "C:\Users\gistide\AppData\Roaming\npm\\node_modules\webpack\bin\webpack.js" runoob1.js bu
ndle.js
D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app\node_modules\_webpack-cli@3.3.9@webpack-cli\bin\cli.js:93
                                throw err;
                                ^

Error: Cannot find module 'D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app\node_modules\_webpack@4.40.2@webpack\node_modules\@webassemblyjs\helpe
r-module-context\lib\index.js'. Please verify that the package.json has a valid "main" entry
    at tryPackage (internal/modules/cjs/loader.js:288:19)
    at Function.Module._findPath (internal/modules/cjs/loader.js:515:18)
    at Function.Module._resolveFilename (internal/modules/cjs/loader.js:759:27)
    at Function.Module._load (internal/modules/cjs/loader.js:677:27)
    at Module.require (internal/modules/cjs/loader.js:830:19)
    at require (D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app\node_modules\_v8-compile-cache@2.0.3@v8-compile-cache\v8-compile-cache.js:161:20)
    at Object.<anonymous> (D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app\node_modules\_webpack@4.40.2@webpack\lib\wasm\WebAssemblyParser.js:11:
5)
    at Module._compile (D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app\node_modules\_v8-compile-cache@2.0.3@v8-compile-cache\v8-compile-cache.js
:192:30)
    at Object.Module._extensions..js (internal/modules/cjs/loader.js:947:10)
    at Module.load (internal/modules/cjs/loader.js:790:32)
    at Function.Module._load (internal/modules/cjs/loader.js:703:12)
    at Module.require (internal/modules/cjs/loader.js:830:19)
    at require (D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app\node_modules\_v8-compile-cache@2.0.3@v8-compile-cache\v8-compile-cache.js:161:20)
    at Object.<anonymous> (D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app\node_modules\_webpack@4.40.2@webpack\lib\wasm\WebAssemblyModulesPlugin
.js:8:27)
    at Module._compile (D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app\node_modules\_v8-compile-cache@2.0.3@v8-compile-cache\v8-compile-cache.js
:192:30)
    at Object.Module._extensions..js (internal/modules/cjs/loader.js:947:10)
    at Module.load (internal/modules/cjs/loader.js:790:32)
    at Function.Module._load (internal/modules/cjs/loader.js:703:12)
    at Module.require (internal/modules/cjs/loader.js:830:19)
    at require (D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app\node_modules\_v8-compile-cache@2.0.3@v8-compile-cache\v8-compile-cache.js:161:20)
    at Object.<anonymous> (D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app\node_modules\_webpack@4.40.2@webpack\lib\WebpackOptionsApply.js:11:34)
    at Module._compile (D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app\node_modules\_v8-compile-cache@2.0.3@v8-compile-cache\v8-compile-cache.js
:192:30)
    at Object.Module._extensions..js (internal/modules/cjs/loader.js:947:10)
    at Module.load (internal/modules/cjs/loader.js:790:32)
    at Function.Module._load (internal/modules/cjs/loader.js:703:12)
    at Module.require (internal/modules/cjs/loader.js:830:19)
    at require (D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app\node_modules\_v8-compile-cache@2.0.3@v8-compile-cache\v8-compile-cache.js:161:20)
    at Object.<anonymous> (D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app\node_modules\_webpack@4.40.2@webpack\lib\webpack.js:10:29)
    at Module._compile (D:\Git仓库\Note\src\main\java\com\zcs\Other\Webpack\app\node_modules\_v8-compile-cache@2.0.3@v8-compile-cache\v8-compile-cache.js
:192:30)
    at Object.Module._extensions..js (internal/modules/cjs/loader.js:947:10) {
  code: 'MODULE_NOT_FOUND',
  path: 'D:\\Git仓库\\Note\\src\\main\\java\\com\\zcs\\Other\\Webpack\\app\\node_modules\\_webpack@4.40.2@webpack\\node_modules\\@webassemblyjs\\helper-m
odule-context\\package.json',
  requestPath: '@webassemblyjs/helper-module-context'
}


```

