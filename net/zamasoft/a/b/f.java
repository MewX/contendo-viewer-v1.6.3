/*    */ package net.zamasoft.a.b;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.RandomAccessFile;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class f
/*    */   extends d
/*    */ {
/*    */   private static final long a = 0L;
/*    */   private int b;
/*    */   private S[] c;
/*    */   
/*    */   protected f(RandomAccessFile raf) throws IOException {
/* 37 */     this.b = raf.readUnsignedShort();
/* 38 */     this.c = new S[this.b];
/* 39 */     for (int i = 0; i < this.b; i++) {
/* 40 */       this.c[i] = new S(raf);
/*    */     }
/*    */   }
/*    */   
/*    */   public int a() {
/* 45 */     return 2;
/*    */   }
/*    */   
/*    */   public int a(int glyphId) {
/* 49 */     for (int i = 0; i < this.b; i++) {
/* 50 */       int n = this.c[i].b(glyphId);
/* 51 */       if (n > -1) {
/* 52 */         return n;
/*    */       }
/*    */     } 
/* 55 */     return -1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */