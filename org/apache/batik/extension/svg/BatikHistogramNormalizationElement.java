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
/*    */ 
/*    */ public class BatikHistogramNormalizationElement
/*    */   extends PrefixableStylableExtensionElement
/*    */   implements BatikExtConstants
/*    */ {
/*    */   protected BatikHistogramNormalizationElement() {}
/*    */   
/*    */   public BatikHistogramNormalizationElement(String prefix, AbstractDocument owner) {
/* 48 */     super(prefix, owner);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLocalName() {
/* 55 */     return "histogramNormalization";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getNamespaceURI() {
/* 62 */     return "http://xml.apache.org/batik/ext";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Node newNode() {
/* 69 */     return (Node)new BatikHistogramNormalizationElement();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/extension/svg/BatikHistogramNormalizationElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */