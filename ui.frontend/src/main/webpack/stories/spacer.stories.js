import Handlebars from 'handlebars/runtime.js';
import Spacer from '../components/spacer/hbs/spacer.hbs';

Handlebars.registerPartial('Spacer', Spacer);

export default {
  title: 'Components/Spacer',
};

const SpacerVariant = ({ label, ...args }) => Spacer({ ...args });
export const AllVariant = SpacerVariant.bind();
