import type { Preview } from '@storybook/html';
import './hb-helper.js';
import './base.scss';
import '../src/main/webpack/site/hardrock/main';

/** If you want to test your components for UNITY, please uncomment below line
 *  and comment above and quit your server and run again.
 */
// import '../src/main/webpack/site/unity/main';

window.STORYBOOK__MODE = true;

const preview: Preview = {
  argTypes: {
    brand: {
      control: 'select',
      options: ['hotel', 'cafe', 'entertainment', 'reverb', 'unity'],
    },
  },
  decorators: [
    // 👇 Defining the decorator in the preview file applies it to all stories
    (Story, { args }) => {
      // 👇 Make it configurable by reading from parameters
      const { brand } = args;
      return `<div class="page-${brand}">${Story()}</class>`;
    },
  ],
};

export default preview;
