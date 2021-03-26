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
/*    */ public class B
/*    */ {
/*    */   private int a;
/*    */   private int b;
/*    */   private int[] c;
/*    */   
/*    */   public B(RandomAccessFile raf) throws IOException {
/* 38 */     this.a = raf.readUnsignedShort();
/* 39 */     this.b = raf.readUnsignedShort();
/* 40 */     this.c = new int[this.b - 1];
/* 41 */     for (int i = 0; i < this.b - 1; i++) {
/* 42 */       this.c[i] = raf.readUnsignedShort();
/*    */     }
/*    */   }
/*    */   
/*    */   public int a() {
/* 47 */     return this.b;
/*    */   }
/*    */   
/*    */   public int a(int i) {
/* 51 */     return (i == 0) ? this.a : this.c[i - 1];
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/B.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */