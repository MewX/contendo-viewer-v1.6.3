/*    */ package org.apache.fontbox.ttf;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
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
/*    */ class TTCDataStream
/*    */   extends TTFDataStream
/*    */ {
/*    */   private final TTFDataStream stream;
/*    */   
/*    */   TTCDataStream(TTFDataStream stream) {
/* 34 */     this.stream = stream;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int read() throws IOException {
/* 40 */     return this.stream.read();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public long readLong() throws IOException {
/* 46 */     return this.stream.readLong();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int readUnsignedShort() throws IOException {
/* 52 */     return this.stream.readUnsignedShort();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public short readSignedShort() throws IOException {
/* 58 */     return this.stream.readSignedShort();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void close() throws IOException {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void seek(long pos) throws IOException {
/* 71 */     this.stream.seek(pos);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int read(byte[] b, int off, int len) throws IOException {
/* 77 */     return this.stream.read(b, off, len);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public long getCurrentPosition() throws IOException {
/* 83 */     return this.stream.getCurrentPosition();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public InputStream getOriginalData() throws IOException {
/* 89 */     return this.stream.getOriginalData();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public long getOriginalDataSize() {
/* 95 */     return this.stream.getOriginalDataSize();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/TTCDataStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */