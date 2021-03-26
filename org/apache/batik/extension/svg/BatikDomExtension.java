/*     */ package org.apache.batik.extension.svg;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.dom.DomExtension;
/*     */ import org.apache.batik.dom.ExtensibleDOMImplementation;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
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
/*     */ public class BatikDomExtension
/*     */   implements DomExtension, BatikExtConstants
/*     */ {
/*     */   public float getPriority() {
/*  45 */     return 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAuthor() {
/*  52 */     return "Thomas DeWeese";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContactAddress() {
/*  59 */     return "deweese@apache.org";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getURL() {
/*  67 */     return "http://xml.apache.org/batik";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  76 */     return "Example extension to standard SVG shape tags";
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
/*     */   public void registerTags(ExtensibleDOMImplementation di) {
/*  89 */     di.registerCustomElementFactory("http://xml.apache.org/batik/ext", "regularPolygon", new BatikRegularPolygonElementFactory());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  94 */     di.registerCustomElementFactory("http://xml.apache.org/batik/ext", "star", new BatikStarElementFactory());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  99 */     di.registerCustomElementFactory("http://xml.apache.org/batik/ext", "histogramNormalization", new BatikHistogramNormalizationElementFactory());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     di.registerCustomElementFactory("http://xml.apache.org/batik/ext", "colorSwitch", new ColorSwitchElementFactory());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 109 */     di.registerCustomElementFactory("http://xml.apache.org/batik/ext", "flowText", new FlowTextElementFactory());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 114 */     di.registerCustomElementFactory("http://xml.apache.org/batik/ext", "flowDiv", new FlowDivElementFactory());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 119 */     di.registerCustomElementFactory("http://xml.apache.org/batik/ext", "flowPara", new FlowParaElementFactory());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 124 */     di.registerCustomElementFactory("http://xml.apache.org/batik/ext", "flowRegionBreak", new FlowRegionBreakElementFactory());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     di.registerCustomElementFactory("http://xml.apache.org/batik/ext", "flowRegion", new FlowRegionElementFactory());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 134 */     di.registerCustomElementFactory("http://xml.apache.org/batik/ext", "flowLine", new FlowLineElementFactory());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 139 */     di.registerCustomElementFactory("http://xml.apache.org/batik/ext", "flowSpan", new FlowSpanElementFactory());
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
/*     */   protected static class BatikRegularPolygonElementFactory
/*     */     implements ExtensibleDOMImplementation.ElementFactory
/*     */   {
/*     */     public Element create(String prefix, Document doc) {
/* 155 */       return (Element)new BatikRegularPolygonElement(prefix, (AbstractDocument)doc);
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
/*     */   protected static class BatikStarElementFactory
/*     */     implements ExtensibleDOMImplementation.ElementFactory
/*     */   {
/*     */     public Element create(String prefix, Document doc) {
/* 171 */       return (Element)new BatikStarElement(prefix, (AbstractDocument)doc);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class BatikHistogramNormalizationElementFactory
/*     */     implements ExtensibleDOMImplementation.ElementFactory
/*     */   {
/*     */     public Element create(String prefix, Document doc) {
/* 185 */       return (Element)new BatikHistogramNormalizationElement(prefix, (AbstractDocument)doc);
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
/*     */   protected static class ColorSwitchElementFactory
/*     */     implements ExtensibleDOMImplementation.ElementFactory
/*     */   {
/*     */     public Element create(String prefix, Document doc) {
/* 201 */       return (Element)new ColorSwitchElement(prefix, (AbstractDocument)doc);
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
/*     */   protected static class FlowTextElementFactory
/*     */     implements ExtensibleDOMImplementation.ElementFactory
/*     */   {
/*     */     public Element create(String prefix, Document doc) {
/* 216 */       return (Element)new FlowTextElement(prefix, (AbstractDocument)doc);
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
/*     */   protected static class FlowDivElementFactory
/*     */     implements ExtensibleDOMImplementation.ElementFactory
/*     */   {
/*     */     public Element create(String prefix, Document doc) {
/* 231 */       return (Element)new FlowDivElement(prefix, (AbstractDocument)doc);
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
/* 246 */       return (Element)new FlowParaElement(prefix, (AbstractDocument)doc);
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
/* 261 */       return (Element)new FlowRegionBreakElement(prefix, (AbstractDocument)doc);
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
/* 276 */       return (Element)new FlowRegionElement(prefix, (AbstractDocument)doc);
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
/* 291 */       return (Element)new FlowLineElement(prefix, (AbstractDocument)doc);
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
/* 306 */       return (Element)new FlowSpanElement(prefix, (AbstractDocument)doc);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/extension/svg/BatikDomExtension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */