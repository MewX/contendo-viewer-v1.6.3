/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import java.net.URL;
/*     */ import java.util.HashMap;
/*     */ import org.apache.batik.css.engine.CSSContext;
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.SVG12CSSEngine;
/*     */ import org.apache.batik.css.engine.value.ShorthandManager;
/*     */ import org.apache.batik.css.engine.value.ValueManager;
/*     */ import org.apache.batik.css.parser.ExtendedParser;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.dom.AbstractNode;
/*     */ import org.apache.batik.dom.AbstractStylableDocument;
/*     */ import org.apache.batik.dom.ExtensibleDOMImplementation;
/*     */ import org.apache.batik.dom.GenericElement;
/*     */ import org.apache.batik.dom.events.DocumentEventSupport;
/*     */ import org.apache.batik.dom.events.EventSupport;
/*     */ import org.apache.batik.dom.svg12.SVGOMWheelEvent;
/*     */ import org.apache.batik.dom.svg12.XBLOMShadowTreeEvent;
/*     */ import org.apache.batik.dom.util.DOMUtilities;
/*     */ import org.apache.batik.util.ParsedURL;
/*     */ import org.w3c.css.sac.InputSource;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.DOMImplementation;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.DocumentType;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.events.Event;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVG12DOMImplementation
/*     */   extends SVGDOMImplementation
/*     */ {
/*     */   public SVG12DOMImplementation() {
/*  66 */     this.factories = svg12Factories;
/*  67 */     registerFeature("CSS", "2.0");
/*  68 */     registerFeature("StyleSheets", "2.0");
/*  69 */     registerFeature("SVG", new String[] { "1.0", "1.1", "1.2" });
/*  70 */     registerFeature("SVGEvents", new String[] { "1.0", "1.1", "1.2" });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CSSEngine createCSSEngine(AbstractStylableDocument doc, CSSContext ctx, ExtendedParser ep, ValueManager[] vms, ShorthandManager[] sms) {
/*  78 */     ParsedURL durl = ((SVGOMDocument)doc).getParsedURL();
/*  79 */     SVG12CSSEngine sVG12CSSEngine = new SVG12CSSEngine((Document)doc, durl, ep, vms, sms, ctx);
/*     */     
/*  81 */     URL url = getClass().getResource("resources/UserAgentStyleSheet.css");
/*  82 */     if (url != null) {
/*  83 */       ParsedURL purl = new ParsedURL(url);
/*  84 */       InputSource is = new InputSource(purl.toString());
/*  85 */       sVG12CSSEngine.setUserAgentStyleSheet(sVG12CSSEngine.parseStyleSheet(is, purl, "all"));
/*     */     } 
/*     */ 
/*     */     
/*  89 */     return (CSSEngine)sVG12CSSEngine;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Document createDocument(String namespaceURI, String qualifiedName, DocumentType doctype) throws DOMException {
/* 100 */     SVGOMDocument result = new SVG12OMDocument(doctype, (DOMImplementation)this);
/* 101 */     result.setIsSVG12(true);
/*     */     
/* 103 */     if (qualifiedName != null) {
/* 104 */       result.appendChild(result.createElementNS(namespaceURI, qualifiedName));
/*     */     }
/* 106 */     return (Document)result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Element createElementNS(AbstractDocument document, String namespaceURI, String qualifiedName) {
/* 116 */     if (namespaceURI == null) {
/* 117 */       return (Element)new GenericElement(qualifiedName.intern(), document);
/*     */     }
/* 119 */     String name = DOMUtilities.getLocalName(qualifiedName);
/* 120 */     String prefix = DOMUtilities.getPrefix(qualifiedName);
/* 121 */     if ("http://www.w3.org/2000/svg".equals(namespaceURI)) {
/* 122 */       ExtensibleDOMImplementation.ElementFactory ef = this.factories.get(name);
/* 123 */       if (ef != null) {
/* 124 */         return ef.create(prefix, (Document)document);
/*     */       }
/* 126 */     } else if ("http://www.w3.org/2004/xbl".equals(namespaceURI)) {
/* 127 */       ExtensibleDOMImplementation.ElementFactory ef = xblFactories.get(name);
/* 128 */       if (ef != null) {
/* 129 */         return ef.create(prefix, (Document)document);
/*     */       }
/*     */     } 
/*     */     
/* 133 */     if (this.customFactories != null) {
/*     */       
/* 135 */       ExtensibleDOMImplementation.ElementFactory cef = (ExtensibleDOMImplementation.ElementFactory)this.customFactories.get(namespaceURI, name);
/* 136 */       if (cef != null) {
/* 137 */         return cef.create(prefix, (Document)document);
/*     */       }
/*     */     } 
/*     */     
/* 141 */     return (Element)new BindableElement(prefix, document, namespaceURI, name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DocumentEventSupport createDocumentEventSupport() {
/* 149 */     DocumentEventSupport result = super.createDocumentEventSupport();
/* 150 */     result.registerEventFactory("WheelEvent", new DocumentEventSupport.EventFactory()
/*     */         {
/*     */           public Event createEvent() {
/* 153 */             return (Event)new SVGOMWheelEvent();
/*     */           }
/*     */         });
/* 156 */     result.registerEventFactory("ShadowTreeEvent", new DocumentEventSupport.EventFactory()
/*     */         {
/*     */           public Event createEvent() {
/* 159 */             return (Event)new XBLOMShadowTreeEvent();
/*     */           }
/*     */         });
/* 162 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EventSupport createEventSupport(AbstractNode n) {
/* 169 */     return new XBLEventSupport(n);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 177 */   protected static HashMap<String, ExtensibleDOMImplementation.ElementFactory> svg12Factories = new HashMap<String, ExtensibleDOMImplementation.ElementFactory>(svg11Factories);
/*     */   
/*     */   static {
/* 180 */     svg12Factories.put("flowDiv", new FlowDivElementFactory());
/*     */ 
/*     */     
/* 183 */     svg12Factories.put("flowLine", new FlowLineElementFactory());
/*     */ 
/*     */     
/* 186 */     svg12Factories.put("flowPara", new FlowParaElementFactory());
/*     */ 
/*     */     
/* 189 */     svg12Factories.put("flowRegionBreak", new FlowRegionBreakElementFactory());
/*     */ 
/*     */     
/* 192 */     svg12Factories.put("flowRegion", new FlowRegionElementFactory());
/*     */ 
/*     */     
/* 195 */     svg12Factories.put("flowRegionExclude", new FlowRegionExcludeElementFactory());
/*     */ 
/*     */     
/* 198 */     svg12Factories.put("flowRoot", new FlowRootElementFactory());
/*     */ 
/*     */     
/* 201 */     svg12Factories.put("flowSpan", new FlowSpanElementFactory());
/*     */ 
/*     */     
/* 204 */     svg12Factories.put("handler", new HandlerElementFactory());
/*     */ 
/*     */     
/* 207 */     svg12Factories.put("multiImage", new MultiImageElementFactory());
/*     */ 
/*     */     
/* 210 */     svg12Factories.put("solidColor", new SolidColorElementFactory());
/*     */ 
/*     */     
/* 213 */     svg12Factories.put("subImage", new SubImageElementFactory());
/*     */ 
/*     */     
/* 216 */     svg12Factories.put("subImageRef", new SubImageRefElementFactory());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class FlowDivElementFactory
/*     */     implements ExtensibleDOMImplementation.ElementFactory
/*     */   {
/*     */     public Element create(String prefix, Document doc) {
/* 232 */       return (Element)new SVGOMFlowDivElement(prefix, (AbstractDocument)doc);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class FlowLineElementFactory
/*     */     implements ExtensibleDOMImplementation.ElementFactory
/*     */   {
/*     */     public Element create(String prefix, Document doc) {
/* 247 */       return (Element)new SVGOMFlowLineElement(prefix, (AbstractDocument)doc);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class FlowParaElementFactory
/*     */     implements ExtensibleDOMImplementation.ElementFactory
/*     */   {
/*     */     public Element create(String prefix, Document doc) {
/* 262 */       return (Element)new SVGOMFlowParaElement(prefix, (AbstractDocument)doc);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class FlowRegionBreakElementFactory
/*     */     implements ExtensibleDOMImplementation.ElementFactory
/*     */   {
/*     */     public Element create(String prefix, Document doc) {
/* 277 */       return (Element)new SVGOMFlowRegionBreakElement(prefix, (AbstractDocument)doc);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class FlowRegionElementFactory
/*     */     implements ExtensibleDOMImplementation.ElementFactory
/*     */   {
/*     */     public Element create(String prefix, Document doc) {
/* 292 */       return (Element)new SVGOMFlowRegionElement(prefix, (AbstractDocument)doc);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class FlowRegionExcludeElementFactory
/*     */     implements ExtensibleDOMImplementation.ElementFactory
/*     */   {
/*     */     public Element create(String prefix, Document doc) {
/* 307 */       return (Element)new SVGOMFlowRegionExcludeElement(prefix, (AbstractDocument)doc);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class FlowRootElementFactory
/*     */     implements ExtensibleDOMImplementation.ElementFactory
/*     */   {
/*     */     public Element create(String prefix, Document doc) {
/* 322 */       return (Element)new SVGOMFlowRootElement(prefix, (AbstractDocument)doc);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class FlowSpanElementFactory
/*     */     implements ExtensibleDOMImplementation.ElementFactory
/*     */   {
/*     */     public Element create(String prefix, Document doc) {
/* 337 */       return (Element)new SVGOMFlowSpanElement(prefix, (AbstractDocument)doc);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class HandlerElementFactory
/*     */     implements ExtensibleDOMImplementation.ElementFactory
/*     */   {
/*     */     public Element create(String prefix, Document doc) {
/* 352 */       return (Element)new SVGOMHandlerElement(prefix, (AbstractDocument)doc);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class MultiImageElementFactory
/*     */     implements ExtensibleDOMImplementation.ElementFactory
/*     */   {
/*     */     public Element create(String prefix, Document doc) {
/* 366 */       return (Element)new SVGOMMultiImageElement(prefix, (AbstractDocument)doc);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class SolidColorElementFactory
/*     */     implements ExtensibleDOMImplementation.ElementFactory
/*     */   {
/*     */     public Element create(String prefix, Document doc) {
/* 382 */       return (Element)new SVGOMSolidColorElement(prefix, (AbstractDocument)doc);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class SubImageElementFactory
/*     */     implements ExtensibleDOMImplementation.ElementFactory
/*     */   {
/*     */     public Element create(String prefix, Document doc) {
/* 396 */       return (Element)new SVGOMSubImageElement(prefix, (AbstractDocument)doc);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class SubImageRefElementFactory
/*     */     implements ExtensibleDOMImplementation.ElementFactory
/*     */   {
/*     */     public Element create(String prefix, Document doc) {
/* 410 */       return (Element)new SVGOMSubImageRefElement(prefix, (AbstractDocument)doc);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 417 */   protected static HashMap<String, ExtensibleDOMImplementation.ElementFactory> xblFactories = new HashMap<String, ExtensibleDOMImplementation.ElementFactory>();
/*     */   
/*     */   static {
/* 420 */     xblFactories.put("xbl", new XBLXBLElementFactory());
/*     */ 
/*     */     
/* 423 */     xblFactories.put("definition", new XBLDefinitionElementFactory());
/*     */ 
/*     */     
/* 426 */     xblFactories.put("template", new XBLTemplateElementFactory());
/*     */ 
/*     */     
/* 429 */     xblFactories.put("content", new XBLContentElementFactory());
/*     */ 
/*     */     
/* 432 */     xblFactories.put("handlerGroup", new XBLHandlerGroupElementFactory());
/*     */ 
/*     */     
/* 435 */     xblFactories.put("import", new XBLImportElementFactory());
/*     */ 
/*     */     
/* 438 */     xblFactories.put("shadowTree", new XBLShadowTreeElementFactory());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class XBLXBLElementFactory
/*     */     implements ExtensibleDOMImplementation.ElementFactory
/*     */   {
/*     */     public Element create(String prefix, Document doc) {
/* 451 */       return (Element)new XBLOMXBLElement(prefix, (AbstractDocument)doc);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class XBLDefinitionElementFactory
/*     */     implements ExtensibleDOMImplementation.ElementFactory
/*     */   {
/*     */     public Element create(String prefix, Document doc) {
/* 465 */       return (Element)new XBLOMDefinitionElement(prefix, (AbstractDocument)doc);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class XBLTemplateElementFactory
/*     */     implements ExtensibleDOMImplementation.ElementFactory
/*     */   {
/*     */     public Element create(String prefix, Document doc) {
/* 479 */       return (Element)new XBLOMTemplateElement(prefix, (AbstractDocument)doc);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class XBLContentElementFactory
/*     */     implements ExtensibleDOMImplementation.ElementFactory
/*     */   {
/*     */     public Element create(String prefix, Document doc) {
/* 493 */       return (Element)new XBLOMContentElement(prefix, (AbstractDocument)doc);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class XBLHandlerGroupElementFactory
/*     */     implements ExtensibleDOMImplementation.ElementFactory
/*     */   {
/*     */     public Element create(String prefix, Document doc) {
/* 507 */       return (Element)new XBLOMHandlerGroupElement(prefix, (AbstractDocument)doc);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class XBLImportElementFactory
/*     */     implements ExtensibleDOMImplementation.ElementFactory
/*     */   {
/*     */     public Element create(String prefix, Document doc) {
/* 521 */       return (Element)new XBLOMImportElement(prefix, (AbstractDocument)doc);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class XBLShadowTreeElementFactory
/*     */     implements ExtensibleDOMImplementation.ElementFactory
/*     */   {
/*     */     public Element create(String prefix, Document doc) {
/* 535 */       return (Element)new XBLOMShadowTreeElement(prefix, (AbstractDocument)doc);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 542 */   protected static final DOMImplementation DOM_IMPLEMENTATION = (DOMImplementation)new SVG12DOMImplementation();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DOMImplementation getDOMImplementation() {
/* 549 */     return DOM_IMPLEMENTATION;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVG12DOMImplementation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */