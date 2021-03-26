/*    */ package net.zamasoft.a.b;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.RandomAccessFile;
/*    */ import java.io.Serializable;
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
/*    */ public abstract class X
/*    */   extends I
/*    */   implements Serializable
/*    */ {
/*    */   private static final long a = 0L;
/*    */   
/*    */   public abstract int a();
/*    */   
/*    */   public abstract int a(int paramInt);
/*    */   
/*    */   public static X a(RandomAccessFile raf, int offset) throws IOException {
/* 37 */     X s = null;
/* 38 */     synchronized (raf) {
/* 39 */       raf.seek(offset);
/* 40 */       int format = raf.readUnsignedShort();
/* 41 */       if (format == 1) {
/* 42 */         s = new Y(raf, offset);
/* 43 */       } else if (format == 2) {
/* 44 */         s = new Z(raf, offset);
/*    */       } 
/*    */     } 
/* 47 */     return s;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/X.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */