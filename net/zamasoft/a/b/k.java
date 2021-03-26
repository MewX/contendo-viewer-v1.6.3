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
/*    */ public class k
/*    */ {
/*    */   private final int a;
/*    */   private final l[] b;
/*    */   private final j[] c;
/*    */   
/*    */   public k(RandomAccessFile raf, int offset) throws IOException {
/* 38 */     synchronized (raf) {
/* 39 */       raf.seek(offset);
/* 40 */       this.a = raf.readUnsignedShort();
/* 41 */       this.b = new l[this.a];
/* 42 */       this.c = new j[this.a]; int i;
/* 43 */       for (i = 0; i < this.a; i++) {
/* 44 */         this.b[i] = new l(raf);
/*    */       }
/* 46 */       for (i = 0; i < this.a; i++) {
/* 47 */         this.c[i] = new j(raf, offset + this.b[i].b());
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public j a(z langSys, String tag) {
/* 53 */     if (tag.length() != 4) {
/* 54 */       return null;
/*    */     }
/* 56 */     int tagVal = tag.charAt(0) << 24 | tag.charAt(1) << 16 | tag.charAt(2) << 8 | tag.charAt(3);
/* 57 */     for (int i = 0; i < this.a; i++) {
/* 58 */       if (this.b[i].a() == tagVal && 
/* 59 */         langSys.a(i)) {
/* 60 */         return this.c[i];
/*    */       }
/*    */     } 
/*    */     
/* 64 */     return null;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 68 */     StringBuffer buff = new StringBuffer(this.a + ":");
/* 69 */     for (int i = 0; i < this.b.length; i++) {
/* 70 */       int tag = this.b[i].a();
/* 71 */       buff.append((char)(tag >> 24 & 0xFF)).append((char)(tag >> 16 & 0xFF))
/* 72 */         .append((char)(tag >> 8 & 0xFF)).append((char)(tag & 0xFF)).append('/');
/*    */     } 
/* 74 */     return buff.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/k.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */