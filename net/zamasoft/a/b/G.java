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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class G
/*    */ {
/*    */   public static final int a = 2;
/*    */   public static final int b = 4;
/*    */   public static final int c = 8;
/*    */   public static final int d = 65280;
/*    */   private int e;
/*    */   private int f;
/*    */   private int[] g;
/*    */   private I[] h;
/*    */   
/*    */   public G(J factory, RandomAccessFile raf, int offset) throws IOException {
/* 49 */     synchronized (raf) {
/* 50 */       raf.seek(offset);
/* 51 */       this.e = raf.readUnsignedShort();
/* 52 */       raf.readUnsignedShort();
/* 53 */       this.f = raf.readUnsignedShort();
/* 54 */       this.g = new int[this.f];
/* 55 */       this.h = new I[this.f]; int i;
/* 56 */       for (i = 0; i < this.f; i++) {
/* 57 */         this.g[i] = raf.readUnsignedShort();
/*    */       }
/* 59 */       for (i = 0; i < this.f; i++) {
/* 60 */         this.h[i] = factory.a(this.e, raf, offset + this.g[i]);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public int a() {
/* 66 */     return this.e;
/*    */   }
/*    */   
/*    */   public int b() {
/* 70 */     return this.f;
/*    */   }
/*    */   
/*    */   public I a(int i) {
/* 74 */     return this.h[i];
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/G.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */