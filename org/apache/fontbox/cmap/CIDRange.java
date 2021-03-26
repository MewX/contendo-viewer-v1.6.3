/*    */ package org.apache.fontbox.cmap;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class CIDRange
/*    */ {
/*    */   private final char from;
/*    */   private char to;
/*    */   private final int cid;
/*    */   
/*    */   CIDRange(char from, char to, int cid) {
/* 34 */     this.from = from;
/* 35 */     this.to = to;
/* 36 */     this.cid = cid;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int map(char ch) {
/* 47 */     if (this.from <= ch && ch <= this.to)
/*    */     {
/* 49 */       return this.cid + ch - this.from;
/*    */     }
/* 51 */     return -1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int unmap(int code) {
/* 62 */     if (this.cid <= code && code <= this.cid + this.to - this.from)
/*    */     {
/* 64 */       return this.from + code - this.cid;
/*    */     }
/* 66 */     return -1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean extend(char newFrom, char newTo, int newCid) {
/* 80 */     if (newFrom == this.to + 1 && newCid == this.cid + this.to - this.from + 1) {
/*    */       
/* 82 */       this.to = newTo;
/* 83 */       return true;
/*    */     } 
/* 85 */     return false;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/cmap/CIDRange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */