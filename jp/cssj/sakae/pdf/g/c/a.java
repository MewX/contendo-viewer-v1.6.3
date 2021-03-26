/*    */ package jp.cssj.sakae.pdf.g.c;
/*    */ 
/*    */ import java.io.FilterOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ 
/*    */ public class a extends FilterOutputStream {
/*    */   protected final byte[] a;
/*  9 */   protected int b = 0;
/*    */   
/*    */   public a(OutputStream out, byte[] buf) {
/* 12 */     super(out);
/* 13 */     this.a = buf;
/*    */   }
/*    */   
/*    */   protected void a() throws IOException {
/* 17 */     this.out.write(this.a, 0, this.b);
/* 18 */     this.b = 0;
/*    */   }
/*    */   
/*    */   public void write(byte[] b, int off, int len) throws IOException {
/* 22 */     while (len > 0) {
/* 23 */       int i = this.a.length - this.b;
/* 24 */       if (i <= len) {
/* 25 */         System.arraycopy(b, off, this.a, this.b, i);
/* 26 */         this.b += i;
/* 27 */         off += i;
/* 28 */         len -= i;
/* 29 */         a(); continue;
/*    */       } 
/* 31 */       System.arraycopy(b, off, this.a, this.b, len);
/* 32 */       this.b += len;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void write(byte[] b) throws IOException {
/* 39 */     write(b, 0, b.length);
/*    */   }
/*    */   
/*    */   public void write(int b) throws IOException {
/* 43 */     this.a[this.b++] = (byte)b;
/* 44 */     if (this.b >= this.a.length) {
/* 45 */       a();
/*    */     }
/*    */   }
/*    */   
/*    */   public void flush() throws IOException {
/* 50 */     if (this.b > 0) {
/* 51 */       a();
/*    */     }
/* 53 */     this.out.flush();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/g/c/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */