import Handlebars from 'handlebars/runtime.js';
import Tabs from '../components/tabs/hbs/tabs.hbs';

Handlebars.registerPartial('tabs', Tabs);

export default {
  title: 'Components/Tabs',
};

const tabItems = [
  'Legendary Steak Burgers',
  'Starters & shareables',
  'Sandwiches',
  'Salads & Bowls',
  'Specialty Entrees',
  'Desserts',
  'Kids',
  'Beverages',
];

const TabsVariant = ({ label, ...args }) => Tabs({ ...args });
export const HardRockThemeVariant = TabsVariant.bind();
HardRockThemeVariant.args = {
  brand: '',
  tabItems,
};

export const CafeThemeVariant = TabsVariant.bind();
CafeThemeVariant.args = {
  brand: 'cafe',
  tabItems,
};

export const HotelThemeVariant = TabsVariant.bind();
HotelThemeVariant.args = {
  brand: 'hotel',
  tabItems,
};

export const EntertainmentThemeVariant = TabsVariant.bind();
EntertainmentThemeVariant.args = {
  brand: 'entertainment',
  tabItems,
};

export const ReverbThemeVariant = TabsVariant.bind();
ReverbThemeVariant.args = {
  brand: 'reverb',
  tabItems,
};

export const UnityThemeVariant = TabsVariant.bind();
UnityThemeVariant.args = {
  brand: 'unity',
  tabItems,
};
