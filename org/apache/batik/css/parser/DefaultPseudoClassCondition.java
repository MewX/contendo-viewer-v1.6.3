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
/*    */ public class DefaultPseudoClassCondition
/*    */   extends AbstractAttributeCondition
/*    */ {
/*    */   protected String namespaceURI;
/*    */   
/*    */   public DefaultPseudoClassCondition(String namespaceURI, String value) {
/* 38 */     super(value);
/* 39 */     this.namespaceURI = namespaceURI;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short getConditionType() {
/* 47 */     return 10;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getNamespaceURI() {
/* 55 */     return this.namespaceURI;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLocalName() {
/* 63 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean getSpecified() {
/* 71 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 78 */     return ":" + getValue();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/parser/DefaultPseudoClassCondition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */