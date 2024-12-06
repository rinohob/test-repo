import Handlebars from 'handlebars/runtime.js';
import Header from '../components/header/hbs/header.hbs';
import {
  MOCK_BUTTONS,
  MOCK_CROWN,
  MOCK_MAINNAV,
  MOCK_CAFE_BUTTONS,
  MOCK_MAINNAV_CAFE,
  MOCK_ENTERTAINMENT_BUTTONS,
  MOCK_CASINO_BUTTONS,
  MOCK_UNITY_BUTTONS,
  MOCK_REVERB_BUTTONS,
  Mock_HardRock_Buttons,
} from './mock-data/data';

Handlebars.registerPartial('Header', Header);

export default {
  title: 'Components/Header',
  argTypes: {
    variant: {
      control: 'select',
      options: ['primary', 'reverb', 'transparent'],
    },
    isReverb: {
      control: 'boolean',
    },
  },
};

const HeaderVariant = ({ label, ...args }) => Header({ ...args });
export const HotelsVariant = HeaderVariant.bind();
HotelsVariant.args = {
  variant: 'transparent',
  brand: 'hotel',
  crownlist: [...MOCK_CROWN],
  mainnav: [...MOCK_MAINNAV],
  buttons: [...MOCK_BUTTONS],
  isReverb: true,
  imageList: [
    {
      imgUrl: 'https://www.hardrock.com/files/5880/LargeLogo.png',
      url: '/some/useful.uri',
      altText: 'HardRock',
    },
  ],
};

export const CafeVariant = HeaderVariant.bind();
CafeVariant.args = {
  variant: 'primary',
  brand: 'cafe',
  crownlist: [...MOCK_CROWN],
  mainnav: [...MOCK_MAINNAV_CAFE],
  buttons: [...MOCK_CAFE_BUTTONS],
  isReverb: true,
  imageList: [
    {
      imgUrl: 'https://www.hardrock.com/files/5880/LargeLogo.png',
      url: '/some/useful.uri',
      altText: 'HardRock',
    },
  ],
};

export const EntertainmentVariant = HeaderVariant.bind();
EntertainmentVariant.args = {
  variant: 'transparent',
  brand: 'entertainment',
  crownlist: [...MOCK_CROWN],
  mainnav: [...MOCK_MAINNAV_CAFE],
  buttons: [...MOCK_ENTERTAINMENT_BUTTONS],
  isReverb: true,
  imageList: [
    {
      imgUrl: 'https://www.hardrock.com/files/5880/LargeLogo.png',
      url: '/some/useful.uri',
      altText: 'HardRock',
    },
  ],
};

export const CasinoVariant = HeaderVariant.bind();
CasinoVariant.args = {
  variant: 'transparent',
  brand: 'entertainment',
  crownlist: [...MOCK_CROWN],
  mainnav: [...MOCK_MAINNAV_CAFE],
  buttons: [...MOCK_CASINO_BUTTONS],
  isReverb: true,
  imageList: [
    {
      imgUrl: 'https://www.hardrock.com/files/5880/LargeLogo.png',
      url: '/some/useful.uri',
      altText: 'HardRock',
    },
  ],
};

export const UnityVariant = HeaderVariant.bind();
UnityVariant.args = {
  variant: 'transparent',
  brand: 'entertainment',
  crownlist: [...MOCK_CROWN],
  mainnav: [...MOCK_MAINNAV_CAFE],
  buttons: [...MOCK_UNITY_BUTTONS],
  isReverb: true,
  imageList: [
    {
      imgUrl: 'https://www.hardrock.com/files/5880/LargeLogo.png',
      url: '/some/useful.uri',
      altText: 'HardRock',
    },
  ],
};

export const ReverbVariant = HeaderVariant.bind();
ReverbVariant.args = {
  variant: 'reverb',
  brand: 'reverb',
  crownlist: [...MOCK_CROWN],
  mainnav: [...MOCK_MAINNAV_CAFE],
  buttons: [...MOCK_REVERB_BUTTONS],
  isReverb: true,
  imageList: [
    {
      imgUrl: 'https://www.hardrock.com/files/5880/LargeLogo.png',
      url: '/some/useful.uri',
      altText: 'HardRock',
    },
  ],
};

export const HardRockVariant = HeaderVariant.bind();
HardRockVariant.args = {
  variant: 'transparent',
  brand: 'reverb',
  crownlist: [...MOCK_CROWN],
  mainnav: [...MOCK_MAINNAV_CAFE],
  buttons: [...Mock_HardRock_Buttons],
  isReverb: true,
  imageList: [
    {
      imgUrl: 'https://www.hardrock.com/files/5880/LargeLogo.png',
      url: '/some/useful.uri',
      altText: 'HardRock',
    },
  ],
};

export const CrownMainNavVariant = HeaderVariant.bind();
CrownMainNavVariant.args = {
  variant: 'transparent',
  brand: 'entertainment',
  crownlist: [...MOCK_CROWN],
  mainnav: [...MOCK_MAINNAV_CAFE],
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

export const CrownMainNavThreeLogoVariant = HeaderVariant.bind();
CrownMainNavThreeLogoVariant.args = {
  variant: 'transparent',
  brand: 'entertainment',
  crownlist: [...MOCK_CROWN],
  mainnav: [...MOCK_MAINNAV_CAFE],
  buttons: [...MOCK_CASINO_BUTTONS],
  isReverb: true,
  imageList: [
    {
      imgUrl:
        'https://casino.hardrock.com/-/media/project/shrss/hri/casinos/hard-rock/casino-lob/logos-and-icons/hard-rock-hotel-casino-logo.png?h=87&iar=0&w=150&rev=7a02cd6d68164c88b18d8e2a6cd592dc',
      url: '/some/useful.uri',
      altText: 'HardRock',
    },
    {
      imgUrl:
        'https://casino.hardrock.com/-/media/project/shrss/hri/casinos/hard-rock/casino-lob/logos-and-icons/hard-rock-hotel-casino-logo.png?h=87&iar=0&w=150&rev=7a02cd6d68164c88b18d8e2a6cd592dc',
      url: '/some/useful.uri',
      altText: 'HardRock',
    },
    {
      imgUrl:
        'https://casino.hardrock.com/-/media/project/shrss/hri/casinos/hard-rock/casino-lob/logos-and-icons/hard-rock-hotel-casino-logo.png?h=87&iar=0&w=150&rev=7a02cd6d68164c88b18d8e2a6cd592dc',
      url: '/some/useful.uri',
      altText: 'HardRock',
    },
  ],
};

export const CrownReverbVariant = HeaderVariant.bind();
CrownReverbVariant.args = {
  variant: 'reverb',
  brand: 'reverb',
  crownlist: [...MOCK_CROWN],
  mainnav: [...MOCK_MAINNAV_CAFE],
  buttons: [...MOCK_REVERB_BUTTONS],
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
