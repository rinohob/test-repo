import Handlebars from 'handlebars/runtime.js';
import Video from '../components/video/hbs/video.hbs';
import ThreeDVideo from '../components/video/hbs/threeD.hbs';

Handlebars.registerPartial('Video', Video);

export default {
  title: 'Components/Video',
  argTypes: {
    variant: {
      control: "select",
      options: ["default", "reverb", "unity"],
    },
    videoType: {
      control: "select",
      options: ["youtube", "vimeo"],
    },
    videoId: {
      control: "text",
    },
    videoUrl: {
      control: "text",
    },
    width: {
        control: "number",
    },
    mute: {
        control: "boolean",
    },
    autoplay: {
        control: "boolean",
    },
    loop: {
        control: "boolean",
    },
    layout: {
      control: "radio",
      options: ["fixed", "responsive"],
    },
    fixedWidth: {
      control: "number",
      if: { arg: 'layout', eq: 'fixed' },
    },
  },
};

const ThreeDVideovariant = ({label, ...args}) => ThreeDVideo({...args});
export const ThreeDVideoType = ThreeDVideovariant.bind();
ThreeDVideoType.args = {
    variant: "default",
    brand: "default",
    videoId:"https://my.matterport.com/show/?m=dtZVXtc8zHX&play=1&lp=1&ts=1&hl=0&mf=1",
    videoUrl: "",
    width: "500",
}


const VideoVariant = ({ label, ...args }) => Video({ ...args });
export const YoutubeVideoType = VideoVariant.bind();
YoutubeVideoType.args = {
    variant: "default",
    brand: "default",
    videoType: "youtube",
    videoId:"MLauRmRZiuw",
    videoUrl: "",
    autoplay: false,
    loop: false,
    mute: false,
    layout: "fixed",
    fixedWidth: 1140,
}

export const VimeoVideoType = VideoVariant.bind();
VimeoVideoType.args = {
    variant: "default",
    brand: "default",
    videoType: "vimeo",
    videoId:"9389973",
    videoUrl: "",
    autoplay: false,
    loop: false,
    mute: false,
    layout: "fixed",
    fixedWidth: "",
}