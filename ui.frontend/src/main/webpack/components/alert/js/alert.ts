import EmblaCarousel, { EmblaCarouselType } from 'embla-carousel';
import {
  getCookieValue,
  isEditWcmMode,
  setCookie,
  setRootStylePropertyValue,
} from '../../../utils/common';
import services from '../../../framework/services';

// Define types for the alert data
interface AlertData {
  id: string;
  alertContent: string;
  startDate: string;
  endDate: string;
  showIcon: string;
  alertMobileContent: string;
  dismissAlert: string;
  className: string;
  ariaLabel: string;
}

class Alert {
  alert: HTMLElement;
  url: string;
  prevIcon: HTMLElement | null;
  nextIcon: HTMLElement | null;
  header: HTMLElement | null;
  mainMenu: HTMLElement | null;
  body: HTMLElement;
  slide: HTMLElement | null;
  slidesCount: number;

  constructor(element: HTMLElement) {
    this.alert = element;
    this.url = this.alert.dataset.url;
    this.prevIcon = this.alert.querySelector('.hrc-alert__prev');
    this.nextIcon = this.alert.querySelector('.hrc-alert__next');
    this.slide = this.alert.querySelector('.hrc-alert__slides');
    this.slidesCount = 0;
    this.header = document.querySelector('.hrc-header');
    this.mainMenu = this.header.querySelector('.hrc-header__main__menu');
    this.body = document.body;
    this.init();
  }

  getHeaderElementHeight(): number {
    return this.header ? this.header.offsetHeight : 0;
  }

  getAlertElementHeight(): number {
    return this.alert ? this.alert.offsetHeight : 0;
  }

  applySingleSlideClass(): void {
    this.alert.classList.add('hrc-alert--only-slide');
  }

  hideAlert(): void {
    this.alert.classList.remove('hrc-alert--show');
  }

  removeSingleSlideClass(): void {
    this.alert.classList.remove('hrc-alert--only-slide');
  }

  adjustLayout(): void {
    if (this.alert) {
      const alertElHeight = this.getAlertElementHeight();
      // const headerElHeight = this.getHeaderElementHeight();
      if (this.header && !isEditWcmMode()) {
        setRootStylePropertyValue('--top-header', `${alertElHeight}px`);
      }
      // const totalHeight = alertElHeight + headerElHeight;
      // this.body.style.marginTop = `${totalHeight}px`;
    }
  }

  handleChevronsDisplay(): void {
    if (this.slide) {
      this.slidesCount = this.slide.querySelectorAll('.hrc-alert__slide').length;
      if (this.slidesCount === 1) {
        this.applySingleSlideClass();
      } else if (this.slidesCount === 0) {
        this.hideAlert();
      } else {
        this.removeSingleSlideClass();
      }
    }
  }

  removeAlertItem(el: MouseEvent): void {
    const target = el.target as HTMLElement;
    if (target && target.classList.contains('hrc-alert__close')) {
      const listItem = target.closest('.hrc-alert__slide') as HTMLElement;
      if (listItem) {
        listItem.remove();
        Alert.storeAlertIdInCookies(listItem.id);
      }
    }
    this.handleChevronsDisplay();
    this.adjustLayout();
  }

  static storeAlertIdInCookies(id: string) {
    let expiredAlerts: string[] = [];

    const cookieValue = getCookieValue('expiredAlerts');

    if (cookieValue) {
      expiredAlerts = cookieValue.split(',');
    }

    expiredAlerts.push(id);
    setCookie('expiredAlerts', expiredAlerts.join(','), 365);
  }

  static checkIfAlertIdExistsInCookies(id: string) {
    const cookieValue = getCookieValue('expiredAlerts');

    if (cookieValue) {
      return cookieValue.split(',').includes(id);
    }

    return false;
  }

