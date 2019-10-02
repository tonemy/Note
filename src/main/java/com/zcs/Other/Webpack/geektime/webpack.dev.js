const path = require('path')
const gplob = require('glob')
const {CleanWebpackPlugin} = require('clean-webpack-plugin')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const webpack = require('webpack')


//多页面输出应用打包
const setMPA = () => {
    const entry = {};
    const HtmlWebpackPlugins = [];
    const entryFiles = gplob.sync(path.join(__dirname, './src/*/index.js'));
    Object.keys(entryFiles)
        .map((index) => {
            const entryFile = entryFiles[index];
            const match = entryFile.match(/geektime\/src\/(.*)\/index\.js/);
            const pageName = match && match[1];
            // console.log('match',match);
            entry[pageName] = entryFile;
            // console.log('fileName',fileName)
            HtmlWebpackPlugins.push(
                new HtmlWebpackPlugin({
                    template: path.join(__dirname, `./src/${pageName}/index.html`),
                    filename: `${pageName}.html`,
                    chunks: [pageName],
                    inject: true,
                    //html-文件压缩
                    minify: {
                        html5: true,
                        collapseWhitespace: true,
                        preserveLineBreak: false,
                        minifyCSS: true,
                        minifyJS: true,
                        removeComments: false
                    }
                })
            )
        })
    // console.log(entryFiles);
    return {
        entry,
        HtmlWebpackPlugins
    }
}
const {entry, HtmlWebpackPlugins} = setMPA();

module.exports = {
    entry: entry,
    output: {
        path: path.join(__dirname, 'dist'),
        filename: '[name].js'
    },
    mode: 'development',
    module: {
        rules: [
            {
                test: /.js$/,
                use: 'babel-loader'
            },
            {
                test: /.css$/,
                use: [
                    'style-loader',
                    'css-loader'
                ]
            },
            {
                test: /.(png|jpg|jepg|gif)$/,
                use: [
                    'file-loader'
                ]
            },
            {
                test: /.(woff|woff2|eot|ttf|otf)$/,
                use: [
                    'file-loader'
                ]
            }
        ]
    },
    plugins: [
        //清理输出目录
        new CleanWebpackPlugin(),
    ].concat(HtmlWebpackPlugins),
    //局部更新
    devServer: {
        contentBase: './dist',
        hot: true
    },
    devtool: 'source-map'
}