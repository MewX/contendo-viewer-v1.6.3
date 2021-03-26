/*    */ package org.apache.batik.css.parser;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefaultAttributeCondition
/*    */   extends AbstractAttributeCondition
/*    */ {
/*    */   protected String localName;
/*    */   protected String namespaceURI;
/*    */   protected boolean specified;
/*    */   
/*    */   public DefaultAttributeCondition(String localName, String namespaceURI, boolean specified, String value) {
/* 53 */     super(value);
/* 54 */     this.localName = localName;
/* 55 */     this.namespaceURI = namespaceURI;
/* 56 */     this.specified = specified;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short getConditionType() {
/* 64 */     return 4;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getNamespaceURI() {
/* 72 */     return this.namespaceURI;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLocalName() {
/* 80 */     return this.localName;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean getSpecified() {
/* 88 */     return this.specified;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 95 */     if (this.value == null) {
/* 96 */       return "[" + this.localName + "]";
/*    */     }
/* 98 */     return "[" + this.localName + "=\"" + this.value + "\"]";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/parser/DefaultAttributeCondition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */