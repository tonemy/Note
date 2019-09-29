const path = require('path')

module.exports = {
    entry: {
        index: path.join(__dirname, 'src/index.js'),
        search: './src/print.js'
    },
    output: {
        path: path.join(__dirname, 'dist'),
        filename: '[name]_[chunkhash:8].[js]'
    },
    mode: 'production',
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
                    {
                        loader: 'file-loader',
                        filename: '[name][hash:8].[ext]'
                    }
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


}