import Handlebars from 'handlebars/runtime.js';
import Carousel from '../components/carousel/hbs/carousel.hbs';
import HeroBanner from '../components/carousel/hbs/heroBanner.hbs';
import Vimeo from '../components/carousel/hbs/vimeo.hbs';

Handlebars.registerPartial('heroBanner', HeroBanner);
Handlebars.registerPartial('vimeo', Vimeo);

export default {
  title: 'Components/Carousel',
};

const CarouselVariant = ({ label, ...args }) => Carousel({ ...args });
export const CarouselHeroBannerVariant = CarouselVariant.bind();
CarouselHeroBannerVariant.args = {
  brand: 'hotel',
  showDots: true,
  isHeroBanner: true,
  autoPlay: true,
  delay: 3000,
  startIndex: 1,
  stopOnHover: true,
};

export const CarouselVimeoVariant = CarouselVariant.bind();
CarouselVimeoVariant.args = {
  brand: 'hotel',
  showDots: true,
  isHeroBanner: false,
  autoPlay: false,
  delay: 3000,
  startIndex: 1,
  stopOnHover: true,
};

export const CarouselAutoPlayVariant = CarouselVariant.bind();
CarouselAutoPlayVariant.args = {
  brand: 'hotel',
  showDots: true,
  isHeroBanner: false,
  autoPlay: true,
  delay: 3000,
  startIndex: 0,
  stopOnHover: true,
};
