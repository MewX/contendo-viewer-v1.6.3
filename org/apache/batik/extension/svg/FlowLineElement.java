/*    */ package org.apache.batik.extension.svg;
/*    */ 
/*    */ import org.apache.batik.anim.dom.SVGOMTextPositioningElement;
/*    */ import org.apache.batik.dom.AbstractDocument;
/*    */ import org.w3c.dom.Node;
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
/*    */ public class FlowLineElement
/*    */   extends SVGOMTextPositioningElement
/*    */   implements BatikExtConstants
/*    */ {
/*    */   protected FlowLineElement() {}
/*    */   
/*    */   public FlowLineElement(String prefix, AbstractDocument owner) {
/* 47 */     super(prefix, owner);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLocalName() {
/* 54 */     return "flowLine";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getNamespaceURI() {
/* 61 */     return "http://xml.apache.org/batik/ext";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Node newNode() {
/* 68 */     return (Node)new FlowLineElement();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/extension/svg/FlowLineElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */