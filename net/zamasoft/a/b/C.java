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
/*    */ public class C
/*    */ {
/*    */   private int a;
/*    */   private int[] b;
/*    */   private B[] c;
/*    */   
/*    */   public C(RandomAccessFile raf, int offset) throws IOException {
/* 38 */     synchronized (raf) {
/* 39 */       raf.seek(offset);
/* 40 */       this.a = raf.readUnsignedShort();
/* 41 */       this.b = new int[this.a];
/* 42 */       this.c = new B[this.a]; int i;
/* 43 */       for (i = 0; i < this.a; i++) {
/* 44 */         this.b[i] = raf.readUnsignedShort();
/*    */       }
/* 46 */       for (i = 0; i < this.a; i++) {
/* 47 */         raf.seek((offset + this.b[i]));
/* 48 */         this.c[i] = new B(raf);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/C.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */