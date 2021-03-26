/*    */ package org.apache.batik.anim.dom;
/*    */ 
/*    */ import org.apache.batik.dom.AbstractDocument;
/*    */ import org.w3c.dom.Node;
/*    */ import org.w3c.dom.svg.SVGFEMergeElement;
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
/*    */ public class SVGOMFEMergeElement
/*    */   extends SVGOMFilterPrimitiveStandardAttributes
/*    */   implements SVGFEMergeElement
/*    */ {
/*    */   protected SVGOMFEMergeElement() {}
/*    */   
/*    */   public SVGOMFEMergeElement(String prefix, AbstractDocument owner) {
/* 47 */     super(prefix, owner);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLocalName() {
/* 54 */     return "feMerge";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Node newNode() {
/* 61 */     return (Node)new SVGOMFEMergeElement();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMFEMergeElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */