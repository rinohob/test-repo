import EmblaCarousel, {
  EmblaOptionsType,
  EmblaCarouselType,
} from 'embla-carousel';
import Autoplay from 'embla-carousel-autoplay';

class Carousel {
  carousel: HTMLElement;
  prevBtnNode: HTMLElement | null;
  nextBtnNode: HTMLElement | null;
  dotsNode: HTMLElement | null;
  autoPlay: boolean;
  delay: number;
  startIndex: number;
  stopOnHover: boolean;

  constructor(element: HTMLElement) {
    this.carousel = element;
    this.prevBtnNode = this.carousel.querySelector(
      '.hrc-carousel__button--prev',
    );
    this.nextBtnNode = this.carousel.querySelector(
      '.hrc-carousel__button--next',
    );
    this.dotsNode = this.carousel.querySelector('.hrc-carousel__dots');

    this.autoPlay = this.carousel.dataset.autoPlay === 'true';
    this.delay = +this.carousel.dataset.delay || 3000;
    this.startIndex = +this.carousel.dataset.startIndex || 0;
    this.stopOnHover = this.carousel.dataset.stopOnHover === 'true';

    this.init();
  }

  private initiateEmblaCarousel(): void {
    const viewportNode = this.carousel.querySelector(
      '.hrc-carousel__viewport',
    ) as HTMLElement;

    const options: EmblaOptionsType = {
      loop: true,
      dragFree: false,
      skipSnaps: false,
      startIndex: this.startIndex,
      slidesToScroll: 'auto',
      align: 'start',
    };

    let autoPlayOptions = [];

    if (this.autoPlay) {
      autoPlayOptions = [
        Autoplay({
          delay: this.delay,
          stopOnMouseEnter: this.stopOnHover,
          stopOnInteraction: !this.stopOnHover,
        }),
      ];
    }

    const emblaApi: EmblaCarouselType = EmblaCarousel(
      viewportNode,
      options,
      autoPlayOptions,
    );

    if (this.prevBtnNode && this.nextBtnNode) {
      const removePrevNextBtnsClickHandlers = Carousel.addPrevNextBtnsClickHandlers(
        emblaApi,
        this.prevBtnNode,
        this.nextBtnNode,
      );
      emblaApi.on('destroy', removePrevNextBtnsClickHandlers);
    }

    if (this.dotsNode) {
      const removeDotBtnsAndClickHandlers = Carousel.addDotBtnsAndClickHandlers(
        emblaApi,
        this.dotsNode,
      );
      emblaApi.on('destroy', removeDotBtnsAndClickHandlers);
    }
  }

  private static addPrevNextBtnsClickHandlers(
    emblaApi: EmblaCarouselType,
    prevBtn: HTMLElement,
    nextBtn: HTMLElement,
  ): () => void {
    const scrollPrev = () => emblaApi.scrollPrev();
    const scrollNext = () => emblaApi.scrollNext();

    prevBtn.addEventListener('click', scrollPrev);
    nextBtn.addEventListener('click', scrollNext);

    return () => {
      prevBtn.removeEventListener('click', scrollPrev);
      nextBtn.removeEventListener('click', scrollNext);
    };
  }

  private static addDotBtnsAndClickHandlers(
    emblaApi: EmblaCarouselType,
    dotsNode: HTMLElement,
  ): () => void {
    let dotNodes: HTMLElement[] = [];
    const copyOfDotsNode: HTMLElement = dotsNode;

    const addDotBtnsWithClickHandlers = () => {
      dotNodes = Array.from(
        copyOfDotsNode.querySelectorAll('.hrc-carousel__dot'),
      ) as HTMLElement[];

      dotNodes.forEach((dotNode, index) => {
        dotNode.addEventListener('click', () => emblaApi.scrollTo(index));
      });
    };

    const toggleDotBtnsActive = () => {
      const previous = emblaApi.previousScrollSnap();
      const selected = emblaApi.selectedScrollSnap();
      if (dotNodes[previous]) {
        dotNodes[previous].classList.remove('hrc-carousel__dot--selected');
      }
      if (dotNodes[selected]) {
        dotNodes[selected].classList.add('hrc-carousel__dot--selected');
      }
    };

    emblaApi
      .on('init', addDotBtnsWithClickHandlers)
      .on('reInit', addDotBtnsWithClickHandlers)
      .on('init', toggleDotBtnsActive)
      .on('reInit', toggleDotBtnsActive)
      .on('select', toggleDotBtnsActive);

    return () => {
      copyOfDotsNode.innerHTML = '';
    };
  }

  private init(): void {
    if (this.carousel) {
      this.initiateEmblaCarousel();
    }
  }
}

export default Carousel;
