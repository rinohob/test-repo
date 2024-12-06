import Handlebars from 'handlebars/runtime.js';
import Cards from '../components/cards/hbs/cards.hbs';

Handlebars.registerPartial('cards', Cards);

export default {
  title: "Components/Cards",
  argTypes: {
    variant: {
      control: 'select',
      options: ['default', 'overlay-vertical', 'overlay-horizontal', 'fullbleed-right', 'fullbleed-left', 'fullbleedWhite-right', 'fullbleedWhite-left'],
    },
    shadowDisable: {
      control: "boolean",
    },
  },
};

const CardsVariant = ({ label, ...args }) => Cards({ ...args });
export const CardsDefaultVariant1 = CardsVariant.bind();
CardsDefaultVariant1.args = {
  brand: "hotel",
  moreCta: false,
  variant: "default",
  teaserDefault: true,
  max:'max-content',
  col: 4,
  img_src:
    "https://casino.hardrock.com/-/media/project/shrss/hri/casinos/hard-rock/casino-lob/destinations/tampa-746x420.jpg?w=343&rev=2ccb7e6160b04f489bc845d6e9353f55&hash=52CA1F99C78C299E705F152ACBE0EF5F",
};
export const CardsDefaultVariant2 = CardsVariant.bind();
CardsDefaultVariant2.args = {
  brand: "",
  moreCta: false,
  variant: "default",
  thirdCta: true,
  teaserDefault: true,
  max:'max-content',
  col: 4,
  img_src:
    "https://casino.hardrock.com/-/media/project/shrss/hri/casinos/hard-rock/casino-lob/destinations/tulsa-746x420.jpg?w=343&rev=35ba4af3d0bf4683b196fd83e3b7aa91&hash=AFC7E8493B4477D3BC84FE2FA31112BA",
};
export const VerticalOverlayVariant = CardsVariant.bind();
VerticalOverlayVariant.args = {
  brand: "",
  moreCta: false,
  variant: "overlay-vertical",
  thirdCta: false,
  teaserDefault: false,
  max:'max-content',
  col: 4,
  img_src: "https://www.hardrock.com/files/5880/12107928_ImageMediumWidth.jpg",
};
export const HorizontalOverlayVariant = CardsVariant.bind();
HorizontalOverlayVariant.args = {
  brand: "",
  moreCta: false,
  thirdCta: false,
  variant: "overlay-horizontal",
  max:'max-content',
  teaserDefault: false,
  col: 6,
  img_src:
    "https://hotel.hardrock.com/files/5829/23120641_ImageMediumWidth.jpg",
};
export const FullWidthOverlayRightVariant = CardsVariant.bind();
FullWidthOverlayRightVariant.args = {
  brand: "",
  moreCta: false,
  variant: "fullbleed teaser--content-right",
  thirdCta: false,
  teaserDefault: false,
  col: 12,
  img_src: "https://www.hardrock.com/files/5880/12553808_ImageLargeWidth.jpg",
};
export const FullWidthOverlayLeftVariant = CardsVariant.bind();
FullWidthOverlayLeftVariant.args = {
  brand: "",
  moreCta: false,
  variant: "fullbleed teaser--content-left",
  teaserDefault: false,
  col: 12,
  img_src: "https://www.hardrock.com/files/5880/12564694_ImageLargeWidth.jpg",
};
export const FullWidthOverlayRightVariantWhite = CardsVariant.bind();
FullWidthOverlayRightVariantWhite.args = {
  brand: "",
  moreCta: false,
  variant: "fullbleed teaser--content-right teaser--overlay-white",
  thirdCta: false,
  teaserDefault: false,
  col: 12,
  img_src: "https://hotel.hardrock.com/files/5829/12207403_ImageLargeWidth.jpg",
};
export const FullWidthOverlayLeftVariantWhite = CardsVariant.bind();
FullWidthOverlayLeftVariantWhite.args = {
  brand: "",
  moreCta: false,
  variant: "fullbleed teaser--content-left teaser--overlay-white",
  thirdCta: false,
  teaserDefault: false,
  col: 12,
  img_src: "https://hotel.hardrock.com/files/5829/15905631_ImageLargeWidth.jpg",
};
export const ShadowDisable = CardsVariant.bind();
ShadowDisable.args = {
  brand: "",
  moreCta: false,
  variant: "default",
  thirdCta: true,
  shadowDisable: true,
  teaserDefault: true,
  col: 4,
  img_src:
    "https://casino.hardrock.com/-/media/project/shrss/hri/casinos/hard-rock/casino-lob/destinations/tampa-746x420.jpg?w=343&rev=2ccb7e6160b04f489bc845d6e9353f55&hash=52CA1F99C78C299E705F152ACBE0EF5F",
};