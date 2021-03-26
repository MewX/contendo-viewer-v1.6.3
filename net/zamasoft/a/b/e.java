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
/*    */ public class e
/*    */   extends d
/*    */ {
/*    */   private static final long a = 0L;
/*    */   private int b;
/*    */   private int[] c;
/*    */   
/*    */   protected e(RandomAccessFile raf) throws IOException {
/* 37 */     this.b = raf.readUnsignedShort();
/* 38 */     this.c = new int[this.b];
/* 39 */     for (int i = 0; i < this.b; i++) {
/* 40 */       this.c[i] = raf.readUnsignedShort();
/*    */     }
/*    */   }
/*    */   
/*    */   public int a() {
/* 45 */     return 1;
/*    */   }
/*    */   
/*    */   public int a(int glyphId) {
/* 49 */     for (int i = 0; i < this.b; i++) {
/* 50 */       if (this.c[i] == glyphId) {
/* 51 */         return i;
/*    */       }
/*    */     } 
/* 54 */     return -1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */