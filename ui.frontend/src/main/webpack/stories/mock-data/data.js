export const MOCK_CROWN = [
  { name: 'Hotels', url: '/some/useful.uri' },
  { name: 'Cafes', url: '/some/useful.uri' },
  { name: 'Casinos', url: '/some/useful.uri' },
  { name: 'Rock Shop', url: '/some/useful.uri' },
  { name: 'Entertainment', url: '/some/useful.uri' },
  { name: 'Games', url: '/some/useful.uri' },
  { name: 'bet', url: '/some/useful.uri' },
];

export const MOCK_MAINNAV = [
  {
    name: 'DESTINATIONS',
    url: '/some/useful.uri',
  },
  { name: 'HOTELS & RESORTS', url: '/some/useful.uri' },
  { name: 'CASINOS', url: '/some/useful.uri' },
  { name: 'OUR STORY', url: '/some/useful.uri' },
  { name: 'MEETINGS & WEDDINGS', url: '/some/useful.uri' },
  { name: 'OFFERS', url: '/some/useful.uri' },
];
export const MOCK_SUBNAV = [
  { name: 'Experience', url: '/some/useful.uri' },
  { name: 'Eat & Drink', url: '#' },
  { name: 'Rock Shop', url: '#' },
  { name: 'Kids & Teen Activities', url: '#' },
  { name: 'Wellness', url: '#' },
  { name: 'Area Guides', url: '#' },
  { name: 'Music Express', url: '#' },
];
export const MOCK_MAINNAV__MEGAMENU = [
  {
    name: 'DESTINATIONS',
    url: '/some/useful.uri',
    child: [
      { name: 'North America', url: '/some/useful.uri' },
      { name: 'Central & South America', url: '/some/useful.uri' },
      { name: 'Europe, Middle East & Africa', url: '/some/useful.uri' },
      { name: 'Asia Pacific', url: '/some/useful.uri' },
    ],
  },
  { name: 'HOTELS & RESORTS', url: '/some/useful.uri' },
  { name: 'CASINOS', url: '/some/useful.uri' },
  {
    name: 'Experience',
    url: '/some/useful.uri',
    child: [
      {
        name: 'Eat & Drink',
        url: '/some/useful.uri',
        subchild: [
          { name: 'Restaurants', url: '/some/useful.uri' },
          { name: 'Bars & Nightlife', url: '/some/useful.uri' },
        ],
      },
      { name: 'Rock Shop', url: '/some/useful.uri' },
      { name: 'Kids & Teen Activities', url: '/some/useful.uri' },
      {
        name: 'Wellness',
        url: '/some/useful.uri',
        subchild: [
          { name: 'Rhythm & Motion', url: '/some/useful.uri' },
          { name: 'Rock On', url: '/some/useful.uri' },
        ],
      },
      { name: 'Area Guides', url: '/some/useful.uri' },
      {
        name: 'Music Experiences',
        url: '/some/useful.uri',
        subchild: [{ name: 'Sound of your stay', url: '/some/useful.uri' }],
      },
      { name: 'Pet Friendly', url: '/some/useful.uri' },
    ],
  },
  { name: 'OUR STORY', url: '/some/useful.uri' },
  { name: 'MEETINGS & WEDDINGS', url: '/some/useful.uri' },
  { name: 'OFFERS', url: '/some/useful.uri' },
];

export const MOCK_MAINNAV_CAFE = [
  { name: 'Menu', url: '/some/useful.uri' },
  { name: 'Locations', url: '/some/useful.uri' },
  { name: 'Catering', url: '/some/useful.uri' },
  { name: 'Parties & Events', url: '/some/useful.uri' },
  { name: `What's Happening`, url: '/some/useful.uri' },
  { name: `Gift Card`, url: '/some/useful.uri' },
  { name: `Shop`, url: '/some/useful.uri' },
];

const COMMON_BUTTONS = [
  {
    url: '/some/useful.uri',
    variation: 'unity',
    icon: 'fas fa-user',
    text: 'Unity Sign In / Join',
    id: '',
    class: 'fas fa-globe',
    type: 'unity',
  },
  {
    url: '#',
    variation: 'map',
    icon: 'fas fa-map-marker',
    type: 'dropdown',
    text: '',
    id: '',
    values: [
      {
        name: 'Portuguese',
        url: '/some/useful.uri',
      },
      {
        name: 'Hindi',
        url: '/some/useful.uri',
      },
    ],
  },
  {
    url: '#',
    variation: 'lang',
    text: 'English',
    id: '',
    type: 'dropdown',
    class: 'fas fa-globe',

    icon: 'fas fa-globe',
    values: [
      {
        name: 'Portuguese',
        url: '/some/useful.uri',
      },
      {
        name: 'Hindi',
        url: '/some/useful.uri',
      },
    ],
  },
];

{
  /* <i class="fas fa-search"></i> */
}

export const Mock_HardRock_Buttons = [
  {
    url: '/some/useful.uri',
    variation: 'icon',
    icon: 'fas fa-search',
    text: 'Find a Location',
    id: '',
    class: 'fas fa-search',
    type: 'unity',
  },
  ...COMMON_BUTTONS,
];

export const MOCK_BUTTONS = [
  ...COMMON_BUTTONS,
  // { url: '/some/useful.uri', variation: 'img', icon: '', url: 'https://hotel.hardrock.com/files/5829/logo-best-rate-drsb.png', id: '', type: 'img'  },
  {
    url: '/some/useful.uri',
    variation: 'primary',
    icon: '',
    text: 'Book Now',
    id: '',
    type: 'primary',
  },
];

export const MOCK_CAFE_BUTTONS = [
  ...COMMON_BUTTONS,
  {
    url: '/some/useful.uri',
    variation: 'secondary',
    icon: '',
    text: 'Reserve a Table',
    id: '',
  },
  {
    url: '/some/useful.uri',
    variation: 'primary',
    type: 'dropdown',
    icon: '',
    text: 'Order Now',
    id: '',
    values: [
      {
        name: 'Portuguese',
        url: '/some/useful.uri',
      },
      {
        name: 'Hindi',
        url: '/some/useful.uri',
      },
    ],
  },
];

export const MOCK_ENTERTAINMENT_BUTTONS = [
  ...COMMON_BUTTONS,
  {
    url: '/some/useful.uri',
    variation: 'img',
    icon: '',
    imgUrl: 'https://hotel.hardrock.com/files/5829/logo-best-rate-drsb.png',
    id: '',
    type: 'img',
  },
  {
    url: '/some/useful.uri',
    variation: 'primary',
    icon: '',
    text: 'View Events',
    id: '',
  },
];

export const MOCK_CASINO_BUTTONS = [
  ...COMMON_BUTTONS,
  {
    url: '/some/useful.uri',
    variation: 'primary',
    icon: '',
    text: 'Book Experience',
    id: '',
  },
];

export const MOCK_UNITY_BUTTONS = [
  {
    url: '/some/useful.uri',
    variation: 'icon',
    icon: 'fas fa-question-circle',
    text: 'Help',
    id: '',
    class: 'fas fa-question-circle',
    type: 'unity',
  },
  ...COMMON_BUTTONS,
];

export const MOCK_REVERB_BUTTONS = [
  {
    url: '#',
    variation: 'map',
    icon: 'fas fa-map-marker',
    type: 'dropdown',
    text: '',
    id: '',
    values: [
      {
        name: 'Portuguese',
        url: '/some/useful.uri',
      },
      {
        name: 'Hindi',
        url: '/some/useful.uri',
      },
    ],
  },
  {
    url: '#',
    variation: 'lang',
    text: 'English',
    id: '',
    type: 'dropdown',
    class: 'fas fa-globe',

    icon: 'fas fa-globe',
    values: [
      {
        name: 'Portuguese',
        url: '/some/useful.uri',
      },
      {
        name: 'Hindi',
        url: '/some/useful.uri',
      },
    ],
  },
  {
    url: '/some/useful.uri',
    variation: 'primary',
    icon: '',
    text: 'Get A Room',
    id: '',
  },
];
