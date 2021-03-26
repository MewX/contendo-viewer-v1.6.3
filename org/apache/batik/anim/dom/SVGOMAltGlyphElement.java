/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAltGlyphElement;
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
/*     */ public class SVGOMAltGlyphElement
/*     */   extends SVGURIReferenceTextPositioningElement
/*     */   implements SVGAltGlyphElement
/*     */ {
/*  44 */   protected static final AttributeInitializer attributeInitializer = new AttributeInitializer(4); static {
/*  45 */     attributeInitializer.addAttribute("http://www.w3.org/2000/xmlns/", null, "xmlns:xlink", "http://www.w3.org/1999/xlink");
/*     */ 
/*     */     
/*  48 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "type", "simple");
/*     */     
/*  50 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "show", "other");
/*     */     
/*  52 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "actuate", "onLoad");
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
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAltGlyphElement() {}
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
/*     */   public SVGOMAltGlyphElement(String prefix, AbstractDocument owner) {
/*  82 */     super(prefix, owner);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/*  89 */     return "altGlyph";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getGlyphRef() {
/*  96 */     return getAttributeNS(null, "glyphRef");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGlyphRef(String glyphRef) throws DOMException {
/* 103 */     setAttributeNS(null, "glyphRef", glyphRef);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFormat() {
/* 110 */     return getAttributeNS(null, "format");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFormat(String format) throws DOMException {
/* 117 */     setAttributeNS(null, "format", format);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AttributeInitializer getAttributeInitializer() {
/* 125 */     return attributeInitializer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 132 */     return (Node)new SVGOMAltGlyphElement();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMAltGlyphElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */