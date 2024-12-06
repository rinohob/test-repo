import Handlebars from "handlebars/runtime.js";
import Accordion from "../components/accordion/hbs/accordion.hbs";


export default {
  title: "Components/Accordion",
  argTypes: {
    isReverb: {
      control: "boolean",
    },
  },
};
const AccordionVariant = ({ label, ...args }) => Accordion({ ...args });
export const DefaultVariant = AccordionVariant.bind();
DefaultVariant.args = {
  variant: "default",
  brand: "default",
};
export const UnityVariant = AccordionVariant.bind();
UnityVariant.args = {
  variant: "unity",
  brand: "unity",
};
export const ReverbVariant = AccordionVariant.bind();
ReverbVariant.args = {
  variant: "reverb",
  brand: "reverb",
};
