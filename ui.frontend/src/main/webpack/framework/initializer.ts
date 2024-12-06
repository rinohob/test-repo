/* eslint-disable */
/**
 * Initializer class
 *
 * Responsible for instantiating and map JS Class on components if [data-component=`component`] is present
 * Takes care of intantiation on DOM mutation
 */
// import CONSTANTS from "../site/js/const";
const CONSTANTS = {
  COMPONENT_TYPE: 'react',
  REACT_COMPONENTS_PATH: ''
};

class Initializer {
  private selectors = {
    component: '[data-component]',
    body: 'body',
  };

  constructor() {
    // initialize the components on DOM ready
    if (document.readyState !== 'loading') {
      this.onDocumentReady();
    } else {
      document.addEventListener('DOMContentLoaded', this.onDocumentReady.bind(this), { once: true });
    }
  }

  private initMutation(): void {
    /*------------------------------------------------------------------
     * MutationObserver is used to listen for DOM changes
     * DOC: https://developer.mozilla.org/en-US/docs/Web/API/MutationObserver#Instance_methods
     * Performance related article :
     * https://hacks.mozilla.org/2012/05/dom-mutationobserver-reacting-to-dom-changes-without-killing-browser-performance
     *------------------------------------------------------------------
     */
    // observe body element for mutations change
    const targetNode = document.querySelector(this.selectors.body);
    // Options for the observer (which mutations to observe)
    const config = { attributes: false, childList: true, subtree: true };
    if (targetNode) {
      const observer = new MutationObserver(this.handleMutation.bind(this));
      // Start observing on body element for configured mutations
      observer.observe(targetNode, config);
    }
  }

  // Callback function to execute when mutations are observed
  private handleMutation(mutationsList: MutationRecord[]): void {
    mutationsList.forEach((mutation) => {
      if (mutation.type === 'childList') {
        const newNodes = mutation.addedNodes;
        // if new nodes are added to the DOM run through initialize component code
        if (newNodes.length) {
          newNodes.forEach((element: HTMLElement) => {
            if (element instanceof HTMLElement && element.dataset.component && !element.dataset.initialized) {
              this.initComponent(element);
              element.dataset.initialized = 'true';
              console.debug('Initializer: Root component is UI component -> ', element.dataset.component);
            }
            element.querySelectorAll(this.selectors.component).forEach((el) => {
              if (el instanceof HTMLElement && el.dataset.component && !el.dataset.initialized) {
                this.initComponent(el);
                el.dataset.initialized = 'true';
                // console.error("Initializer: Child component is UI component ->", el.dataset.component);
              }
            });
          });
        }
      }
    })
  }

  private initComponent(el: HTMLElement): void {
    const { dataset } = el;
    const componentName = dataset.component;
    const componentType = dataset.cmpType;
    const isReactComponent = CONSTANTS.COMPONENT_TYPE === (componentType && componentType.toLowerCase());
    const pathExtension = isReactComponent ? CONSTANTS.REACT_COMPONENTS_PATH : '';
    import(
      /* webpackExclude: /\.stories[0-9]?\.js$/ */
      `../${pathExtension}components/${componentName}/js/${componentName}.ts`
    )
      .then((component) => {
        if (component?.default) {
          const cmpModule = component.default;
          new cmpModule(el);
        }
        // console.log("com", component);
        // component.default.init(el);
      })
      .catch((error) => {
        console.debug(`../${pathExtension}components/${componentName}/scripts/_${componentName}.js not found`);
      });
  }

  private onDocumentReady(): void {
    const elements = document.querySelectorAll(this.selectors.component);
    for (let i = 0; i < elements.length; i++) {
      const element = elements[i];
      if (element instanceof HTMLElement) {
        this.initComponent(element);
      }
    }
    this.initMutation();
  }
}

export { Initializer };
