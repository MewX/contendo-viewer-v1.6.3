/*    */ package org.apache.batik.util.io;
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
/*    */ public abstract class AbstractCharDecoder
/*    */   implements CharDecoder
/*    */ {
/*    */   protected static final int BUFFER_SIZE = 8192;
/*    */   protected InputStream inputStream;
/* 45 */   protected byte[] buffer = new byte[8192];
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int position;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int count;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected AbstractCharDecoder(InputStream is) {
/* 62 */     this.inputStream = is;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void dispose() throws IOException {
/* 69 */     this.inputStream.close();
/* 70 */     this.inputStream = null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void fillBuffer() throws IOException {
/* 77 */     this.count = this.inputStream.read(this.buffer, 0, 8192);
/* 78 */     this.position = 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void charError(String encoding) throws IOException {
/* 87 */     throw new IOException(Messages.formatMessage("invalid.char", new Object[] { encoding }));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void endOfStreamError(String encoding) throws IOException {
/* 97 */     throw new IOException(Messages.formatMessage("end.of.stream", new Object[] { encoding }));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/io/AbstractCharDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */