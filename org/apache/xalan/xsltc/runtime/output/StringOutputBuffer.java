/*    */ package org.apache.xalan.xsltc.runtime.output;
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
/*    */ class StringOutputBuffer
/*    */   implements OutputBuffer
/*    */ {
/* 30 */   private StringBuffer _buffer = new StringBuffer();
/*    */ 
/*    */   
/*    */   public String close() {
/* 34 */     return this._buffer.toString();
/*    */   }
/*    */   
/*    */   public OutputBuffer append(String s) {
/* 38 */     this._buffer.append(s);
/* 39 */     return this;
/*    */   }
/*    */   
/*    */   public OutputBuffer append(char[] s, int from, int to) {
/* 43 */     this._buffer.append(s, from, to);
/* 44 */     return this;
/*    */   }
/*    */   
/*    */   public OutputBuffer append(char ch) {
/* 48 */     this._buffer.append(ch);
/* 49 */     return this;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/runtime/output/StringOutputBuffer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */