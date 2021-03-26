/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.anim.values.AnimatableStringValue;
/*     */ import org.apache.batik.anim.values.AnimatableValue;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.svg.SVGAnimatedString;
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
/*     */ public class SVGOMAnimatedString
/*     */   extends AbstractSVGAnimatedValue
/*     */   implements SVGAnimatedString
/*     */ {
/*     */   protected String animVal;
/*     */   
/*     */   public SVGOMAnimatedString(AbstractElement elt, String ns, String ln) {
/*  51 */     super(elt, ns, ln);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBaseVal() {
/*  58 */     return this.element.getAttributeNS(this.namespaceURI, this.localName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBaseVal(String baseVal) throws DOMException {
/*  65 */     this.element.setAttributeNS(this.namespaceURI, this.localName, baseVal);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAnimVal() {
/*  72 */     if (this.hasAnimVal) {
/*  73 */       return this.animVal;
/*     */     }
/*  75 */     return this.element.getAttributeNS(this.namespaceURI, this.localName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getUnderlyingValue(AnimationTarget target) {
/*  82 */     return (AnimatableValue)new AnimatableStringValue(target, getBaseVal());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateAnimatedValue(AnimatableValue val) {
/*  89 */     if (val == null) {
/*  90 */       this.hasAnimVal = false;
/*     */     } else {
/*  92 */       this.hasAnimVal = true;
/*  93 */       this.animVal = ((AnimatableStringValue)val).getString();
/*     */     } 
/*  95 */     fireAnimatedAttributeListeners();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrAdded(Attr node, String newv) {
/* 102 */     fireBaseAttributeListeners();
/* 103 */     if (!this.hasAnimVal) {
/* 104 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrModified(Attr node, String oldv, String newv) {
/* 112 */     fireBaseAttributeListeners();
/* 113 */     if (!this.hasAnimVal) {
/* 114 */       fireAnimatedAttributeListeners();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attrRemoved(Attr node, String oldv) {
/* 122 */     fireBaseAttributeListeners();
/* 123 */     if (!this.hasAnimVal)
/* 124 */       fireAnimatedAttributeListeners(); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMAnimatedString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */