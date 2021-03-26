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
/*    */ public class DefaultBeginHyphenAttributeCondition
/*    */   extends DefaultAttributeCondition
/*    */ {
/*    */   public DefaultBeginHyphenAttributeCondition(String localName, String namespaceURI, boolean specified, String value) {
/* 38 */     super(localName, namespaceURI, specified, value);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short getConditionType() {
/* 46 */     return 8;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     return "[" + getLocalName() + "|=\"" + getValue() + "\"]";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/parser/DefaultBeginHyphenAttributeCondition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */