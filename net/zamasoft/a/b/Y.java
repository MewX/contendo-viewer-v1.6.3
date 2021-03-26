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
/*    */ public class Y
/*    */   extends X
/*    */ {
/*    */   private static final long a = 0L;
/*    */   private int b;
/*    */   private short c;
/*    */   private d d;
/*    */   
/*    */   protected Y(RandomAccessFile raf, int offset) throws IOException {
/* 39 */     this.b = raf.readUnsignedShort();
/* 40 */     this.c = raf.readShort();
/* 41 */     synchronized (raf) {
/* 42 */       raf.seek((offset + this.b));
/* 43 */       this.d = d.a(raf);
/*    */     } 
/*    */   }
/*    */   
/*    */   public int a() {
/* 48 */     return 1;
/*    */   }
/*    */   
/*    */   public int a(int glyphId) {
/* 52 */     int i = this.d.a(glyphId);
/* 53 */     if (i > -1) {
/* 54 */       return glyphId + this.c;
/*    */     }
/* 56 */     return glyphId;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/Y.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */