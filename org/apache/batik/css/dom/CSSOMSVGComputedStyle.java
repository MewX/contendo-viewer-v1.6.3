/*     */ package org.apache.batik.css.dom;
/*     */ 
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.CSSStylableElement;
/*     */ import org.apache.batik.css.engine.value.Value;
/*     */ import org.w3c.dom.css.CSSValue;
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
/*     */ public class CSSOMSVGComputedStyle
/*     */   extends CSSOMComputedStyle
/*     */ {
/*     */   public CSSOMSVGComputedStyle(CSSEngine e, CSSStylableElement elt, String pseudoElt) {
/*  43 */     super(e, elt, pseudoElt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected CSSValue createCSSValue(int idx) {
/*  50 */     if (idx > 59) {
/*  51 */       if (this.cssEngine.getValueManagers()[idx] instanceof org.apache.batik.css.engine.value.svg.SVGPaintManager) {
/*  52 */         return (CSSValue)new ComputedCSSPaintValue(idx);
/*     */       }
/*  54 */       if (this.cssEngine.getValueManagers()[idx] instanceof org.apache.batik.css.engine.value.svg.SVGColorManager) {
/*  55 */         return (CSSValue)new ComputedCSSColorValue(idx);
/*     */       }
/*     */     } else {
/*  58 */       switch (idx) {
/*     */         case 15:
/*     */         case 45:
/*  61 */           return (CSSValue)new ComputedCSSPaintValue(idx);
/*     */         
/*     */         case 19:
/*     */         case 33:
/*     */         case 43:
/*  66 */           return (CSSValue)new ComputedCSSColorValue(idx);
/*     */       } 
/*     */     } 
/*  69 */     return super.createCSSValue(idx);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class ComputedCSSColorValue
/*     */     extends CSSOMSVGColor
/*     */     implements CSSOMSVGColor.ValueProvider
/*     */   {
/*     */     protected int index;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ComputedCSSColorValue(int idx) {
/*  88 */       super(null);
/*  89 */       this.valueProvider = this;
/*  90 */       this.index = idx;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Value getValue() {
/*  97 */       return CSSOMSVGComputedStyle.this.cssEngine.getComputedStyle(CSSOMSVGComputedStyle.this.element, CSSOMSVGComputedStyle.this.pseudoElement, this.index);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class ComputedCSSPaintValue
/*     */     extends CSSOMSVGPaint
/*     */     implements CSSOMSVGColor.ValueProvider
/*     */   {
/*     */     protected int index;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ComputedCSSPaintValue(int idx) {
/* 117 */       super((CSSOMSVGColor.ValueProvider)null);
/* 118 */       this.valueProvider = this;
/* 119 */       this.index = idx;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Value getValue() {
/* 126 */       return CSSOMSVGComputedStyle.this.cssEngine.getComputedStyle(CSSOMSVGComputedStyle.this.element, CSSOMSVGComputedStyle.this.pseudoElement, this.index);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/dom/CSSOMSVGComputedStyle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */