/*    */ package org.apache.batik.css.dom;
/*    */ 
/*    */ import org.apache.batik.css.engine.CSSEngine;
/*    */ import org.apache.batik.css.engine.CSSStylableElement;
/*    */ import org.w3c.dom.Element;
/*    */ import org.w3c.dom.css.CSSStyleDeclaration;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CSSOMSVGViewCSS
/*    */   extends CSSOMViewCSS
/*    */ {
/*    */   public CSSOMSVGViewCSS(CSSEngine engine) {
/* 39 */     super(engine);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CSSStyleDeclaration getComputedStyle(Element elt, String pseudoElt) {
/* 48 */     if (elt instanceof CSSStylableElement) {
/* 49 */       return new CSSOMSVGComputedStyle(this.cssEngine, (CSSStylableElement)elt, pseudoElt);
/*    */     }
/*    */ 
/*    */     
/* 53 */     return null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/dom/CSSOMSVGViewCSS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */