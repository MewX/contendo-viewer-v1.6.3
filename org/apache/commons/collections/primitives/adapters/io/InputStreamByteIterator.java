/*    */ package org.apache.commons.collections.primitives.adapters.io;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.NoSuchElementException;
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
/*    */ public class InputStreamByteIterator
/*    */   implements ByteIterator
/*    */ {
/*    */   private InputStream stream;
/*    */   private boolean nextAvailable;
/*    */   private int next;
/*    */   
/*    */   public InputStreamByteIterator(InputStream in) {
/* 79 */     this.stream = null;
/* 80 */     this.nextAvailable = false;
/*    */     this.stream = in;
/*    */   }
/*    */   
/*    */   public boolean hasNext() {
/*    */     ensureNextAvailable();
/*    */     return (-1 != this.next);
/*    */   }
/*    */   
/*    */   public byte next() {
/*    */     if (!hasNext())
/*    */       throw new NoSuchElementException("No next element"); 
/*    */     this.nextAvailable = false;
/*    */     return (byte)this.next;
/*    */   }
/*    */   
/*    */   public void remove() throws UnsupportedOperationException {
/*    */     throw new UnsupportedOperationException("remove() is not supported here");
/*    */   }
/*    */   
/*    */   public static ByteIterator adapt(InputStream in) {
/*    */     return (null == in) ? null : new InputStreamByteIterator(in);
/*    */   }
/*    */   
/*    */   private void ensureNextAvailable() {
/*    */     if (!this.nextAvailable)
/*    */       readNext(); 
/*    */   }
/*    */   
/*    */   private void readNext() {
/*    */     try {
/*    */       this.next = this.stream.read();
/*    */       this.nextAvailable = true;
/*    */     } catch (IOException e) {
/*    */       throw new RuntimeException(e.toString());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/io/InputStreamByteIterator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */