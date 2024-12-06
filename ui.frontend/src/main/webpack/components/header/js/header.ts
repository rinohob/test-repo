import { isEditWcmMode } from '../../../utils/common';

class Header {
  header: HTMLElement;
  menuIcon: HTMLElement;
  menuIconReverb: HTMLElement;
  menu: HTMLElement;
  bodyTag: HTMLElement;
  hamburgerBtnEl: HTMLElement;
  dropDownButtons: NodeListOf<HTMLElement>;
  childMenuEl: HTMLElement;
  menuItems: NodeListOf<HTMLElement>;
  menuIconRightEl: NodeListOf<HTMLElement>;
  menuIconLeftEl: HTMLElement;
  isReverbVariant: boolean;

  constructor(element) {
    this.header = element;
    this.bodyTag = document.body;
    this.isReverbVariant = element.closest('.header')?.classList.contains('hrc-header--reverb');
    
    this.dropDownButtons = document.querySelectorAll(
      '.hrc-header__main .hrc-header__dropdown',
    );
    this.menu = this.header.querySelector('#menu');
    this.hamburgerBtnEl = this.header.querySelector('.hrc-header__ham-icon');
    this.menuItems = this.header.querySelectorAll('.hrc-header__main__menu-link');
    this.setBodyMarginTop();
    this.init();
  }

  setBodyMarginTop() {
    if (!document.querySelector('.hrc-header--transparent') && !isEditWcmMode()) {
      this.bodyTag.classList.add('body-overlap');
    }
  }

  toggleMenu(menuIcon: HTMLElement, menu: HTMLElement) {
    menuIcon.addEventListener('click', () => {
      menuIcon.classList.toggle('change');
      menu.classList.toggle('show');
      this.bodyTag.classList.toggle('active-overlay');
    });
  }

  static toggleDropdownContent(e) {
    const element = e.currentTarget;
    if (element) {
      const btn = element.querySelector('.hrc-header__button');
      const targetElement = element.querySelector(
        '.hrc-header__dropdown__content',
      );
      const chevEle = element.querySelector('.hrc-header__button__chev');
      targetElement?.classList.toggle('hrc-header__dropdown__content--show');
      btn?.setAttribute('aria-expanded', btn.getAttribute('aria-expanded') === 'true' ? 'false' : 'true'); 
      element.classList.toggle('active');
      chevEle?.classList.toggle('fa-chevron-down');
      chevEle?.classList.toggle('fa-chevron-up');
    }
  }

  handleMenuClick(e: Event) {
    const element = e.currentTarget as HTMLElement;
    if (element) {
      const hasChild = element.classList.contains('has-child');
      const hasSubChild = element.classList.contains('has-subchild');
      const isbackMenuEl = element.classList.contains('hrc-header__main__menu-link--back');
      if (isbackMenuEl) {
        e.preventDefault();
        const closestEl = element.closest('.hrc-header__child__wrapper');
        closestEl.classList.remove('is-open');
      } else if (this.isReverbVariant || Header.isMobileOrTablet()) {
        if (hasChild) {
          e.preventDefault();
          const childWrapEl = element.parentElement.querySelector('.hrc-header__child__wrapper');
          childWrapEl.classList.toggle('is-open');
        } else if (hasSubChild) {
          e.preventDefault();
          const childWrapEl = element.parentElement.querySelector('.hrc-header__subchild-menu');
          const icon = element.querySelector('.hrc-header__button__chev');
          childWrapEl.classList.toggle('is-open');
          icon.classList.toggle('fa-chevron-down');
          icon.classList.toggle('fa-chevron-up');
        }
      }
    }
  }

  static isMobileOrTablet() {
    const width = window.innerWidth;
    return width <= 1200;
  }

  static preventDefaultBehavior(elements) {
    if (elements.length) {
      const headerEl = document.querySelector('.hrc-header--reverb');
      if (this.isMobileOrTablet() || headerEl) {
        elements.forEach((el) => {
          el.addEventListener('click', (e) => {
            e.preventDefault();
          });
        });
      }
    }
  }

  init() {
    if (this.hamburgerBtnEl) {
      this.hamburgerBtnEl.addEventListener('click', () => {
        this.hamburgerBtnEl.classList.toggle('hrc-header__ham-icon--open');
        this.menu.classList.toggle('show');
        this.bodyTag.classList.toggle('active-overlay');
      });
    }

    if (this.bodyTag) {
      this.bodyTag.addEventListener('click', (event: PointerEvent) => {
        if ((event.target as HTMLElement).classList.contains('active-overlay')) {
          this.hamburgerBtnEl.classList.remove('hrc-header__ham-icon--open');
          this.menu.classList.remove('show');
          this.bodyTag.classList.remove('active-overlay');
        }
      });
    }

    if (this.dropDownButtons) {
      this.dropDownButtons.forEach((btn) => {
        btn.addEventListener('click', Header.toggleDropdownContent.bind(this));
      });
    }

    if (this.menuItems) {
      this.menuItems.forEach((item) => {
        item.addEventListener('click', this.handleMenuClick.bind(this));
      });
    }
  }
}

export default Header;
