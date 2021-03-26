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
/*    */ public class E
/*    */   extends D
/*    */ {
/*    */   private int a;
/*    */   private int b;
/*    */   private int[] c;
/*    */   private C[] d;
/*    */   
/*    */   protected E(RandomAccessFile raf, int offset) throws IOException {
/* 41 */     this.a = raf.readUnsignedShort();
/* 42 */     this.b = raf.readUnsignedShort();
/* 43 */     this.c = new int[this.b];
/* 44 */     this.d = new C[this.b];
/* 45 */     for (int i = 0; i < this.b; i++) {
/* 46 */       this.c[i] = raf.readUnsignedShort();
/*    */     }
/* 48 */     synchronized (raf) {
/* 49 */       raf.seek((offset + this.a));
/* 50 */       d.a(raf);
/* 51 */       for (int j = 0; j < this.b; j++) {
/* 52 */         this.d[j] = new C(raf, offset + this.c[j]);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public int a() {
/* 58 */     return 1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/E.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */