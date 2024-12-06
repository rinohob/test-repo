/* eslint-disable */
const { exec } = require('child_process');
const { BRAND_NAMES } = require('./common.config');

let str = '';

BRAND_NAMES.forEach((brandName) => {
  str += `npm run build --brand_name=${brandName} && `;
});
console.log(str.slice(0, -3));

exec(str.slice(0, -3), (error, stdout, stderr) => {
  console.log(`stdout: ${stdout}`);
  console.log(`stderr: ${stderr}`);
  if (error !== null) {
    console.log(`exec error: ${error}`);
  }
});
