import Handlebars from "handlebars/runtime.js";
import Footer from "../components/footer/hbs/footer.hbs";

export default {
  title: "Components/Footer",
  argTypes: {
    variant: {
      control: "select",
      options: ["default", "reverb", "unity"],
    },
    isReverb: {
      control: "boolean",
    },
  },
};
const FooterVariant = ({ label, ...args }) => Footer({ ...args });
export const DefaultVariant = FooterVariant.bind();
DefaultVariant.args = {
  variant: "color",
  brand: '',
  bgColor: '#0f0f0f',

};
export const BgImageVariant = FooterVariant.bind();
BgImageVariant.args = {
  variant: "image",
  brand: '',
  bgImgUrl: 'https://cafe.hardrock.com/files/5282/23413359_ImageLargeWidth.avif',
};