import Handlebars from 'handlebars/runtime.js';
import Alert from '../components/alert/hbs/alert.hbs';
import header from '../components/alert/hbs/header.hbs';

Handlebars.registerPartial('header', header);

export default {
  title: 'Components/Alert',
  argTypes: {},
};
const AlertVariant = ({ label, ...args }) => Alert({ ...args });
export const DefaultVariant = AlertVariant.bind();
DefaultVariant.args = {
  variant: 'default',
  brand: 'cafe',
  prevIconClass: 'fas fa-chevron-left',
  nextIconClass: 'fas fa-chevron-right',
  dataURL: 'http://localhost:6006/resources/mock-alert-data.json',
};
