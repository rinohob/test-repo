import type { StorybookConfig } from '@storybook/html-webpack5';
import { plugins } from '../webpack.common';
const path = require('path');
const TSConfigPathsPlugin = require('tsconfig-paths-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');

const resolve = {
  extensions: ['.js', '.ts'],
};
// const SOURCE_ROOT = '/src/main/webpack';

const config: StorybookConfig = {
  stories: ['../src/**/*.mdx', '../src/**/*.stories.@(js|jsx|mjs|ts|tsx)'],
  addons: [
    '@storybook/addon-webpack5-compiler-swc',
    '@storybook/addon-links',
    '@storybook/addon-essentials',
    {
      name: '@storybook/addon-styling-webpack',
      options: {
        rules: [
          // Replaces any existing Sass rules with given rules
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
                    return [require('autoprefixer')];
                  },
                },
              },
              {
                loader: 'sass-loader',
              },
              {
                loader: 'glob-import-loader',
                options: {
                  resolve: resolve,
                },
              },
            ],
          },
        ],
        plugins: [
          new MiniCssExtractPlugin({
            filename: 'clientlib-[name]/[name].css',
          }),
        ],
      },
    },
    '@chromatic-com/storybook',
    '@storybook/addon-interactions',
  ],
  webpackFinal: async (config) => {
    config.module?.rules?.push({
      test: /\.hbs$/,
      loader: 'handlebars-loader',
    });
    return config;
  },
  framework: {
    name: '@storybook/html-webpack5',
    options: {},
  },
  staticDirs: [
    { from: '../src/main/webpack/site/hardrock/resources', to: '/resources' },
    { from: '../src/main/webpack/site/unity/resources', to: '/resources' },
    { from: '../src/main/webpack/site/master/resources', to: '/resources' },
  ],
};
export default config;
