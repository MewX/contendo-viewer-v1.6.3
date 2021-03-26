/*    */ package org.apache.commons.io.input;
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
/*    */ public class DemuxInputStream
/*    */   extends InputStream
/*    */ {
/* 32 */   private final InheritableThreadLocal<InputStream> m_streams = new InheritableThreadLocal<>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public InputStream bindStream(InputStream input) {
/* 42 */     InputStream oldValue = this.m_streams.get();
/* 43 */     this.m_streams.set(input);
/* 44 */     return oldValue;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void close() throws IOException {
/* 56 */     InputStream input = this.m_streams.get();
/* 57 */     if (null != input)
/*    */     {
/* 59 */       input.close();
/*    */     }
/*    */   }
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
/*    */   public int read() throws IOException {
/* 73 */     InputStream input = this.m_streams.get();
/* 74 */     if (null != input)
/*    */     {
/* 76 */       return input.read();
/*    */     }
/*    */ 
/*    */     
/* 80 */     return -1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/input/DemuxInputStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */