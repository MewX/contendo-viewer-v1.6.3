/*    */ package org.apache.batik.css.dom;
/*    */ 
/*    */ import org.apache.batik.css.engine.CSSEngine;
/*    */ import org.apache.batik.css.engine.CSSStylableElement;
/*    */ import org.w3c.dom.Element;
/*    */ import org.w3c.dom.css.CSSStyleDeclaration;
/*    */ import org.w3c.dom.css.ViewCSS;
/*    */ import org.w3c.dom.views.DocumentView;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CSSOMViewCSS
/*    */   implements ViewCSS
/*    */ {
/*    */   protected CSSEngine cssEngine;
/*    */   
/*    */   public CSSOMViewCSS(CSSEngine engine) {
/* 47 */     this.cssEngine = engine;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DocumentView getDocument() {
/* 55 */     return (DocumentView)this.cssEngine.getDocument();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CSSStyleDeclaration getComputedStyle(Element elt, String pseudoElt) {
/* 64 */     if (elt instanceof CSSStylableElement) {
/* 65 */       return new CSSOMComputedStyle(this.cssEngine, (CSSStylableElement)elt, pseudoElt);
/*    */     }
/*    */ 
/*    */     
/* 69 */     return null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/dom/CSSOMViewCSS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */