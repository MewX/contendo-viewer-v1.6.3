/*    */ package org.apache.batik.css.parser;
/*    */ 
/*    */ import org.w3c.css.sac.LangCondition;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefaultLangCondition
/*    */   implements LangCondition
/*    */ {
/*    */   protected String lang;
/*    */   
/*    */   public DefaultLangCondition(String lang) {
/* 41 */     this.lang = lang;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short getConditionType() {
/* 49 */     return 6;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLang() {
/* 56 */     return this.lang;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 63 */     return ":lang(" + this.lang + ")";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/parser/DefaultLangCondition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */