/*    */ package org.apache.xmlgraphics.image.codec.util;
/*    */ 
/*    */ import java.io.IOException;
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
/*    */ public class ImageInputStreamSeekableStreamAdapter
/*    */   extends SeekableStream
/*    */ {
/*    */   private ImageInputStream stream;
/*    */   
/*    */   public ImageInputStreamSeekableStreamAdapter(ImageInputStream stream) throws IOException {
/* 42 */     this.stream = stream;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSeekBackwards() {
/* 47 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getFilePointer() throws IOException {
/* 52 */     return this.stream.getStreamPosition();
/*    */   }
/*    */ 
/*    */   
/*    */   public void seek(long pos) throws IOException {
/* 57 */     this.stream.seek(pos);
/*    */   }
/*    */ 
/*    */   
/*    */   public int read() throws IOException {
/* 62 */     return this.stream.read();
/*    */   }
/*    */ 
/*    */   
/*    */   public int read(byte[] b, int off, int len) throws IOException {
/* 67 */     return this.stream.read(b, off, len);
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() throws IOException {
/* 72 */     super.close();
/* 73 */     this.stream.close();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/util/ImageInputStreamSeekableStreamAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */