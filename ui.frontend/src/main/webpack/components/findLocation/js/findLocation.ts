class FindLocation {
  private element: HTMLElement;
  private searchInput: HTMLInputElement | null;
  private searchBtn: HTMLElement | null;
  private searchUrl: string | undefined;
  private alertMessage: string | undefined;

  constructor(element: HTMLElement) {
    this.element = element;
    this.searchInput = element.querySelector('.hrc-location__input__type') as HTMLInputElement;
    this.searchBtn = element.querySelector('.cmp-button') as HTMLElement;
    this.searchUrl = element.dataset.searchUrl;
    this.alertMessage = element.dataset.message;
    this.initEventListeners();
  }
  private initEventListeners() {
    if (this.searchBtn) {
      this.searchBtn.addEventListener('click', this.handleSearch.bind(this));
    }
  }
  private handleSearch(e: Event) {
    e.preventDefault();
    if (this.searchInput && this.searchInput.value === '') {
      // eslint-disable-next-line no-alert
      alert(this.alertMessage);
    } else {
      window.location.href = `${this.searchUrl}?search=${this.searchInput.value}`;
      // eslint-disable-next-line no-alert
    }
  }
}

export default FindLocation;
