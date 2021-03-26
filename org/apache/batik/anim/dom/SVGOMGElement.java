/*    */ package org.apache.batik.anim.dom;
/*    */ 
/*    */ import org.apache.batik.dom.AbstractDocument;
/*    */ import org.w3c.dom.Node;
/*    */ import org.w3c.dom.svg.SVGGElement;
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
/*    */ public class SVGOMGElement
/*    */   extends SVGGraphicsElement
/*    */   implements SVGGElement
/*    */ {
/*    */   protected SVGOMGElement() {}
/*    */   
/*    */   public SVGOMGElement(String prefix, AbstractDocument owner) {
/* 46 */     super(prefix, owner);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLocalName() {
/* 53 */     return "g";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Node newNode() {
/* 60 */     return (Node)new SVGOMGElement();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMGElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */