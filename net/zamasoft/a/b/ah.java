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
/*    */ public abstract class ah
/*    */   implements Serializable, aa
/*    */ {
/*    */   private static final long a = 0L;
/*    */   private i b;
/*    */   private RandomAccessFile c;
/*    */   private int d;
/*    */   private int e;
/* 37 */   private int[] f = null;
/*    */   
/* 39 */   private short[] g = null;
/*    */   
/*    */   protected ah(i de, RandomAccessFile raf) throws IOException {
/* 42 */     this.b = de;
/* 43 */     this.c = raf;
/*    */   }
/*    */   
/*    */   public void a(int numberOfHMetrics, int lsbCount) throws IOException {
/* 47 */     this.d = numberOfHMetrics;
/* 48 */     this.e = lsbCount;
/*    */   }
/*    */   
/*    */   private void b() {
/* 52 */     if (this.c == null) {
/*    */       return;
/*    */     }
/* 55 */     synchronized (this.c) {
/*    */       try {
/* 57 */         this.f = new int[this.d];
/* 58 */         this.c.seek(this.b.c()); int j;
/* 59 */         for (j = 0; j < this.d; j++) {
/* 60 */           this.f[j] = this.c.read() << 24 | this.c.read() << 16 | this.c.read() << 8 | this.c
/* 61 */             .read();
/*    */         }
/* 63 */         if (this.e > 0) {
/* 64 */           this.g = new short[this.e];
/* 65 */           for (j = 0; j < this.e; j++) {
/* 66 */             this.g[j] = (short)(this.c.read() << 8 | this.c.read());
/*    */           }
/*    */         } 
/* 69 */       } catch (IOException e) {
/* 70 */         throw new RuntimeException(e);
/*    */       } 
/* 72 */       this.c = null;
/*    */     } 
/*    */   }
/*    */   public synchronized int a(int j) {
/*    */     int advance;
/* 77 */     b();
/*    */     
/* 79 */     if (j < this.f.length) {
/* 80 */       advance = this.f[j] >> 16;
/*    */     } else {
/* 82 */       advance = this.f[this.f.length - 1] >> 16;
/*    */     } 
/* 84 */     return advance;
/*    */   }
/*    */   public synchronized short b(int j) {
/*    */     short lsb;
/* 88 */     b();
/*    */     
/* 90 */     if (j < this.f.length) {
/* 91 */       lsb = (short)(this.f[j] & 0xFFFF);
/*    */     } else {
/* 93 */       j -= this.f.length;
/* 94 */       lsb = this.g[j];
/*    */     } 
/* 96 */     return lsb;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/ah.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */