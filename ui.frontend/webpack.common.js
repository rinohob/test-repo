const path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const TSConfigPathsPlugin = require('tsconfig-paths-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');
const ESLintPlugin = require('eslint-webpack-plugin');

const SOURCE_ROOT = `${__dirname}/src/main/webpack`;


const BRANDS = ['hardrock', 'unity'];

const resolve = {
    extensions: ['.js', '.ts'],
    plugins: [new TSConfigPathsPlugin({
        configFile: './tsconfig.json',
    })],
};
const entry = {};
const patterns = [];

BRANDS.forEach((brandName) => {
    entry[brandName] = SOURCE_ROOT + "/site/" + brandName + "/main.ts";
    patterns.push({
        from: path.resolve(__dirname, SOURCE_ROOT + `/site/master/resources/`),
        to: "./clientlib-" + brandName,
    });
    patterns.push({
        from: path.resolve(__dirname, SOURCE_ROOT + `/site/${brandName}/resources/`),
        to: "./clientlib-" + brandName,
    });
});

module.exports = {
    resolve,
    entry,
    output: {
        filename: (chunkData) => (chunkData.chunk.name === 'dependencies' ? 'clientlib-dependencies/[name].js' : 'clientlib-[name]/[name].js'),
        path: path.resolve(__dirname, 'dist'),
        chunkFilename: "clientlib-dynamic-modules/resources/[name]-[contenthash].js",
        publicPath: "/etc.clientlibs/shrss/clientlibs/",
        clean: true
    },
    module: {
        rules: [
            {
                test: /\.tsx?$/,
                exclude: /node_modules|\.d\.ts$/,
                use: [
                    {
                        loader: 'ts-loader',
                        options: { onlyCompileBundledFiles: true },
                        
                    },
                    {
                        loader: 'glob-import-loader',
                        options: {
                            resolve,
                        },
                    },
                ],
            },
                {
                    test: /\.d\.ts$/,
                    loader: 'ignore-loader'
                },
            {
                test: /\.scss$/,
                use: [
                    MiniCssExtractPlugin.loader,
                    {
                        loader: 'css-loader',
                        options: {
                            url: false,
                        },
                    },
                    {
                        loader: 'postcss-loader',
                        options: {
                            plugins() {
                                return [
                                    require('autoprefixer'),
                                ];
                            },
                        },
                    },
                    {
                        loader: 'sass-loader',
                    },
                    {
                        loader: 'glob-import-loader',
                        options: {
                            resolve,
                        },
                    },
                ],
            },
        ],
    },
    plugins: [
        new CleanWebpackPlugin(),
        new ESLintPlugin({
            extensions: ['js', 'ts', 'tsx'],
        }),
        new MiniCssExtractPlugin({
            filename: 'clientlib-[name]/[name].css',
        }),
        new CopyWebpackPlugin({
            patterns: [...patterns],
          }),
    ],
    stats: {
        assetsSort: 'chunks',
        builtAt: true,
        children: false,
        chunkGroups: true,
        chunkOrigins: true,
        colors: false,
        errors: true,
        errorDetails: true,
        env: true,
        modules: false,
        performance: true,
        providedExports: false,
        source: false,
        warnings: true,
    },
};
