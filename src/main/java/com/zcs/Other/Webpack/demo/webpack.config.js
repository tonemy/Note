const path = require('path')
const HtmlWebpackPlugin = require('html-webpack-plugin')

module.exports = {
    entry: {
        // 'aaa': __dirname +"/src/static/component/admin/js/admin.js",
        'bbb': __dirname + "/src/static/component/login/js/login.js"
    },
    output: {
        filename: '[name].bundle.js',
        path: path.resolve(__dirname, 'dist')
    },
    plugins: [
        new HtmlWebpackPlugin({
            // template: "./src/static/component/admin/admin.html",
           template: "./src/static/component/login/login.html"
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
    }
}