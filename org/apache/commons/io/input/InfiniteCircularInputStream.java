/*    */ package org.apache.commons.io.input;
/*    */ 
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
/*    */ public class InfiniteCircularInputStream
/*    */   extends InputStream
/*    */ {
/*    */   private final byte[] repeatedContent;
/* 33 */   private int position = -1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public InfiniteCircularInputStream(byte[] repeatedContent) {
/* 42 */     this.repeatedContent = repeatedContent;
/*    */   }
/*    */ 
/*    */   
/*    */   public int read() {
/* 47 */     this.position = (this.position + 1) % this.repeatedContent.length;
/* 48 */     return this.repeatedContent[this.position] & 0xFF;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/io/input/InfiniteCircularInputStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */