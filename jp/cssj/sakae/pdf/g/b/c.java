/*    */ package jp.cssj.sakae.pdf.g.b;
/*    */ 
/*    */ import java.io.FilterOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import javax.crypto.Cipher;
/*    */ 
/*    */ public class c
/*    */   extends FilterOutputStream {
/*    */   private final Cipher a;
/* 11 */   private byte[] b = new byte[1];
/*    */   
/*    */   public c(OutputStream out, Cipher cipher) {
/* 14 */     super(out);
/* 15 */     this.a = cipher;
/*    */   }
/*    */   
/*    */   public void write(int x) throws IOException {
/* 19 */     this.b[0] = (byte)x;
/* 20 */     write(this.b, 0, 1);
/*    */   }
/*    */   
/*    */   public void write(byte[] bytes) throws IOException {
/* 24 */     write(bytes, 0, bytes.length);
/*    */   }
/*    */   
/*    */   public void write(byte[] bytes, int off, int len) throws IOException {
/* 28 */     byte[] block = this.a.update(bytes, off, len);
/* 29 */     if (block != null) {
/* 30 */       this.out.write(block);
/*    */     }
/*    */   }
/*    */   
/*    */   public void close() throws IOException {
/* 35 */     Exception ex = null;
/*    */     try {
/* 37 */       byte[] block = this.a.doFinal();
/* 38 */       if (block != null) {
/* 39 */         this.out.write(block);
/*    */       }
/* 41 */     } catch (Exception e) {
/* 42 */       ex = e;
/*    */     } finally {
/* 44 */       this.out.close();
/*    */     } 
/* 46 */     if (ex != null) {
/* 47 */       IOException ioe = new IOException();
/* 48 */       ioe.initCause(ex);
/* 49 */       throw ioe;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/g/b/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */