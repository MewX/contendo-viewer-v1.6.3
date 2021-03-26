/*    */ package org.apache.xalan.xsltc.runtime.output;
/*    */ 
/*    */ import java.io.BufferedWriter;
/*    */ import java.io.IOException;
/*    */ import java.io.Writer;
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
/*    */ class WriterOutputBuffer
/*    */   implements OutputBuffer
/*    */ {
/*    */   private static final int KB = 1024;
/* 31 */   private static int BUFFER_SIZE = 4096;
/*    */   private Writer _writer;
/*    */   
/*    */   static {
/* 35 */     String osName = System.getProperty("os.name");
/* 36 */     if (osName.equalsIgnoreCase("solaris")) {
/* 37 */       BUFFER_SIZE = 32768;
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
/*    */   public WriterOutputBuffer(Writer writer) {
/* 50 */     this._writer = new BufferedWriter(writer, BUFFER_SIZE);
/*    */   }
/*    */   
/*    */   public String close() {
/*    */     
/* 55 */     try { this._writer.flush(); } catch (IOException e)
/*    */     
/*    */     { 
/* 58 */       throw new RuntimeException(e.toString()); }
/*    */     
/* 60 */     return "";
/*    */   }
/*    */   
/*    */   public OutputBuffer append(String s) {
/*    */     
/* 65 */     try { this._writer.write(s); } catch (IOException e)
/*    */     
/*    */     { 
/* 68 */       throw new RuntimeException(e.toString()); }
/*    */     
/* 70 */     return this;
/*    */   }
/*    */   
/*    */   public OutputBuffer append(char[] s, int from, int to) {
/*    */     
/* 75 */     try { this._writer.write(s, from, to); } catch (IOException e)
/*    */     
/*    */     { 
/* 78 */       throw new RuntimeException(e.toString()); }
/*    */     
/* 80 */     return this;
/*    */   }
/*    */   
/*    */   public OutputBuffer append(char ch) {
/*    */     
/* 85 */     try { this._writer.write(ch); } catch (IOException e)
/*    */     
/*    */     { 
/* 88 */       throw new RuntimeException(e.toString()); }
/*    */     
/* 90 */     return this;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/runtime/output/WriterOutputBuffer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */