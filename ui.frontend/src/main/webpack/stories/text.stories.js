export default {
  title: 'Components/Text',
};

const TextVariant = ({ label, ...args }) => `<div class="text aem-GridColumn aem-GridColumn--default--12">
<div data-cmp-data-layer="{&quot;text-d20849ab7d&quot;:{&quot;@type&quot;:&quot;shrss/components/text&quot;,&quot;repo:modifyDate&quot;:&quot;2024-07-08T06:55:07Z&quot;,&quot;xdm:text&quot;:&quot;<p>this is a test comp</p>\r\n<p>&amp;nbsp;</p>\r\n<p style=\&quot;text-align: center;\&quot;>sample&amp;nbsp;</p>\r\n<p>&amp;nbsp;</p>\r\n<p><a href=\&quot;http://www.google.com\&quot;>link</a></p>\r\n<p>&amp;nbsp;</p>\r\n<p><i>paragraph</i></p>\r\n<pre>\r\ntesting\r\n</pre>\r\n<p>&amp;nbsp;</p>\r\n&quot;}}" id="text-d20849ab7d" class="cmp-text">
    <p>We’ve compiled a list of nearby destinations and attractions along with local publications and what they recommend to do around town during your stay at a REVERB Hotel.
Check out our <a href="www.google.com">curated city guides</a> if you're curious what trendy hot spots that local artists prefer. </p>
</div>
</div>`;
export const DefaultVariant = TextVariant.bind();






