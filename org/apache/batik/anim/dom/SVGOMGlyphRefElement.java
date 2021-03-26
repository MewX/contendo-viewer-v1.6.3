/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedString;
/*     */ import org.w3c.dom.svg.SVGGlyphRefElement;
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
/*     */ public class SVGOMGlyphRefElement
/*     */   extends SVGStylableElement
/*     */   implements SVGGlyphRefElement
/*     */ {
/*  68 */   protected static final AttributeInitializer attributeInitializer = new AttributeInitializer(4); static {
/*  69 */     attributeInitializer.addAttribute("http://www.w3.org/2000/xmlns/", null, "xmlns:xlink", "http://www.w3.org/1999/xlink");
/*     */ 
/*     */     
/*  72 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "type", "simple");
/*     */     
/*  74 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "show", "other");
/*     */     
/*  76 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "actuate", "onLoad");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedString href;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMGlyphRefElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGOMGlyphRefElement(String prefix, AbstractDocument owner) {
/*  98 */     super(prefix, owner);
/*  99 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/* 106 */     super.initializeAllLiveAttributes();
/* 107 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 114 */     this.href = createLiveAnimatedString("http://www.w3.org/1999/xlink", "href");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 122 */     return "glyphRef";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedString getHref() {
/* 129 */     return this.href;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getGlyphRef() {
/* 136 */     return getAttributeNS(null, "glyphRef");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGlyphRef(String glyphRef) throws DOMException {
/* 143 */     setAttributeNS(null, "glyphRef", glyphRef);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFormat() {
/* 150 */     return getAttributeNS(null, "format");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFormat(String format) throws DOMException {
/* 157 */     setAttributeNS(null, "format", format);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getX() {
/* 164 */     return Float.parseFloat(getAttributeNS(null, "x"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setX(float x) throws DOMException {
/* 171 */     setAttributeNS(null, "x", String.valueOf(x));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getY() {
/* 178 */     return Float.parseFloat(getAttributeNS(null, "y"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setY(float y) throws DOMException {
/* 185 */     setAttributeNS(null, "y", String.valueOf(y));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getDx() {
/* 192 */     return Float.parseFloat(getAttributeNS(null, "dx"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDx(float dx) throws DOMException {
/* 199 */     setAttributeNS(null, "dx", String.valueOf(dx));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getDy() {
/* 206 */     return Float.parseFloat(getAttributeNS(null, "dy"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDy(float dy) throws DOMException {
/* 213 */     setAttributeNS(null, "dy", String.valueOf(dy));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AttributeInitializer getAttributeInitializer() {
/* 221 */     return attributeInitializer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 228 */     return (Node)new SVGOMGlyphRefElement();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMGlyphRefElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */