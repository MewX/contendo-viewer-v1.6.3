/*    */ package org.apache.batik.dom;
/*    */ 
/*    */ import org.w3c.dom.CDATASection;
/*    */ import org.w3c.dom.Node;
/*    */ import org.w3c.dom.Text;
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
/*    */ public class GenericCDATASection
/*    */   extends AbstractText
/*    */   implements CDATASection
/*    */ {
/*    */   protected boolean readonly;
/*    */   
/*    */   protected GenericCDATASection() {}
/*    */   
/*    */   public GenericCDATASection(String value, AbstractDocument owner) {
/* 50 */     this.ownerDocument = owner;
/* 51 */     setNodeValue(value);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getNodeName() {
/* 59 */     return "#cdata-section";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short getNodeType() {
/* 67 */     return 4;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isReadonly() {
/* 74 */     return this.readonly;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setReadonly(boolean v) {
/* 81 */     this.readonly = v;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Text createTextNode(String text) {
/* 88 */     return getOwnerDocument().createCDATASection(text);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Node newNode() {
/* 95 */     return new GenericCDATASection();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/GenericCDATASection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */