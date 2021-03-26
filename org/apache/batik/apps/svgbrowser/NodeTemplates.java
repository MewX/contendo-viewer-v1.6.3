/*      */ package org.apache.batik.apps.svgbrowser;
/*      */ 
/*      */ import java.lang.reflect.Field;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class NodeTemplates
/*      */ {
/*      */   public static final String VALUE = "Value";
/*      */   public static final String NAME = "Name";
/*      */   public static final String TYPE = "Type";
/*      */   public static final String DESCRIPTION = "Description";
/*      */   public static final String CATEGORY = "Category";
/*      */   public static final String BASIC_SHAPES = "Basic Shapes";
/*      */   public static final String LINKING = "Linking";
/*      */   public static final String TEXT = "Text";
/*      */   public static final String ANIMATION = "Animation";
/*      */   public static final String CLIP_MASK_COMPOSITE = "Clipping, Masking and Compositing";
/*      */   public static final String COLOR = "Color";
/*      */   public static final String INTERACTIVITY = "Interactivity";
/*      */   public static final String FONTS = "Fonts";
/*      */   public static final String DOCUMENT_STRUCTURE = "Document Structure";
/*      */   public static final String FILTER_EFFECTS = "Filter Effects";
/*      */   public static final String EXTENSIBILITY = "Extensibility";
/*      */   public static final String GRADIENTS_AND_PATTERNS = "Gradients and Patterns";
/*      */   public static final String PAINTING = "Painting: Filling, Stroking and Marker Symbols";
/*      */   public static final String METADATA = "Metadata";
/*      */   public static final String PATHS = "Paths";
/*      */   public static final String SCRIPTING = "Scripting";
/*      */   public static final String STYLING = "Styling";
/*  100 */   private Map nodeTemplatesMap = new HashMap<Object, Object>();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  105 */   private ArrayList categoriesList = new ArrayList();
/*      */ 
/*      */ 
/*      */   
/*  109 */   public static String rectMemberName = "rectElement";
/*      */   
/*  111 */   public static String rectElementValue = "<rect width=\"0\" height=\"0\"/>";
/*      */   
/*  113 */   public static String rectElementName = "rect";
/*      */   
/*  115 */   public static short rectElementType = 1;
/*      */   
/*  117 */   public static String rectElementCategory = "Basic Shapes";
/*      */   
/*  119 */   public static String rectElementDescription = "Rect";
/*      */ 
/*      */   
/*  122 */   public static String circleMemberName = "circleElement";
/*      */   
/*  124 */   public static String circleElementValue = "<circle r=\"0\"/>";
/*      */   
/*  126 */   public static String circleElementName = "circle";
/*      */   
/*  128 */   public short circleElementType = 1;
/*      */   
/*  130 */   public static String circleElementCategory = "Basic Shapes";
/*      */   
/*  132 */   public static String circleElementDescription = "Circle";
/*      */ 
/*      */   
/*  135 */   public static String lineElementMemberName = "lineElement";
/*      */   
/*  137 */   public static String lineElementName = "line";
/*      */   
/*  139 */   public static String lineElementValue = "<line x1=\"0\" y1=\"0\" x2=\"0\" y2=\"0\"/>";
/*      */   
/*  141 */   public static short lineElementType = 1;
/*      */   
/*  143 */   public static String lineElementCategory = "Basic Shapes";
/*      */   
/*  145 */   public static String lineElementDescription = "Text";
/*      */ 
/*      */   
/*  148 */   public static String pathElementMemberName = "pathElement";
/*      */   
/*  150 */   public static String pathElementName = "path";
/*      */   
/*  152 */   public static String pathElementValue = "<path d=\"M0,0\"/>";
/*      */   
/*  154 */   public static short pathElementType = 1;
/*      */   
/*  156 */   public static String pathElementCategory = "Paths";
/*      */   
/*  158 */   public static String pathElementDescription = "Path";
/*      */ 
/*      */   
/*  161 */   public static String groupElementMemberName = "groupElement";
/*      */   
/*  163 */   public static String groupElementName = "g";
/*      */   
/*  165 */   public static String groupElementValue = "<g/>";
/*      */   
/*  167 */   public static short groupElementType = 1;
/*      */   
/*  169 */   public static String groupElementCategory = "Document Structure";
/*      */   
/*  171 */   public static String groupElementDescription = "Group";
/*      */ 
/*      */   
/*  174 */   public static String ellipseElementMemberName = "ellipseElement";
/*      */   
/*  176 */   public static String ellipseElementName = "ellipse";
/*      */   
/*  178 */   public static String ellipseElementValue = "<ellipse/>";
/*      */   
/*  180 */   public static short ellipseElementType = 1;
/*      */   
/*  182 */   public static String ellipseElementCategory = "Basic Shapes";
/*      */   
/*  184 */   public static String ellipseElementDescription = "Ellipse";
/*      */ 
/*      */   
/*  187 */   public static String imageElementMemberName = "imageElement";
/*      */   
/*  189 */   public static String imageElementName = "image";
/*      */   
/*  191 */   public static String imageElementValue = "<image xlink:href=\"file/c://\"/>";
/*      */   
/*  193 */   public static short imageElementType = 1;
/*      */   
/*  195 */   public static String imageElementCategory = "Document Structure";
/*      */   
/*  197 */   public static String imageElementDescription = "Image";
/*      */ 
/*      */   
/*  200 */   public static String polygonElementMemberName = "polygonElement";
/*      */   
/*  202 */   public static String polygonElementName = "polygon";
/*      */   
/*  204 */   public static String polygonElementValue = "<polygon/>";
/*      */   
/*  206 */   public static short polygonElementType = 1;
/*      */   
/*  208 */   public static String polygonElementCategory = "Basic Shapes";
/*      */   
/*  210 */   public static String polygonElementDescription = "Polygon";
/*      */ 
/*      */   
/*  213 */   public static String polylineElementMemberName = "polylineElement";
/*      */   
/*  215 */   public static String polylineElementName = "polyline";
/*      */   
/*  217 */   public static String polylineElementValue = "<polyline/>";
/*      */   
/*  219 */   public static short polylineElementType = 1;
/*      */   
/*  221 */   public static String polylineElementCategory = "Basic Shapes";
/*      */   
/*  223 */   public static String polylineElementDescription = "Polyline";
/*      */ 
/*      */   
/*  226 */   public static String textElementMemberName = "textElement";
/*      */   
/*  228 */   public static String textElementName = "text";
/*      */   
/*  230 */   public static String textElementValue = "<text> </text>";
/*      */   
/*  232 */   public static short textElementType = 1;
/*      */   
/*  234 */   public static String textElementCategory = "Text";
/*      */   
/*  236 */   public static String textElementDescription = "Text";
/*      */ 
/*      */   
/*  239 */   public static String tRefElementMemberName = "tRefElement";
/*      */   
/*  241 */   public static String tRefElementName = "tref";
/*      */   
/*  243 */   public static String tRefElementValue = "<tref/>";
/*      */   
/*  245 */   public static short tRefElementType = 1;
/*      */   
/*  247 */   public static String tRefElementCategory = "Text";
/*      */   
/*  249 */   public static String tRefElementDescription = "TRef";
/*      */ 
/*      */   
/*  252 */   public static String tspanElementMemberName = "tspanElement";
/*      */   
/*  254 */   public static String tspanElementName = "tspan";
/*      */   
/*  256 */   public static String tspanElementValue = "<tspan/>";
/*      */   
/*  258 */   public static short tspanElementType = 1;
/*      */   
/*  260 */   public static String tspanElementCategory = "Text";
/*      */   
/*  262 */   public static String tspanElementDescription = "TSpan";
/*      */ 
/*      */   
/*  265 */   public static String textPathElementMemberName = "textPathElement";
/*      */   
/*  267 */   public static String textPathElementName = "textPath";
/*      */   
/*  269 */   public static String textPathElementValue = "<textPath/>";
/*      */   
/*  271 */   public static short textPathElementType = 1;
/*      */   
/*  273 */   public static String textPathElementCategory = "Text";
/*      */   
/*  275 */   public static String textPathElementDescription = "TextPath";
/*      */ 
/*      */   
/*  278 */   public static String svgElementMemberName = "svgElement";
/*      */   
/*  280 */   public static String svgElementName = "svg";
/*      */   
/*  282 */   public static String svgElementValue = "<svg/>";
/*      */   
/*  284 */   public static short svgElementType = 1;
/*      */   
/*  286 */   public static String svgElementCategory = "Document Structure";
/*      */   
/*  288 */   public static String svgElementDescription = "svg";
/*      */ 
/*      */   
/*  291 */   public static String feBlendElementMemberName = "feBlendElement";
/*      */   
/*  293 */   public static String feBlendElementName = "feBlend";
/*      */   
/*  295 */   public static String feBlendElementValue = "<feBlend/>";
/*      */   
/*  297 */   public static short feBlendElementType = 1;
/*      */   
/*  299 */   public static String feBlendElementCategory = "Filter Effects";
/*      */   
/*  301 */   public static String feBlendElementDescription = "FeBlend";
/*      */ 
/*      */   
/*  304 */   public static String feColorMatrixElementMemberName = "feColorMatrixElement";
/*      */   
/*  306 */   public static String feColorMatrixElementName = "feColorMatrix";
/*      */   
/*  308 */   public static String feColorMatrixElementValue = "<feColorMatrix/>";
/*      */   
/*  310 */   public static short feColorMatrixElementType = 1;
/*      */   
/*  312 */   public static String feColorMatrixElementCategory = "Filter Effects";
/*      */   
/*  314 */   public static String feColorMatrixElementDescription = "FeColorMatrix";
/*      */ 
/*      */   
/*  317 */   public static String feComponentTransferElementMemberName = "feComponentTransferElement";
/*      */   
/*  319 */   public static String feComponentTransferElementName = "feComponentTransfer";
/*      */   
/*  321 */   public static String feComponentTransferElementValue = "<feComponentTransfer/>";
/*      */   
/*  323 */   public static short feComponentTransferElementType = 1;
/*      */   
/*  325 */   public static String feComponentTransferElementCategory = "Filter Effects";
/*      */   
/*  327 */   public static String feComponentTransferElementDescription = "FeComponentTransfer";
/*      */ 
/*      */   
/*  330 */   public static String feCompositeElementMemberName = "feCompositeElement";
/*      */   
/*  332 */   public static String feCompositeElementName = "feComposite";
/*      */   
/*  334 */   public static String feCompositeElementValue = "<feComposite/>";
/*      */   
/*  336 */   public static short feCompositeElementType = 1;
/*      */   
/*  338 */   public static String feCompositeElementCategory = "Filter Effects";
/*      */   
/*  340 */   public static String feCompositeElementDescription = "FeComposite";
/*      */ 
/*      */   
/*  343 */   public static String feConvolveMatrixElementMemberName = "feConvolveMatrixElement";
/*      */   
/*  345 */   public static String feConvolveMatrixElementName = "feConvolveMatrix";
/*      */   
/*  347 */   public static String feConvolveMatrixElementValue = "<feConvolveMatrix/>";
/*      */   
/*  349 */   public static short feConvolveMatrixElementType = 1;
/*      */   
/*  351 */   public static String feConvolveMatrixElementCategory = "Filter Effects";
/*      */   
/*  353 */   public static String feConvolveMatrixElementDescription = "FeConvolveMatrix";
/*      */ 
/*      */   
/*  356 */   public static String feDiffuseLightingElementMemberName = "feDiffuseLightingElement";
/*      */   
/*  358 */   public static String feDiffuseLightingElementName = "feDiffuseLighting";
/*      */   
/*  360 */   public static String feDiffuseLightingElementValue = "<feDiffuseLighting/>";
/*      */   
/*  362 */   public static short feDiffuseLightingElementType = 1;
/*      */   
/*  364 */   public static String feDiffuseLightingElementCategory = "Filter Effects";
/*      */   
/*  366 */   public static String feDiffuseLightingElementDescription = "FeDiffuseLighting";
/*      */ 
/*      */   
/*  369 */   public static String feDisplacementMapElementMemberName = "feDisplacementMapElement";
/*      */   
/*  371 */   public static String feDisplacementMapElementName = "feDisplacementMap";
/*      */   
/*  373 */   public static String feDisplacementMapElementValue = "<feDisplacementMap/>";
/*      */   
/*  375 */   public static short feDisplacementMapElementType = 1;
/*      */   
/*  377 */   public static String feDisplacementMapElementCategory = "Filter Effects";
/*      */   
/*  379 */   public static String feDisplacementMapElementDescription = "FeDisplacementMap";
/*      */ 
/*      */   
/*  382 */   public static String feDistantLightElementMemberName = "feDistantLightElement";
/*      */   
/*  384 */   public static String feDistantLightElementName = "feDistantLight";
/*      */   
/*  386 */   public static String feDistantLightElementValue = "<feDistantLight/>";
/*      */   
/*  388 */   public static short feDistantLightElementType = 1;
/*      */   
/*  390 */   public static String feDistantLightElementCategory = "Filter Effects";
/*      */   
/*  392 */   public static String feDistantLightElementDescription = "FeDistantLight";
/*      */ 
/*      */   
/*  395 */   public static String feFloodElementMemberName = "feFloodElement";
/*      */   
/*  397 */   public static String feFloodElementName = "feFlood";
/*      */   
/*  399 */   public static String feFloodElementValue = "<feFlood/>";
/*      */   
/*  401 */   public static short feFloodElementType = 1;
/*      */   
/*  403 */   public static String feFloodElementCategory = "Filter Effects";
/*      */   
/*  405 */   public static String feFloodElementDescription = "FeFlood";
/*      */ 
/*      */   
/*  408 */   public static String feFuncAElementMemberName = "feFuncAElement";
/*      */   
/*  410 */   public static String feFuncAElementName = "feFuncA";
/*      */   
/*  412 */   public static String feFuncAElementValue = "<feFuncA/>";
/*      */   
/*  414 */   public static short feFuncAElementType = 1;
/*      */   
/*  416 */   public static String feFuncAElementCategory = "Filter Effects";
/*      */   
/*  418 */   public static String feFuncAElementDescription = "FeFuncA";
/*      */ 
/*      */   
/*  421 */   public static String feFuncBElementMemberName = "feFuncBElement";
/*      */   
/*  423 */   public static String feFuncBElementName = "feFuncB";
/*      */   
/*  425 */   public static String feFuncBElementValue = "<feFuncB/>";
/*      */   
/*  427 */   public static short feFuncBElementType = 1;
/*      */   
/*  429 */   public static String feFuncBElementCategory = "Filter Effects";
/*      */   
/*  431 */   public static String feFuncBElementDescription = "FeFuncB";
/*      */ 
/*      */   
/*  434 */   public static String feFuncGElementMemberName = "feFuncGElement";
/*      */   
/*  436 */   public static String feFuncGElementName = "feFuncG";
/*      */   
/*  438 */   public static String feFuncGElementValue = "<feFuncG/>";
/*      */   
/*  440 */   public static short feFuncGElementType = 1;
/*      */   
/*  442 */   public static String feFuncGElementCategory = "Filter Effects";
/*      */   
/*  444 */   public static String feFuncGElementDescription = "FeFuncG";
/*      */ 
/*      */   
/*  447 */   public static String feFuncRElementMemberName = "feFuncRElement";
/*      */   
/*  449 */   public static String feFuncRElementName = "feFuncR";
/*      */   
/*  451 */   public static String feFuncRElementValue = "<feFuncR/>";
/*      */   
/*  453 */   public static short feFuncRElementType = 1;
/*      */   
/*  455 */   public static String feFuncRElementCategory = "Filter Effects";
/*      */   
/*  457 */   public static String feFuncRElementDescription = "FeFuncR";
/*      */ 
/*      */   
/*  460 */   public static String feGaussianBlurElementMemberName = "feGaussianBlurElement";
/*      */   
/*  462 */   public static String feGaussianBlurElementName = "feGaussianBlur";
/*      */   
/*  464 */   public static String feGaussianBlurElementValue = "<feGaussianBlur/>";
/*      */   
/*  466 */   public static short feGaussianBlurElementType = 1;
/*      */   
/*  468 */   public static String feGaussianBlurElementCategory = "Filter Effects";
/*      */   
/*  470 */   public static String feGaussianBlurElementDescription = "FeGaussianBlur";
/*      */ 
/*      */   
/*  473 */   public static String feImageElementMemberName = "feImageElement";
/*      */   
/*  475 */   public static String feImageElementName = "feImage";
/*      */   
/*  477 */   public static String feImageElementValue = "<feImage/>";
/*      */   
/*  479 */   public static short feImageElementType = 1;
/*      */   
/*  481 */   public static String feImageElementCategory = "Filter Effects";
/*      */   
/*  483 */   public static String feImageElementDescription = "FeImage";
/*      */ 
/*      */   
/*  486 */   public static String feMergeElementMemberName = "feImageElement";
/*      */   
/*  488 */   public static String feMergeElementName = "feMerge";
/*      */   
/*  490 */   public static String feMergeElementValue = "<feMerge/>";
/*      */   
/*  492 */   public static short feMergeElementType = 1;
/*      */   
/*  494 */   public static String feMergeElementCategory = "Filter Effects";
/*      */   
/*  496 */   public static String feMergeElementDescription = "FeMerge";
/*      */ 
/*      */   
/*  499 */   public static String feMergeNodeElementMemberName = "feMergeNodeElement";
/*      */   
/*  501 */   public static String feMergeNodeElementName = "feMergeNode";
/*      */   
/*  503 */   public static String feMergeNodeElementValue = "<feMergeNode/>";
/*      */   
/*  505 */   public static short feMergeNodeElementType = 1;
/*      */   
/*  507 */   public static String feMergeNodeElementCategory = "Filter Effects";
/*      */   
/*  509 */   public static String feMergeNodeElementDescription = "FeMergeNode";
/*      */ 
/*      */   
/*  512 */   public static String feMorphologyElementMemberName = "feMorphologyElement";
/*      */   
/*  514 */   public static String feMorphologyElementName = "feMorphology";
/*      */   
/*  516 */   public static String feMorphologyElementValue = "<feMorphology/>";
/*      */   
/*  518 */   public static short feMorphologyElementType = 1;
/*      */   
/*  520 */   public static String feMorphologyElementCategory = "Filter Effects";
/*      */   
/*  522 */   public static String feMorphologyElementDescription = "FeMorphology";
/*      */ 
/*      */   
/*  525 */   public static String feOffsetElementMemberName = "feMorphologyElement";
/*      */   
/*  527 */   public static String feOffsetElementName = "feOffset";
/*      */   
/*  529 */   public static String feOffsetElementValue = "<feOffset/>";
/*      */   
/*  531 */   public static short feOffsetElementType = 1;
/*      */   
/*  533 */   public static String feOffsetElementCategory = "Filter Effects";
/*      */   
/*  535 */   public static String feOffsetElementDescription = "FeOffset";
/*      */ 
/*      */   
/*  538 */   public static String fePointLightElementMemberName = "fePointLightElement";
/*      */   
/*  540 */   public static String fePointLightElementName = "fePointLight";
/*      */   
/*  542 */   public static String fePointLightElementValue = "<fePointLight/>";
/*      */   
/*  544 */   public static short fePointLightElementType = 1;
/*      */   
/*  546 */   public static String fePointLightElementCategory = "Filter Effects";
/*      */   
/*  548 */   public static String fePointLightElementDescription = "FePointLight";
/*      */ 
/*      */   
/*  551 */   public static String feSpecularLightingElementMemberName = "fePointLightElement";
/*      */   
/*  553 */   public static String feSpecularLightingElementName = "feSpecularLighting";
/*      */   
/*  555 */   public static String feSpecularLightingElementValue = "<feSpecularLighting/>";
/*      */   
/*  557 */   public static short feSpecularLightingElementType = 1;
/*      */   
/*  559 */   public static String feSpecularLightingElementCategory = "Filter Effects";
/*      */   
/*  561 */   public static String feSpecularLightingElementDescription = "FeSpecularLighting";
/*      */ 
/*      */   
/*  564 */   public static String feSpotLightElementMemberName = "feSpotLightElement";
/*      */   
/*  566 */   public static String feSpotLightElementName = "feSpotLight";
/*      */   
/*  568 */   public static String feSpotLightElementValue = "<feSpotLight/>";
/*      */   
/*  570 */   public static short feSpotLightElementType = 1;
/*      */   
/*  572 */   public static String feSpotLightElementCategory = "Filter Effects";
/*      */   
/*  574 */   public static String feSpotLightElementDescription = "FeSpotLight";
/*      */ 
/*      */   
/*  577 */   public static String feTileElementMemberName = "feTileElement";
/*      */   
/*  579 */   public static String feTileElementName = "feTile";
/*      */   
/*  581 */   public static String feTileElementValue = "<feTile/>";
/*      */   
/*  583 */   public static short feTileElementType = 1;
/*      */   
/*  585 */   public static String feTileElementCategory = "Filter Effects";
/*      */   
/*  587 */   public static String feTileElementDescription = "FeTile";
/*      */ 
/*      */   
/*  590 */   public static String feTurbulenceElementMemberName = "feTurbulenceElement";
/*      */   
/*  592 */   public static String feTurbulenceElementName = "feTurbulence";
/*      */   
/*  594 */   public static String feTurbulenceElementValue = "<feTurbulence/>";
/*      */   
/*  596 */   public static short feTurbulenceElementType = 1;
/*      */   
/*  598 */   public static String feTurbulenceElementCategory = "Filter Effects";
/*      */   
/*  600 */   public static String feTurbulenceElementDescription = "FeTurbulence";
/*      */ 
/*      */   
/*  603 */   public static String filterElementMemberName = "filterElement";
/*      */   
/*  605 */   public static String filterElementName = "filter";
/*      */   
/*  607 */   public static String filterElementValue = "<filter/>";
/*      */   
/*  609 */   public static short filterElementType = 1;
/*      */   
/*  611 */   public static String filterElementCategory = "Filter Effects";
/*      */   
/*  613 */   public static String filterElementDescription = "Filter";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  655 */   public static String aElementMemberName = "aElement";
/*      */   
/*  657 */   public static String aElementName = "a";
/*      */   
/*  659 */   public static String aElementValue = "<a/>";
/*      */   
/*  661 */   public static short aElementType = 1;
/*      */   
/*  663 */   public static String aElementCategory = "Linking";
/*      */   
/*  665 */   public static String aElementDescription = "A";
/*      */ 
/*      */   
/*  668 */   public static String altGlyphElementMemberName = "altGlyphElement";
/*      */   
/*  670 */   public static String altGlyphElementName = "altGlyph";
/*      */   
/*  672 */   public static String altGlyphElementValue = "<altGlyph/>";
/*      */   
/*  674 */   public static short altGlyphElementType = 1;
/*      */   
/*  676 */   public static String altGlyphElementCategory = "Text";
/*      */   
/*  678 */   public static String altGlyphElementDescription = "AltGlyph";
/*      */ 
/*      */   
/*  681 */   public static String altGlyphDefElementMemberName = "altGlyphDefElement";
/*      */   
/*  683 */   public static String altGlyphDefElementName = "altGlyphDef";
/*      */   
/*  685 */   public static String altGlyphDefElementValue = "<altGlyphDef/>";
/*      */   
/*  687 */   public static short altGlyphDefElementType = 1;
/*      */   
/*  689 */   public static String altGlyphDefElementCategory = "Text";
/*      */   
/*  691 */   public static String altGlyphDefElementDescription = "AltGlyphDef";
/*      */ 
/*      */   
/*  694 */   public static String altGlyphItemElementMemberName = "altGlyphItemElement";
/*      */   
/*  696 */   public static String altGlyphItemElementName = "altGlyphItem";
/*      */   
/*  698 */   public static String altGlyphItemElementValue = "<altGlyphItem/>";
/*      */   
/*  700 */   public static short altGlyphItemElementType = 1;
/*      */   
/*  702 */   public static String altGlyphItemElementCategory = "Text";
/*      */   
/*  704 */   public static String altGlyphItemElementDescription = "AltGlyphItem";
/*      */ 
/*      */   
/*  707 */   public static String clipPathElementMemberName = "clipPathElement";
/*      */   
/*  709 */   public static String clipPathElementName = "clipPath";
/*      */   
/*  711 */   public static String clipPathElementValue = "<clipPath/>";
/*      */   
/*  713 */   public static short clipPathElementType = 1;
/*      */   
/*  715 */   public static String clipPathElementCategory = "Clipping, Masking and Compositing";
/*      */   
/*  717 */   public static String clipPathElementDescription = "ClipPath";
/*      */ 
/*      */   
/*  720 */   public static String colorProfileElementMemberName = "colorProfileElement";
/*      */   
/*  722 */   public static String colorProfileElementName = "color-profile";
/*      */   
/*  724 */   public static String colorProfileElementValue = "<color-profile/>";
/*      */   
/*  726 */   public static short colorProfileElementType = 1;
/*      */   
/*  728 */   public static String colorProfileElementCategory = "Color";
/*      */   
/*  730 */   public static String colorProfileElementDescription = "ColorProfile";
/*      */ 
/*      */   
/*  733 */   public static String cursorElementMemberName = "cursorElement";
/*      */   
/*  735 */   public static String cursorElementName = "cursor";
/*      */   
/*  737 */   public static String cursorElementValue = "<cursor/>";
/*      */   
/*  739 */   public static short cursorElementType = 1;
/*      */   
/*  741 */   public static String cursorElementCategory = "Interactivity";
/*      */   
/*  743 */   public static String cursorElementDescription = "Cursor";
/*      */ 
/*      */   
/*  746 */   public static String definitionSrcElementMemberName = "definitionSrcElement";
/*      */   
/*  748 */   public static String definitionSrcElementName = "definition-src";
/*      */   
/*  750 */   public static String definitionSrcElementValue = "<definition-src/>";
/*      */   
/*  752 */   public static short definitionSrcElementType = 1;
/*      */   
/*  754 */   public static String definitionSrcElementCategory = "Fonts";
/*      */   
/*  756 */   public static String definitionSrcElementDescription = "DefinitionSrc";
/*      */ 
/*      */   
/*  759 */   public static String defsElementMemberName = "defsElement";
/*      */   
/*  761 */   public static String defsElementName = "defs";
/*      */   
/*  763 */   public static String defsElementValue = "<defs/>";
/*      */   
/*  765 */   public static short defsElementType = 1;
/*      */   
/*  767 */   public static String defsElementCategory = "Document Structure";
/*      */   
/*  769 */   public static String defsElementDescription = "Defs";
/*      */ 
/*      */   
/*  772 */   public static String descElementMemberName = "descElement";
/*      */   
/*  774 */   public static String descElementName = "desc";
/*      */   
/*  776 */   public static String descElementValue = "<desc/>";
/*      */   
/*  778 */   public static short descElementType = 1;
/*      */   
/*  780 */   public static String descElementCategory = "Document Structure";
/*      */   
/*  782 */   public static String descElementDescription = "Desc";
/*      */ 
/*      */   
/*  785 */   public static String foreignObjectElementMemberName = "foreignObjectElement";
/*      */   
/*  787 */   public static String foreignObjectElementName = "foreignObject";
/*      */   
/*  789 */   public static String foreignObjectElementValue = "<foreignObject/>";
/*      */   
/*  791 */   public static short foreignObjectElementType = 1;
/*      */   
/*  793 */   public static String foreignObjectElementCategory = "Extensibility";
/*      */   
/*  795 */   public static String foreignObjectElementDescription = "ForeignObject";
/*      */ 
/*      */   
/*  798 */   public static String glyphElementMemberName = "glyphElement";
/*      */   
/*  800 */   public static String glyphElementName = "glyph";
/*      */   
/*  802 */   public static String glyphElementValue = "<glyph/>";
/*      */   
/*  804 */   public static short glyphElementType = 1;
/*      */   
/*  806 */   public static String glyphElementCategory = "Fonts";
/*      */   
/*  808 */   public static String glyphElementDescription = "Glyph";
/*      */ 
/*      */   
/*  811 */   public static String glyphRefElementMemberName = "glyphRefElement";
/*      */   
/*  813 */   public static String glyphRefElementName = "glyphRef";
/*      */   
/*  815 */   public static String glyphRefElementValue = "<glyphRef/>";
/*      */   
/*  817 */   public static short glyphRefElementType = 1;
/*      */   
/*  819 */   public static String glyphRefElementCategory = "Text";
/*      */   
/*  821 */   public static String glyphRefElementDescription = "GlyphRef";
/*      */ 
/*      */   
/*  824 */   public static String hkernElementMemberName = "hkernElement";
/*      */   
/*  826 */   public static String hkernElementName = "hkern";
/*      */   
/*  828 */   public static String hkernElementValue = "<hkern/>";
/*      */   
/*  830 */   public static short hkernElementType = 1;
/*      */   
/*  832 */   public static String hkernElementCategory = "Fonts";
/*      */   
/*  834 */   public static String hkernElementDescription = "Hkern";
/*      */ 
/*      */   
/*  837 */   public static String linearGradientElementMemberName = "linearGradientElement";
/*      */   
/*  839 */   public static String linearGradientElementName = "linearGradient";
/*      */   
/*  841 */   public static String linearGradientElementValue = "<linearGradient/>";
/*      */   
/*  843 */   public static short linearGradientElementType = 1;
/*      */   
/*  845 */   public static String linearGradientElementCategory = "Gradients and Patterns";
/*      */   
/*  847 */   public static String linearGradientElementDescription = "LinearGradient";
/*      */ 
/*      */   
/*  850 */   public static String markerElementMemberName = "markerElement";
/*      */   
/*  852 */   public static String markerElementName = "marker";
/*      */   
/*  854 */   public static String markerElementValue = "<marker/>";
/*      */   
/*  856 */   public static short markerElementType = 1;
/*      */   
/*  858 */   public static String markerElementCategory = "Painting: Filling, Stroking and Marker Symbols";
/*      */   
/*  860 */   public static String markerElementDescription = "Marker";
/*      */ 
/*      */   
/*  863 */   public static String maskElementMemberName = "maskElement";
/*      */   
/*  865 */   public static String maskElementName = "mask";
/*      */   
/*  867 */   public static String maskElementValue = "<mask/>";
/*      */   
/*  869 */   public static short maskElementType = 1;
/*      */   
/*  871 */   public static String maskElementCategory = "Clipping, Masking and Compositing";
/*      */   
/*  873 */   public static String maskElementDescription = "Mask";
/*      */ 
/*      */   
/*  876 */   public static String metadataElementMemberName = "metadataElement";
/*      */   
/*  878 */   public static String metadataElementName = "metadata";
/*      */   
/*  880 */   public static String metadataElementValue = "<metadata/>";
/*      */   
/*  882 */   public static short metadataElementType = 1;
/*      */   
/*  884 */   public static String metadataElementCategory = "Metadata";
/*      */   
/*  886 */   public static String metadataElementDescription = "Metadata";
/*      */ 
/*      */   
/*  889 */   public static String missingGlyphElementMemberName = "missingGlyphElement";
/*      */   
/*  891 */   public static String missingGlyphElementName = "missing-glyph";
/*      */   
/*  893 */   public static String missingGlyphElementValue = "<missing-glyph/>";
/*      */   
/*  895 */   public static short missingGlyphElementType = 1;
/*      */   
/*  897 */   public static String missingGlyphElementCategory = "Fonts";
/*      */   
/*  899 */   public static String missingGlyphElementDescription = "MissingGlyph";
/*      */ 
/*      */   
/*  902 */   public static String mpathElementMemberName = "mpathElement";
/*      */   
/*  904 */   public static String mpathElementName = "mpath";
/*      */   
/*  906 */   public static String mpathElementValue = "<mpath/>";
/*      */   
/*  908 */   public static short mpathElementType = 1;
/*      */   
/*  910 */   public static String mpathElementCategory = "Animation";
/*      */   
/*  912 */   public static String mpathElementDescription = "Mpath";
/*      */ 
/*      */   
/*  915 */   public static String patternElementMemberName = "patternElement";
/*      */   
/*  917 */   public static String patternElementName = "pattern";
/*      */   
/*  919 */   public static String patternElementValue = "<pattern/>";
/*      */   
/*  921 */   public static short patternElementType = 1;
/*      */   
/*  923 */   public static String patternElementCategory = "Gradients and Patterns";
/*      */   
/*  925 */   public static String patternElementDescription = "Pattern";
/*      */ 
/*      */   
/*  928 */   public static String radialGradientElementMemberName = "radialGradientElement";
/*      */   
/*  930 */   public static String radialGradientElementName = "radialGradient";
/*      */   
/*  932 */   public static String radialGradientElementValue = "<radialGradient/>";
/*      */   
/*  934 */   public static short radialGradientElementType = 1;
/*      */   
/*  936 */   public static String radialGradientElementCategory = "Gradients and Patterns";
/*      */   
/*  938 */   public static String radialGradientElementDescription = "RadialGradient";
/*      */ 
/*      */   
/*  941 */   public static String scriptElementMemberName = "scriptElement";
/*      */   
/*  943 */   public static String scriptElementName = "script";
/*      */   
/*  945 */   public static String scriptElementValue = "<script/>";
/*      */   
/*  947 */   public static short scriptElementType = 1;
/*      */   
/*  949 */   public static String scriptElementCategory = "Scripting";
/*      */   
/*  951 */   public static String scriptElementDescription = "script";
/*      */ 
/*      */   
/*  954 */   public static String setElementMemberName = "setElement";
/*      */   
/*  956 */   public static String setElementName = "set";
/*      */   
/*  958 */   public static String setElementValue = "<set attributeName=\"fill\" from=\"white\" to=\"black\" dur=\"1s\"/>";
/*      */   
/*  960 */   public static short setElementType = 1;
/*      */   
/*  962 */   public static String setElementCategory = "Animation";
/*      */   
/*  964 */   public static String setElementDescription = "set";
/*      */ 
/*      */   
/*  967 */   public static String stopElementMemberName = "stopElement";
/*      */   
/*  969 */   public static String stopElementName = "stop";
/*      */   
/*  971 */   public static String stopElementValue = "<stop/>";
/*      */   
/*  973 */   public static short stopElementType = 1;
/*      */   
/*  975 */   public static String stopElementCategory = "Gradients and Patterns";
/*      */   
/*  977 */   public static String stopElementDescription = "Stop";
/*      */ 
/*      */   
/*  980 */   public static String styleElementMemberName = "styleElement";
/*      */   
/*  982 */   public static String styleElementName = "style";
/*      */   
/*  984 */   public static String styleElementValue = "<style/>";
/*      */   
/*  986 */   public static short styleElementType = 1;
/*      */   
/*  988 */   public static String styleElementCategory = "Styling";
/*      */   
/*  990 */   public static String styleElementDescription = "Style";
/*      */ 
/*      */   
/*  993 */   public static String switchElementMemberName = "switchElement";
/*      */   
/*  995 */   public static String switchElementName = "switch";
/*      */   
/*  997 */   public static String switchElementValue = "<switch/>";
/*      */   
/*  999 */   public static short switchElementType = 1;
/*      */   
/* 1001 */   public static String switchElementCategory = "Document Structure";
/*      */   
/* 1003 */   public static String switchElementDescription = "Switch";
/*      */ 
/*      */   
/* 1006 */   public static String symbolElementMemberName = "symbolElement";
/*      */   
/* 1008 */   public static String symbolElementName = "symbol";
/*      */   
/* 1010 */   public static String symbolElementValue = "<symbol/>";
/*      */   
/* 1012 */   public static short symbolElementType = 1;
/*      */   
/* 1014 */   public static String symbolElementCategory = "Document Structure";
/*      */   
/* 1016 */   public static String symbolElementDescription = "Symbol";
/*      */ 
/*      */   
/* 1019 */   public static String titleElementMemberName = "titleElement";
/*      */   
/* 1021 */   public static String titleElementName = "title";
/*      */   
/* 1023 */   public static String titleElementValue = "<title/>";
/*      */   
/* 1025 */   public static short titleElementType = 1;
/*      */   
/* 1027 */   public static String titleElementCategory = "Document Structure";
/*      */   
/* 1029 */   public static String titleElementDescription = "Title";
/*      */ 
/*      */   
/* 1032 */   public static String useElementMemberName = "useElement";
/*      */   
/* 1034 */   public static String useElementName = "use";
/*      */   
/* 1036 */   public static String useElementValue = "<use/>";
/*      */   
/* 1038 */   public static short useElementType = 1;
/*      */   
/* 1040 */   public static String useElementCategory = "Document Structure";
/*      */   
/* 1042 */   public static String useElementDescription = "Use";
/*      */ 
/*      */   
/* 1045 */   public static String viewElementMemberName = "viewElement";
/*      */   
/* 1047 */   public static String viewElementName = "view";
/*      */   
/* 1049 */   public static String viewElementValue = "<view/>";
/*      */   
/* 1051 */   public static short viewElementType = 1;
/*      */   
/* 1053 */   public static String viewElementCategory = "Linking";
/*      */   
/* 1055 */   public static String viewElementDescription = "View";
/*      */ 
/*      */   
/* 1058 */   public static String vkernElementMemberName = "vkernElement";
/*      */   
/* 1060 */   public static String vkernElementName = "vkern";
/*      */   
/* 1062 */   public static String vkernElementValue = "<vkern/>";
/*      */   
/* 1064 */   public static short vkernElementType = 1;
/*      */   
/* 1066 */   public static String vkernElementCategory = "Fonts";
/*      */   
/* 1068 */   public static String vkernElementDescription = "Vkern";
/*      */ 
/*      */   
/* 1071 */   public static String fontElementMemberName = "fontElement";
/*      */   
/* 1073 */   public static String fontElementName = "font";
/*      */   
/* 1075 */   public static String fontElementValue = "<font/>";
/*      */   
/* 1077 */   public static short fontElementType = 1;
/*      */   
/* 1079 */   public static String fontElementCategory = "Fonts";
/*      */   
/* 1081 */   public static String fontElementDescription = "Font";
/*      */ 
/*      */   
/* 1084 */   public static String fontFaceElementMemberName = "fontFaceElement";
/*      */   
/* 1086 */   public static String fontFaceElementName = "font-face";
/*      */   
/* 1088 */   public static String fontFaceElementValue = "<font-face/>";
/*      */   
/* 1090 */   public static short fontFaceElementType = 1;
/*      */   
/* 1092 */   public static String fontFaceElementCategory = "Fonts";
/*      */   
/* 1094 */   public static String fontFaceElementDescription = "FontFace";
/*      */ 
/*      */   
/* 1097 */   public static String fontFaceFormatElementMemberName = "fontFaceFormatElement";
/*      */   
/* 1099 */   public static String fontFaceFormatElementName = "font-face-format";
/*      */   
/* 1101 */   public static String fontFaceFormatElementValue = "<font-face-format/>";
/*      */   
/* 1103 */   public static short fontFaceFormatElementType = 1;
/*      */   
/* 1105 */   public static String fontFaceFormatElementCategory = "Fonts";
/*      */   
/* 1107 */   public static String fontFaceFormatElementDescription = "FontFaceFormat";
/*      */ 
/*      */   
/* 1110 */   public static String fontFaceNameElementMemberName = "fontFaceNameElement";
/*      */   
/* 1112 */   public static String fontFaceNameElementName = "font-face-name";
/*      */   
/* 1114 */   public static String fontFaceNameElementValue = "<font-face-name/>";
/*      */   
/* 1116 */   public static short fontFaceNameElementType = 1;
/*      */   
/* 1118 */   public static String fontFaceNameElementCategory = "Fonts";
/*      */   
/* 1120 */   public static String fontFaceNameElementDescription = "FontFaceName";
/*      */ 
/*      */   
/* 1123 */   public static String fontFaceSrcElementMemberName = "fontFaceSrcElement";
/*      */   
/* 1125 */   public static String fontFaceSrcElementName = "font-face-src";
/*      */   
/* 1127 */   public static String fontFaceSrcElementValue = "<font-face-src/>";
/*      */   
/* 1129 */   public static short fontFaceSrcElementType = 1;
/*      */   
/* 1131 */   public static String fontFaceSrcElementCategory = "Fonts";
/*      */   
/* 1133 */   public static String fontFaceSrcElementDescription = "FontFaceSrc";
/*      */ 
/*      */   
/* 1136 */   public static String fontFaceUriElementMemberName = "fontFaceUriElement";
/*      */   
/* 1138 */   public static String fontFaceUriElementName = "font-face-uri";
/*      */   
/* 1140 */   public static String fontFaceUriElementValue = "<font-face-uri/>";
/*      */   
/* 1142 */   public static short fontFaceUriElementType = 1;
/*      */   
/* 1144 */   public static String fontFaceUriElementCategory = "Fonts";
/*      */   
/* 1146 */   public static String fontFaceUriElementDescription = "FontFaceUri";
/*      */ 
/*      */   
/* 1149 */   public static String animateElementMemberName = "fontFaceUriElement";
/*      */   
/* 1151 */   public static String animateElementName = "animate";
/*      */   
/* 1153 */   public static String animateElementValue = "<animate attributeName=\"fill\" from=\"white\" to=\"black\" dur=\"1s\"/>";
/*      */ 
/*      */   
/* 1156 */   public static short animateElementType = 1;
/*      */   
/* 1158 */   public static String animateElementCategory = "Animation";
/*      */   
/* 1160 */   public static String animateElementDescription = "Animate";
/*      */ 
/*      */   
/* 1163 */   public static String animateColorElementMemberName = "animateColorElement";
/*      */   
/* 1165 */   public static String animateColorElementName = "animateColor";
/*      */   
/* 1167 */   public static String animateColorElementValue = "<animateColor attributeName=\"fill\" from=\"white\" to=\"black\" dur=\"1s\"/>";
/*      */ 
/*      */   
/* 1170 */   public static short animateColorElementType = 1;
/*      */   
/* 1172 */   public static String animateColorElementCategory = "Animation";
/*      */   
/* 1174 */   public static String animateColorElementDescription = "AnimateColor";
/*      */ 
/*      */   
/* 1177 */   public static String animateMotionElementMemberName = "animateMotionElement";
/*      */   
/* 1179 */   public static String animateMotionElementName = "animateMotion";
/*      */   
/* 1181 */   public static String animateMotionElementValue = "<animateMotion dur=\"1s\" path=\"M0,0\"/>";
/*      */   
/* 1183 */   public static short animateMotionElementType = 1;
/*      */   
/* 1185 */   public static String animateMotionElementCategory = "Animation";
/*      */   
/* 1187 */   public static String animateMotionElementDescription = "AnimateMotion";
/*      */ 
/*      */   
/* 1190 */   public static String animateTransformElementMemberName = "animateTransformElement";
/*      */   
/* 1192 */   public static String animateTransformElementName = "animateTransform";
/*      */   
/* 1194 */   public static String animateTransformElementValue = "<animateTransform attributeName=\"transform\" type=\"rotate\" from=\"0\" to=\"0\" dur=\"1s\"/>";
/*      */ 
/*      */   
/* 1197 */   public static short animateTransformElementType = 1;
/*      */   
/* 1199 */   public static String animateTransformElementCategory = "Animation";
/*      */   
/* 1201 */   public static String animateTransformElementDescription = "AnimateTransform";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NodeTemplates() {
/* 1208 */     this.categoriesList.add("Document Structure");
/* 1209 */     this.categoriesList.add("Styling");
/* 1210 */     this.categoriesList.add("Paths");
/* 1211 */     this.categoriesList.add("Basic Shapes");
/* 1212 */     this.categoriesList.add("Text");
/* 1213 */     this.categoriesList.add("Painting: Filling, Stroking and Marker Symbols");
/* 1214 */     this.categoriesList.add("Color");
/* 1215 */     this.categoriesList.add("Gradients and Patterns");
/* 1216 */     this.categoriesList.add("Clipping, Masking and Compositing");
/* 1217 */     this.categoriesList.add("Filter Effects");
/* 1218 */     this.categoriesList.add("Interactivity");
/* 1219 */     this.categoriesList.add("Linking");
/* 1220 */     this.categoriesList.add("Scripting");
/* 1221 */     this.categoriesList.add("Animation");
/* 1222 */     this.categoriesList.add("Fonts");
/* 1223 */     this.categoriesList.add("Metadata");
/* 1224 */     this.categoriesList.add("Extensibility");
/*      */ 
/*      */     
/* 1227 */     initializeTemplates();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void initializeTemplates() {
/* 1234 */     Field[] fields = getClass().getDeclaredFields();
/* 1235 */     for (Field currentField : fields) {
/*      */       try {
/* 1237 */         if (currentField.getType() == String.class && currentField.getName().endsWith("MemberName")) {
/*      */           
/* 1239 */           boolean isAccessible = currentField.isAccessible();
/* 1240 */           currentField.setAccessible(true);
/* 1241 */           String baseFieldName = currentField.get(this).toString();
/* 1242 */           String nodeValue = getClass().getField(baseFieldName + "Value").get(this).toString();
/*      */           
/* 1244 */           String nodeName = getClass().getField(baseFieldName + "Name").get(this).toString();
/*      */           
/* 1246 */           short nodeType = ((Short)getClass().getField(baseFieldName + "Type").get(this)).shortValue();
/*      */           
/* 1248 */           String nodeDescription = getClass().getField(baseFieldName + "Description").get(this).toString();
/*      */           
/* 1250 */           String nodeCategory = getClass().getField(baseFieldName + "Category").get(this).toString();
/*      */           
/* 1252 */           NodeTemplateDescriptor desc = new NodeTemplateDescriptor(nodeName, nodeValue, nodeType, nodeCategory, nodeDescription);
/*      */ 
/*      */           
/* 1255 */           this.nodeTemplatesMap.put(desc.getName(), desc);
/* 1256 */           currentField.setAccessible(isAccessible);
/*      */         } 
/* 1258 */       } catch (IllegalArgumentException e) {
/* 1259 */         e.printStackTrace();
/* 1260 */       } catch (IllegalAccessException e) {
/* 1261 */         e.printStackTrace();
/* 1262 */       } catch (SecurityException e) {
/* 1263 */         e.printStackTrace();
/* 1264 */       } catch (NoSuchFieldException e) {
/* 1265 */         e.printStackTrace();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class NodeTemplateDescriptor
/*      */   {
/*      */     private String name;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private String xmlValue;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private short type;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private String category;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private String description;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public NodeTemplateDescriptor(String name, String xmlValue, short type, String category, String description) {
/* 1305 */       this.name = name;
/* 1306 */       this.xmlValue = xmlValue;
/* 1307 */       this.type = type;
/* 1308 */       this.category = category;
/* 1309 */       this.description = description;
/*      */     }
/*      */     
/*      */     public String getCategory() {
/* 1313 */       return this.category;
/*      */     }
/*      */     
/*      */     public void setCategory(String category) {
/* 1317 */       this.category = category;
/*      */     }
/*      */     
/*      */     public String getDescription() {
/* 1321 */       return this.description;
/*      */     }
/*      */     
/*      */     public void setDescription(String description) {
/* 1325 */       this.description = description;
/*      */     }
/*      */     
/*      */     public String getName() {
/* 1329 */       return this.name;
/*      */     }
/*      */     
/*      */     public void setName(String name) {
/* 1333 */       this.name = name;
/*      */     }
/*      */     
/*      */     public short getType() {
/* 1337 */       return this.type;
/*      */     }
/*      */     
/*      */     public void setType(short type) {
/* 1341 */       this.type = type;
/*      */     }
/*      */     
/*      */     public String getXmlValue() {
/* 1345 */       return this.xmlValue;
/*      */     }
/*      */     
/*      */     public void setXmlValue(String xmlValue) {
/* 1349 */       this.xmlValue = xmlValue;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ArrayList getCategories() {
/* 1359 */     return this.categoriesList;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Map getNodeTemplatesMap() {
/* 1368 */     return this.nodeTemplatesMap;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/NodeTemplates.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */