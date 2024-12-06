module.exports = {
  parser: '@typescript-eslint/parser', // Specifies the ESLint parser
  extends: ['airbnb', 'plugin:@typescript-eslint/recommended', 'plugin:storybook/recommended',
    'plugin:import/typescript'],
  parserOptions: {
    ecmaVersion: 2018, // Allows for the parsing of modern ECMAScript features
    sourceType: 'module', // Allows for the use of imports
  },
  settings: {
    'import/resolver': {
      typescript: true,
      node: true,
    },
  },
  rules: {
    curly: 1,
    '@typescript-eslint/explicit-function-return-type': [0],
    '@typescript-eslint/no-explicit-any': [0],
    'ordered-imports': [0],
    'object-literal-sort-keys': [0],
    'max-len': [1, 120],
    'new-parens': 1,
    'no-bitwise': 1,
    'no-cond-assign': 1,
    'lines-between-class-members': 'off',
    'no-trailing-spaces': 0,
    'eol-last': 1,
    'func-style': ['error', 'declaration', { allowArrowFunctions: true }],
    'import/extensions': ['warn', 'always', { js: 'never', ts: 'never' }],
    semi: 1,
    'no-var': 0,
    'linebreak-style': 0,
  },
  env: {
    browser: true,
  },
};
