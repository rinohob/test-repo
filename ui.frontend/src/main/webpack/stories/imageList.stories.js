import Handlebars from 'handlebars/runtime.js';
import ImageList from '../components/imageList/hbs/imageList.hbs';

Handlebars.registerPartial('ImageList', ImageList);

export default {
  title: 'Components/ImageList',
  argTypes: {
    variant: {
      control: "select",
      options: ["default", "reverb", "unity"],
    },
  },
};
const items = [
    { src: "https://hotel.hardrock.com/files/5829/awards-travel-leisure-2022.png", alt: "logo" },
    { src: "https://hotel.hardrock.com/files/5829/awards-JD-Power-2023.png", alt: "logo" },
    { src: "https://hotel.hardrock.com/files/5829/21808171_ImageMediumWidth.png", alt: "logo" },
    { src: "https://hotel.hardrock.com/files/5829/21808176_ImageMediumWidth.png", alt: "logo" },
    { src: "https://hotel.hardrock.com/files/5829/21808181_ImageMediumWidth.png", alt: "logo" },
    { src: "https://hotel.hardrock.com/files/5829/21808186_ImageMediumWidth.png", alt: "logo" },
    { src: "https://hotel.hardrock.com/files/5829/21808186_ImageMediumWidth.png", alt: "logo" },
  ];

const ImageListVariant = ({label, ...args}) => ImageList({...args});
export const ImageListType = ImageListVariant.bind();
ImageListType.args = {
    variant: "default",
    brand: "default",
    items: items,
    showDots: true,
}