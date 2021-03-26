/*    */ package org.apache.commons.csv;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class Token
/*    */ {
/*    */   private static final int INITIAL_TOKEN_LENGTH = 50;
/*    */   
/*    */   enum Type
/*    */   {
/* 34 */     INVALID,
/*    */ 
/*    */     
/* 37 */     TOKEN,
/*    */ 
/*    */     
/* 40 */     EOF,
/*    */ 
/*    */     
/* 43 */     EORECORD,
/*    */ 
/*    */     
/* 46 */     COMMENT;
/*    */   }
/*    */ 
/*    */   
/* 50 */   Type type = Type.INVALID;
/*    */ 
/*    */   
/* 53 */   final StringBuilder content = new StringBuilder(50);
/*    */   
/*    */   boolean isReady;
/*    */ 
/*    */   
/*    */   void reset() {
/* 59 */     this.content.setLength(0);
/* 60 */     this.type = Type.INVALID;
/* 61 */     this.isReady = false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 71 */     return this.type.name() + " [" + this.content.toString() + "]";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/csv/Token.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */