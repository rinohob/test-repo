import Handlebars from 'handlebars/runtime.js';
import HeroBanner from '../components/heroBanner/hbs/heroBanner.hbs';

Handlebars.registerPartial('heroBanner', HeroBanner);

export default {
  title: 'Components/HeroBanner',
  argTypes: {
    align: {
      control: 'select',
      options: ['center', 'left', 'right'],
    },
    height: {
      control: 'select',
      options: ['lg', 'md', 'sm'],
    },
    hideMobileDesc: {
      control: 'boolean',
    },
    gradientOff: {
      control: 'boolean',
    },
    color: {
      control: 'select',
      options: ['light', 'dark'],
    },
    videoBanner: {
      control: 'boolean',
    },
    videoType: {
      control: 'select',
      options: ['youtube', 'vimeo', 'aemcore'],
    },
    videoId: {
      control: 'text',
    },
    videoUrl: {
      control: 'text',
    },
    hideVideoContent: {
      control: 'boolean',
    },
    headerHeight: {
      control: 'boolean',
    },
  },
};

const HeroBannerVariant = ({ label, ...args }) => HeroBanner({ ...args });
export const HeroBannerDefaultVariant = HeroBannerVariant.bind();
HeroBannerDefaultVariant.args = {
  brand: 'hotel',
  align: 'left',
  moreCta: false,
  gradientOff: true,
  color: 'light',
  videoBanner: false,
  height: 'lg',
  hideMobileDesc: false,
  headerHeight: '',
};
export const TextCenterVariant = HeroBannerVariant.bind();
TextCenterVariant.args = {
  brand: 'unity',
  align: 'center',
  moreCta: true,
  gradientOff: true,
  color: 'light',
  videoBanner: false,
  hideMobileDesc: false,
};
export const TextRightVariant = HeroBannerVariant.bind();
TextRightVariant.args = {
  brand: 'hotel',
  align: 'right',
  moreCta: true,
  gradientOff: true,
  color: 'light',
  videoBanner: false,
  height: 'lg',
  hideMobileDesc: false,
  headerHeight: '',
};
export const TextLeftVariant = HeroBannerVariant.bind();
TextLeftVariant.args = {
  brand: "entertainment",
  align: "left",
  moreCta: true,
  gradientOff: true,
  color: "light",
  videoBanner: true,
  videoType: "vimeo",
  videoId: "347119375",
  hideVideoContent: true,
  headerHeight: "",
  hideMobileDesc: "",
};
export const SizeLgVariant = HeroBannerVariant.bind();
SizeLgVariant.args = {
  brand: "",
  height: "lg",
  moreCta: true,
  gradientOff: "",
  color: "",
  videoBanner: false,
  videoType: "",
  videoId: "",
  videoUrl: "",
  headerHeight: "",
  hideMobileDesc: "",
};
export const SizeMdVariant = HeroBannerVariant.bind();
SizeMdVariant.args = {
  brand: '',
  height: 'md',
  moreCta: true,
  gradientOff: '',
  color: '',
  videoBanner: false,
  videoType: '',
  videoId: '',
  videoUrl: '',
  headerHeight: '',
};
export const SizeSmVariant = HeroBannerVariant.bind();
SizeSmVariant.args = {
  brand: '',
  height: 'sm',
  moreCta: true,
  gradientOff: '',
  color: '',
  videoBanner: false,
  videoType: '',
  videoId: '',
  videoUrl: '',
  headerHeight: '',
};
export const GradientVariant = HeroBannerVariant.bind();
GradientVariant.args = {
  brand: '',
  height: 'sm',
  moreCta: true,
  gradientOff: false,
  color: '',
  videoBanner: false,
  videoType: '',
  videoId: '',
  videoUrl: '',
  headerHeight: '',
};
export const TextDarkVariant = HeroBannerVariant.bind();
TextDarkVariant.args = {
  brand: '',
  height: '',
  moreCta: true,
  gradientOff: '',
  color: 'dark',
  videoBanner: false,
  videoType: '',
  videoId: '',
  videoUrl: '',
  headerHeight: '',
};
export const hideMobileDescVariant = HeroBannerVariant.bind();
hideMobileDescVariant.args = {
  brand: '',
  height: '',
  moreCta: true,
  gradientOff: '',
  color: 'dark',
  videoBanner: false,
  videoType: '',
  videoId: '',
  videoUrl: '',
  hideMobileDesc: true,
  headerHeight: '',
};
export const VideoTeaserVariant = HeroBannerVariant.bind();
VideoTeaserVariant.args = {
  brand: 'cafe',
  height: 'sm',
  moreCta: false,
  gradientOff: true,
  color: 'light',
  videoBanner: true,
  videoType: 'youtube',
  videoId: 'https://www.youtube.com/embed/yPcRn-YLYt4',
  hideVideoContent: true,
  headerHeight: '',
};
export const hideVideoContentVariant = HeroBannerVariant.bind();
hideVideoContentVariant.args = {
  brand: '',
  height: '',
  moreCta: true,
  gradientOff: '',
  color: '',
  videoBanner: true,
  videoType: '',
  videoId: '',
  videoUrl: '',
  hideVideoContent: true,
  headerHeight: '',
};
export const addHeaderHeightVariant = HeroBannerVariant.bind();
addHeaderHeightVariant.args = {
  brand: 'hotel',
  height: 'md',
  moreCta: true,
  gradientOff: true,
  align: 'right',
  color: 'light',
  videoBanner: false,
  hideVideoContent: true,
  headerHeight: true,
};
