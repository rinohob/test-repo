import Handlebars from "handlebars/runtime.js";

Handlebars.registerHelper("ifEquals", function (arg1, arg2, options) {
  return arg1 == arg2 ? options.fn(this) : options.inverse(this);
});

Handlebars.registerHelper("ifNot", function (arg1, arg2, options) {
  return arg1 != arg2 ? options.fn(this) : options.inverse(this);
});


Handlebars.registerHelper(
    "In",
    function (...args) {
      const temp = [...args];
      const options = temp.pop();
      const arg1 = temp.shift();
      return temp.indexOf(arg1) > -1 ? options.fn(this) : options.inverse(this);
    }
  );

Handlebars.registerHelper(
  "ifEqualsOR",

  function (arg1, arg2, arg3, arg4, options) {
    return arg1 == arg2 || arg3 === arg4 ? options.fn(this) : options.inverse(this);
  }
);

Handlebars.registerHelper(
  "ifEqualsOR3",

  function (arg1, arg2, arg3, arg4, arg5, arg6, options) {
    return arg1 == arg2 || arg3 === arg4 || arg5 === arg6 ? options.fn(this) : options.inverse(this);
  }
);

Handlebars.registerHelper(
  "ifEqualsOR4",

  function (arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, options) {
    return arg1 == arg2 || arg3 === arg4 || arg5 === arg6 || arg7 === arg8 ? options.fn(this) : options.inverse(this);
  }
);

Handlebars.registerHelper(
  "ifEqualsOR5",

  function (arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, options) {
    return arg1 == arg2 || arg3 === arg4 || arg5 === arg6 || arg7 === arg8 || arg9 === arg10 ? options.fn(this) : options.inverse(this);
  }
);

Handlebars.registerHelper(
  "ifEqualsOR6",

  function (arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, options) {
    return arg1 == arg2 || arg3 === arg4 || arg5 === arg6 || arg7 === arg8 || arg9 === arg10 || arg11 === arg12 ? options.fn(this) : options.inverse(this);
  }
);

Handlebars.registerHelper(
  "ifEqualsOR6",

  function (arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, options) {
    return arg1 == arg2 || arg3 === arg4 || arg5 === arg6 || arg7 === arg8 || arg9 === arg10 || arg11=== arg12 ? options.fn(this) : options.inverse(this);
  }
);

Handlebars.registerHelper(
  "ifEqualsOR7",

  function (arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, args13, arg14 , options) {
    return arg1 == arg2 || arg3 === arg4 || arg5 === arg6 || arg7 === arg8 || arg9 === arg10 || arg11=== arg12 || args13 === arg14 ? options.fn(this) : options.inverse(this);
  }
);

Handlebars.registerHelper(
  "ifEqualsOR8",

  function (arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, args13, arg14 , args15, arg16, options) {
    return arg1 == arg2 || arg3 === arg4 || arg5 === arg6 || arg7 === arg8 || arg9 === arg10 || arg11=== arg12 || args13 === arg14 || args15 === arg16 ? options.fn(this) : options.inverse(this);
  }
);

Handlebars.registerHelper(
  "ifEqualsOR9",

  function (arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, args13, arg14 , args15, arg16, args17, arg18, options) {
    return arg1 == arg2 || arg3 === arg4 || arg5 === arg6 || arg7 === arg8 || arg9 === arg10 || arg11=== arg12 || args13 === arg14 || args15 === arg16 || args17 === arg18 ? options.fn(this) : options.inverse(this);
  }
);

Handlebars.registerHelper(
  "ifEqualsOR10",

  function (arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, args13, arg14 , args15, arg16, args17, arg18, args19, arg20, options) {
    return arg1 == arg2 || arg3 === arg4 || arg5 === arg6 || arg7 === arg8 || arg9 === arg10 || arg11=== arg12 || args13 === arg14 || args15 === arg16 || args17 === arg18 || args19 === arg20? options.fn(this) : options.inverse(this);
  }
);

// TO DO should be changed to general instead of specific to the component
Handlebars.registerHelper(
  'addClass',
  function (array) {
    if(array) {
      if(array.length > 4 && array.length <=6) {
        return 'hrc-carousel__btn--hide-md';
      } else if(array.length > 2 && array.length <=6) {
        return 'hrc-carousel__btn--hide-sm';
      } else if(array.length === 2) {
        return 'hrc-carousel__btn--hide-xs'
      }
    }
    return;
  }
);

window.STORYBOOK__MODE = true;
