/*    */ package jp.cssj.sakae.sac.parser;
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
/* 68 */     super(localName, namespaceURI, specified, value);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short getConditionType() {
/* 76 */     return 8;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/*    */     String qName;
/* 84 */     if (this.namespaceURI == null) {
/* 85 */       qName = this.localName;
/*    */     } else {
/* 87 */       qName = this.namespaceURI + "|" + this.localName;
/*    */     } 
/* 89 */     return "[" + qName + "|=\"" + getValue() + "\"]";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/parser/DefaultBeginHyphenAttributeCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */