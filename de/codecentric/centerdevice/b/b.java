/*    */ package de.codecentric.centerdevice.b;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ class b extends InputStream {
/*    */   private InputStream a;
/*    */   private long b;
/*    */   
/*    */   public b(InputStream other, long bytes) {
/* 11 */     this.a = other;
/* 12 */     this.b = bytes;
/*    */   }
/*    */   
/*    */   public int read() throws IOException {
/* 16 */     if (this.b <= 0L) {
/* 17 */       return -1;
/*    */     }
/* 19 */     this.b--;
/* 20 */     return this.a.read();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/de/codecentric/centerdevice/b/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */