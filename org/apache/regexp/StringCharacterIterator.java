/*    */ package org.apache.regexp;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class StringCharacterIterator
/*    */   implements CharacterIterator
/*    */ {
/*    */   private final String src;
/*    */   
/*    */   public StringCharacterIterator(String paramString) {
/* 72 */     this.src = paramString;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String substring(int paramInt1, int paramInt2) {
/* 78 */     return this.src.substring(paramInt1, paramInt2);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String substring(int paramInt) {
/* 84 */     return this.src.substring(paramInt);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public char charAt(int paramInt) {
/* 90 */     return this.src.charAt(paramInt);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isEnd(int paramInt) {
/* 96 */     return !(paramInt < this.src.length());
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/regexp/StringCharacterIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */