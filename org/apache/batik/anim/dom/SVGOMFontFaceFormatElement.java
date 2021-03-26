/*    */ package org.apache.batik.anim.dom;
/*    */ 
/*    */ import org.apache.batik.dom.AbstractDocument;
/*    */ import org.w3c.dom.Node;
/*    */ import org.w3c.dom.svg.SVGFontFaceFormatElement;
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
/*    */ public class SVGOMFontFaceFormatElement
/*    */   extends SVGOMElement
/*    */   implements SVGFontFaceFormatElement
/*    */ {
/*    */   protected SVGOMFontFaceFormatElement() {}
/*    */   
/*    */   public SVGOMFontFaceFormatElement(String prefix, AbstractDocument owner) {
/* 60 */     super(prefix, owner);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLocalName() {
/* 67 */     return "font-face-format";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Node newNode() {
/* 74 */     return (Node)new SVGOMFontFaceFormatElement();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMFontFaceFormatElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */