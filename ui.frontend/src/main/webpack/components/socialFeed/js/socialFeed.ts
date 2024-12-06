import DOMPurify from 'dompurify';
import services from '../../../framework/services';

class SocialFeed {
  socialFeed: HTMLElement;
  socialFeedGridContainer: HTMLElement;
  loadingImage: HTMLElement;
  loadMoreBtn: HTMLElement;
  url: string;

  constructor(element: HTMLElement) {
    this.socialFeed = element;
    this.socialFeedGridContainer = this.socialFeed.querySelector(
      '.hrc-socialfeed__grid',
    );
    this.loadingImage = this.socialFeed.querySelector(
      '.hrc-socialfeed__loading',
    );
    this.loadMoreBtn = this.socialFeed.querySelector(
      '.hrc-socialfeed__load-more',
    );
    this.url = this.socialFeed.dataset.url;

    this.init();
  }

  static splitData(
    apiData: any[],
  ): { firstFour: any[]; fifthItem: any[] | null }[] {
    const chunks = [];
    for (let i = 0; i < apiData.length; i += 5) {
      const firstFour = apiData.slice(i, i + 4);
      const fifthItem = i + 4 < apiData.length ? [apiData[i + 4]] : null;
      chunks.push({ firstFour, fifthItem });
    }
    return chunks;
  }

  private static getColumnClassNames(
    baseClass: string,
    idx: number,
    hideIndex: number,
  ): string {
    return idx >= hideIndex ? `${baseClass} ${baseClass}--hide` : baseClass;
  }

  static socialFeedItem({ data, className }) {
    return `<div class="${className}"> 
    ${data
    .map(
      (item) => `
      <a
        href="${item.PostURL}"
        target="_blank"
        class="hrc-socialfeed__item"
      >
        <div class="hrc-socialfeed__content" style="background-image: url(${item.PostImageURL});">
          <div class="hrc-socialfeed__text">
            ${item.PostText}
          </div>
        </div>
      </a>
      `,
    )
    .join('')}
      </div>`;
  }

  static renderTemplates(dataChunks) {
    const renderedHTML = dataChunks
      .map((chunk, idx) => {
        const firstFourHtml = SocialFeed.socialFeedItem({
          data: chunk.firstFour,
          className: SocialFeed.getColumnClassNames(
            'hrc-socialfeed__column-first',
            idx,
            2,
          ),
        });
        const fifthItemHtml = chunk.fifthItem
          ? SocialFeed.socialFeedItem({
            data: chunk.fifthItem,
            className: SocialFeed.getColumnClassNames(
              'hrc-socialfeed__column-second',
              idx,
              1,
            ),
          })
          : '';

        return firstFourHtml + fifthItemHtml;
      })
      .join('');

    console.log(DOMPurify.sanitize(renderedHTML));

    return renderedHTML;
  }

  async fetchSocialFeedData(): Promise<void> {
    if (!this.url) return;

    try {
      const socialFeedData = await services.get(this.url);

      if (!socialFeedData.length) return;

      if (socialFeedData.length > 9) {
        this.loadMoreBtn.classList.add('hrc-socialfeed__load-more--show');
        this.bindLoadMoreEvent();
      }

      const dataChunks = SocialFeed.splitData(socialFeedData);

      if (!dataChunks.length) return;

      this.loadingImage.classList.add('hrc-socialfeed__loading--hide');
      this.socialFeedGridContainer.classList.add('hrc-socialfeed__grid--show');

      this.socialFeedGridContainer.innerHTML = DOMPurify.sanitize(
        SocialFeed.renderTemplates(dataChunks),
      );
    } catch (err) {
      console.error(`Failed to fetch data: ${(err as Error).message}`);
    }
  }

  private bindLoadMoreEvent(): void {
    this.loadMoreBtn.addEventListener('click', () => {
      const hiddenItems = this.socialFeedGridContainer.querySelectorAll(
        '.hrc-socialfeed__column-first--hide, .hrc-socialfeed__column-second--hide',
      );
      hiddenItems.forEach((item) => {
        item.classList.remove(
          'hrc-socialfeed__column-first--hide',
          'hrc-socialfeed__column-second--hide',
        );
      });
      this.loadMoreBtn.classList.remove('hrc-socialfeed__load-more--show');
    });
  }

  private observeSocialFeed(): void {
    const observer = new IntersectionObserver((entries, observe) => {
      entries.forEach((entry) => {
        if (entry.isIntersecting) {
          this.fetchSocialFeedData();
          observe.unobserve(entry.target); // Stop observing after the first fetch
        }
      });
    });
    observer.observe(this.socialFeed);
  }

  private init(): void {
    if (this.socialFeed) {
      this.observeSocialFeed();
    }
  }
}

export default SocialFeed;
