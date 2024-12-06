import Handlebars from "handlebars/runtime.js";
import List from "../components/list/hbs/list.hbs";

export default {
  title: "Components/List",
  argTypes: {
    variant: {
      control: "select",
      options: ["default", "reverb", "unity"],
    },
    isReverb: {
      control: "boolean",
    },
    piped: {
        control: "boolean",
    },
    columns: {
      control: "select",
      options: ["1", "2", "3", "4", "5"],
    }
  },
};
const items = [
  { label: "Memorobilia", url: "/enter" },
  { label: "Careers", url: "/rock", active: true },
  { label: "News", url: "/kids" },
  { label: "Accessibility", url: "/well" },
  { label: "Contacts", url: "/area" },
  { label: "Sitemap", url: "/music" },
  { label: "Travel Advisors", url: "/music" },
];
const ListVariant = ({ label, ...args }) => List({ ...args });
export const DefaultVariant = ListVariant.bind();
DefaultVariant.args = {
  variant: "default",
  brand: "default",
  piped: false,
  items: items,
  columns: "1",
};

