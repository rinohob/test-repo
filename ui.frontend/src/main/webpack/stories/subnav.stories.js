import Handlebars from "handlebars/runtime.js";
import Subnav from "../components/subnav/hbs/subnav.hbs";
import { MOCK_SUBNAV, MOCK_SUBNAV_ACTIVE } from "./mock-data/data";
import header from "../components/subnav/hbs/header.hbs";

Handlebars.registerPartial("header", header);

export default {
  title: "Components/Subnav",
  argTypes: {
    isReverb: {
      control: "boolean",
    },
  },
};
const items = [
  { label: "Entertainment", url: "/enter" },
  { label: "Rock Shop", url: "/rock", active: true },
  { label: "Kids & Teen Activities", url: "/kids" },
  { label: "Wellness", url: "/well" },
  { label: "Area Guides", url: "/area" },
  { label: "Music Express", url: "/music" },
];
const SubnavVariant = ({ label, ...args }) => Subnav({ ...args });
export const DefaultVariant = SubnavVariant.bind();
DefaultVariant.args = {
  variant: "default",
  brand: "default",
  subnavlist: [...MOCK_SUBNAV],
  isReverb: true,
  items: items,
};
export const UnityVariant = SubnavVariant.bind();
UnityVariant.args = {
  variant: "unity",
  brand: "unity",
  subnavlist: [...MOCK_SUBNAV],
  isReverb: true,
  items: items,
};
export const ReverbVariant = SubnavVariant.bind();
ReverbVariant.args = {
  variant: "reverb",
  brand: "reverb",
  subnavlist: [...MOCK_SUBNAV],
  isReverb: true,
  items: items,
};
