import Handlebars from 'handlebars/runtime.js';
import FindLocation from '../components/findLocation/hbs/findLocation.hbs';

Handlebars.registerPartial('FindLocation', FindLocation);

export default {
  title: "Components/FindLocation",
};

const FindLocationVariant = ({ label, ...args }) => FindLocation({ ...args });
export const AllVariant = FindLocationVariant.bind();
