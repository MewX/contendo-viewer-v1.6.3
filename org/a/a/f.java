/*    */ package org.a.a;
/*    */ 
/*    */ import javax.json.stream.JsonLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class f
/*    */   implements JsonLocation
/*    */ {
/* 49 */   static final JsonLocation a = new f(-1L, -1L, -1L);
/*    */   
/*    */   private final long b;
/*    */   private final long c;
/*    */   private final long d;
/*    */   
/*    */   f(long lineNo, long columnNo, long streamOffset) {
/* 56 */     this.c = lineNo;
/* 57 */     this.b = columnNo;
/* 58 */     this.d = streamOffset;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getLineNumber() {
/* 63 */     return this.c;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getColumnNumber() {
/* 68 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getStreamOffset() {
/* 73 */     return this.d;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 78 */     return "(line no=" + this.c + ", column no=" + this.b + ", offset=" + this.d + ")";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/a/a/f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */