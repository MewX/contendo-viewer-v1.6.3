/*    */ package jp.cssj.e.d;
/*    */ 
/*    */ import java.io.File;
/*    */ import jp.cssj.e.d;
/*    */ 
/*    */ 
/*    */ class c
/*    */   implements d
/*    */ {
/*    */   private static final long d = 0L;
/*    */   private final long e;
/*    */   private final File f;
/*    */   
/*    */   public c(long timestamp, File file) {
/* 15 */     this.e = timestamp;
/* 16 */     this.f = file;
/*    */   }
/*    */   
/*    */   public int a() {
/* 20 */     return a(this.f);
/*    */   }
/*    */   
/*    */   public int a(d validity) {
/* 24 */     return a(((c)validity).f);
/*    */   }
/*    */   
/*    */   private int a(File file) {
/* 28 */     if (file.lastModified() != this.e) {
/* 29 */       return -1;
/*    */     }
/* 31 */     return 1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/e/d/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */