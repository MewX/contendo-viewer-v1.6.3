/*    */ package org.apache.batik.anim.dom;
/*    */ 
/*    */ import org.apache.batik.dom.AbstractDocument;
/*    */ import org.w3c.dom.Node;
/*    */ import org.w3c.dom.svg.SVGFontFaceUriElement;
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
/*    */ 
/*    */ 
/*    */ public class SVGOMFontFaceUriElement
/*    */   extends SVGOMElement
/*    */   implements SVGFontFaceUriElement
/*    */ {
/*    */   protected SVGOMFontFaceUriElement() {}
/*    */   
/*    */   public SVGOMFontFaceUriElement(String prefix, AbstractDocument owner) {
/* 47 */     super(prefix, owner);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLocalName() {
/* 54 */     return "font-face-uri";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Node newNode() {
/* 61 */     return (Node)new SVGOMFontFaceUriElement();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMFontFaceUriElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */