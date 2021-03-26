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
/*    */ public abstract class D
/*    */   extends I
/*    */ {
/*    */   public static D a(RandomAccessFile raf, int offset) throws IOException {
/* 31 */     D ls = null;
/* 32 */     synchronized (raf) {
/* 33 */       raf.seek(offset);
/* 34 */       int format = raf.readUnsignedShort();
/* 35 */       if (format == 1) {
/* 36 */         ls = new E(raf, offset);
/*    */       }
/*    */     } 
/* 39 */     return ls;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/D.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */