import Button from '../components/button/hbs/button.hbs';
import AllButtons from '../components/button/hbs/allButton.hbs';

export default {
  title: 'Components/Button',
  argTypes: {
    variant: {
      control: 'select',
      options: ['primary', 'secondary', 'primary-light', 'secondary-light', 'tertiary'],
    },
    isDisabled: {
      control: 'boolean',
    },
    url: {
      control: 'string'
    }
  },
};

const ButtonVariant = ({ label, ...args }) => Button({ ...args });
export const DefaultVariant = ButtonVariant.bind();
DefaultVariant.args = {
  variant: 'primary',
  buttonText: 'Primary',
}

const AllButtonvariant = ({label, ...args}) => AllButtons({...args});
export const AllButtonsVersion = AllButtonvariant.bind();
