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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class d
/*    */   implements Serializable
/*    */ {
/*    */   private static final long a = 0L;
/*    */   
/*    */   public abstract int a();
/*    */   
/*    */   public abstract int a(int paramInt);
/*    */   
/*    */   protected static d a(RandomAccessFile raf) throws IOException {
/* 43 */     d c = null;
/* 44 */     int format = raf.readUnsignedShort();
/* 45 */     if (format == 1) {
/* 46 */       c = new e(raf);
/* 47 */     } else if (format == 2) {
/* 48 */       c = new f(raf);
/*    */     } 
/* 50 */     return c;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */