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
/*    */ public class T
/*    */ {
/*    */   private int a;
/*    */   private int b;
/*    */   private A[] c;
/*    */   private z d;
/*    */   private z[] e;
/*    */   
/*    */   protected T(RandomAccessFile raf, int offset) throws IOException {
/* 42 */     synchronized (raf) {
/* 43 */       raf.seek(offset);
/* 44 */       this.a = raf.readUnsignedShort();
/* 45 */       this.b = raf.readUnsignedShort();
/* 46 */       if (this.b > 0) {
/* 47 */         this.c = new A[this.b];
/* 48 */         for (int i = 0; i < this.b; i++) {
/* 49 */           this.c[i] = new A(raf);
/*    */         }
/*    */       } 
/*    */ 
/*    */       
/* 54 */       if (this.b > 0) {
/* 55 */         this.e = new z[this.b];
/* 56 */         for (int i = 0; i < this.b; i++) {
/* 57 */           raf.seek((offset + this.c[i].b()));
/* 58 */           this.e[i] = new z(raf);
/*    */         } 
/*    */       } 
/* 61 */       if (this.a > 0) {
/* 62 */         raf.seek((offset + this.a));
/* 63 */         this.d = new z(raf);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public z a() {
/* 69 */     return this.d;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 73 */     StringBuffer buff = new StringBuffer();
/* 74 */     for (int i = 0; i < this.c.length; i++) {
/* 75 */       int tag = this.c[i].a();
/* 76 */       buff.append((char)(tag >> 24 & 0xFF)).append((char)(tag >> 16 & 0xFF))
/* 77 */         .append((char)(tag >> 8 & 0xFF)).append((char)(tag & 0xFF)).append('/');
/*    */     } 
/* 79 */     return buff.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/T.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */