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
/*    */ public class g
/*    */   implements aa
/*    */ {
/*    */   private short[] a;
/*    */   
/*    */   protected g(i de, RandomAccessFile raf) throws IOException {
/* 32 */     synchronized (raf) {
/* 33 */       raf.seek(de.c());
/* 34 */       int len = de.b() / 2;
/* 35 */       this.a = new short[len];
/* 36 */       for (int j = 0; j < len; j++) {
/* 37 */         this.a[j] = raf.readShort();
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public int a() {
/* 43 */     return 1668707360;
/*    */   }
/*    */   
/*    */   public short[] b() {
/* 47 */     return this.a;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/g.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */