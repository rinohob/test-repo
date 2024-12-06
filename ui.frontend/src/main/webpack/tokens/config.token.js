const Brands = require('../../../../brands.config');
const StyleDictionaryPackage = require('style-dictionary');


StyleDictionaryPackage.registerFormat({
  name: 'custom/css/class',
  formatter: function (dictionary, config) {
    const isSubBrand = config?.files[0]?.className;
    const getAllStyles = () => {
      return dictionary.allProperties.map(prop => {
        return `--${prop.name}: ${prop.value};`;
      }).join('\n');
    }

    // console.log('brand', isSubBrand);
    if (isSubBrand != 'undefined') {
      return `.page-${isSubBrand}{
       ${getAllStyles()}
      }`
    } else {
      return `:root{
        ${getAllStyles()}
       }`
    }
  }
});


const getStyleDictionaryConfig = (brand, parentBrand) => {
  return {
    source: [`src/main/webpack/tokens/${brand}/**/*.json`, `src/main/webpack/tokens/${brand}/**/*.js`],
    platforms: {
      scss: {
        transformGroup: "scss",
        buildPath: `src/main/webpack/site/${parentBrand ?? brand}/styles/root/`,
        files: [
          {
            destination: `${parentBrand ? '_'+ brand + '_variable.scss' : "_variables.scss"}`,
            format: "custom/css/class",
            className: `${parentBrand  ? brand : undefined}`
          },
        ],
      },
    },
  };
};

const buildBrandLibrary = (brand) => {
  const StyleDictionary = StyleDictionaryPackage.extend(getStyleDictionaryConfig(brand.name));
  StyleDictionary.buildAllPlatforms();
  if(brand.childBrands) {
      brand.childBrands.forEach((childBrand) => {
        const StyleDictionary = StyleDictionaryPackage.extend(getStyleDictionaryConfig(childBrand, brand.name));
        StyleDictionary.buildAllPlatforms();
    });
  }
  
}

Brands.forEach((brand) => {
  buildBrandLibrary(brand);
});
