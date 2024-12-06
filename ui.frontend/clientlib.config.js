/* eslint-disable */
/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ Copyright 2020 Adobe Systems Incorporated
 ~
 ~ Licensed under the Apache License, Version 2.0 (the "License");
 ~ you may not use this file except in compliance with the License.
 ~ You may obtain a copy of the License at
 ~
 ~     http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ Unless required by applicable law or agreed to in writing, software
 ~ distributed under the License is distributed on an "AS IS" BASIS,
 ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ~ See the License for the specific language governing permissions and
 ~ limitations under the License.
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

const path = require("path");
const BRAND_NAMES = ['hardrock', 'unity'];
const BUILD_DIR = path.join(__dirname, "dist");
const CLIENTLIB_DIR = path.join(
  __dirname,
  '..',
  'ui.apps',
  'src',
  'main',
  'content',
  'jcr_root',
  'apps',
  'shrss',
  'clientlibs'
);
const libsBaseConfig = {
  allowProxy: true,
  serializationFormat: "xml",
  cssProcessor: ["default:none", "min:none"],
  jsProcessor: ["default:none", "min:none"],
};

const libs = [];

//  let brandNameList = process?.env?.npm_config_brand_name ? [process.env.npm_config_brand_name.trim().toLowerCase()] : null;
//  if (!brandNameList) {
brandNameList = BRAND_NAMES;
//  }

brandNameList.forEach((brandName) => {
  const dependencyObj = {
    ...libsBaseConfig,
    name: "clientlib-dependencies-" + brandName,
    categories: [brandName + ".dependencies"],
    assets: {
      // Copy entrypoint scripts and stylesheets into the respective ClientLib
      // directories
      js: {
        cwd: "clientlib-dependencies-" + brandName,
        files: ["**/*.js"],
        flatten: false,
      },
      css: {
        cwd: "clientlib-dependencies-" + brandName,
        files: ["**/*.css"],
        flatten: false,
      },
    },
  };
  const siteObj = {
    ...libsBaseConfig,
    name: "clientlib-" + brandName,
    categories: [brandName + ".site"],
    dependencies: [brandName + ".dependencies"],
    assets: {
      // Copy entrypoint scripts and stylesheets into the respective ClientLib
      // directories
      js: {
        cwd: "clientlib-" + brandName,
        files: ["**/*.js", "../resources/clientlib-dynamic-modules/vendors-*.js"],
        flatten: false,
      },
      css: {
        cwd: "clientlib-" + brandName,
        files: ["**/*.css"],
        flatten: false,
      },

      resources: {
        files: [
          {
            src: "resources/clientlib-dynamic-modules/**.js",
            dest: "clientlib-dynamic-modules",
          },
          { src: `clientlib-${brandName}/fonts/*`, dest: "fonts" },
          { src: `clientlib-${brandName}/webfonts/*`, dest: "webfonts" },
          { src: `clientlib-${brandName}/icon-font/*`, dest: "icon-font" },
          { src: `clientlib-${brandName}/icon-font/fonts/*`, dest: "icon-font/fonts" },
          { src: `clientlib-${brandName}/images/*`, dest: "images" },
          { src: `clientlib-${brandName}/mock-alert-data.json`, dest: "mock-alert-data.json" },
        ],
        // files: ["**/*.*"]
      },
    },
  };
  libs.push(dependencyObj);
  libs.push(siteObj);
});

const dynamicImport = {
  ...libsBaseConfig,
  name: 'clientlib-dynamic-modules',
  categories: ['shrss.dynamic-modules'],
  dependencies: [],
  assets: {
    resources: {
      files: ["clientlib-dynamic-modules/resources/*"]
    }
  },
}

// Config for `aem-clientlib-generator`
module.exports = {
  context: BUILD_DIR,
  clientLibRoot: CLIENTLIB_DIR,
  libs: [...libs, dynamicImport],
};
