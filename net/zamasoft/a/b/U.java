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
/*    */ public class U
/*    */ {
/*    */   private final int a;
/*    */   private final V[] b;
/*    */   private final T[] c;
/*    */   
/*    */   protected U(RandomAccessFile raf, int offset) throws IOException {
/* 38 */     synchronized (raf) {
/* 39 */       raf.seek(offset);
/* 40 */       this.a = raf.readUnsignedShort();
/* 41 */       this.b = new V[this.a];
/* 42 */       this.c = new T[this.a]; int i;
/* 43 */       for (i = 0; i < this.a; i++) {
/* 44 */         this.b[i] = new V(raf);
/*    */       }
/* 46 */       for (i = 0; i < this.a; i++) {
/* 47 */         this.c[i] = new T(raf, offset + this.b[i].b());
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public int a() {
/* 53 */     return this.a;
/*    */   }
/*    */   
/*    */   public V a(int i) {
/* 57 */     return this.b[i];
/*    */   }
/*    */   
/*    */   public T a(String tag) {
/* 61 */     if (tag.length() != 4) {
/* 62 */       return null;
/*    */     }
/* 64 */     int tagVal = tag.charAt(0) << 24 | tag.charAt(1) << 16 | tag.charAt(2) << 8 | tag.charAt(3);
/* 65 */     for (int i = 0; i < this.a; i++) {
/* 66 */       if (this.b[i].a() == tagVal) {
/* 67 */         return this.c[i];
/*    */       }
/*    */     } 
/* 70 */     return null;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 74 */     StringBuffer buff = new StringBuffer(this.a + ":");
/* 75 */     for (int i = 0; i < this.a; i++) {
/* 76 */       int tag = this.b[i].a();
/* 77 */       buff.append((char)(tag >> 24 & 0xFF)).append((char)(tag >> 16 & 0xFF))
/* 78 */         .append((char)(tag >> 8 & 0xFF)).append((char)(tag & 0xFF)).append('/');
/*    */     } 
/* 80 */     return buff.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/U.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */