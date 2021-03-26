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
/*    */ public class j
/*    */ {
/*    */   private int a;
/*    */   private int[] b;
/*    */   
/*    */   protected j(RandomAccessFile raf, int offset) throws IOException {
/* 36 */     synchronized (raf) {
/* 37 */       raf.seek(offset);
/* 38 */       raf.readUnsignedShort();
/* 39 */       this.a = raf.readUnsignedShort();
/* 40 */       this.b = new int[this.a];
/* 41 */       for (int i = 0; i < this.a; i++) {
/* 42 */         this.b[i] = raf.readUnsignedShort();
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public int a() {
/* 48 */     return this.a;
/*    */   }
/*    */   
/*    */   public int a(int i) {
/* 52 */     return this.b[i];
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/j.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */