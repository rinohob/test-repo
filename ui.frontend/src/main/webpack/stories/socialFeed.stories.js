import Handlebars from 'handlebars/runtime.js';
import SocialFeed from '../components/socialFeed/hbs/socialFeed.hbs';

Handlebars.registerPartial('socialFeed', SocialFeed);

export default {
  title: 'Components/SocialFeed',
};

const SocialFeedVariant = ({ label, ...args }) => SocialFeed({ ...args });
export const SocialFeedPrimaryVariant = SocialFeedVariant.bind();
SocialFeedPrimaryVariant.args = {
  headerText: 'What’s happening at Hard Rock',
  dataURL: 'http://localhost:6006/resources/mock-socialfeed-data.json',
  links: [
    {
      id: 1,
      name: '@OFFICIALHARDROCK',
      link: 'https://www.instagram.com/officialhardrock/',
    },
    {
      id: 2,
      name: '@HARDROCKCAFE',
      link: 'https://www.instagram.com/hardrockcafe/',
    },
    {
      id: 3,
      name: '@HARDROCKHOTELS',
      link: 'https://www.instagram.com/hardrockhotels/',
    },
  ],
};
