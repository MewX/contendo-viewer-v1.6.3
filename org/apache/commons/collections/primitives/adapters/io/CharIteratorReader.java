/*    */ package org.apache.commons.collections.primitives.adapters.io;
/*    */ 
/*    */ import java.io.Reader;
/*    */ import org.apache.commons.collections.primitives.CharIterator;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CharIteratorReader
/*    */   extends Reader
/*    */ {
/*    */   private CharIterator iterator;
/*    */   
/*    */   public CharIteratorReader(CharIterator in) {
/* 54 */     this.iterator = null;
/*    */     this.iterator = in;
/*    */   }
/*    */   
/*    */   public int read(char[] buf, int off, int len) {
/*    */     if (this.iterator.hasNext()) {
/*    */       int count = 0;
/*    */       while (this.iterator.hasNext() && count < len) {
/*    */         buf[off + count] = this.iterator.next();
/*    */         count++;
/*    */       } 
/*    */       return count;
/*    */     } 
/*    */     return -1;
/*    */   }
/*    */   
/*    */   public void close() {}
/*    */   
/*    */   public static Reader adapt(CharIterator in) {
/*    */     return (null == in) ? null : new CharIteratorReader(in);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/io/CharIteratorReader.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */