/*    */ package org.apache.xmlgraphics.image.loader.util;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.imageio.stream.ImageInputStream;
/*    */ import org.apache.xmlgraphics.image.codec.util.SeekableStream;
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
/*    */ public class SeekableStreamAdapter
/*    */   extends SeekableStream
/*    */ {
/*    */   private ImageInputStream iin;
/*    */   
/*    */   public SeekableStreamAdapter(ImageInputStream iin) {
/* 40 */     this.iin = iin;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getFilePointer() throws IOException {
/* 45 */     return this.iin.getStreamPosition();
/*    */   }
/*    */ 
/*    */   
/*    */   public int read() throws IOException {
/* 50 */     return this.iin.read();
/*    */   }
/*    */ 
/*    */   
/*    */   public int read(byte[] b, int off, int len) throws IOException {
/* 55 */     return this.iin.read(b, off, len);
/*    */   }
/*    */ 
/*    */   
/*    */   public void seek(long pos) throws IOException {
/* 60 */     this.iin.seek(pos);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canSeekBackwards() {
/* 65 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public int skipBytes(int n) throws IOException {
/* 70 */     return this.iin.skipBytes(n);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/util/SeekableStreamAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */