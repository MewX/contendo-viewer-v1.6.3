/*    */ package com.github.jaiimageio.impl.plugins.clib;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import javax.imageio.stream.ImageInputStream;
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
/*    */ public final class InputStreamAdapter
/*    */   extends InputStream
/*    */ {
/*    */   ImageInputStream stream;
/*    */   
/*    */   public InputStreamAdapter(ImageInputStream stream) {
/* 60 */     this.stream = stream;
/*    */   }
/*    */   
/*    */   public void close() throws IOException {
/* 64 */     this.stream.close();
/*    */   }
/*    */   
/*    */   public void mark(int readlimit) {
/* 68 */     this.stream.mark();
/*    */   }
/*    */   
/*    */   public boolean markSupported() {
/* 72 */     return true;
/*    */   }
/*    */   
/*    */   public int read() throws IOException {
/* 76 */     return this.stream.read();
/*    */   }
/*    */   
/*    */   public int read(byte[] b, int off, int len) throws IOException {
/* 80 */     return this.stream.read(b, off, len);
/*    */   }
/*    */   
/*    */   public void reset() throws IOException {
/* 84 */     this.stream.reset();
/*    */   }
/*    */   
/*    */   public long skip(long n) throws IOException {
/* 88 */     return this.stream.skipBytes(n);
/*    */   }
/*    */   
/*    */   public ImageInputStream getWrappedStream() {
/* 92 */     return this.stream;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/clib/InputStreamAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */