/*    */ package org.apache.commons.collections.primitives.adapters.io;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ import org.apache.commons.collections.primitives.ByteIterator;
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
/*    */ public class ByteIteratorInputStream
/*    */   extends InputStream
/*    */ {
/*    */   private ByteIterator iterator;
/*    */   
/*    */   public ByteIteratorInputStream(ByteIterator in) {
/* 46 */     this.iterator = null;
/*    */     this.iterator = in;
/*    */   }
/*    */   
/*    */   public int read() {
/*    */     if (this.iterator.hasNext())
/*    */       return 0xFF & this.iterator.next(); 
/*    */     return -1;
/*    */   }
/*    */   
/*    */   public static InputStream adapt(ByteIterator in) {
/*    */     return (null == in) ? null : new ByteIteratorInputStream(in);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/io/ByteIteratorInputStream.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */