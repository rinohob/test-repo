import Handlebars from 'handlebars/runtime.js';
import Title from '../components/title/hbs/title.hbs';

Handlebars.registerPartial('Title', Title);

export default {
  title: 'Components/Title',
};

const TitleVariant = ({ label, ...args }) => Title({ ...args });
export const AllVariant = TitleVariant.bind();
