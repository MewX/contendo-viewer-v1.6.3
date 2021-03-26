/*    */ package org.apache.xmlgraphics.image.loader.util;
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
/*    */ public class ImageInputStreamAdapter
/*    */   extends InputStream
/*    */ {
/*    */   private ImageInputStream iin;
/*    */   private long lastMarkPosition;
/*    */   
/*    */   public ImageInputStreamAdapter(ImageInputStream iin) {
/* 43 */     assert iin != null : "InputStream is null";
/* 44 */     this.iin = iin;
/*    */   }
/*    */ 
/*    */   
/*    */   public int read(byte[] b, int off, int len) throws IOException {
/* 49 */     return this.iin.read(b, off, len);
/*    */   }
/*    */ 
/*    */   
/*    */   public int read(byte[] b) throws IOException {
/* 54 */     return this.iin.read(b);
/*    */   }
/*    */ 
/*    */   
/*    */   public int read() throws IOException {
/* 59 */     return this.iin.read();
/*    */   }
/*    */ 
/*    */   
/*    */   public long skip(long n) throws IOException {
/* 64 */     return this.iin.skipBytes(n);
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() throws IOException {
/* 69 */     this.iin.close();
/* 70 */     this.iin = null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public synchronized void mark(int readlimit) {
/*    */     try {
/* 78 */       this.lastMarkPosition = this.iin.getStreamPosition();
/* 79 */     } catch (IOException ioe) {
/* 80 */       throw new RuntimeException("Unexpected IOException in ImageInputStream.getStreamPosition()", ioe);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean markSupported() {
/* 87 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized void reset() throws IOException {
/* 92 */     this.iin.seek(this.lastMarkPosition);
/*    */   }
/*    */ 
/*    */   
/*    */   public int available() throws IOException {
/* 97 */     return 0;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/util/ImageInputStreamAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */