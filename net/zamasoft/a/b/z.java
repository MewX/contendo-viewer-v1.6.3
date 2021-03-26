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
/*    */ public class z
/*    */ {
/*    */   private final int a;
/*    */   private final int[] b;
/*    */   
/*    */   protected z(RandomAccessFile raf) throws IOException {
/* 35 */     raf.readUnsignedShort();
/* 36 */     raf.readUnsignedShort();
/* 37 */     this.a = raf.readUnsignedShort();
/* 38 */     this.b = new int[this.a];
/* 39 */     for (int i = 0; i < this.a; i++) {
/* 40 */       this.b[i] = raf.readUnsignedShort();
/*    */     }
/*    */   }
/*    */   
/*    */   protected boolean a(int n) {
/* 45 */     for (int i = 0; i < this.a; i++) {
/* 46 */       if (this.b[i] == n) {
/* 47 */         return true;
/*    */       }
/*    */     } 
/* 50 */     return false;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/z.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */