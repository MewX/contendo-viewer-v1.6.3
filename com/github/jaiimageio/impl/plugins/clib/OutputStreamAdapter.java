/*    */ package com.github.jaiimageio.impl.plugins.clib;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import javax.imageio.stream.ImageOutputStream;
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
/*    */ public final class OutputStreamAdapter
/*    */   extends OutputStream
/*    */ {
/*    */   ImageOutputStream stream;
/*    */   
/*    */   public OutputStreamAdapter(ImageOutputStream stream) {
/* 60 */     this.stream = stream;
/*    */   }
/*    */   
/*    */   public void close() throws IOException {
/* 64 */     this.stream.close();
/*    */   }
/*    */   
/*    */   public void write(byte[] b) throws IOException {
/* 68 */     this.stream.write(b);
/*    */   }
/*    */   
/*    */   public void write(byte[] b, int off, int len) throws IOException {
/* 72 */     this.stream.write(b, off, len);
/*    */   }
/*    */   
/*    */   public void write(int b) throws IOException {
/* 76 */     this.stream.write(b);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/clib/OutputStreamAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */