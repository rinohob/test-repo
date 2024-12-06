import Handlebars from 'handlebars/runtime.js';
import Spacer from '../components/separator/hbs/separator.hbs';


export default {
  title: 'Components/Separator',
};

const SpacerVariant = ({ label, ...args }) => Spacer({ ...args });
export const AllVariant = SpacerVariant.bind();
