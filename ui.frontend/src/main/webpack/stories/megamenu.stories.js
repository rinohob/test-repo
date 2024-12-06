import Handlebars from 'handlebars/runtime.js';
import Header from '../components/header/hbs/header.hbs';
import {
  MOCK_CROWN,
  MOCK_MAINNAV__MEGAMENU,
  MOCK_CASINO_BUTTONS,
} from './mock-data/data';

Handlebars.registerPartial('Header', Header);

export default {
  title: 'Components/MegaMenu',
  argTypes: {
    variant: {
      control: 'select',
      options: ['primary', 'reverb', 'transparent'],
    },
  },
};

const MegaMenuVariant = ({ label, ...args }) => Header({ ...args });

export const MegaMenuPrimaryVariant = MegaMenuVariant.bind();
MegaMenuPrimaryVariant.args = {
  variant: 'primary',
  brand: 'entertainment',
  crownlist: [...MOCK_CROWN],
  mainnav: [...MOCK_MAINNAV__MEGAMENU],
  buttons: [...MOCK_CASINO_BUTTONS],
  isReverb: true,
  imageList: [
    {
      imgUrl:
        'https://casino.hardrock.com/-/media/project/shrss/hri/casinos/hard-rock/casino-lob/logos-and-icons/hard-rock-hotel-casino-logo.png?h=87&iar=0&w=150&rev=7a02cd6d68164c88b18d8e2a6cd592dc',
      url: '/some/useful.uri',
      altText: 'HardRock',
      location: 'HOLLYWOOD, FL',
    },
  ],
};

export const MegaMenuTransparentVariant = MegaMenuVariant.bind();
MegaMenuTransparentVariant.args = {
  variant: 'transparent',
  brand: 'entertainment',
  crownlist: [...MOCK_CROWN],
  mainnav: [...MOCK_MAINNAV__MEGAMENU],
  buttons: [...MOCK_CASINO_BUTTONS],
  isReverb: true,
  imageList: [
    {
      imgUrl:
        'https://casino.hardrock.com/-/media/project/shrss/hri/casinos/hard-rock/casino-lob/logos-and-icons/hard-rock-hotel-casino-logo.png?h=87&iar=0&w=150&rev=7a02cd6d68164c88b18d8e2a6cd592dc',
      url: '/some/useful.uri',
      altText: 'HardRock',
      location: 'HOLLYWOOD, FL',
    },
  ],
};

export const MegaMenuReverbVariant = MegaMenuVariant.bind();
MegaMenuReverbVariant.args = {
  variant: 'reverb',
  brand: 'entertainment',
  crownlist: [...MOCK_CROWN],
  mainnav: [...MOCK_MAINNAV__MEGAMENU],
  buttons: [...MOCK_CASINO_BUTTONS],
  isReverb: true,
  imageList: [
    {
      imgUrl:
        'https://casino.hardrock.com/-/media/project/shrss/hri/casinos/hard-rock/casino-lob/logos-and-icons/hard-rock-hotel-casino-logo.png?h=87&iar=0&w=150&rev=7a02cd6d68164c88b18d8e2a6cd592dc',
      url: '/some/useful.uri',
      altText: 'HardRock',
      location: 'HOLLYWOOD, FL',
    },
  ],
};
