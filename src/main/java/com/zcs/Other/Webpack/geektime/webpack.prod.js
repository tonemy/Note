const path = require('path')
const MiniCssExtractPlugin = require('mini-css-extract-plugin')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const OptimizeCssAssetsWebpackPlugin = require('optimize-css-assets-webpack-plugin')
const {CleanWebpackPlugin} = require('clean-webpack-plugin')
const gplob = require('glob')

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
    entry:  entry,
    output: {
        path: path.join(__dirname, 'dist'),
        filename: '[name]_[chunkhash:8].js'
    },
    mode: 'none',
    module: {
        rules: [
            {
                test: /.js$/,
                use: 'babel-loader'
            },
            {
                test: /.css$/,
                use: [
                    MiniCssExtractPlugin.loader,
                    'css-loader',
                    //css 自动补齐前缀处理
                    {
                        loader: 'postcss-loader',
                        options:{
                            plugins: () => [
                                require('autoprefixer')({
                                    overrideBrowserslist : ['last 2 version', '>1%', 'ios 7']
                                })
                            ]
                        }
                    },
                    //移动端css px转rem
                    {
                        loader: 'px2rem-loader',
                        options: {
                            remUnit: 75,
                            remPrecision: 8
                        }
                    }
                ]
            },
            //图片字体-文件指纹
            {
                test: /.(png|jpg|jepg|gif)$/,
                use: [
                    {
                        loader: 'file-loader',
                        options:{
                            name: '[name]_[hash:8].[ext]'
                        }
                    }
                ]
            },
            {
                test: /.(woff|woff2|eot|ttf|otf)$/,
                use: [
                    {
                        loader: 'file-loader',
                        options:{
                            name: '[name]_[hash:8].[ext]'
                        }
                    }
                ]
            }
        ]
    },
    plugins: [
        //css-文件指纹
        new MiniCssExtractPlugin({
            filename: '[name]_[contenthash:8].css'
        }),

        //css-文件压缩
        new OptimizeCssAssetsWebpackPlugin({
            assetNameRegExp: /\.css$/g,
            cssProcessor: require('cssnano')
        }),
        //清理输出目录
        new CleanWebpackPlugin(),

    ].concat(HtmlWebpackPlugins),

    devtool: 'eval'
}