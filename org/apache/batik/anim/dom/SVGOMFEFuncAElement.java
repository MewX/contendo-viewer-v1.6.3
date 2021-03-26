/*    */ package org.apache.batik.anim.dom;
/*    */ 
/*    */ import org.apache.batik.dom.AbstractDocument;
/*    */ import org.w3c.dom.Node;
/*    */ import org.w3c.dom.svg.SVGFEFuncAElement;
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
/*    */ public class SVGOMFEFuncAElement
/*    */   extends SVGOMComponentTransferFunctionElement
/*    */   implements SVGFEFuncAElement
/*    */ {
/*    */   protected SVGOMFEFuncAElement() {}
/*    */   
/*    */   public SVGOMFEFuncAElement(String prefix, AbstractDocument owner) {
/* 47 */     super(prefix, owner);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLocalName() {
/* 54 */     return "feFuncA";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Node newNode() {
/* 61 */     return (Node)new SVGOMFEFuncAElement();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMFEFuncAElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */