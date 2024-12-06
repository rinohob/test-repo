interface Initializable {
    init(): void;
}

function Init<T extends Initializable>(init: () => void) {
  if (window.STORYBOOK__MODE || (!window.STORYBOOK__MODE && document.readyState === 'loading')) {
    document.addEventListener('DOMContentLoaded', () => {
      init();
    });
  } else {
    init();
  }
}
export default Init;
