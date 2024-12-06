import Handlebars from "handlebars/runtime.js";
import Breadcrumb from "../components/breadcrumb/hbs/breadcrumb.hbs";

export default {
  title: "Components/Breadcrumb",
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
const BreadcrumbVariant = ({ label, ...args }) => Breadcrumb({ ...args });
export const DefaultVariant = BreadcrumbVariant.bind();
DefaultVariant.args = {
  variant: "default",
  brand: "default",
};
export const UnityVariant = BreadcrumbVariant.bind();
UnityVariant.args = {
  variant: "unity",
  brand: "unity",
};
export const ReverbVariant = BreadcrumbVariant.bind();
ReverbVariant.args = {
  variant: "reverb",
  brand: "reverb",
};
export const HiddenVariant = BreadcrumbVariant.bind();
HiddenVariant.args = {
  variant: "",
  brand: "",
  template: "hidden",
};