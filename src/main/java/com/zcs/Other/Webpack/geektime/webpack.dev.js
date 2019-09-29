const path = require('path')
const webpack = require('webpack')

module.exports = {
    entry: {
        index: path.join(__dirname, 'src/index.js'),
        search: './src/print.js'
    },
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
                test: /.(png|jpg|jepg|gif)/,
                use: [
                    'file-loader'
                ]
            },
            {
                test: /.(woff|woff2|eot|ttf|otf)/,
                use: [
                    'file-loader'
                ]
            }
        ]
    },
    plugins: [
        new webpack.HotModuleReplacementPlugin()
    ],
    devServer: {
        contentBase: './dist'
    }

}