  static createLiElement(alert: AlertData): HTMLElement {
    const li = document.createElement('li');
    li.id = alert.id;
    li.classList.add('hrc-alert__slide');
    if (alert.className.trim()) {
      li.classList.add(alert.className);
    }

    li.setAttribute('role', 'group');
    li.setAttribute('aria-label', alert.ariaLabel);
    li.setAttribute('aria-roledescription', 'slide');

    const spanIcon = document.createElement('span');
    spanIcon.classList.add('fas', 'fa-exclamation-triangle');
    if (alert.showIcon === 'false') {
      spanIcon.classList.add('hrc-alert--icon-hide');
    }

    const pContentTab = document.createElement('p');
    pContentTab.classList.add('hrc-alert__slide--tab');
    pContentTab.innerHTML = alert.alertContent;

    const pContentMobile = document.createElement('p');
    pContentMobile.classList.add('hrc-alert__slide--mobile');
    pContentMobile.innerHTML = alert.alertMobileContent;

    const btnElement = document.createElement('button');
    btnElement.classList.add(
      'hrc-alert__close',
      'fas',
      'fa-times-circle',
      'hrc-alert--icon-hide',
    );
    if (alert.dismissAlert === 'true') {
      btnElement.classList.remove('hrc-alert--icon-hide');
    }

    li.appendChild(spanIcon);
    li.appendChild(pContentTab);
    li.appendChild(pContentMobile);
    li.appendChild(btnElement);

    return li;
  }

  initiateEmblaCarousel(): void {
    const viewportNode = this.alert.querySelector(
      '.hrc-alert__viewport',
    ) as HTMLElement;
    const embla: EmblaCarouselType = EmblaCarousel(viewportNode, {
      loop: true,
      dragFree: false,
    });

    this.prevIcon.addEventListener('click', () => embla.scrollPrev(), false);
    this.nextIcon.addEventListener('click', () => embla.scrollNext(), false);
  }

  static getNonExpiredAlerts(data: AlertData[]): AlertData[] {
    return data.filter((val) => !Alert.checkIfAlertIdExistsInCookies(val.id));
  }

  static getActiveSortedAlerts(
    data: AlertData[],
    currentDate: Date,
  ): AlertData[] {
    return data
      .filter(
        (alert) => currentDate >= new Date(alert.startDate)
          && currentDate < new Date(alert.endDate),
      )
      .sort(
        (a, b) => new Date(b.startDate).getTime() - new Date(a.startDate).getTime(),
      );
  }

  renderAlertSlides(data: AlertData[]): void {
    const slideContainerEl = this.alert.querySelector('.hrc-alert__slides');
    if (!slideContainerEl) return;

    slideContainerEl.innerHTML = '';
    data.forEach((alert, idx) => {
      const alertElement = Alert.createLiElement(alert);
      slideContainerEl.appendChild(alertElement);
    });
  }

  async showAlerts(): Promise<void> {
    const currentDate = new Date();

    try {
      if (!this.url) return;

      const alertData: AlertData[] = await services.get(this.url);

      if (!alertData.length) return;

      const nonExpiredAlerts = Alert.getNonExpiredAlerts(alertData);

      if (!nonExpiredAlerts.length) return;

      const activeSortedAlerts = Alert.getActiveSortedAlerts(
        nonExpiredAlerts,
        currentDate,
      );

      if (!activeSortedAlerts.length) return;

      this.renderAlertSlides(activeSortedAlerts);

      this.alert.classList.add('hrc-alert--show');

      if (activeSortedAlerts.length > 1) {
        this.initiateEmblaCarousel();
      } else {
        this.handleChevronsDisplay();
      }
      this.adjustLayout();
    } catch (err) {
      console.error(`Failed to fetch data: ${(err as Error).message}`);
    }
  }

  init(): void {
    if (this.alert) {
      this.showAlerts();
    }

    if (this.alert) {
      window.addEventListener('resize', this.adjustLayout.bind(this));
    }

    if (this.slide) {
      this.slide.addEventListener('click', this.removeAlertItem.bind(this));
    }
  }
}

export default Alert;
