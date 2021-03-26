/*     */ package org.apache.batik.extension;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSStylableElement;
/*     */ import org.apache.batik.css.engine.StyleDeclarationProvider;
/*     */ import org.apache.batik.css.engine.StyleMap;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.util.ParsedURL;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.css.CSSStyleDeclaration;
/*     */ import org.w3c.dom.css.CSSValue;
/*     */ import org.w3c.dom.svg.SVGAnimatedString;
/*     */ import org.w3c.dom.svg.SVGStylable;
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
/*     */ public abstract class StylableExtensionElement
/*     */   extends ExtensionElement
/*     */   implements CSSStylableElement, SVGStylable
/*     */ {
/*     */   protected ParsedURL cssBase;
/*     */   protected StyleMap computedStyleMap;
/*     */   
/*     */   protected StylableExtensionElement() {}
/*     */   
/*     */   protected StylableExtensionElement(String name, AbstractDocument owner) {
/*  69 */     super(name, owner);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StyleMap getComputedStyleMap(String pseudoElement) {
/*  78 */     return this.computedStyleMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setComputedStyleMap(String pseudoElement, StyleMap sm) {
/*  85 */     this.computedStyleMap = sm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getXMLId() {
/*  92 */     return getAttributeNS(null, "id");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCSSClass() {
/*  99 */     return getAttributeNS(null, "class");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ParsedURL getCSSBase() {
/* 106 */     if (this.cssBase == null) {
/* 107 */       String bu = getBaseURI();
/* 108 */       if (bu == null) {
/* 109 */         return null;
/*     */       }
/* 111 */       this.cssBase = new ParsedURL(bu);
/*     */     } 
/* 113 */     return this.cssBase;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPseudoInstanceOf(String pseudoClass) {
/* 121 */     if (pseudoClass.equals("first-child")) {
/* 122 */       Node n = getPreviousSibling();
/* 123 */       while (n != null && n.getNodeType() != 1) {
/* 124 */         n = n.getPreviousSibling();
/*     */       }
/* 126 */       return (n == null);
/*     */     } 
/* 128 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StyleDeclarationProvider getOverrideStyleDeclarationProvider() {
/* 137 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CSSStyleDeclaration getStyle() {
/* 146 */     throw new UnsupportedOperationException("Not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CSSValue getPresentationAttribute(String name) {
/* 154 */     throw new UnsupportedOperationException("Not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedString getClassName() {
/* 162 */     throw new UnsupportedOperationException("Not implemented");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/extension/StylableExtensionElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */