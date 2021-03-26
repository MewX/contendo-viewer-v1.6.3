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
/*    */ public class x
/*    */   implements aa
/*    */ {
/*    */   private int a;
/*    */   private u[] b;
/*    */   
/*    */   protected x(i de, RandomAccessFile raf) throws IOException {
/* 35 */     synchronized (raf) {
/* 36 */       raf.seek(de.c());
/* 37 */       raf.readUnsignedShort();
/* 38 */       this.a = raf.readUnsignedShort();
/* 39 */       this.b = new u[this.a];
/* 40 */       for (int j = 0; j < this.a; j++) {
/* 41 */         this.b[j] = u.a(raf);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public int b() {
/* 47 */     return this.a;
/*    */   }
/*    */   
/*    */   public u a(int i) {
/* 51 */     return this.b[i];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int a() {
/* 60 */     return 1801810542;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/x.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */