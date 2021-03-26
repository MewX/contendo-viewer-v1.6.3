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
/*    */ 
/*    */ public abstract class u
/*    */ {
/*    */   public abstract int a();
/*    */   
/*    */   public abstract y a(int paramInt);
/*    */   
/*    */   public static u a(RandomAccessFile raf) throws IOException {
/* 39 */     u table = null;
/* 40 */     raf.readUnsignedShort();
/* 41 */     raf.readUnsignedShort();
/* 42 */     int coverage = raf.readUnsignedShort();
/* 43 */     int format = coverage >> 8;
/*    */     
/* 45 */     switch (format) {
/*    */       case 0:
/* 47 */         table = new v(raf);
/*    */         break;
/*    */       case 2:
/* 50 */         table = new w(raf);
/*    */         break;
/*    */     } 
/*    */ 
/*    */     
/* 55 */     return table;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/u.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */