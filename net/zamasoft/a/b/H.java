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
/*    */ public class H
/*    */ {
/*    */   private final int a;
/*    */   private final int[] b;
/*    */   private final G[] c;
/*    */   
/*    */   public H(RandomAccessFile raf, int offset, J factory) throws IOException {
/* 37 */     synchronized (raf) {
/* 38 */       raf.seek(offset);
/* 39 */       this.a = raf.readUnsignedShort();
/* 40 */       this.b = new int[this.a];
/* 41 */       this.c = new G[this.a]; int i;
/* 42 */       for (i = 0; i < this.a; i++) {
/* 43 */         this.b[i] = raf.readUnsignedShort();
/*    */       }
/* 45 */       for (i = 0; i < this.a; i++) {
/* 46 */         this.c[i] = new G(factory, raf, offset + this.b[i]);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public G a(j feature, int index) {
/* 52 */     if (feature.a() > index) {
/* 53 */       int i = feature.a(index);
/* 54 */       return this.c[i];
/*    */     } 
/* 56 */     return null;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 60 */     StringBuffer buff = new StringBuffer(this.a + ":");
/* 61 */     for (int i = 0; i < this.c.length; i++) {
/* 62 */       int tag = this.c[i].a();
/* 63 */       buff.append((char)(tag >> 24 & 0xFF)).append((char)(tag >> 16 & 0xFF))
/* 64 */         .append((char)(tag >> 8 & 0xFF)).append((char)(tag & 0xFF)).append('/');
/*    */     } 
/* 66 */     return buff.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/H.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */