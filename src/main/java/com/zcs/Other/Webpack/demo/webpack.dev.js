const path = require('path')
const HtmlWebpackPlugin = require('html-webpack-plugin')

module.exports = {
    entry: {
        'aaa': __dirname +"/src/static/component/admin/js/admin.js",
        // 'bbb': __dirname + "/src/static/component/login/js/login.js"
    },
    output: {
        filename: '[name].js',
        path: path.join(__dirname, 'dist')
    },
    mode: "development",
    plugins: [
        new HtmlWebpackPlugin({
            template: "./src/static/component/admin/admin.html",
           // template: "./src/static/component/login/login.html"
        })
    ],
    module: {
        rules: [
            {
                test: /\.css$/,
                use: [
                    'style-loader',
                    'css-loader'
                ]
            },
            {
                test: /\.(png)|(jpg)|(gif)|(woff)|(svg)|(eot)|(ttf)$/,
                use: ['url-loader']
            }
        ]
    },

    devServer: {
        contentBase: './dist'
    }
}