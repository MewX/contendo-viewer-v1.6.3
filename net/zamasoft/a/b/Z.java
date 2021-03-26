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
/*    */ 
/*    */ 
/*    */ public class Z
/*    */   extends X
/*    */ {
/*    */   private static final long a = 0L;
/*    */   private int b;
/*    */   private int c;
/*    */   private int[] d;
/*    */   private d e;
/*    */   
/*    */   protected Z(RandomAccessFile raf, int offset) throws IOException {
/* 41 */     this.b = raf.readUnsignedShort();
/* 42 */     this.c = raf.readUnsignedShort();
/* 43 */     this.d = new int[this.c];
/* 44 */     for (int i = 0; i < this.c; i++) {
/* 45 */       this.d[i] = raf.readUnsignedShort();
/*    */     }
/* 47 */     synchronized (raf) {
/* 48 */       raf.seek((offset + this.b));
/* 49 */       this.e = d.a(raf);
/*    */     } 
/*    */   }
/*    */   
/*    */   public int a() {
/* 54 */     return 2;
/*    */   }
/*    */   
/*    */   public int a(int glyphId) {
/* 58 */     int i = this.e.a(glyphId);
/* 59 */     if (i > -1) {
/* 60 */       return this.d[i];
/*    */     }
/* 62 */     return glyphId;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/Z.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */