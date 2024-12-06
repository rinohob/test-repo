class Subnav {
  private header: HTMLElement | null;
  private toggleDropdown: HTMLElement | null;
  private dropdownWrapper: HTMLElement | null;
  private element: HTMLElement;
  private dropdownSelected: HTMLElement;
  private scrollY: number;

  constructor(element) {
    this.element = element;
    this.header = document.querySelector('.hrc-header');
    this.dropdownWrapper = element.querySelector('.cmp-list');
    this.dropdownSelected = element.querySelector('.cmp-list__value');
    this.createDropdownView();
    this.addEventListeners();
  }

  private createDropdownView(): void {
    const menu = this.dropdownWrapper.querySelectorAll('.cmp-list__item-link');
    const elements = [];
    menu.forEach((item, index) => {
      if (index === 0 || item.classList.contains('active')) {
        const temp = item.cloneNode(true);
        elements.push(temp);
      }
    });
    if (elements.length) {
      this.dropdownSelected.append(...elements);
      this.toggleDropdown = elements.find((element) => element.classList.contains('active'));
      this.toggleDropdown.setAttribute('href', '#');
      this.toggleDropdown.setAttribute('aria-label', 'Sub navigation dropdown');
      this.toggleDropdown.setAttribute('aria-haspopup', 'true');
      this.toggleDropdown.setAttribute('aria-expanded', 'false');
    }
  }
  private addEventListeners(): void {
    if (this.toggleDropdown) {
      this.toggleDropdown.addEventListener('click', (e: Event) => {
        e.preventDefault();
        this.dropdownWrapper?.classList.toggle('cmp-list--show');
        this.toggleDropdown.setAttribute(
          'aria-expanded',
          this.dropdownWrapper?.classList.contains('cmp-list--show')
            ? 'true'
            : 'false',
        );
      });
    }

    window.addEventListener('scroll', () => {
      this.handleScroll();
    });
  }
  private handleScroll(): void {
    const mainNav: HTMLElement | null = document.querySelector('.hrc-header__main');
    const mainNavBottom = mainNav?.getBoundingClientRect() || {
      bottom: undefined,
    };
    const subnavTop = this.element?.getBoundingClientRect() || {
      top: undefined,
    };
    const windowWidth: number = window.innerWidth;

    if (windowWidth >= 1200 && this.header) {
      if (mainNavBottom.bottom > subnavTop.top) {
        this.scrollY = window.scrollY;
        document.body.classList.add('list--sticky-subnav');
      } else if (window.scrollY < this.scrollY) {
        document.body.classList.remove('list--sticky-subnav');
      }
    }
  }
}

export default Subnav;
