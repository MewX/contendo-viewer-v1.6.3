/*    */ package org.apache.batik.util.io;
/*    */ 
/*    */ import java.io.IOException;
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
/*    */ public class StringDecoder
/*    */   implements CharDecoder
/*    */ {
/*    */   protected String string;
/*    */   protected int length;
/*    */   protected int next;
/*    */   
/*    */   public StringDecoder(String s) {
/* 50 */     this.string = s;
/* 51 */     this.length = s.length();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int readChar() throws IOException {
/* 59 */     if (this.next == this.length) {
/* 60 */       return -1;
/*    */     }
/* 62 */     return this.string.charAt(this.next++);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void dispose() throws IOException {
/* 69 */     this.string = null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/io/StringDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */