import Handlebars from 'handlebars/runtime.js';
import Container from '../components/container/hbs/container.hbs';

Handlebars.registerPartial('container', Container);

export default {
  title: 'Components/Container',
};

const ContainerVariant = ({ label, ...args }) => Container({ ...args });
export const ContainerPrimaryVariant = ContainerVariant.bind();
ContainerPrimaryVariant.args = {
  brand: '',
  fullBleed: false,
  height: '500px',
  bgColor: '#43474E',
};

export const ContainerFullBleedVariant = ContainerVariant.bind();
ContainerFullBleedVariant.args = {
  brand: '',
  fullBleed: true,
  bgImgUrl:
    'https://cafe.hardrock.com/files/5282/23413359_ImageLargeWidth.avif',
};
