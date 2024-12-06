import Handlebars from 'handlebars/runtime.js';
import SocialMedia from '../components/socialmedia/hbs/socialmedia.hbs';
const socialMediaData = [
  {
    link: "https://www.facebook.com/HardRockHotelNewYork/",
    icon: "fa-facebook-f",
    label: "Hard Rock Hotel New York Facebook Link"
  },
  {
    link: "https://twitter.com/HardRockHotels",
    icon: "fa-twitter",
    label: "Hard Rock Hotel Twitter Link"
  },
  {
    link: "https://www.instagram.com/hardrockhotelnyc/",
    icon: "fa-instagram",
    label: "Hard Rock Hotel Instagram Link"
  },
  {
    link: "http://youtube.com/hardrock",
    icon: "fa-youtube",
    label: "YouTube Link"
  },
  {
    link: "http://youtube.com/hardrock",
    icon: "fa-tiktok",
    label: "TikTok Link"
  },
  {
    link: "http://youtube.com/hardrock",
    icon: "fa-linkedin-in",
    label: "LinkedIn Link"
  }
];

export default {
  title: 'Components/SocialMedia',
  argTypes: {
    variant: {
      control: 'select',
      options: ['default', 'col-2'],
    },
  },
};

const SocialMediavariant = ({ label, ...args }) => SocialMedia({ ...args });
export const Deafult = SocialMediavariant.bind();
Deafult.args = {
  socialMediaLinks: socialMediaData
}
