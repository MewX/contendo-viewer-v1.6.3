/*    */ package org.apache.batik.extension.svg;
/*    */ 
/*    */ import org.apache.batik.dom.AbstractDocument;
/*    */ import org.apache.batik.extension.PrefixableStylableExtensionElement;
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
/*    */ public class FlowDivElement
/*    */   extends PrefixableStylableExtensionElement
/*    */   implements BatikExtConstants
/*    */ {
/*    */   protected FlowDivElement() {}
/*    */   
/*    */   public FlowDivElement(String prefix, AbstractDocument owner) {
/* 47 */     super(prefix, owner);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLocalName() {
/* 54 */     return "flowDiv";
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
/* 68 */     return (Node)new FlowDivElement();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/extension/svg/FlowDivElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */