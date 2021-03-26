/*      */ package org.apache.batik.anim.dom;
/*      */ 
/*      */ import java.net.URL;
/*      */ import java.util.HashMap;
/*      */ import org.apache.batik.css.dom.CSSOMSVGViewCSS;
/*      */ import org.apache.batik.css.engine.CSSContext;
/*      */ import org.apache.batik.css.engine.CSSEngine;
/*      */ import org.apache.batik.css.engine.SVGCSSEngine;
/*      */ import org.apache.batik.css.engine.value.ShorthandManager;
/*      */ import org.apache.batik.css.engine.value.ValueManager;
/*      */ import org.apache.batik.css.parser.ExtendedParser;
/*      */ import org.apache.batik.dom.AbstractDocument;
/*      */ import org.apache.batik.dom.AbstractStylableDocument;
/*      */ import org.apache.batik.dom.ExtensibleDOMImplementation;
/*      */ import org.apache.batik.dom.events.DOMTimeEvent;
/*      */ import org.apache.batik.dom.events.DocumentEventSupport;
/*      */ import org.apache.batik.dom.svg.SVGOMEvent;
/*      */ import org.apache.batik.dom.util.CSSStyleDeclarationFactory;
/*      */ import org.apache.batik.dom.util.DOMUtilities;
/*      */ import org.apache.batik.i18n.LocalizableSupport;
/*      */ import org.apache.batik.util.ParsedURL;
/*      */ import org.w3c.css.sac.InputSource;
/*      */ import org.w3c.dom.DOMException;
/*      */ import org.w3c.dom.DOMImplementation;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.DocumentType;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.css.CSSStyleDeclaration;
/*      */ import org.w3c.dom.css.CSSStyleSheet;
/*      */ import org.w3c.dom.css.ViewCSS;
/*      */ import org.w3c.dom.events.Event;
/*      */ import org.w3c.dom.stylesheets.StyleSheet;
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
/*      */ public class SVGDOMImplementation
/*      */   extends ExtensibleDOMImplementation
/*      */   implements CSSStyleDeclarationFactory
/*      */ {
/*      */   public static final String SVG_NAMESPACE_URI = "http://www.w3.org/2000/svg";
/*      */   protected static final String RESOURCES = "org.apache.batik.dom.svg.resources.Messages";
/*      */   protected HashMap<String, ExtensibleDOMImplementation.ElementFactory> factories;
/*      */   
/*      */   public static DOMImplementation getDOMImplementation() {
/*   86 */     return DOM_IMPLEMENTATION;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SVGDOMImplementation() {
/*   93 */     this.factories = svg11Factories;
/*   94 */     registerFeature("CSS", "2.0");
/*   95 */     registerFeature("StyleSheets", "2.0");
/*   96 */     registerFeature("SVG", new String[] { "1.0", "1.1" });
/*   97 */     registerFeature("SVGEvents", new String[] { "1.0", "1.1" });
/*      */   }
/*      */   
/*      */   protected void initLocalizable() {
/*  101 */     this.localizableSupport = new LocalizableSupport("org.apache.batik.dom.svg.resources.Messages", getClass().getClassLoader());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CSSEngine createCSSEngine(AbstractStylableDocument doc, CSSContext ctx, ExtendedParser ep, ValueManager[] vms, ShorthandManager[] sms) {
/*  111 */     ParsedURL durl = ((SVGOMDocument)doc).getParsedURL();
/*  112 */     SVGCSSEngine sVGCSSEngine = new SVGCSSEngine((Document)doc, durl, ep, vms, sms, ctx);
/*      */     
/*  114 */     URL url = getClass().getResource("resources/UserAgentStyleSheet.css");
/*  115 */     if (url != null) {
/*  116 */       ParsedURL purl = new ParsedURL(url);
/*  117 */       InputSource is = new InputSource(purl.toString());
/*  118 */       sVGCSSEngine.setUserAgentStyleSheet(sVGCSSEngine.parseStyleSheet(is, purl, "all"));
/*      */     } 
/*      */ 
/*      */     
/*  122 */     return (CSSEngine)sVGCSSEngine;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ViewCSS createViewCSS(AbstractStylableDocument doc) {
/*  129 */     return (ViewCSS)new CSSOMSVGViewCSS(doc.getCSSEngine());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Document createDocument(String namespaceURI, String qualifiedName, DocumentType doctype) throws DOMException {
/*  140 */     SVGOMDocument sVGOMDocument = new SVGOMDocument(doctype, (DOMImplementation)this);
/*      */     
/*  142 */     if (qualifiedName != null) {
/*  143 */       sVGOMDocument.appendChild(sVGOMDocument.createElementNS(namespaceURI, qualifiedName));
/*      */     }
/*  145 */     return (Document)sVGOMDocument;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CSSStyleSheet createCSSStyleSheet(String title, String media) {
/*  155 */     throw new UnsupportedOperationException("DOMImplementationCSS.createCSSStyleSheet is not implemented");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CSSStyleDeclaration createCSSStyleDeclaration() {
/*  166 */     throw new UnsupportedOperationException("CSSStyleDeclarationFactory.createCSSStyleDeclaration is not implemented");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public StyleSheet createStyleSheet(Node n, HashMap<String, String> attrs) {
/*  177 */     throw new UnsupportedOperationException("StyleSheetFactory.createStyleSheet is not implemented");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CSSStyleSheet getUserAgentStyleSheet() {
/*  185 */     throw new UnsupportedOperationException("StyleSheetFactory.getUserAgentStyleSheet is not implemented");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Element createElementNS(AbstractDocument document, String namespaceURI, String qualifiedName) {
/*  196 */     if ("http://www.w3.org/2000/svg".equals(namespaceURI)) {
/*  197 */       String name = DOMUtilities.getLocalName(qualifiedName);
/*  198 */       ExtensibleDOMImplementation.ElementFactory ef = this.factories.get(name);
/*  199 */       if (ef != null) {
/*  200 */         return ef.create(DOMUtilities.getPrefix(qualifiedName), (Document)document);
/*      */       }
/*  202 */       throw document.createDOMException((short)8, "invalid.element", new Object[] { namespaceURI, qualifiedName });
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  207 */     return super.createElementNS(document, namespaceURI, qualifiedName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DocumentEventSupport createDocumentEventSupport() {
/*  215 */     DocumentEventSupport result = new DocumentEventSupport();
/*  216 */     result.registerEventFactory("SVGEvents", new DocumentEventSupport.EventFactory()
/*      */         {
/*      */           public Event createEvent() {
/*  219 */             return (Event)new SVGOMEvent();
/*      */           }
/*      */         });
/*  222 */     result.registerEventFactory("TimeEvent", new DocumentEventSupport.EventFactory()
/*      */         {
/*      */           public Event createEvent() {
/*  225 */             return (Event)new DOMTimeEvent();
/*      */           }
/*      */         });
/*  228 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  236 */   protected static HashMap<String, ExtensibleDOMImplementation.ElementFactory> svg11Factories = new HashMap<String, ExtensibleDOMImplementation.ElementFactory>();
/*      */   
/*      */   static {
/*  239 */     svg11Factories.put("a", new AElementFactory());
/*      */ 
/*      */     
/*  242 */     svg11Factories.put("altGlyph", new AltGlyphElementFactory());
/*      */ 
/*      */     
/*  245 */     svg11Factories.put("altGlyphDef", new AltGlyphDefElementFactory());
/*      */ 
/*      */     
/*  248 */     svg11Factories.put("altGlyphItem", new AltGlyphItemElementFactory());
/*      */ 
/*      */     
/*  251 */     svg11Factories.put("animate", new AnimateElementFactory());
/*      */ 
/*      */     
/*  254 */     svg11Factories.put("animateColor", new AnimateColorElementFactory());
/*      */ 
/*      */     
/*  257 */     svg11Factories.put("animateMotion", new AnimateMotionElementFactory());
/*      */ 
/*      */     
/*  260 */     svg11Factories.put("animateTransform", new AnimateTransformElementFactory());
/*      */ 
/*      */     
/*  263 */     svg11Factories.put("circle", new CircleElementFactory());
/*      */ 
/*      */     
/*  266 */     svg11Factories.put("clipPath", new ClipPathElementFactory());
/*      */ 
/*      */     
/*  269 */     svg11Factories.put("color-profile", new ColorProfileElementFactory());
/*      */ 
/*      */     
/*  272 */     svg11Factories.put("cursor", new CursorElementFactory());
/*      */ 
/*      */     
/*  275 */     svg11Factories.put("definition-src", new DefinitionSrcElementFactory());
/*      */ 
/*      */     
/*  278 */     svg11Factories.put("defs", new DefsElementFactory());
/*      */ 
/*      */     
/*  281 */     svg11Factories.put("desc", new DescElementFactory());
/*      */ 
/*      */     
/*  284 */     svg11Factories.put("ellipse", new EllipseElementFactory());
/*      */ 
/*      */     
/*  287 */     svg11Factories.put("feBlend", new FeBlendElementFactory());
/*      */ 
/*      */     
/*  290 */     svg11Factories.put("feColorMatrix", new FeColorMatrixElementFactory());
/*      */ 
/*      */     
/*  293 */     svg11Factories.put("feComponentTransfer", new FeComponentTransferElementFactory());
/*      */ 
/*      */     
/*  296 */     svg11Factories.put("feComposite", new FeCompositeElementFactory());
/*      */ 
/*      */     
/*  299 */     svg11Factories.put("feConvolveMatrix", new FeConvolveMatrixElementFactory());
/*      */ 
/*      */     
/*  302 */     svg11Factories.put("feDiffuseLighting", new FeDiffuseLightingElementFactory());
/*      */ 
/*      */     
/*  305 */     svg11Factories.put("feDisplacementMap", new FeDisplacementMapElementFactory());
/*      */ 
/*      */     
/*  308 */     svg11Factories.put("feDistantLight", new FeDistantLightElementFactory());
/*      */ 
/*      */     
/*  311 */     svg11Factories.put("feFlood", new FeFloodElementFactory());
/*      */ 
/*      */     
/*  314 */     svg11Factories.put("feFuncA", new FeFuncAElementFactory());
/*      */ 
/*      */     
/*  317 */     svg11Factories.put("feFuncR", new FeFuncRElementFactory());
/*      */ 
/*      */     
/*  320 */     svg11Factories.put("feFuncG", new FeFuncGElementFactory());
/*      */ 
/*      */     
/*  323 */     svg11Factories.put("feFuncB", new FeFuncBElementFactory());
/*      */ 
/*      */     
/*  326 */     svg11Factories.put("feGaussianBlur", new FeGaussianBlurElementFactory());
/*      */ 
/*      */     
/*  329 */     svg11Factories.put("feImage", new FeImageElementFactory());
/*      */ 
/*      */     
/*  332 */     svg11Factories.put("feMerge", new FeMergeElementFactory());
/*      */ 
/*      */     
/*  335 */     svg11Factories.put("feMergeNode", new FeMergeNodeElementFactory());
/*      */ 
/*      */     
/*  338 */     svg11Factories.put("feMorphology", new FeMorphologyElementFactory());
/*      */ 
/*      */     
/*  341 */     svg11Factories.put("feOffset", new FeOffsetElementFactory());
/*      */ 
/*      */     
/*  344 */     svg11Factories.put("fePointLight", new FePointLightElementFactory());
/*      */ 
/*      */     
/*  347 */     svg11Factories.put("feSpecularLighting", new FeSpecularLightingElementFactory());
/*      */ 
/*      */     
/*  350 */     svg11Factories.put("feSpotLight", new FeSpotLightElementFactory());
/*      */ 
/*      */     
/*  353 */     svg11Factories.put("feTile", new FeTileElementFactory());
/*      */ 
/*      */     
/*  356 */     svg11Factories.put("feTurbulence", new FeTurbulenceElementFactory());
/*      */ 
/*      */     
/*  359 */     svg11Factories.put("filter", new FilterElementFactory());
/*      */ 
/*      */     
/*  362 */     svg11Factories.put("font", new FontElementFactory());
/*      */ 
/*      */     
/*  365 */     svg11Factories.put("font-face", new FontFaceElementFactory());
/*      */ 
/*      */     
/*  368 */     svg11Factories.put("font-face-format", new FontFaceFormatElementFactory());
/*      */ 
/*      */     
/*  371 */     svg11Factories.put("font-face-name", new FontFaceNameElementFactory());
/*      */ 
/*      */     
/*  374 */     svg11Factories.put("font-face-src", new FontFaceSrcElementFactory());
/*      */ 
/*      */     
/*  377 */     svg11Factories.put("font-face-uri", new FontFaceUriElementFactory());
/*      */ 
/*      */     
/*  380 */     svg11Factories.put("foreignObject", new ForeignObjectElementFactory());
/*      */ 
/*      */     
/*  383 */     svg11Factories.put("g", new GElementFactory());
/*      */ 
/*      */     
/*  386 */     svg11Factories.put("glyph", new GlyphElementFactory());
/*      */ 
/*      */     
/*  389 */     svg11Factories.put("glyphRef", new GlyphRefElementFactory());
/*      */ 
/*      */     
/*  392 */     svg11Factories.put("hkern", new HkernElementFactory());
/*      */ 
/*      */     
/*  395 */     svg11Factories.put("image", new ImageElementFactory());
/*      */ 
/*      */     
/*  398 */     svg11Factories.put("line", new LineElementFactory());
/*      */ 
/*      */     
/*  401 */     svg11Factories.put("linearGradient", new LinearGradientElementFactory());
/*      */ 
/*      */     
/*  404 */     svg11Factories.put("marker", new MarkerElementFactory());
/*      */ 
/*      */     
/*  407 */     svg11Factories.put("mask", new MaskElementFactory());
/*      */ 
/*      */     
/*  410 */     svg11Factories.put("metadata", new MetadataElementFactory());
/*      */ 
/*      */     
/*  413 */     svg11Factories.put("missing-glyph", new MissingGlyphElementFactory());
/*      */ 
/*      */     
/*  416 */     svg11Factories.put("mpath", new MpathElementFactory());
/*      */ 
/*      */     
/*  419 */     svg11Factories.put("path", new PathElementFactory());
/*      */ 
/*      */     
/*  422 */     svg11Factories.put("pattern", new PatternElementFactory());
/*      */ 
/*      */     
/*  425 */     svg11Factories.put("polygon", new PolygonElementFactory());
/*      */ 
/*      */     
/*  428 */     svg11Factories.put("polyline", new PolylineElementFactory());
/*      */ 
/*      */     
/*  431 */     svg11Factories.put("radialGradient", new RadialGradientElementFactory());
/*      */ 
/*      */     
/*  434 */     svg11Factories.put("rect", new RectElementFactory());
/*      */ 
/*      */     
/*  437 */     svg11Factories.put("set", new SetElementFactory());
/*      */ 
/*      */     
/*  440 */     svg11Factories.put("script", new ScriptElementFactory());
/*      */ 
/*      */     
/*  443 */     svg11Factories.put("stop", new StopElementFactory());
/*      */ 
/*      */     
/*  446 */     svg11Factories.put("style", new StyleElementFactory());
/*      */ 
/*      */     
/*  449 */     svg11Factories.put("svg", new SvgElementFactory());
/*      */ 
/*      */     
/*  452 */     svg11Factories.put("switch", new SwitchElementFactory());
/*      */ 
/*      */     
/*  455 */     svg11Factories.put("symbol", new SymbolElementFactory());
/*      */ 
/*      */     
/*  458 */     svg11Factories.put("text", new TextElementFactory());
/*      */ 
/*      */     
/*  461 */     svg11Factories.put("textPath", new TextPathElementFactory());
/*      */ 
/*      */     
/*  464 */     svg11Factories.put("title", new TitleElementFactory());
/*      */ 
/*      */     
/*  467 */     svg11Factories.put("tref", new TrefElementFactory());
/*      */ 
/*      */     
/*  470 */     svg11Factories.put("tspan", new TspanElementFactory());
/*      */ 
/*      */     
/*  473 */     svg11Factories.put("use", new UseElementFactory());
/*      */ 
/*      */     
/*  476 */     svg11Factories.put("view", new ViewElementFactory());
/*      */ 
/*      */     
/*  479 */     svg11Factories.put("vkern", new VkernElementFactory());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class AElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  492 */       return (Element)new SVGOMAElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class AltGlyphElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  505 */       return (Element)new SVGOMAltGlyphElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class AltGlyphDefElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  519 */       return (Element)new SVGOMAltGlyphDefElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class AltGlyphItemElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  533 */       return (Element)new SVGOMAltGlyphItemElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class AnimateElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  546 */       return (Element)new SVGOMAnimateElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class AnimateColorElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  560 */       return (Element)new SVGOMAnimateColorElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class AnimateMotionElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  574 */       return (Element)new SVGOMAnimateMotionElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class AnimateTransformElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  589 */       return (Element)new SVGOMAnimateTransformElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class CircleElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  603 */       return (Element)new SVGOMCircleElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class ClipPathElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  616 */       return (Element)new SVGOMClipPathElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class ColorProfileElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  630 */       return (Element)new SVGOMColorProfileElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class CursorElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  643 */       return (Element)new SVGOMCursorElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class DefinitionSrcElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  657 */       return (Element)new SVGOMDefinitionSrcElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class DefsElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  671 */       return (Element)new SVGOMDefsElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class DescElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  684 */       return (Element)new SVGOMDescElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class EllipseElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  697 */       return (Element)new SVGOMEllipseElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FeBlendElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  710 */       return (Element)new SVGOMFEBlendElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FeColorMatrixElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  724 */       return (Element)new SVGOMFEColorMatrixElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FeComponentTransferElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  739 */       return (Element)new SVGOMFEComponentTransferElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FeCompositeElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  754 */       return (Element)new SVGOMFECompositeElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FeConvolveMatrixElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  768 */       return (Element)new SVGOMFEConvolveMatrixElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FeDiffuseLightingElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  783 */       return (Element)new SVGOMFEDiffuseLightingElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FeDisplacementMapElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  798 */       return (Element)new SVGOMFEDisplacementMapElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FeDistantLightElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  813 */       return (Element)new SVGOMFEDistantLightElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FeFloodElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  827 */       return (Element)new SVGOMFEFloodElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FeFuncAElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  840 */       return (Element)new SVGOMFEFuncAElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FeFuncRElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  853 */       return (Element)new SVGOMFEFuncRElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FeFuncGElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  866 */       return (Element)new SVGOMFEFuncGElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FeFuncBElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  881 */       return (Element)new SVGOMFEFuncBElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FeGaussianBlurElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  895 */       return (Element)new SVGOMFEGaussianBlurElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FeImageElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  909 */       return (Element)new SVGOMFEImageElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FeMergeElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  923 */       return (Element)new SVGOMFEMergeElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FeMergeNodeElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  937 */       return (Element)new SVGOMFEMergeNodeElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FeMorphologyElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  951 */       return (Element)new SVGOMFEMorphologyElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FeOffsetElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  965 */       return (Element)new SVGOMFEOffsetElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FePointLightElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  979 */       return (Element)new SVGOMFEPointLightElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FeSpecularLightingElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/*  993 */       return (Element)new SVGOMFESpecularLightingElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FeSpotLightElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1008 */       return (Element)new SVGOMFESpotLightElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FeTileElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1021 */       return (Element)new SVGOMFETileElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FeTurbulenceElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1035 */       return (Element)new SVGOMFETurbulenceElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FilterElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1048 */       return (Element)new SVGOMFilterElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FontElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1061 */       return (Element)new SVGOMFontElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FontFaceElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1074 */       return (Element)new SVGOMFontFaceElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FontFaceFormatElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1088 */       return (Element)new SVGOMFontFaceFormatElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FontFaceNameElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1103 */       return (Element)new SVGOMFontFaceNameElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FontFaceSrcElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1117 */       return (Element)new SVGOMFontFaceSrcElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class FontFaceUriElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1131 */       return (Element)new SVGOMFontFaceUriElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class ForeignObjectElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1145 */       return (Element)new SVGOMForeignObjectElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class GElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1159 */       return (Element)new SVGOMGElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class GlyphElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1172 */       return (Element)new SVGOMGlyphElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class GlyphRefElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1185 */       return (Element)new SVGOMGlyphRefElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class HkernElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1198 */       return (Element)new SVGOMHKernElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class ImageElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1211 */       return (Element)new SVGOMImageElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class LineElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1224 */       return (Element)new SVGOMLineElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class LinearGradientElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1238 */       return (Element)new SVGOMLinearGradientElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class MarkerElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1252 */       return (Element)new SVGOMMarkerElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class MaskElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1265 */       return (Element)new SVGOMMaskElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class MetadataElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1278 */       return (Element)new SVGOMMetadataElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class MissingGlyphElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1292 */       return (Element)new SVGOMMissingGlyphElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class MpathElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1305 */       return (Element)new SVGOMMPathElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class PathElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1318 */       return (Element)new SVGOMPathElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class PatternElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1331 */       return (Element)new SVGOMPatternElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class PolygonElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1344 */       return (Element)new SVGOMPolygonElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class PolylineElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1357 */       return (Element)new SVGOMPolylineElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class RadialGradientElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1371 */       return (Element)new SVGOMRadialGradientElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class RectElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1385 */       return (Element)new SVGOMRectElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class ScriptElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1398 */       return (Element)new SVGOMScriptElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class SetElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1411 */       return (Element)new SVGOMSetElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class StopElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1424 */       return (Element)new SVGOMStopElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class StyleElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1437 */       return (Element)new SVGOMStyleElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class SvgElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1450 */       return (Element)new SVGOMSVGElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class SwitchElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1463 */       return (Element)new SVGOMSwitchElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class SymbolElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1476 */       return (Element)new SVGOMSymbolElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class TextElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1489 */       return (Element)new SVGOMTextElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class TextPathElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1502 */       return (Element)new SVGOMTextPathElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class TitleElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1515 */       return (Element)new SVGOMTitleElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class TrefElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1528 */       return (Element)new SVGOMTRefElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class TspanElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1541 */       return (Element)new SVGOMTSpanElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class UseElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1554 */       return (Element)new SVGOMUseElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class ViewElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1567 */       return (Element)new SVGOMViewElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static class VkernElementFactory
/*      */     implements ExtensibleDOMImplementation.ElementFactory
/*      */   {
/*      */     public Element create(String prefix, Document doc) {
/* 1580 */       return (Element)new SVGOMVKernElement(prefix, (AbstractDocument)doc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1587 */   protected static final DOMImplementation DOM_IMPLEMENTATION = (DOMImplementation)new SVGDOMImplementation();
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGDOMImplementation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */