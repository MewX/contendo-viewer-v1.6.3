/*    */ package org.apache.batik.dom;
/*    */ 
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
/*    */ public class GenericText
/*    */   extends AbstractText
/*    */ {
/*    */   protected boolean readonly;
/*    */   
/*    */   protected GenericText() {}
/*    */   
/*    */   public GenericText(String value, AbstractDocument owner) {
/* 48 */     this.ownerDocument = owner;
/* 49 */     setNodeValue(value);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getNodeName() {
/* 57 */     return "#text";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short getNodeType() {
/* 65 */     return 3;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isReadonly() {
/* 72 */     return this.readonly;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setReadonly(boolean v) {
/* 79 */     this.readonly = v;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Text createTextNode(String text) {
/* 86 */     return getOwnerDocument().createTextNode(text);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Node newNode() {
/* 93 */     return new GenericText();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/GenericText.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */