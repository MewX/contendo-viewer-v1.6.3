/*    */ package net.zamasoft.a.b;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
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
/*    */ public class F
/*    */   implements aa
/*    */ {
/* 30 */   private byte[] a = null;
/*    */   
/* 32 */   private int[] b = null;
/*    */   
/* 34 */   private short c = 0;
/*    */   
/*    */   protected F(i de, RandomAccessFile raf) throws IOException {
/* 37 */     synchronized (raf) {
/* 38 */       raf.seek(de.c());
/* 39 */       this.a = new byte[de.b()];
/* 40 */       raf.read(this.a);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void a(int numGlyphs, boolean shortEntries) {
/* 45 */     if (this.a == null) {
/*    */       return;
/*    */     }
/* 48 */     this.b = new int[numGlyphs + 1];
/* 49 */     ByteArrayInputStream bais = new ByteArrayInputStream(this.a);
/* 50 */     if (shortEntries) {
/* 51 */       this.c = 2;
/* 52 */       for (int i = 0; i <= numGlyphs; i++) {
/* 53 */         this.b[i] = bais.read() << 8 | bais.read();
/*    */       }
/*    */     } else {
/* 56 */       this.c = 1;
/* 57 */       for (int i = 0; i <= numGlyphs; i++) {
/* 58 */         this.b[i] = bais.read() << 24 | bais.read() << 16 | bais.read() << 8 | bais.read();
/*    */       }
/*    */     } 
/* 61 */     this.a = null;
/*    */   }
/*    */   
/*    */   public int a(int i) {
/* 65 */     if (this.b == null) {
/* 66 */       return 0;
/*    */     }
/* 68 */     return this.b[i] * this.c;
/*    */   }
/*    */   
/*    */   public int a() {
/* 72 */     return 1819239265;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/F.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */